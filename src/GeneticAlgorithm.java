import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GeneticAlgorithm {
    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        // Reads in a data file with the format shown below and creates and returns an
        // ArrayList of Item objects.
        File f = new File(filename);
        Scanner sc = new Scanner(f);
        sc.useDelimiter("\\n");
        ArrayList<Item> items = new ArrayList<Item>();
        while (sc.hasNextLine()) {
            items.add(sc.next());
            System.out.println(items);
            sc.close();
        }
        return items;
    }

    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) {
        // Creates and returns an ArrayList of populationSize Chromosome objects that
        // each contain the items, with their included field randomly set to true or
        // false.
        ArrayList<Chromosome> Chromosomes = new ArrayList<Chromosome>(populationSize);
        return Chromosomes;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Reads the data about the items in from a file called items.txt and performs
        // the steps described in the RUNNING THE GENETIC ALGORITHM section of the
        // project pdf.

        initializePopulation(readData("items.txt"), 10);
    }
}
