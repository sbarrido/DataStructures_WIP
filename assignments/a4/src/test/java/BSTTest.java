import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {
    BST<Integer> intTree;
    BST<String> strTree;
    BinaryNode<String> rootStr;

    @BeforeEach
    void initBST() {
        intTree = new BST<>();

        rootStr = new BinaryNode<>("Start");
        strTree = new BST<>(rootStr);
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
    void insertBalanceTests() {
        //Insert empty
        intTree.insert(5);
        assertEquals(true, intTree.root() instanceof BinaryNode<Integer>);
        assertEquals(5, intTree.root().data());
        assertEquals(1, intTree.height());
        assertEquals(1, intTree.size());
        assertEquals(true, intTree.isBalanced());

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
        assertEquals(3, strTree.height());
        assertEquals(3, strTree.size());
        assertEquals(false, strTree.isBalanced());

        //insert to root.right
        strTree.insert("Answer");
        assertEquals(true, "Start".compareTo("Answer") > 0);
        assertEquals("Answer", strTree.root().right().data());
        assertEquals(3, strTree.height());
        assertEquals(4, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert root.left.left.left
        strTree.insert("z");
        assertEquals(true, "Start".compareTo("z") < 0);
        assertEquals("z", strTree.root().left().left().left().data());
        assertEquals(4, strTree.height());
        assertEquals(5, strTree.size());
        assertEquals(false, strTree.isBalanced());

        //Insert to root.right.left
        strTree.insert("Quiz");
        assertEquals(true, "Answer".compareTo("Quiz") < 0);
        assertEquals("Quiz", strTree.root().right().left().data());
        assertEquals(4, strTree.height());
        assertEquals(6, strTree.size());
        assertEquals(false, strTree.isBalanced());

        //insert to root.right.right
        strTree.insert("ABOUND");
        assertEquals(true, "Start".compareTo("ABOUND") > 0);
        assertEquals(true, "Answer".compareTo("ABOUND") > 0);
        assertEquals("ABOUND", strTree.root().right().right().data());
        assertEquals(4, strTree.height());
        assertEquals(7, strTree.size());
        assertEquals(false, strTree.isBalanced());

        //insert to root.left.right
        strTree.insert("THANK");
        assertEquals(true, "Start".compareTo("THANK") < 0);
        assertEquals(true, "THICC".compareTo("THANK") > 0);
        assertEquals("THANK", strTree.root().left().right().data());
        assertEquals(4, strTree.height());
        assertEquals(8, strTree.size());
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
        assertEquals(3, strTree.height());
        assertEquals(3, strTree.size());
        assertEquals(false, strTree.isBalanced());

        //insert to root.right
        strTree.insert("Answer");
        assertEquals(true, "Start".compareTo("Answer") > 0);
        assertEquals("Answer", strTree.root().right().data());
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
    void deleteTest() {
        //Delete Empty
        assertEquals(null, intTree.delete(0));
        assertEquals(0, intTree.size());
        assertEquals(0, intTree.height());
        //Delete Root
        assertEquals(rootStr, strTree.delete("Start"));
        assertEquals(null, strTree.root());
        assertEquals(0, strTree.size());
        assertEquals(0, strTree.height());

        strTree.insert(rootStr.data());
        loadStr();
        assertEquals(4, strTree.size());
        assertEquals(3, strTree.height());
        assertEquals(true, strTree.isBalanced());

        //Delete Leaf
        assertEquals("Answer", strTree.delete("Answer").data());
        assertEquals(3, strTree.size());
        assertEquals(3, strTree.height());
        assertEquals(false, strTree.isBalanced());

        //Delete Mid
        assertEquals("THICC", strTree.delete("THICC").data());
        assertEquals("apple", strTree.root().left().data());
        assertEquals(2, strTree.size());
        assertEquals(2, strTree.height());
        assertEquals(true, strTree.isBalanced());
    }
    @Test
    void orderingTest() {
        // Start
        // THICC ANSWER
        //apple
        loadStr();
        ArrayList<String> pre = (ArrayList<String>) strTree.preOrderList();
        ArrayList<String> in = (ArrayList<String>) strTree.inOrderList();
        ArrayList<String> post = (ArrayList<String>) strTree.postOrderList();

        //Pre
        assertEquals("Start", pre.get(0));
        assertEquals("THICC", pre.get(1));
        assertEquals("apple", pre.get(2));
        assertEquals("Answer", pre.get(3));

        //in order
        assertEquals("apple", in.get(0));
        assertEquals("THICC", in.get(1));
        assertEquals("Start", in.get(2));
        assertEquals("Answer", in.get(3));

        //Post
        assertEquals("apple", post.get(0));
        assertEquals("THICC", post.get(1));
        assertEquals("Answer", post.get(2));
        assertEquals("Start", post.get(3));
    }
}
