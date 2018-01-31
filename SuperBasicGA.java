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
            boolean[] genome = new boolean[Elf.maxFitness];
            for(int j = 0; j < Elf.maxFitness; j++) {
                int geneBit = randInt(0,1);
                if(geneBit == 0) {
                    genome[j] = false;
                } else {
                    genome[j] = true;
                }
            }
            collection[index++] = new Elf(genome);
        }
        Population initial = new Population(collection);
        int bestEverFitness = initial.greatestFitness();
        Population mostRecentGen;
        mostRecentGen = initial;
        Elf[] nextGenElves = new Elf[Population.popSize];
        int numGenerations = 1;
        /*("Generation " + numGenerations + ": Best individual has fitness ");
        System.out.println(mostRecentGen.greatestFitness());
        System.out.print("That individual's genes were ");
        mostRecentGen.bestIndiv().display();*/
        while(bestEverFitness < Elf.maxFitness) {
            Elf[] breeders = mostRecentGen.chooseBreeders();
            for(int i = 0; i < Population.popSize; i++) {
                int parent1Index = randInt(0, breeders.length - 1);
                int parent2Index = randInt(0, breeders.length - 1);
                Elf parentOne = breeders[parent1Index];
                Elf parentTwo = breeders[parent2Index];
                nextGenElves[i] = Population.crossover(parentOne, parentTwo);
            }
            Population nextGen = new Population(nextGenElves);
            numGenerations++;
            if(nextGen.greatestFitness() > bestEverFitness) {
                bestEverFitness = nextGen.greatestFitness();
                System.out.print("New best ever fitness is "+bestEverFitness);
                System.out.println(" in generation "+numGenerations);
            }
            mostRecentGen = nextGen;
            /*System.out.print("Generation " + numGenerations + ": Best individual has fitness ");
            System.out.println(mostRecentGen.greatestFitness());
            System.out.print("That individual's genes were ");
            mostRecentGen.bestIndiv().display();*/
        }
        System.out.println("Generations required to achieve max fitness: " + numGenerations);
    }
    public static int randInt(int min, int max) {
        double rand = Math.random();
        double rand2 = rand * (max - min + 1);
        int result = (int)(rand2 + min);
        return result;
    }
}
