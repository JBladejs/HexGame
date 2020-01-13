package com.bladejs.hexgame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;

class Hexagon {
    static final float SQRT3 = 1.7320f;
    private final float SCALE;

    private float x;
    private float y;
    private float[] vertices;
    private Polygon shape;

    Hexagon(float x, float y, float scale) {
        this.SCALE = scale;
        this.x = x;
        this.y = y;
        refresh();
    }

    void update(float x, float y) {
       this.x += x;
       this.y += y;
       refresh();
    }

    private void refresh(){
        vertices = new float[]{
                x - SCALE, y,
                x - SCALE/2, y + SCALE*SQRT3/2,
                x + SCALE/2, y + SCALE*SQRT3/2,
                x + SCALE, y,
                x + SCALE/2, y - SCALE*SQRT3/2,
                x - SCALE/2, y - SCALE*SQRT3/2
        };
        shape = new Polygon(vertices);
    }

    boolean contains(float x, float y) {
        return shape.contains(x, y);
    }

    void drawShapes(ShapeRenderer shapeRenderer) {
        shapeRenderer.triangle(vertices[0], vertices[1], vertices[2], vertices[3], vertices[10], vertices[11]);
        shapeRenderer.triangle(vertices[4], vertices[5], vertices[6], vertices[7], vertices[8], vertices[9]);
        shapeRenderer.rect(vertices[10], vertices[11], SCALE, SCALE*SQRT3);
    }

    float getX() {
        return x;
    }

    float getY() {
        return y;
    }

    float[] getVertices() {
        return vertices;
    }
}
