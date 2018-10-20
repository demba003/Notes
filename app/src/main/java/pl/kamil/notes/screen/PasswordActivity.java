package pl.kamil.notes.screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.kamil.notes.R;
import pl.kamil.notes.screen.list.ListActivity;
import pl.kamil.notes.utils.Preferences;

public class PasswordActivity extends AppCompatActivity {

    EditText passwordText;
    EditText repeatPassword;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        setTitle(R.string.enter_password);

        passwordText = findViewById(R.id.passwordText);
        repeatPassword = findViewById(R.id.repeatPassword);
        confirm = findViewById(R.id.confirm);

        if (Preferences.getPasswordHash(this).isEmpty()) {
            repeatPassword.setVisibility(View.VISIBLE);
            setTitle(R.string.set_password);
        }

        confirm.setOnClickListener(v -> {
            if (Preferences.getPasswordHash(this).isEmpty()) {
                if (passwordText.getText().toString().equals(repeatPassword.getText().toString())) {
                    Preferences.setPassword(this, passwordText.getText().toString());
                    startApp();
                } else {
                    Toast.makeText(

                            this,
                            R.string.passwords_not_matching,
                            Toast.LENGTH_SHORT)
                            .show();
                }
            } else {
                if (passwordText.getText().toString().equals(Preferences.getPasswordHash(this))) {
                    startApp();
                } else {
                    Toast.makeText(

                            this,
                            R.string.wrong_password,
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void startApp() {
        startActivity(new Intent(this, ListActivity.class));
    }
}
