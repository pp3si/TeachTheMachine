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
    }
    public static int randInt(int min, int max) {
        double rand = Math.random();
        double rand2 = rand * (max - min + 1);
        int result = (int)(rand2 + min);
        return result;
    }
}
