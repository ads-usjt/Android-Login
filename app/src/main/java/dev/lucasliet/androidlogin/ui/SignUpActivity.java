package dev.lucasliet.androidlogin.ui;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import dev.lucasliet.androidlogin.R;
import dev.lucasliet.androidlogin.factory.ActionBarFactory;
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
        ActionBarFactory.createActionBar(getSupportActionBar());
        replaceFragment(R.id.frameLayoutMainF,
                ProfileFragment.newInstance(true,"")
                ,"PROFILEFRAGMENT",
                "INITIALPROFILE");
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }
}