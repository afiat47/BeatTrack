package com.example.beattrack;

import android.content.DialogInterface;
<<<<<<< HEAD

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;

=======
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
>>>>>>> main
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
=======
>>>>>>> main
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class login extends AppCompatActivity {

<<<<<<< HEAD
    private EditText editTextEmail, editTextPassword;



        private TextView txtForgotPassword;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);


            //Button buttonSignup = findViewById(R.id.signup);
            Button buttonLogin = findViewById(R.id.login);

            //buttonSignup.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View v) {
            //        startActivity(new Intent(Login.this,SignUp.class));
            //    }
            //});


            editTextEmail = findViewById(R.id.email_input_login);
            editTextPassword = findViewById(R.id.password_input_login);

            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginUser();
                }
            });
        }


        private void loginUser(){
            String email = String.valueOf(editTextEmail.getText()).trim();
            String password = String.valueOf(editTextPassword.getText()).trim();

            if(email.isEmpty()|| password.isEmpty()){
                Toast.makeText(this, "can't be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(login.this, "Successful", Toast.LENGTH_SHORT).show();
                        SharedPreferences sp = getSharedPreferences("sp",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("amILoggedIn",true);
                        editor.apply();
                        startActivity(new Intent(login.this,HomeActivity.class));
                    }
                    else{
                        Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });


            txtForgotPassword = findViewById(R.id.forgot_password);

            txtForgotPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle forgot password functionality
                    showForgotPasswordDialog();
                }
            });
        }
        private void showForgotPasswordDialog() {
            // Implement your logic to show a dialog or navigate to a password recovery screen
            // For example, you can use an AlertDialog to prompt the user for their email address
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Forgot Password");
            builder.setMessage("Please enter your email address to recover your password");

            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            builder.setView(input);

            builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String emailAddress = input.getText().toString();
                    // Implement the logic to send a password recovery email to the entered email address
                    // You can use libraries like JavaMail or interact with an API to send the email
                    Toast.makeText(login.this, "Password recovery email sent to: " + emailAddress, Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();

        }
    }
=======
    private TextView txtForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtForgotPassword = findViewById(R.id.forgot_password);

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle forgot password functionality
                showForgotPasswordDialog();
            }
        });
    }
    private void showForgotPasswordDialog() {
        // Implement your logic to show a dialog or navigate to a password recovery screen
        // For example, you can use an AlertDialog to prompt the user for their email address
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Forgot Password");
        builder.setMessage("Please enter your email address to recover your password");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        builder.setView(input);

        builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String emailAddress = input.getText().toString();
                // Implement the logic to send a password recovery email to the entered email address
                // You can use libraries like JavaMail or interact with an API to send the email
                Toast.makeText(login.this, "Password recovery email sent to: " + emailAddress, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
>>>>>>> main
