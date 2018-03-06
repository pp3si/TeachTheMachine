
/**
 * 
 * An attempt to make hex color codes a vector space for easy computation of distance. (int mult only)
 * 
 * Ian Goodwin
 * 2/6/2018
 * 
 */
public class Color
{
    // instance variables - replace the example below with your own
    public int[] code = new int[6];
    
    public final static Color ZERO = new Color(0, 0, 0, 0, 0, 0);
    /**
     * Constructor for objects of class Color
     */
    public Color(int [] ref) {
        code = ref;
    }
    public Color(int a, int b, int c, int d, int e, int f) {
        code[0] = a;
        code[1] = b;
        code[2] = c;
        code[3] = d;
        code[4] = e;
        code[5] = f;
    }
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static Color add(Color A, Color B) {
        int [] tempCode = new int[6];
        for(int i = 0; i < 6; i++) {
            int tempSum = A.code[i] + B.code[i];
            if(tempSum > 15) {
                tempSum = tempSum % 16;
            }
            if(tempSum < 0) {
                tempSum += 16;
            }
            tempCode[i] = tempSum;
        }
        return new Color(tempCode);
    }
    public static Color multiply(Color A, int k) {
        int [] tempCode = new int[6];
        for(int i = 0; i < 6; i++) {
            int tempSum = A.code[i]*k;
            if(tempSum > 15) {
                tempSum = tempSum % 16;
            }
            if(tempSum < 0) {
                tempSum += 16;
            }
            tempCode[i] = tempSum;
        }
        return new Color(tempCode);
    }
    private static boolean equals(Color A, Color B) {
        for(int i = 0; i < 6; i++) {
            if(A.code[i] != B.code[i]) {
                return false;
            }
        }
        return true;
    }
    public void print() {
        for(int i = 0; i < 6; i++) {
            System.out.print(Integer.toHexString(code[i]));
        }
        System.out.println("");
    }
    /*public static int innerProduct(Color A, Color B) {
        int sum = 0;
        Color Temp = add(A, B);
        for(int i = 0; i < 6; i++) {
            sum += (Temp.code[i]);
        }
        return sum;
    }*/
    public static double distance(Color A, Color B) {
        double sum = 0;
        for(int i = 0; i < 6; i++) {
            int temp = A.code[i] - B.code[i];
            sum += Math.pow(temp,2);
        }
        double answer = Math.sqrt(sum);
        return answer;
    }
    public static void testDistance() {
        Color testA = ZERO;
        int[] yarg = {15, 15, 15, 15, 15, 15};
        Color testB = new Color(yarg);
        System.out.println(distance(testA, testB));
        int[] blarg = {1, 1, 0, 1, 2, 1};
        Color testC = new Color(blarg);
        int[] flarg = {15, 15, 0, 0, 0, 0};
        Color testD = new Color(flarg);
        System.out.println(distance(testC, testA));
    }
    /*public static boolean testInnerProduct() {
        int[] blarg = {2, 12, 9, 3, 11, 0};
        Color testA = new Color(blarg);
        int[] yarg = {13, 5, 5, 3, 8, 4};
        Color testB = new Color(yarg);
        int[] flarg = {7, 14, 15, 6, 12, 11};
        Color testC = new Color(flarg);
        int[] zero = {0, 0, 0, 0, 0, 0};
        final Color ZERO = new Color(zero);
        int Test = innerProduct(testB, testC);
        int Test2 = innerProduct(testC, testB);
        if(Test != Test2) {
            System.out.println("Fails symmetry");
            return false;
        }
        testA.print();
        testB.print();
        add(testA, testB).print();
        Test = innerProduct(add(testA, testB), testC);
        Test2 = innerProduct(testA, testC) + innerProduct(testB, testC);
        System.out.println(Test);
        System.out.println(Test2);
        if(Test != Test2) {
            System.out.println("Fails additivity");
            return false;
        }
        Test = innerProduct(multiply(testC, 2), testB);
        Test2 = 2 * innerProduct(testC, testB);
        if(Test != Test2) {
            System.out.println("Fails homogeneity");
            return false;
        }
        Test = innerProduct(testA, testA);
        if(Test < 0) {
            System.out.println("Fails positivity");
            return false;
        }
        //Must verify second part on own
        return true;
    }*/
    public static boolean testAxioms() {
        int[] blarg = {2, 12, 9, 3, 11, 0};
        Color testA = new Color(blarg);
        int[] yarg = {13, 5, 5, 3, 8, 4};
        Color testB = new Color(yarg);
        int[] flarg = {7, 14, 15, 6, 12, 11};
        Color testC = new Color(flarg);
        int[] zero = {0, 0, 0, 0, 0, 0};
        final Color ZERO = new Color(zero);
        
        Color Test = add(testA, testB);
        for(int i = 0; i < 6; i++) {
            if(Test.code[i] < 0 || Test.code[i] > 15) {
                System.out.println("Failure to complete axiom 1: Addition closure");
                return false;
            }
        }
        Color Test2 = add(testB, testA);
        if(!equals(Test, Test2)) {
            System.out.println("Failure to complete axiom 2: Commutativity for addition");
            return false;
        }
        Test = add(testA, add(testB, testC));
        Test2 = add(add(testA, testB), testC);
        if(!equals(Test, Test2)) {
            System.out.println("Failure to complete axiom 3: Associativity for addition");
            return false;
        }
        Test = add(ZERO, testB);
        if(!equals(Test, testB)) {
            System.out.println("Failure to complete axiom 4: Additive identity");
            return false;
        }
        Test = multiply(testC, -1);
        Test2 = add(testC, Test);
        if(!equals(Test2, ZERO)) {
            System.out.println("Failure to complete axiom 5: Additive inverse");
            return false;
        }
        Test = multiply(testA, 3);
        for(int i = 0; i < 6; i++) {
            if(Test.code[i] < 0 || Test.code[i] > 15) {
                System.out.println("Failure to complete axiom 6: Multiplication closure");
                return false;
            }
        }
        Test = multiply(add(testA, testC), 5);
        Test2 = add(multiply(testA, 5), multiply(testC, 5));
        if(!equals(Test, Test2)) {
            System.out.println("Failure to complete axiom 7: Distributive property");
            return false;
        }
        Test = multiply(testB, 7);
        Test2 = add(multiply(testB, 2), multiply(testB, 5));
        if(!equals(Test, Test2)) {
            System.out.println("Failure to complete axiom 8: Other distributive property");
            return false;
        }
        Test = multiply(testB, 21);
        Test2 = multiply(multiply(testB, 3), 7);
        if(!equals(Test, Test2)) {
            System.out.println("Failure to complete axiom 9: Multiplicative associativity");
            return false;
        }
        Test = multiply(testC, 1);
        if(!equals(Test, testC)) {
            System.out.println("Failure to complete axiom 10: Multiplicative identity");
            return false;
        }
        return true;
    }
}
