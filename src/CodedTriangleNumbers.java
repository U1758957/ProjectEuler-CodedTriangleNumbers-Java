import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class to find coded triangle numbers in a given file input
 */
public class CodedTriangleNumbers {

    private static Map<String, Integer> alphabetValue = new HashMap<>();
    private static List<Integer> triangleNumbers = new ArrayList<>();

    /**
     * Initialises the alphabetValue HashMap, and the triangleNumbers ArrayList.
     */
    private static void initialise() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int sumOfAlphabet = 0;
        int triangleIndex = 1;
        int currentTriangle = 0;
        for (int x = 0; x < alphabet.length(); x++) {
            alphabetValue.put(String.valueOf(alphabet.charAt(x)), x + 1);
            sumOfAlphabet += x + 1;
        }
        while (currentTriangle < sumOfAlphabet) {
            currentTriangle = triangleNumber(triangleIndex);
            triangleIndex++;
            triangleNumbers.add(currentTriangle);
        }
    }

    /**
     * Calculates triangle numbers
     *
     * @param n the nth triangle number to calculate
     * @return a triangle number
     */
    private static int triangleNumber(int n) {
        return n * (n + 1) / 2;
    }

    /**
     * Read the file and output it as a nice array of strings
     *
     * @return the file content as a String array
     * @throws IOException if for some reason the file cannot be read
     */
    private static String[] getTextToSearch() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("p042_words.txt")));
        String content = reader.readLine();
        return content.replace("\"", "").split(",");
    }

    /**
     * Find the amount of triangle words from an array of words
     *
     * @param words the words to check
     * @return the count of triangle words
     */
    private static int amountOfTriangles(String[] words) {
        int sum;
        int triangles = 0;
        for (String word : words) {
            sum = 0;
            for (int x = 0; x < word.length(); x++) {
                sum += alphabetValue.get(String.valueOf(word.charAt(x)));
            }
            if (triangleNumbers.contains(sum)) triangles++;

        }
        return triangles;
    }

    /**
     * The main method, initialises the map and triangle numbers, then outputs an answer
     *
     * @param args unused
     * @throws IOException if for some reason the file cannot be read
     */
    public static void main(String[] args) throws IOException {
        initialise();
        System.out.println("There're " + amountOfTriangles(getTextToSearch()) + " triangle words from the given input");
    }

}
