package lesson_6;

public class MyTreeMap<Key extends Comparable<Key>, Value> {
   // ссылка на корневой элемент
    Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;

        int size;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private boolean isKeyNotNull(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key не должен быть null");
        }
        return true;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        isKeyNotNull(key);
        if (node == null) {
            return null;
        }
        // если наш ключ совпал
        int cmp = key.compareTo(node.key);

        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            // идем на лево
            return get(node.left, key);
        } else {
            // идем на право
            return get(node.right, key);
        }
    }

    public boolean contains(Key key) {
        return get(root, key) != null;
    }

    public void put(Key key, Value value) {
        isKeyNotNull(key);
        if (value == null) {
            // delete(key);
            return;
        }
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        // новый узел
        if (node == null) {
            return new Node(key, value);
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.value = value;
        } else if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }

        // пересчитать размер узла
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Node node) {
        if (node == null) {
            return "";
        }

        return toString(node.left) + " " + node.key.toString() + " = " +
                node.value.toString() + " " + toString(node.right);
    }


    public Key minKey() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }

        return min(node.left);
    }

    public Key maxKey() {
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }

        return max(node.right);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }

        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void delete(Key key) {
        isKeyNotNull(key);
        delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else  if(cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node temp = node;
            node = min(node.right);

            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }


}
