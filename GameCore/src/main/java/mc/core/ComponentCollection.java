package mc.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import mc.core.event.Event;
import mc.core.event.EventProvider;
import one.util.streamex.StreamEx;

public class ComponentCollection implements ComponentOrganizer {

    private Map<WrapKey<?>, Set<Component>> elements;
    private Event<Component> onAdd;
    private Event<Component> onRemove;

    public ComponentCollection() {
        this.elements = new HashMap<>();
        this.onAdd = new Event<>();
        this.onRemove = new Event<>();
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
        this.elements.computeIfAbsent(new WrapKey<>(component.getClass(), component.getTag()), key -> new HashSet<>())
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
        return getComponent(new WrapKey<>(type));
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
        return getComponent(new WrapKey<>(tag));
    }

    @Override
    public <T extends Component> T getComponentOfParent(String tag) {
        return getComponentsOfParent(new WrapKey<T>(tag)).findFirst().orElse(null);
    }

    @Override
    public <T extends Component> T getComponentOfChildren(String tag) {
        return getComponentsOfChildren(new WrapKey<T>(tag)).findFirst().orElse(null);
    }

    @Override
    public <T extends Component> StreamEx<T> getComponents(String tag) {
        return getComponents(new WrapKey<>(tag));
    }

    @Override
    public <T extends Component> StreamEx<T> getComponentsOfParent(String tag) {
        return getComponentsOfParent(new WrapKey<>(tag));
    }

    @Override
    public <T extends Component> StreamEx<T> getComponentsOfChildren(String tag) {
        return getComponentsOfChildren(new WrapKey<>(tag));
    }

    /**
     * filter by the key, flatten the stream and cast to T
     */
    @SuppressWarnings("unchecked")
    private <T extends Component> StreamEx<T> getComponents(WrapKey<T> key) {
        return StreamEx.ofValues(this.elements, wrapKey -> wrapKey.equals(key))
                .flatMap(list -> StreamEx.of(list).map(value -> (T) value));
    }

    /**
     * take first matching element
     */
    private <T extends Component> T getComponent(WrapKey<T> key) {
        return getComponents(key).findFirst().orElse(null);
    }

    /**
     * extend the generic method 'getComponents' with all children components which
     * match. Depth first search
     */
    private <T extends Component> StreamEx<T> getComponentsOfChildren(WrapKey<T> key) {
        return getComponents(key)
                .append(StreamEx.ofValues(this.elements).flatMap(list -> StreamEx.of(list)).flatMap(value -> {
                    if (key.isClass())
                        return value.getParent().<T>getComponentsOfChildren(key.clazz);
                    else
                        return value.getParent().<T>getComponentsOfChildren(key.tag);
                }));
    }

    /**
     * extend the generic method 'getComponents' with all components of all parents
     */
    private <T extends Component> StreamEx<T> getComponentsOfParent(WrapKey<T> key) {
        return getComponents(key)
                .append(StreamEx.ofValues(this.elements).flatMap(list -> StreamEx.of(list)).flatMap(value -> {
                    if (value.getParent() != null) {
                        if (key.isClass())
                            return value.getParent().<T>getComponentsOfParent(key.clazz);
                        else
                            return value.getParent().<T>getComponentsOfParent(key.tag);
                    }
                    if (value.getGameObject() != null) {
                        if (key.isClass())
                            return value.getGameObject().<T>getComponentsOfParent(key.clazz);
                        else
                            return value.getGameObject().<T>getComponentsOfParent(key.tag);
                    }
                    return StreamEx.<T>empty();
                }));
    }

    /**
     * this key is needed to enable a double key access for the elements map. only
     * one of the properties need to match, this is achieved by the equals method.
     */
    private class WrapKey<T> {
        public Class<T> clazz;
        public String tag;

        public WrapKey(Class<T> clazz) {
            this(clazz, "");
        }

        public WrapKey(String tag) {
            this(null, tag);
        }

        private WrapKey(Class<T> clazz, String tag) {
            this.tag = tag;
            this.clazz = clazz;
        }

        public boolean isClass() {
            return this.clazz != null;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof WrapKey<?> == false)
                return false;
            WrapKey<?> obj = (WrapKey<?>) o;
            return this.clazz.equals(obj.clazz) || this.tag.equals(obj.tag);
        }
    }
}