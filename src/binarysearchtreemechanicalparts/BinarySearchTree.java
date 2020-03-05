/*
 * This class contains a nested class Node of generic type. The class BinarySearchTree
 * is of type Node and is used to perform methods on the Nodes within the tree
 * including add, recursive add, balance tree, find root node, clear tree, delete,
 * search, find smallest or largest, and display the tree. 
 */
package binarysearchtreemechanicalparts;

public class BinarySearchTree<Node> {

    //inner nested class Node
    private class Node<T> {

        //declare attributes of class node
        public T data;
        public Node left;
        public Node right;

        public Node(T data) {
            this.data = data;
        }
    }//end node class
    //declare root outside of node class
    Node root;

    //default constuctor
    public BinarySearchTree() {
    }

    //method to find root of tree
    public String findRoot() {
        if (root != null) {
            return root.data.toString();
        } else {
            return "Tree is empty";
        }
    }

    //method to add a node to the tree, passes values to a private recursive method
    public void add(String data) {
        Node newItem = new Node(data);
        if (root == null) {
            root = newItem;
        } else {
            root = recursiveInsert(root, newItem);
        }
    }

    //method to add nodes to tree in the currect order, keeping the tree sorted.
    //it also keeps the tree balanced by checking the height of tree
    private Node recursiveInsert(Node current, Node n) {
        if (current == null) {
            current = n;
            return current;
        } else if (n.data.toString().compareTo(current.data.toString()) < 0) {
            current.left = recursiveInsert(current.left, n);
            current = balanceTree(current);
        } else if (n.data.toString().compareTo(current.data.toString()) > 0) {
            current.right = recursiveInsert(current.right, n);
            current = balanceTree(current);
        }
        return current;
    }

    //method to check if the tree is balanced. if it is not, rotations are
    // performed to correct it.
    private Node balanceTree(Node current) {
        int bFactor = balanceFactor(current);
        if (bFactor > 1) {
            if (balanceFactor(current.left) > 0) {
                current = rotateLL(current);
            } else {
                current = rotateLR(current);
            }
        } else if (bFactor < -1) {
            if (balanceFactor(current.right) > 0) {
                current = rotateRL(current);
            } else {
                current = rotateRR(current);
            }
        }
        return current;
    }

    //method to clear all nodes within the tree passes the root vallue to a 
    //private method
    public void clearTree() {
        root = clearTree(root);
    }

    //ClearTree method passes the root node data value to the delete method which
    //effectively deletes the entire tree
    private Node clearTree(Node n) {
        try {
            if (delete(n, n.data.toString()) != null) {
                System.out.println("Tree has been cleared");
            }
        } catch (NullPointerException npe) {
            System.out.println("Tree is empty");
        }
        return null;
    }

    //a public method passing a target value to a private method for deletion.
    // also contains some print statements and throws exception if target not found.
    public void delete(String target) {//and here
        try {
            root = delete(root, target);
            if (root.data != null) {
                System.out.println("'" + target + "' was deleted!");
            }
        } catch (NullPointerException npe) {
            System.out.println("Nothing found for '" + target + "'. No deletion performed.");
        }
    }

    //the private delete method takes the root node and compares it with the target 
    // deletion node to search for it in the already balanced tree. if found, will 
    // set its value to null (or mov child nodes) and perform a tree rebalance if  
    // required. if not found, will return null.
    private Node delete(Node current, String target) {
        Node parent;
        if (current.data == null) {
            return null; //tree is empty
        } else {
            //left subtree
            if (target.compareTo(current.data.toString()) < 0) { //target < data
                current.left = delete(current.left, target);
                if (balanceFactor(current) == -2) {
                    if (balanceFactor(current.right) <= 0) {
                        current = rotateRR(current);
                    } else {
                        current = rotateRL(current);
                    }
                }
            } //right subtree
            else if (target.compareTo(current.data.toString()) > 0) { //target > data
                current.right = delete(current.right, target);
                if (balanceFactor(current) == 2) {
                    if (balanceFactor(current.left) >= 0) {
                        current = rotateLL(current);
                    } else {
                        current = rotateLR(current);
                    }
                }
            } //if target is found
            else {
                if (current.right != null) {
                    //delete its inorder successor
                    parent = current.right;
                    while (parent.left != null) {
                        parent = parent.left;
                    }
                    current.data = parent.data;
                    current.right = delete(current.right, parent.data.toString());
                    if (balanceFactor(current) == 2)//rebalancing
                    {
                        if (balanceFactor(current.left) >= 0) {
                            current = rotateLL(current);
                        } else {
                            current = rotateLR(current);
                        }
                    }
                } else { //if current.left != null
                    return current.left;
                }
            }
            return current;
        }
    }

    //search method passes a String in to a private method. Contains print statements.
    public void find(String key) {
        try {
            if (find(key, root).data == key) {
                System.out.println("'" + key + "' was found!");
            }
        } catch (NullPointerException npe) {
            System.out.println("Nothing found for '" + key + "'");
        }
    }

