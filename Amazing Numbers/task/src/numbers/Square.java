package numbers;
import static java.lang.Math.*;
/**
 perfect square is an integer that is the square of an integer;
 in other words, it is the product of an integer with itself.
 For example, 9 is a square number, since it equals 32 and can be written as 3×3.
 **/
public class Square {

    static boolean isPerfectSquareNum(String strNum) {

        if ("1".equals(strNum)) {
            return true;
        }

        long sqrRootNum = (long) sqrt(Double.parseDouble(strNum));
        long numSqred = sqrRootNum * sqrRootNum;

        return numSqred == Long.parseLong(strNum);
    }

    static String isPerfSquareToStr(String strNum) {
        if (isPerfectSquareNum(strNum)) {
            return "true";
        }

        return "false";
    }
}
