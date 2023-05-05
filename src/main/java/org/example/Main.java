package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        // Calls the getTransactions method from the LedgerScreen class
        LedgerScreen.getTransactions();
        showHomescreen();

    }

    public static void  showHomescreen() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                                    ~~Welcome to your Homescreen!~~ 
                         Main Menu        
                    [D]~~~ add Deposit
                    [P]~~~ Make Payment
                    [L]~~~ Ledger
                    [X]~~~ Exit"""
            );
            String input = scanner.nextLine();
            switch (input.toUpperCase()) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    LedgerScreen.showLedger();
                    break;
                case "X":
                    System.exit(0);
                default:
                    System.out.println("please enter a valid option");
                    break;

            }
        }

    }

    public static void addDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Date: YYYY-MM-DD");
        String date = scanner.nextLine();
        System.out.println("Enter Time : HH:MM:SS");
        String time = scanner.nextLine();
        System.out.println("Enter Description");
        String description = scanner.nextLine();
        System.out.println("Vendor");
        String vendor = scanner.nextLine();
        System.out.println("Enter Transaction Amount");
        double amount = scanner.nextDouble();//Storing the amount as Double

        //using the filewriter to add collected data to the csv file
        try (FileWriter fileWriter = new FileWriter("Transactions.csv", true)) {
            fileWriter.write("\n" +
                    date + "|" +
                    time + "|" +
                    description + "|" +
                    vendor + "|" + "+" +
                    amount
            );
            // Creating new LocalDate and LocalTime objects to store the transaction date and time respectively
            LocalDate ldate = LocalDate.parse(date);
            LocalTime ltime = LocalTime.parse(time);
            // Creating a new Transaction object to store the transaction details
            Transaction t = new Transaction(ldate, ltime, description,vendor, amount);
            LedgerScreen.transactions.add (t);
            System.out.println("Deposit Added successfully!");
        } catch (IOException e) {
            System.out.println("Error inputting data!");
        }
        //showHomescreen();
    }


    public static void makePayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Date: YYYY-MM-DD");
        String date = scanner.nextLine();
        System.out.println("Enter Time : HH:MM:SS");
        String time = scanner.nextLine();
        System.out.println("Enter Description");
        String description = scanner.nextLine();
        System.out.println("Enter Vendor");
        String vendor = scanner.nextLine();
        System.out.println("Enter Transaction Amount");
        double amount = scanner.nextDouble();

        try (FileWriter fileWriter = new FileWriter("Transactions.csv", true)) {
            fileWriter.write("\n" +
                    date + "|" +
                    time + "|" +
                    description + "|" +
                    vendor + "|" + "-" +
                    amount

            );
            LocalDate ldate = LocalDate.parse(date);
            LocalTime ltime = LocalTime.parse(time);
            Transaction t = new Transaction(ldate, ltime, description,vendor, amount);
            LedgerScreen.transactions.add (t);
            System.out.println("Your Payment was successfully!");
        } catch (IOException e) {
            System.out.println("Error inputting data!");
        }
        //showHomescreen();
    }

    public static void showLedger() {
        LedgerScreen.showLedger();
        return;
    }
}
























