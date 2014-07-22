package se.raihle.tactics.jobs;

/**
 * Created by Jacob on 2014-07-22.
 */
public interface Job {

	public void execute(float delta);

	public boolean isFinished();

	public boolean isImmediate();
}
