package store.bizscanner.global.util;

import java.util.Random;

public class PasswordUtil {

    public static String generateRandomPassword() {
        int index = 0;
        char[] charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();


        StringBuffer password = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 8 ; i++) {
            double rd = random.nextDouble();
            index = (int) (charSet.length * rd);

            password.append(charSet[index]);
        }
        System.out.println(password);
        return password.toString();
        //StringBuffer를 String으로 변환해서 return 하려면 toString()을 사용하면 된다.
    }
}
