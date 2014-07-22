package se.raihle.tactics.jobs;

/**
 * Created by Jacob on 2014-07-22.
 */
public class Wait implements Job {

	private float stayTime;
	private float tick;

	public Wait(float stayTime) {
		if (stayTime <= 0) {
			throw new IllegalArgumentException("stayTime must be > 0");
		}

		this.stayTime = stayTime;
	}

	@Override
	public void execute(float delta) {
		tick += delta;
	}

	@Override
	public boolean isFinished() {
		return tick >= stayTime;
	}

	@Override
	public boolean isImmediate() {
		return false;
	}
}
