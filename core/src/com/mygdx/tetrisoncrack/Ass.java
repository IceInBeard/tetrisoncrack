package com.mygdx.tetrisoncrack;

        import java.util.HashMap;
        import java.util.HashSet;
        import java.util.Map;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.Texture.TextureFilter;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.graphics.g2d.TextureRegion;
        import com.badlogic.gdx.math.Rectangle;
        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.Input.Keys;

public class Ass {

    public static TextureRegion menuScreen , gameScreen, pauseScreen, blockTextureRegion;
    public static Rectangle tetrisScreenGrid, menuScreenStartButton , gamePauseButton, pauseScreenMenuButton;
    public static Color black;

    // Load assets
    public static void load(){

        // Background for main menu
        Texture menuScreenTexture;
        menuScreenTexture = new Texture(Gdx.files.internal("TetrisOnCrack-mainmenu.png"));
        menuScreenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        menuScreen = new TextureRegion(menuScreenTexture, 0, 0, 480, 800);

        // We have the buttons on the background so we make a clickable rectangle
        // May implement Stage and Actors?
        menuScreenStartButton = new Rectangle(50, 410, 380, 90);

        // Background for pause screen
        Texture pauseScreenTexture;
        pauseScreenTexture = new Texture(Gdx.files.internal("TetrisOnCrack-pausemenu.png"));
        pauseScreenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        pauseScreen = new TextureRegion(pauseScreenTexture, 0, 0, 480, 800);

        // We have the buttons on the background so we make a clickable rectangle
        // May implement Stage and Actors?
        pauseScreenMenuButton = new Rectangle(50, 90, 380, 90);


        // Background for game screen
        Texture gameScreenTexture;
        gameScreenTexture = new Texture(Gdx.files.internal("TetrisOnCrack-spelplan.png"));
        gameScreenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        gameScreen = new TextureRegion(gameScreenTexture, 0, 0, 480, 800);

        // We have the buttons on the background so we make a clickable rectangle
        // May implement Stage and Actors?
        gamePauseButton = new Rectangle(390, 10, 80, 80);

        // The area where the blocks are falling and stuff
        tetrisScreenGrid = new Rectangle(40, 20, 340, 660);


        //
        // Block colors (might change, more to come)
        //

        black = rgb(0,0,0);

        // Block texture
        Texture blockTexture;
        blockTexture = new Texture(Gdx.files.internal("block.png"));
        blockTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        blockTextureRegion = new TextureRegion(blockTexture, 1, 1, 34, 34);


    }

    // Convert RGB colors to this wiered system
    static Color rgb(int r, int g, int b){
        return new Color(r / 255f, g / 255f, b / 255f, 1.0f);
    }

    // So X mark where there is a block and O where there isn't
    // Will be updated with something like 1, 2, 3, 4 or something
    // for different colored blocks
    final static int X = 1;
    final static int O = 0;

    // Classical tetris shapes
    // ( I-shape, O-shape, T-shape, L-shape, J-shape, Z-shape and S-shape)
    // Stored upside down becasue the grid starts from bottom left
    public static int[][][] tetrisPieces = new int[][][]{
            {{O,X,O,O},
             {O,X,O,O},
             {O,X,O,O},
             {O,X,O,O}},

            {{X,X},
             {X,X}},

            {{O,X,O},
             {X,X,X},
             {O,O,O}},

            {{O,X,X},
             {O,X,O},
             {O,X,O}},

            {{X,X,O},
             {O,X,O},
             {O,X,O}},

            {{X,O,O},
             {X,X,O},
             {O,X,O}},

            {{O,O,X},
             {O,X,X},
             {O,X,O}}
    };

}
