
/**
 * An elf is the individual I'm using for my GA.
 *
 * Ian Goodwin
 * 1/9/2018
 */
public class Elf
{
    // instance variables - replace the example below with your own
    public boolean[] gene;
    public int test;
    //We're just trying to match the object's genes to five trues - our ideal solution

    /**
     * Constructor for objects of class Elf
     */
    public Elf(boolean[] inputs) {
        // initialise instance variables
        boolean[] temp = new boolean[inputs.length];
        for(int i = 0; i < temp.length; i++) {
            temp[i] = inputs[i];
        }
        gene = temp;
    }

    /**
     * This method assigns a fitness score to an Elf
     *
     * No parameters, just works with the stuff we know about the elf
     * return    the fitness score for the Elf
     */
    public int getFitness() {
        int sum = 0;
        
    }
}
