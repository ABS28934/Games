import bagel.*;

public class Level1 extends Levels{



    public Level1(int levelScore, String csvFile){
        super(levelScore, csvFile);
        readCsv();
    }

    public void update(Input input){
        SCORE_FONT.drawString("Score " + super.getLevelScore(), SCORE_LOCATION, SCORE_LOCATION);
        for (int i = 0; i < getLaneNum(); i++) {
            super.setLevelScore(getLevelScore() + super.getNormalLanes().get(i).update(input, getAccuracy()));
        }
        super.getAccuracy().update();
        super.setFinished(checkFinished());

        if (checkFinished()){
            setLevelScore(0);
            for (int i = 0; i < super.getLaneNum(); i++) {
                 super.getNormalLanes().get(i).setCompleted();
            }

        }
    }

}
