package com.baboomer.baboomer.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baboomer.baboomer.R;
import com.baboomer.baboomer.models.Comunicado;
import com.bumptech.glide.Glide;

import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {

    private List<Comunicado> comunicados;
    private Context context;
    private ItemInterface itemInterface;

    public ContentAdapter(List<Comunicado> comunicados, Context context) {
        this.comunicados = comunicados;
        this.context = context;
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ContentViewHolder viewHolder = null;

        switch (viewType){
            case Comunicado.NOTIFICACION_ENFERMO:
            case Comunicado.NOTIFICACION_CONDUCTA:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_notificacion, parent, false);
                viewHolder = new ContentViewHolder(view,viewType);
                break;

                case Comunicado.ANUNCIO:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_anuncio, parent, false);
                viewHolder = new ContentViewHolder(view,viewType);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        final Comunicado comunicado = comunicados.get(position);

        if (comunicado.getTipoComunicado() != Comunicado.ANUNCIO){
            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemInterface.onItemClicked(comunicado);
                }
            });
            if (comunicado.getTipoComunicado() == Comunicado.NOTIFICACION_ENFERMO){
                holder.tvTitulo.setText(context.getResources().getString(R.string.notif_title_enfermo));
                holder.container.setBackground(context.getResources().getDrawable(R.drawable.bg_notificacion_enfermo));
            } else {
                holder.tvTitulo.setText(context.getResources().getString(R.string.notif_title_conducta));
                holder.container.setBackground(context.getResources().getDrawable(R.drawable.bg_notificacion_conducta));
            }
        } else {
            if (comunicado.getImagen() != null){
                Glide.with(context).load(comunicado.getImagen()).into(holder.ivImagen);
                holder.ivImagen.setVisibility(View.VISIBLE);
            }
            holder.tvHora.setText(comunicado.getFecha());
        }
        holder.tvDescripcion.setText(comunicado.getMensaje());

    }

    @Override
    public int getItemCount() {
        return comunicados.size();
    }

    @Override
    public int getItemViewType(int position) {
        return comunicados.get(position).getTipoComunicado();
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitulo, tvDescripcion, tvHora;
        public ImageView ivImagen;
        public LinearLayout container;

        public ContentViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == Comunicado.NOTIFICACION_ENFERMO
                    || viewType == Comunicado.NOTIFICACION_CONDUCTA) {
                tvTitulo = itemView.findViewById(R.id.tv_notificacion_titulo);
                tvDescripcion = itemView.findViewById(R.id.tv_notificacion_descripcion);
                container = itemView.findViewById(R.id.notificacion_container);
            } else if (viewType == Comunicado.ANUNCIO) {
                tvTitulo = itemView.findViewById(R.id.tv_anuncio_title);
                tvDescripcion = itemView.findViewById(R.id.tv_anuncio_descripcion);
                tvHora = itemView.findViewById(R.id.tv_anuncio_hora);
                ivImagen = itemView.findViewById(R.id.iv_anuncio_imagen);
                container = itemView.findViewById(R.id.cv_anuncio_card);
            }
        }
    }

    public void setClickListener(ItemInterface itemInterface) {
        this.itemInterface = itemInterface;
    }

    public interface ItemInterface{
        void onItemClicked(Comunicado comunicado);
    }
}
