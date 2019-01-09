package dhbw.engine.impl.frame;

public class FrameCounter {

	private double fpsCap;
	private double time;
	private double processedTime;

	public FrameCounter(int fpsCap) {
		this.fpsCap = 1.0 / fpsCap;
	}

	/**
	 * check if the FPS cap is reached
	 * 
	 * @return true if there is another update to go and false if the FPS cap is
	 *         reached
	 */
	public boolean isUpdating() {
		double nextTime = getTimeInSeconds();
		// passed since last check
		double passedTime = nextTime - this.time;
		this.processedTime += passedTime;
		this.time = nextTime;

		if (this.processedTime > this.fpsCap) {
			this.processedTime -= this.fpsCap;
			return true;
		}
		return false;
	}

	private double getTimeInSeconds() {
		return System.nanoTime() / 1000000000.0;
	}

}
