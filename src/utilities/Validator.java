package utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Validator {
    private static Scanner scanner = new Scanner(System.in);

    public static int getInteger(String message) {
        int result;

        while (true) {
            try {
                System.out.print(message);
                result = Integer.parseInt(scanner.nextLine());
                return result;
            } catch (Exception e) {
                System.out.println("Not an integer!");
            }
        }
    }

    public static int getInteger(String message, int min, int max) {
        int result;

        while (true) {
            try {
                System.out.print(message);
                result = Integer.parseInt(scanner.nextLine());
                if (result < min || result > max)
                    throw new NumberOutOfRangeException("Your input must be from " + min + " to " + max + "!");
                return result;
            } catch (NumberOutOfRangeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Not an integer!");
            }
        }
    }

    public static String getNoBlankString(String message) {
        String result;

        do {
            System.out.print(message);
            result = scanner.nextLine();
            if (result.isBlank()) System.out.println("No input blank string!");
        } while (result.isBlank());

        return result.trim();
    }

    public static double getDouble(String message, double min, boolean isEqual) {
        double result;

        while (true) {
            try {
                System.out.print(message);
                result = Double.parseDouble(scanner.nextLine());
                if ((isEqual && result < min) || (!isEqual && result <= min))
                    throw new NumberOutOfRangeException("Your number must be greater than " + (isEqual ? "or equal " : "") + min);
                return result;
            } catch (NumberOutOfRangeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Not a double!");
            }
        }
    }

    public static String getValidString(String message, String pattern) {
        String result;

        do {
            System.out.print(message);
            result = scanner.nextLine();
            if (!result.matches(pattern)) System.out.println("Invalid format!");
        } while (!result.matches(pattern));

        return result;
    }

    public static String getDateString(String message) {
        String result;

        while (true) {
            result = getValidString(message, "^(0{0,1}[1-9]|[1-2][0-9]|3[0-1])\\/(0{0,1}[1-9]|1[0-2])\\/\\d{4}$");
            String[] numbers = result.split("/");
            int d = Integer.parseInt(numbers[0]);
            int m = Integer.parseInt(numbers[1]);
            int y = Integer.parseInt(numbers[2]);

            int[] months_30 = {4, 6, 9, 11};

            if (y != 2021) {
                System.out.println("The year must be 2021!");
                continue;
            }

            if (contains(months_30, m) && d > 30) {
                System.out.println("This month has only 30 days!");
                continue;
            }

            if (m == 2 && d > 28) {
                System.out.println("2/2021 has only 28 days");
                continue;
            }

            return result;
        }
    }

    public static boolean isExpiredDate(String input, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);

        try {
            Date date = dateFormat.parse(input);
            return (new Date()).after(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    private static boolean contains(int[] arrays, int element) {
        for (int i = 0; i < arrays.length; i++)
            if (arrays[i] == element) return true;
        return false;
    }
}
