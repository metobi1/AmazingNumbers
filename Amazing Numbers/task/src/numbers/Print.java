package numbers;

import java.util.List;

public class Print {

    static void printWelcomeTitle() {
        System.out.println("Welcome to Amazing Numbers!\n");
    }

    static void printWelcomeInstructions() {

        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
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
        } else if (parNum == 1) {
            System.out.println("The second parameter should be a natural number.");
        }
    }

    static void printErrorMessage(String wrongProperty) {

        System.out.printf("The property %s is wrong.%n", wrongProperty);
        System.out.printf("Available properties: %s%n", AmazingNumbers.PROPERTIES);
    }

    static void printErrorMessage(List<String> wrongProperties) {

        System.out.printf("The properties %s are wrong.%n", wrongProperties);
        System.out.printf("Available properties: %s%n", AmazingNumbers.PROPERTIES);
    }

    static void printMutExErrorMessage(List<String> wrongProperties) {

        System.out.printf("The request contains mutually exclusive properties: %s.%n", wrongProperties);
        System.out.println("There are no numbers with these properties.");
    }

    static void printTitle(String strNum) {

        System.out.printf("Properties of %s%n", strNum);
    }

    static void printProperties(String str) {
        System.out.println(str);
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

    static void printSpyStatus(String spyStatus) {
        System.out.printf("        spy: %s%n", spyStatus);
    }

    static void printSunnyStatus(String sunnyStatus) {
        System.out.printf("        sunny: %s%n", sunnyStatus);
    }

    static void printPerfectSquareStatus(String perfSquareStatus) {
        System.out.printf("        square: %s%n", perfSquareStatus);
    }

    static void printJumpingStatus(String jumpingStatus) {
        System.out.printf("        jumping: %s%n", jumpingStatus);
    }

    static void printAll(String strNum, String even, String odd,
                         String buzz, String duck, String palindrome,
                         String gapful, String spy, String sunny,
                         String perfectSquare, String jumping) {
        printTitle(strNum);
        printParity(even, odd);
        printBuzzStatus(buzz);
        printDuckStatus(duck);
        printPalindromicStatus(palindrome);
        printGapfulStatus(gapful);
        printSpyStatus(spy);
        printSunnyStatus(sunny);
        printPerfectSquareStatus(perfectSquare);
        printJumpingStatus(jumping);

    }
}
