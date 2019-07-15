package mc.core.model;

import mc.core.Component;
import mc.core.event.interfaces.Disposable;
import mc.core.event.interfaces.EventProvider;
import one.util.streamex.StreamEx;

/**
 * ComponentOrganizer
 */
public interface ComponentCollection extends Disposable {

    /**
     * get all components
     * 
     * @return stream of {@link Component}
     */
    public StreamEx<Component> getComponents();

    /**
     * get the first component of a specific type. It is not guaranteed that the
     * returned component is the last added component.
     * 
     * @param <T>  needs to extend from {@link Component}
     * @param type filter by this class
     * @return
     */
    public <T extends Component> T getComponent(Class<T> type);

    /**
     * get the first component of a specific type. also search its parents. does
     * only search up
     * 
     * @param <T>  needs to extend from {@link Component}
     * @param type filter by this class
     * @return
     */
    public <T extends Component> T getComponentOfParent(Class<T> type);

    /**
     * get the first component of a specific type. also search's through children
     * using depth first search
     * 
     * @param <T>  needs to extend from {@link Component}
     * @param type filter by this class
     * @return
     */
    public <T extends Component> T getComponentOfChildren(Class<T> type);

    /**
     * get all components of a specific type
     * 
     * @param <T>  needs to extend from {@link Component}
     * @param type filter by this class
     * @return stream of T
     */
    public <T extends Component> StreamEx<T> getComponents(Class<T> type);

    /**
     * get all components of a specific type. also search its parents. does only
     * search up
     * 
     * @param <T>  needs to extend from {@link Component}
     * @param type filter by this class
     * @return
     */
    public <T extends Component> StreamEx<T> getComponentsOfParent(Class<T> type);

    /**
     * get all components of a specific type. also search's through children using
     * depth first search
     * 
     * @param <T>  needs to extend from {@link Component}
     * @param type filter by this class
     * @return
     */
    public <T extends Component> StreamEx<T> getComponentsOfChildren(Class<T> type);

    /**
     * get the first component of a specific type. It is not guaranteed that the
     * returned component is the last added component.
     * 
     * @param <T> needs to extend from {@link Component}
     * @param tag filter by this tag
     * @return
     */
    public <T extends Component> T getComponent(String tag);

    /**
     * get the first component of a specific type. also search its parents. does
     * only search up
     * 
     * @param <T> needs to extend from {@link Component}
     * @param tag filter by this tag
     * @return
     */
    public <T extends Component> T getComponentOfParent(String tag);

    /**
     * get the first component of a specific type. also search's through children
     * using depth first search
     * 
     * @param <T> needs to extend from {@link Component}
     * @param tag filter by this tag
     * @return
     */
    public <T extends Component> T getComponentOfChildren(String tag);

    /**
     * get all components of a specific type
     * 
     * @param <T> needs to extend from {@link Component}
     * @param tag filter by this tag
     * @return stream of T
     */
    public <T extends Component> StreamEx<T> getComponents(String tag);

    /**
     * get all components of a specific type. also search its parents. does only
     * search up
     * 
     * @param <T> needs to extend from {@link Component}
     * @param tag filter by this tag
     * @return
     */
    public <T extends Component> StreamEx<T> getComponentsOfParent(String tag);

    /**
     * get all components of a specific type. also search's through children using
     * depth first search
     * 
     * @param <T> needs to extend from {@link Component}
     * @param tag filter by this tag
     * @return
     */
    public <T extends Component> StreamEx<T> getComponentsOfChildren(String tag);

    /**
     * get the first component of a specific type. It is not guaranteed that the
     * returned component is the last added component.
     * 
     * @param <T> needs to extend from {@link Component}
     * @param tag filter by this tag
     * @return
     */
    public <T extends Component> T getComponent(Tag tag);

    /**
     * get the first component of a specific type. also search its parents. does
     * only search up
     * 
     * @param <T> needs to extend from {@link Component}
     * @param tag filter by this tag
     * @return
     */
    public <T extends Component> T getComponentOfParent(Tag tag);

    /**
     * get the first component of a specific type. also search's through children
     * using depth first search
     * 
     * @param <T> needs to extend from {@link Component}
     * @param tag filter by this tag
     * @return
     */
    public <T extends Component> T getComponentOfChildren(Tag tag);

    /**
     * get all components of a specific type
     * 
     * @param <T> needs to extend from {@link Component}
     * @param tag filter by this tag
     * @return stream of T
     */
    public <T extends Component> StreamEx<T> getComponents(Tag tag);

    /**
     * get all components of a specific type. also search its parents. does only
     * search up
     * 
     * @param <T> needs to extend from {@link Component}
     * @param tag filter by this tag
     * @return
     */
    public <T extends Component> StreamEx<T> getComponentsOfParent(Tag tag);

    /**
     * get all components of a specific type. also search's through children using
     * depth first search
     * 
     * @param <T> needs to extend from {@link Component}
     * @param tag filter by this tag
     * @return
     */
    public <T extends Component> StreamEx<T> getComponentsOfChildren(Tag tag);

    /**
     * get all components of a specific type
     * 
     * @param <T>
     * @param key
     * @return
     */
    public <T extends Component> StreamEx<T> getComponents(WrapKey<T> key);

    /**
     * extends the generic method {@link getComponents} with all children components
     * which match. Depth first search
     */
    /**
     * 
     * @param <T>
     * @param key
     * @return
     */
    public <T extends Component> StreamEx<T> getComponentsOfChildren(WrapKey<T> key);

    /**
     * extend the generic method {@link getComponents} with all components of all
     * parents
     */
    /**
     * 
     * @param <T>
     * @param key
     * @return
     */
    public <T extends Component> StreamEx<T> getComponentsOfParent(WrapKey<T> key);

    /**
     * add component to the organizer
     * 
     * @param component will be added
     */
    public void addComponent(Component component);

    /**
     * remove a component and return true if it was part of the organizer
     * 
     * @param component gets removed if it exits
     * @return
     */
    public boolean removeComponent(Component component);

    /**
     * if a component is added, this event gets invoked
     * 
     * @return
     */
    public EventProvider<Component> OnAdd();

    /**
     * if a component is removed, this event gets invoked
     */
    public EventProvider<Component> OnRemove();

}