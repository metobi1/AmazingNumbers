package numbers;

import static numbers.Happy.*;

/**
 4 is not a happy number because the sequence starts with 42 = 16, 12 + 62 = 37,
 and finally reaches 22 + 02 = 4. This is the number that started the sequence,
 so the process goes on in an infinite cycle. A number that is not happy is called Sad (or Unhappy).
 **/

public class Sad {

    static String isSadToStr(String strNum) {
        if (isHappyNum(strNum)) {
            return "false";
        }
        return "true";
    }
}
