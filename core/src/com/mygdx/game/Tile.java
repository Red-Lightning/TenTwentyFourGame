package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Tile {
    Vector2 tileLocation;
    int tileVal;
    Vector2 tilePos = new Vector2();

    // Constructor
    public Tile(int val, Vector2 loc) {
        this.tileLocation = loc;
        this.tileVal = val;
    }

    public void update() {
        tilePos.x = (Constants.TILE_SIZE)*tileLocation.y + Constants.TILE_PADDING*(tileLocation.y+1);
        tilePos.y = (Constants.TILE_SIZE)*(Constants.GRID_SIZE - 1 - tileLocation.x) + Constants.TILE_PADDING*(Constants.GRID_SIZE - tileLocation.x);
    }

    // moves tile right
    public void moveRight() {
        if(tileLocation.y < Constants.GRID_SIZE-1) tileLocation.y++;
    }


    public void moveLeft() {
        if(tileLocation.y > 0) tileLocation.y--;
    }

    public void moveUp() {
        if(tileLocation.x > 0) tileLocation.x--;
    }

    public void moveDown() {
        if(tileLocation.x < Constants.GRID_SIZE-1) tileLocation.x++;
    }


    public void render(ShapeRenderer renderer) {
        update();
        renderer.setColor(new Color(0.9f, 0.85f/tileVal, 0.65f/tileVal, 1));
        renderer.rect(tilePos.x, tilePos.y, Constants.TILE_SIZE, Constants.TILE_SIZE);

    }

    public void renderText(SpriteBatch batch, BitmapFont font) {
        final GlyphLayout layout = new GlyphLayout(font, new Integer(tileVal).toString());
        font.draw(batch, layout, tilePos.x + (Constants.TILE_SIZE - layout.width)/2, tilePos.y + (Constants.TILE_SIZE+layout.height)/2);
    }
}
