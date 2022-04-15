import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class BruteForce {
    public static ArrayList<Item> getOptimalSet(ArrayList<Item> items) throws InvalidArgumentException {

        if (items.size() > 10) {
            throw new InvalidArgumentException();
        } else {
            // Base case
            if (items.size() <= 1) {
                return items;
            }

            ArrayList<Item> bestSet = new ArrayList<>();
            // Recursive Step
            for (int i = 0; i < items.size(); i++) {
                ArrayList<Item> copyItems = new ArrayList<>();
                for (Item item : items) {
                    Item newItem = new Item(item);
                    copyItems.add(newItem);
                }
                copyItems.remove(i);
                copyItems = getOptimalSet(copyItems);
                int fitness = getFitness(copyItems);
                if (fitness > getFitness(bestSet)) {
                    bestSet = copyItems;
                }
            }
            if (getFitness(items) > getFitness(bestSet)) {
                bestSet = items;
            }
            return bestSet;
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Item> items = new ArrayList<>();
        items = readData("items.txt");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }

        System.out.println("\nBrute forcing...\n");

        System.out.println(getFitness(getOptimalSet(items)));

    }

    public static int getFitness(ArrayList<Item> items) {
        // Returns the fitness of this chromosome. If the sum of all of the included
        // items' weight are greater than 10, the fitness is zero. Otherwise, the
        // fitness is equal to the sum of all of the included items' values.
        double doubleFitness = 0;
        int totalValue = 0;
        // Checking each item's fitness in the Chromosome that calls this method
        for (Item item : items) {
            doubleFitness += item.getWeight();
            totalValue += item.getValue();
        }
        // If fitness is greater than 10 its fitness is set to zero
        if (doubleFitness > 10.0) {
            return 0;
        }
        return totalValue; // printing out value of the chromosome
    }

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
}
