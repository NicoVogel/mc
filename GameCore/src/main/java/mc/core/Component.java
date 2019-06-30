package mc.core;

import mc.core.event.Disposable;

/**
 * Component
 */
public interface Component extends Disposable, ComponentOrganizer, ElementStatus {

    public GameObject getGameObject();

    public Component getParent();

    public String getTag();

    public void setTag(String tag);
}