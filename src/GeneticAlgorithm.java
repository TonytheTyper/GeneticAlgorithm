import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GeneticAlgorithm {
    public static final int POP_SIZE = 100;
    public static final int NUM_EPOCHS = 1000;
    public static final int NUM_THREADS = 1;
    // 6 Threads:
    // Average fitness between 7400-7600
    // Average time between 800-1000ms

    // 4 Threads:
    // Average fitness between 7400-7600
    // Average time between 800-1000ms

    // 2 Threads:
    // Average fitness between 7400-7600
    // Average time between 1150-1400ms

    // 1 Thread:
    // Average fitness between 7400-7600
    // Average time between 2000-2200ms

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

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        long start = System.currentTimeMillis();
        // Reads the data about the items in from a file called items.txt and performs
        // the steps described in the RUNNING THE GENETIC ALGORITHM section of the
        // project pdf.
        ArrayList<Chromosome> arrayListChromosomes = new ArrayList<>();
        // Reads the data and initializes the population in an ArrayList<Chromosome>,
        // Also catches FileNotFoundException
        try {
            arrayListChromosomes = initializePopulation(readData("more_items.txt"), POP_SIZE);
        } catch (FileNotFoundException e) {
            System.out.println("Error: file is not in directory");
        }

        // Creating threads
        geneticThread[] threads = new geneticThread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new geneticThread(arrayListChromosomes);
            threads[i].start();
        }

        // Joining Threads
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
        }

        // Printing fittest individual
        Chromosome fittestChromosome = new Chromosome();
        for (int i = 0; i < threads.length; i++) {
            Collections.sort(threads[i].getChromosomes());
            if (threads[i].getChromosomes().get(0).getFitness() > fittestChromosome.getFitness()) {
                fittestChromosome = threads[i].getChromosomes().get(0);
            }
        }
        System.out.println(fittestChromosome);

        // Printing out time it took to run the threads
        long end = System.currentTimeMillis();
        System.out.println(end - start + " milliseconds");
    }
}
