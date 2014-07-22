package se.raihle.tactics.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Jacob on 2014-07-22.
 */
public class Sprite extends GameObject {
	private Texture image;

	public Sprite(Texture image) {
		this(image, 0, 0, image.getWidth(), image.getHeight());
	}

	public Sprite(Texture image, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.image = image;
	}

	// horizontally centered, vertically bottom-aligned
	@Override
	public void renderTo(SpriteBatch batch) {
		batch.setColor(1, 1, 1, getOpacity());
		batch.draw(image, getX() - getWidth() / 2, getY(), getWidth(), getHeight());
	}

	public Texture getImage() {
		return image;
	}
}
