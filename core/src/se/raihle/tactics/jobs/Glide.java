package se.raihle.tactics.jobs;

import se.raihle.tactics.game.GameObject;

/**
 * Created by Jacob on 2014-07-22.
 */
public class Glide implements Job {

	private final GameObject object;
	private final int xDelta;
	private final int yDelta;
	private float animationTime;
	private float tick = 0;

	public Glide(GameObject object, int xDelta, int yDelta, float animationTime) {
		if (animationTime <= 0) {
			throw new IllegalArgumentException("animationTime must be > 0");
		}
		this.object = object;
		this.xDelta = xDelta;
		this.yDelta = yDelta;
		this.animationTime = animationTime;
	}

	@Override
	public void execute(float delta) {
		if (!isFinished()) {
			delta = Math.min(delta, animationTime - tick);
			tick += delta;
			object.addX(xDelta * delta / animationTime);
			object.addY(yDelta * delta / animationTime);
		}
	}

	@Override
	public boolean isFinished() {
		return tick >= animationTime;
	}

	@Override
	public boolean isImmediate() {
		return false;
	}
}
