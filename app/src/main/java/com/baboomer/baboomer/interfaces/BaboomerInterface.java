package com.baboomer.baboomer.interfaces;

import com.baboomer.baboomer.models.Comunicado;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaboomerInterface {

    @GET("/api/comunicado")
    Call<List<Comunicado>> getComunicados();

    @POST("/api/tutor/login")
    Call<JsonObject> login(
            @Body JsonObject body
    );

    @GET("/api/comunicado/enterado/{ComunicadoId}")
    Call<JsonObject> enterado(
            @Path("ComunicadoId") int comunicadoId
    );
}
