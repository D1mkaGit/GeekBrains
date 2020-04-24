package lesson_8;

import java.util.LinkedList;

public class ChainingHashMap<Key, Value> {
    private final int capacity = 7;
    private int size = 0;

    private final LinkedList<Node>[] st;

    public ChainingHashMap() {
        st = new LinkedList[capacity];
        for (int i = 0; i < st.length; i++) {
            st[i] = new LinkedList<>();
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private boolean isKeyNotNull(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Key null");
        }
        return true;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value value) {
        isKeyNotNull(key);
        int i = hash(key);
        for (Node node : st[i]) {
            if (key.equals(node.key)) {
                node.value = value;
                return;
            }
        }
        st[i].addLast(new Node(key, value));
        size++;
    }

    public Value get(Key key) {
        isKeyNotNull(key);
        int i = hash(key);
        for (Node node : st[i]) {
            if (key.equals(node.key)) {
                return node.value;
            }
        }
        return null;
    }

    public Node delete(Key key) {
        isKeyNotNull(key);
        int i = hash(key);
        for (Node node : st[i]) {
            if (key.equals(node.key)) {
                st[i].remove();
                return node;
            }
        }
        return null;
    }

    public Node deleteByValue(String value) {
        for (LinkedList<Node> list : st) {
            for (Node node : list) {
                if (node.value.equals(value)) {
                    st[hash(node.key)].remove();
                    return node;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            for (Node node : st[i]) {
                sb.append(node.key).append("=").append(node.value).append(", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private class Node {
        final Key key;
        Value value;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }
}
