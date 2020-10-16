package dev.lucasliet.androidlogin.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
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

        replaceFragment(HomeFragment.newInstance("",""), "HOMEFRAGMENT", "HOME");

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.home:
                    replaceFragment(HomeFragment.newInstance("",""), "HOMEFRAGMENT", "HOME");
                    return true;

                case R.id.contact:
                    replaceFragment(ContactFragment.newInstance("",""), "CONTACTFRAGMENT", "CONTACT");
                    return true;

                case R.id.profile:
                    replaceFragment(ProfileFragment.newInstance(false,""), "PROFILEFRAGMENT", "PROFILE");
                    return true;

                case R.id.setting:
                    replaceFragment(SettingsFragment.newInstance("",""), "SETTINGSFRAGMENT", "SETTINGS");
                    return true;

                case R.id.map:
                    replaceFragment(MapFragment.newInstance("",""), "MAPFRAGMENT", "MAP");
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
            case R.id.exit:
                finish();
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

    protected void replaceFragment(@NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }
}