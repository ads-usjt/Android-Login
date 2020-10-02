package dev.lucasliet.androidlogin.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import dev.lucasliet.androidlogin.R;

public class LoginActivity extends AppCompatActivity {

    private TextView textViewSignUp;
    private Button buttonLogin;

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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}