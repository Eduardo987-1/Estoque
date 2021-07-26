package com.eduardo.alcas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eduardo.alcas.adapter.Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Consulta_alca10 extends AppCompatActivity {
    private RecyclerView recyclerView;
    ArrayList<Alcas10> alcas10ArrayList;
    Adapter adapter;
    FirebaseFirestore db;
    private String m_Text = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_alca10);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        alcas10ArrayList = new ArrayList<Alcas10>();
        adapter = new Adapter(Consulta_alca10.this,alcas10ArrayList);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));


        recyclerView.setAdapter(adapter);
        EventChengeListener();

        //evento de clique
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener
                (getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Alcas10 alcas10 = alcas10ArrayList.get(position);
                            AlertDialog.Builder alert = new AlertDialog.Builder(Consulta_alca10.this);
                            alert.setTitle("Mensagem");
                            alert.setMessage("Quer excluir "+alcas10.getCor()+" ?");
                            alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    db.collection("Alcas 10").whereEqualTo("cor", alcas10.getCor())
                                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete( Task<QuerySnapshot> task) {
                                            if (task.isSuccessful() && !task.getResult().isEmpty()) {
                                                DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                                String documentId = documentSnapshot.getId();
                                                db.collection("Alcas 10")
                                                        .document(documentId)
                                                        .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(Consulta_alca10.this,"Item excluido",Toast.LENGTH_SHORT).show();
                                                        finish();
                                                        overridePendingTransition(0, 0);
                                                        startActivity(getIntent());
                                                        overridePendingTransition(0, 0);
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure( Exception e) {


                                                    }
                                                });

                                            }
                                        }
                                    });
                                }
                });
                alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Consulta_alca10.this,"Não foi possivel excluir",Toast.LENGTH_SHORT).show();

                    }
                });
                alert.create().show();
            }
            @Override
            public void onLongItemClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Consulta_alca10.this);
                builder.setTitle("Digite a nova quantidade");
                // Set up the input
                final EditText input = new EditText(Consulta_alca10.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);
                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        Alcas10 alcas10 = alcas10ArrayList.get(position);
                        Map<String,Object> usuarios = new HashMap<>();
                        usuarios.put("quantidade",m_Text);
                        db.collection("Alcas 10")
                                .whereEqualTo("quantidade",alcas10.getQuantidade())
                                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful() && !task.getResult().isEmpty()) {
                                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                    db.collection("Alcas 10")
                                            .document(alcas10.getCor())
                                            .update(usuarios)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(Consulta_alca10.this, "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                    overridePendingTransition(0, 0);
                                                    startActivity(getIntent());
                                                    overridePendingTransition(0, 0);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull @NotNull Exception e) {
                                            Toast.makeText(Consulta_alca10.this, "Same error ocurred", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }else{
                                    Toast.makeText(Consulta_alca10.this, "Failed", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();


                        }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        }));





    }
    private  void EventChengeListener(){
        db.collection("Alcas 10").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException error) {
                if (error != null){

                    Log.e("Firebase error",error.getMessage());
                    return;
                }

                for (DocumentChange dc :value.getDocumentChanges()){
                    if (dc.getType() == DocumentChange.Type.ADDED){
                        alcas10ArrayList.add(dc.getDocument().toObject(Alcas10.class));

                    }
                    adapter.notifyDataSetChanged();


                }

            }
        });

    }
}