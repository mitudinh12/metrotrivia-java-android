package fi.tudinhmetropolia.metrotrivia;

public class ModeResult {

    private String modeName;
    private Integer bronzeNumbers;
    private Integer silverNumbers;
    private Integer goldNumbers;

    public ModeResult(String modeName, Integer bronzeNumber, Integer silver, Integer goldNumbers) {
        this.modeName = modeName;
        this.bronzeNumbers = bronzeNumbers;
        this.silverNumbers = silverNumbers;
        this.goldNumbers = goldNumbers;
    }

    public Integer getBronzeNumbers() {
        return bronzeNumbers;
    }

    public Integer getSilverNumbers() {
        return silverNumbers;
    }

    public Integer getGoldNumbers() {
        return goldNumbers;
    }

    public String getModeName() {
        return modeName;
    }

    public void setBronzeNumbers(Integer bronzeNumbers) {
        this.bronzeNumbers = bronzeNumbers;
    }

    public void setSilverNumbers(Integer silverNumbers) {
        this.silverNumbers = silverNumbers;
    }

    public void setGoldNumbers(Integer goldNumbers) {
        this.goldNumbers = goldNumbers;
    }

    public void incrementMedal(String medal) {
        switch (medal) {
            case "bronze":
                bronzeNumbers++;
                break;
            case "silver":
                silverNumbers++;
                break;
            case "gold":
                goldNumbers++;
                break;
        }
    }
}
