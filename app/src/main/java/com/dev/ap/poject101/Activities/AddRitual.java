package com.dev.ap.poject101.Activities;

import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dev.ap.poject101.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddRitual extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{
    EditText name;
    RadioButton morning,evening,afternoon;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String id1;
    String day;
    String a;
    int hour,min;
    TextView time1;
    String username;
    String z;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ritual);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Add Ritual");

        name = (EditText)findViewById(R.id.ritual);
        morning = (RadioButton)findViewById(R.id.morning);
        evening = (RadioButton)findViewById(R.id.evening);
        afternoon = (RadioButton)findViewById(R.id.afternoon);
        time1 = (TextView)findViewById(R.id.time1);
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("h:mm a");
        String date_str = df.format(cal.getTime());
        time1.setText(date_str);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Rituals");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        username = user.getEmail();
        z=username.substring(0,username.indexOf('@'));


        Toast.makeText(AddRitual.this,z,Toast.LENGTH_LONG).show();


        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment time = new TimePickerFragment();
                time .show(getSupportFragmentManager(),"Time Picker");
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_ritual,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action) {
           a = name.getText().toString();

           if(!a.matches(""))
           {
               if(morning.isChecked()||evening.isChecked()||afternoon.isChecked())
               {
                 if(morning.isChecked())
                   day = "morning";
                 else if(evening.isChecked())
                     day = "evening";
                 else if(afternoon.isChecked())
                     day = "afternoon";

                   id1 = myRef.push().getKey();
                   ritual rituals = new ritual(a,day,hour,min);

                   myRef.child(z).child(id1).setValue(rituals);
                   Toast.makeText(AddRitual.this, "Data inserted succesfully",Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(AddRitual.this,"Select one of the option ",Toast.LENGTH_LONG).show();
               }
           }
           else
               Toast.makeText(AddRitual.this,"Write name of ritual",Toast.LENGTH_LONG).show();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        hour = hourOfDay;
        min = minute;
        if(hourOfDay>12) {
            time1.setText(String.valueOf(hourOfDay-12)+ ":"+(String.valueOf(minute)+" PM"));
        } else if(hourOfDay==12) {
            time1.setText("12"+ ":"+(String.valueOf(minute)+" PM"));
        } else if(hourOfDay<12) {
            if(hourOfDay!=0) {
                time1.setText(String.valueOf(hourOfDay) + ":" + (String.valueOf(minute) + " AM"));
            } else {
                time1.setText("12" + ":" + (String.valueOf(minute) + " AM"));
            }
        }
    }
}

