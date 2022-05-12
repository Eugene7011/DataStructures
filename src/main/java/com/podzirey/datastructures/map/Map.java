package com.podzirey.datastructures.map;

import java.util.List;
import java.util.Iterator;

public interface Map<K, V> extends Iterable<Map.Entry<K,V>> {
    V put(K key, V value);

    V get(K key);

    V remove(K key);

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    V putIfAbsent(K key, V value);

    void putAll(HashMap<K, V> map);

    List<K> keys();

    List<V> values();

    interface Entry<K, V>  {
        K getKey();

        V getValue();

        void setValue(V value);
    }
}
