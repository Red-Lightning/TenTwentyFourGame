package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.security.Key;

public class BoardScreen extends InputAdapter implements Screen{

    private final TenTwentyFourGame game;
    FitViewport viewport;
    ShapeRenderer renderer;
    SpriteBatch batch;
    BitmapFont font;
    Grid grid = new Grid();

    public BoardScreen(TenTwentyFourGame game) {

        this.game = game;
    }

    @Override
    public void show() {
        viewport = new FitViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(Constants.FONT_SCALE);
        grid.init();

    }

    @Override
    public void render(float delta) {
        viewport.apply();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.DARK_GRAY);
        for(int i=0; i < Constants.GRID_SIZE; i++) {
            float startX = (Constants.TILE_SIZE)*i + Constants.TILE_PADDING*(i+1);
            for(int j=0; j < Constants.GRID_SIZE; j++) {
                float startY = (Constants.TILE_SIZE)*j + Constants.TILE_PADDING*(j+1);
                renderer.rect(startX, startY, Constants.TILE_SIZE, Constants.TILE_SIZE);
            }
        }

        grid.render(renderer);
        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        grid.renderText(batch, font);
        batch.end();


    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.RIGHT) {
            grid.moveRight();
        }

        if(keycode == Input.Keys.LEFT) {
            grid.moveLeft();
        }

        if(keycode == Input.Keys.UP) {
            grid.moveUp();
        }

        if(keycode == Input.Keys.DOWN) {
            grid.moveDown();
        }

        return true;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
        font.dispose();
        batch.dispose();

    }
}
