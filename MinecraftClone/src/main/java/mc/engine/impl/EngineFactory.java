package mc.engine.impl;

import mc.engine.settings.Settings;
import mc.engine.settings.SettingsManager;

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
