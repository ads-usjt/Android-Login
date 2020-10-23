package dev.lucasliet.androidlogin.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import java.util.List;

import dev.lucasliet.androidlogin.R;
import dev.lucasliet.androidlogin.model.Contact;
import dev.lucasliet.androidlogin.model.ContactViewModel;
import dev.lucasliet.androidlogin.model.User;
import dev.lucasliet.androidlogin.model.UserViewModel;

public class ContactFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ContactViewModel contactViewModel;
    private Contact currentContact;
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private Button buttonSave;

    private String mParam1;
    private String mParam2;

    public ContactFragment() {
        // Required empty public constructor
    }

    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Hawk.init(getActivity()).build();

        editTextEmail = view.findViewById(R.id.editTextEmailC);
        editTextName = view.findViewById(R.id.editTextNameC);
        editTextPhone = view.findViewById(R.id.editTextPhoneC);
        buttonSave = view.findViewById(R.id.buttonSaveC);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        contactViewModel.getSaveSuccess().observe( getActivity(), ( wasContactSaveSuccessful ) -> {
            if ( wasContactSaveSuccessful ){
                Toast.makeText(
                        getActivity(),
                        R.string.contact_register_sucess,
                        Toast.LENGTH_SHORT
                ).show();
                eraseFields();
            }
            else
                Toast.makeText(
                        getActivity(),
                        R.string.contact_register_fail,
                        Toast.LENGTH_SHORT
                ).show();
        });
    }

    public void save() {
        if (currentContact == null) currentContact = new Contact();
        if (validateFields()){
            currentContact.setEmail(editTextEmail.getText().toString());
            currentContact.setName(editTextName.getText().toString());
            currentContact.setPhone(editTextPhone.getText().toString());

            contactViewModel.saveContact(currentContact);
        }
    }

    public void eraseFields() {
        editTextPhone.setText("");
        editTextEmail.setText("");
        editTextName.setText("");
    }

    public boolean validateFields(){
        boolean valid = true;
        if(editTextName.getText().toString().trim().length()==0){
            valid = false;
            Toast.makeText(getActivity(),"Nome inválido!",
                    Toast.LENGTH_SHORT).show();
        }
        if(editTextEmail.getText().toString().trim().length()==0){
            valid = false;
            Toast.makeText(getActivity(),"Email inválido!",
                    Toast.LENGTH_SHORT).show();
        }
        if(editTextPhone.getText().toString().trim().length()==0){
            valid = false;
            Toast.makeText(getActivity(),"Telefone inválido!",
                    Toast.LENGTH_SHORT).show();
        }
        return valid;
    }
}