    //private search method uses the root node to search for the target by 
    // comparing the string 'data' in each node. if not found returns null.
    private Node find(String target, Node current) {
        if (current.data == null) {
            return null;
        } else if (target == current.data) { //or replace with compareTo == 0 (same)
            return current;
        } else if (target.compareTo(current.data.toString()) < 0) {
            return find(target, current.left);
        } else if (target.compareTo(current.data.toString()) > 0) {
            return find(target, current.right);
        }
        return current;
    }

    //find smallest method passes the root node to a private findSmallest method.
    // also contains print statements
    public void findSmallest() {
        try {
            String smallest = findSmallest(root).data.toString();
            if (smallest != null) {
                System.out.println("minimum is: " + smallest);
            }
        } catch (NullPointerException npe) {
            System.out.println("Error: No smallest value as the tree is empty");
        }
    }

    //private find smallest method uses the root node to recursively check for a
    // left child of the current node. the smallest node value is returned
    private Node findSmallest(Node current) {
        if (current != null) {
            while (current.left != null) { //loop to left
                current = current.left;
            }
            Node smallest = current;
            return smallest;
        } else {
            return null;
        }
    }

    //find largest method passes the root node to a private findLargest method.
    // also contains print statements
    public void findLargest() {
        try {
            String largest = findLargest(root).data.toString();
            if (largest != null) {
                System.out.println("maximum is: " + largest);
            }
        } catch (NullPointerException npe) {
            System.out.println("Error: No largest value as the tree is empty");
        }
    }

    //private find largest method uses the root node to recursively check for a
    // right child of the current node. the largest node value is returned
    private Node findLargest(Node current) {
        if (current != null) {
            while ((current.right) != null) {
                current = current.right;
                findLargest(current);
            }
            Node largest = current;
            return largest;
        } else {
            return null;
        }
    }

    //contains method used for testing the searc method in JUnit
    public boolean contains(String current) {
        return find(current, root) != null;
    }

    //public method checks if the tree is empty, if it is not, it will call on the 
    // private display tree method to display nodes in the tree in the correct order
    public void displayTree() {
        if (root == null) {
            System.out.println("-----Balanced Tree Display-----\nTree is empty");
            return;
        }
        System.out.println("-----Balanced Tree Display-----");
        inOrderDisplayTree(root);
        System.out.println();
    }

    //private display method to recursively call all nodes in the tree and display them.
    private void inOrderDisplayTree(Node current) {
        if (current != null) {
            inOrderDisplayTree(current.left);
            System.out.println(current.data);
            inOrderDisplayTree(current.right);
        }
    }

    //method max uses a ternary condition statement to compare the left and right
    // sub-trees. returns the height value (int) of the higher sized sub-tree. 
    // used by the getHeight method
    private int max(int l, int r) {
        return l > r ? l : r;
    }

    //private getHeight method obtains the height of the 2 sub-trees (left and right)
    // and uses the max method to compare these heights. it returns this height + 1
    private int getHeight(Node current) {
        int height = 0;
        if (current != null) {
            int l = getHeight(current.left);
            int r = getHeight(current.right);
            int m = max(l, r);
            height = m + 1;
        }
        return height;
    }

    //method to pass the root value in to a private getSize method which will 
    //return the number of nodes as an int
    public int getSize() {
        int length = getSize(root);
        return length;
    }

    //private method uses root node to count the number of nodes on each side of
    // the tree in a similar way the getHeight method works
    private int getSize(Node current) {
        int length = 0;
        if (current != null) {
            int left = getHeight(current.left);
            int right = getHeight(current.right);
            length = left + right + 1;
        }
        return length;
    }

    //balance factor uses getHeight method to get the height of each side of the
    //Binary Search Tree, and within each sub-tree side the height of each left 
    //and right side of that sub-tree. It is used by the balanceTree method 
    private int balanceFactor(Node current) {
        int l = getHeight(current.left);
        int r = getHeight(current.right);
        int bFactor = l - r;
        return bFactor;
    }

    //these 4 private methods perform rotations to change the structure of the 
    //tree depending on where nodes or inserted or deleted. 
    private Node rotateRR(Node parent) {
        Node pivot = parent.right;
        parent.right = pivot.left;
        pivot.left = parent;
        return pivot;
    }

    private Node rotateLL(Node parent) {
        Node pivot = parent.left;
        parent.left = pivot.right;
        pivot.right = parent;
        return pivot;
    }

    private Node rotateLR(Node parent) {
        Node pivot = parent.left;
        parent.left = rotateRR(pivot);
        return rotateLL(parent);
    }

    private Node rotateRL(Node parent) {
        Node pivot = parent.right;
        parent.right = rotateLL(pivot);
        return rotateRR(parent);
    }
}
