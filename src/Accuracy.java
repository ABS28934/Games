import bagel.*;

/**
 * Class for dealing with accuracy of pressing the notes
 */
public class Accuracy {
    public static final int PERFECT_SCORE = 10;
    public static final int GOOD_SCORE = 5;
    public static final int BAD_SCORE = -1;
    public static final int MISS_SCORE = -5;
    public static final int NOT_SCORED = 0;


    public static final String PERFECT = "PERFECT";
    public static final String GOOD = "GOOD";
    public static final String BAD = "BAD";
    public static final String MISS = "MISS";
    private static final int PERFECT_RADIUS = 15;
    private static final int GOOD_RADIUS = 50;
    private static final int BAD_RADIUS = 100;
    private static final int MISS_RADIUS = 200;
    private static final Font ACCURACY_FONT = new Font(ShadowDance.FONT_FILE, 40);
    private static final int RENDER_FRAMES = 30;
    private String currAccuracy = null;
    private int frameCount = 0;
    public final static int ACTIVATE_DISTANCE = 50;

    public static final String BOMBED = "LANE CLEAR";

    public static final String DOUBLE = "DOUBLE SCORE";

    public static final String SPEED_UP = "SPEED UP";

    public static final String SLOW_DOWN = "SLOW DOWN";

    public static final int SPEED_SCORE = 15;
    public static final int ARBITRARY_SCORE = 10;

    private boolean doubled = false;

    private static final int DOUBLE_FRAME = 480;

    private int scoreChange = 1;
    private int doubledFrameCount = 0;




    public void setAccuracy(String accuracy) {
        currAccuracy = accuracy;
        frameCount = 0;
    }

    public int evaluateScore(int height, int targetHeight, boolean triggered) {
        int distance = Math.abs(height - targetHeight);

        if (triggered) {
            if (distance <= PERFECT_RADIUS) {
                setAccuracy(PERFECT);
                if (doubled){
                    return PERFECT_SCORE*scoreChange;
                }
                return PERFECT_SCORE;
            } else if (distance <= GOOD_RADIUS) {
                setAccuracy(GOOD);
                if (doubled){
                    return GOOD_SCORE*scoreChange;
                }
                return GOOD_SCORE;
            } else if (distance <= BAD_RADIUS) {
                setAccuracy(BAD);
                if (doubled){
                    return BAD_SCORE*scoreChange;
                }
                return BAD_SCORE;
            } else if (distance <= MISS_RADIUS) {
                setAccuracy(MISS);
                if (doubled){
                    return MISS_SCORE*scoreChange;
                }
                return MISS_SCORE;
            }

        } else if (height >= (Window.getHeight())) {
            setAccuracy(MISS);
            if (doubled){
                return MISS_SCORE*scoreChange;
            }
            return MISS_SCORE;
        }

        return NOT_SCORED;

    }
    public int speedUp(int height, int targetHeight, boolean triggered) {
        int distance = Math.abs(height - targetHeight);

        if (triggered) {
            if (distance <= ACTIVATE_DISTANCE) {
                setAccuracy(SPEED_UP);
                Note.setSpeed(Note.getSpeed() + 1);
                if (doubled){
                    return SPEED_SCORE*scoreChange;
                }
                return SPEED_SCORE;
            }
        }

        return NOT_SCORED;
    }
    public int slowDown(int height, int targetHeight, boolean triggered) {
        int distance = Math.abs(height - targetHeight);

        if (triggered) {
            if (distance <= ACTIVATE_DISTANCE) {
                setAccuracy(SLOW_DOWN);
                Note.setSpeed(Note.getSpeed() - 1);
                if (doubled){
                    return SPEED_SCORE*scoreChange;
                }
                return SPEED_SCORE;
            }
        }
        return NOT_SCORED;
    }
    public int bombed() {
            setAccuracy(BOMBED);
            return ARBITRARY_SCORE;
    }
    public int doubled(int height, int targetHeight, boolean triggered) {
        int distance = Math.abs(height - targetHeight);

        if (triggered) {
            if (distance <= ACTIVATE_DISTANCE) {
                setAccuracy(DOUBLE);
                if (doubled){
                    scoreChange *= 2;
                } else {
                    setDoubled(true);
                }
                return ARBITRARY_SCORE;
            }
        }
        return NOT_SCORED;
    }
    public void setDoubled(boolean doubled) {
        this.doubled = doubled;
        if (doubled) {
            doubledFrameCount = DOUBLE_FRAME;  // Initialize the frame count when doubled is set to true
            scoreChange *= 2;
        }
    }


    public void update() {
        frameCount++;
        if (doubled && doubledFrameCount > 0) {
            doubledFrameCount--;
            if (doubledFrameCount == 0) {
                doubled = false;
                scoreChange /= 2;
            }
        }
        if (currAccuracy != null && frameCount < RENDER_FRAMES) {
            ACCURACY_FONT.drawString(currAccuracy,
                    Window.getWidth()/2 - ACCURACY_FONT.getWidth(currAccuracy)/2,
                    Window.getHeight()/2);
        }
    }
}
