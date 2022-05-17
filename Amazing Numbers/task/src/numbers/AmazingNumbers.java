package numbers;

import java.text.NumberFormat;
import java.util.*;

import static numbers.Print.*;
import static numbers.BuzzNumbers.*;
import static numbers.DuckNumbers.*;
import static numbers.Palindromic.*;
import static numbers.GapfulNumbers.*;
import static numbers.SpyNumbers.*;
import static numbers.Sunny.*;
import static numbers.Square.*;

/**
 Our program can search for Spy and Palindromic numbers.
 What if we want to find all even Spy numbers?
 Or find all odd numbers among Palindromic numbers?
 Are there any Palindromics that are also Spies?
 In this stage, you will add the ability to search for several properties at once!

 What if a user enters the same property twice by mistake?
 For example, they want to find all even numbers that are even?
 Just ignore this duplicate property.
 What about two mutually exclusive properties?
 For example, if a user wants to find even numbers that are odd.
 In this case, the program will initiate the search, but there are no such numbers.
 We need to make our program foolproof.
 If a request contains mutually exclusive properties,
 the program should abort this request and warn the user.
 There are three pairs of mutually exclusive properties.
 A request cannot include Even and Odd, Duck and Spy, Sunny and Square numbers.
 **/

public class AmazingNumbers {
    private static final Scanner scanner =
            new Scanner(System.in);
    private static boolean exit = false;
    static final List<String> PROPERTIES =
            List.of(("EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, " +
                    "SPY, SUNNY, SQUARE").split(", "));

    static void runAmazingNumbers() {

        printWelcomeMessage();

        while (!exit) {

            printGetRequest();

            List<String> requests = Arrays.asList(scanner.nextLine().split(" "));

            if (isMultParameters(requests)) {
                if (!isValidMultReq(requests)) {
                    continue;
                } else {
                    processMultReqSeries(requests);
                }
            } else {
                singleRequest(requests.get(0));
            }
        }
    }

    static void singleRequest(String strNum) {

        if ("0".equals(strNum)) {
            exit = true;
            printGoodbye();
        } else if ("".equals(strNum)) {
            printWelcomeInstructions();
        } else if (!isNaturalNumber(strNum)) {
            printErrorMessage(0);
        } else {
            displayProperties(strNum);
        }
    }

    static boolean isValidMultReq(List<String> requests) {

        boolean isValid = true;

        for (int i = 0; i < requests.size(); i++) {

            if (i < 2) {
                isValid = isNaturalNumber(requests.get(i));
                if (!isValid) {
                    printErrorMessage(i);
                    return isValid;
                }
            } else if (requests.size() == 4 && !isProperty(requests.get(2))
                    && !isProperty(requests.get(3))) {
                List<String> wrongProp = List.of(requests.get(2), requests.get(3));
                printErrorMessage(wrongProp);
                return false;
            } else {
                isValid = isProperty(requests.get(i));
                if(!isValid) {
                    printErrorMessage(requests.get(i).toUpperCase());
                    return isValid;
                }
            }
        }
        if (4 == requests.size()) {
            if (mutExclusive(requests)) {
                printMutExErrorMessage(Arrays.asList(requests.get(2), requests.get(3)));
                isValid = false;
            }
        }
        return isValid;
    }

    static boolean mutExclusive(List<String> unSortedStr) {

        List<String> sortedStr = new ArrayList<>(unSortedStr);

        Collections.sort(sortedStr);
        return "even".equalsIgnoreCase(sortedStr.get(2)) &&
                "odd".equalsIgnoreCase(sortedStr.get(3)) ||
                "duck".equalsIgnoreCase(sortedStr.get(2)) &&
                        "spy".equalsIgnoreCase(sortedStr.get(3)) ||
                "square".equalsIgnoreCase(sortedStr.get(2)) &&
                        "sunny".equalsIgnoreCase(sortedStr.get(3));
    }

    static void processMultReqFiltered(List<String> requests, boolean propOfProp) {

        long startNum = Long.parseLong(requests.get(0));
        int consecNum = Integer.parseInt(requests.get(1));
        int count = 0;
        String propType = requests.get(2);
        String propType2 = "";
        if (propOfProp) {
            propType2 = requests.get(3);
        }

        for (long i = startNum;; i++) {

            if (propOfProp) {
                if ("true".equals(hasProp(propType, Long.toString(i))) &&
                        "true".equals(hasProp(propType2, Long.toString(i)))) {
                    String properties = checkProperties(Long.toString(i));
                    printProperties(properties);
                    count++;
                    if (count == consecNum) {
                        break;
                    }
                }
            } else {
                if ("true".equals(hasProp(propType, Long.toString(i)))) {
                    String properties = checkProperties(Long.toString(i));
                    printProperties(properties);
                    count++;
                    if (count == consecNum) {
                        break;
                    }
                }
            }
        }
    }

