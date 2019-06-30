package mc.core.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mc.core.ComponentCollection;
import mc.core.ComponentOrganizer;

/**
 * Factory
 */
public class Factory implements CoreFactory {

    private static CoreFactory instance = null;

    public static CoreFactory Instance() {
        if (Factory.instance == null) {
            Factory.instance = new Factory();
        }
        return Factory.instance;
    }

    protected void overrideInstance(CoreFactory factory) {
        Factory.instance = factory;
    }

    @Override
    public ComponentOrganizer createComponentOrganizer() {
        return new ComponentCollection();
    }

    @Override
    public <T> List<T> createList() {
        return new ArrayList<>();
    }

    @Override
    public <K, V> Map<K, V> createMap() {
        return new HashMap<>();
    }

    @Override
    public <T> Set<T> createSet() {
        return new HashSet<>();
    }

}