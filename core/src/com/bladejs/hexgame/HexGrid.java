package com.bladejs.hexgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import static com.bladejs.hexgame.Hexagon.SQRT3;
import static com.bladejs.hexgame.Toolset.getTextCenter;
import static java.lang.Math.abs;

class HexGrid {
    private Hexagon[][] grid;
    private Hexagon touchedHex;
    private Array<Hexagon> neighboringHexes;

    HexGrid(int width, int height, float scale) {
        neighboringHexes = new Array<>();
        grid = new Hexagon[width][height];
        for (int i = 0; i < grid.length; i++) {
            Hexagon[] row = grid[i];
            for (int j = 0; j < row.length; j++) {
                grid[i][j] = new Hexagon(i*1.5f*scale, j*SQRT3*scale + i*SQRT3/2*scale, scale);
            }
        }
    }

    void draw(ShapeRenderer shapeRenderer) {
        for (Hexagon[] row : grid) {
            for (Hexagon hex : row) {
                if (hex != touchedHex) {
                    shapeRenderer.polygon(hex.getVertices());
                }
            }
        }
    }

    void sign(SpriteBatch batch, BitmapFont font) {
        for (int i = 0; i < grid.length; i++) {
            Hexagon[] row = grid[i];
            for (int j = 0; j < row.length; j++) {
                Hexagon hex = row[j];
                String text = i + "," + j;
                float offset = getTextCenter(font, text);
                if(hex != touchedHex) font.setColor(Color.BLACK);
                else font.setColor(Color.WHITE);
                font.draw(batch, text, hex.getX() - offset, hex.getY() + font.getXHeight() / 2);
            }
        }
    }

    void fill(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(0.1294f, 0.1294f, 0.1294f, 1);
        touchedHex.drawShapes(shapeRenderer);
        shapeRenderer.setColor(0.1294f, 0.5882f, 0.9529f, 1);
        for (Hexagon hex: neighboringHexes) {
            hex.drawShapes(shapeRenderer);
        }
    }

    void touch(float x, float y) {
        outerloop:
        for (int i = 0; i < grid.length; i++) {
            Hexagon[] row = grid[i];
            for (int j = 0; j < row.length; j++) {
                Hexagon hex = row[j];
                if (hex.contains(x, y)) {
                    if(touchedHex == hex) break outerloop;
                    touchedHex = hex;
                    neighboringHexes.clear();
                    for (int k = i - 1; k < i + 2; k++) {
                        for (int l = j - 1; l < j + 2; l++) {
                            if (k >= 0 && l >= 0 && k < grid.length && l < row.length && abs((k - i) + (l - j)) < 2) {
                                Hexagon temp = grid[k][l];
                                if (temp != null && temp != hex) neighboringHexes.add(temp);
                            }
                        }
                    }
                    return;
                }
            }
        }
        touchedHex = null;
        neighboringHexes.clear();
    }

    void update(float x, float y) {
        for (Hexagon[] row: grid) {
            for (Hexagon hex: row) {
                hex.update(x, y);
            }
        }
    }

    boolean isTouched(){
        return touchedHex != null;
    }

}
