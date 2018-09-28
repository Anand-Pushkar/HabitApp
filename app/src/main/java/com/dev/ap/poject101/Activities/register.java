package com.dev.ap.poject101.Activities;

import android.support.annotation.NonNull;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.ap.poject101.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    Button register;
    EditText id,password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        id = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.pass);
        register = (Button)findViewById(R.id.reg);
        mAuth = FirebaseAuth.getInstance();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Register");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = id.getText().toString();
                String pass = password.getText().toString();
                if(email.equals("")||pass.equals(""))
                {
                    Toast.makeText(register.this,"Please fill all the details",Toast.LENGTH_LONG).show();
                }
                else
                {
                    mAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(register.this,"Successfull",Toast.LENGTH_LONG).show();

                                    } else {

                                        Toast.makeText(register.this, "Authentication failed"+task.getException().getMessage(),
                                                Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                }

            }
        });
    }
}
