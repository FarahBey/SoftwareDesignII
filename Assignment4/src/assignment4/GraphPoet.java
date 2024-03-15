
package assignment4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GraphPoet {


    /**
     *
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    ArrayList<Vertex> digraph = new ArrayList<>();
    public GraphPoet(File corpus) throws IOException {

        /* Read in the File and place into graph here */
        //make sure to throw exception if not a readable file
        //instantiate a new graph then
        if (!corpus.isFile() || !corpus.canRead()) {
            throw new IOException("File is not readable.");
        }

        Vertex<String> endWord = new Vertex<>();
        Scanner in = new Scanner(corpus);

        String words = in.nextLine();
        while(in.hasNextLine()){
            words += " " + in.nextLine();
        }
        String[] splitWords = words.toLowerCase().split("\\s+");//add if more spaces
        for(int i = 0; i < splitWords.length - 1; i++){
            Vertex<String> word1;
            Vertex<String> word2;
//                // how to add last element of line
//                if(i == 0 && iter > 0){
//                   word1 = endWord;
//                   word2 = new Vertex<>(splitWords[i]);
//                }
//                else {
                    word1 = new Vertex<>(splitWords[i]);
                    word2 = new Vertex<>(splitWords[i + 1]);
                //}
                //endWord = word2;
                if(digraph.contains(word1)){
                    digraph.get(digraph.indexOf(word1)).addEdge(word2.getName());

                }
                else{
                    word1.addEdge(word2.getName());
                    digraph.add(word1);
                }
            }
            endWord = new Vertex<>(splitWords[splitWords.length - 1]);
            digraph.add(endWord);

        //for testing
//        for(int i = 0; i < digraph.size(); i++){
//            System.out.println(digraph.get(i));
//        }


    }

    /**
     * Generate a poem.
     *
     * @param input File from which to create the poem
     * @return poem (as described above)
     */
    public String poem(File input) throws IOException {

        if (!input.isFile() || !input.canRead()) {
            throw new IOException("File is not readable.");
        }

        LinkedList<String> arraypoem = new LinkedList<>();
        Scanner in = new Scanner(input);
        String words = in.nextLine();
        while(in.hasNextLine()){
            words += " " + in.nextLine();
        }
        String[] splitWords = words.toLowerCase().split("\\s+");
        int i = 0;

        while(i < splitWords.length ){
            arraypoem.add(i,splitWords[i]);
            i++;
            }

        for(int x = 0; x < arraypoem.size() - 1; x++){
            Vertex<String> first = new Vertex<>(arraypoem.get(x));
            Vertex<String> second = new Vertex<>(arraypoem.get(x + 1));
            if(digraph.contains(first)){
                    //get values and keys
                    Set<String> keys = digraph.get(digraph.indexOf(first)).getEdgesKey();
                    Collection<Integer> values = digraph.get(digraph.indexOf(first)).getEdgesValue();
                    ArrayList<String> keys1 = new ArrayList<>();
                    ArrayList<Integer> values1 = new ArrayList<>();

                    if(!keys.isEmpty()) {

                        for (String a : keys) {
                            keys1.add(a);
                        }
                        for (Integer a : values) {
                            values1.add(a);
                        }
                    }
                    String wordBetween = " ";
                    int priority = 0;
                    int hold = 0;
                    for(int k = 0; k < keys1.size() ; k++) {
                        ArrayList<String> keys3 = new ArrayList<>();
                        ArrayList<Integer> values3 = new ArrayList<>();
                        //if (!((digraph.get(digraph.indexOf(keys1.get(k)))).getEdgesKey()).isEmpty()) {
                        Vertex<String> w1 = new Vertex<>(keys1.get(k));
                            Set<String> keys2 = digraph.get(digraph.indexOf(w1)).getEdgesKey();
                            Collection<Integer> values2 = digraph.get(digraph.indexOf(w1)).getEdgesValue();

                        if(!keys2.isEmpty()){
                            for (String a : keys2) {
                                keys3.add(a);
                            }
                            for (Integer a : values2) {
                                values3.add(a);
                            }
                        }
                        while(hold < keys3.size()) {
                            if (keys3.get(hold).equals(second.getName())) {
                                if (priority < (values1.get(k) + values3.get(hold))) {
                                    priority = values1.get(k) + values3.get(hold);
                                    wordBetween = keys1.get(k);
                                    //System.out.println(wordBetween);
                                }
                            }
                            hold++;
                        }
                    }
                if(wordBetween != " ") {
                    arraypoem.add(x + 1, wordBetween);
                    x++;
                }
            }
        }
        /* Read in input and use graph to complete poem */
        String poem = "";
        for(int f = 0; f < arraypoem.size(); f++) {
            poem = poem + arraypoem.get(f) + " ";
        }
        return poem;
    }

}
