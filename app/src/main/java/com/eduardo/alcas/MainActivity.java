package com.eduardo.alcas;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
        private EditText edt_Nome,edt_Quantidade;
        private Button bt_cadastrar_10,bt_consulta_10,bt_Cadastrar_13,bt_consulta_13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IniciarComponentes();
        bt_cadastrar_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Cadastro_alcas10.class);
                startActivity(intent);

            }
        });
        bt_consulta_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Consulta_alca10.class);
                startActivity(intent);

            }
        });

        bt_Cadastrar_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Cadastro_alcas_13.class);
                startActivity(intent);

            }
        });
        bt_consulta_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,Consulta_alca13.class);
                startActivity(intent);

            }
        });



    }
    private void IniciarComponentes(){
        //edt_Nome = findViewById(R.id.edt_Nome);
        bt_cadastrar_10 = findViewById(R.id.bt_cadastrar_10);
        bt_consulta_10 = findViewById(R.id.bt_consulta_10);
        bt_Cadastrar_13 = findViewById(R.id.bt_cadastrar_13);
        bt_consulta_13 = findViewById(R.id.bt_consulta_13);

    }
}