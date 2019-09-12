package com.baboomer.baboomer.services;

import com.baboomer.baboomer.interfaces.BaboomerInterface;
import com.baboomer.baboomer.models.Comunicado;
import com.baboomer.baboomer.utils.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetComunicadosService {

    public static void startService(final ComunicadosInterface callback){

        BaboomerInterface service = RetrofitInstance
                .getInstance()
                .create(BaboomerInterface.class);

        Call<List<Comunicado>> call = service.getComunicados();
        call.enqueue(new Callback<List<Comunicado>>() {
            @Override
            public void onResponse(Call<List<Comunicado>> call, Response<List<Comunicado>> response) {
                if (response.isSuccessful()){
                    callback.onGetComunicadosSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Comunicado>> call, Throwable t) {

            }
        });
    }

    public interface ComunicadosInterface{
        void onGetComunicadosSuccess(List<Comunicado> comunicados);
    }
}
