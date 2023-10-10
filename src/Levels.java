import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import bagel.*;

public abstract class Levels {
    public final Font SCORE_FONT = new Font(ShadowDance.FONT_FILE, 30);
    public final static int SCORE_LOCATION = 35;
    private int levelScore;
    private String csvFile;
    private static int laneNum = 0;
    private boolean finished = false;
    private final Accuracy accuracy = new Accuracy();
    private final ArrayList<NormalLane> normalLanes = new ArrayList<>();

    //private static SpecialLane specialLane = new SpecialLane();

    public Levels(int levelScore,String csvFile){
        this.levelScore = levelScore;
        this.csvFile = csvFile;

    }

    public void readCsv() {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String textRead;
            while ((textRead = br.readLine()) != null) {
                String[] splitText = textRead.split(",");

                if (splitText[0].equals("Lane") && !splitText[1].equals("Special")) {
                    // reading lanes
                    String laneType = splitText[1];
                    int pos = Integer.parseInt(splitText[2]);
                    Keys relevantKey = Lane.getRelevantKey(laneType);
                    Image laneImage = Lane.getLaneImage(laneType);
                    NormalLane normalLane = new NormalLane(laneType, pos, relevantKey,laneImage);
                    normalLanes.add(normalLane);
                    laneNum++;
                } else if (splitText[0].equals("Lane") && splitText[1].equals("Special")) {
                    String laneType = splitText[1];
                    int pos = Integer.parseInt(splitText[2]);
                    Keys relevantKey = Lane.getRelevantKey(laneType);
                    Image laneImage = Lane.getLaneImage(laneType);
                   // specialLane = new SpecialLane(laneType,pos,relevantKey,laneImage);
                }
                else if (!splitText[0].equals("Lane") && !splitText[0].equals("Special")){
                    // reading notes
                    String noteType = splitText[0];
                    NormalLane normalLane = null;
                    //int xCoordinate = 0;
                    for (int i = 0; i < laneNum; i++) {
                        if (normalLanes.get(i).getLaneType().equals(noteType)) {
                            normalLane = normalLanes.get(i);
                            //xCoordinate = normalLane.getXCoordinate();
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
                        }
                    }
//                    } else if (splitText[0].equals("Special")) {
//                        SpecialNote specialNote = new SpecialNote(splitText[1],Integer.parseInt(splitText[2]),specialLane.getXCoordinate());
//                        specialLane.addSpecial(specialNote);
//                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
//        for (int i = 0; i < getLaneNum(); i++) {
//            for (int j = 0; j < normalLanes.get(i).getNumNormals();j++) {
//                System.out.println(normalLanes.get(i).getNormalNotes().get(i).getFrameNumber());
//            }
//        }
            for (int j = 0; j < normalLanes.get(1).getNumNormals();j++) {
                System.out.println(normalLanes.get(1).getNormalNotes().get(j).getFrameNumber());
            }



    }

    public boolean checkFinished() {
        for (int i = 0; i < laneNum; i++) {
            if (!normalLanes.get(i).isNormalFinished()) {
                return false;
            }
        }
        return true;
    }

    public static int getLaneNum() {
        return laneNum;
    }

    public ArrayList<NormalLane> getNormalLanes() {
        return normalLanes;
    }


    public void setLevelScore(int levelScore) {
        this.levelScore = levelScore;
    }


    public int getLevelScore() {
        return levelScore;
    }

    public Accuracy getAccuracy() {
        return accuracy;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isFinished() {
        return finished;
    }


//    public static SpecialLane getSpecialLane() {
//        return specialLane;
//    }
}
