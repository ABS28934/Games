import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import bagel.*;

/** Define a Levels superclass
 *
 */
public abstract class Levels {
    /** The font used for displaying scores. */
    public final Font SCORE_FONT = new Font(ShadowDance.FONT_FILE, 30);
    /** The x and y coordinate on the screen where the score is displayed. */
    public final static int SCORE_LOCATION = 35;

    private int levelScore;
    private String csvFile;
    private static int laneNum = 0;
    private boolean finished = false;
    private final Accuracy accuracy = new Accuracy();
    private final ArrayList<NormalLane> normalLanes = new ArrayList<>();
    private SpecialLane specialLane = new SpecialLane();

    /**
     * Constructor for the Levels class.
     *
     * @param levelScore The initial score for the level.
     * @param csvFile    The path to the CSV file containing level data.
     *
     */
    public Levels(int levelScore,String csvFile){
        this.levelScore = levelScore;
        this.csvFile = csvFile;

    }

    /**
     * Method used to read file and create lanes that contain notes
     */
    public void readCsv() {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String textRead;
            while ((textRead = br.readLine()) != null) {
                String[] splitText = textRead.split(",");

                if (splitText[0].equals("Lane") && !splitText[1].equals("Special")) {
                    // reading normal lanes
                    String laneType = splitText[1];
                    int pos = Integer.parseInt(splitText[2]);
                    Keys relevantKey = Lane.getRelevantKey(laneType);
                    Image laneImage = Lane.getLaneImage(laneType);
                    NormalLane normalLane = new NormalLane(laneType, pos, relevantKey, laneImage);
                    normalLanes.add(normalLane);
                    laneNum++;


                }
                 else if (splitText[0].equals("Lane") && splitText[1].equals("Special")) {
                     // reading special lane
                    String laneType = splitText[1];
                    int pos = Integer.parseInt(splitText[2]);
                    Keys relevantKey = Lane.getRelevantKey(laneType);
                    Image laneImage = Lane.getLaneImage(laneType);
                    specialLane = new SpecialLane(laneType,pos,relevantKey,laneImage);
                }
                else if (!splitText[0].equals("Lane") && !splitText[0].equals("Special")) {
                    // reading normal notes
                    String noteType = splitText[0];
                    NormalLane normalLane = null;
                    for (int i = 0; i < laneNum; i++) {
                        if (normalLanes.get(i).getLaneType().equals(noteType)) {
                            normalLane = normalLanes.get(i);
                        }
                    }
                    if (normalLane != null) {
                        switch (splitText[1]) {
                            case "Normal":
                                NormalNote normalNote = new NormalNote(noteType, Integer.parseInt(splitText[2]));
                                normalLane.addNormal(normalNote);
                                break;
                            case "Hold":
                                HoldNote holdNote = new HoldNote(noteType, Integer.parseInt(splitText[2]));
                                normalLane.addHold(holdNote);
                                break;
                            case "Bomb":
                                SpecialNote bomb = new SpecialNote(splitText[1], Integer.parseInt(splitText[2]));
                                normalLane.addBomb(bomb);
                                break;

                        }
                    }
                } else if (splitText[0].equals("Special")) {
                    // reading special notes
                    String noteType = splitText[1];
                         SpecialNote specialNote = new SpecialNote(noteType,Integer.parseInt(splitText[2]));
                       specialLane.addSpecial(specialNote);

                    }
                }

            } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Check if the level is finished by checking if each
     * normal lane is finished drawing notes
     *
     * @return boolean This returns whether the game is finished.
     */
    public boolean checkFinished() {
        for (int i = 0; i < laneNum; i++) {
            if (!normalLanes.get(i).isNormalFinished()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the number of lanes in the level.
     *
     * @return int This returns the number of lanes.
     */
    public static int getLaneNum() {
        return laneNum;
    }

    /**
     * Get a list of normal lanes in the level.
     *
     * @return ArrayList<NormalLane> Returns an arrray list of normal lanes.
     */

    public ArrayList<NormalLane> getNormalLanes() {
        return normalLanes;
    }
    /**
     * Get the special lane in the level.
     *
     * @return SpecialLane Returns the special lane.
     */
    public SpecialLane getSpecialLane() {
        return specialLane;
    }

    /**
     * Set the score for the level.
     *
     * @param levelScore The current level score that will be reassigned
     */
    public void setLevelScore(int levelScore) {
        this.levelScore = levelScore;
    }

    /**
     * Get the level's score
     *
     * @return int Return the level's score
     */
    public int getLevelScore() {
        return levelScore;
    }

    /**
     * Get Accuracy for the level
     *
     * @return Accuracy Returns the accuracy attribute
     */
    public Accuracy getAccuracy() {
        return accuracy;
    }

    /**
     * Sets whether the level is finished
     *
     * @param finished The current status to be reassigned
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Get the level's completion status
     *
     * @return boolean Returns whether the level is finished
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Sets the lane number
     *
     * @param laneNum The lane number to be reassigned
     */
    public static void setLaneNum(int laneNum) {
        Levels.laneNum = laneNum;
    }

}
