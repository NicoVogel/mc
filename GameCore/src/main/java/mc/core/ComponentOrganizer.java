package mc.core;

import mc.core.event.Disposable;
import mc.core.event.EventProvider;
import one.util.streamex.StreamEx;

/**
 * ComponentOrganizer
 */
public interface ComponentOrganizer extends Disposable {

    /**
     * get all components
     * 
     * @return stream of {@link Component}
     */
    public StreamEx<Component> getComponents();

    /**
     * get the first component of a specific type
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
     * get the first component of a specific type
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