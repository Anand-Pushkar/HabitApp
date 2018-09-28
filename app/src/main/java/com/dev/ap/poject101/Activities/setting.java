package com.dev.ap.poject101.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dev.ap.poject101.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class setting extends AppCompatActivity {

    Button signOut,Change,delete;
    EditText oldPass,newPass,rePass;
    private FirebaseAuth mAuth;
    String email,oldpassword,newPassword,rePassword;
    int a = 0;
    LinearLayout linear;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        signOut =(Button)findViewById(R.id.signOut);
        Change = (Button)findViewById(R.id.change);
        delete = (Button)findViewById(R.id.delete);
        linear = (LinearLayout)findViewById(R.id.linear);
        oldPass = (EditText)findViewById(R.id.oldPass);
        newPass = (EditText)findViewById(R.id.newPass);
        rePass = (EditText)findViewById(R.id.reNewPass);


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent out = new Intent(setting.this,LoginActivity.class);
                startActivity(out);
            }
        });

        Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(a==0)
              {
                  Toast.makeText(setting.this,"Enter Details",Toast.LENGTH_LONG).show();
                  linear.setVisibility(View.VISIBLE);
                  a++;
              }
              else if(a==1)
              {
                  email=user.getEmail();
                  oldpassword = oldPass.getText().toString();
                  newPassword = newPass.getText().toString();
                  rePassword = rePass.getText().toString();
                  AuthCredential credential = EmailAuthProvider.getCredential(email,oldpassword);
                  user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                          if(task.isSuccessful())
                          {
                             if(newPassword.equals(rePassword))
                             {
                                 user.updatePassword(rePassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                     @Override
                                     public void onComplete(@NonNull Task<Void> task) {
                                         if(task.isSuccessful())
                                         {
                                             Toast.makeText(setting.this,"Password Changed",Toast.LENGTH_LONG).show();
                                         }
                                         else
                                         {
                                             Toast.makeText(setting.this,"Not Changed",Toast.LENGTH_LONG).show();
                                         }
                                     }
                                 });
                             }
                             else
                             {
                                 Toast.makeText(setting.this,"New Password dosen't match",Toast.LENGTH_LONG).show();
                             }
                          }
                      }
                  });
              }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(setting.this);
                builder.setIcon(R.drawable.delete1);
                builder.setTitle("Delete Your Account");
                builder.setMessage("Are you sure :-");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(setting.this,"Deleted",Toast.LENGTH_LONG).show();
                                    Intent out = new Intent(setting.this,LoginActivity.class);
                                    startActivity(out);
                                }
                                else
                                    Toast.makeText(setting.this,"Error",Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });

                builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener()
                {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });

    }
}
