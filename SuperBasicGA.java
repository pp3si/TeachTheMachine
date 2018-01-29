/**
 * I am trying to create a very basic GA to find Hello World
 *
 * Ian Goodwin
 * 1/9/2018
 */
public class SuperBasicGA
{
    /**
     * This method is meant to run the GA using all the other stuff
     */
    public static void main(String [] args) {
        Elf[] collection = new Elf[Population.popSize];
        int index = 0;
        for(int i = 0; i < Population.popSize; i++) {//Generate initial population with random genes
            boolean[] genome = new boolean[5];
            for(int j = 0; j < 5; j++) {
                int geneBit = randInt(0,1);
                if(geneBit == 0) {
                    genome[i] = false;
                } else {
                    genome[i] = true;
                }
            }
            collection[index++] = new Elf(genome);
        }
        Population initial = new Population(collection);
        int bestEverFitness = initial.greatestFitness();
        Population mostRecentGen;
        mostRecentGen = initial;
        while(bestEverFitness != 
        Elf[] generation1Elves = new Elf[Population.popSize];
        for(int i = 0; i < Population.popSize; i++) {
            int parent1Index = randInt(0, initialBreeders.length);
            int parent2Index = randInt(0, initialBreeders.length);
            Elf parentOne = initial.indivs[parent1Index];
            Elf parentTwo = initial.indivs[parent2Index];
            generation1Elves[i] = Population.crossover(parentOne, parentTwo);
        }
        Population gen1 = new Population(generation1Elves);
    }
    public static int randInt(int min, int max) {
        double rand = Math.random();
        double rand2 = rand * (max - min + 1);
        int result = (int)(rand2 + min);
        return result;
    }
}
