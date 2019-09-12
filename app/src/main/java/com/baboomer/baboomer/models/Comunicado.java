package com.baboomer.baboomer.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Comunicado implements Serializable {

    public static final int ANUNCIO = 0;
    public static final int NOTIFICACION_ENFERMO = 1;
    public static final int NOTIFICACION_CONDUCTA = 2;

    @SerializedName("id")
    private int id;

    @SerializedName("autorComunicado")
    private AutorComunicado autorComunicado;

    @SerializedName("mensaje")
    private String mensaje;

    @SerializedName("imagen")
    private String imagen;

    @SerializedName("fecha")
    private String fecha;

    @SerializedName("tipoComunicado")
    private int tipoComunicado;

    @SerializedName("enterado")
    private String enterado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public AutorComunicado getAutorComunicado() {
        return autorComunicado;
    }

    public void setAutorComunicado(AutorComunicado autor) {
        this.autorComunicado = autor;
    }

    public int getTipoComunicado() {
        return tipoComunicado;
    }

    public void setTipoComunicado(int tipoComunicado) {
        this.tipoComunicado = tipoComunicado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEnterado() {
        return enterado;
    }

    public void setEnterado(String enterado) {
        this.enterado = enterado;
    }
}
