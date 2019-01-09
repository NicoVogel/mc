package dhbw.engine.impl.display;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

import dhbw.engine.impl.EngineFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DisplayManager {

	private long windowID;

	/**
	 * if its 0, then it is not initialized
	 * 
	 * @return
	 */
	public long getWindowID() {
		return this.windowID;
	}

	public void create() {
		if (!GLFW.glfwInit()) {
			log.error("the display is already open and can not be opened twice!");
			return;
		}
		DisplayInitialSettingsBE settings = EngineFactory.getSettings().getDisplaySettings();
		this.windowID = GLFW.glfwCreateWindow(settings.getHeight(), settings.getWidth(), settings.getTitle(),
				settings.getMonitorForFullscreen(), settings.getShareResourcesWindow());

		if (this.windowID == 0) {
			log.error("the display could not be created");
			System.exit(-1);
		}

		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(this.windowID, getMidScreenCoordinate(videoMode.width(), settings.getWidth()),
				getMidScreenCoordinate(videoMode.height(), settings.getHeight()));

		GLFW.glfwShowWindow(this.windowID);
	}

	private int getMidScreenCoordinate(int full, int part) {
		return full - part / 2;
	}
}
