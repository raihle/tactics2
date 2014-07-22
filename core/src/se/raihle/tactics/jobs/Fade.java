package se.raihle.tactics.jobs;

import se.raihle.tactics.game.GameObject;

/**
 * Created by Jacob on 2014-07-22.
 */
public class Fade implements Job {
	private final GameObject object;
	private final int startOpacity;
	private final int targetOpacity;
	private final float animationTime;
	float tick = 0;

	public Fade(GameObject object, int startOpacity, int targetOpacity, float animationTime) {
		if (animationTime <= 0) {
			throw new IllegalArgumentException("animationTime must be > 0");
		}
		this.object = object;
		this.startOpacity = startOpacity;
		this.targetOpacity = targetOpacity;
		this.animationTime = animationTime;
	}

	@Override
	public void execute(float delta) {
		if (isFinished()) {
			return;
		}

		delta = Math.min(delta, animationTime - tick);
		tick += delta;

		float opacityDifference = targetOpacity - startOpacity;
		float opacityDelta = opacityDifference * tick / animationTime;
		object.setOpacity(startOpacity + opacityDelta);
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
