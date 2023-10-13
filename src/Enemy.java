import bagel.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class for enemy characters
 */
public class Enemy extends Character {
    private int speed = 1;
    private int direction;
    private boolean active = false;
    // The maximum distance for collision detection.
    private static final int COLLISION_DISTANCE = 104;

    // x-coordinates the trigger direction change
    private static final int DIR_CHANGE_1 = 100;
    private static final int DIR_CHANGE_2 = 900;


    /**
     * Constructor for an enemy
     *
     * @param characterType The character type (enemy in this case)
     */
    public Enemy(String characterType) {
        super(characterType);
        setRandomDirection();
        setxCoordinate(randomX());
        setyCoordinate(randomY());
    }

    /**
     * Updates the position of the enemy and its stealing of normal notes.
     *
     * @param normalNotes An ArrayList of normal notes in the game.
     * @param x           The x-coordinate representing a note's x coordinate.
     */
    public void update(ArrayList<NormalNote> normalNotes, int x) {
        if (active) {
            drawCharacter();
            move();
            steal(normalNotes,x);
        }
    }

    /**
     * Checks if the enemy character is currently active.
     *
     * @return boolean Returns whether the enemy is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the enemy character's activity status.
     *
     * @param active Current state to be reassigned.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    // Randomly set the initial direction (-1 for left, 1 for right)
    private void setRandomDirection() {
        direction = (new Random().nextBoolean()) ? -1 : 1;
    }

    // Generate a random x-coordinate between 100 and 900
    private int randomX() {
        int minX = 100;
        int maxX = 900;
        return new Random().nextInt((maxX - minX) + 1) + minX;
    }

    // Generate a random y-coordinate between 100 and 500
    private int randomY() {
        int minY = 100;
        int maxY = 500;
        return new Random().nextInt((maxY - minY) + 1) + minY;
    }

    // Controls the movement of the enemy
    private void move() {
        setxCoordinate(getxCoordinate() + speed * direction);
        if (getxCoordinate() <= DIR_CHANGE_1) {
            direction = 1; // Reverse direction when reaching 100
        } else if (getxCoordinate() >= DIR_CHANGE_2) {
            direction = -1; // Reverse direction when reaching 900
        }
    }

    // Determines any of the normal notes have been stolen by the enemy
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
