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

        strTree.insert("THICC");
        assertEquals(true, "THICC".compareTo(rootStr.data()) > 0);
        assertEquals("THICC", strTree.root().right().data());
        assertEquals(2, strTree.height());
        assertEquals(2, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.right.right
        //RotateL
        strTree.insert("apple");
        assertEquals(true, "apple".compareTo(rootStr.data()) > 0);
        assertEquals(true, "apple".compareTo("THICC") > 0);
        assertEquals(2, strTree.height());
        assertEquals(3, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.left.right
        strTree.insert("THE");
        assertEquals(true, "THE".compareTo(strTree.root().data()) < 0);
        assertEquals(true, "THE".compareTo("Start") > 0);
        assertEquals(3, strTree.height());
        assertEquals(4, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.left.left
        strTree.insert("Answer");
        assertEquals(true, "Answer".compareTo(strTree.root().data()) < 0);
        assertEquals(true, "Answer".compareTo("Start") < 0);
        assertEquals(3, strTree.height());
        assertEquals(5, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.left.left.left
        //right rotate
        String value = "Ab";
        strTree.insert(value);
        assertEquals(true, value.compareTo(strTree.root().data()) < 0);
        assertEquals(true, value.compareTo("Start") < 0);
        assertEquals(true, value.compareTo("Answer") < 0);
        assertEquals(3, strTree.height());
        assertEquals(6, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //insert to root.left.right.right
        value = "Answers";
        strTree.insert(value);
        assertEquals(true, value.compareTo(strTree.root().data()) < 0);
        assertEquals(true, value.compareTo("Answer") > 0);
        assertEquals(3, strTree.height());
        assertEquals(7, strTree.size());
        assertEquals(true, strTree.isBalanced());
    }
    void loadStr() {
        //Insert to root.right
        strTree.insert("THICC");
        assertEquals(true, "THICC".compareTo(rootStr.data()) > 0);
        assertEquals("THICC", strTree.root().right().data());
        assertEquals(2, strTree.height());
        assertEquals(2, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.right.right
        //RotateL
        strTree.insert("apple");
        assertEquals(true, "apple".compareTo(rootStr.data()) > 0);
        assertEquals(true, "apple".compareTo("THICC") > 0);
        assertEquals(2, strTree.height());
        assertEquals(3, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.left.right
        strTree.insert("THE");
        assertEquals(true, "THE".compareTo(strTree.root().data()) < 0);
        assertEquals(true, "THE".compareTo("Start") > 0);
        assertEquals(3, strTree.height());
        assertEquals(4, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.left.left
        strTree.insert("Answer");
        assertEquals(true, "Answer".compareTo(strTree.root().data()) < 0);
        assertEquals(true, "Answer".compareTo("Start") < 0);
        assertEquals(3, strTree.height());
        assertEquals(5, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.left.left.left
        //right rotate
        String value = "Ab";
        strTree.insert(value);
        assertEquals(true, value.compareTo(strTree.root().data()) < 0);
        assertEquals(true, value.compareTo("Start") < 0);
        assertEquals(true, value.compareTo("Answer") < 0);
        assertEquals(3, strTree.height());
        assertEquals(6, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //insert to root.left.right.right
        value = "Answers";
        strTree.insert(value);
        assertEquals(true, value.compareTo(strTree.root().data()) < 0);
        assertEquals(true, value.compareTo("Answer") > 0);
        assertEquals(3, strTree.height());
        assertEquals(7, strTree.size());
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
    void deleteTest() {
        /*              Start
                Answer          THICC
            Ab     Answers  THE         apple
         */
        loadStr();
        assertEquals(7, strTree.size());
        assertEquals(3, strTree.height());

        //Delete Leaf
        strTree.delete("apple");
        assertEquals(6, strTree.size());
        assertEquals(3, strTree.height());

        //Delete Leaf
        //Rotate right
        /*              Answer
                   Ab           Start
                            Answers   Thicc
         */
        strTree.delete("THE");
        assertEquals(5, strTree.size());
        assertEquals(3, strTree.height());
        assertEquals("Answer", strTree.root().data());
    }
    @Test
    void orderingTest() {
        //  THICC
        // Start Answer
        //Ab
        loadStr();
        ArrayList<String> pre = (ArrayList<String>) strTree.preOrderList();
        ArrayList<String> in = (ArrayList<String>) strTree.inOrderList();
        ArrayList<String> post = (ArrayList<String>) strTree.postOrderList();

        //Pre
        assertEquals("Start", pre.get(0));
        assertEquals("Answer", pre.get(1));
        assertEquals("Ab", pre.get(2));
        assertEquals("Answers", pre.get(3));
        assertEquals("THICC", pre.get(4));
        assertEquals("THE", pre.get(5));
        assertEquals("apple", pre.get(6));

        //in order
        assertEquals("Ab", in.get(0));
        assertEquals("Answer", in.get(1));
        assertEquals("Answers", in.get(2));
        assertEquals("Start", in.get(3));

        //Post
        assertEquals("Ab", post.get(0));
        assertEquals("Answers", post.get(1));
        assertEquals("Answer", post.get(2));
        assertEquals("THE", post.get(3));
    }
}
