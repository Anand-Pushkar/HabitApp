package com.dev.ap.poject101.Models;

/**
 * Created by Pushkar on 28-Sep-18.
 */

public class JourneysData {

    String journeyName;
    int journeyId;

    public JourneysData(String journeyName, int journeyId) {
        this.journeyName = journeyName;
        this.journeyId = journeyId;
    }

    public String getJourneyName() {

        return journeyName;
    }

    public int getJourneyId() {
        return journeyId;
    }
}
