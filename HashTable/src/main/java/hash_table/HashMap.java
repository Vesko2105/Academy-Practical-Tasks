package hash_table;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    int size = 0;
    Node[] bins = new Node[INITIAL_CAPACITY];

    private static class Node<K1, V1>
            implements Map.Entry<K1, V1>
    {
        final int hash;
        final K1 key;
        V1 value;
        Node<K1, V1> next = null;

        public Node(K1 key, V1 value) {
            hash = key.hashCode();
            this.key = key;
            this.value = value;
        }

        public Node(int hash, K1 key, V1 value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        @Override
        public K1 getKey() {
            return key;
        }

        @Override
        public V1 getValue() {
            return value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(hash, key, value, next);
        }

        @Override
        public String toString() {
            return "[ " + key + ", " + value + "]";
        }
    }

    private Node<K, V> findByKey(K key, int hash, Node<K, V> bin) {
        while (bin != null) {
            if (bin.hash == hash && key.equals(bin.key)) {
                return bin;
            }
            bin = bin.next;
        }
        return null;
    }

    private void insertNode(Node<K, V> node, int bin) {
        incrementSize();
        node.next = bins[bin];
        bins[bin] = node;
    }

    private void incrementSize() {
        size++;
        if(size > bins.length * LOAD_FACTOR) {
            Node[] newBins = new Node[2 * bins.length];
            for (var entry : this) {
                Node<K, V> node = (Node) entry;
                int newBinNumber = Math.abs(node.hash) % newBins.length;
                node.next = newBins[newBinNumber];
                newBins[newBinNumber] = node;
            }
            bins = newBins;
        }
    }

    private int binNumber(int hash) {
        return Math.abs(hash) % bins.length;
    }

    public V put(K key, V value) {
        int hash = key.hashCode();
        int bin = binNumber(hash);
        Node<K, V> node = findByKey(key, hash, bins[bin]);
        if (node != null) {
            V old = node.value;
            node.value = value;
            return old;
        }
        else {
            insertNode(new Node<>(hash, key, value), bin);
            return null;
        }
    }

    @Override
    public V get(K key) {
        int hash = key.hashCode();
        int bin = binNumber(hash);
        Node<K, V> node = findByKey(key, hash, bins[bin]);

        if(node != null)
            return node.value;
        return null;
    }

    @Override
    public V remove(K key) {
        int position = binNumber(key.hashCode());
        Node<K, V> bin = findByKey(key, key.hashCode(), bins[position]);
        if(bin != null){
            size--;
            bins[position] = null;
            return bin.value;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        Node<K, V> found =  Arrays.stream(bins).filter(Objects::nonNull).filter(node -> node.getKey().equals(key)).findAny().orElse(null);
        return found != null;
    }

    @Override
    public Entry<K, V> getEntry(K key) {
        int hash = key.hashCode();
        int bin = binNumber(hash);
        return findByKey(key, hash, bins[bin]);
    }

    @Override
    public Entry<K, V> putEntry(K key, V value) {
        Entry<K, V> original = getEntry(key);
        Entry<K, V> toReturn = null;
        if(original != null){
            toReturn = new Node<>(original.getKey(), original.getValue());
        }
        put(key, value);
        return toReturn;
    }

    @Override
    public Entry<K, V> removeEntry(K key) {
        Entry<K, V> toRemove = getEntry(key);
        V removed = remove(key);
        return removed == null ? null : toRemove;
    }

    @Override
    public void clear() {
        Arrays.fill(bins, null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return this.new EntryIterator();
    }

    class EntryIterator implements Iterator<Entry<K, V>> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            while(cursor < bins.length &&bins[cursor] == null)
                cursor++;
            return cursor < bins.length;
        }

        @Override
        public Entry<K, V> next() throws NoSuchElementException{
            if(!hasNext())
                throw new NoSuchElementException();
            return bins[cursor++];
        }
    }
}