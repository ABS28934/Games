import bagel.*;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Character {

    private int speed = 1;
    private int direction;
    private boolean active = false;
    private int xCoordinate;
    private int yCoordinate;

    public static final int COLLISION_DISTANCE = 104;
    public static final int DIR_CHANGE_1 = 100;
    public static final int DIR_CHANGE_2 = 900;

    public Enemy(String characterType) {
        super(characterType);
        setRandomDirection();
        setxCoordinate(randomX());
        setyCoordinate(randomY());
    }

    public void update(ArrayList<NormalNote> normalNotes, int x) {
        if (active) {
            drawCharacter();
            move();
            steal(normalNotes,x);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private void setRandomDirection() {
        // Randomly set the initial direction (-1 for left, 1 for right)
        direction = (new Random().nextBoolean()) ? -1 : 1;
    }

    private int randomX() {
        // Generate a random x-coordinate between 100 and 900
        int minX = 100;
        int maxX = 900;
        return new Random().nextInt((maxX - minX) + 1) + minX;
    }

    private int randomY() {
        // Generate a random y-coordinate between 100 and 500
        int minY = 100;
        int maxY = 500;
        return new Random().nextInt((maxY - minY) + 1) + minY;
    }

    private void move() {
        setxCoordinate(getxCoordinate() + speed * direction);
        if (getxCoordinate() <= DIR_CHANGE_1) {
            direction = 1; // Reverse direction when reaching 100
        } else if (getxCoordinate() >= DIR_CHANGE_2) {
            direction = -1; // Reverse direction when reaching 900
        }
    }

    private void steal(ArrayList<NormalNote> normalNotes, int x){
        for (NormalNote note: normalNotes){
            if (Math.abs(getyCoordinate() - note.getyCoordinate()) <= COLLISION_DISTANCE && Math.abs(getxCoordinate() - x) <= COLLISION_DISTANCE){
                if (note.isActive()){
                    note.deactivate();
                }
            }
        }
    }

}
