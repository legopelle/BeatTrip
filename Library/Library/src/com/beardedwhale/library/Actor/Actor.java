package com.beardedwhale.library.Actor;

import java.util.ArrayList;
import java.util.List;

import com.beardedwhale.library.Actor.Action;
import com.beardedwhale.library.Actor.Actor;

public class Actor {

	public List<Action> instantActions = new ArrayList<Action>();
	public List<Action> actionQueue = new ArrayList<Action>();

	public void act(float dt) {
		
		//To avoid conflicts when an action modifies the list we
		//must clone the list:
		List<Action> instantActionsClone = new ArrayList<Action>();
		instantActionsClone.addAll(instantActions);
		
		for (Action action : instantActionsClone) {
			if (action != null) {
				doAction(action, dt);
				if (action.finished) {
					action.cleanUp();
				}
			}
		}
		cleanUpActions();

		if (actionQueue.size() > 0) {
			Action firstInLine = actionQueue.get(0);
			if (firstInLine != null) {
				doAction(firstInLine, dt);
				if (firstInLine.finished) {
					firstInLine.cleanUp();
					actionQueue.remove(0);
				}
			}
		}
	}

	private void cleanUpActions() {
		for (int i = 0; i < instantActions.size(); i++) {
			if (instantActions.get(i).finished) {
				instantActions.remove(i);
				i--; //To compensate for removing an element of the list.
			}
		}

	}

	public void doAction(Action action, float dt) {
		if (action.firstRun) {
			action.prepare();
			action.firstRun = false;
		}
		action.run(dt);
	}

	public timerAction waitFor(float time) {
	    return new timerAction(0, time, 0) {

		@Override
		public void doPreAction(float dt) {
		}

		@Override
		public void doPostAction(float dt) {
		}

		@Override
		public void prepare() {
		}

		@Override
		public void doMainAction(float dt) {
		    
		}

		@Override
		public void cleanUp() {
		    // TODO Auto-generated method stub
		    
		}
		
	    };
	}
}