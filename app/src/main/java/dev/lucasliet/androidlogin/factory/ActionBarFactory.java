package dev.lucasliet.androidlogin.factory;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import dev.lucasliet.androidlogin.R;

public abstract class ActionBarFactory extends AppCompatActivity {
    public static void createActionBar(ActionBar supportActionBar){
        supportActionBar.setDisplayOptions(androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        supportActionBar.setCustomView(R.layout.action_bar);
    }
}
