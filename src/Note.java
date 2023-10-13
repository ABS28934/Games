import bagel.*;

/**
 * Superclass for Notes
 */

public abstract class Note {
    private Image noteImage;
    private static int speed = 2;
    private int frameNumber;

    private String noteType;
    private int yCoordinate = 0;

    private boolean active = false;
    private boolean completed = false;
    private boolean pressed = false ;

    private boolean bombed = false;

    /**
     * Constructor for a note
     *
     * @param noteType The type of the note.
     * @param frameNumber The frame number when the note should appear.
     */
    public Note(String noteType, int frameNumber) {
        this.noteType = noteType;
        this.frameNumber = frameNumber;
    }

    /**
     * Draws the note on the screen.
     *
     * @param x The x-coordinate where the note should be drawn.
     */
    public void draw(int x) {
        if (active) {
            noteImage.draw(x, yCoordinate);
        }
    }

    /**
     * Checks if the note is an active bomb.
     *
     * @return boolean Returns whether a note is an active bomb.
     */
    public boolean isBombed() {
        return bombed;
    }

    /**
     * Sets whether the note is bombed or not.
     *
     * @param bombed Current state to be reassigned.
     */
    public void setBombed(boolean bombed) {
        this.bombed = bombed;
    }

    /**
     * Sets the image for the note.
     *
     * @param noteImage Image to be reassigned.
     */
    public void setNoteImage(Image noteImage) {
        this.noteImage = noteImage;
    }

    /**
     * Sets the y-coordinate of the note.
     *
     * @param yCoordinate The y-coordinate to be reassigned.
     */
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Sets the speed of the note.
     *
     * @param speed The speed to be reassigned.
     */
    public static void setSpeed(int speed) {
        Note.speed = speed;
    }

    /**
     * Checks if the note is currently active.
     *
     * @return boolean Returns whether it is completed.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Checks if the note is completed.
     *
     * @return boolean Returns whether it is completed.
     */
    public boolean isCompleted() {return completed;}

    /**
     * Gets the speed of the note.
     *
     * @return int Returns the speed of the note.
     */
    public static int getSpeed() {
        return speed;
    }

    /**
     * Gets the type of the note.
     *
     * @return String Returns the type of the note.
     */
    public String getNoteType() {
        return noteType;
    }

    /**
     * Deactivates the note and marks it as completed.
     */
    public void deactivate() {
        active = false;
        completed = true;
    }

    /**
     * Gets the y-coordinate of the note.
     *
     * @return int Returns the y-coordinate of the note.
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     * Checks if the note is pressed.
     *
     * @return boolean Returns whether note is pressed.
     */
    public boolean isPressed() {
        return pressed;
    }

    /**
     * Sets whether the note is pressed or not.
     *
     * @param pressed Current state to be reassigned.
     */
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    /**
     * Updates the state of the note by drawing it
     * and controlling its movement
     */
    public void update() {
        if (active) {
            yCoordinate += speed;
        }
        if (ShadowDance.getFrameCount() >= frameNumber && !completed) {
            active =true;
        }
    }

}
