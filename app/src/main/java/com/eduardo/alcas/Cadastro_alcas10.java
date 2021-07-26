package com.eduardo.alcas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Cadastro_alcas10 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private EditText quantidade;
    private Button cadastrar;
    private  String selecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_alcas10);
        IniciarComponenentes();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalvarDados();
                Toast.makeText(getApplicationContext(),"Cadastrado com sucesso", Toast.LENGTH_SHORT).show();

            }
        });

     }
    private void SalvarDados(){
        Alcas10 alcas10 = new Alcas10();
        alcas10.cor = selecionado;
        alcas10.quantidade = quantidade.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String,Object> usuarios = new HashMap<>();
        usuarios.put("cor",alcas10.getCor());
        usuarios.put("quantidade",alcas10.getQuantidade());
        // UsuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Alcas 10").document(selecionado);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db","Sucesso ao Salvar os Dados");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("db","Erro ao salvar os dados" + e.toString());

                    }
                });

    }

    public void IniciarComponenentes(){
        quantidade = findViewById(R.id.edt_quantidade);
        cadastrar = findViewById(R.id.bt_cadastrar);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selecionado = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}