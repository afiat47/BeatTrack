package com.example.beattrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class signup extends AppCompatActivity {
    private EditText editTextName, editTextEmail, editTextPassword, editTextCP, editTextHeight, editTextWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextName = findViewById(R.id.name_input_signup);
        editTextEmail = findViewById(R.id.email_input_signup);
        editTextPassword = findViewById(R.id.password_input_signup);
        editTextCP = findViewById(R.id.confirm_password_signup);
        editTextHeight = findViewById(R.id.height_input_signup);
        editTextWeight = findViewById(R.id.weight_input_signup);

        Button buttonSignUp = findViewById(R.id.signup);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser();
            }
        });

    }

    private void signUpUser(){
        String name = String.valueOf(editTextName.getText()).trim();
        String email = String.valueOf(editTextEmail.getText()).trim();
        String password = String.valueOf(editTextPassword.getText()).trim();
        String cp = String.valueOf(editTextCP.getText()).trim();
        String height = String.valueOf(editTextHeight.getText()).trim();
        String weight = String.valueOf(editTextWeight.getText()).trim();

        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || cp.isEmpty() || height.isEmpty() || weight.isEmpty()){
            Toast.makeText(this, "Can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!cp.equals(password)){
            Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String uid = auth.getUid();

                    if(uid == null){
                        Toast.makeText(signup.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
                    Map<String,String> map = new HashMap<>();
                    map.put("name",name);
                    map.put("email",email);
                    map.put("height",height);
                    map.put("weight",weight);

                    ref.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(signup.this, "Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signup.this, login.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}