package numbers;

import static numbers.BuzzNumbers.*;
public class Print {

    static void printErrorMessage() {
        System.out.println("This number is not natural!");
    }

    static void printParity() {

        String parity;
        if (checkParity()) {
            parity = "Even";
        } else {
            parity = "Odd";
        }
        System.out.printf("This number is %s.%n", parity);
    }

    static void printBuzzStatus() {
        if (isBuzzNumber()) {
            System.out.println("It is a Buzz number.");
            return;
        }
        System.out.println("It is not a Buzz number.");
    }

    static void printWord() {
        System.out.println("Explanation:");
    }

    static void printAll(String explanation) {

        printBuzzStatus();
        printWord();
        printExplanation(explanation);
    }

    static void printExplanation(String explanation) {
        switch (explanation) {
            case "NotDiv/End":
                System.out.printf("%s is neither divisible by 7" +
                        " nor does it end with 7.%n", getNumStr());
                break;
            case "divSeven":
                System.out.printf("%s is divisible by 7 %n", getNumStr());
                break;
            case "EndSeven":
                System.out.printf("%s ends with 7 %n", getNumStr());
                break;
            default:
                System.out.printf("%s is divisible by 7 and ends with 7.", getNumStr());
        }
    }
}
