package com.mygdx.tetrisoncrack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;


public class MenuScreen implements Screen {

    SpriteBatch batch = new SpriteBatch();
    Vector3 touchPoint = new Vector3();

    OrthographicCamera cam;
    Sprite mainMenu_sprite;
    Game game;

    int languageSelectX;


    public MenuScreen(Game game) {
        this.game = game;

        languageSelectX = 95;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 480, 800);

        mainMenu_sprite = new Sprite(Ass.menuScreen);
        mainMenu_sprite.setPosition(0, 0);




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

        // We have the buttons on the background so we make a clickable rectangle
        // May implement Stage, Actors and event listeners?
        if (pushed(Ass.menuScreenStartButton)){
            game.setScreen(new TetrisScreen(game));
        }

        if (pushed(Ass.menuScreenLanguageButton)){
            if(Ass.locale.getLanguage() == "en"){
                Ass.locale = new Locale("sv", "SE");
                languageSelectX = 245;
            } else {
                Ass.locale = new Locale("en", "GB");
                languageSelectX = 95;
            }

            Ass.myBundle = I18NBundle.createBundle(Ass.baseFileHandle, Ass.locale);
        }

        // Add background color
        Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        batch.draw(mainMenu_sprite, 0, 0);

        batch.draw(Ass.selectCircle, languageSelectX, 85);



        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}
