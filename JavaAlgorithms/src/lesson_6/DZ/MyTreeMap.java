package lesson_6.DZ;


public class MyTreeMap<Key extends Comparable<Key>, Value> {
    Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int size;

        // добавлена высота
        int height;////////

        public Node( Key key, Value value ) {
            this.key = key;
            this.value = value;
            size = 1;
            height = 0;////////
        }
    }

    public int size() {
        return size(root);
    }

    private int size( Node node ) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    ///////////////////////////
    public int height() {
        return height(root);
    }

    private int height( Node node ) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }
    /////////////////////////////////

    public boolean isEmpty() {
        return root == null;
    }

    private boolean isKeyNotNull( Key key ) {
        if (key == null) {
            throw new IllegalArgumentException("key не должен быть null");
        }
        return true;
    }

    public boolean contains( Key key ) {
        return get(root, key) != null;
    }

    public Value get( Key key ) {
        return get(root, key);
    }

    private Value get( Node node, Key key ) {
        isKeyNotNull(key);
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    public void put( Key key, Value value ) {
        isKeyNotNull(key);
        if (value == null) {
            //delete(key)
            return;
        }
        root = put(root, key, value);
    }

    private Node put( Node node, Key key, Value value ) {
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
        node.size = size(node.left) + size(node.right) + 1;

        calcHeight(node);
        return node;
    }

    public Key minKey() {
        return min(root).key;
    }

    private Node min( Node node ) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    public Key maxKey() {
        return max(root).key;
    }

    private Node max( Node node ) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    private Node deleteMin( Node node ) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;

        calcHeight(node);
        return node;
    }

    public void delete( Key key ) {
        isKeyNotNull(key);
        delete(root, key);
    }

    private Node delete( Node node, Key key ) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
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

        calcHeight(node);
        return node;
    }

    private void calcHeight( Node node ) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    public boolean isBalance() {
        return isBalance(root);
    }

    private boolean isBalance( Node node ) {
        if (node == null) {
            return true;
        }
        return isBalance(node.left) &&
                isBalance(node.right) &&
                Math.abs(
                        Math.max(height(node.left), 0)
                                - Math.max(height(node.right), 0)) < 2
                ;
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString( Node node ) {
        if (node == null) {
            return "";
        }
        return toString(node.left) + " " +
                node.key.toString() + " = " + node.value.toString() +
                " h(" + node.height + ")" +
                ", " + toString(node.right);
    }
}
