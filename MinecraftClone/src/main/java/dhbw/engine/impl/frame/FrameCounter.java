package dhbw.engine.impl.frame;

import java.util.Timer;
import java.util.TimerTask;

import dhbw.engine.EventProvider;
import dhbw.engine.impl.display.DisplayManager;

public class FrameCounter {

	private long thisFrame;
	private long lastFrame;
	private int fpsCounter;
	private long totalFpsCounter;
	private int cycles;
	private Timer timer;
	private EventProvider<FpsListener, FpsEvent> fpsListener;
	private FrameCounter selfeReference;

	public FrameCounter(DisplayManager displayManager) {
		this.selfeReference = this;
		this.fpsListener = new EventProvider<>();
		this.timer = new Timer();
		initTimer(displayManager);
	}

	private void initTimer(DisplayManager displayManager) {
		this.timer.schedule(new TimerTask() {
			@Override
			public void run() {
				int fps = fpsCounter;
				fpsCounter = 0;
				totalFpsCounter += fps;
				cycles++;
				fpsListener.invoke(new FpsEvent(selfeReference, fps, getAverageFPS()));
				if (displayManager.isWindowClosed()) {
					timer.cancel();
				}
			}
		}, 0, 1000);// invoke every second
	}

	public void update() {
		this.lastFrame = this.thisFrame;
		this.thisFrame = System.currentTimeMillis();
		this.fpsCounter++;
	}

	public double getAverageFPS() {
		return this.totalFpsCounter / (double) this.cycles;
	}

	public double timeSinceLastFrameInSeconds() {
		return (this.thisFrame - lastFrame) / 1000.0;
	}

	public void add(FpsListener listener) {
		this.fpsListener.add(listener);
	}

	public void remove(FpsListener listener) {
		this.fpsListener.remove(listener);
	}
}
