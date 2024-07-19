package com.example.firestoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.FileObserver;
import android.text.style.UpdateAppearance;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    Button saveBtn;
    Button readBtn;
    Button updateBtn;
    Button deleteBtn;
    EditText nameET;
    EditText emailET;

    // Firebase Firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference firebaseRef = db.collection("Friends")
                                                .document("9iDhHn4917XicI38XfX9");
    private CollectionReference collectionReference = db.collection("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveBtn = findViewById(R.id.saveBTN);
        readBtn = findViewById(R.id.readBTN);
        updateBtn = findViewById(R.id.updateBTN);
        deleteBtn = findViewById(R.id.deleteBTN);
        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveDataToNewDocument();
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAllDocumentsInCollection();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateSpecificDocument();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAll();
            }
        });



    }

    public void SaveDataToNewDocument(){
        String name = nameET.getText().toString();
        String email = emailET.getText().toString();

        Friend friend = new Friend(name,email);

        // add a new object to specified location
//        collectionReference.add(friend);
        // can also check the success of the function by below implem.

        collectionReference.add(friend).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                String docID = documentReference.getId();
            }
        });
    }

    private void GetAllDocumentsInCollection(){
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                String data = "";
                // executed when data retrieval is successful
                // the queryDocumentSnapshots contains the documents in the collection
                for(QueryDocumentSnapshot snapshot: queryDocumentSnapshots){
                    // transform snapshots into objects
                    Friend friend = snapshot.toObject(Friend.class);
                    data+= "Name: "+friend.getName()+" Email: "+friend.getEmail()+"\n";


                }
                TextView text = findViewById(R.id.text);
                text.setText(data);
            }
        });
    }
    private void UpdateSpecificDocument(){
        String name = nameET.getText().toString();
        String email = emailET.getText().toString();

        firebaseRef.update("name", name);
        firebaseRef.update("email", email);
    }

    private void DeleteAll(){
        firebaseRef.delete();
    }
}