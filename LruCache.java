import java.util.*;

public class LruCache {
    private int capacity;
    private LinkedHashMap<Integer, Integer> cache;

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        if (cache.size() == capacity && !cache.containsKey(key)) {
            Iterator<Map.Entry<Integer, Integer>> iterator = cache.entrySet().iterator();
            iterator.next();
            iterator.remove();
        }
        cache.put(key, value);
    }

    public void print() {
        for (Map.Entry<Integer, Integer> entry : cache.entrySet()) {
            System.out.print("{" + entry.getKey() + ", " + entry.getValue() + "} ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LruCache lruCache = new LruCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.print();
        System.out.println(lruCache.get(1));
        lruCache.print();
        lruCache.put(3, 3);
        lruCache.print();
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        lruCache.print();
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
        lruCache.print();
    }
}
