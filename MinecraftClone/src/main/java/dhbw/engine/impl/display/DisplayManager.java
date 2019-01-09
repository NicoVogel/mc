package dhbw.engine.impl.display;

import java.nio.DoubleBuffer;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import dhbw.engine.impl.EngineFactory;
import dhbw.engine.impl.display.render.Model;
import dhbw.engine.impl.display.render.Renderer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DisplayManager {

	private long windowID;
	private Renderer renderer;
	@Getter
	private List<Model> models;

	public DisplayManager() {
		this.renderer = new Renderer();
	}

	public void create() {
		if (!GLFW.glfwInit()) {
			log.error("the display is already open and can not be opened twice!");
			return;
		}
		DisplayInitialSettingsBE settings = EngineFactory.getSettings().getDisplaySettings();
		this.windowID = GLFW.glfwCreateWindow(settings.getWidth(), settings.getHeight(), settings.getTitle(),
				settings.getMonitorForFullscreen(), settings.getShareResourcesWindow());

		if (this.windowID == 0) {
			log.error("the display could not be created");
			System.exit(-1);
		}

		GLFW.glfwMakeContextCurrent(this.windowID);
		GL.createCapabilities();

		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(this.windowID, getMidScreenCoordinate(videoMode.width(), settings.getWidth()),
				getMidScreenCoordinate(videoMode.height(), settings.getHeight()));

		GLFW.glfwShowWindow(this.windowID);
	}

	public boolean isWindowClosed() {
		if (this.windowID == 0) {
			log.warn("The window was not opend in the first place");
			return true;
		}
		return GLFW.glfwWindowShouldClose(this.windowID);
	}

	public void update() {
		clearScreenFromPreviousFrame();

		GL11.glClearColor(1, 0, 0, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		render();

		renderEverything();
	}

	/**
	 * returns true if that key is clicked
	 * 
	 * @param keyCode
	 * @return
	 */
	public boolean isKeyDown(int keyCode) {
		return GLFW.glfwGetKey(this.windowID, keyCode) == 1;
	}

	public double getMouseX() {
		DoubleBuffer buffer = BufferUtils.createDoubleBuffer(1);
		GLFW.glfwGetCursorPos(this.windowID, buffer, null);
		return buffer.get(0);
	}

	public double getMouseY() {
		DoubleBuffer buffer = BufferUtils.createDoubleBuffer(1);
		GLFW.glfwGetCursorPos(this.windowID, null, buffer);
		return buffer.get(0);
	}

	/**
	 * returns true if that mouse button is clicked
	 * 
	 * @param mouseButton
	 * @return
	 */
	public boolean isMouseDown(int mouseButton) {
		return GLFW.glfwGetMouseButton(this.windowID, mouseButton) == 1;
	}

	private void render() {
		this.models.stream().forEach(x -> this.renderer.renderModel(x));
	}

	private void clearScreenFromPreviousFrame() {
		GLFW.glfwPollEvents();
	}

	/**
	 * call it after clear Screen and the changes which happend
	 */
	private void renderEverything() {
		GLFW.glfwSwapBuffers(this.windowID);
	}

	private int getMidScreenCoordinate(int full, int part) {
		return (full - part) / 2;
	}
}
