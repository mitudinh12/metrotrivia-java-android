package fi.tudinhmetropolia.metrotrivia;

/* Create a plain old java class (POJO representation) for JSON data using RoboPOJOGenerator plugin
 * Source: https://www.geeksforgeeks.org/convert-json-string-to-java-object-using-gson/*/

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class QuestionDataModel {

    @SerializedName("difficulty")
    private String difficulty;

    @SerializedName("correct_answer")
    private String correctAnswer;

    @SerializedName("category")
    private String category;

    @SerializedName("type")
    private String type;

    @SerializedName("question")
    private String question;

    @SerializedName("incorrect_answers")
    private List<String> incorrectAnswers;

    public String getDifficulty() {
        return difficulty;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public static class MedalsOfMode {
        private ArrayList<ModeResult> mode;

        public MedalsOfMode(ArrayList<ModeResult> modes) {
            this.mode = mode;
        }

    }
}
