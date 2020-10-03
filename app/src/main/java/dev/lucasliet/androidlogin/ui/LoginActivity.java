package dev.lucasliet.androidlogin.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import dev.lucasliet.androidlogin.R;
import dev.lucasliet.androidlogin.model.User;
import dev.lucasliet.androidlogin.model.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    private TextView textViewSignUp;
    private Button buttonLogin;
    private UserViewModel userViewModel;
    private User currentUser;
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Hawk.init(this).build();

        textViewSignUp = findViewById(R.id.textViewSignUp);
        buttonLogin = findViewById(R.id.buttonLogin);

        if (Hawk.contains("has_registration")){
            if (Hawk.get("has_registration")){
                enableLogin();
            } else {
                disableLogin();
            }
        } else {
            disableLogin();
        }

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUser().observe(this, this::updateUser);
    }

    private void updateUser(User user) {
        currentUser = user;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Hawk.contains("has_registration")){
            if (Hawk.get("has_registration")){
                enableLogin();
            } else {
                disableLogin();
            }
        } else {
            disableLogin();
        }
    }

    private void enableLogin() {
        textViewSignUp.setVisibility(View.GONE);
        buttonLogin.setEnabled(true);
        buttonLogin.setBackgroundColor(getColor(R.color.colorPrimary));
    }

    private void disableLogin() {
        textViewSignUp.setVisibility(View.VISIBLE);
        buttonLogin.setEnabled(false);
        buttonLogin.setBackgroundColor(getColor(R.color.disabled));
    }

    public void SignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void Login(View view) {
        if (currentUser != null
                && currentUser.getEmail().equalsIgnoreCase(editTextEmail.getText().toString())
                && currentUser.getPassword().equals(editTextPassword.getText().toString())
        ){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
        }

    }
}