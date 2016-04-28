package com.mygdx.tetrisoncrack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


public class MenuScreen implements Screen {

    SpriteBatch batch = new SpriteBatch();
    Vector3 touchPoint = new Vector3();

    OrthographicCamera cam;
    Sprite mm_sprite;
    Game game;

    public MenuScreen(Game game) {
        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);

    }

    @Override
    public void render(float delta) {

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
