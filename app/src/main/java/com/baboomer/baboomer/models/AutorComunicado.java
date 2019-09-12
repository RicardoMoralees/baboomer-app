package com.baboomer.baboomer.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AutorComunicado implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("tipoUsuario")
    private String tipoUsuario;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("email")
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
