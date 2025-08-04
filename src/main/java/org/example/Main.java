package org.example;


import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    static boolean checkCustomerTier (String string) {
     if (string.equalsIgnoreCase("Regular") || string.equalsIgnoreCase("Silver") || string.equalsIgnoreCase("Gold")) {
         return true;
     }
        System.out.println("Invalid Input. Please choose whether: \"Regular\", \"Silver\", \"Gold\".");
     System.exit(0);
     return false;
    }

    static boolean checkShippingZone (String string) {
        if (string.equalsIgnoreCase("ZoneA") || string.equalsIgnoreCase("ZoneB") || string.equalsIgnoreCase("ZoneC") || string.equalsIgnoreCase("Unknown")) {
            return true;
        }
        System.out.println("Invalid Input. Please choose whether: \"ZoneA\", \"ZoneB\", \"ZoneC\", or \"Unknown\").");
        System.exit(0);
        return false;

    }

    static boolean checkDiscountCode (String string) {
        if (string.equals("SAVE10") || string.equals("FREESHIP") || string.equals("")) {
            return true;
        }
        System.out.println("Invalid Input. Please input a valid discount code.");
        return false;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // === ORDER PROCESSOR ===
        System.out.println("Welcome to the Interactive Order Processor!\n");

        // USER INPUT
        System.out.println("--- Enter Order Details ---");
        System.out.print("Enter unit price: ");
        double unitPrice = scanner.nextDouble();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Is customer a member (true/false)?: ");
        boolean isCustomerMember = scanner.nextBoolean();

        System.out.print("Enter customer tier (Regular, Silver, Gold): ");
        String customerTier = scanner.next();
        checkCustomerTier(customerTier);


        System.out.print("Enter shipping zone (ZoneA, ZoneB, ZoneC, Unknown): ");
        String shippingZone = scanner.next();
        checkShippingZone(shippingZone);

        System.out.print("Enter discount code (SAVE10, FREESHIP, or \"\" for none): ");
        String discountCode = scanner.next();
        checkDiscountCode(discountCode);

        // PRINT INPUT
        System.out.println();
        System.out.println("--- Order Details ---");
        System.out.println("Unit Price: $" + unitPrice);
        System.out.println("Quantity: " + quantity);
        System.out.println("Is Member: " + isCustomerMember);
        System.out.println("Customer Tier " + customerTier);
        System.out.println("Shipping Zone: " + shippingZone);
        System.out.println("Discount Code: " + discountCode);

        // CALCULATION STEPS
        System.out.println();
        System.out.println("--- Calculation Steps ---");

        // Subtotal
        double initialSubtotal = unitPrice*quantity;
        System.out.println("Initial Subtotal: $" + initialSubtotal);

        // After Tier Discount
        double afterTierDiscount = 0;
        if (customerTier.equalsIgnoreCase("Gold")) {
            afterTierDiscount = initialSubtotal*.85;
            System.out.println("After Tier Discount (Gold - 15%): $" + afterTierDiscount);
        } else if (customerTier.equalsIgnoreCase("Silver")) {
            afterTierDiscount = initialSubtotal*.90;
            System.out.println("After Tier Discount (Silver - 10%): $" + afterTierDiscount);
        } else {
            afterTierDiscount = initialSubtotal;
            System.out.println("After Tier Discount (Regular - No Discount): $" + afterTierDiscount);
        }

        // After Quantity Discount
        double afterQuantityDiscount = afterTierDiscount;
        if (quantity >= 5) {
            afterQuantityDiscount = afterTierDiscount*.95;
            System.out.println("After Quantity Discount (5% for >=5 items): $" + afterQuantityDiscount);
        } else {
            System.out.println("After Quantity Discount (No discount for < 5 items): $" + afterQuantityDiscount);
        }

        // After Promo Discount
        double afterPromoDiscount = afterQuantityDiscount;

        double shippingCost;
        if (discountCode.equals("SAVE10") && afterQuantityDiscount > 75.00) {
            afterPromoDiscount = afterQuantityDiscount-10;
            System.out.println("After Promotional Code (SAVE10 for >$75): $" + afterQuantityDiscount);
        } else if (discountCode.equals("FREESHIP")) {
            shippingCost = 0;
            System.out.println("Free Shipping Promo Activated!");
        } else {
            System.out.println("After Promotional Code (No promotions availed): $" + afterQuantityDiscount);
        }

        // After Small Order Surcharge
        double afterSmallSurcharge = afterPromoDiscount < 25.00 ? (afterPromoDiscount + 3): afterPromoDiscount;
        System.out.println("After Small Order Surcharge (if applicable): $"+ afterSmallSurcharge);

        // Shipping Cost
        System.out.println();
        if (!(discountCode.equals("FREESHIP"))) {
            switch (shippingZone) {
                case "ZoneA":
                    shippingCost = 5.00;
                    break;
                case "ZoneB":
                    shippingCost = 12.50;
                    break;
                case "ZoneC":
                    shippingCost = 20.00;
                    break;
                default:
                    shippingCost = 25.00;
                    break;
            }
        }

        // Final Order Total
        System.out.println();
        double finalOrderTotal = shippingCost+afterSmallSurcharge;
        System.out.println("Final Order Total: $"+ finalOrderTotal);



        // === STRING EQUALITY DEMO ===
        System.out.println();
        System.out.println("--- String Equality Demo ---");
        System.out.print("Enter first string for comparison: ");
        String string1 = scanner.next();
        System.out.print("Enter second string for comparison: ");
        String string2 = scanner.next();
        System.out.println();
        System.out.println("String 1: \""+ string1 +"\"");
        System.out.println("String 2: \""+ string2 +"\"");

        // COMPARISON RESULTS

        boolean areStringsEqualEqual = (string1 == string2);
        String areStringsEqualEqualExplanation = " (Compares references, which are different for user input strings)";

        boolean areStringsDotEquals = string1.equals(string2);
        String areStringsDotEqualsFalse = " (Content is different due to case)";
        String areStringsDotEqualsTrue = " (Content is identical even with the case)";

        boolean areStringsDotEqualsIgnoreCase = string1.equalsIgnoreCase(string2);
        String areStringsDotEqualsIgnoreCaseFalse = " (Content is different even while ignoring the case)";
        String areStringsDotEqualsIgnoreCaseTrue = " (Content is identical while ignoring the case)";

        System.out.println();
        System.out.println("String 1 == String 2: " + areStringsEqualEqual + areStringsEqualEqualExplanation);

        if (areStringsDotEquals) {
            System.out.println("String 1 .equals() String 2: " + areStringsDotEquals + areStringsDotEqualsTrue);
        } else {
            System.out.println("String 1 .equals() String 2: " + areStringsDotEquals + areStringsDotEqualsFalse);
        }

        if (areStringsDotEqualsIgnoreCase) {
            System.out.println("String 1 .equals() String 2: " + areStringsDotEqualsIgnoreCase + areStringsDotEqualsIgnoreCaseTrue);
        } else {
            System.out.println("String 1 .equalsIgnoreCase() String 2: " + areStringsDotEqualsIgnoreCase + areStringsDotEqualsIgnoreCaseFalse);
        }


        scanner.close();
    }
}