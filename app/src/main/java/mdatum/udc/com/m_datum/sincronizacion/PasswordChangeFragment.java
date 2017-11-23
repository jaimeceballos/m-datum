package mdatum.udc.com.m_datum.sincronizacion;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mdatum.udc.com.m_datum.MDatumController;
import mdatum.udc.com.m_datum.MainActivity;
import mdatum.udc.com.m_datum.R;
import mdatum.udc.com.m_datum.data.prefs.SessionPrefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static mdatum.udc.com.m_datum.data.prefs.SessionPrefs.PREF_USER_TOKEN;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordChangeFragment extends Fragment {

    private WebDatumApi webDatumApi;
    private EditText et_new_password1, et_new_password2;
    private Button btn_change;

    public PasswordChangeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_password_change, container, false);

        webDatumApi = ((MDatumController)getActivity().getApplication()).getApiSession();

        et_new_password1 = (EditText) rootView.findViewById(R.id.et_new_password1);
        et_new_password2 = (EditText) rootView.findViewById(R.id.et_new_password2);
        btn_change = (Button) rootView.findViewById(R.id.btn_change);

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verificar_password(et_new_password1.getText().toString(),et_new_password2.getText().toString())){
                    cambiarPassword();
                }else{
                    Toast.makeText(getContext(), "Verique las contraseñas ingresadas.", Toast.LENGTH_LONG).show();
                }
            }
        });


        return  rootView;
    }

    private boolean verificar_password(String pass1, String pass2){

        if(!pass1.equals("") && !pass2.equals("")){
            if(pass1.equals(pass2)) {
                return true;
            }
        }
        return false;
    }

    public void cambiarPassword(){
        String password1 = et_new_password1.getText().toString();
        String password2 = et_new_password2.getText().toString();
        SharedPreferences prefs = getContext().getSharedPreferences("MDATUM_PREFS", Activity.MODE_PRIVATE);

        Call<Void> changePass = webDatumApi.password_change("Token "+prefs.getString("PREF_USER_TOKEN",null),new PasswordChangeBody(password1,password2));

        changePass.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    startActivity(new Intent(getContext(),MainActivity.class));
                    getActivity().finish();
                }
                else{
                    Toast.makeText(getContext(),"Ocurrio un error al intentar cambiar la contraseña.",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

}
