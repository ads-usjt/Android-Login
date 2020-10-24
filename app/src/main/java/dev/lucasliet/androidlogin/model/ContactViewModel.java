package dev.lucasliet.androidlogin.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository contactRepository;
    private LiveData<List<Contact>> contatosResponseLiveData;
    private LiveData<Boolean> saveWithSuccessLiveData;
    public ContactViewModel(@NonNull Application application) {
        super(application);
        Log.d("RESPOSTA","CRIACAO DA VIEWMODEL");
        contactRepository = new ContactRepository();
        contatosResponseLiveData = contactRepository.getAllContacts();
        saveWithSuccessLiveData = contactRepository.getSaveSucess();
    }
    public void getContacts() {
        contactRepository.getContacts();
    }
    public LiveData<List<Contact>> getContactsResponseLiveData() {
        return contatosResponseLiveData;
    }
    public LiveData<Boolean> getSaveSuccess() {
        return saveWithSuccessLiveData;
    }
    public void saveContact(Contact contact){
        contactRepository.saveContact(contact);
    }
    public void updateContact(Contact contact){
        contactRepository.updateContact(contact);
    }
}
