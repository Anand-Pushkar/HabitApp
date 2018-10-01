package com.dev.ap.poject101.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.dev.ap.poject101.Adapters.AdapterJourneys;
import com.dev.ap.poject101.Interfaces.OnItemClickListener;
import com.dev.ap.poject101.Models.JourneysData;
import com.dev.ap.poject101.R;

import java.util.ArrayList;

public class JourneysActivity extends AppCompatActivity {

    RecyclerView rvJourneyList;
    AdapterJourneys adapterJourneys;
    ArrayList<JourneysData> journeysDataArrayList = generateJourneys();

    public static final String TAG = "JourneysActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journeys);

        Log.d(TAG, "onCreate: JourneysActivity start");

        rvJourneyList = (RecyclerView)findViewById(R.id.rvJourneys);
        rvJourneyList.setLayoutManager(new GridLayoutManager(this,2));
        adapterJourneys = new AdapterJourneys(journeysDataArrayList, this);
        rvJourneyList.setAdapter(adapterJourneys);

        Log.d(TAG, "onCreate: AdapterJourneys has been set");

        adapterJourneys.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int itemId, View clickedView) {

                if(itemId == 1){

                    Intent intentHealth = new Intent(JourneysActivity.this, HealthActivity.class);
                    intentHealth.putExtra("JourneyId", itemId);
                    startActivity(intentHealth);

                }
                else if (itemId == 2){

                    Intent intentSpiritual = new Intent(JourneysActivity.this, SpiritualActivity.class);
                    intentSpiritual.putExtra("JourneyId", itemId);
                    startActivity(intentSpiritual);

                }
                else if (itemId == 3){

                    Intent intentSocial = new Intent(JourneysActivity.this, SocialActivity.class);
                    intentSocial.putExtra("JourneyId", itemId);
                    startActivity(intentSocial);

                }
                else if (itemId == 4){

                    Intent intentRelationships = new Intent(JourneysActivity.this, RelationshipsActivity.class);
                    intentRelationships.putExtra("JourneyId", itemId);
                    startActivity(intentRelationships);

                }
                else if (itemId == 5){

                    Intent intentProductivity = new Intent(JourneysActivity.this, ProductivityActivity.class);
                    intentProductivity.putExtra("JourneyId", itemId);
                    startActivity(intentProductivity);

                }
                else if (itemId == 6){

                    Intent intentFinance = new Intent(JourneysActivity.this, FinanceActivity.class);
                    intentFinance.putExtra("JourneyId", itemId);
                    startActivity(intentFinance);

                }


            }
        });

    }

    static ArrayList<JourneysData> generateJourneys(){

        ArrayList<JourneysData> journeysData = new ArrayList<>();

        journeysData.add(new JourneysData("Health",1));
        journeysData.add(new JourneysData("Spiritual",2));
        journeysData.add(new JourneysData("Social",3));
        journeysData.add(new JourneysData("Relationships",4));
        journeysData.add(new JourneysData("Productivity",5));
        journeysData.add(new JourneysData("Finance",6));

        return journeysData;
    }
}
