package com.podzirey.datastructures.map;

import java.util.List;

public interface Map {
    Object put(Object key, Object value);

    Object get(Object key);

    Object remove(Object key);

    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    Object putIfAbsent(Object key, Object value);

    void putAll(HashMap map);

    List keys();

    List values();
}
