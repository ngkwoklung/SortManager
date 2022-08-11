package org.sparta.jn.sortmanager.sorters.binarysort;

import org.sparta.jn.sortmanager.exceptions.ChildNotFoundException;
import org.sparta.jn.sortmanager.sorters.Sortable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//public - methods you can access
//private - implementation
//abstract Nodes - show values
public class BinaryTreeImpl implements BinaryTree, Sortable {
    private final Node rootNode;

    public BinaryTreeImpl(int elements) {
        rootNode = new Node(elements);
    }

    @Override
    public void addElement(int element) {
        addNodeToTree(rootNode, element);
    }

    @Override
    public int getRootElement() {
        return rootNode.getValue();
    }

    @Override
    public int getNumberOfElements() {
        return countNode(rootNode);
    }

    @Override
    public void addElements(int[] elements) {
        for(int element: elements) {
            addElement(element);
        }

    }

    public boolean findElement(int element) {
        Node node = findNode(element);
        if (node != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getLeftChild(int element) throws ChildNotFoundException {
        Node node = findNode(element);
        return node.getLeftChild().getValue();

//        try {
//            return node.getLeftChild().getValue();
//        } catch (ChildNotFoundException e) {
//            System.err.println("That element has no left child!");
//            return 0;
//        }
    }

    @Override
    public int getRightChild(int element) throws ChildNotFoundException {
        Node node = findNode(element);
        return node.getRightChild().getValue();
    }

    @Override
    public int[] getSortedTreeAsc() {

        List<Integer> list = new ArrayList<>();
        treeToListAsc(rootNode, list);
        return listToArray(list);
    }

    @Override
    public int[] getSortedTreeDesc() {

        List<Integer> list = new ArrayList<>();
        treeToListDesc(rootNode, list);
        return listToArray(list);
    }
    private void treeToListAsc(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        treeToListAsc(node.getLeftChild(), list);
        list.add(node.getValue());
        treeToListAsc(node.getRightChild(), list);
    }

    private void treeToListDesc(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        treeToListDesc(node.getRightChild(), list);
        list.add(node.getValue());
        treeToListDesc(node.getLeftChild(), list);
    }

    private int[] listToArray(List<Integer> listIn) {
        int[] arr = new int[listIn.size()];
        for (int i = 0; i < listIn.size(); i++) {
            arr[i] = listIn.get(i);
        }
        return arr;
    }

    private void treeToArrayAsc(Node node, int[] arr, int count) { //Not working
        if (node == null) {
            return;
        }
        treeToArrayAsc(node.getLeftChild(), arr, count);
        arr[count++] = node.getValue();
        treeToArrayAsc(node.getRightChild(), arr, count);
    }

    private void addNodeToTree(Node node, int element) { //grunt work
        if (element < node.getValue()) {
            if (node.isLeftChildEmpty()) {
                node.setLeftChild(new Node(element));
            } else {
                addNodeToTree(node.getLeftChild(), element);
            }
        } else if (element > node.getValue()) {
            if (node.isRightChildEmpty()) {
                node.setRightChild(new Node(element));
            } else {
                addNodeToTree(node.getRightChild(), element);
            }
        }
    }

    private Node findNode(int element) {
        Node node = rootNode;
        while(node != null) {
            if (element == node.getValue()) {
                return node;
            }

            if(element < node.getValue()) {
                node = node.getLeftChild();
            } else if (element > node.getValue()) {
                node = node.getRightChild();
            }
        }
        return null;
    }

    private int countNode(Node node) {
        int count = 1;
        if (node == null) {
            return 0;
        }
        count = count + countNode(node.getLeftChild()) + countNode(node.getRightChild());
        return count;
    }




    @Override
    public int[] sortArray(int[] arrayToSort) {
        return new int[0];
    }
    public static void main(String[] args) {
        BinaryTreeImpl tree = new BinaryTreeImpl(5);
        tree.addElement(11);
        tree.addElement(2);
        tree.addElement(6);
//        System.out.println(tree.getNumberOfElements());
//        System.out.println(tree.getLeftChild(11));
//        System.out.println(Arrays.toString(tree.getSortedTreeAsc()));;
//        ArrayList<Object> arrayList = new ArrayList<>();
        System.out.println(Arrays.toString(tree.getSortedTreeDesc()));
    }
}

