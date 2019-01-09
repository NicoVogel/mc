package dhbw.engine.settings;

import dhbw.engine.impl.display.DisplayInitialSettingsBE;

public class SettingsManager implements Settings {

	@Override
	public DisplayInitialSettingsBE getDisplaySettings() {
		DisplayInitialSettingsBE display = new DisplayInitialSettingsBE();
		display.setHeight(600);
		display.setWidth(800);
		display.setTitle("Test");
		return display;
	}

}
