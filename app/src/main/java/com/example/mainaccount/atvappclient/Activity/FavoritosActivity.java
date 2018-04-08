package com.example.mainaccount.atvappclient.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mainaccount.atvappclient.R;

public class FavoritosActivity extends Activity {

    private TextView txt_favoritos;
    private ListView list_favoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
    }
}
