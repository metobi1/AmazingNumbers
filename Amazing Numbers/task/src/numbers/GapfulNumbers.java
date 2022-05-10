package numbers;

/** In this stage, we are going to add one more property — Gapful numbers.
 It is a number that contains at least 3 digits and is divisible by the
 concatenation of its first and last digit without a remainder.
 12 is not a Gapful number, as it has only two digits.
 132 is a Gapful number, as 132 % 12 == 0.
 Another good example of a Gapful number is 7881, as 7881 % 71 == 0.**/

public class GapfulNumbers {

    static String isGapful(String numStr) {

        if (numStr.length() < 3) {
            return "false";
        }
        String begEndNums = String.format( "%s%s", numStr.substring(0, 1),
                numStr.substring(numStr.length() - 1));
        Long concatNum = Long.parseLong(begEndNums);
        Long number = Long.parseLong(numStr);

        if (number % concatNum == 0) {
            return "true";
        }
        return "false";
    }
}
