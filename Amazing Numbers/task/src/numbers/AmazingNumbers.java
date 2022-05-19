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
import static numbers.JumpingNumbers.*;
import static numbers.Happy.*;
import static numbers.Sad.*;

/**
 Our program is finished. It can indicate many interesting properties of numbers,
 it knows how to calculate them. Now, when prompted, a user can have a list of number properties.
 To complete the program, let's add an ability to exclude a property from the search query.
 If a user puts a minus (-) before the property, exclude this property from the search query.
 For example, if a user specifies palindromic -duck,
 it means that they are looking for Palindromic numbers that are not Ducks.
 Objectives
 Your program should process the user requests. In this stage, your program should:

 Welcome users;
 Display the instructions;
 Ask for a request;
 If a user enters an empty request, print the instructions;
 If the user enters zero, terminate the program;
 If numbers are not natural, print the error message;
 If an incorrect property is specified, print the error message and the list of available properties;
 For one number, print the properties of the number;
 For two numbers, print the properties of all numbers in the list;
 For two numbers and two properties, print the list of numbers that contain the specified properties;
 If a property is preceded by a minus, this property should not be present in a number;
 If the user specifies mutually exclusive properties, abort the request and warn the user.
 Once the request is processed, continue execution from step 3.

 In this stage, property names include
 even, odd, buzz, duck, palindromic, gapful, spy, sunny, square, jumping, sad, and happy.
 Mutually exclusive properties are even/odd, duck/spy, sunny/square, sad/happy pairs,
 as well as direct opposites (property and -property).
 The test won't check the order of properties, their indentation, and spaces.
 You may format numbers as you like.
 **/

public class AmazingNumbers {
    private static final Scanner scanner =
            new Scanner(System.in);
    private static boolean exit = false;
    static final List<String> PROPERTIES =
            List.of(("EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, " +
                    "SPY, SUNNY, SQUARE, JUMPING, HAPPY, SAD").split(", "));

    static List<String> mutExclList = null;
    static List<String> errorList = new ArrayList<>();

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

