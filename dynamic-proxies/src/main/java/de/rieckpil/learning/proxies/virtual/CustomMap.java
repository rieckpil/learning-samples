package de.rieckpil.learning.proxies.virtual;

import java.util.function.BiConsumer;

public interface CustomMap<K, V> {
    int size();
    V get(Object key);
    V put(K key, V value);
    V remove(Object key);
    void clear();
    void forEach(BiConsumer<? super K, ? super V> action);
}
