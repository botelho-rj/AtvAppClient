package com.example.mainaccount.atvappclient.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mainaccount.atvappclient.Livro;
import com.example.mainaccount.atvappclient.R;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class LivroAdapter extends ArrayAdapter<Livro>{


    private ArrayList<Livro> livros;
    private Context contexto;
    private String codigo;
    FirebaseStorage firebaseStorage;


    public LivroAdapter(Context context,ArrayList<Livro> objects) {
        super(context, 0, objects);
        this.contexto = context;
        this.livros = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        firebaseStorage = FirebaseStorage.getInstance();
        View view = null;

        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.listalivro, parent, false);

        TextView txtLivro = (TextView) view.findViewById(R.id.txtLivro);
        TextView txtAutor = (TextView) view.findViewById(R.id.txtAutor);
        TextView key = (TextView) view.findViewById(R.id.txtCodigo);

        Livro livro = livros.get(position);

        txtLivro.setText("Livro: "+livro.getLivro());
        txtAutor.setText("Autor: "+livro.getAutor());
        key.setText(livro.getKey());

        return view;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }
}
