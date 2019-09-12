package com.baboomer.baboomer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baboomer.baboomer.R;
import com.baboomer.baboomer.models.Comunicado;
import com.baboomer.baboomer.services.EnteradoService;

public class DetalleActivity extends AppCompatActivity implements View.OnClickListener, EnteradoService.EnteradoInterface {

    public static final String INTENT_ID = "ID";
    public static final String INTENT_TIPO = "TIPO";
    public static final String INTENT_FECHA = "FECHA";
    public static final String INTENT_MENSAJE = "MENSAJE";
    public static final String INTENT_NOMBRE = "NOMBRE";

    private TextView tvTitulo, tvFecha, tvMensaje, tvNombre, tvDepto;
    private Button btnEnterado;
    private ImageView ivClose;
    private LinearLayout llHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        tvTitulo = findViewById(R.id.tv_detalle_titulo);
        tvFecha = findViewById(R.id.tv_detalle_fecha);
        tvMensaje = findViewById(R.id.tv_detalle_mensaje);
        tvNombre = findViewById(R.id.tv_detalle_nombre);
        tvDepto = findViewById(R.id.tv_detalle_departamento);
        btnEnterado = findViewById(R.id.btn_detalle_enterado);
        ivClose = findViewById(R.id.iv_detalle_cerrar);
        llHeader = findViewById(R.id.ll_detalle_header);

        ivClose.setOnClickListener(this);
        btnEnterado.setOnClickListener(this);

        if (getIntent().getIntExtra(INTENT_TIPO,1) == Comunicado.NOTIFICACION_ENFERMO){
            tvTitulo.setText(getResources().getString(R.string.notif_title_enfermo));
            tvDepto.setText("Enfermería");
            llHeader.setBackground(getResources().getDrawable(R.drawable.bg_notificacion_enfermo));
        } else {
            tvTitulo.setText(getResources().getString(R.string.notif_title_conducta));
            tvDepto.setText("Dirección");
            llHeader.setBackground(getResources().getDrawable(R.drawable.bg_notificacion_conducta));
        }
        tvFecha.setText(getIntent().getStringExtra(INTENT_FECHA));
        tvNombre.setText(getIntent().getStringExtra(INTENT_NOMBRE));
        tvMensaje.setText(getIntent().getStringExtra(INTENT_MENSAJE));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ivClose.getId()){
            finish();
        } else {
            EnteradoService.startService(getIntent().getIntExtra(INTENT_ID,0),this);
        }
    }

    @Override
    public void onEnterado() {
        finish();
    }
}
