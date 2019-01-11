package mc.engine.impl.frame;

import java.util.EventObject;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FpsEvent extends EventObject {

	private static final long serialVersionUID = 358517685565045822L;

	private int fpsCount;
	private double avgFps;

	public FpsEvent(Object source, int fpsCount, double avgFps) {
		super(source);
		this.fpsCount = fpsCount;
		this.avgFps = avgFps;
	}

}