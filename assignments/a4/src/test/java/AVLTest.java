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
        intTree.insert(0);
        assertEquals(0, intTree.root().data());
        assertEquals(1, intTree.height());
        assertEquals(1, intTree.size());
        assertEquals(true, intTree.isBalanced());

        //Insert to rootleft
        strTree.insert("THICC");
        assertEquals(true, rootStr.data().compareTo("THICC") < 0);
        assertEquals("THICC", strTree.root().left().data());
        assertEquals(2, strTree.height());
        assertEquals(2, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Attempt insert root.left.left
        //should right-rotate
        strTree.insert("apple");
        assertEquals(true, "THICC".compareTo("apple") < 0);
        assertEquals("THICC", strTree.root().data());
        assertEquals("apple",strTree.root().left().data());
        assertEquals("Start", strTree.root().right().data());
        assertEquals(2, strTree.height());
        assertEquals(3, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //attempt insert root.left.left
        strTree.insert("z");
        assertEquals(true, "THICC".compareTo("z") < 0);
        assertEquals(true, "apple".compareTo("z") < 0);
        assertEquals(3, strTree.height());
        assertEquals(4, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Attempt insert root.right.right
        //Makes a line
        strTree.insert("Quiz");
        assertEquals(true, "THICC".compareTo("Quiz") > 0);
        assertEquals(true, "Start".compareTo("Quiz") > 0);
        assertEquals(4, strTree.height());
        assertEquals(5, strTree.size());
        assertEquals(true, strTree.isBalanced());
    }
    void loadStr() {
        //Insert to root.left
        strTree.insert("THICC");
        assertEquals(true, rootStr.data().compareTo("THICC") < 0);
        assertEquals("THICC", strTree.root().left().data());
        assertEquals(2, strTree.height());
        assertEquals(2, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //insert to root.left.left
        strTree.insert("apple");
        assertEquals(true, "THICC".compareTo("apple") < 0);
        assertEquals(2, strTree.height());
        assertEquals(3, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //insert to root.right
        strTree.insert("Answer");
        assertEquals(true, "Start".compareTo("Answer") > 0);
        assertEquals(3, strTree.height());
        assertEquals(4, strTree.size());
        assertEquals(true, strTree.isBalanced());
    }
    @Test
    void searchTest() {
        //Search Empty
        assertEquals(null, intTree.search(0));
        //Search Root
        assertEquals(rootStr, strTree.search("Start"));

        loadStr();
        //Search Mid
        assertEquals("THICC", strTree.search("THICC").data());
        //Search Leaf
        assertEquals("Answer", strTree.search("Answer").data());

        //Search !Exist
        assertEquals(null, strTree.search("My Mental"));
    }
    @Test
    void orderingTest() {
        //  THICC
        // apple Start
        //          Answer
        loadStr();
        ArrayList<String> pre = (ArrayList<String>) strTree.preOrderList();
        ArrayList<String> in = (ArrayList<String>) strTree.inOrderList();
        ArrayList<String> post = (ArrayList<String>) strTree.postOrderList();

        //Pre
        assertEquals("THICC", pre.get(0));
        assertEquals("apple", pre.get(1));
        assertEquals("Start", pre.get(2));
        assertEquals("Answer", pre.get(3));

        //in order
        assertEquals("apple", in.get(0));
        assertEquals("THICC", in.get(1));
        assertEquals("Start", in.get(2));
        assertEquals("Answer", in.get(3));

        //Post
        assertEquals("apple", post.get(0));
        assertEquals("Answer", post.get(1));
        assertEquals("Start", post.get(2));
        assertEquals("THICC", post.get(3));
    }
}
