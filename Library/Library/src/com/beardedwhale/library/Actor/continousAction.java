package com.beardedwhale.library.Actor;

public abstract class continousAction extends Action{
	
	public continousAction() {
		super();
	}
	
	public void run(float dt) {
		super.run(dt);
		doMainAction(dt);

		if (endCondition()) {
			finished = true;
		}
	}
	
	public abstract boolean endCondition();
}
