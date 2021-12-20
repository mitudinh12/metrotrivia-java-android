package fi.tudinhmetropolia.metrotrivia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfileScreen extends AppCompatActivity {
    public static final String EASY_BRONZE_KEY = "Easy bronzes";
    public static final String EASY_SILVER_KEY = "Easy silver";
    public static final String EASY_GOLD_KEY = "Easy golds";
    public static final String MEDIUM_BRONZE_KEY = "Medium bronzes";
    public static final String MEDIUM_SILVER_KEY = "Medium silvers";
    public static final String MEDIUM_GOLD_KEY = "Medium golds";
    public static final String HARD_BRONZE_KEY = "Hard bronzes";
    public static final String HARD_SILVER_KEY = "Hard silvers";
    public static final String HARD_GOLD_KEY = "Hard golds";
    private ModeResult easyModeResult;
    private ModeResult mediumModeResult;
    private ModeResult hardModeResult;
    String modeChosen = GameGlobalModel.getInstance().getDifficulty(); //get difficulty value from singleton
    ListView allModesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        findViews(); //find views from xml files
        initializeModeWithMedals(); // initialize ModeResult objects
        loadFromSharedPreferences(); //get data from shared preferences
        updateCorrectMedal(modeToUpdateMedal()); //get mode and medal from Singleton to update ProfileScreen
        displayMedals(); //display all the medals from all the modes in ProFileScreen
    }

    private void displayMedals() {
        ArrayList<ModeResult> listOfModes = new ArrayList<ModeResult>(Arrays.asList(
                easyModeResult, mediumModeResult, hardModeResult)); //list of all ModeResult objects
        MedalAdapter medalAdapter = new MedalAdapter(listOfModes); //instantialize Adapter, read data from the list of allModeResult objects
        allModesListView.setAdapter(medalAdapter); //display ListView
    }

    private void findViews() {
        allModesListView = findViewById(R.id.mode_list_view);
    }

    private ModeResult modeToUpdateMedal() {
        switch (modeChosen) { //get mode chosen by user from ChoseModeActivity, which has also been set to singleton GameGlobalModel
            case "medium":
                return mediumModeResult;
            case "hard":
                return hardModeResult;
            default:
                return easyModeResult;
        }
    }

    private void initializeModeWithMedals() {
        easyModeResult = new ModeResult("easy", 0, 0, 0);
        mediumModeResult = new ModeResult("medium", 0, 0, 0);
        hardModeResult = new ModeResult("hard", 0, 0, 0);
    }

    private void updateCorrectMedal(ModeResult modePlayedResult) {
        switch (GameGlobalModel.getInstance().getMedal()) { //get medal acquired from GameEndActivity, which has also been set to singleton GameGlobalModel
            case "bronze":
                modePlayedResult.incrementMedal("bronze");
                break;
            case "silver":
                modePlayedResult.incrementMedal("silver");
                break;
            case "gold":
                modePlayedResult.incrementMedal("gold");
                break;
            case "none":
                break;
        }
        saveToSharedPreferences();
        //Reset difficulty and medal from Singleton for a new game session
        GameGlobalModel.getInstance().resetDifficulty();
        GameGlobalModel.getInstance().resetMedal();
    }

    public void saveToSharedPreferences() {
        // Save/write data when app is closed
        SharedPreferences prefPut = getSharedPreferences("Application", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefPut.edit();
        editor.putInt(EASY_BRONZE_KEY, easyModeResult.getBronzeNumbers());
        editor.putInt(EASY_SILVER_KEY, easyModeResult.getSilverNumbers());
        editor.putInt(EASY_GOLD_KEY, easyModeResult.getGoldNumbers());

        editor.putInt(MEDIUM_BRONZE_KEY, mediumModeResult.getBronzeNumbers());
        editor.putInt(MEDIUM_SILVER_KEY, mediumModeResult.getSilverNumbers());
        editor.putInt(MEDIUM_GOLD_KEY, mediumModeResult.getGoldNumbers());

        editor.putInt(HARD_BRONZE_KEY, hardModeResult.getBronzeNumbers());
        editor.putInt(HARD_SILVER_KEY, hardModeResult.getSilverNumbers());
        editor.putInt(HARD_GOLD_KEY, hardModeResult.getGoldNumbers());

        editor.apply();
        editor.commit();
    }


    public void loadFromSharedPreferences() {
        //Load/read data when app is launched
        SharedPreferences prefGet = getSharedPreferences("Application", Activity.MODE_PRIVATE);
        easyModeResult.setBronzeNumbers(prefGet.getInt(EASY_BRONZE_KEY, 0));
        easyModeResult.setSilverNumbers(prefGet.getInt(EASY_SILVER_KEY, 0));
        easyModeResult.setGoldNumbers(prefGet.getInt(EASY_GOLD_KEY, 0));

        mediumModeResult.setBronzeNumbers(prefGet.getInt(MEDIUM_BRONZE_KEY, 0));
        mediumModeResult.setSilverNumbers(prefGet.getInt(MEDIUM_SILVER_KEY, 0));
        mediumModeResult.setGoldNumbers(prefGet.getInt(MEDIUM_GOLD_KEY, 0));

        hardModeResult.setBronzeNumbers(prefGet.getInt(HARD_BRONZE_KEY, 0));
        hardModeResult.setSilverNumbers(prefGet.getInt(HARD_SILVER_KEY, 0));
        hardModeResult.setGoldNumbers(prefGet.getInt(HARD_GOLD_KEY, 0));
    }
}