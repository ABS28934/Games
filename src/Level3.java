import bagel.*;

import java.util.ArrayList;

public class Level3 extends Levels{

    private Guardian guardian = new Guardian("guardian");

    private final ArrayList<Enemy> enemies = new ArrayList<>();

    private static final int ENEMY_FRAME = 600;

    private int indexEnemies = 0;

    public Level3(int levelScore, String csvFile){
        super(levelScore, csvFile);
    }

    public void update(Input input){
        SCORE_FONT.drawString("Score " + super.getLevelScore(), SCORE_LOCATION, SCORE_LOCATION);
        guardian.update(input,enemies,enemyActive());
        if (ShadowDance.getFrameCount()%ENEMY_FRAME == 0){
            enemies.add(new Enemy("enemy"));
            enemies.get(indexEnemies).setActive(true);
            indexEnemies++;
        }
        for (int i = 0; i < enemies.size(); i++) {
            for (int j = 0; j < getLaneNum(); j++) {
                enemies.get(i).update(getNormalLanes().get(j).getNormalNotes(),getNormalLanes().get(j).getXCoordinate());
            }
        }
        for (int i = 0; i < getLaneNum(); i++) {
            super.setLevelScore(getLevelScore() + super.getNormalLanes().get(i).update(input, getAccuracy()));
        }

        super.setLevelScore(getLevelScore() + super.getSpecialLane().update(input, getAccuracy()));

        super.getAccuracy().update();
        super.setFinished(checkFinished());
        if (checkFinished()){
            for (int i=0; i< getLaneNum();i++){
                getNormalLanes().get(i).clearNotes();
            }
            getSpecialLane().clearNotes();
            getNormalLanes().clear();
            setLaneNum(0);

        }


    }

    public boolean enemyActive(){
        for (Enemy enemy: enemies){
            if (enemy.isActive()){
                return true;
            }
        }
        return false;
    }



}
