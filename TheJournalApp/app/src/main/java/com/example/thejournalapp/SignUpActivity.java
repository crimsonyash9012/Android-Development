package com.example.thejournalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {

    // Widgets
    EditText password_create, username_create, email_create;
    Button createBTN;

    //Firebase
    private FirebaseAuth firebaseAuth;
    // when signing in or out
    private FirebaseAuth.AuthStateListener authStateListener; // listen changes in user authentication state
    // currently authenticated user in firebase
    private FirebaseUser currentUser;

    // Firebase connection
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        createBTN = findViewById(R.id.acc_signUp_btn);
        password_create = findViewById(R.id.password_create);
        email_create = findViewById(R.id.email_create);
        username_create = findViewById(R.id.username_create_ET);

        // Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Listening for changes in the authentication
        // state and responds accordingly when the
        // the state changes
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();

                // CHeck the user if logged in or not
                if (currentUser != null){
                    // user Already logged in
                }else{
                    // The user signed out
                }
            }
        };

        createBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(email_create.getText().toString())
                        &&  !TextUtils.isEmpty(username_create.getText().toString())
                        && !TextUtils.isEmpty(password_create.getText().toString())){

                    String email = email_create.getText().toString().trim();
                    String pass = password_create.getText().toString().trim();
                    String username = username_create.getText().toString().trim();

                    CreateUserEmailAccount(email, pass, username);

                }else{
                    Toast.makeText(
                            SignUpActivity.this,
                            "No Empty Fields are Allowed!",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    private void CreateUserEmailAccount(
            String email,
            String pass,
            String username
    ){
        if (!TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(pass)
                && !TextUtils.isEmpty(username)
        ){
            firebaseAuth.createUserWithEmailAndPassword(
                    email, pass
            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        // The user is created successfully!
                        Toast.makeText(SignUpActivity.this,
                                "Account is created successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

}
}