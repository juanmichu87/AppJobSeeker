package com.example.testmenu.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.testmenu.R;
import com.example.testmenu.adapters.PostsAdapter;
import com.example.testmenu.adapters.PostsAdapter2;
import com.example.testmenu.entidades.Publicacion;
import com.example.testmenu.firebase.AutentificacioFirebase;
import com.example.testmenu.firebase.PublicacionFirebase;
import com.example.testmenu.utils.ViewedMensajeHelper;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class MisOfertasActivity2 extends AppCompatActivity {

    private Toolbar mToolbar;
    RecyclerView recyclerView;
    ImageButton btnSalir;

    AutentificacioFirebase mAutentificacionFirebase;
    PublicacionFirebase mPublicacionfirebase;
    PostsAdapter2 mPostsAdapter2;


    private String idUserCarta;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_ofertas);

        idUserCarta = getIntent().getStringExtra("idUserCarta");
        mToolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerViewInicio);
        btnSalir = findViewById(R.id.volverAtrasMisOfertas);

        mAutentificacionFirebase = new AutentificacioFirebase();
        mPublicacionfirebase = new PublicacionFirebase();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnSalir.setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Query query = mPublicacionfirebase.getPublicacionDeUsuario(idUserCarta);
        FirestoreRecyclerOptions<Publicacion> options = new FirestoreRecyclerOptions.Builder<Publicacion>()
                .setQuery(query, Publicacion.class)
                .build();

        mPostsAdapter2 = new PostsAdapter2(options, this);
        recyclerView.setAdapter(mPostsAdapter2);
        mPostsAdapter2.startListening();
        ViewedMensajeHelper.updateOnline(true,MisOfertasActivity2.this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPostsAdapter2.stopListening();
    }
    @Override
    protected void onPause() {
        super.onPause();
        ViewedMensajeHelper.updateOnline(false,MisOfertasActivity2.this);
    }

}