package com.baboomer.baboomer.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.baboomer.baboomer.R;
import com.baboomer.baboomer.adapters.ContentAdapter;
import com.baboomer.baboomer.dialogs.CerrarSesionDialog;
import com.baboomer.baboomer.models.Comunicado;
import com.baboomer.baboomer.services.GetComunicadosService;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements GetComunicadosService.ComunicadosInterface, ContentAdapter.ItemInterface, View.OnClickListener {

    private RecyclerView rvContenido;
    private ImageView ivCerrarSesion;
    private ContentAdapter adapter;
    private List<Comunicado> comunicados;
    private ProgressBar progressBar;
    private CerrarSesionDialog cerrarSesionDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rvContenido = findViewById(R.id.rv_content);
        progressBar = findViewById(R.id.pb_home);
        ivCerrarSesion = findViewById(R.id.iv_logout);
        ivCerrarSesion.setOnClickListener(this);
    }

    @Override
    public void onGetComunicadosSuccess(List<Comunicado> comunicados) {
        this.comunicados = comunicados;
        rvContenido.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContentAdapter(comunicados,this);
        adapter.setClickListener(this);
        rvContenido.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
        rvContenido.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClicked(Comunicado comunicado) {
        Intent intent = new Intent(this, DetalleActivity.class);

        intent.putExtra(DetalleActivity.INTENT_ID, comunicado.getId());
        intent.putExtra(DetalleActivity.INTENT_TIPO, comunicado.getTipoComunicado());
        intent.putExtra(DetalleActivity.INTENT_MENSAJE, comunicado.getMensaje());
        intent.putExtra(DetalleActivity.INTENT_FECHA, comunicado.getFecha());
        intent.putExtra(DetalleActivity.INTENT_NOMBRE, comunicado.getAutorComunicado().getNombre());

        startActivity(intent);

    }

    private void showCerrarSesionDialog() {
        if(cerrarSesionDialog != null){
            cerrarSesionDialog.show(getSupportFragmentManager(), "CerrarSesionDialog");
        } else {
            cerrarSesionDialog = CerrarSesionDialog.create();
            cerrarSesionDialog.setClickListener(this);
            showCerrarSesionDialog();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ivCerrarSesion.getId()){
            showCerrarSesionDialog();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        GetComunicadosService.startService(this);
    }
}
