package com.jodhpur.banna.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jodhpur.banna.Banna;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width =Banna.WIDTH;
		config.height = Banna.HEIGHT;
		config.title = Banna.TITLE;
		new LwjglApplication(new Banna(Banna.WIDTH,Banna.HEIGHT), config);
	}
}
