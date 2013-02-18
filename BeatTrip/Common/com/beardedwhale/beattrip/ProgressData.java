package com.beardedwhale.beattrip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ProgressData {

    public static int locked = -1;
    public static int unlocked = 0;

    public static String savefile = "savegame";

    public static void storeProgress(String levelname, int result) {

	Preferences save = Gdx.app.getPreferences(savefile);

	int oldresult = save.getInteger(levelname+"best", -1);

	if (result < oldresult || oldresult == -1) {
	    save.putInteger(levelname +"best", result);
	}

	save.flush();
    }

    public static int getBest(String levelname) {
	Preferences save = Gdx.app.getPreferences(savefile);
	return (int) save.getInteger(levelname+"best", -1);
    }

    public static int getGoldLimit(String levelname) {
	// TODO Auto-generated method stub
	return 0;
    }


}