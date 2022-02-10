import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GeneticAlgorithm {

    static ArrayList<Item> items = new ArrayList<Item>();

    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        // Reads in a data file with the format shown below and creates and returns an
        // ArrayList of Item objects.

        File file = new File(filename);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] tokens = line.split(", ");
            String name = tokens[0];
            double weight = Double.parseDouble(tokens[1]);
            int value = Integer.parseInt(tokens[2]);
            Item thisItem = new Item(name, weight, value);
            items.add(thisItem);
            // System.out.println("Initial items added from file: " + thisItem);
        }
        sc.close();
        return items;
    }

    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) {
        // Creates and returns an ArrayList of populationSize Chromosome objects that
        // each contain the items, with their included field randomly set to true or
        // false.
        ArrayList<Chromosome> arrayListChromosomes = new ArrayList<Chromosome>(populationSize);
        for (int i = 0; i < populationSize; i++) {
            Chromosome thisChromosome = new Chromosome(items);
            arrayListChromosomes.add(thisChromosome);
        }
        // System.out.println(arrayListChromosomes);
        return arrayListChromosomes;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Reads the data about the items in from a file called items.txt and performs
        // the steps described in the RUNNING THE GENETIC ALGORITHM section of the
        // project pdf.
        ArrayList<Chromosome> arrayListChromosomes = new ArrayList<Chromosome>();
        // Reads the data and initializes the population in an ArrayList<Chromosome>,
        // Also catches FileNotFoundException
        try {
            arrayListChromosomes = initializePopulation(readData("items.txt"), 10);
        } catch (FileNotFoundException e) {
            System.out.println("Error: file is not in directory");
        }
        // Creating next generation of Chromosomes
        ArrayList<Chromosome> nextGenChromosomes = new ArrayList<Chromosome>(30);
        // Adding current population to the next generation
        nextGenChromosomes = arrayListChromosomes;
        for (int i = 0; i < nextGenChromosomes.size(); i++) {
            nextGenChromosomes.get(i).crossover(nextGenChromosomes.get(i));
        }
    }
}
