package mc.core.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import mc.core.Component;
import mc.core.event.impl.Event;
import mc.core.event.interfaces.EventProvider;
import one.util.streamex.StreamEx;

public class ComponentCollectionImpl implements ComponentCollection {

    private Map<WrapKey<?>, Set<Component>> elements;
    private Event<Component> onAdd;
    private Event<Component> onRemove;
    private ParentProvider parent;

    public ComponentCollectionImpl(ParentProvider parent) {
        this.elements = new HashMap<>();
        this.onAdd = new Event<>();
        this.onRemove = new Event<>();
        this.parent = parent;
    }

    @Override
    public void dispose() {
        this.onAdd.dispose();
        this.onRemove.dispose();
        StreamEx.ofValues(this.elements).flatMap(list -> StreamEx.of(list)).forEach(value -> value.dispose());
    }

    @Override
    public EventProvider<Component> OnAdd() {
        return this.onAdd.getProvider();
    }

    @Override
    public EventProvider<Component> OnRemove() {
        return this.onRemove.getProvider();
    }

    @Override
    public void addComponent(Component component) {
        this.elements
                .computeIfAbsent(new WrapKey<>(component.getClass(), component.getTagObject()), key -> new HashSet<>())
                .add(component);
    }

    @Override
    public boolean removeComponent(Component component) {
        Set<Component> values = this.elements.get(new WrapKey<>(component.getClass()));
        if (values == null) {
            return false;
        }
        return values.remove(component);
    }

    @Override
    public StreamEx<Component> getComponents() {
        return StreamEx.ofValues(this.elements).flatMap(list -> StreamEx.of(list));
    }

    @Override
    public <T extends Component> T getComponent(Class<T> type) {
        return getComponents(new WrapKey<>(type)).findFirst().orElse(null);
    }

    @Override
    public <T extends Component> T getComponentOfParent(Class<T> type) {
        return getComponentsOfParent(new WrapKey<>(type)).findFirst().orElse(null);
    }

    @Override
    public <T extends Component> T getComponentOfChildren(Class<T> type) {
        return getComponentsOfChildren(new WrapKey<>(type)).findFirst().orElse(null);
    }

    @Override
    public <T extends Component> StreamEx<T> getComponents(Class<T> type) {
        return getComponents(new WrapKey<>(type));
    }

    @Override
    public <T extends Component> StreamEx<T> getComponentsOfParent(Class<T> type) {
        return getComponentsOfParent(new WrapKey<>(type));
    }

    @Override
    public <T extends Component> StreamEx<T> getComponentsOfChildren(Class<T> type) {
        return getComponentsOfChildren(new WrapKey<>(type));
    }

    @Override
    public <T extends Component> T getComponent(String tag) {
        return getComponents(new WrapKey<T>(new Tag(tag))).findFirst().orElse(null);
    }

    @Override
    public <T extends Component> T getComponentOfParent(String tag) {
        return getComponentsOfParent(new WrapKey<T>(new Tag(tag))).findFirst().orElse(null);
    }

    @Override
    public <T extends Component> T getComponentOfChildren(String tag) {
        return getComponentsOfChildren(new WrapKey<T>(new Tag(tag))).findFirst().orElse(null);
    }

    @Override
    public <T extends Component> StreamEx<T> getComponents(String tag) {
        return getComponents(new WrapKey<>(new Tag(tag)));
    }

    @Override
    public <T extends Component> StreamEx<T> getComponentsOfParent(String tag) {
        return getComponentsOfParent(new WrapKey<>(new Tag(tag)));
    }

    @Override
    public <T extends Component> StreamEx<T> getComponentsOfChildren(String tag) {
        return getComponentsOfChildren(new WrapKey<>(new Tag(tag)));
    }

    @Override
    public <T extends Component> T getComponent(Tag tag) {
        return getComponents(new WrapKey<T>(tag)).findFirst().orElse(null);
    }

    @Override
    public <T extends Component> T getComponentOfParent(Tag tag) {
        return getComponentsOfParent(new WrapKey<T>(tag)).findFirst().orElse(null);
    }

    @Override
    public <T extends Component> T getComponentOfChildren(Tag tag) {
        return getComponentsOfChildren(new WrapKey<T>(tag)).findFirst().orElse(null);
    }

    @Override
    public <T extends Component> StreamEx<T> getComponents(Tag tag) {
        return getComponents(new WrapKey<>(tag));
    }

    @Override
    public <T extends Component> StreamEx<T> getComponentsOfParent(Tag tag) {
        return getComponentsOfParent(new WrapKey<>(tag));
    }

    @Override
    public <T extends Component> StreamEx<T> getComponentsOfChildren(Tag tag) {
        return getComponentsOfChildren(new WrapKey<>(tag));
    }

    /**
     * filter by the key, flatten the stream and cast to T
     */
    @SuppressWarnings("unchecked")
    public <T extends Component> StreamEx<T> getComponents(WrapKey<T> key) {
        return StreamEx.ofValues(this.elements, wrapKey -> wrapKey.equals(key))
                .flatMap(list -> StreamEx.of(list).map(value -> (T) value));
    }

    /**
     * extend the generic method 'getComponents' with all children components which
     * match. Depth first search
     */
    public <T extends Component> StreamEx<T> getComponentsOfChildren(WrapKey<T> key) {
        return getComponents(key).append(StreamEx.ofValues(this.elements).flatMap(list -> StreamEx.of(list))
                .flatMap(value -> value.<T>getComponentsOfChildren(key)));
    }

    /**
     * extend the generic method 'getComponents' with all components of all parents
     */
    public <T extends Component> StreamEx<T> getComponentsOfParent(WrapKey<T> key) {
        return getComponents(key).append(StreamEx.of(this.parent == null ? StreamEx.<T>empty()
                : this.parent.getParent() != null ? this.parent.getParent().<T>getComponentsOfParent(key)
                        : this.parent.getGameObject() != null
                                ? this.parent.getGameObject().<T>getComponentsOfParent(key)
                                : StreamEx.<T>empty()));

    }

}