package mc.core.world;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlayerEvent {

	private double oldX;
	private double oldY;
	private double oldZ;

	private double newX;
	private double newY;
	private double newZ;

}
