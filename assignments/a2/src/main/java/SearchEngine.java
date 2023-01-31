import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.Scanner;

public class SearchEngine {

    private int mode;
    private List<Node> nodeList;

    // TODO: build the SearchEngine's nodelist according to mode (1 = ArrayList; 2 = SortedArrayList); build the searchEngine
    public SearchEngine(int mode) throws IOException {
        //1. = ArrayList
        //2. = SortedArrayList
        switch (mode) {
            case 1 -> {
                this.nodeList = new ArrayList<>();
                this.mode = mode;
                this.buildList();
            }
            case 2 -> {
                this.nodeList = new SortedArrayList<>();
                this.mode = mode;
                this.buildList();
            }
            default -> throw new IOException();
        }
    }

    public List<Node> getNodeList(){
        return this.nodeList;
    }

    // Go through the dataset and then create a new Node if the word hasn't been seen before. Add the current URL to its references
    // if it hasn't been seen. If the node has been created already, add the current URL to its references. Add the Node to the the
    // SearchEngine's nodeList
    public void buildList() throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream("dataset.txt");
        InputStreamReader inRead = new InputStreamReader((in));
        BufferedReader reader = new BufferedReader(inRead);
        String url;
        while((url = reader.readLine()) != null){
//            System.out.println("Loading " + url);
            Document doc = Jsoup.connect(url).get();
            String text = doc.body().text().toLowerCase();
            String[] words = text.split("\\s+"); // splits by whitespace
            // LOGIC HERE:
            //Iterate through list of words
            for(String w : words) {
                Node tmp = new Node(w, this.mode);
                int index = this.nodeList.search(tmp);

                //Searches if exists, index != -1
                //true = insert additional reference
                //false = add whole node w/ reference
                if(index > -1){
                    this.nodeList.get(index).insertReference(url);
                } else {
                    tmp.insertReference(url);
                    this.nodeList.add(tmp);
                }
            }
        }
        reader.close();
        System.out.println("Finished reading through all URLs");
    }

    // TODO: Return the node's reference list - if the term isn't found, return an empty list
    public List<String> search(String term) {
        long startTime = System.nanoTime();
        System.out.println("Searching for " + term + " using data structure mode " + this.mode + "...");
        // Search logic goes here
        Node targetNode = new Node(term, this.mode);
        int flag = this.nodeList.search(targetNode);
        List<String> target = null;
        if (flag != -1) {
            target = this.nodeList.get(flag).getReferences();
        }


        //Filter reference list
        //visited urls stored in ArrayList<string> found
        if(target != null) {
            SortedArrayList<String> found = new SortedArrayList<>();
            for(int i = 0; i < target.size(); i++) {
                if(found.search(target.get(i)) == -1) {
                    found.add(target.get(i));
                }
            }
            long endTime = System.nanoTime();
            long time = (endTime - startTime) / 1000;

            //Print Results
            System.out.println("Found " + found.size() + " results in " + time + " microseconds");
            System.out.println("Displaying results for '" + term + "':");
            for(int i = 0; i < found.size(); i++){
                System.out.println(i + ". " + found.get(i));
            }
        }
        return target;
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter mode as in what data structure to use:");
        System.out.println("    1. Array List ");
        System.out.println("    2. Sorted Array List");

        int mode = input.nextInt();

        System.out.println("Building Search Engine...");
        SearchEngine engine = new SearchEngine(mode);

        String answer = "y";
        while (answer.equals("y")) {
            input.nextLine(); // consume the remaining newline character
            System.out.print("Search (enter a term to query): ");
            String term = input.nextLine();
            engine.search(term);
            System.out.print("Would you like to search another term (y/n)? ");
            answer = input.nextLine();
        }
        input.close();
    }
}
