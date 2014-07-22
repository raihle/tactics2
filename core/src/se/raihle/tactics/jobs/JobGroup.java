package se.raihle.tactics.jobs;

/**
 * Created by Jacob on 2014-07-22.
 * A group of jobs which will be executed simultaneously
 */
public class JobGroup implements Job {

	private final Job[] jobs;
	private boolean finished;
	private boolean immediate;

	public JobGroup(Job... jobs) {
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
				job.execute(delta);
				if (!job.isImmediate()) {
					finished = false;
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
