package org.example;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;


public class LedgerScreen {
    public static ArrayList<Transaction> transactions = getTransactions();

    // This line declares a static method named "getTransactions" that returns an ArrayList of Transaction objects
    public static ArrayList<Transaction> getTransactions() {
        // This line creates a new ArrayList of Transaction objects named "transactions"
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            //// This line creates a FileReader object named "fileReader" with the filename "transaction.csv"
            FileReader fileReader = new FileReader("Transactions.csv");
            // This line creates a BufferedReader object named "bufferedReader" that reads from "fileReader"
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // This line declares a String variable named "input"
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                String[] details = input.split("\\|");
                LocalDate date = LocalDate.parse(details[0]);
                LocalTime time = LocalTime.parse(details[1]);
                String description = details[2];
                String vendor = details[3];
                double amount = Double.parseDouble(details[4]);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction);

            }
            bufferedReader.close();
            // This line prints an error message if the file is not found
        } catch (IOException e) {
            System.out.println("File not found!");
            System.exit(0);
        }
        return transactions;
    }

    // This line declares a static method named "showLedger" that takes no parameters and returns void
    public static void showLedger() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                                    ~~Welcome to your Account Ledger!~~
                           Ledger Menu
                    [A]~~~All Entries
                    [D]~~~Deposits
                    [P]~~~Payments
                    [R]~~~Reports
                    [H]~~~Home
                    """);
            String input = scanner.nextLine();
            switch (input.toUpperCase()) {
                case "A":
                    showEntries();
                    break;
                case "D":
                    showDeposits();
                    break;
                case "P":
                    showPayments();
                    break;
                case "R":
                    reportsMenu();
                    break;
                case "H":
                    return;
                default:
                    System.out.println("please enter a valid option");
            }

        }
    }


    public static void showEntries() {

        System.out.println("All Entries");
        for (Transaction item : transactions) {
            System.out.println(
                    item.getDate() + " " +
                            item.getDescription() + " " +
                            item.getVendor() + " " +
                            item.getAmount()
            );
        }
    }

    public static void showDeposits() {
        System.out.println("Only the entries deposits into the account");
        for (Transaction item : transactions) {
            if (item.getAmount() > 0) {
                System.out.println(
                        item.getDate() + " " +
                                item.getDescription() + " " +
                                item.getVendor() + " " +
                                item.getAmount());
            }
        }
    }

    public static void showPayments() {
        System.out.println("Only the negative entries");
        for (Transaction item : transactions) {
            if (item.getAmount() < 0) {
                System.out.println(
                        item.getDate() + " " +
                                item.getDescription() + " " +
                                item.getVendor() + " " +
                                item.getAmount());
            }
        }
    }


    public static void reportsMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Welcome to your ReportMenu!\n" +
                    "Main Menu:\n" +
                    "[1]- Month To Date\n" +
                    "[2]- Previous Month\n" +
                    "[3]- Year To Date\n" +
                    "[4]- Previous Year\n" +
                    "[5]- Search by Vendor\n" +
                    "[0]- Back To Report page\n" +
                    "[H]- Home");
        String input = scanner.nextLine();
        switch (input.toUpperCase()) {
            case "1":
                monthToDate();
                break;
            case "2":
                previousMonth();
                break;
            case "3":
                yearToDate();
                break;
            case "4":
                previousYear();
                break;
            case "5":
                searchByVendor();
                break;
            case "X":
                System.exit(0);
            default:
                System.out.println("please enter a valid option");
                break;
        }

    }

}


    public static String getCurrentDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    public static String getMonth(String date) {
        //using the index of that String
        String month = date.substring(5, 7);
        return month;
    }

    public static String getCurrentDay(String date) {
        //using the index of that String
        String day = date.substring(8, 10);
        return day;
    }


    public static void searchByVendor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter vendor's name: ");
        String vendorName = scanner.nextLine();
        for (Transaction item : transactions) {
            if (item.getVendor().equals(vendorName)) {
                System.out.println(
                        item.getDate() + " " +
                                item.getDescription() + " " +
                                item.getVendor() + " " +
                                item.getAmount());
            }

        }
    }


    public static void monthToDate() {
        Scanner scanner = new Scanner(System.in);
        String currentDate = getCurrentDate();
        System.out.println("Which month report would you like to see?");
        String userMonth = scanner.nextLine();
        for (Transaction item : transactions) {
            //converts data from LocalDate type to string type
            String itemsDate = item.getDate().toString();
            //gets month from item string
            String itemsMonth = getMonth(itemsDate);
            //get day from current date "yyyy-mm-dd"
            String currentDay = getCurrentDay(currentDate);
            //get day from items day
            String itemsDay = getCurrentDay(itemsDate);
            //converts day from string to int "05" -> 05 or 5
            int currentNumberDay = Integer.parseInt(currentDay);
            int itemNumberDay = Integer.parseInt(itemsDay);
            if (userMonth.equals(itemsMonth) && itemNumberDay <= currentNumberDay) {
                System.out.println(
                        item.getDate() + " " +
                                item.getDescription() + " " +
                                item.getVendor() + " " +
                                item.getAmount());
            }

        }
    }


    public static void previousMonth() {
        // Get the previous month as a string.
        String previousMonth = Integer.parseInt(getMonth(getCurrentDate())) - 1 + "";
        // Check if the transaction was made in the previous month.
        for (Transaction item : transactions) {

            String itemMonth = getMonth(item.getDate() + "");
            if (itemMonth.equals(0 + previousMonth)) {
                // Print the transaction details.
                System.out.println(
                        item.getDate() + " " +
                                item.getDescription() + " " +
                                item.getVendor() + " " +
                                item.getAmount());
            }

        }
    }

    public static void yearToDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What Year to date report would you like to see?");
        int userYear = Integer.parseInt(scanner.nextLine());
        for (Transaction item : transactions) {
            //converts date from LocalDate type to string type
            String itemsDate = item.getDate().toString();
            //gets month from item string
            int itemsYear = Integer.parseInt((item.getDate() + "").substring(0, 4));
            //get year from current year "yyyy-mm-dd"
            int currentYear = Integer.parseInt((getCurrentDate() + "").substring(0, 4));

            if (itemsYear >= userYear && itemsYear <= currentYear) {
                System.out.println(
                        item.getDate() + " " +
                                item.getDescription() + " " +
                                item.getVendor() + " " +
                                item.getAmount());

            }

        }
    }


    //This method will display transaction made from previous year using the transactions files.
    public static void previousYear() {
        // Get the previous year as a string.
        String previousYear = Integer.parseInt((getCurrentDate() + "").substring(0, 4)) - 1 + "";
        for (Transaction item : transactions) {

            String itemYear = (item.getDate() + "").substring(0, 4);
// Check if the transaction was made in the previous year.
            if (itemYear.equals(previousYear)) {
                System.out.println(
                        // Print the transaction details.
                        item.getDate() + " " +
                                item.getDescription() + " " +
                                item.getVendor() + " " +
                                item.getAmount());
            }


        }

}

}