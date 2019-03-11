package mc.core.world;

import lombok.Getter;

@Getter
public class PlayerView {

	private Player player;
	private Chunk[][] view;
	private int viewDistence;

	public Chunk[][] getView() {
		if (this.view == null) {
			int area = getAreaSize();
			this.view = new Chunk[area][area];
		}
		return this.view;
	}

	/**
	 * ignores calls with values smaller than 0
	 * 
	 * @param viewDistance
	 */
	public void setViewDistence(int viewDistance) {
		if (viewDistance < 0) {
			return;
		}
		this.view = null;
		this.viewDistence = viewDistance;
	}

	private int getAreaSize() {
		return this.viewDistence * 2 + 1;
	}

}
