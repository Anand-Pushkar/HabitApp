package com.dev.ap.poject101.Activities;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dev.ap.poject101.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText id, password;
    Button login, register, forgot;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    RelativeLayout rellay1,rellay2;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       rellay1=(RelativeLayout)findViewById(R.id.rellay1);
       rellay2=(RelativeLayout)findViewById(R.id.rellay2);
        handler.postDelayed(runnable,2000);
        id = (EditText) findViewById(R.id.LoginId);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.Login);
        register = (Button) findViewById(R.id.register);
        forgot = (Button) findViewById(R.id.forgot);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String a = id.getText().toString();
                    String b = password.getText().toString();
                    if(a.equals("")||b.equals(""))
                    {
                        Toast.makeText(LoginActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        AsyncTaskDemo asyncTaskDemo = new AsyncTaskDemo();
                        asyncTaskDemo.execute();
                    }

                }
            });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                       Intent i1 = new Intent(LoginActivity.this,register.class);
                       startActivity(i1);
                }
            });

            forgot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = id.getText().toString();

                    if(email.equals(""))
                    {
                        Toast.makeText(LoginActivity.this, "Please provide the USERNAME", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        mAuth.sendPasswordResetEmail(email)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(LoginActivity.this,"Email Sent, Reset your Password",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(LoginActivity.this,"Not Sent"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }

                }
            });

    }
        class AsyncTaskDemo extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(LoginActivity.this, "Loading...", "Wait");

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                String email = id.getText().toString();
                String pass = password.getText().toString();
                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "login Successfull", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(i);
                                    progressDialog.dismiss();
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Login failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }
    }
