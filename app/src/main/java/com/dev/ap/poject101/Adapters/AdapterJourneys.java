package com.dev.ap.poject101.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.ap.poject101.Activities.JourneysActivity;
import com.dev.ap.poject101.Interfaces.OnItemClickListener;
import com.dev.ap.poject101.Models.JourneysData;
import com.dev.ap.poject101.R;

import java.util.ArrayList;

/**
 * Created by Pushkar on 28-Sep-18.
 */

public class AdapterJourneys extends RecyclerView.Adapter <AdapterJourneys.JourneysViewHolder> {

    public static final String TAG = "AdapterJourneys";

    ArrayList<JourneysData> journeysDataArrayList;
    Context context;

    // Object for the Interface
    private OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        Log.d(TAG, "setOnItemClickListener: Listener initialized");

        this.onItemClickListener = onItemClickListener;
    }

    //Constructor of AdapterJourneys
    public AdapterJourneys(ArrayList<JourneysData> journeysDataArrayList, JourneysActivity context) {

        Log.d(TAG, "AdapterJourneys: Constructor of AdapterJourneys");
        this.journeysDataArrayList = journeysDataArrayList;
        this.context = context;
    }

    @Override
    public JourneysViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.list_item_journeys, parent, false);

        Log.d(TAG, "onCreateViewHolder: onCreateVIewHolder "+journeysDataArrayList.size());

        return new JourneysViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JourneysViewHolder holder, int position) {

        final JourneysData thisJourney = journeysDataArrayList.get(position);
        holder.tvJourneyName.setText(thisJourney.getJourneyName());

        Log.d(TAG, "onBindViewHolder: onBindViewHolder111111111");

        holder.thisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(thisJourney.getJourneyId(), view);
                }
                Log.d(TAG, "onClick: View Clicked222222222222222");
            }
        });

    }

    @Override
    public int getItemCount() {
        return journeysDataArrayList.size();
    }

    static class JourneysViewHolder extends RecyclerView.ViewHolder{

        TextView tvJourneyName;
        View thisView;

        public JourneysViewHolder(View itemView) {
            super(itemView);

            this.thisView = itemView;
            tvJourneyName = (TextView)itemView.findViewById(R.id.tv_JourneyName);
        }
    }
}
