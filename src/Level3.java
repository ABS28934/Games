import bagel.*;

public class Level3 extends Levels{

    public Level3(int levelScore, String csvFile){
        super(levelScore, csvFile);
    }

    public boolean checkFinished() {
        for (int i = 0; i < getLaneNum(); i++) {
            if (!getNormalLanes().get(i).isNormalFinished()) {
                return false;
            }
        }
        if (!getSpecialLane().isSpecialFinished()) {
            return false;
        }
        return true;
    }

    public void update(Input input){
        SCORE_FONT.drawString("Score " + super.getLevelScore(), SCORE_LOCATION, SCORE_LOCATION);
        for (int i = 0; i < getLaneNum(); i++) {
            super.setLevelScore(getLevelScore() + super.getNormalLanes().get(i).update(input, getAccuracy()));
        }

        super.setLevelScore(getLevelScore() + super.getSpecialLane().update(input, getAccuracy()));

        super.getAccuracy().update();
        super.setFinished(checkFinished());
        if (checkFinished()){
            setLevelScore(0);
            for (int i=0; i< getLaneNum();i++){
                getNormalLanes().get(i).clearNotes();
            }
            getSpecialLane().clearNotes();
            getNormalLanes().clear();
            setLaneNum(0);

        }


    }



}
