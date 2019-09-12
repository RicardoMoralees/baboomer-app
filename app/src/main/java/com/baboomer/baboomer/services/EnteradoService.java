package com.baboomer.baboomer.services;

import android.util.Log;

import com.baboomer.baboomer.interfaces.BaboomerInterface;
import com.baboomer.baboomer.utils.RetrofitInstance;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnteradoService {

    public static void startService(int enterado, final EnteradoInterface callback){

        BaboomerInterface service = RetrofitInstance
                .getInstance()
                .create(BaboomerInterface.class);


        Call<JsonObject> call = service.enterado(enterado);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                callback.onEnterado();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public interface EnteradoInterface{
        void onEnterado();
    }
}
