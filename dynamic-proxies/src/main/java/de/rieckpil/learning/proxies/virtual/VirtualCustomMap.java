package de.rieckpil.learning.proxies.virtual;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class VirtualCustomMap<K, V> implements CustomMap<K, V> {

    private final Supplier<CustomMap<K, V>> mapSupplier;
    private CustomMap<K, V> realMap;
    public VirtualCustomMap(Supplier<CustomMap<K, V>> mapSupplier) {
        this.mapSupplier = mapSupplier;
    }
    private CustomMap<K, V> getRealMap() { // not thread-safe
        if (realMap == null) realMap = mapSupplier.get();
        return realMap;
    }
    @Override
    public int size() {
        return getRealMap().size();
    }
    @Override
    public V get(Object key) {
        return getRealMap().get(key);
    }
    @Override
    public V put(K key, V value) {
        return getRealMap().put(key, value);
    }
    @Override
    public V remove(Object key) {
        return getRealMap().remove(key);
    }
    @Override
    public void clear() {
        getRealMap().clear();
    }
    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        getRealMap().forEach(action);
    }
}
