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
            while(in1 == in2) {
                in1 = SuperBasicGA.randInt(0,19);
                in2 = SuperBasicGA.randInt(0,19);
            }
            Elf e1 = indivs[in1];
            Elf e2 = indivs[in2];
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
    public Elf crossover(Elf parent1, Elf parent2) {
        //
        boolean[] newGene = new boolean[5];
        for(int i = 0; i < newGene.length; i++) {
            int coinFlip = SuperBasicGA.randInt(1,2);
            if(coinFlip == 1) {
                newGene[i] = parent1.gene[i];
            } else {
                newGene[i] = parent2.gene[i];
            }
            double rand = Math.random();
            if(rand <= .05) {//to see if the gene will mutate to a random bit
                coinFlip = SuperBasicGA.randInt(1,2);
                if(coinFlip == 1) {
                    newGene[i] = false;
                } else {
                    newGene[i] = true;
                }
            }
        }
        return new Elf(newGene);
    }
    public Elf[] indivs;
    public final int popSize = 20;
    
}
