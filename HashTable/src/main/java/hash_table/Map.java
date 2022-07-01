package hash_table;

interface Map<K, V> extends Iterable<Map.Entry<K, V>> {
    V put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    interface Entry<K1, V1> {
        K1 getKey();

        V1 getValue();
    }

    Entry<K, V> putEntry(K key, V value);

    Entry<K, V> getEntry(K key);

    Entry<K, V> removeEntry(K key);

    void clear();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}