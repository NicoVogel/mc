package dhbw.engine.impl.display;

import lombok.Data;

@Data
public class DisplayInitialSettingsBE {

	private int width;
	private int height;
	private String title;
	private int monitorForFullscreen;
	private int shareResourcesWindow;

	/**
	 * 0 is the default and means it is in window mode
	 * 
	 * @param monitorForFullscreen
	 */
	public void setMonitorForFullscreen(int monitorForFullscreen) {
		this.monitorForFullscreen = monitorForFullscreen;
	}

	/**
	 * 0 is the default and means that it does not share its resources with another
	 * window
	 * 
	 * @param shareResourcesWindow
	 */
	public void setShareResourcesWindow(int shareResourcesWindow) {
		this.shareResourcesWindow = shareResourcesWindow;
	}
}
