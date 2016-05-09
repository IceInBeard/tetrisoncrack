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


public class TetrisScreen implements Screen {

    // The grid size mesaured in blocks
    // based on Tetris standard size
    // Rows above 20 is "off screen"
    final int GRID_HEIGHT = 24;
    final int GRID_WIDTH = 10;

    // Block size is based on 10 blocks to fit in 340 px
    // 340 px the width of the area of the tetrisScreenGrid
    final int BLOCK_SIZE = 34;

    SpriteBatch batch = new SpriteBatch();
    Vector3 touchPoint = new Vector3();

    OrthographicCamera cam;
    Sprite game_sprite, block_sprite;
    Game game;
    GameState state;

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

        // JUST FOR TESTING: Added some random blocks
        grid[0][0] = 1;
        grid[0][9] = 1;
        grid[19][0] = 1;
        grid[19][9] = 1;

        grid[9][5] = 1;
        grid[9][4] = 1;


    }

    class PlayState implements GameState {

        public void draw(){
            drawGame();
        }

        public void update(float delta){
            if(pushed(Ass.gamePauseButton)){
                state = new PauseState(PlayState.this);
            }

            elapsedTime += delta;
            batch.draw(penguinAnimation.getKeyFrame(elapsedTime,true) ,150, 695);
        }
    }

    class PauseState implements GameState {
        GameState returnState;
        public PauseState(GameState returnState){
            this.returnState = returnState;
        }

        public void draw(){
            batch.draw(Ass.pauseScreen, 0, 0);
        }

        public void update(float delta){

            // Should get it's own button in the future
            // now it's just down in the corner because of reasons
            if(pushed(Ass.gamePauseButton)){
                state = returnState;
            }

            if(pushed(Ass.pauseScreenMenuButton)){
               game.setScreen(new MenuScreen(game));
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

    void drawGame(){

        batch.draw(game_sprite,  0,  0);

        drawBlocks(grid, Ass.tetrisScreenGrid, 0, 0);


    }

    void drawBlocks(int[][] blocks, Rectangle gridRectangle, int x, int y){

        Matrix4 grid_scale = new Matrix4();
        grid_scale.setToTranslationAndScaling(gridRectangle.x, gridRectangle.y, 0.0f, gridRectangle.width / BLOCK_SIZE, gridRectangle.height / BLOCK_SIZE, 1.0f);

        batch.setTransformMatrix(grid_scale);



        for(int i = 0; i < blocks.length; i++){
            for(int j = 0; j < blocks[i].length; j++){
                // Need to be expanded to take more colors than 1
                if(blocks[i][j] == 1 && y + i < GRID_HEIGHT - 2){
                   // Gdx.app.log("Draw blocks", "i/j " + i + "/" + j);

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

