import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {
    // used for random number generation
    private static Random rng;
    public static long dummy = 0;

    public Chromosome() {
        // No arg constructor
        rng = new Random();
    }

    public Chromosome(ArrayList<Item> items) {
        // Adds a copy of each of the items passed in to this Chromosome. Uses a random
        // number to decide whether each item's included field is set to true or false.
        rng = new Random();
        int trueOrFalse;
        for (int i = 0; i < items.size(); i++) {
            trueOrFalse = rng.nextInt(10) + 1; // changing random number each iteration
            Item anItem = new Item(items.get(i));
            if (trueOrFalse == 1) { // Setting isIncluded based on random integer
                anItem.setIncluded(true);
            } else {
                anItem.setIncluded(false);
            }
            this.add(anItem);
        }
    }

    public Chromosome copy() {
        Chromosome newChromosome = new Chromosome();
        for (Item i : this) {
            Item newItem = new Item(i);
            newChromosome.add(newItem);
        }
        return newChromosome;
    }

    public Chromosome crossover(Chromosome other) {
        // Creates and returns a new child chromosome by performing the crossover
        // operation on THIS chromosome and the OTHER one that is passed in (i.e. for
        // each item, use a random number to decide which parent's item should be copied
        // and added to the child).

        Chromosome child = new Chromosome();
        for (int i = 0; i < other.size(); i++) {
            int copyThisOrOther = rng.nextInt(10) + 1;
            if (copyThisOrOther >= 6) { // randomly chooses which parent to take from
                // Child takes from the other Chromosome
                child.add(new Item(other.get(i)));
            } else {
                // Child takes from this Chromosome
                child.add(new Item(this.get(i)));
            }
        }
        return child;
    }

    public void mutate() {
        // Performs the mutation operation on this chromosome (i.e. for each item in
        // this chromosome, use a random number to decide whether or not to flip it's
        // included field from true to false or vice versa)

        // for each item in the mutating chromosome
        for (Item item : this) {
            int flip = rng.nextInt(10) + 1;
            // randomly flipping the isIncluded field
            if (flip == 1) {
                item.setIncluded(!item.isIncluded());// sets included to the opposite of what it was
            }
        }
    }

    public int getFitness() {
        dummy = 0;
        for (int i = 0; i < this.size() * 1000; i++) {
            dummy += i;
        }
        // Returns the fitness of this chromosome. If the sum of all of the included
        // items' weight are greater than 10, the fitness is zero. Otherwise, the
        // fitness is equal to the sum of all of the included items' values.
        double doubleFitness = 0;
        int totalValue = 0;
        // Checking each item's fitness in the Chromosome that calls this method
        for (Item item : this) {
            // making sure the item isIncluded
            if (item.isIncluded()) {
                doubleFitness += item.getWeight();
                totalValue += item.getValue();
            }
        }
        // If fitness is greater than 10 its fitness is set to zero
        if (doubleFitness > 10.0) {
            return 0;
        }
        return totalValue; // printing out value of the chromosome
    }

    public int compareTo(Chromosome other) {
        // returns -1 if THIS chromosome's fitness is greater than the OTHER's fitness,
        // +1 if THIS chromosome's fitness is less than the OTHER one's, and 0 if their
        // fitness is the same.
        if (this.getFitness() > other.getFitness()) {
            return -1;
        } else if (this.getFitness() < other.getFitness()) {
            return 1;
        } else {
            return 0;
        }
    }

    public String toString() {
        // Displays the name, weight, and value of all items in this chromosome whose
        // included value is true, followed by the fitness of this chromosome.
        String allTheIncludedItems = "This Chromosome has these items: ";
        for (Item item : this) {
            if (item.isIncluded()) {
                allTheIncludedItems += item.toString() + " ";
            }
        }
        allTheIncludedItems += "\nThis Chromosome's fitness: " + this.getFitness();
        return allTheIncludedItems;
    }
}
