package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        homescreen();
    }

    public static void homescreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to your Account Ledger!\n"+
                "Main Menu:\n" +
                "[D]- add Deposit\n" +
                "[P]- Make Payment\n" +
                "[L]-Ledger\n" +
                "[X]- Exit");
        String input = scanner.nextLine();
        switch (input.toUpperCase()) {
            case "D":
                addDeposit();
                break;
            case "P":
                makePayment();
                break;
            case "X":
                System.exit(0);
            default:
                System.out.println("please enter a valid option");
                break;
        }

    }

    public static void addDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Date: YYYY-MM-DD");
        String date = scanner.nextLine();
        System.out.println("Enter Time : HH:MM;SS");
        String time = scanner.nextLine();
        System.out.println("Enter Description");
        String description = scanner.nextLine();
        System.out.println("Enter Vendor");
        String vendor = scanner.nextLine();
        System.out.println("Enter Transaction Amount");
        double amount = scanner.nextDouble();

        try (FileWriter fileWriter = new FileWriter("Trancastions.csv", true)) {
            fileWriter.write("\n" +
                    date + "|" +
                    time + "|" +
                    description + "|" +
                    vendor + "|" + "-" +
                    amount
            );
            fileWriter.close();
            System.out.println("Deposit Added successfully!");
        } catch (IOException e) {
            System.out.println("Error inputting data!");
        }
        homescreen();
    }


    public static void makePayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Date: YYYY-MM-DD");
        String date = scanner.nextLine();
        System.out.println("Enter Time : HH:MM;SS");
        String time = scanner.nextLine();
        System.out.println("Enter Description");
        String description = scanner.nextLine();
        System.out.println("Enter Vendor");
        String vendor = scanner.nextLine();
        System.out.println("Enter Transaction Amount");
        double amount = scanner.nextDouble();

        try (FileWriter fileWriter = new FileWriter("Trancastions.csv", true)) {
            fileWriter.write("\n" +
                    date + "|" +
                    time + "|" +
                    description + "|" +
                    vendor + "|" + "-" +
                    amount
            );
            fileWriter.close();
            System.out.println("Payment Added successfully!");
        } catch (IOException e) {
            System.out.println("Error inputting data!");
        }
        homescreen();
    }

    public static void showLedger(){
        Ledger.showLedger();

    }
}















