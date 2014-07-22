package se.raihle.tactics;

/**
 * Created by Jacob on 2014-07-22.
 */
public class PrecisePosition {
	private int x;
	private int y;
	private float xError;
	private float yError;

	public PrecisePosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int newX) {
		x = newX;
		xError = 0f;
	}

	public int getY() {
		return y;
	}

	public void setY(int newY) {
		y = newY;
		yError = 0f;
	}

	public void addX(float delta) {
		xError += delta;
		x += (int) xError;
		xError = xError % 1;
	}

	public void addY(float delta) {
		yError += delta;
		y += (int) yError;
		yError = yError % 1;
	}


}
