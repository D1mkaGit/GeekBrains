package lesson_6.my_dz;

import java.util.Random;

public class MyTreeApp {
    public static void main(String[] args) {
        int stopLevel = 6;
        int numberOfTrees = 20;
        int numberOfNotBalanced = 0;
        MyTree[] myTree = new MyTree[numberOfTrees - 1];
        numberOfNotBalanced = makeTreesAndCalculateNumberOfNotBalanced(stopLevel, numberOfNotBalanced, myTree);
        System.out.println(" ");
        System.out.println((numberOfNotBalanced * 100 / numberOfTrees) + "% созданных деревьев являются несбалансированными");
    }

    private static int makeTreesAndCalculateNumberOfNotBalanced(int stopLevel, int numberOfNotBalanced, MyTree[] myTree) {
        for (int i = 0; i < myTree.length; i++) {
            myTree[i] = new MyTree();
            while (true) {
                int id = getRandomNumberInRange(-100, 100);
                myTree[i].insert(new MyData(id));
                if (myTree[i].find(id).getLevel() == stopLevel) break;
            }
            System.out.print("Дерево " +
                    (myTree[i].isBalancedTree() ? "сбалансированно" : "не сбалансированно") +
                    " с корневым числом: \"" + myTree[i].getRoot().getMyData().getId() + "\":");
            System.out.println(myTree[i]);

            if (!myTree[i].isBalancedTree()) numberOfNotBalanced++;

        }
        return numberOfNotBalanced;
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}

class MyTree {
    private MyNode root;
    private int rootMaxLeftLevel;
    private int rootMaxRightLevel;

    public MyNode find(int key) {
        MyNode current = root;
        while (current.getMyData().getId() != key) {
            if (key < current.getMyData().getId()) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
            if (current == null) return null;
        }
        return current;
    }

    public void insert(MyData myData) {
        MyNode node = new MyNode();
        node.setMyData(myData);
        if (root == null) {
            root = node;
        } else {
            MyNode current = root;
            MyNode parent;
            while (true) {
                parent = current;
                if (myData.getId() < current.getMyData().getId()) {
                    current = current.getLeftChild();
                    if (current == null) {
                        parent.setLeftChild(node);
                        node.setLevel(level(parent) + 1);
                        return;
                    }
                } else {
                    current = current.getRightChild();
                    if (current == null) {
                        parent.setRightChild(node);
                        node.setLevel(level(parent) + 1);
                        return;
                    }
                }
            }
        }
    }

    public MyNode getRoot() {
        return root;
    }

    private int level(MyNode node) {
        if (node == null) {
            return 0;
        }
        return node.getLevel();
    }

    public boolean isBalancedTree() {
        inOrderLevels(root.getLeftChild(), 1, true);
        inOrderLevels(root.getRightChild(), 1, false);
        int leftNodeCount = this.rootMaxLeftLevel;
        int rightNodeCount = this.rootMaxRightLevel;
        return (leftNodeCount - rightNodeCount) <= 1 && (rightNodeCount - leftNodeCount) <= 1;
    }

    private void inOrderLevels(MyNode rootNode, int rootMaxLevel, boolean isLeft) {
        if (rootNode != null) {
            inOrderLevels(rootNode.getLeftChild(), rootMaxLevel, isLeft);
            if (rootMaxLevel < rootNode.getLevel()) {
                if (isLeft) {
                    this.rootMaxLeftLevel = rootNode.getLevel();
                } else {
                    this.rootMaxRightLevel = rootNode.getLevel();
                }
                rootMaxLevel = rootNode.getLevel();
            }
            inOrderLevels(rootNode.getRightChild(), rootMaxLevel, isLeft);
        }
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(MyNode node) {
        if (node == null) {
            return "";
        }

        return toString(node.getLeftChild()) + " " + node.getMyData().getId() + " " + toString(node.getRightChild());
    }
}

class MyNode {
    private MyData myData;
    private MyNode leftChild;
    private MyNode rightChild;

    private int level;

    public MyData getMyData() {
        return myData;
    }

    public void setMyData(MyData myData) {
        this.myData = myData;
    }

    public MyNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(MyNode leftChild) {
        this.leftChild = leftChild;
    }

    public MyNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(MyNode rightChild) {
        this.rightChild = rightChild;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}

class MyData {
    private final int id;

    public MyData(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}

