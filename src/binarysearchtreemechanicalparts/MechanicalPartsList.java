
/* Name: Zara Duncanson
 * Student ID: P229768
 * Date: 20/02/2020
 * Task: Portfolio Q2
 * This program creates a Binary search tree object of type Node with mechanical
 * parts stored as Nodes within the Balanced Binary Search Tree Stucture. These 
 * nodes can be added, searched, and deleted. The tree can also be cleared entirely. 
 */
package binarysearchtreemechanicalparts;

public class MechanicalPartsList {

    public static void main(String[] args) {
        //create a binary search tree of type string to store mechanical parts
        BinarySearchTree<String> mechanicalParts = new BinarySearchTree<>();
        //add the parts as nodes to the tree
        mechanicalParts.add("Engine");
        mechanicalParts.add("Clutch");
        mechanicalParts.add("Wheel");
        mechanicalParts.add("Spark Plug");
        mechanicalParts.add("Radiator");
        mechanicalParts.add("Hose");
        mechanicalParts.add("Air Filter");
        mechanicalParts.add("Petrol Tank");
        mechanicalParts.add("Fan Belt");
        mechanicalParts.add("Axel");
        //display tree
        System.out.println("Mechanical Parts List: ");
        mechanicalParts.displayTree();
        //display root node string
        System.out.println("Tree root: " + mechanicalParts.findRoot());
        //search for part not present in tree
        mechanicalParts.find("Exhaust");
        //search for part present in tree
        mechanicalParts.find("Hose");
        //delete node
        mechanicalParts.delete("Axel");
        //attempt to delete item not present
        mechanicalParts.delete("Hub Cap");
        //display tree after deletion
        System.out.println("\nMechanical Parts List after deletion: ");
        mechanicalParts.displayTree();

        //clear all nodes and display
        mechanicalParts.clearTree();
        System.out.println("Mechanical Parts List after tree clear: ");
        mechanicalParts.displayTree();
        //attempt to find smallest node in empty tree
        mechanicalParts.findSmallest();
    }

}