    static void processTwoReq(List<String> requests) {

        // get the string to be incremented
        String strStaNum = requests.get(0);

        long staNum = Long.parseLong(strStaNum);
        // get the loop max number
        int consNum = Integer.parseInt(requests.get(1));

        for (int i = 0; i < consNum; i++) {

            String strNum =  String.valueOf(staNum + i);
            String properties = checkProperties(strNum);
            printProperties(properties);
        }
    }

    static void processMultReqSeries(List<String> requests) {

        if (requests.size() == 2) {
            processTwoReq(requests);
        }
        else if (requests.size() == 3) {
            processMultReqFiltered(requests, false);
        }
        else if (requests.size() == 4) {
            if (compareTo(requests.get(2), requests.get(3))) {
                processMultReqFiltered(requests, false);
            } else {
                processMultReqFiltered(requests, true);
            }
        }
    }

    static boolean compareTo(String firStr, String secStr) {
        return firStr.compareTo(secStr) == 0;
    }

    static String hasProp(String propType, String strNum) {
        if ("buzz".equalsIgnoreCase(propType)) {
            return isBuzzNumber(strNum);
        } else if ("duck".equalsIgnoreCase(propType)) {
            return isDuckNum(strNum);
        } else if ("palindromic".equalsIgnoreCase(propType)) {
            return isPalindromic(strNum);
        } else if ("gapful".equalsIgnoreCase(propType)) {
            return isGapful(strNum);
        }else if ("even".equalsIgnoreCase(propType)) {
            return isEven(strNum);
        } else if ("odd".equalsIgnoreCase(propType)) {
            return isOdd(strNum);
        } else if ("spy".equalsIgnoreCase(propType)) {
            return isSpyNumber(strNum);
        } else if ("sunny".equalsIgnoreCase(propType)) {
            return isSunnyToStr(strNum);
        }
        return isPerfSquareToStr(strNum);
    }

    static String checkProperties(String strNum) {

        StringBuilder properties =
                new StringBuilder(String.format("%s is", strNum));

        if (checkParity(strNum)) {
            String even = "even";
            properties.append(String.format(" %s", even));
        } else {
            String odd = "odd";
            properties.append(String.format(" %s", odd));
        }
        if ("true".equals(isBuzzNumber(strNum))) {
            String buzz = "buzz";
            properties.append(String.format(" %s", buzz));
        }
        if ("true".equals(isDuckNum(strNum))) {
            String duck = "duck";
            properties.append(String.format(" %s", duck));
        }
        if ("true".equals(isPalindromic(strNum))) {
            String palindromic = "palindromic";
            properties.append(String.format(" %s", palindromic));
        }
        if ("true".equals(isGapful(strNum))) {
            String gapful = "gapful";
            properties.append(String.format(" %s", gapful));
        }
        if ("true".equals(isSpyNumber(strNum))) {
            String spy = "spy";
            properties.append(String.format(" %s", spy));
        }
        if ("true".equals(isSunnyToStr(strNum))) {
            String sunny = "sunny";
            properties.append(String.format(" %s", sunny));
        }
        if ("true".equals(isPerfSquareToStr(strNum))) {
            String square = "square";
            properties.append(String.format(" %s", square));
        }
        return properties.toString();
    }

    static boolean isMultParameters(List<String> requests) {
        return requests.size() > 1;
    }

    static void displayProperties(String strNum) {

        String formatted = NumberFormat.getInstance()
                .format(Long.valueOf(strNum));

        boolean evenOdd = checkParity(strNum);
        String even;
        String odd;

        if (evenOdd) {
            even = "true";
            odd = "false";
        } else {
            even = "false";
            odd = "true";
        }
        printAll(formatted, even, odd, isBuzzNumber(strNum),
                isDuckNum(strNum), isPalindromic(strNum),
                isGapful(strNum), isSpyNumber(strNum),
                isSunnyToStr(strNum), isPerfSquareToStr(strNum));
    }
    static boolean isNaturalNumber(String strNum) {

        try {
            long naturalNum = Long.parseLong(strNum);
            if (naturalNum < 1) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    static boolean isProperty(String property) {
        return PROPERTIES.contains(property.toUpperCase());
    }
}
