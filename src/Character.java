import bagel.*;

/**
 * Defines a superclass for characters in the game
 */
public abstract class Character {
    private Image characterImage;
    private int xCoordinate;
    private int yCoordinate;

    /**
     * Constructor for a character
     *
     * @param characterType The subclass of the character, used to set the image.
     */
    public Character(String characterType) {

        this.characterImage = new Image("res/"+ characterType +".png");
    }

    /**
     * Gets the x-coordinate of the character.
     *
     * @return int Returns x-coordinate of the character.
     */
    public int getxCoordinate() {
        return xCoordinate;
    }

    /**
     * Sets the x-coordinate of the character.
     *
     * @param xCoordinate The y-coordinate to be reset.
     */
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * Gets the y-coordinate of the character.
     *
     * @return int Returns y-coordinate of the character.
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     * Sets the y-coordinate of the character.
     *
     * @param yCoordinate The y-coordinate to be reset.
     */
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Draws the character at its set coordinates.
     */
    public void drawCharacter() {
        characterImage.draw(xCoordinate, yCoordinate);
    }
}
