package es.travelworld.www.actv4intentens2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import es.travelworld.www.actv4intentens2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Usuario usuario=new Usuario("","","");
        binding.setUser(usuario);
        binding.btMeapunto.setEnabled(false);//¿es asi como se inhabilita el boton?//




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
                //aqui cuando hago el (binding.setUser(adapterView.getItemAtPosition(i));) el i me da error y no se que parametro usar//
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        /*En estas lineas de código, el usuario del array adapter me sale error. al igual que el parametro i en el spinner.
        ArrayList<Usuario> usuarios=new ArrayList<>();
        usuarios.add(new Usuario("Carlos","Perez","30"));
        usuarios.add(new Usuario("Marta","Lopez","22"));
        ArrayAdapter<Usuario>adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item.usuario);


        binding.spEdad.setAdapter(adapter);
        binding.spEdad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                binding.setUser(adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

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


        //aqui quiero colocar los caracteres especiales para que salga como error. he utilizado el binding para llamarlo, pero nose si tengo que utilizar algun metodo para poder llamarlos//
            binding.textinputedittexNombre.getText().toString();
            binding.textinputedittexApellido.getText().toString();
            char[]specialCaracteres = { '!','@','#','$','%','&'};
            Toast.makeText(this,"Los caracteres especiales no estan permitidos",Toast.LENGTH_LONG).show();




    }
}