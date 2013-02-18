package com.beardedwhale.library.Actor;

public abstract class waitAction extends Action{

	public float waitTime;

	/**
	 * @param waitTime How long to wait.
	 */
	public waitAction(float waitingTime) {
		super();
		waitTime = waitingTime;
		finished = false;
	}

	@Override
	public void run(float dt) {
		super.run(dt);
		if (clock >waitTime) {
		    finished = true;
		}
	}
}
