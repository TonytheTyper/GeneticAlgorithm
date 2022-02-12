import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GeneticAlgorithm {

    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        // Reads in a data file with the format shown below and creates and returns an
        // ArrayList of Item objects.

        ArrayList<Item> items = new ArrayList<>();

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
        }
        sc.close();
        return items;
    }

    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) {
        // Creates and returns an ArrayList of populationSize Chromosome objects that
        // each contain the items, with their included field randomly set to true or
        // false.
        ArrayList<Chromosome> arrayListChromosomes = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            Chromosome thisChromosome = new Chromosome(items);
            arrayListChromosomes.add(thisChromosome);
        }
        return arrayListChromosomes;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Reads the data about the items in from a file called items.txt and performs
        // the steps described in the RUNNING THE GENETIC ALGORITHM section of the
        // project pdf.
        ArrayList<Chromosome> arrayListChromosomes = new ArrayList<>();
        // Reads the data and initializes the population in an ArrayList<Chromosome>,
        // Also catches FileNotFoundException
        try {
            arrayListChromosomes = initializePopulation(readData("more_items.txt"), 100);
        } catch (FileNotFoundException e) {
            System.out.println("Error: file is not in directory");
        }
        for (int j = 0; j < 5000; j++) {
            // Creating next generation of Chromosomes
            ArrayList<Chromosome> nextGenChromosomes = new ArrayList<>();
            // Adding current population to the next generation
            for (int i = 0; i < arrayListChromosomes.size(); i++) {
                nextGenChromosomes.add(arrayListChromosomes.get(i));
            }
            Collections.shuffle(nextGenChromosomes);
            int size = arrayListChromosomes.size();
            for (int i = 0; i < size; i += 2) {
                Chromosome child = new Chromosome();
                // randomly choosing individuals in each generation
                child = nextGenChromosomes.get(i).crossover(nextGenChromosomes.get(i + 1));
                nextGenChromosomes.add(child);
            }
            // Mutating 10% of population
            Collections.shuffle(nextGenChromosomes);
            for (int i = 0; i < (nextGenChromosomes.size() * 0.1); i++) {
                nextGenChromosomes.get(i).mutate();
            }
            // Sorting Chromosomes according to fitness
            Collections.sort(nextGenChromosomes);
            // Clearing out current generation
            arrayListChromosomes.clear();
            // Adding top 10 of the next generation to the current generation
            for (int i = 0; i < 10; i++) {
                arrayListChromosomes.add(nextGenChromosomes.get(i));
            }
        }
        // Printing fittest individual
        Collections.sort(arrayListChromosomes);
        System.out.println(arrayListChromosomes.get(0));
    }
}
