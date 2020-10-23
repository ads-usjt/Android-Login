package dev.lucasliet.androidlogin.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactRepository {
    private static final String CONTACTS_SERVICE_BASE_URL = "https://crudcrud.com";
    private ContactService contactService;
    private MutableLiveData<List<Contact>> contactsResponseMutableLiveData;
    private MutableLiveData<Boolean> saveSuccessMutableLiveData;
    public ContactRepository() {
        contactsResponseMutableLiveData = new MutableLiveData<>();
        saveSuccessMutableLiveData = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        contactService = new retrofit2.Retrofit.Builder()
                .baseUrl(CONTACTS_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ContactService.class);
    }
    
    public void getContacts() {
        contactService.getAllContacts()
                .enqueue(new Callback<List<Contact>>() {
                    @Override
                    public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                        if (response.body() != null) {
                            Log.d("RESPOSTA", "tenho resultato-->"+response.body());
                            contactsResponseMutableLiveData.postValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Contact>> call, Throwable t) {
                        Log.e("RESPOSTA", "FALHOU->"+t.getMessage());
                        contactsResponseMutableLiveData.postValue(null);
                    }
                });
    }

    public LiveData<List<Contact>> getAllContacts() {
        return contactsResponseMutableLiveData;
    }
    public LiveData<Boolean> getSaveSucess() {
        return saveSuccessMutableLiveData;
    }
    public void saveContact(Contact contact){
        contactService.saveContact(contact)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body() != null) {
                            Log.d("RESPOSTA", "tenho resultato-->"+response.body());
                            saveSuccessMutableLiveData.postValue(new Boolean(true));
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("RESPOSTA", "FALHOU->"+t.getMessage());
                        saveSuccessMutableLiveData.postValue(new Boolean(false));
                    }
                });
    }

//    public void updateContact(Contact contact){
//        ContactPut contatoPut = new ContactPut(contact.getName(),contact.getEmail(),
//                contact.getPhone();
//        contactService.updateContact(contact.getId(),contatoPut)
//                .enqueue(new Callback<ResponseBody>() {
//                    @Override
//
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                        if (response.body() != null) {
//                            Log.d("RESPOSTA", "tenho resultato-->"+response.body());
//                            saveSuccessMutableLiveData.postValue(new Boolean(true));
//                        }
//                    }
//                    @Override
//
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                        Log.e("RESPOSTA", "FALHOU->"+t.getMessage());
//                        saveSuccessMutableLiveData.postValue(new Boolean(false));
//                    }
//                });
//    }

    public Call<ResponseBody> deleteContact(Contact contato){
        return contactService.deleteContact(contato.getId());
    }

}