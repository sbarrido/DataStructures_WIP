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
        //RotateLeft
        /*      Start                           THICC
                    THICC           =>      Start   apple
                           apple

         */
        strTree.insert("apple");
        assertEquals("THICC", strTree.root().data());
        assertEquals(true, "apple".compareTo(rootStr.data()) > 0);
        assertEquals(true, "apple".compareTo("THICC") > 0);
        assertEquals(2, strTree.height());
        assertEquals(3, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.left.right
        /*
                THICC
            Start     apple
                THE
         */
        strTree.insert("THE");
        assertEquals(true, "THE".compareTo(strTree.root().data()) < 0);
        assertEquals(true, "THE".compareTo("Start") > 0);
        assertEquals(3, strTree.height());
        assertEquals(4, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.left.left
       /*
                THICC
            Start     apple
      Answer     THE
        */
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
    void loadInt() {
        intTree.insert(0);
        assertEquals(1, intTree.size());
        assertEquals(1, intTree.height());

        intTree.insert(1);
        assertEquals(2, intTree.size());
        assertEquals(2, intTree.height());

        /*
            0
               1     =>       1
                  2      0        2
         */
        // Left Rotate
        intTree.insert(2);
        assertEquals(3, intTree.size());
        assertEquals(2, intTree.height());

        /*
                    1
              0         2
                            3
         */
        intTree.insert(3);
        assertEquals(4, intTree.size());
        assertEquals(3, intTree.height());

         /*
                    1                             1
              0         2           =>      0           3
                            3                       2       4
                                4
         */
        //Internal Left Rotate
        intTree.insert(4);
        assertEquals(5, intTree.size());
        assertEquals(3, intTree.height());

    }
    void loadStr() {
        //Insert to root.right

        strTree.insert("THICC");
        assertEquals(true, "THICC".compareTo(strTree.root().data()) > 0);
        assertEquals("THICC", strTree.root().right().data());
        assertEquals(2, strTree.height());
        assertEquals(2, strTree.size());
        assertEquals(true, strTree.isBalanced());

        //Insert to root.right.right
        //RotateL
        strTree.insert("apple");
        assertEquals(true, "apple".compareTo(strTree.root().data()) > 0);
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
            /*          Start
                Answer          THICC
            Ab     Answers  THE
         */
        strTree.delete("apple");
        assertEquals(6, strTree.size());
        assertEquals(3, strTree.height());

        //Delete Mid
            /*          Start
                Answer        THE
            Ab     Answers
         */
        strTree.delete("THICC");
        assertEquals(5, strTree.size());
        assertEquals(3, strTree.height());
        assertEquals("Start", strTree.root().data());
        assertEquals("THE", strTree.root().right().data());


        //Delete Leaf
            /*          Start               Answer
                Answer           =>    Ab           Start
            Ab     Answers                     Answers
         */
        //ROTATE RIGHT
        strTree.delete("THE");
        assertEquals(4, strTree.size());
        assertEquals(3, strTree.height());
        assertEquals("Start", strTree.root().right().data());
        assertEquals("Answer", strTree.root().data());
        assertEquals("Ab", strTree.root().left().data());

        loadInt();
              /*
                    1                             1
              0         2           =>      0           3
                            3                       2       4
                                4
         */
        assertEquals(5, intTree.size());
        assertEquals(3, intTree.height());

        /*
                        1
                  0          2
                                   4
         */
        intTree.delete(3);
        assertEquals(4, intTree.size());
        assertEquals(3, intTree.height());
        assertEquals(2, intTree.root().right().data());
    }
    @Test
    void orderingTest() {
        /*              Start
                Answer          THICC
            Ab     Answers  THE         apple
         */
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
