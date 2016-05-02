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

    public static TextureRegion menuScreen;


    // Load assets
    public static void load(){

        Texture menuScreenTexture;

        menuScreenTexture = new Texture(Gdx.files.internal("main-screen.png"));
        menuScreenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

            menuScreen = new TextureRegion(menuScreenTexture, 0, 0, 480, 800);
    }

    // Convert RGB colors to this wiered system
    static Color rgb(int r, int g, int b){
        return new Color(r / 255f, g / 255f, b / 255f, 1.0f);
    }

}
