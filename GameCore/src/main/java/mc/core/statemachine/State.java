package mc.core.statemachine;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import one.util.streamex.StreamEx;

/**
 * State
 */
@Getter
public class State<T> {

    private Set<T> properties;
    private int number;
    private String name;

    public State(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public Set<T> getPropertySet() {
        if (this.properties == null) {
            this.properties = new HashSet<>();
        }
        return this.properties;
    }

    public StreamEx<T> getProperties() {
        return StreamEx.of(getPropertySet());
    }

    public void addProperty(T property) {
        getPropertySet().add(property);
    }

    public boolean removeProperty(T property) {
        return getPropertySet().remove(property);
    }
}