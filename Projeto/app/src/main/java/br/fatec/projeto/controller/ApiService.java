package br.fatec.projeto.controller;

import br.fatec.projeto.model.MyJson;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
   @GET("?results=10&nat=br&inc=gender,name,location,picture&noinfo")
   Call<MyJson> getUsuarios();
}
