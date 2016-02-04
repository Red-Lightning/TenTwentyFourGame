package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Grid {
    Array<Tile> grid2 = new Array<Tile>(Constants.GRID_SIZE*Constants.GRID_SIZE);

    Tile[][] grid = new Tile[Constants.GRID_SIZE][Constants.GRID_SIZE];


    private void addNewTile() {
        int x;
        int y;
        int tileVal;
        int size = 1;
        x = MathUtils.random(Constants.GRID_SIZE-1);
        y = MathUtils.random(Constants.GRID_SIZE-1);
        while( grid[x][y] != null || size < Constants.GRID_SIZE*Constants.GRID_SIZE) {
            x = MathUtils.random(Constants.GRID_SIZE-1);
            y = MathUtils.random(Constants.GRID_SIZE-1);
            size++;
        }
        grid[x][y] = new Tile(MathUtils.random(1,2), new Vector2(x,y));
    }

    // initialize the grid by adding a Tile at location 1,1
    public void init() {

        for(int i=0; i < Constants.GRID_SIZE; i++)
            for(int j=0; j< Constants.GRID_SIZE; j++)
                grid[i][j] = null;

        //grid2.add(new Tile(2, new Vector2(1,1)));
        /*
        grid[0][0] = new Tile(2, new Vector2(0,0));
        grid[0][2] = new Tile(4, new Vector2(0,2));
        grid[2][1] = new Tile(4, new Vector2(2,1));
        grid[3][0] = new Tile(2, new Vector2(3,0));
        grid[3][3] = new Tile(8, new Vector2(3,3));
        */
        addNewTile();
        addNewTile();

    }

    public void moveRight() {

        // loop through the rows
        boolean putNewTile = false;

        for(int row = 0; row < Constants.GRID_SIZE; row++)
        {
            int range = 0;
            boolean flag = false;
            // loop through each col
            for(int col = Constants.GRID_SIZE-2; col >= 0; col--)
            {
                if( grid[row][col] != null ) {
                    for(int i = col; i < Constants.GRID_SIZE-1 - range; i++)
                    {
                        if( grid[row][i+1] != null ) {
                            if( grid[row][i+1].tileVal == grid[row][i].tileVal ) {
                                grid[row][i+1].tileVal *= 2;
                                grid[row][i] = null;
                                flag = true;
                                putNewTile = true;
                                break;
                            }

                        } else {
                            grid[row][i].moveRight();
                            putNewTile = true;
                            grid[row][i+1] = grid[row][i];
                            grid[row][i] = null;
                        }
                    }
                }
                if( flag ) { range++; flag = false; }
            }
        }

        if( putNewTile ) { addNewTile(); }

    }

    public void moveLeft() {

        boolean putNewTile = false;

        for(int row = 0; row < Constants.GRID_SIZE; row++)
        {
            int range = 0;
            boolean flag = false;
            // loop through each col
            for(int col = 1; col < Constants.GRID_SIZE; col++)
            {
                if( grid[row][col] != null ) {
                    for(int i = col; i > range; i--)
                    {
                        if( grid[row][i-1] != null ) {
                            if( grid[row][i-1].tileVal == grid[row][i].tileVal ) {
                                grid[row][i-1].tileVal *= 2;
                                grid[row][i] = null;
                                flag = true;
                                putNewTile = true;
                                break;
                            }

                        } else {
                            grid[row][i].moveLeft();
                            putNewTile = true;
                            grid[row][i-1] = grid[row][i];
                            grid[row][i] = null;
                        }
                    }
                }
                if( flag ) { range++; flag = false; }
            }
        }

        if( putNewTile ) { addNewTile(); }

    }

    public void moveUp() {

        boolean putNewTile = false;

        for(int col = 0; col < Constants.GRID_SIZE; col++)
        {
            int range = 0;
            boolean flag = false;
            // loop through each col
            for(int row = 1; row < Constants.GRID_SIZE; row++)
            {
                if( grid[row][col] != null ) {
                    for(int i = row; i > range; i--)
                    {
                        if( grid[i-1][col] != null ) {
                            if( grid[i-1][col].tileVal == grid[i][col].tileVal ) {
                                grid[i-1][col].tileVal *= 2;
                                grid[i][col] = null;
                                flag = true;
                                putNewTile = true;
                                break;
                            }

                        } else {
                            grid[i][col].moveUp();
                            putNewTile = true;
                            grid[i-1][col] = grid[i][col];
                            grid[i][col] = null;
                        }
                    }
                }
                if( flag ) { range++; flag = false; }
            }
        }

        if( putNewTile ) { addNewTile(); }
    }

    public void moveDown() {

        boolean putNewTile = false;

        for(int col = 0; col < Constants.GRID_SIZE; col++)
        {
            int range = 0;
            boolean flag = false;
            // loop through each col
            for(int row = Constants.GRID_SIZE-2; row >= 0; row--)
            {
                if( grid[row][col] != null ) {
                    for(int i = row; i < Constants.GRID_SIZE-1 - range; i++)
                    {
                        if( grid[i+1][col] != null ) {
                            if( grid[i+1][col].tileVal == grid[i][col].tileVal ) {
                                grid[i+1][col].tileVal *= 2;
                                grid[i][col] = null;
                                flag = true;
                                putNewTile = true;
                                break;
                            }

                        } else {
                            grid[i][col].moveDown();
                            putNewTile = true;
                            grid[i+1][col] = grid[i][col];
                            grid[i][col] = null;
                        }
                    }
                }
                if( flag ) { range++; flag = false; }
            }
        }

        if( putNewTile ) { addNewTile(); }
    }

    // Renders all Tiles in the grid
    public void render(ShapeRenderer renderer) {
        for(int i=0; i<Constants.GRID_SIZE; i++)
            for (int j = 0; j <Constants.GRID_SIZE ; j++) {
                if(grid[i][j] != null)
                    grid[i][j].render(renderer);
            }
    }

    public void renderText(SpriteBatch batch, BitmapFont font) {
        for(int i=0; i<Constants.GRID_SIZE; i++)
            for (int j = 0; j <Constants.GRID_SIZE ; j++) {
                if(grid[i][j] != null)
                    grid[i][j].renderText(batch, font);
            }
    }


}
