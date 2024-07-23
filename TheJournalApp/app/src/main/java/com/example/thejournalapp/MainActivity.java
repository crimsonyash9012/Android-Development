package com.example.thejournalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText emailEt, passEt;
    Button loginBtn, createAccount;

    // firebase auth
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createAccount = findViewById(R.id.newAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Signed Up", Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        // login
        loginBtn = findViewById(R.id.login);
        emailEt = findViewById(R.id.email);
        passEt = findViewById(R.id.password);


        // Firebase authentication
        // ensuring working with a single shared instance of firebase auth for entire app
        firebaseAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(v -> {

                    loginEmailPassUser(
                            emailEt.getText().toString().trim(),
                            passEt.getText().toString().trim()
                    );
                }

        );

    }

    private void loginEmailPassUser(String email, String pass){
        if(!TextUtils.isEmpty(email) &&
        !TextUtils.isEmpty(pass)){
            firebaseAuth.signInWithEmailAndPassword(email, pass)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, JournalListActivity.class);
                            startActivity(i);
                        }
                    });
        }
    }
}