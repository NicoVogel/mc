package mc.core.settings;

import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Settings {

	private static Settings singelton = new Settings();

	public static Settings Instance() {
		return singelton;
	}

	@Getter
	@Setter
	private boolean integrityCheck;
	@Getter
	private Map<String, String> generalInfo = new HashMap<>();

}
