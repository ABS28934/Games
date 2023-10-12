import bagel.*;

import java.security.Key;
import java.util.ArrayList;

public class Lane {
    private final static int Y_COORDINATE = 384;
    public final static int TARGET_HEIGHT = 657;
    private String laneType;
    private int xCoordinate;
    private Keys relevantKey;
    private Image laneImage;


    public Lane(String laneType, int xCoordinate, Keys relevantKey, Image laneImage) {
        this.laneType = laneType;
        this.xCoordinate = xCoordinate;
        this.relevantKey = relevantKey;
        this.laneImage = laneImage;
    }

    public Lane(){
        return;
    }


    public Keys getRelevantKey() {
        return relevantKey;
    }

    public String getLaneType() {
        return laneType;
    }

    public  int getXCoordinate() {
        return xCoordinate;
    }

    public static Keys getRelevantKey(String laneType){
        if (laneType.equals("Left")) {
            return Keys.LEFT;
        }
        if (laneType.equals("Right")) {
            return Keys.RIGHT;
        }
        if (laneType.equals("Up")) {
            return Keys.UP;
        }
        if (laneType.equals("Down")) {
            return Keys.DOWN;
        }
        if (laneType.equals("Special")) {
            return Keys.SPACE;
        }
        return null;
    }

    public static Image getLaneImage(String laneType) {

        return new Image("res/lane" + laneType + ".png");
    }

    public void drawLane() {
        laneImage.draw(xCoordinate, Y_COORDINATE);
    }


}
