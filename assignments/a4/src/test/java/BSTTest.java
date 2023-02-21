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

        //Insert to root.right
        strTree.insert("THICC");
        assertEquals(true, "THICC".compareTo(rootStr.data()) > 0);
        assertEquals("THICC", strTree.root().right().data());
        assertEquals(2, strTree.height());
        assertEquals(2, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.right.right
        strTree.insert("apple");
        assertEquals(true, "apple".compareTo(rootStr.data()) > 0);
        assertEquals(true, "apple".compareTo("THICC") > 0);
        assertEquals(3, strTree.height());
        assertEquals(3, strTree.size());
        assertEquals(false, strTree.isBalanced());

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

    public void loadStr() {
        //Insert to root.right
        strTree.insert("THICC");
        assertEquals(true, "THICC".compareTo(rootStr.data()) > 0);
        assertEquals("THICC", strTree.root().right().data());
        assertEquals(2, strTree.height());
        assertEquals(2, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.right.right
        strTree.insert("apple");
        assertEquals(true, "apple".compareTo(rootStr.data()) > 0);
        assertEquals(true, "apple".compareTo("THICC") > 0);
        assertEquals(3, strTree.height());
        assertEquals(3, strTree.size());
        assertEquals(false, strTree.isBalanced());

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
    @Test
    void searchTest() {
        //Search Empty
        assertEquals(null, intTree.search(0));
        //Search Root
        assertEquals(rootStr, strTree.search("Start"));

        loadStr();
        assertEquals(7, strTree.size());
        //Search Mid
        assertEquals("THICC", strTree.search("THICC").data());
        assertEquals("Answer", strTree.search("Answer").data());
        //Search Leaf
        assertEquals("Answers", strTree.search("Answers").data());
        assertEquals("Ab", strTree.search("Ab").data());
        assertEquals("THE", strTree.search("THE").data());
        assertEquals("apple", strTree.search("apple").data());

        //Search !Exist
        assertEquals(null, strTree.search("My Mental"));
    }
    @Test
    void deleteTest() {
        //Delete Em(pty
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
        assertEquals(7, strTree.size());
        assertEquals(3, strTree.height());
        assertEquals(true, strTree.isBalanced());

        //Delete Mid - left
        assertEquals("Answer", strTree.delete("Answer").data());
        assertEquals("Ab", strTree.root().left().data());
        assertEquals(6, strTree.size());
        assertEquals(3, strTree.height());
        assertEquals(true, strTree.isBalanced());

        //Delete Leaf
        assertEquals("Answers", strTree.delete("Answers").data());
        assertEquals("Ab", strTree.root().left().data());
        assertEquals(5, strTree.size());
        assertEquals(3, strTree.height());
        assertEquals(true, strTree.isBalanced());

        //Delete Leaf - unbalance
        assertEquals("Ab", strTree.delete("Ab").data());
        assertEquals(null, strTree.root().left());
        assertEquals(4, strTree.size());
        assertEquals(3, strTree.height());
        assertEquals(false, strTree.isBalanced());

        //Delete Leaf - unbalce
        assertEquals("apple", strTree.delete("apple").data());
        assertEquals("THICC", strTree.root().right().data());
        assertEquals(3, strTree.size());
        assertEquals(3, strTree.height());
        assertEquals(false, strTree.isBalanced());

        //Delete Leaf - mid only 1 child - reblance
        assertEquals("THICC", strTree.delete("THICC").data());
        assertEquals("THE", strTree.root().right().data());
        assertEquals(2, strTree.size());
        assertEquals(2, strTree.height());
        assertEquals(true, strTree.isBalanced());
    }
    @Test
    void orderingTest() {
        /*
                Start
           Answer      THICC
        Ab   Answers THE    apple

         */
        loadStr();
        ArrayList<String> pre = (ArrayList<String>) strTree.preOrderList();
        ArrayList<String> in = (ArrayList<String>) strTree.inOrderList();
        ArrayList<String> post = (ArrayList<String>) strTree.postOrderList();

        //Pre - Node / left / right
        assertEquals("Start", pre.get(0));
        assertEquals("Answer", pre.get(1));
        assertEquals("Ab", pre.get(2));
        assertEquals("Answers", pre.get(3));
        assertEquals("THICC", pre.get(4));
        assertEquals("THE", pre.get(5));
        assertEquals("apple", pre.get(6));


        //in order - left / node / right
        assertEquals("Ab", in.get(0));
        assertEquals("Answer", in.get(1));
        assertEquals("Answers", in.get(2));
        assertEquals("Start", in.get(3));
        assertEquals("THE", in.get(4));
        assertEquals("THICC", in.get(5));
        assertEquals("apple", in.get(6));

        //Post - left / right / node
        assertEquals("Ab", post.get(0));
        assertEquals("Answers", post.get(1));
        assertEquals("Answer", post.get(2));
        assertEquals("THE", post.get(3));
        assertEquals("apple", post.get(4));
        assertEquals("THICC", post.get(5));
        assertEquals("Start", post.get(6));
    }
}
