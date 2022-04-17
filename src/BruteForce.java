import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class BruteForce {
    public static ArrayList<Item> getOptimalSet(ArrayList<Item> items) throws InvalidArgumentException {

        // Making sure the size of items passed in doesn't exceed 10
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
                // Copying the arraylist so that you can remove an item while looping
                // through items
                ArrayList<Item> copyItems = new ArrayList<>();
                for (Item item : items) {
                    Item newItem = new Item(item);
                    copyItems.add(newItem);
                }
                // Removing an item from copyItems before performing recursion
                copyItems.remove(i);
                copyItems = getOptimalSet(copyItems);
                // Going through each iteration and storing the best
                // set from the copyItems Array based on fitness
                int fitness = getFitness(copyItems);
                if (fitness > getFitness(bestSet)) {
                    bestSet = copyItems;
                }
            }
            // After loop is finished it will check which arraylist has the best fitness
            // the list that is passed in to the method or the best set found from the loop
            if (getFitness(items) > getFitness(bestSet)) {
                bestSet = items;
            }
            return bestSet;
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Item> items = new ArrayList<>();
        items = readData("items.txt");

        System.out.println("\nBrute forcing...\n");
        items = getOptimalSet(items);
        int fitness = getFitness(items);

        System.out.println("The best fit chromosome is worth $" + fitness + " and has these items:\n" + items);

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
