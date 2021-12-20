package fi.tudinhmetropolia.metrotrivia;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TheGameActivity extends AppCompatActivity {
    final Handler handler = new Handler();
    Gson gson = new Gson();
    private int progressPercent = 0;
    private ProgressBar progressBar;
    private Button answer1Button;
    private Button answer2Button;
    private Button answer3Button;
    private Button answer4Button;
    private Button nextButton;
    private TextView questionTextView;
    private String chosenDifficulty = "easy";
    private List<QuestionDataModel> questionList; //store all questions from 1 json file
    private int questionCounter = 0; //determine the current question
    private Integer correctAnswerCounter = 0; //count the score

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_game);
        chosenDifficulty = GameGlobalModel.getInstance().getDifficulty();//get chosen difficulty from singleton
        resetProgressBar(); //reset the progress bar whenever a new game set is open
        loadQuestionList(); //get data from json file
        findViews(); //find views from xml file
        setAnswers(); //display answers randomly to the screen
        setQuestion(); //display question to the screen
        setAnswerClickListener(answer1Button); //react to user's answer choice (correct or incorrect)
        setAnswerClickListener(answer2Button);
        setAnswerClickListener(answer3Button);
        setAnswerClickListener(answer4Button);
        nextButtonClickListener(nextButton); //button to move user to the next activity where they can check the game result
    }

    private void findViews() {
        answer1Button = findViewById(R.id.answer_1_button);
        answer2Button = findViewById(R.id.answer_2_button);
        answer3Button = findViewById(R.id.answer_3_button);
        answer4Button = findViewById(R.id.answer_4_button);
        questionTextView = findViewById(R.id.question_text_view);
        progressBar = findViewById(R.id.progress_bar);
        nextButton = findViewById(R.id.next_button);
    }

    private void setAnswers() {
        ArrayList<String> answers = new ArrayList<>(); // a list of all 4 answers
        answers.addAll(questionList.get(questionCounter).getIncorrectAnswers());
        answers.add(questionList.get(questionCounter).getCorrectAnswer());
        Collections.shuffle(answers); //Shuffle the answer list. Source: https://www.geeksforgeeks.org/shuffle-or-randomize-a-list-in-java/
        // Assign random answers to answer buttons
        answer1Button.setText(answers.get(0));
        answer2Button.setText(answers.get(1));
        if (answers.size() > 2) {
            answer3Button.setText(answers.get(2));
            answer4Button.setText(answers.get(3));
            answer3Button.setVisibility(View.VISIBLE); //all 4 answers are visible for multiple choice questions
            answer4Button.setVisibility(View.VISIBLE);
        } else {
            answer3Button.setVisibility(View.GONE);//only 2 answers are visible for true/false questions
            answer4Button.setVisibility(View.GONE);
        }
    }

    private void setQuestion() {
        questionTextView.setText(questionList.get(questionCounter).getQuestion());
    }

    private void loadQuestionList() { //choose 5 random question-answers sets from questionList
        String fileName = "easyQuestions";
        switch (chosenDifficulty) { //difficulty value taken from singleton (GameGlobalModel)
            case "medium":
                fileName = "mediumQuestions.json";
                break;
            case "hard":
                fileName = "hardQuestions.json";
                break;
            default:
                fileName = "easyQuestions.json";
                break;
        }
        questionList = Util.convertJsonFileToModel(this, fileName);
        /* Get 5 random questions the whole question list:
        * 1. Shuffle the whole list
        * 2. Choose the top 5 questions and reassign to the question list*/
        Collections.shuffle(questionList);
        questionList = questionList.subList(0, 5);
    }

    private void setAnswerClickListener(Button button) {
        button.setOnClickListener(v -> {
            if (questionCounter < 4) { //Logic for the first 4 questions
                checkAnswer(button.getText().toString(), button);
                setProgressBar();
                openNewQuestion();
            } else if (questionCounter < 5) { //Logic for the final question
                checkAnswer(button.getText().toString(), button);
                setProgressBar();
                nextButton.setVisibility(View.VISIBLE);
            }
        });
    }

    private void openNewQuestion() {
        handler.postDelayed(new Runnable() { //delay 1sec to open up a new question
            @Override
            public void run() {
                questionCounter++;
                enableButtons(); //get user input by making answer buttons clickable again
                setQuestion();
                setAnswers();
                resetAnswerButtonColor(answer1Button); //reset answer button color to original
                resetAnswerButtonColor(answer2Button);
                resetAnswerButtonColor(answer3Button);
                resetAnswerButtonColor(answer4Button);
            }
        }, 1000);
    }

    private void checkAnswer(String answer, Button button) {
        //check user input (check whether the choice is correct or incorrect answer)
        if (answer.equals(questionList.get(questionCounter).getCorrectAnswer())) {
            button.setBackground(getResources().getDrawable(R.drawable.custom_correct_answer));
            correctAnswerCounter++;
        } else {
            button.setBackground(getResources().getDrawable(R.drawable.custom_incorrect_answer));
        }
        disableButtons();
    }

    private void setProgressBar() { //show how many questions have been unfold (5 totals)
        progressPercent += 20;
        progressBar.setProgress(progressPercent);
        progressBar.setMax(100);
    }

    private void resetAnswerButtonColor(Button button) {
        button.setBackground(getResources().getDrawable(R.drawable.custom_answer_button));
    }

    private void resetProgressBar() {
        progressPercent = 0;
    }

    private void nextButtonClickListener(Button button) {
        nextButton.setOnClickListener(V -> {
            Intent intent = new Intent(this, fi.tudinhmetropolia.metrotrivia.GameEndActivity.class);
            intent.putExtra("score", correctAnswerCounter.toString()); //determine which medal to give in the next activity
            startActivity(intent);
        });
    }

    private void enableButtons() {
        answer1Button.setEnabled(true);
        answer2Button.setEnabled(true);
        answer3Button.setEnabled(true);
        answer4Button.setEnabled(true);
    }
    private void disableButtons(){
        answer1Button.setEnabled(false);
        answer2Button.setEnabled(false);
        answer3Button.setEnabled(false);
        answer4Button.setEnabled(false);
    }
}