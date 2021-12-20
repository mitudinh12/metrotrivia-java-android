package fi.tudinhmetropolia.metrotrivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ChoseModeActivity extends AppCompatActivity {
    public static final String TAG = "Test: ";
    public static final String EXTRA = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_mode);
    }

    public void choseEasy(View V) {
        String chosenDifficulty = "easy";
        Intent game = new Intent(this, fi.tudinhmetropolia.metrotrivia.TheGameActivity.class);
        startActivity(game);
        //set difficulty value to singleton (GameGlobalModel) to get questions and update medal in the correct mode
        GameGlobalModel.getInstance().setDifficulty(chosenDifficulty);
    }

    public void choseMedium(View V) {
        String chosenDifficulty = "medium";
        Intent game = new Intent(this, fi.tudinhmetropolia.metrotrivia.TheGameActivity.class);
        startActivity(game);
        //set difficulty value to singleton (GameGlobalModel) to get questions and update medal in the correct mode
        GameGlobalModel.getInstance().setDifficulty(chosenDifficulty);

    }

    public void choseHard(View V) {
        String chosenDifficulty = "hard";
        Intent game = new Intent(this, fi.tudinhmetropolia.metrotrivia.TheGameActivity.class);
        startActivity(game);
        //set difficulty value to singleton (GameGlobalModel) to get questions and update medal in the correct mode
        GameGlobalModel.getInstance().setDifficulty(chosenDifficulty);

    }
}