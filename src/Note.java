import bagel.*;

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


    public Note(String noteType, int frameNumber) {
        this.noteType = noteType;
        this.frameNumber = frameNumber;
    }

    public void draw(int x) {
        if (active) {
            noteImage.draw(x, yCoordinate);
        }
    }

    public boolean isBombed() {
        return bombed;
    }

    public void setBombed(boolean bombed) {
        this.bombed = bombed;
    }

    public void setNoteImage(Image noteImage) {
        this.noteImage = noteImage;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public static void setSpeed(int speed) {
        Note.speed = speed;
    }


    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
    public boolean isCompleted() {return completed;}

    public int getFrameNumber() {
        return frameNumber;
    }

    public static int getSpeed() {
        return speed;
    }

    public String getNoteType() {
        return noteType;
    }

    public void deactivate() {
        active = false;
        completed = true;
    }



    public int getyCoordinate() {
        return yCoordinate;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public void update() {
        if (active) {
            yCoordinate += speed;
        }
        if (ShadowDance.getFrameCount() >= frameNumber && !completed) {
            active =true;
        }



    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