    static boolean allValid(List<String> requests) {
        List<String> multProps = requests.subList(2, requests.size());
        boolean isProp = true;
        for (String prop : multProps) {

            if (prop.charAt(0) == '-') {
                prop = prop.substring(1);

            }
            if (!isProperty(prop)) {
                errorList.add(prop);
                isProp = false;
            }
        }
        return isProp;
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
            } else if (!allValid(requests)) {
                if (errorList.size() > 1) {
                    printErrorMessage(errorList);
                    errorList.clear();
                    return false;
                } else {
                    printErrorMessage(errorList.toString());
                    errorList.clear();
                    return false;
                }
            }
        }
        if (requests.size() >= 4) {
            if (mutExclusive(requests)) {
                printMutExErrorMessage(mutExclList);
                isValid = false;
            }
        }
        return isValid;
    }

    static boolean mutExclusive(List<String> unSortedStr) {

        List<String> sortedStr = new ArrayList<>(unSortedStr);

        int mut1 = 0, mut2 = 0, mut3 = 0, mut4 = 0, mut5 = 0 ,
                mut6 = 0, mut7 = 0, mut9 = 0;
        StringBuilder mutStr1 = new StringBuilder();
        StringBuilder mutStr2 = new StringBuilder();
        StringBuilder mutStr3 = new StringBuilder();
        StringBuilder mutStr4 = new StringBuilder();
        StringBuilder mutStr5 = new StringBuilder();
        StringBuilder mutStr6 = new StringBuilder();
        StringBuilder mutStr7 = new StringBuilder();
        StringBuilder mutStr9 = new StringBuilder();

        for (int i = 0; i < sortedStr.size(); i++) {

            if ("even".equalsIgnoreCase(sortedStr.get(i))) {
                if (mutStr1.indexOf("EVEN") == -1) {
                    mutStr1.append(sortedStr.get(i).toUpperCase()).append(" ");
                    mut1++;
                }
            } else if ("odd".equalsIgnoreCase(sortedStr.get(i))) {
                if (mutStr1.indexOf("ODD") == -1) {
                    mutStr1.append(sortedStr.get(i).toUpperCase()).append(" ");
                    mut1++;
                }
            } else if ("duck".equalsIgnoreCase(sortedStr.get(i))) {
                if (mutStr2.indexOf("DUCK") == -1) {
                    mutStr2.append(sortedStr.get(i).toUpperCase()).append(" ");
                    mut2++;
                }
            } else if ("spy".equalsIgnoreCase(sortedStr.get(i))) {
                if (mutStr2.indexOf("SPY") == -1) {
                    mutStr2.append(sortedStr.get(i).toUpperCase()).append(" ");
                    mut2++;
                }
            } else if ("square".equalsIgnoreCase(sortedStr.get(i))) {
                if (mutStr3.indexOf("SQUARE") == -1) {
                    mutStr3.append(sortedStr.get(i).toUpperCase()).append(" ");
                    mut3++;
                }
            } else if ("sunny".equalsIgnoreCase(sortedStr.get(i))) {
                if (mutStr3.indexOf("SUNNY") == -1) {
                    mutStr3.append(sortedStr.get(i).toUpperCase()).append(" ");
                    mut3++;
                }
            }else if ("happy".equalsIgnoreCase(sortedStr.get(i))) {
                if (mutStr4.indexOf("HAPPY") == -1) {
                    mutStr4.append(sortedStr.get(i).toUpperCase()).append(" ");
                    mut4++;
                }
            } else if ("sad".equalsIgnoreCase(sortedStr.get(i))) {
                if (mutStr4.indexOf("SAD") == -1) {
                    mutStr4.append(sortedStr.get(i).toUpperCase()).append(" ");
                    mut4++;
                }
            } else if ("-even".equalsIgnoreCase(sortedStr.get(i))) {
                if (mutStr6.indexOf("-EVEN") == -1) {
                    mutStr6.append(sortedStr.get(i).toUpperCase()).append(" ");
                    mut6++;
                }
            } else if ("-odd".equalsIgnoreCase(sortedStr.get(i))) {
                if (mutStr6.indexOf("-ODD") == -1) {
                    mutStr6.append(sortedStr.get(i).toUpperCase()).append(" ");
                    mut6++;
                }
            } else if ("-duck".equalsIgnoreCase(sortedStr.get(i))) {
                if (mutStr7.indexOf("-DUCK") == -1) {
                    mutStr7.append(sortedStr.get(i).toUpperCase()).append(" ");
                    mut7++;
                }
            } else if ("-spy".equalsIgnoreCase(sortedStr.get(i))) {
                if (mutStr7.indexOf("-SPY") == -1) {
                    mutStr7.append(sortedStr.get(i).toUpperCase()).append(" ");
                    mut7++;
                }
            } else if ("-happy".equalsIgnoreCase(sortedStr.get(i))) {
                if (mutStr9.indexOf("-HAPPY") == -1) {
                    mutStr9.append(sortedStr.get(i).toUpperCase()).append(" ");
                    mut9++;
                }
            } else if ("-sad".equalsIgnoreCase(sortedStr.get(i))) {
                if (mutStr9.indexOf("-SAD") == -1) {
                    mutStr9.append(sortedStr.get(i).toUpperCase()).append(" ");
                    mut9++;
                }
            }
            if (negMuts(sortedStr, sortedStr.get(i))) {
                mutStr5.append(sortedStr.get(i).toUpperCase()).append(" ");
                mut5 = 2;
            }
        }
        loadErrorList(mut1, mut2, mut3, mut4, mut5, mut6, mut7, mut9,
                mutStr1.toString(), mutStr2.toString(), mutStr3.toString(),
                mutStr4.toString(), mutStr5.toString(), mutStr6.toString(),
                mutStr7.toString(), mutStr9.toString());
        return  mut1 == 2 || mut2 == 2 || mut3 == 2 || mut4 == 2 || mut5 == 2 ||
                mut6 == 2 || mut7 == 2 || mut9 == 2;
    }

    static boolean negMuts(List<String> requests, String posProp ) {
        for (String prop : requests) {
            if (Objects.equals(prop.substring(1), posProp) ||
                    Objects.equals(prop, posProp.substring(1))) {
                return true;
            }
        }
        return false;
    }

    static void loadErrorList(int mut1, int mut2, int mut3, int mut4, int mut5,
                              int mut6, int mut7, int mut9,
                              String mutStr1, String mutStr2, String mutStr3,
                              String mutStr4, String mutStr5, String mutStr6,
                              String mutStr7, String mutStr9) {
        if (mut1 == 2) {
            String[] str = mutStr1.split(" ");
            mutExclList = Arrays.asList(str);
        } else if (mut2 == 2) {
            String[] str = mutStr2.split(" ");
            mutExclList = Arrays.asList(str);
        } else if (mut3 == 2) {
            String[] str = mutStr3.split(" ");
            mutExclList = Arrays.asList(str);
        } else if (mut4 == 2) {
            String[] str = mutStr4.split(" ");
            mutExclList = Arrays.asList(str);
        } else if (mut5 == 2) {
            String[] str = mutStr5.split(" ");
            mutExclList = Arrays.asList(str);
        } else if (mut6 == 2) {
            String[] str = mutStr6.split(" ");
            mutExclList = Arrays.asList(str);
        } else if (mut7 == 2) {
            String[] str = mutStr7.split(" ");
            mutExclList = Arrays.asList(str);
        } else if (mut9 == 2) {
            String[] str = mutStr9.split(" ");
            mutExclList = Arrays.asList(str);
        }
    }

    static void processMultReqFiltered(List<String> requests, boolean propOfProp) {

        long startNum = Long.parseLong(requests.get(0));
        int consecNum = Integer.parseInt(requests.get(1));
        int count = 0;
        String propType = requests.get(2);

        for (long i = startNum;; i++) {

            if (propOfProp) {

                if (propType.charAt(0) == '-') {

                    if (!"true".equals(hasProp(propType.substring(1),
                            Long.toString(i))) && allHaveProp(requests, Long.toString(i))) {
                        String properties = checkProperties(Long.toString(i));
                        printProperties(properties);
                        count++;
                        if (count == consecNum) {
                            break;
                        }
                    }

                } else if ("true".equals(hasProp(propType, Long.toString(i))) &&
                        allHaveProp(requests, Long.toString(i))) {
                    String properties = checkProperties(Long.toString(i));
                    printProperties(properties);
                    count++;
                    if (count == consecNum) {
                        break;
                    }
                }

            } else if (propType.charAt(0) == '-') {

                if (!"true".equals(hasProp(propType.substring(1), Long.toString(i)))) {
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

    static boolean allHaveProp(List<String> requests, String strNum ) {

        List<String> multProps = requests.subList(3, requests.size());

        for (String prop : multProps) {

            if (prop.charAt(0) == '-') {
                if ("true".equals(hasProp(prop.substring(1), strNum))) {
                    return false;
                }
            } else if (!"true".equals(hasProp(prop, strNum))) {
                return false;
            }
        }
        return true;
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
        else if (requests.size() >= 4) {
            processMultReqFiltered(requests, true);
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
        } else if ("square".equalsIgnoreCase(propType)) {
            return isPerfSquareToStr(strNum);
        } else if ("jumping".equalsIgnoreCase(propType)) {
            return isJumpingToStr(strNum);
        } else if ("happy".equalsIgnoreCase(propType)) {
            return isHappyToStr(strNum);
        }
        return isSadToStr(strNum);
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
        if ("true".equals(isJumpingToStr(strNum))) {
            String jumping = "jumping";
            properties.append(String.format(" %s", jumping));
        }
        if ("true".equals(isHappyToStr(strNum))) {
            String happy = "happy";
            properties.append(String.format(" %s", happy));
        }
        if ("true".equals(isSadToStr(strNum))) {
            String sad = "sad";
            properties.append(String.format(" %s", sad));
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
                isSunnyToStr(strNum), isPerfSquareToStr(strNum),
                isJumpingToStr(strNum), isHappyToStr(strNum), isSadToStr(strNum));
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
