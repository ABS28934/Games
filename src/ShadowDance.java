import bagel.*;

/**
 * Skeleton Code for SWEN20003 Project 2, Semester 2, 2023
 * Please enter your name below
 * @AnandiNambiar
 */
public class ShadowDance extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final Image BACKGROUND_IMAGE = new Image("res/background.png");
    public final static String FONT_FILE = "res/FSO8BITR.TTF";

    private final static String GAME_TITLE = "SHADOW DANCE";

    private final Font TITLE_FONT = new Font(FONT_FILE, 64);
    private final static int TITLE_X = 220;
    private final static int TITLE_Y = 250;
    private static final String INSTRUCTIONS = "SELECT LEVELS WITH\n      NUMBER KEYS\n\n       1     2     3";

    private final Font INSTRUCTION_FONT = new Font(FONT_FILE, 24);
    private final static int INSTRUCTION_X = 320;
    private final static int INSTRUCTION_Y = 440;
    private static final String SCORE_MESSAGE = "SCORE";
    private static final String WIN_MESSAGE = "CLEAR!";
    private static final String LOSE_MESSAGE = "TRY AGAIN";

    private final static int END_MESSAGE = 300;

    private static final String REPLAY_INSTRUCTIONS = "PRESS SPACE TO RETURN LEVEL SELECTION";
    private final static int REPLAY_INSTRUCTIONS_Y = 500;
    private boolean showInstructions = true;
    private boolean gameWon = false;
    private boolean gameLost = false;
    private boolean level1 = false;

    private boolean level2 = false;
    private boolean level3 = false;

    private static int frameCount = 0;
    private static int score = 0;
    private final static int LEVEL1_TARGET = 150;
    private final static String LEVEL1_CSV = "res/level1.csv";
    private final static int LEVEL2_TARGET = 400;
    private final static String LEVEL2_CSV = "res/level2.csv";


    private Level1 level_1;
    private Level2 level_2;



    public ShadowDance(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
            level_1 = new Level1(score, LEVEL1_CSV);
            level_2 = new Level2(score, LEVEL2_CSV);


    }

    /**
     * Method used to read file and create objects (you can change
     * this method as you wish).
     */

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowDance game = new ShadowDance();
        game.run();
    }


    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     */
    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }
        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);

        if (showInstructions) {
            // instruction screen
            TITLE_FONT.drawString(GAME_TITLE, TITLE_X, TITLE_Y);
            INSTRUCTION_FONT.drawString(INSTRUCTIONS,
                    INSTRUCTION_X, INSTRUCTION_Y);
            if (input.wasPressed(Keys.NUM_1)){
                level1 = true;
                showInstructions = false;
                level_1.readCsv();
            }
            if (input.wasPressed(Keys.NUM_2)){
                level2 = true;
                showInstructions = false;
                level_2.readCsv();
            }
            if (input.wasPressed(Keys.NUM_3)){
                level3 = true;
                showInstructions = false;
            }
        }
        if (level1 && !level2 && !level3){
            frameCount++;
            level_1.update(input);
            if (level_1.isFinished()) {
                level1 = false;
                // Check if the score beats the target score
                if (level_1.getLevelScore() >= LEVEL1_TARGET) {
                    gameWon = true;
                } else {
                    gameLost = true;
                }
            }

        } else if (level2 && !level1 && !level3){
            frameCount=frameCount+1;
            level_2.update(input);
            if (level_2.isFinished()) {
                level2 = false;
                System.out.println(level_2.getLevelScore());
                // Check if the score beats the target score
                if (level_2.getLevelScore() >= LEVEL2_TARGET) {
                    gameWon = true;
                } else {
                    gameLost = true;
                }
            }



        } else if (level3 && !level2 && !level1){


        }
        if (gameWon){
            TITLE_FONT.drawString(WIN_MESSAGE, WINDOW_WIDTH/2 - 130 ,END_MESSAGE);
            INSTRUCTION_FONT.drawString(REPLAY_INSTRUCTIONS, WINDOW_HEIGHT/2 - 200, REPLAY_INSTRUCTIONS_Y);
            if (input.wasPressed(Keys.SPACE)){
                gameWon = false;
                showInstructions = true;
                frameCount = 0;
                level_1 = new Level1(score,LEVEL1_CSV);
                level_2 = new Level2(score,LEVEL2_CSV);
            }
        }
        if (gameLost){
            TITLE_FONT.drawString(LOSE_MESSAGE, WINDOW_WIDTH/2 - 220 ,END_MESSAGE);
            INSTRUCTION_FONT.drawString(REPLAY_INSTRUCTIONS, WINDOW_HEIGHT/2 - 200, REPLAY_INSTRUCTIONS_Y);
            if (input.wasPressed(Keys.SPACE)){
                gameLost = false;
                showInstructions = true;
                frameCount = 0;
                level_1 = new Level1(score,LEVEL1_CSV);
                level_2 = new Level2(score,LEVEL2_CSV);

            }
        }
    }
    public static int getFrameCount() {
        return frameCount;
    }
}
