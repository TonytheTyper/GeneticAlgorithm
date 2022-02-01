import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {
    private static Random rng;
    // used for random number generation

    public Chromosome() {
        // No arg constructor
    }

    public Chromosome(ArrayList<Item> items) {
        // Adds a copy of each of the items passed in to this Chromosome. Uses a random
        // number to decide whether each item's included field is set to true or false.
    }

    public Chromosome crossover(Chromosome other) {
        return other;
        // Creates and returns a new child chromosome by performing the crossover
        // operation on THIS chromosome and the OTHER one that is passed in (i.e. for
        // each item, use a random number to decide which parent's item should be copied
        // and added to the child).

    }

    public void mutate() {
        // performs the mutation operation on this chromosome (i.e. for each item in
        // this chromosome, use a random number to decide whether or not to flip it's
        // included field from true to false or vice versa)
    }

    public int getFitness() {
        // Returns the fitness of this chromosome. If the sum of all of the included
        // items' weight are greater than 10, the fitness is zero. Otherwise, the
        // fitness is equal to the sum of all of the included items' values.
        return fitness;
    }

    public int compareTo(Chromosome other) {
        // returns -1 if THIS chromosome's fitness is greater than the OTHER's fitness,
        // +1 if THIS chromosome's fitness is less than the OTHER one's, and 0 if their
        // fitness is the same.
    }

    public String toString() {
        // Displays the name, weight, and value of all items in this chromosome whose
        // included value is true, followed by the fitness of this chromosome.
    }
}