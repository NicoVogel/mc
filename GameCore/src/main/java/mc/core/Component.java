package mc.core;

import mc.core.event.Disposable;

/**
 * This interface allows for maximum flexibility while creating all components
 */
public interface Component extends Disposable, ComponentCollection, ElementStatus {

    /**
     * provide the parent game object
     */
    public GameObject getGameObject();

    /**
     * provide the parent component, is only set if this component is a children of
     * another component
     */
    public Component getParent();

    /**
     * get a tag which can be set by a programmer. The advantage is that components
     * can be fund by the tag. multiple components can have the same tag
     */
    public String getTag();

    /**
     * tag a component to identify it later on
     */
    public void setTag(String tag);
}