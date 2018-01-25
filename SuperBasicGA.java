
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
        
    }
    public static int randInt(int min, int max) {
        double rand = Math.random();
        double rand2 = rand * (max - min + 1);
        int result = (int)(rand2 + min);
        return result;
    }
}
