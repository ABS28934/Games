import bagel.*;

/**
 * Superclass for lane
 */
public abstract class Lane {
    private final static int Y_COORDINATE = 384;

    /**
     * The target height of the lane.
     */
    public final static int TARGET_HEIGHT = 657;
    private String laneType;
    private int xCoordinate;
    private Keys relevantKey;
    private Image laneImage;
    /**
     * The initial speed of notes.
     */
    public static final int SPEED = 2;

    /**
     * Constructor for a new lane
     *
     * @param laneType   The type of the lane.
     * @param xCoordinate The x-coordinate of the lane.
     * @param relevantKey The relevant key for the lane.
     * @param laneImage   The image corresponding to the lane.
     */
    public Lane(String laneType, int xCoordinate, Keys relevantKey, Image laneImage) {
        this.laneType = laneType;
        this.xCoordinate = xCoordinate;
        this.relevantKey = relevantKey;
        this.laneImage = laneImage;
    }

    /**
     * Default constructor.
     */
    public Lane(){
        return;
    }


    /**
     * Gets the relevant key associated with the lane.
     *
     * @return Keys Returns the key for the lane
     */
    public Keys getRelevantKey() {
        return relevantKey;
    }

    /**
     * Gets the type of the lane.
     *
     * @return String Returns the type of the lane.
     */
    public String getLaneType() {
        return laneType;
    }

    /**
     * Gets the X-coordinate of the lane.
     *
     * @return int Returns the x-coordinate of the lane.
     */
    public  int getXCoordinate() {
        return xCoordinate;
    }

    /**
     * Gets the relevant key based on the lane type.
     *
     * @param laneType The type of the lane.
     * @return Keys Returns the corresponding key to the lane type.
     */
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

    /**
     * Gets the lane's image based on the lane type.
     *
     * @param laneType The type of the lane.
     * @return Image Returns the image corresponding to the lane.
     */
    public static Image getLaneImage(String laneType) {

        return new Image("res/lane" + laneType + ".png");
    }

    /**
     * Draws the lane on the screen at the given coordinates.
     */
    public void drawLane() {
        laneImage.draw(xCoordinate, Y_COORDINATE);
    }


}
