import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTest {
    AVL<Integer> intTree;
    AVL<String> strTree;
    BinaryNode<String> rootStr;

    @BeforeEach
    void initAVL() {
        intTree = new AVL<>();

        rootStr = new BinaryNode<>("Start");
        strTree = new AVL<>(rootStr);
    }
    @Test
    void initTest() {
        assertEquals(null, intTree.root());
        assertEquals(0, intTree.height());
        assertEquals(0, intTree.size());


        assertEquals(true, strTree.root() instanceof BinaryNode<String>);
        assertEquals(1, strTree.height());
        assertEquals(1, strTree.size());
        assertEquals(true, strTree.isBalanced());
    }
    @Test
    void insertTests() {
        //Insert Empty
        intTree.insert(5);
        assertEquals(true, intTree.root() instanceof BinaryNode<Integer>);
        assertEquals(5, intTree.root().data());
        assertEquals(1, intTree.height());
        assertEquals(1, intTree.size());
        assertEquals(true, intTree.isBalanced());

        //Insert to root.right
        strTree.insert("THICC");
        assertEquals(true, "THICC".compareTo(rootStr.data()) > 0);
        assertEquals("THICC", strTree.root().right().data());
        assertEquals(2, strTree.height());
        assertEquals(2, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.right.right
        //Left ROTATE
        strTree.insert("apple");
        assertEquals(true, "apple".compareTo(rootStr.data()) > 0);
        assertEquals(true, "apple".compareTo("THICC") > 0);
        assertEquals(2, strTree.height());
        assertEquals(3, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.right.left
        strTree.insert("THE");
        assertEquals(true, "THE".compareTo(rootStr.data()) > 0);
        assertEquals(true, "THE".compareTo("THICC") < 0);
        assertEquals(3, strTree.height());
        assertEquals(4, strTree.size());
        assertEquals(false, strTree.isBalanced());

        //Insert to root.left
        strTree.insert("Answer");
        assertEquals(true, "Answer".compareTo(rootStr.data()) < 0);
        assertEquals(3, strTree.height());
        assertEquals(5, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.left.left
        String value = "Ab";
        strTree.insert(value);
        assertEquals(true, value.compareTo(rootStr.data()) < 0);
        assertEquals(true, value.compareTo("Answer") < 0);
        assertEquals(3, strTree.height());
        assertEquals(6, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //insert to root.left.right
        value = "Answers";
        strTree.insert(value);
        assertEquals(true, value.compareTo(rootStr.data()) < 0);
        assertEquals(true, value.compareTo("Answer") > 0);
        assertEquals(3, strTree.height());
        assertEquals(7, strTree.size());
        assertEquals(true, strTree.isBalanced());
    }
//    void loadStr() {
//        //Insert to root.right
//        strTree.insert("THICC");
//        assertEquals(true, "THICC".compareTo(rootStr.data()) > 0);
//        assertEquals("THICC", strTree.root().right().data());
//        assertEquals(2, strTree.height());
//        assertEquals(2, strTree.size());
//        assertEquals(true, strTree.isBalanced());
//
//        //Insert to root.right.right
//        strTree.insert("apple");
//        assertEquals(true, "apple".compareTo(rootStr.data()) > 0);
//        assertEquals(true, "apple".compareTo("THICC") > 0);
//        assertEquals(3, strTree.height());
//        assertEquals(3, strTree.size());
//        assertEquals(false, strTree.isBalanced());
//
//        //Insert to root.right.left
//        strTree.insert("THE");
//        assertEquals(true, "THE".compareTo(rootStr.data()) > 0);
//        assertEquals(true, "THE".compareTo("THICC") < 0);
//        assertEquals(3, strTree.height());
//        assertEquals(4, strTree.size());
//        assertEquals(false, strTree.isBalanced());
//
//        //Insert to root.left
//        strTree.insert("Answer");
//        assertEquals(true, "Answer".compareTo(rootStr.data()) < 0);
//        assertEquals(3, strTree.height());
//        assertEquals(5, strTree.size());
//        assertEquals(true, strTree.isBalanced());
//
//        //Insert to root.left.left
//        String value = "Ab";
//        strTree.insert(value);
//        assertEquals(true, value.compareTo(rootStr.data()) < 0);
//        assertEquals(true, value.compareTo("Answer") < 0);
//        assertEquals(3, strTree.height());
//        assertEquals(6, strTree.size());
//        assertEquals(true, strTree.isBalanced());
//
//        //insert to root.left.right
//        value = "Answers";
//        strTree.insert(value);
//        assertEquals(true, value.compareTo(rootStr.data()) < 0);
//        assertEquals(true, value.compareTo("Answer") > 0);
//        assertEquals(3, strTree.height());
//        assertEquals(7, strTree.size());
//        assertEquals(true, strTree.isBalanced());
//    }
//    @Test
//    void searchTest() {
//        //Search Empty
//        assertEquals(null, intTree.search(0));
//        //Search Root
//        assertEquals(rootStr, strTree.search("Start"));
//
//        loadStr();
//        //Search Mid
//        assertEquals("THICC", strTree.search("THICC").data());
//        //Search Leaf
//        assertEquals("Answer", strTree.search("Answer").data());
//
//        //Search !Exist
//        assertEquals(null, strTree.search("My Mental"));
//    }
//    @Test
//    void orderingTest() {
//        //  THICC
//        // apple Start
//        //          Answer
//        loadStr();
//        ArrayList<String> pre = (ArrayList<String>) strTree.preOrderList();
//        ArrayList<String> in = (ArrayList<String>) strTree.inOrderList();
//        ArrayList<String> post = (ArrayList<String>) strTree.postOrderList();
//
//        //Pre
//        assertEquals("THICC", pre.get(0));
//        assertEquals("apple", pre.get(1));
//        assertEquals("Start", pre.get(2));
//        assertEquals("Answer", pre.get(3));
//
//        //in order
//        assertEquals("apple", in.get(0));
//        assertEquals("THICC", in.get(1));
//        assertEquals("Start", in.get(2));
//        assertEquals("Answer", in.get(3));
//
//        //Post
//        assertEquals("apple", post.get(0));
//        assertEquals("Answer", post.get(1));
//        assertEquals("Start", post.get(2));
//        assertEquals("THICC", post.get(3));
//    }
}
