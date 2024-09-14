package com.wora.common.utils;

import com.wora.ticket.domain.valueObjects.Price;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Date;
import java.util.Scanner;

public class InputScanner {
    private final static Scanner SCANNER = new Scanner(System.in);

    public static Integer scanInt(String message) {
        System.out.print(message);
        return SCANNER.nextInt();
    }

    public static Double scanDouble(String message) {
        System.out.print(message);
        return SCANNER.nextDouble();
    }

    public static Float scanFloat(String message) {
        System.out.print(message);
        return SCANNER.nextFloat();
    }

    public static String scanString(String message) {
        System.out.print(message);
        return SCANNER.nextLine();
    }

    public static void clearBuffer() {
        SCANNER.nextLine();
    }

    public static Date scanDate(String prompt) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        Date date = null;

        while (date == null) {
            System.out.print(prompt);
            String input = SCANNER.nextLine().trim();

            if (input.isEmpty()) {
                return null;
            }

            try {
                date = dateFormat.parse(input);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }

        return date;
    }

    public static Boolean scanBoolean(String prompt) {
        Boolean result = null;

        System.out.print(prompt);
        String input = SCANNER.nextLine().trim().toLowerCase();

        if (input.isEmpty()) {
            return null;
        }

        switch (input) {
            case "true", "t", "yes", "y", "1" -> result = true;
            case "false", "f", "no", "n", "0" -> result = false;
            default -> System.out.println("Invalid input. Please enter true or false.");
        }

        System.out.println("result is boolean : " + result);
        return result;
    }

    public static Price scanPrice(String prompt) {
        final Float amount = scanFloat(prompt);
        clearBuffer();
        final String currencyCode = scanString("Enter currency code: ");
        final Currency currency = Currency.getInstance(currencyCode.toUpperCase());
        return new Price(amount, currency);
    }

    public static LocalDateTime scanLocalDateTime(String prompt) {
        String input = scanString(prompt + "1986-04-08 12:30");
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(input, formatter);
    }

    public static LocalDate scanLocalDate(String prompt) {
        String input = scanString(prompt + "1986-04-08");
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(input, formatter);
    }

    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
