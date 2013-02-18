package com.beardedwhale.library.Actor;

public abstract class timerAction extends Action{

	public float preTime;
	public float runTime;
	public float postTime;

	/**
	 * @param pre How long the preaction is run.
	 * @param main How long the action is run.
	 * @param post How long the postaction is run.
	 */
	public timerAction(float pre, float main, float post) {
		super();
		preTime = pre;
		runTime = preTime + main;
		postTime = runTime + post;

		finished = false;
	}

	@Override
	public void run(float dt) {
		super.run(dt);

		if (clock < preTime) {
			doPreAction(dt);
		}
		else if (clock < runTime) {
			doMainAction(dt);
		}
		else if (clock < postTime) {
			doPostAction(dt);
		}
		else {
			finished = true;
		}
	}

	public abstract void doPreAction(float dt);

	public abstract void doPostAction(float dt);

}
