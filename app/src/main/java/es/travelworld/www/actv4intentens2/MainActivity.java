package es.travelworld.www.actv4intentens2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import es.travelworld.www.actv4intentens2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private final ActivityResultLauncher<Intent>cameraLauncher =registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result->{
                if (result.getResultCode()==RESULT_OK && result.getData()!=null){

                    Bundle extras =result.getData().getExtras();
                    Bitmap imageBitmap = (Bitmap)extras.get("data");
                    binding.ivRegistro.setImageBitmap(imageBitmap);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Usuario usuario=new Usuario("","",0);
        binding.setUser(usuario);
        binding.btMeapunto.setEnabled(false);
        binding.ibCamara.setEnabled(true);



        (binding.btMeapunto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre =binding.textinputedittexNombre.getText().toString();
                String apellido= binding.textinputedittexApellido.getText().toString();
                Snackbar.make(view,"Bienvenido".toString(),Snackbar.LENGTH_LONG).show();
                binding.setUser(new Usuario());
            }

        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opcionesEdad, android.R.layout.simple_spinner_item);
        binding.spEdad.setAdapter(adapter);
        binding.spEdad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String seleccion=adapterView.getItemAtPosition(i).toString();
                Snackbar.make(view,adapterView.getItemAtPosition(i).toString(),Snackbar.LENGTH_LONG).show();

                switch (seleccion){
                    case "18-99":
                        binding.btMeapunto.setEnabled(true);
                        break;
                    case "0-5":
                        binding.btMeapunto.setEnabled(false);
                        break;

                }
                Toast.makeText(MainActivity.this,"solo válido si eres mayor de edad",Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.tvCondiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://developers.google.com/ml-kit/terms";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);

                }
                Toast.makeText(MainActivity.this, "no se puede abrir el navegador", Toast.LENGTH_SHORT).show();
            }
        });


        TextWatcher textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(android.text.Editable editable) {
            if (editable.toString().contains("!")||editable.toString().contains("@")){
                binding.textinputedittexNombre.setError("Los caracteres especiales no estan permitidos");
                binding.textinputedittexApellido.setError("Los caracteres especiales no estan permitidos");
                }
            }
        };
        binding.textinputedittexNombre.addTextChangedListener(textWatcher);
        binding.textinputedittexApellido.addTextChangedListener(textWatcher);

        //aqui he intentando desarrollar el código de la cámara con un Intent. el activityResultLauncherCamara no me lo reconoce//
        binding.ibCamara.setOnClickListener(view -> abrirCamara());
        activityResultLauncherCamara=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->{
            if (result.getResultCode()==RESULT_OK){
                Intent data =result.getData();
                Bitmap imageBimap =(Bitmap) data.getExtras().get("data");
                imageButton.setImageBitmap(imageBimap); //como puedo referenciar el imagebutton para que lo reconozca?//
            }
        });
    }
    private void abrirCamara(){
        Intent sacarFotoIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (sacarFotoIntent.resolveActivity(getPackageManager())!=null){

        cameraLauncher.launch(sacarFotoIntent);
        }

    }
    private void dispatchTakePictureIntent(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){//el concepto CAMERA no reconoce//
            Log.d("depurando","No tengo permiso para la camara");
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        }else{

            Log.d("depurando","Tengo permiso para la cámara");
            Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            activityResultLaunvherCamara.launch(intent);
        }
    }

}