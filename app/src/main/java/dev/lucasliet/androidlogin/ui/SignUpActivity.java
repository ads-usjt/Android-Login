package dev.lucasliet.androidlogin.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import dev.lucasliet.androidlogin.R;
import dev.lucasliet.androidlogin.model.User;
import dev.lucasliet.androidlogin.model.UserViewModel;

public class SignUpActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private User currentUser;
    private EditText editTextName;
    private EditText editTextCPF;
    private EditText editTextEmail;
    private EditText editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Hawk.init(this).build();

        editTextCPF = findViewById(R.id.editTextCPF);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUser().observe(this, this::updateView);
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

    public void Save(View view) {
        if (currentUser == null) currentUser = new User();
        currentUser.setCpf(editTextCPF.getText().toString());
        currentUser.setEmail(editTextEmail.getText().toString());
        currentUser.setName(editTextName.getText().toString());
        currentUser.setPassword(editTextPassword.getText().toString());

        userViewModel.insert(currentUser);
        Toast.makeText(
                this,
                "Usuário salvo com sucesso",
                Toast.LENGTH_SHORT
        ).show();
        Hawk.put("has_registration", true);
        finish();
    }
}