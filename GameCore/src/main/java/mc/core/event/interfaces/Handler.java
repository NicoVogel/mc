package mc.core.event.interfaces;

import mc.core.event.handler.EventRegister;

public interface Handler{

    /**
     * Registers a WorldObject for a specific event
     * @param o The WorldObject which shall be registered
     * @param s The Action for which the WorldObject shall be registered. Must be of Enum type Action.
     */
    public void registerObject(Object o, EventRegister s);

    /**
     * Removes a WorldObject from a specific event
     * @param o The WorldObject which shall be registered
     * @param s The Action for which the WorldObject shall be registered. Must be of Enum type Action.
     */
    public void removeObjectFromAction(Object o, EventRegister s);

    /**
     * Removes a WorldObject from all events
     * @param o The WorldObject which shall be registered
     * @param s The Action for which the WorldObject shall be registered. Must be of Enum type Action.
     */
    public void removeObjectFromAllActions(Object o);

}