import bagel.*;
public class Level1 extends Levels{

    public Level1(int levelScore, int targetScore, String csvFile){
        super(levelScore,targetScore, csvFile);
        readCsv();
    }

    public void update(Input input){
        SCORE_FONT.drawString("Score " + super.getLevelScore(), SCORE_LOCATION, SCORE_LOCATION);
        for (int i = 0; i < getLaneNum(); i++) {
            setLevelScore(getLevelScore() + getNormalLanes().get(i).update(input, getAccuracy()));
        }

        getAccuracy().update();
        setFinished(checkFinished());
    }
}
