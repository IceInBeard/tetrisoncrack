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

    public static TextureRegion menuScreen , gameScreen;
    public static Rectangle menuScreenStartButton;


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

        // Background for game screen
        Texture gameScreenTexture;
        gameScreenTexture = new Texture(Gdx.files.internal("TetrisOnCrack-spelplan.png"));
        gameScreenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        gameScreen = new TextureRegion(gameScreenTexture, 0, 0, 480, 800);

    }

    // Convert RGB colors to this wiered system
    static Color rgb(int r, int g, int b){
        return new Color(r / 255f, g / 255f, b / 255f, 1.0f);
    }

}
