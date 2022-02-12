import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {
    // used for random number generation
    private static Random rng;

    public Chromosome() {
        // No arg constructor
    }

    public Chromosome(ArrayList<Item> items) {
        // Adds a copy of each of the items passed in to this Chromosome. Uses a random
        // number to decide whether each item's included field is set to true or false.
        rng = new Random();
        Item anItem;
        int trueOrFalse;
        for (int i = 0; i < items.size(); i++) {
            trueOrFalse = rng.nextInt(items.size()); // changing random number each iteration
            anItem = new Item(items.get(i));
            if (trueOrFalse > (items.size() / 2)) { // Setting isIncluded based on randomly being above or below the
                                                    // midpoint of items ArrayList
                anItem.setIncluded(true);
            } else {
                anItem.setIncluded(false);
            }
            add(anItem);
            // System.out.println(anItem);
            // System.out.println(anItem.isIncluded());
        }
    }

    public Chromosome crossover(Chromosome other) {
        // Creates and returns a new child chromosome by performing the crossover
        // operation on THIS chromosome and the OTHER one that is passed in (i.e. for
        // each item, use a random number to decide which parent's item should be copied
        // and added to the child).
        rng = new Random();
        int copyThisOrOther = rng.nextInt(other.size());
        Chromosome child;
        if (copyThisOrOther > (other.size() / 2)) {
            // Child has the other takes from the other Chromosome
            child = new Chromosome(other);
            // System.out.println("The other child was taken: " + child + ". This child was
            // not: " + this);
        } else {
            // Child has the other takes from this Chromosome
            child = new Chromosome(this);
            // System.out.println("This child was taken: " + child + ". The other was not: "
            // + other);
        }
        // System.out.println("Returning child: " + child);
        return child;
    }

    public void mutate() {
        // Performs the mutation operation on this chromosome (i.e. for each item in
        // this chromosome, use a random number to decide whether or not to flip it's
        // included field from true to false or vice versa)
        rng = new Random();
        Chromosome mutatingChromosome = new Chromosome(this);
        int flip = rng.nextInt(this.size());

        // for each item in the mutating chromosome
        for (Item item : mutatingChromosome) {
            // randomly flipping the isIncluded field
            if (flip > (this.size() / 2)) {
                if (item.isIncluded()) {// if it is included
                    item.setIncluded(false);// flip to false
                } else {// if it wasn't included
                    item.setIncluded(true);// flip to true
                }
            }
        }
    }

    public int getFitness() {
        // Returns the fitness of this chromosome. If the sum of all of the included
        // items' weight are greater than 10, the fitness is zero. Otherwise, the
        // fitness is equal to the sum of all of the included items' values.
        double doubleFitness = 0;
        Chromosome fitnessChromosome = new Chromosome(this);
        // Checking each item's fitness in the Chromosome that calls this method
        for (Item item : fitnessChromosome) {
            // making sure the item isIncluded
            if (item.isIncluded()) {
                doubleFitness += item.getWeight();
            }
        }
        // If fitness is greater than 10 its fitness is set to zero
        if (doubleFitness > 10.0) {
            doubleFitness = 0;
        }
        int fitness = (int) Math.round(doubleFitness);
        return fitness;
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
        allTheIncludedItems += "\nThis Chromosome's fitness: " + this.getFitness() + "\n";
        // Item item;
        // for (int i = 0; i < this.size(); i++) {
        // item = new Item(this.get(i));
        // if (item.isIncluded()) {
        // return /* "toString " + */ item.toString();
        // }
        // }
        return allTheIncludedItems;
    }
}
