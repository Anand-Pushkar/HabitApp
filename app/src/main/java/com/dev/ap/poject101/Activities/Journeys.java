package com.dev.ap.poject101.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.dev.ap.poject101.Adapters.AdapterJourneys;
import com.dev.ap.poject101.R;

import java.util.ArrayList;

public class Journeys extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterJourneys adapterJourneys;
    ArrayList<JourneysData> journeysDataArrayList = generateJourneys();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journeys);



    }

    static ArrayList<JourneysData> generateJourneys(){

        ArrayList<JourneysData> journeysData = new ArrayList<>();

        journeysData.add(new JourneysData("Health",1));
        journeysData.add(new JourneysData("Spiritual",2));
        journeysData.add(new JourneysData("Social",3));
        journeysData.add(new JourneysData("Relationships",4));
        journeysData.add(new JourneysData("Professional and Productivity",5));
        journeysData.add(new JourneysData("Finance",6));

        return journeysData;
    }
}
