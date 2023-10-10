//import bagel.*;
//
//public class Level2 extends Levels{
//
//    public Level2(int levelScore, String csvFile){
//        super(levelScore, csvFile);
//        readCsv();
//    }
//
//    public void update(Input input){
//        SCORE_FONT.drawString("Score " + super.getLevelScore(), SCORE_LOCATION, SCORE_LOCATION);
//        for (int i = 0; i < getLaneNum(); i++) {
//            super.setLevelScore(getLevelScore() + super.getNormalLanes().get(i).update(input, getAccuracy()));
//        }
//        super.setLevelScore(getLevelScore() + super.getSpecialLane().update(input, getAccuracy()));
//        super.getAccuracy().update();
//
//    }
//
//
//
//}
