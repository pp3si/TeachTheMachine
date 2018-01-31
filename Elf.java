/**
 * An elf is the individual I'm using for my GA.
 *
 * Ian Goodwin
 * 1/17/2018
 */
public class Elf
{
    public boolean[] gene;
    //We're just trying to match the object's genes to five trues - our ideal solution
    public static final double mutationRate = .05;
    public static final int maxFitness = 5;
    //can't set a matrix = to {stuff, stuff, stuff}; only in declaration
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
     * returns the fitness score for the Elf (max 5, min 0)
     */
    public int getFitness() {
        int sum = 0;
        for(int i = 0; i < gene.length; i++) {
            if(gene[i]) {
                sum++;
            }
        }
        return sum;
    }
    public void display() {
        for(int i = 0; i < maxFitness; i++) {
            System.out.print(gene[i] + " ");
        }
        System.out.println("");
    }
}
