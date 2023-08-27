// Copyright (C) 2020
// All rights reserved
package costcalculator;

import java.util.Scanner;

/**
 * Main class
 * @author Miguel Parra
 */
public class Main {
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Vacation Cost Calculator!");

        try {
            // Get destination input
            System.out.println("Please enter the destination (Paris, New York City, or Other):");
            String destination = scanner.nextLine().trim(); // Trim to remove leading/trailing spaces

            // Validate destination
            if (!isValidDestination(destination)) {
                throw new IllegalArgumentException("Invalid destination. Please enter Paris, New York City, or Other.");
            }

            // Get number of travelers input
            System.out.println("Please enter the number of travelers:");
            int numberOfTravelers = scanner.nextInt();

            // Validate number of travelers
            if (!isValidNumberOfTravelers(numberOfTravelers)) {
                throw new IllegalArgumentException("Invalid number of travelers. Please enter a number between 1 and 80.");
            }

            // Get duration input
            System.out.println("Please enter the duration of the vacation in days:");
            int durationInDays = scanner.nextInt();

            // Validate duration
            if (!isValidDuration(durationInDays)) {
                throw new IllegalArgumentException("Invalid duration. Please enter a positive number of days.");
            }

            double baseCost = 1000.0;
            double totalCost = baseCost;

            // Additional cost based on destination
            if (destination.equalsIgnoreCase("Paris")) {
                totalCost += 500.0;
            } else if (destination.equalsIgnoreCase("New York City")) {
                totalCost += 600.0;
            } else if (!destination.equalsIgnoreCase("Other")) {
                // Invalid destination
                throw new IllegalArgumentException("Invalid destination. Please enter Paris, New York City, or Other.");
            }

            // Group discounts
            if (numberOfTravelers > 4 && numberOfTravelers <= 10) {
                totalCost *= 0.90; // Apply 10% discount
            } else if (numberOfTravelers > 10) {
                totalCost *= 0.80; // Apply 20% discount
            }

            // Penalty fee for vacations less than 7 days
            if (durationInDays < 7) {
                totalCost += 200.0;
            }

            // Promotion policy
            if (durationInDays > 30 || numberOfTravelers == 2) {
                totalCost -= 200.0;
            }

            // Check for group size limit
            if (numberOfTravelers > 80) {
                // Invalid group size
                throw new IllegalArgumentException("Invalid group size. Maximum group size is 80.");
            }

            // Validate and display the total cost
            if (totalCost > 0) {
                System.out.println("Total cost of the vacation package: $" + totalCost);
            } else {
                System.out.println("Invalid input. Please check your inputs and try again.");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Validate destination
     * @param destination
     * @return
     */
    private static boolean isValidDestination(String destination) {
        return destination.equalsIgnoreCase("Paris") ||
               destination.equalsIgnoreCase("New York City") ||
               destination.equalsIgnoreCase("Other");
    }

    /**
     * Validate number of travelers
     * @param numberOfTravelers
     * @return
     */
    private static boolean isValidNumberOfTravelers(int numberOfTravelers) {
        return numberOfTravelers >= 1 && numberOfTravelers <= 80;
    }

    /**
     * Validate duration
     * @param durationInDays
     * @return
     */
    private static boolean isValidDuration(int durationInDays) {
        return durationInDays > 0;
    }

}
