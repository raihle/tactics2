package se.raihle.tactics.jobs;

import se.raihle.tactics.game.GameObject;

/**
 * Created by Jacob on 2014-07-22.
 */
public class FadeInOut implements Job {

	private final JobQueue queue;

	public FadeInOut(GameObject object, int enterOffsetX, int enterOffsetY, int exitOffsetX, int exitOffsetY, float enterTime, float stayTime, float exitTime) {
		Move enterMove = new Move(object, enterOffsetX, enterOffsetY);
		Fade enterFade = new Fade(object, 0, 1, enterTime);
		Glide enterGlide = new Glide(object, -enterOffsetX, -enterOffsetY, enterTime);
		Wait wait = new Wait(stayTime);
		Fade exitFade = new Fade(object, 1, 0, exitTime);
		Glide exitGlide = new Glide(object, exitOffsetX, exitOffsetY, exitTime);

		queue = new JobQueue(enterMove, new JobGroup(enterFade, enterGlide), wait, new JobGroup(exitFade, exitGlide));
	}

	@Override
	public void execute(float delta) {
		queue.execute(delta);
	}

	@Override
	public boolean isFinished() {
		return queue.isFinished();
	}

	@Override
	public boolean isImmediate() {
		return queue.isImmediate();
	}
}
