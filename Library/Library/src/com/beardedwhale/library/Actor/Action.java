package com.beardedwhale.library.Actor;

public abstract class Action {

	public float clock = 0;

	public Actor parent;

	public boolean firstRun = true;
	public boolean finished = false;

	public Action() {
		firstRun = true;
	}

	public void run(float dt) {
		clock += dt;
	}

	public void prepare() {
	}

	public void doMainAction(float dt) {
	}

	public abstract void cleanUp();

}
