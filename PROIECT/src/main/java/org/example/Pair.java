package org.example;

// pentru ca Java nu are o clasa pair
public class Pair<K, V> {
    private K mKey;
    private V mValue;

    public Pair(K key, V value) {
        mKey = key;
        mValue = value;
    }

    public K getKey() {
        return mKey;
    }

    public V getValue() {
        return mValue;
    }
}
