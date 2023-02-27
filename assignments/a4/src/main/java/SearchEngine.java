import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchEngine {
    private int mode;
    private Tree<Node> nodeTree; // List -> Tree

    // build everything bahahah
    // TODO: mode 3 = BST mode 4 = AVL
    public SearchEngine(int mode) throws IOException {
        this.mode = mode;

        switch(this.mode) {
            case 3:
                nodeTree = new BST<>();
                buildList();
                break;
            case 4:
                nodeTree = new AVL<>();
                buildList();
                break;
        }
    }

    public Tree<Node> getNodeTree(){
        return this.nodeTree;
    }

    // assumes that the file exists already
    // TODO: tweak logic so that it builds the proper tree
    public void buildList() throws IOException {
        System.out.println("reading");
        InputStream in = getClass().getClassLoader().getResourceAsStream("dataset.txt");
        InputStreamReader inRead = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(inRead);
//        BufferedReader reader = new BufferedReader(new FileReader("dataset.txt"));
        String url;
        while((url = reader.readLine()) != null){
            System.out.println("reading..." + url);
            Document doc = Jsoup.connect(url).get();
            String text = doc.body().text().toLowerCase();
            String[] words = text.split("\\s+"); // splits by whitespace

            for (String word : words) {
                Node node = new Node(word);
                BinaryNode<Node> target = nodeTree.search(node);

                if(target == null) {
                    nodeTree.insert(node);
                } else {
                    Node data = target.data();
                    ArrayList<String> urls = data.getReferences();
                    if(!urls.contains(url)) {
                        urls.add(url);
                    }
                }
            }
        }
        reader.close();
        System.out.println("Finished reading through all URLs");
    }

    // return a List ew ._.
    // TODO: Return the reference list of URLs
    public ArrayList<String> search(String term) {
        System.out.println("Searching for " + term + " using data structure mode " + mode + "...");
        // Search logic goes here
        Node target = new Node(term);
        BinaryNode<Node> found = nodeTree.search(target);
        ArrayList<String> targetReferences = null;
        if(found != null) {
            Node node = found.data();
            targetReferences = node.getReferences();
        }

        return targetReferences;
    }

    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner(System.in);

        System.out.println("Enter mode as in what data structure to use:");
        System.out.println("    3. BST ");
        System.out.println("    4. AVL");

        int mode = input.nextInt();

        System.out.println("Building Search Engine...");
        SearchEngine engine = new SearchEngine(mode);

        String answer = "y";
        while (answer.equals("y")) {
            input.nextLine(); // consume the remaining newline character
            System.out.print("Search (enter a term to query): ");
            String term = input.nextLine();
            ArrayList<String> targetUrls = engine.search(term);
            int count = 1;
            if(targetUrls == null) {
                System.out.println("Not found");
            } else {
                for(String url : targetUrls) {
                    System.out.println(count++ + ". " + url);
                }
            }
            System.out.print("Would you like to search another term (y/n)? ");
            answer = input.nextLine();
        }
        input.close();
    }
}