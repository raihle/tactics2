package se.raihle.tactics.jobs;

/**
 * Created by Jacob on 2014-07-22.
 * A queue of jobs which will be executed in order
 */
public class JobQueue implements Job {

	private final Job[] jobs;
	private boolean finished;
	private boolean immediate;

	public JobQueue(Job... jobs) {
		this.jobs = jobs;
		finished = false;

		immediate = true;
		for (Job job : jobs) {
			if (!job.isImmediate()) {
				immediate = false;
				break;
			}
		}
	}

	@Override
	public void execute(float delta) {
		finished = true;
		for (Job job : jobs) {
			if (!job.isFinished()) {
				if (job.isImmediate()) {
					job.execute(delta);
				} else {
					finished = false;
					job.execute(delta);
					break;
				}
			}
		}
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	@Override
	public boolean isImmediate() {
		return immediate;
	}

}
