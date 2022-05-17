package numbers;
import static numbers.Square.*;
/**
 N is a sunny number if N+1 is a perfect square number.
 **/
public class Sunny {

    static boolean isSunnyNum(String strNum) {

        long sunnyNum = Long.parseLong(strNum);
        long addOne = sunnyNum + 1;

        return isPerfectSquareNum(String.valueOf(addOne));
    }

    static String isSunnyToStr(String strNum) {
        if (isSunnyNum(strNum)) {
            return "true";
        }

        return "false";
    }
}
