package se.raihle.tactics.jobs;

import se.raihle.tactics.game.GameObject;

/**
 * Created by Jacob on 2014-07-22.
 */
public class Move implements Job {
	private final GameObject object;
	private final int xDelta;
	private final int yDelta;
	private boolean finished;

	public Move(GameObject object, int xDelta, int yDelta) {
		this.object = object;
		this.xDelta = xDelta;
		this.yDelta = yDelta;
	}

	@Override
	public void execute(float delta) {
		if (isFinished()) {
			return;
		}
		finished = true;
		object.addX(xDelta);
		object.addY(yDelta);
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	@Override
	public boolean isImmediate() {
		return true;
	}
}
