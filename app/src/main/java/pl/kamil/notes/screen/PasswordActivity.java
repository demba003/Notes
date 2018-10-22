package pl.kamil.notes.screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;

import pl.kamil.notes.R;
import pl.kamil.notes.screen.list.ListActivity;
import pl.kamil.notes.utils.HashUtils;
import pl.kamil.notes.utils.Preferences;

public class PasswordActivity extends AppCompatActivity {

    private EditText passwordText;
    private EditText repeatPassword;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        setTitle(R.string.enter_password);

        passwordText = findViewById(R.id.passwordText);
        repeatPassword = findViewById(R.id.repeatPassword);
        confirm = findViewById(R.id.confirm);

        if (Preferences.getPassword(this).isEmpty()) {
            repeatPassword.setVisibility(View.VISIBLE);
            setTitle(R.string.set_password);
        }

        confirm.setOnClickListener(v -> {
            if (Preferences.getPassword(this).isEmpty()) {
                if (passwordText.getText().toString().equals(repeatPassword.getText().toString())) {
                    try {
                        Preferences.setPassword(this, HashUtils.getSHA1Hash(passwordText.getText().toString()));
                        startApp();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(
                            this,
                            R.string.passwords_not_matching,
                            Toast.LENGTH_SHORT)
                            .show();
                }
            } else {
                try {
                    if (HashUtils.getSHA1Hash(passwordText.getText().toString()).equals(Preferences.getPassword(this))) {
                        startApp();
                    } else {
                        Toast.makeText(
                                this,
                                R.string.wrong_password,
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void startApp() {
        startActivity(new Intent(this, ListActivity.class));
    }
}
