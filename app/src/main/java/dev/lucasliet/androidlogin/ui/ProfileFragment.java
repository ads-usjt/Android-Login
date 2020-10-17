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

import dev.lucasliet.androidlogin.R;
import dev.lucasliet.androidlogin.model.User;
import dev.lucasliet.androidlogin.model.UserViewModel;


public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private UserViewModel userViewModel;
    private User currentUser;
    private EditText editTextName;
    private EditText editTextCPF;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSave;

    // TODO: Rename and change types of parameters
    private boolean mParam1;
    private String mParam2;

    public ProfileFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(boolean param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getBoolean(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Hawk.init(getActivity()).build();

        editTextCPF = view.findViewById(R.id.editTextCPFF);
        editTextEmail = view.findViewById(R.id.editTextEmailF);
        editTextName = view.findViewById(R.id.editTextNameF);
        editTextPassword = view.findViewById(R.id.editTextPasswordF);
        buttonSave = view.findViewById(R.id.buttonSaveF);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUser().observe(getActivity(), this::updateView);
    }

    private void updateView(User user){
        if(user != null && user.getId() > 0){
            currentUser = user;
            editTextName.setText(user.getName());
            editTextCPF.setText(user.getCpf());
            editTextEmail.setText(user.getEmail());
            editTextPassword.setText(user.getPassword());
        }
    }

    public void save() {
        if (currentUser == null) currentUser = new User();
        currentUser.setCpf(editTextCPF.getText().toString());
        currentUser.setEmail(editTextEmail.getText().toString());
        currentUser.setName(editTextName.getText().toString());
        currentUser.setPassword(editTextPassword.getText().toString());

        userViewModel.insert(currentUser);
        Toast.makeText(
                getActivity(),
                R.string.user_register_sucess,
                Toast.LENGTH_SHORT
        ).show();
        Hawk.put("has_registration", true);
        if(mParam1){
            getActivity().finish();
        }
    }
}