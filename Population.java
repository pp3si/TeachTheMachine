/**
 * A collection of Elves.
 *
 * Ian H. Goodwin
 * 1/17/2018
 */
public class Population
{
    /**
     * Constructor for objects of class Population
     */
    public Population(Elf[] inputs)
    {
        //Add an error message if inputs doesn't have length 20?
        Elf[] temp = new Elf[inputs.length];
        for(int i = 0; i < temp.length; i++) {
            temp[i] = inputs[i];
        }
        indivs = temp;
    }
    /**
     * Chooses 10 random pairs of Elves in the population, takes the more fit of each, and
     * returns them as the breeding pool (allows an individual Elf to be in there more than once)
     */
    public Elf[] chooseBreeders()
    {
        Elf[] breedPool = new Elf[popSize / 2];
        for(int i = 0; i < (popSize / 2); i++) {
            int in1 = -1;
            int in2 = -1;
            //If they don't get change, it won't work
            while(in1 == in2) {
                in1 = SuperBasicGA.randInt(0, Population.popSize - 1);
                in2 = SuperBasicGA.randInt(0, Population.popSize - 1);
                //We won't choose between the same Elf, so we ensure they're different
                //By random-picking an index for each until they're not the same
            }
            Elf e1 = indivs[in1];
            Elf e2 = indivs[in2];
            
            //Chooses the more fit and puts it into the parents pool
            if(e1.getFitness() > e2.getFitness()) {
                breedPool[i] = e1;
            } else if(e1.getFitness() < e2.getFitness()) {
                breedPool[i] = e2;
            } else {
                int coinFlip = SuperBasicGA.randInt(1,2);
                if(coinFlip == 1) {
                    breedPool[i] = e1;
                } else {
                    breedPool[i] = e2;
                }
            }
        }
        return breedPool;
    }
    public static Elf crossover(Elf parent1, Elf parent2) {
        //Takes two parents (selected randomly from the breeding pool) and creates a child
        boolean[] newGene = new boolean[5];
        for(int i = 0; i < newGene.length; i++) {
            int coinFlip = SuperBasicGA.randInt(1,2);
            if(coinFlip == 1) {
                newGene[i] = parent1.gene[i];
            } else {
                newGene[i] = parent2.gene[i];
            }
            //For each gene, there is an equal chance it will inherit from each parent
            
            double rand = Math.random();
            if(rand <= .05) {//to see if the gene will mutate to a random bit
                coinFlip = SuperBasicGA.randInt(1,2);
                if(coinFlip == 1) {
                    newGene[i] = false;
                } else {
                    newGene[i] = true;
                }
            }
            //There's then a 5% chance/gene that it will become a randomly determined bit
        }
        return new Elf(newGene);
    }
    public int greatestFitness() {//Displays the greatest fitness of a population
        //This allows us to track how well successive populations are doing at finding the solution
        int max = 0;
        for(int i = 0; i < popSize; i++) {
            if(indivs[i].getFitness() > max) {
                max = indivs[i].getFitness();
            }
        }
        return max;
    }
    public Elf bestIndiv() {
        for(int i = 0; i < popSize; i++) {
             if(indivs[i].getFitness() == greatestFitness()) {
                 return indivs[i];
             }
        }
        System.out.println("bestIndiv gave an error");
        return indivs[0];
    }
    public Elf[] indivs;
    public static final int popSize = 20;
}
