package com.bladejs.hexgame;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class HexInputProcessor implements InputProcessor {

    private Vector3 touchPos;
    private HexGrid grid;
    private Camera camera;
    private float prevX, prevY;

    HexInputProcessor(Camera camera, HexGrid grid) {
        this.camera = camera;
        touchPos = new Vector3();
        this.grid = grid;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        camera.unproject(touchPos.set(screenX, screenY, 0));
        prevX = touchPos.x;
        prevY = touchPos.y;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        camera.unproject(touchPos.set(screenX, screenY, 0));
        grid.update(touchPos.x - prevX, touchPos.y - prevY);
        prevX = touchPos.x;
        prevY = touchPos.y;
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
