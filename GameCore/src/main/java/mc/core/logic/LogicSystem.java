package mc.core.logic;

import java.util.Map;

import mc.core.GameObject;

public class LogicSystem {

	// general map of all logical compositions which are known to the current game.
	// the interger is the ID of the specific gameobject logic.
	// on load of a world, the ID sould be changed based on the worlds internal references and as general reference a hash sould be used of the gameobject
	private Map<Integer, GameObject> logic;
	
	
}
