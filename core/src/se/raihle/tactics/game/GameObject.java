package se.raihle.tactics.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import se.raihle.tactics.PrecisePosition;

/**
 * Created by Jacob on 2014-07-22.
 */
public class GameObject {
	private float opacity = 1f;

	private int width, height;

	private PrecisePosition pos;

	public GameObject() {
		this(0, 0, 0, 0);
	}

	public GameObject(int x, int y, int width, int height) {
		this.width = width;
		this.height = height;
		this.pos = new PrecisePosition(x, y);
	}

	// horizontally centered, vertically bottom-aligned
	public void renderTo(SpriteBatch batch) {
		/*batch.setColor(1,1,1,opacity);
		batch.draw(image, pos.getX() - width / 2, pos.getY(), width, height);*/
	}

	public void addX(float delta) {
		pos.addX(delta);
	}

	public int getX() {
		return pos.getX();
	}

	public void setX(int newX) {
		pos.setX(newX);
	}

	public void addY(float delta) {
		pos.addY(delta);
	}

	public int getY() {
		return pos.getY();
	}

	public void setY(int newY) {
		pos.setY(newY);
	}

	public int getTop() {
		return getY() + getHeight();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Rectangle getRect() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public float getOpacity() {
		return opacity;
	}

	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}
}
