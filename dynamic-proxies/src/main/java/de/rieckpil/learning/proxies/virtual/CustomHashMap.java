package de.rieckpil.learning.proxies.virtual;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class CustomHashMap<K, V> implements CustomMap<K, V> {
    private final Map<K, V> map = new HashMap<>();

    public CustomHashMap() {
        System.out.println("CustomHashMap constructed");
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(K key, V value) {
        return map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        map.forEach(action);
    }
}
