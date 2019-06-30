package mc.core.factory;

import java.util.List;
import java.util.Map;
import java.util.Set;

import mc.core.ComponentOrganizer;

/**
 * CoreFactory
 */
public interface CoreFactory {

    public ComponentOrganizer createComponentOrganizer();

    public <T> List<T> createList();

    public <K, V> Map<K, V> createMap();

    public <T> Set<T> createSet();

}