package dev.lucasliet.androidlogin.model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactService {
    String apiKey = "c7f8aa1f5a344936a44cdb37ae93e03e";

    @GET("api/"+apiKey+"/contact")
    Call<List<Contact>> getAllContacts();

    @POST("api/"+apiKey+"/contact")
    Call<ResponseBody> saveContact(
            @Body Contact contact
        );

    @PUT("api/"+apiKey+"/contact/{id}")
    Call<ResponseBody> updateContact(
            @Path("id") String id,
            @Body ContactPut contactPut
       );

    @DELETE("api/"+apiKey+"/contact/{id}")
    Call<ResponseBody> deleteContact(
              @Path("id") String id
        );
}