package numbers;

public class Print {

    static void printWelcomeTitle() {
        System.out.println("Welcome to Amazing Numbers!\n");
    }

    static void printWelcomeInstructions() {

        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
    }

    static void printWelcomeMessage() {
        printWelcomeTitle();
        printWelcomeInstructions();
    }

    static void printGoodbye() {
        System.out.println("Goodbye!");
    }

    static void printGetRequest () {
        System.out.print("Enter a request: ");
    }

    static void printErrorMessage(int parNum) {

        if (parNum == 0) {
            System.out.println("The first parameter should be a natural number or zero.");
        } else {
            System.out.println("The second parameter should be a natural number.");
        }
    }

    static void printTitle(String strNum) {

        System.out.printf("Properties of %s%n", strNum);
    }

    static void printParity(String even, String odd) {

        System.out.printf("        even: %s%n", even);
        System.out.printf("        odd: %s%n", odd);
    }

    static void printBuzzStatus(String buzzStatus) {
        System.out.printf("        " +
                "buzz: %s%n", buzzStatus);
    }

    static void printDuckStatus(String duckStatus) {
        System.out.printf("        duck: %s%n", duckStatus);
    }

    static void printPalindromicStatus(String palinStatus) {
        System.out.printf("        palindromic: %s%n", palinStatus);
    }

    static void printGapfulStatus(String gapfulStatus) {
        System.out.printf("        gapful: %s%n", gapfulStatus);
    }

    static void printAll(String strNum, String even, String odd,
                         String buzz, String duck, String palindrome, String gapful) {
        printTitle(strNum);
        printParity(even, odd);
        printBuzzStatus(buzz);
        printDuckStatus(duck);
        printPalindromicStatus(palindrome);
        printGapfulStatus(gapful);
    }
}
