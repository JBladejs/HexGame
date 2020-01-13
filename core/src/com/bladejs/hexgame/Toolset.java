package com.bladejs.hexgame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

class Toolset {
    static float getTextCenter(BitmapFont bitmapFont, String value) {
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(bitmapFont, value);
        return glyphLayout.width / 2;
    }
}
