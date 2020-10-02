package dev.lucasliet.androidlogin.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.orhanobut.hawk.Hawk;

import dev.lucasliet.androidlogin.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Hawk.init(this).build();
    }

    public void Save(View view) {
        Hawk.put("has_registration", true);
        finish();
    }
}