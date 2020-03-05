/*
 * Name: Zara Duncanson
 * Student ID: P229768
 * Date: 20/02/2020
 * Task: Portfolio Q2
 * This is the test class for MechanicalPartsList - a balanced Binary Search Tree
 */
package binarysearchtreemechanicalparts;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Zara
 */
public class MechanicalPartsListTest {
    /**
     * A BST is created and some actions performed for testing
     */
    BinarySearchTree<String> testParts = new BinarySearchTree<>();

    //test if the BST is currently empty
    @Test
    public void testBSTIsEmpty() {
        assertEquals(0, testParts.getSize()); //test if BST is empty
    }

    @Test
    public void testBSTIntIsEmpty() {
        BinarySearchTree<Integer> testPartsInt = new BinarySearchTree<>();
        assertEquals(0, testPartsInt.getSize()); //test if BST is empty
    }
    
    //test add method
    @Test
    public void testAdd() {
        testParts.add("Nut");
        testParts.add("Bolt");
        assertEquals(2, testParts.getSize());
    }

    //test delete method
    @Test
    public void testDelete() {
        testAdd();
        testParts.delete("Nut");
        assertEquals(1, testParts.getSize());
    }

    //test clear whole tree method
    @Test
    public void testClearTree() {
        testAdd();
        testParts.clearTree();
        assertEquals(0, testParts.getSize());
    }

    //test get root node method
    @Test
    public void testFindRootNode() {
        testParts.add("Nut");
        testParts.add("Bolt");
        String root = testParts.findRoot();
        assertThat(root, is("Nut")); //test the root node
    }

    //test search method
    @Test
    public void testSearchWhenElementPresent() {
        testParts.add("Nut");
        testParts.add("Bolt");
        assertThat(testParts.contains("Nut"), is(true));
    }
    
    //test search method when element does not exist
    @Test(expected = NullPointerException.class)
    public void testSearchWhenElementAbsent() {
        assertThat(testParts.contains("Nut"), is(false));
    }
}
