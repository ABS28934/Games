import bagel.*;

public abstract class Character {
    private Image characterImage;
    private int xCoordinate;
    private int yCoordinate;

    public Character(String characterType) {
        this.characterImage = new Image("res/"+ characterType +".png");
    }

    public Image getCharacterImage() {
        return characterImage;
    }

    public void setCharacterImage(Image characterImage) {
        this.characterImage = characterImage;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void drawCharacter() {
        characterImage.draw(xCoordinate, yCoordinate);
    }
}
