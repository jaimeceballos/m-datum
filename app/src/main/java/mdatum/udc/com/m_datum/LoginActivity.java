package mdatum.udc.com.m_datum;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mdatum.udc.com.m_datum.sincronizacion.ApiError;
import mdatum.udc.com.m_datum.sincronizacion.LoginBody;
import mdatum.udc.com.m_datum.sincronizacion.UserToken;
import mdatum.udc.com.m_datum.sincronizacion.Usuario;
import mdatum.udc.com.m_datum.sincronizacion.WebDatumApi;
import mdatum.udc.com.m_datum.data.prefs.SessionPrefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {




    private WebDatumApi mWebDatumApi;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        prefs = getApplicationContext().getSharedPreferences("MDATUM_PREFS", Activity.MODE_PRIVATE);

        //Crear conexion a la API de WebDatum
        mWebDatumApi = ((MDatumController)getApplication()).getApiSession();

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    if(!((MDatumController)getApplication()).isOnline()){
                        showLoginError("Error de red");
                        return false;
                    }
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!((MDatumController)getApplication()).isOnline()){
                    showLoginError("Error de red");
                    return;
                }
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            //Llamada a la API
            Call<UserToken> loginCall = mWebDatumApi.login(new LoginBody(email,password));
            loginCall.enqueue(new Callback<UserToken>() {
                @Override
                public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                    //mostrar progreso
                    showProgress(false);

                    //procesar errores
                    if(!response.isSuccessful()){
                        String error = "Se produjo un error al intentar autenticar.";
                        if (response.errorBody().contentType().subtype().equals("json")){
                            ApiError apiError = ApiError.fromResponseBody(response.errorBody());
                            if(apiError.getDetail()==null && apiError.getNon_field_errors().size() > 0){
                                for(int i = 0; i < apiError.getNon_field_errors().size(); i++){
                                    if(apiError.getNon_field_errors().get(i).equals("Unable to log in with provided credentials."))
                                        error = "Usuario o password incorrectos.";
                                }
                            }else{
                                error = apiError.getDetail();
                            }

                            Log.d("LoginActivity",error);

                        }else{
                            error = response.message();
                        }
                        showLoginError(error);
                        return;
                    }
                    //Guardar en preferencias
                    SessionPrefs.get(LoginActivity.this).saveToken(response.body());
                    //Dirigir al Activity
                    showSplashScreen();
                }

                @Override
                public void onFailure(Call<UserToken> call, Throwable t) {
                    showProgress(false);
                    showLoginError(t.getMessage());
                }
            });
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }



    private void showLoginError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    private void showSplashScreen(){
        obtenerUsuario();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    private boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnected();
    }

    private void obtenerUsuario(){
        Call<Usuario> usuario = mWebDatumApi.usuario("Token "+prefs.getString("PREF_USER_TOKEN",null));
        usuario.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    Usuario user = response.body();
                    SessionPrefs.get(LoginActivity.this).setUserLoged(Integer.parseInt(user.getPk()));

                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }

}

