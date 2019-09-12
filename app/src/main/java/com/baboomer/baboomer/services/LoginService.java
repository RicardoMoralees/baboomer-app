package com.baboomer.baboomer.services;

import com.baboomer.baboomer.interfaces.BaboomerInterface;
import com.baboomer.baboomer.models.Comunicado;
import com.baboomer.baboomer.utils.RetrofitInstance;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {

    public static void startService(String email, String contra, String token, final LoginInterface callback){

        BaboomerInterface service = RetrofitInstance
                .getInstance()
                .create(BaboomerInterface.class);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", contra);
        jsonObject.addProperty("tokenFirebase", token);

        Call<JsonObject> call = service.login(jsonObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    callback.onLoginSuccess();
                }else {
                    callback.onLoginFail();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public interface LoginInterface{
        void onLoginSuccess();
        void onLoginFail();
    }
}
