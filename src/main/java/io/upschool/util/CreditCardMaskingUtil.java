package io.upschool.util;

public class CreditCardMaskingUtil {

    public static String maskCreditCardNumber(String creditCardNumber) {
        //kart numarasındaki gereksiz karakterler
        String cleanedNumber = creditCardNumber.replaceAll("[^0-9]", "");

        // Kart numarasının son 4 hanesi
        String lastDigits = cleanedNumber.substring(cleanedNumber.length() - 4);

        // Maskeleme
        StringBuilder maskedNumber = new StringBuilder(cleanedNumber.length());
        for (int i = 0; i < cleanedNumber.length() - 4; i++) {
            maskedNumber.append('*');
        }
        maskedNumber.append(lastDigits);

        return maskedNumber.toString();
    }

    public static void main(String[] args) {
        String creditCardNumber = "4221-1611-2233-0005";
        String maskedNumber = maskCreditCardNumber(creditCardNumber);
        System.out.println("Masked Credit Card Number: " + maskedNumber);
    }
}
