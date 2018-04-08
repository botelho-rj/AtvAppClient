package com.example.mainaccount.atvappclient.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.mainaccount.atvappclient.Adapter.LivroAdapter;
import com.example.mainaccount.atvappclient.Livro;
import com.example.mainaccount.atvappclient.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListarActivity extends Activity {

    private ListView list;
    private ArrayAdapter<Livro> adapter;
    private ArrayList<Livro> livros;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListener;

    private FirebaseDatabase firebaseDatabase;
    private LivroAdapter livroAdapter;
    private String codigoLivro;
    private Button btn_favoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        livros = new ArrayList<>();
        list = (ListView) findViewById(R.id.list);
        list.setOnLongClickListener(longClickListener);

        adapter = new LivroAdapter(this, livros);
        list.setAdapter(adapter);
        firebase = FirebaseDatabase.getInstance().getReference();
        firebase = firebase.child("addlivro");

        btn_favoritos = (Button) findViewById(R.id.btn_favoritos);
        btn_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarActivity.this, FavoritosActivity.class);
                startActivity(intent);
            }
        });

    }

    private AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.d("Chave: ", livroAdapter.getLivros().get(i).getKey());
            firebase = firebaseDatabase.getReference("livro/"+livroAdapter.getLivros().get(i).getKey());
            codigoLivro = livroAdapter.getLivros().get(i).getKey();

            firebase.addListenerForSingleValueEvent(valueEventListenerFavoritos);


            return true;
        }
    };

    ValueEventListener valueEventListenerFavoritos = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            livros.clear();

            for(DataSnapshot dados : dataSnapshot.getChildren()){
                Livro novoLivro = dados.getValue(Livro.class);
                livros.add(novoLivro);
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListener);
    }
}
