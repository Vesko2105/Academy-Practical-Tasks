package hash_table;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    class TestEntry<K, V> implements Map.Entry<K, V>{
        K key;
        V value;

        public TestEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            Map.Entry<K, V> otherEntry = (Map.Entry<K, V>) other;
            if(otherEntry != null)
                return Objects.equals(getKey(), otherEntry.getKey()) && Objects.equals(getValue(), otherEntry.getValue());
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(getKey(), getValue());
        }
    }
    @Nested
    @DisplayName("Putting elements in map")
    class puttingElements{
        @Test
        @DisplayName("Putting, returning put element's value")
        void puttingElementsValues(){
            //Preparation
            Map<String, Integer> map = new HashMap<>();
            int expectedMapSize = 3;
            Integer expectedValue1 = 1;
            Integer redundantValue2 = 2;
            Integer expectedValue2 = 22;
            Integer expectedValue3 = 3;


            //Action
            Integer valueReplaced1 = map.put("#1", 1);
            Integer valueReplaced2 = map.put("#2", 2);
            Integer valueReplaced22 = map.put("#2", 22);
            Integer valueReplaced3 = map.put("#3", 3);
            Integer actualMapSize = map.size();
            Integer value1 = map.get("#1");
            Integer value2 = map.get("#2");
            Integer value3 = map.get("#3");

            //Assertion
            assertNull(valueReplaced1);
            assertNull(valueReplaced2);
            assertEquals(redundantValue2, valueReplaced22);
            assertNull(valueReplaced3);
            assertEquals(expectedMapSize, actualMapSize);
            assertEquals(expectedValue1, value1);
            assertEquals(expectedValue2, value2);
            assertEquals(expectedValue3, value3);
        }

        @Test
        @DisplayName("Putting, returning put element's entry")
        void puttingElementsEntries(){
            //Preparation
            Map<String, Integer> map = new HashMap<>();
            int expectedMapSize = 3;
            TestEntry<String, Integer> expectedEntry1 = new TestEntry<>("#1", 1);
            TestEntry<String, Integer> redundantEntry2 = new TestEntry<>("#2", 2);
            TestEntry<String, Integer> expectedEntry2 = new TestEntry<>("#2", 22);
            TestEntry<String, Integer> expectedEntry3 = new TestEntry<>("#3", 3);


            //Action
            Map.Entry<String, Integer> entryReplaced1 = map.putEntry("#1", 1);
            Map.Entry<String, Integer> entryReplaced2 = map.putEntry("#2", 2);
            Map.Entry<String, Integer> entryReplaced22 = map.putEntry("#2", 22);
            Map.Entry<String, Integer> entryReplaced3 = map.putEntry("#3", 3);
            Integer actualMapSize = map.size();
            Map.Entry<String, Integer> value1 = map.getEntry("#1");
            Map.Entry<String, Integer> value2 = map.getEntry("#2");
            Map.Entry<String, Integer> value3 = map.getEntry("#3");

            //Assertion
            assertNull(entryReplaced1);
            assertNull(entryReplaced2);
            assertEquals(redundantEntry2, entryReplaced22);
            assertNull(entryReplaced3);
            assertEquals(expectedMapSize, actualMapSize);
            assertEquals(expectedEntry1, value1);
            assertEquals(expectedEntry2, value2);
            assertEquals(expectedEntry3, value3);
        }

    }

    @Nested
    @DisplayName("Removing elements from map")
    class RemovingElements{
        @Test
        @DisplayName("Removing, returning removed element's value")
        void removingElementsValues(){
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("#1", 34);
            map.put("#2", 432);
            map.put("#3", 51);
            map.put("#4", 92);

            int sizeBefore = map.size();
            Integer map2ValBefore = map.get("#2");
            Integer map23ValBefore = map.get("#3");

            Integer removed2 = map.remove("#2");
            Integer removed3 = map.remove("#3");

            int sizeAfter = map.size();
            Integer map2ValAfter = map.get("#2");
            Integer map23ValAfter = map.get("#3");

            Integer removedInvalid = map.remove("!");
            int sizeAfterInvalidRemove = map.size();

            assertEquals(4, sizeBefore);
            assertEquals(432, map2ValBefore);
            assertEquals(51, map23ValBefore);

            assertEquals(2, sizeAfter);
            assertEquals(removed2, map2ValBefore);
            assertEquals(removed3, map23ValBefore);
            assertNull(map2ValAfter);
            assertNull(map23ValAfter);

            assertNull(removedInvalid);
            assertEquals(sizeAfter, sizeAfterInvalidRemove);
        }

        //@Disabled
        @Test
        @DisplayName("Removing, returning removed element's entry")
        void removingElementsEntries(){
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("#1", 34);
            map.put("#2", 432);
            map.put("#3", 51);
            map.put("#4", 92);

            int sizeBefore = map.size();
            Map.Entry<String, Integer> map2EntryBefore = map.getEntry("#2");
            Map.Entry<String, Integer> map3EntryBefore = map.getEntry("#3");

            Map.Entry<String, Integer> removed2 = map.removeEntry("#2");
            Map.Entry<String, Integer> removed3 = map.removeEntry("#3");

            int sizeAfter = map.size();
            Map.Entry<String, Integer> map2EntryAfter = map.getEntry("#2");
            Map.Entry<String, Integer> map3EntryAfter = map.getEntry("#3");

            Map.Entry<String, Integer> removedInvalid = map.removeEntry("!");
            int sizeAfterInvalidRemove = map.size();

            assertEquals(4, sizeBefore);
            assertEquals("#2", map2EntryBefore.getKey());
            assertEquals(432, map2EntryBefore.getValue());
            assertEquals("#3", map3EntryBefore.getKey());
            assertEquals(51, map3EntryBefore.getValue());
            assertEquals(2, sizeAfter);

            assertEquals(removed2.getKey(), map2EntryBefore.getKey());
            assertEquals(removed2.getValue(), map2EntryBefore.getValue());
            assertEquals(removed3.getKey(), map3EntryBefore.getKey());
            assertEquals(removed3.getValue(), map3EntryBefore.getValue());
            assertNull(map2EntryAfter);
            assertNull(map3EntryAfter);

            assertNull(removedInvalid);
            assertEquals(sizeAfterInvalidRemove, sizeAfter);
        }
    }

    @Nested
    @DisplayName("Iterating over map")
    class MapIterating {
        @Test
        @DisplayName("Looping over map with 10 elements")
        void loopingOver10ElementMap() {
            HashMap<String, Integer> map = new HashMap<>();
            ArrayList<String> result = new ArrayList<>();
            int expectedSize = 10;
            for (int i = 0; i < 10; i++) {
                map.put("#" + i, i);
            }

            for (Map.Entry<String, Integer> entry : map) {
                result.add(entry.toString());
            }
            int actualSize = result.size();

            assertEquals(expectedSize, actualSize);
        }

        @Test
        @DisplayName("Going outside the bounds of the map")
        void invalidIterating() {
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                map.put("#" + i, i);
            }
            var iterator = map.iterator();
            var expectedException = NoSuchElementException.class;

            for (int i = 0; i < 10; i++) {
                iterator.next();
            }


            assertThrows(expectedException, () -> iterator.next());
        }
    }

    @Test
    @DisplayName("ContainsKey function")
    void containsKeyTest(){
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("#" + i, i);
        }

        boolean actualFound1 = map.containsKey("#1");
        boolean actualFound2 = map.containsKey("#7");
        boolean actualFound3 = map.containsKey("!");
        boolean actualFound4 = map.containsKey("?");

        assertTrue(actualFound1);
        assertTrue(actualFound2);
        assertFalse(actualFound3);
        assertFalse(actualFound4);
    }
}
