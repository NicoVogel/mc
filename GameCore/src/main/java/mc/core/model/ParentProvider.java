package mc.core.model;

import mc.core.Component;
import mc.core.GameObject;

/**
 * ParentComponent
 */
public interface ParentProvider {

    /**
     * provide the parent game object
     */
    public GameObject getGameObject();

    /**
     * provide the parent component, is only set if this component is a children of
     * another component
     */
    public Component getParent();
}