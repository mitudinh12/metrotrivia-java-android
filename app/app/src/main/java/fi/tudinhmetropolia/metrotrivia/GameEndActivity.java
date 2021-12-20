package fi.tudinhmetropolia.metrotrivia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameEndActivity extends AppCompatActivity {
    private String correctAnswerTotal = "0";
    private TextView scoreTextView;
    private Button profilePageButton;
    private Button tryAgainButton;
    private ImageView medalImageView;
    TextView noMedalMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);
        correctAnswerTotal = getIntent().getStringExtra("score"); //get score from the game to determine award (medal)
        findViews(); //find views from xml file
        displayResult(); //display the score and awarded medal (or no medal)
        setProfileButtonClickListener(profilePageButton); //button to move to the ProfileScreen activity
        setReplayButtonListener(tryAgainButton); //button to move to the ChoseMode activity
    }


    private void setReplayButtonListener(Button button) {
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, fi.tudinhmetropolia.metrotrivia.ChoseModeActivity.class);
            startActivity(intent);
        });
    }

    private void setProfileButtonClickListener(Button button) {
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, fi.tudinhmetropolia.metrotrivia.ProfileScreen.class);
            startActivity(intent);
        });
    }

    private void displayResult() {
        scoreTextView.setText(correctAnswerTotal); //display number of correct answers
        switch (correctAnswerTotal) { //set medal to singleton to update ProfileScreen
            case "3": //if user gets 3/5 points
                medalImageView.setImageDrawable(getResources().getDrawable(R.drawable.bronze));
                GameGlobalModel.getInstance().setMedal("bronze");
                break;
            case "4": //if user gets 4/5 points
                medalImageView.setImageDrawable(getResources().getDrawable(R.drawable.silver));
                GameGlobalModel.getInstance().setMedal("silver");
                break;
            case "5": //if user gets 5/5 points
                medalImageView.setImageDrawable(getResources().getDrawable(R.drawable.gold));
                GameGlobalModel.getInstance().setMedal("gold");
                break;
            default: //if user doesn't get enough point to be awarded with a medal
                medalImageView.setVisibility(View.GONE);
                noMedalMessage.setVisibility(View.VISIBLE);
                GameGlobalModel.getInstance().setMedal("");
                break;
        }
    }

    private void findViews() {
        scoreTextView = findViewById(R.id.result_text_view);
        profilePageButton = findViewById(R.id.profile_screen_button);
        tryAgainButton = findViewById(R.id.try_again_button);
        medalImageView = findViewById(R.id.medal_image_view);
        noMedalMessage = findViewById(R.id.no_medal_message_text_view);
    }
}