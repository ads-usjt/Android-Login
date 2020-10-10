package dev.lucasliet.androidlogin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import dev.lucasliet.androidlogin.R;
import dev.lucasliet.androidlogin.factory.ActionBarFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBarFactory.createActionBar(Objects.requireNonNull(getSupportActionBar()));

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.home:
                    Log.d("NAVMENU","openFragment(new HomeFragment())");
                    return true;

                case R.id.contact:
                    openFragment(new TrendFragment());
                    return true;

                case R.id.profile:
                    openFragment(new AccountFragment());
                    return true;

                case R.id.setting:
                    openFragment(new SettingsFragment());
                    return true;

            }

            return false;
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(this,SignUpActivity.class);
                startActivity(intent);
            case R.id.exit:
                finish();
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }
}