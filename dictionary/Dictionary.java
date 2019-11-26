package dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Dictionary {

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br;
        Scanner s = new Scanner(System.in);
        String word = "";
        String line = "";
        String missingWord = "";
        int numberOfWordsNotFound = 0;
        boolean contains = true;
        File file = new File("C:\\\\Users\\\\yaoli\\\\0Grade 11 Compsci\\\\Dictionary\\\\src\\\\dictionary\\\\dictionary.txt");
        List<String> dictionary = new ArrayList<String>();
        BufferedReader inputStream = null;

        System.out.println("Please enter your sentence!");
        word = s.nextLine();
        word = word.replaceAll("[^\\w]", " ").trim();
        String[] words = word.split("\\s+");
        try {
            inputStream = new BufferedReader(new FileReader(file));
            while (true) {
                line = inputStream.readLine();
                if (line == null) {
                    break;
                }
                dictionary.add(line.toLowerCase());
            }
            for (String word1 : words) {
                if (dictionary.contains(word1.toLowerCase()) == false) {
                    missingWord += " [" + word1 + "]";
                    numberOfWordsNotFound++;
                    contains = false;
                }
            }
            if (contains) {
                System.out.println("All words are found!!");
                System.out.println(word);
            } else {
                System.out.println(numberOfWordsNotFound + ((numberOfWordsNotFound > 1) ? " words are not found!!" : " word is not found!!"));
                System.out.println("\"" + missingWord + ((numberOfWordsNotFound > 1) ? "\" are not found!!" : "\" is not found!!"));
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Error opening file");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}