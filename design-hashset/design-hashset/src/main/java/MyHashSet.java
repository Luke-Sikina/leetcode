import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

class MyHashSet {
    private LinkedList<Integer>[] table;
    private int tableSize;
    private int entries;
    private static final int STARTING_TABLE_SIZE = 16;

    public MyHashSet() {
        table = new LinkedList[STARTING_TABLE_SIZE];
        tableSize = STARTING_TABLE_SIZE;
        entries = 0;
    }

    public void add(int key) {
        add(key, 1);
    }

    private void add(int key, int increment) {
        if (entries == tableSize) {
            adjustTableSize(tableSize * 2);
        }

        int resizedKey = resizeKey(key);
        LinkedList<Integer> head = table[resizedKey];
        if (head == null) {
            head = new LinkedList<>(List.of(key));
            table[resizedKey] = head;
            entries+=increment;
        } else if (!head.contains(key)){
            head.add(key);
            entries+=increment;
        }
    }

    private void adjustTableSize(int newSize) {
        tableSize = newSize;
        LinkedList<Integer>[] oldTable = table;
        table = new LinkedList[tableSize];

        for (LinkedList<Integer> integers : oldTable) {
            if (integers != null) {
                integers.forEach(i -> add(i, 0));
            }
        }
    }

    private int resizeKey(int key) {
        return key & (table.length - 1);
    }

    public void remove(int key) {
        int resizedKey = resizeKey(key);
        LinkedList<Integer> head = table[resizedKey];
        if (head != null && head.remove(Integer.valueOf(key))) {
            entries--;
        }
        if (entries == tableSize / 2 && tableSize > STARTING_TABLE_SIZE) {
            adjustTableSize(tableSize / 2);
        }

    }

    public boolean contains(int key) {
        LinkedList<Integer> head = table[resizeKey(key)];
        return head != null && head.contains(key);
    }
}