package numbers;

public class Print {

    static void printErrorMessage() {
        System.out.println("This number is not natural!");
    }

    static void printTitle(String numStr) {
        System.out.printf("Properties of %s%n", numStr);
    }

    static void printParity(String even, String odd) {

        System.out.printf("        even: %s%n", even);
        System.out.printf("        odd: %s%n", odd);
    }

    static void printBuzzStatus(String buzzStatus) {
        System.out.printf("        buzz: %s%n", buzzStatus);
    }

    static void printDuckStatus(String duckStatus) {
        System.out.printf("        duck: %s%n", duckStatus);
    }

    static void printAll(String numStr, String even, String odd,
                         String buzz, String duck) {
        printTitle(numStr);
        printParity(even, odd);
        printBuzzStatus(buzz);
        printDuckStatus(duck);
    }
}
