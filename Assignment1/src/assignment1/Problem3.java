package assignment1;

import java.util.Scanner;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class Problem3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String sentence = in.nextLine();

        MaxentTagger tagger = new MaxentTagger("english-bidirectional-distsim.tagger");
        String taggedString = tagger.tagString(sentence);
        System.out.println(taggedString);
    }
}
