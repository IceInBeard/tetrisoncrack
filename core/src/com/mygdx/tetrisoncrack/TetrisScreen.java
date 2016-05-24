package com.mygdx.tetrisoncrack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;


public class TetrisScreen implements Screen {

    // The grid size mesaured in blocks
    // based on Tetris standard size
    // Rows above 20 is "off screen"
    final int GRID_HEIGHT = 24;
    final int GRID_WIDTH = 10;

    // Block size is based on 10 blocks to fit in 340 px
    // 340 px the width of the area of the tetrisScreenGrid
    final int BLOCK_SIZE = 34;

    // Time between each tick in the game (like for example when block moves down)
    final float TICK_TIME = 1;
    float tickTimeModifier = 3; // Should increase with every level

    final int POINTS_PER_ROW = 10;

    // Handles the thingimajigg that prints things to the screen
    SpriteBatch batch = new SpriteBatch();

    // Handles user input on the screen
    Vector3 touchPoint = new Vector3();

    OrthographicCamera cam;

    Sprite game_sprite, block_sprite;

    Game game;
    GameState state;

    tetrisPiece currentPiece, nextPiece, nextNextPiece, heldPiece;

    float timeSinceLastTic;

    int rows = 0;
    int score = 0;

    Random randomPieceGenerator;

    // For the penguin animation (animation needs an elaspedTime variable
    Animation penguinAnimation;
    float elapsedTime;

    // Using different game states
    // so we can pause the game for example
    interface GameState {
        void draw();
        void update(float delta);
    }


    // The grid 2-dimensional array where origo is bottom left
    // It's [row][column]
    int[][] grid = new int[GRID_HEIGHT][GRID_WIDTH];

    // Placeholder for the rotated piece
    int[][] rotatedPiece;

    public TetrisScreen(Game game) {

        this.game = game;
        state = new PlayState();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 480, 800);

        game_sprite = new Sprite(Ass.gameScreen);
        game_sprite.setPosition(0, 0);

        block_sprite = new Sprite(Ass.blockTextureRegion);
        block_sprite.setPosition(0, 0);

        penguinAnimation = new Animation(1f/4f, Ass.penguinAnimationRegion);

        timeSinceLastTic = 0;


        /*
        /   Touch control
        */
        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void onUp() {
                // Gdx.app.log("MyTag", "Up");
                if(state instanceof PlayState){
                    rotatedPiece = currentPiece.rotate();
                    if(!collidesWithGridOrWall(rotatedPiece,currentPiece.x,currentPiece.y)){
                        currentPiece.pieceGrid = rotatedPiece;
                    }
                }
            }

            @Override
            public void onRight() {
               // Gdx.app.log("MyTag", "Right");

                if(state instanceof PlayState){
                    if(currentPiece != null && !collidesWithGridOrWall(currentPiece.pieceGrid,currentPiece.x + 1,currentPiece.y)){
                        currentPiece.movePieceRight();
                    }
                } else if(state instanceof tutorialState) {
                   // Gdx.app.log("State","State: Tutorial");
                }



            }

            @Override
            public void onLeft() {
               // Gdx.app.log("MyTag", "Left");

                if(state instanceof PlayState){
                    if(currentPiece != null && !collidesWithGridOrWall(currentPiece.pieceGrid,currentPiece.x - 1,currentPiece.y)) {
                        currentPiece.movePieceLeft();
                    }
                } else if(state instanceof tutorialState) {
                    // Gdx.app.log("State","State: Tutorial");
                }


            }

