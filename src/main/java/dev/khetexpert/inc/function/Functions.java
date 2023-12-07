// Functions Class

package dev.khetexpert.inc.function;

import dev.khetexpert.inc.objects.Admin;

import java.util.Random;

@Admin
public class Functions {

    public static String randomOTPGenerator() {
        Random random = new Random();
        return String.valueOf(random.nextInt(99999999));
    }

    public static String mailBody(String otp) {
        return "Hello there!..\n\n\n" + "OTP for KhetExpert Email verification is " + otp + "\n\n\nTeam KhetExpert\n" + "khetexpert.inc";
    }

}
