import java.util.ArrayList;
import java.util.Collections;

public class geneticThread extends Thread {
    private ArrayList<Chromosome> chromosomes;

    public geneticThread(ArrayList<Chromosome> c) {
        chromosomes = new ArrayList<>();
        for (int i = 0; i < c.size(); i++) {
            chromosomes.add(c.get(i).copy());
        }
    }

    public void run() {
        for (int j = 0; j < GeneticAlgorithm.NUM_EPOCHS / GeneticAlgorithm.NUM_THREADS; j++) {
            // Creating next generation of Chromosomes
            ArrayList<Chromosome> nextGenChromosomes = new ArrayList<>();
            // Adding current population to the next generation
            for (int i = 0; i < chromosomes.size(); i++) {
                nextGenChromosomes.add(chromosomes.get(i));
            }
            Collections.shuffle(nextGenChromosomes);
            for (int i = 0; i < chromosomes.size(); i += 2) {
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
            chromosomes.clear();
            // Adding top 10 of the next generation to the current generation
            for (int i = 0; i < 10; i++) {
                chromosomes.add(nextGenChromosomes.get(i));
            }
        }
    }

    public ArrayList<Chromosome> getChromosomes() {
        return chromosomes;
    }
}
