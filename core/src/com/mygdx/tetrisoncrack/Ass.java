package com.mygdx.tetrisoncrack;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.audio.Music;
        import com.badlogic.gdx.audio.Sound;
        import com.badlogic.gdx.files.FileHandle;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.Texture.TextureFilter;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.graphics.g2d.TextureRegion;
        import com.badlogic.gdx.math.Rectangle;
        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.utils.I18NBundle;

        import java.util.Locale;


public class Ass {

    public static TextureRegion menuScreen , gameScreen, pauseScreen, blockTextureRegion, speechbubbleTextureRegion, selectCircle, gameOverScreen;
    public static TextureRegion[] penguinAnimationRegion;
    public static Rectangle tetrisScreenGrid, menuScreenStartButton, menuScreenLanguageButton , gamePauseButton, gameTutorialButton, pauseScreenMenuButton, pauseScreenResumeButton, tutorialSpeachBubbleRect, nextBlock, nextNextBlock;
    public static Color black, white, green;
    public static Texture menuScreenTexture, pauseScreenTexture, gameScreenTexture, blockTexture, penguinImg, speechbubbleTexture, menuScreenSelect, gameOverScreenTexture;

    public static BitmapFont font;

    //Used for translating stuff
    public static FileHandle baseFileHandle;
    public static I18NBundle myBundle;
    public static Locale locale;

    public static Music backgroundMusic;
    public static Sound gameOverSound, bamSound, babaBamSound, pfffSound, toaPaus, toiletBreak, ploppSound;


    // Load assets
    public static void load(){

        // Background for main menu
        menuScreenTexture = new Texture(Gdx.files.internal("TetrisOnCrack-mainmenu.png"));
        menuScreenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        menuScreen = new TextureRegion(menuScreenTexture, 0, 0, 480, 800);

        // Language selection circle on the main menu
        menuScreenSelect = new Texture(Gdx.files.internal("inringad.png"));
        menuScreenSelect.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        selectCircle = new TextureRegion(menuScreenSelect, 0, 0, 90, 90);

        // We have the buttons on the background so we make a clickable rectangle
        // May implement Stage and Actors?
        menuScreenStartButton = new Rectangle(50, 410, 380, 90);
        menuScreenLanguageButton = new Rectangle(50, 100, 380, 90);

        // Background for Game over screen
        gameOverScreenTexture = new Texture(Gdx.files.internal("TetrisOnCrack-gameover.png"));
        gameOverScreenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        gameOverScreen = new TextureRegion(gameOverScreenTexture, 0, 0, 480, 800);

        // Background for pause screen
        pauseScreenTexture = new Texture(Gdx.files.internal("TetrisOnCrack-pausemenu.png"));
        pauseScreenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        pauseScreen = new TextureRegion(pauseScreenTexture, 0, 0, 480, 800);

        // We have the buttons on the background so we make a clickable rectangle
        // May implement Stage and Actors?
        pauseScreenMenuButton = new Rectangle(50, 90, 380, 90);
        pauseScreenResumeButton = new Rectangle(50, 180, 380, 90);

        // Background for game screen
        gameScreenTexture = new Texture(Gdx.files.internal("TetrisOnCrack-spelplan2.png"));
        gameScreenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        gameScreen = new TextureRegion(gameScreenTexture, 0, 0, 480, 800);

        // Placement of next and nextnext block
        nextBlock = new Rectangle(405, 690,17,17);
        nextNextBlock = new Rectangle(405, 600,17,17);

        // Speech bubble
        speechbubbleTexture = new Texture(Gdx.files.internal("pratbubbla.png"));
        speechbubbleTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        speechbubbleTextureRegion = new TextureRegion(speechbubbleTexture, 0, 0, 340, 200);
        tutorialSpeachBubbleRect = new Rectangle(40,480, 340,200);

        // We have the buttons on the background so we make a clickable rectangle
        // May implement Stage and Actors?
        gamePauseButton = new Rectangle(390, 10, 80, 80);
        gameTutorialButton = new Rectangle(150, 695, 128, 128);


        // The grid (size of one block)
        tetrisScreenGrid = new Rectangle(40, 20, 34, 34);

        //
        // colors (might change, more to come)
        //

        black = rgb(0,0,0);
        white = rgb(255,255,255);
        green = rgb(0,255,0);

        // Block texture
        blockTexture = new Texture(Gdx.files.internal("block2.png"));
        blockTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        blockTextureRegion = new TextureRegion(blockTexture, 0, 0, 34, 34);


        // Splits the penguin sprites into a texture region array
        penguinImg = new Texture(Gdx.files.internal("PenguinSprite_256.png"));
        penguinAnimationRegion = new TextureRegion[4];
        TextureRegion[][] penguinFrameSplitter = TextureRegion.split(penguinImg,128,128);

        penguinAnimationRegion[0] = penguinFrameSplitter[0][0]; // Expressionless
        penguinAnimationRegion[1] = penguinFrameSplitter[0][1]; // Standard arms upp
        penguinAnimationRegion[2] = penguinFrameSplitter[1][0]; // Pooping
        penguinAnimationRegion[3] = penguinFrameSplitter[1][1]; // Happy



        // Fonts
        font = new BitmapFont(Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.fnt"), Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.png"),false, true);

        // Translation and stuff like that
        baseFileHandle = Gdx.files.internal("MyBundle");
        locale = new Locale("en", "GB");
        //Locale locale = new Locale("sv", "SE");
        myBundle = I18NBundle.createBundle(baseFileHandle, locale);


        // Music and sound effects

        backgroundMusic =  Gdx.audio.newMusic(Gdx.files.internal("TetrisBGMusic.mp3"));
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal("Game_Over.mp3"));
        bamSound = Gdx.audio.newSound(Gdx.files.internal("BAM.mp3"));
        babaBamSound = Gdx.audio.newSound(Gdx.files.internal("BABABAM.mp3"));
        pfffSound = Gdx.audio.newSound(Gdx.files.internal("Pfff.mp3"));
        toaPaus = Gdx.audio.newSound(Gdx.files.internal("TOAPAUS.mp3"));
        toiletBreak = Gdx.audio.newSound(Gdx.files.internal("TOILETBREAK.mp3"));
        ploppSound = Gdx.audio.newSound(Gdx.files.internal("PLUUUUPPP.mp3"));




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
