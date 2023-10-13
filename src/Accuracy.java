import bagel.*;

/**
 * Class for dealing with accuracy of pressing the notes
 */
public class Accuracy {
    /**
     * The perfect score awarded for perfect accuracy.
     */
    public static final int PERFECT_SCORE = 10;
    /**
     * The score awarded for good accuracy.
     */
    public static final int GOOD_SCORE = 5;
    /**
     * The score awarded for bad accuracy.
     */
    public static final int BAD_SCORE = -1;
    /**
     * The score awarded for missing a note.
     */
    public static final int MISS_SCORE = -5;
    /**
     * The score awarded when accuracy is not evaluated.
     */
    public static final int NOT_SCORED = 0;
    /**
     * The message for perfect accuracy.
     */
    public static final String PERFECT = "PERFECT";
    /**
     * The message for good accuracy.
     */
    public static final String GOOD = "GOOD";
    /**
     * The message for bag accuracy.
     */
    public static final String BAD = "BAD";
    /**
     * The message for a missed note.
     */
    public static final String MISS = "MISS";
    // Maximum distance for each accuracy
    private static final int PERFECT_RADIUS = 15;
    private static final int GOOD_RADIUS = 50;
    private static final int BAD_RADIUS = 100;
    private static final int MISS_RADIUS = 200;
    private static final Font ACCURACY_FONT = new Font(ShadowDance.FONT_FILE, 40);
    private static final int RENDER_FRAMES = 30;
    private String currAccuracy = null;
    private int frameCount = 0;
    /**
     * The activation distance for detecting a special note
     */
    public final static int ACTIVATE_DISTANCE = 50;

    /**
     * Accuracy label for when all notes in a lane are cleared.
     */
    public static final String BOMBED = "LANE CLEAR";

    /**
     * Accuracy label for when the player's score is doubled.
     */
    public static final String DOUBLE = "DOUBLE SCORE";

    /**
     * Accuracy label for when the game speed is increased.
     */
    public static final String SPEED_UP = "SPEED UP";

    /**
     * Accuracy label for when the game speed is decreased.
     */
    public static final String SLOW_DOWN = "SLOW DOWN";

    /**
     * The score awarded for pressing a speed note.
     */
    public static final int SPEED_SCORE = 15;

    private boolean doubled = false;
    private static final int DOUBLE_FRAME = 480;
    private int scoreChange = 1;
    private int doubledFrameCount = 0;
    private int doubleScore = 2;
    private int speedChange = 1;

    /**
     * Sets the current accuracy label and resets framecount.
     *
     * @param accuracy The accuracy label to be set.
     */
    public void setAccuracy(String accuracy) {
        currAccuracy = accuracy;
        frameCount = 0;
    }

    /**
     * Determines the score based on the distance between the player's input and the target height.
     *
     * @param height        The height of the player's input.
     * @param targetHeight  The target height for the note.
     * @param triggered     Indicates whether the note has been pressed or missed.
     * @return int          Returns the score awarded based on accuracy.
     */
    public int evaluateScore(int height, int targetHeight, boolean triggered) {
        int distance = Math.abs(height - targetHeight);

        if (triggered) {
            if (distance <= PERFECT_RADIUS) {
                setAccuracy(PERFECT);
                if (doubled) {
                    return PERFECT_SCORE * scoreChange;
                }
                return PERFECT_SCORE;
            } else if (distance <= GOOD_RADIUS) {
                setAccuracy(GOOD);
                if (doubled) {
                    return GOOD_SCORE * scoreChange;
                }
                return GOOD_SCORE;
            } else if (distance <= BAD_RADIUS) {
                setAccuracy(BAD);
                if (doubled) {
                    return BAD_SCORE * scoreChange;
                }
                return BAD_SCORE;
            } else if (distance <= MISS_RADIUS) {
                setAccuracy(MISS);
                if (doubled) {
                    return MISS_SCORE * scoreChange;
                }
                return MISS_SCORE;
            }
        } else if (height >= (Window.getHeight())) {
            setAccuracy(MISS);
            if (doubled) {
                return MISS_SCORE * scoreChange;
            }
            return MISS_SCORE;
        }

        return NOT_SCORED;
    }

    /**
     * Increases the game speed and updates the accuracy label.
     *
     * @return int  Returns the score awarded for speeding up the game.
     */
    public int speedUp() {
        setAccuracy(SPEED_UP);
        Note.setSpeed(Note.getSpeed() + speedChange);
        if (doubled) {
            return SPEED_SCORE * scoreChange;
        }
        return SPEED_SCORE;
    }

    /**
     * Decreases the game speed and updates the accuracy label.
     *
     * @return int  Returns the score awarded for slowing down the game.
     */
    public int slowDown() {
        setAccuracy(SLOW_DOWN);
        Note.setSpeed(Note.getSpeed() - speedChange);
        if (doubled) {
            return SPEED_SCORE * scoreChange;
        }
        return SPEED_SCORE;
    }

    /**
     * Updates the accuracy label to indicate the lane has been bombed
     */
    public void bombed() {
        setAccuracy(BOMBED);
    }

    /**
     * Sets double to true and changes the accuracy label
     */
    public void doubled() {
        setAccuracy(DOUBLE);
        if (doubled) {
            scoreChange *= doubleScore;
        } else {
            setDoubled(true);
        }
    }

    /**
     * Sets whether the player's score is doubled.
     *
     * @param doubled   Current state to be reset.
     */
    public void setDoubled(boolean doubled) {
        this.doubled = doubled;
        if (doubled) {
            doubledFrameCount = DOUBLE_FRAME;  // Initialize the frame count when doubled is set to true
            scoreChange *= doubleScore;
        }
    }

    /**
     * Updates the accuracy and handles the display of the accuracy label on the screen.
     */
    public void update() {
        frameCount++;
        if (doubled && doubledFrameCount > 0) {
            doubledFrameCount--;
            if (doubledFrameCount == 0) {
                doubled = false;
                scoreChange /= doubleScore;
            }
        }
        if (currAccuracy != null && frameCount < RENDER_FRAMES) {
            ACCURACY_FONT.drawString(currAccuracy,
                    Window.getWidth() / 2 - ACCURACY_FONT.getWidth(currAccuracy) / 2,
                    Window.getHeight() / 2);
        }
    }
}
