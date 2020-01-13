package com.bladejs.hexgame

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector3

import com.badlogic.gdx.Input.Keys.*

class HexGame : ApplicationAdapter() {
    private var camera: OrthographicCamera? = null
    private var batch: SpriteBatch? = null
    private var font: BitmapFont? = null
    private var shapeRenderer: ShapeRenderer? = null
    private var grid: HexGrid? = null

    override fun create() {
        batch = SpriteBatch()
        camera = OrthographicCamera()
        camera!!.setToOrtho(false, 600f, 600f)
        shapeRenderer = ShapeRenderer()
        grid = HexGrid(30, 30, 32f)
        font = BitmapFont()
        val inputProcessor = HexInputProcessor(camera, grid)
        Gdx.input.inputProcessor = inputProcessor
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        camera!!.update()

        if (grid!!.isTouched) {
            shapeRenderer!!.projectionMatrix = camera!!.combined
            shapeRenderer!!.begin(ShapeRenderer.ShapeType.Filled)
            shapeRenderer!!.color = Color.BLACK
            grid!!.fill(shapeRenderer!!)
            shapeRenderer!!.end()
        }

        shapeRenderer!!.projectionMatrix = camera!!.combined
        shapeRenderer!!.begin(ShapeRenderer.ShapeType.Line)
        shapeRenderer!!.color = Color.BLACK
        grid!!.draw(shapeRenderer)
        shapeRenderer!!.end()

        batch!!.projectionMatrix = camera!!.combined
        batch!!.begin()
        grid!!.sign(batch, font)
        batch!!.end()

        if (Gdx.input.justTouched()) {
            val touchPos = Vector3()
            touchPos.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
            camera!!.unproject(touchPos)
            grid!!.touch(touchPos.x, touchPos.y)
        }

        val speed: Float
        if (Gdx.input.isKeyPressed(SHIFT_LEFT) || Gdx.input.isKeyPressed(SHIFT_RIGHT))
            speed = 5f
        else
            speed = 0.5f
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) grid!!.update(-speed, 0f)
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) grid!!.update(speed, 0f)
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) grid!!.update(0f, speed)
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) grid!!.update(0f, -speed)
    }

    override fun dispose() {
        batch!!.dispose()
        shapeRenderer!!.dispose()
    }
}
