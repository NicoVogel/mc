package dhbw.engine.impl;

import dhbw.engine.settings.Settings;
import dhbw.engine.settings.SettingsManager;

public class EngineFactory {

	private EngineFactory() {

	}

	protected static Settings settings;

	public static Settings getSettings() {
		if (EngineFactory.settings == null) {
			EngineFactory.settings = new SettingsManager();
		}
		return EngineFactory.settings;
	}
}