            @Override
            public void onDown() {
                // Gdx.app.log("MyTag", "Down");
                swipePieceDown();
            }
        }));

    }

      class PlayState implements GameState {


        public void draw(){
            drawGame();
            drawPenguin();
            drawScore(10,780);

        }

        public void update(float delta){

            elapsedTime += delta;

            if(pushed(Ass.gamePauseButton)){
                 state = new PauseState(PlayState.this);
            }

            if(pushed(Ass.gameTutorialButton)){
                state = new tutorialState(PlayState.this);
            }

            if(currentPiece == null) {
                spawnPieceOnGrid();
            }

            // Check if Game over
            if(collidesWithGridOrWall(currentPiece.pieceGrid, currentPiece.x, currentPiece.y)){
                //Gdx.app.log("GameOver","Game Over");
                state = new GameOverState();
            }


            timeSinceLastTic+=delta;

            if(timeSinceLastTic >= TICK_TIME/tickTimeModifier){
                // THIS IS WHERE THE TICK HAPPENS! PARTY!
                if(!collidesWithGridOrWall(currentPiece.pieceGrid, currentPiece.x, currentPiece.y - 1)) {
                    currentPiece.movePieceDown();
                }
                else {
                    pieceToGrid();
                    clearRows();
                    spawnPieceOnGrid();
                }

                timeSinceLastTic = 0;
            }


        }



        void spawnPieceOnGrid(){
            randomPieceGenerator = new Random();
            int randomPieceNumber;

            // Generate next and nextnext blocks on first go
            if(currentPiece == null){
                // Generate a random number 0 to 6 (the different piecetypes)
                randomPieceNumber = randomPieceGenerator.nextInt(7);
                nextPiece = new tetrisPiece(randomPieceNumber);

                // Generate a new random number 0 to 6 (the different piecetypes)
                randomPieceNumber = randomPieceGenerator.nextInt(7);
                nextNextPiece = new tetrisPiece(randomPieceNumber);


            }

            // Generate a random number 0 to 6 (the different piecetypes)
            randomPieceNumber = randomPieceGenerator.nextInt(7);

            currentPiece = nextPiece;
            nextPiece = nextNextPiece;
            nextNextPiece = new tetrisPiece(randomPieceNumber);
        }

      void pieceToGrid(){

          Gdx.input.vibrate(100);

          int size = currentPiece.pieceGrid.length;
          for(int i = 0; i < size; i++){
              for(int j = 0; j < size; j++){
                  if(currentPiece.pieceGrid[i][j] == 1){
                      grid[currentPiece.y + i][currentPiece.x + j] = 1;
                  }
              }
          }
      }

          void clearRows() {
              int rowsCleared = 0;

              for (int i = 0; i < GRID_HEIGHT; ) {
                  boolean full = true;

                  for (int j = 0; j < GRID_WIDTH; j++) {
                      if (grid[i][j] == 0)
                          full = false;
                  }

                  if (full) {
                      // Move rows down...
                      rows++;
                      rowsCleared++;
                      Gdx.input.cancelVibrate();
                      Gdx.input.vibrate(500);

                      for (int k = i; k < GRID_HEIGHT - 1; k++) {
                          grid[k] = grid[k + 1];
                      }

                      grid[GRID_HEIGHT - 1] = new int[GRID_WIDTH];
                  } else {
                      i++;
                  }

              }

              score += rowsCleared * POINTS_PER_ROW;
          }

    }

    class PauseState implements GameState {
        GameState returnState;

        public PauseState(GameState returnState){
            this.returnState = returnState;
        }

        public void draw(){
            batch.draw(Ass.pauseScreen, 0, 0);

            drawScore(10,780);
        }

        public void update(float delta){

            // Should get it's own button in the future
            // now it's just down in the corner because of reasons
            if(pushed(Ass.pauseScreenResumeButton)){
                state = returnState;
            }

            if(pushed(Ass.pauseScreenMenuButton)){
               game.setScreen(new MenuScreen(game));
            }

        }
    }

    class GameOverState implements GameState {


        public GameOverState(){

        }

        public void draw(){
            batch.draw(Ass.gameOverScreen, 0, 0);

            drawScore(10,780);
        }

        public void update(float delta){

            if(pushed(Ass.pauseScreenMenuButton)){
                game.setScreen(new MenuScreen(game));
            }

            if(pushed(Ass.pauseScreenResumeButton)){
                game.setScreen(new TetrisScreen(game));
            }

        }
    }

    class tutorialState implements GameState {
        GameState returnState;
        int tutorialStage;
        String[] tutorialStrings;

        public tutorialState(GameState returnState){
            this.returnState = returnState;
            tutorialStage = 0;
            tutorialStrings = new String[3];
            tutorialStrings[0] = Ass.myBundle.get("tuttText1");
            tutorialStrings[1] = Ass.myBundle.get("tuttText2");
            tutorialStrings[2] = Ass.myBundle.get("tuttText3");

        }

        public void draw(){

            drawGame();
            drawPenguin();
            batch.setColor(1f,1f,1f,0.9f);
            batch.draw(Ass.speechbubbleTextureRegion, 40, 480);
            Ass.font.draw(batch, tutorialStrings[tutorialStage] , 80 , 590 );

            drawScore(10,780);
        }

        public void update(float delta){

            elapsedTime += delta;

            if(pushed(Ass.tutorialSpeachBubbleRect)){
                tutorialStage += 1;

                if(tutorialStage >= tutorialStrings.length){
                    state = returnState;
                }

            }


        }
    }


    // We have the buttons on the background so we make a clickable rectangle
    // May implement Stage, Actors and event listeners?
    boolean pushed(com.badlogic.gdx.math.Rectangle r){
        if (!Gdx.input.justTouched())
            return false;
        cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        return r.contains(touchPoint.x, touchPoint.y);
    }


    @Override
    public void render(float delta) {

        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        batch.setColor(Ass.white);

        state.draw();
        state.update(delta);

        batch.end();

    }

    public boolean collidesWithGridOrWall(int[][] p, int p_x, int p_y){
        // i is p row, j is p column
        for(int i = 0; i < p.length; i++){
            for(int j = 0; j < p.length; j++){
                if(p[i][j] == 1){
                    int b_x = p_x + j;
                    int b_y = p_y + i;

                    if(b_y < 0 || b_y >= GRID_HEIGHT ||
                            b_x < 0 || b_x >= GRID_WIDTH ||
                            grid[b_y][b_x] == 1)
                        return true;
                }
            }
        }

        return false;
    }

    void swipePieceDown(){
        // When swiping down
        // move piece down and play sound?




    }

    void drawGame(){

        batch.draw(game_sprite,  0,  0);

        drawBlocks(grid, Ass.tetrisScreenGrid, 0, 0);

        if(currentPiece != null) {
            drawBlocks(currentPiece.pieceGrid, Ass.tetrisScreenGrid, currentPiece.x, currentPiece.y);

            drawBlocks(nextPiece.pieceGrid,Ass.nextBlock,0,0);
            drawBlocks(nextNextPiece.pieceGrid,Ass.nextNextBlock,0,0);
        }
    }

    void drawScore(int x, int y){


        Ass.font.setColor(Ass.black);
        Ass.font.draw(batch, Ass.myBundle.get("score") + ": " + score , x, y);

    }

    void drawPenguin(){
        // Draw the penguin animation
        batch.draw(penguinAnimation.getKeyFrame(elapsedTime,true) ,150, 695);
    }

    void drawBlocks(int[][] blocks, Rectangle gridRectangle, int x, int y){

        // Used to scale the block size and position to fit within the rectangle
        Matrix4 grid_scale = new Matrix4();
        grid_scale.setToTranslationAndScaling(gridRectangle.x, gridRectangle.y, 0.0f, gridRectangle.width / BLOCK_SIZE, gridRectangle.height / BLOCK_SIZE, 1.0f);
        batch.setTransformMatrix(grid_scale);


        // Go through the grid and print a block on all elements with 1s in them
        for(int i = 0; i < blocks.length; i++){
            for(int j = 0; j < blocks[i].length; j++){
                // Need to be expanded to take more colors than 1
                if(blocks[i][j] == 1 && y + i < GRID_HEIGHT - 2){

                    batch.draw(
                            block_sprite,
                            BLOCK_SIZE * (x + j),
                            BLOCK_SIZE * (y + i));
                }


            }
        }

        batch.setTransformMatrix(new Matrix4().idt());
    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}

