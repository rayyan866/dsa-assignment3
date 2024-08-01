import java.util.ArrayList;
import java.util.List;

public class LinearProbingHashMap {
    private List<Pair> table;
    private List<Boolean> occupied;
    private int capacity;
    private int size;

    public LinearProbingHashMap(int cap) {
        this.capacity = cap;
        this.size = 0;
        this.table = new ArrayList<>(capacity);
        this.occupied = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(new Pair(-1, -1));
            occupied.add(false);
        }
    }

    public LinearProbingHashMap() {
        this(101);
    }

    private int hash(int key) {
        return key % capacity;
    }

    public boolean find(int key) {
        int index = hash(key);
        int originalIndex = index;
        while (occupied.get(index)) {
            if (table.get(index).key == key) {
                return true;
            }
            index = (index + 1) % capacity;
            if (index == originalIndex) break;
        }
        return false;
    }

    public void insert(int key, int value) {
        if (size >= capacity) {
            System.out.println("HashMap is full");
            return;
        }

        int index = hash(key);
        while (occupied.get(index)) {
            if (table.get(index).key == key) {
                table.get(index).value = value;
                return;
            }
            index = (index + 1) % capacity;
        }

        table.set(index, new Pair(key, value));
        occupied.set(index, true);
        size++;
    }

    public void remove(int key) {
        int index = hash(key);
        int originalIndex = index;
        while (occupied.get(index)) {
            if (table.get(index).key == key) {
                table.set(index, new Pair(-1, -1));
                occupied.set(index, false);
                size--;
                return;
            }
            index = (index + 1) % capacity;
            if (index == originalIndex) break;
        }
    }

    public void print() {
        for (int i = 0; i < capacity; ++i) {
            if (occupied.get(i)) {
                System.out.print("{" + table.get(i).key + ", " + table.get(i).value + "} ");
            }
        }
        System.out.println();
    }

    private static class Pair {
        int key;
        int value;

        Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LinearProbingHashMap hashMapLP = new LinearProbingHashMap();
        hashMapLP.insert(1, 100);
        hashMapLP.insert(2, 200);
        hashMapLP.insert(3, 300);
        hashMapLP.print();
        System.out.println(hashMapLP.find(2));
        hashMapLP.remove(2);
        hashMapLP.print();
        System.out.println(hashMapLP.find(2));

    }
}
