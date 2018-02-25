package com.nyc.ac_44_comprehensive_android_practical_assessment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private EditText username, password;
    private Button submit;
    public static final String SHARED_PREF_KEY = "goto_login_activity";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        sharedPreferences = getSharedPreferences(SHARED_PREF_KEY,MODE_PRIVATE);

        if (!sharedPreferences.getString("username", "").equals("") && !sharedPreferences.getString("password", "").equals("")){
            Intent intent = new Intent(LoginActivity.this, BreedsActivity.class);
            startActivity(intent);
            Log.d(TAG, "onCreate: Logging back into breeds activity");
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "please enter a username", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onClick: please enter a username");
                }
                if (password.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "please enter a password", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onClick: please enter a password");
                }
                if (password.getText().toString().contains(username.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "password cannot contain username", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onClick: password cannot contain username");

                }
                if (!username.getText().toString().equals("")
                        && !password.getText().toString().equals("")
                        && !password.getText().toString().contains(username.getText().toString())) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.putString("username", username.getText().toString());
                    editor.putString("password", password.getText().toString());
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this,BreedsActivity.class);
                    startActivity(intent);
                    Log.d(TAG, "onClick: went to BreedsAvtivity first Time");

                }
            }
        });
    }
}
