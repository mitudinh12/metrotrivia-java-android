package fi.tudinhmetropolia.metrotrivia;

public class GameGlobalModel {
    // to get difficulty value from any activity
    private static GameGlobalModel instance;
    private String difficulty = "";
    private String medal = "";

    public static GameGlobalModel getInstance() {
        if (instance == null) {
            instance = new GameGlobalModel();
        }
        return instance;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void resetDifficulty() {
        difficulty = "";
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    public void resetMedal() {
        this.medal = "";
    }
}

