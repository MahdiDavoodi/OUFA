package davoodi.mahdi.fifa.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.util.List;

import davoodi.mahdi.fifa.data.ClubsData;
import davoodi.mahdi.fifa.data.LeaguesData;
import davoodi.mahdi.fifa.data.OwnersData;
import davoodi.mahdi.fifa.data.ResultsData;
import davoodi.mahdi.fifa.data.SeasonsData;
import davoodi.mahdi.fifa.data.TransfersData;
import davoodi.mahdi.fifa.parsers.JsonParser;
import davoodi.mahdi.fifa.R;
import davoodi.mahdi.fifa.components.Club;
import davoodi.mahdi.fifa.components.League;
import davoodi.mahdi.fifa.components.Season;
import davoodi.mahdi.fifa.preferences.AppPreferences;

public class StartPage extends AppCompatActivity {

    AppPreferences preferences;
    private static final int firstSeason = 2021;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);
    }

    // OnClickListener for start button.
    public void startOnClick(View view) {
        preferences = new AppPreferences(this);

        createDatabase();
        // Edit Preferences.
        preferences.setCurrentSeason(firstSeason);
        preferences.setLastSeen("StartPage");
        // Leagues.
        preferences.setMtCreated(false);
        preferences.setTmCreated(false);
        preferences.setChampionsCreated(false);
        preferences.setEuropeCreated(false);
        preferences.setGoldenCreated(false);
        //
        startActivity(new Intent(StartPage.this, CreateOwnersPage.class));
        overridePendingTransition(R.anim.activity_slide_from_right, R.anim.activity_slide_to_left);
        finish();
    }

    // Create app database.
    private void createDatabase() {
        // Clubs and Leagues from json files.
        importJson();

        // Owners
        OwnersData ownersData = new OwnersData(this);
        ownersData.getWritableDatabase();

        // Seasons
        preferences.setSeasonCount(1);
        int firstSeasonID = firstSeason;
        SeasonsData seasonsData = new SeasonsData(this);
        Season firstSeason = new Season(firstSeasonID,
                0,
                0,
                0,
                0,
                0,
                0);
        seasonsData.insertSeason(firstSeason);
        seasonsData.getWritableDatabase();

        // Results
        ResultsData resultsData = new ResultsData(this);
        resultsData.getWritableDatabase();

        // Transfers
        TransfersData transfersData = new TransfersData(this);
        transfersData.getWritableDatabase();

    }

    // Import json files into database.
    private void importJson() {

        // Clubs.
        ClubsData clubsData = new ClubsData(this);
        Log.i("database", "Database opened");
        InputStream clubsInput = getResources().openRawResource(R.raw.clubs);
        List<Club> clubs = JsonParser.clubsJson(clubsInput);
        Log.i("jsonParser", "Returned " + clubs.size() + " clubs!");
        for (Club club : clubs)
            clubsData.insertClub(club);

        // Leagues.
        LeaguesData leaguesData = new LeaguesData(this);
        Log.i("database", "Database opened");
        InputStream leaguesInput = getResources().openRawResource(R.raw.leagues);
        List<League> leagues = JsonParser.leaguesJson(leaguesInput);
        Log.i("jsonParser", "Returned " + leagues.size() + " leagues!");
        for (League league : leagues)
            leaguesData.insertLeague(league);
    }
}