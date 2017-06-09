package mdatum.udc.com.m_datum.encuestaAgroquimicos;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mdatum.udc.com.m_datum.R;


public class EstablecimientoActivity extends AppCompatActivity {

    private String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiento);

        //Arreglo que carga el spinner de Régimen de Tenencia de Tierra
        List<String> regTenenciaArray = new ArrayList<String>();
        regTenenciaArray.add("Propiedad");
        regTenenciaArray.add("Sucesión indivisa");
        regTenenciaArray.add("Arrendatario");
        regTenenciaArray.add("Otro");


        name = Environment.getExternalStorageDirectory()+"/establecimiento.jpg";

        Button btnFoto = (Button) findViewById(R.id.btn_foto);
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri output = Uri.fromFile(new File(name));
                intent.putExtra(MediaStore.EXTRA_OUTPUT,output);

                startActivityForResult(intent,1);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (data != null){
            if (data.hasExtra("data")){
                ImageView ivEstablecimiento = (ImageView) findViewById(R.id.iv_establecimiento);
                ivEstablecimiento.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
                Log.d("HAS EXTRA","entra en has extra");
            }else{
                Log.d("HAS EXTRA","no entra en has extra");
                ImageView ivEstablecimiento = (ImageView) findViewById(R.id.iv_establecimiento);
                ivEstablecimiento.setImageBitmap(BitmapFactory.decodeFile(name));

                new MediaScannerConnection.MediaScannerConnectionClient(){
                  private MediaScannerConnection msc = null;{
                        msc = new MediaScannerConnection(getApplicationContext(),this);msc.connect();
                    }
                    public void onMediaScannerConnected(){
                        msc.scanFile(name,null);
                    }
                    public void onScanCompleted(String path,Uri uri){
                        msc.disconnect();
                    }
                };
            }
        }
    }

}



