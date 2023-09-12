package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import controller.TimekeeperController;
import model.Timekeeper;

public class TimekeeperView {
    private static TimekeeperController timekeeperController = new TimekeeperController();

    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nTimekeeper Management Menu:");
            System.out.println("1. List All Timekeepers");
            System.out.println("2. Add Timekeeper");
            System.out.println("3. Edit Timekeeper");
            System.out.println("4. Delete Timekeeper");
            System.out.println("5. Return to Main Menu");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    listAllTimekeepers();
                    break;
                case 2:
                    addTimekeeper(scanner);
                    break;
                case 3:
                    editTimekeeper(scanner);
                    break;
                case 4:
                    deleteTimekeeper(scanner);
                    break;
                case 5:
                    MainView.main(args);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private static void listAllTimekeepers() throws ClassNotFoundException {
        List<Timekeeper> timekeepers = timekeeperController.getAllTimekeepers();
        System.out.println("\nList of All Timekeepers:");
        for (Timekeeper timekeeper : timekeepers) {
            System.out.println("Timekeeper ID: " + timekeeper.getTimekeeper_Id());
            System.out.println("Date and Time: " + timekeeper.getDate_Time());
            System.out.println("In/Out: " + timekeeper.getIn_Out());
            System.out.println("Employee ID: " + timekeeper.getEmpId());
            System.out.println("-------------");
        }
    }

    private static void addTimekeeper(Scanner scanner) throws ClassNotFoundException {
        Timekeeper timekeeper = new Timekeeper();
        System.out.println("\nEnter Timekeeper Details:");

        System.out.print("Timekeeper ID: ");
        timekeeper.setTimekeeper_Id(scanner.nextLine());

        System.out.print("Date and Time (yyyy-MM-dd HH:mm:ss): ");
        String dateTimeStr = scanner.nextLine();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateTime = dateFormat.parse(dateTimeStr);
            timekeeper.setDate_Time(dateTime);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Use yyyy-MM-dd HH:mm:ss.");
            return;
        }

        System.out.print("In/Out (e.g., 'In' or 'Out'): ");
        timekeeper.setIn_Out(scanner.nextLine());

        System.out.print("Employee ID: ");
        timekeeper.setEmpId(scanner.nextBigInteger());

        timekeeperController.addTimekeeper(timekeeper);
        System.out.println("Timekeeper added successfully!");
    }

    private static void editTimekeeper(Scanner scanner) throws ClassNotFoundException {
        System.out.print("\nEnter Timekeeper ID to edit: ");
        String timekeeperId = scanner.nextLine();

        Timekeeper existingTimekeeper = timekeeperController.getTimekeeperById(timekeeperId);

        if (existingTimekeeper == null) {
            System.out.println("Timekeeper not found.");
            return;
        }

        System.out.println("Editing Timekeeper ID " + timekeeperId + ":");
        System.out.print("Date and Time (yyyy-MM-dd HH:mm:ss): ");
        String dateTimeStr = scanner.nextLine();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateTime = dateFormat.parse(dateTimeStr);
            existingTimekeeper.setDate_Time(dateTime);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Use yyyy-MM-dd HH:mm:ss.");
            return;
        }


        System.out.print("In/Out (e.g., 'In' or 'Out'): ");
        existingTimekeeper.setIn_Out(scanner.nextLine());

        timekeeperController.updateTimekeeper(existingTimekeeper);
        System.out.println("Timekeeper updated successfully!");
    }

    private static void deleteTimekeeper(Scanner scanner) throws ClassNotFoundException {
        System.out.print("\nEnter Timekeeper ID to delete: ");
        String timekeeperId = scanner.nextLine();

        Timekeeper existingTimekeeper = timekeeperController.getTimekeeperById(timekeeperId);

        if (existingTimekeeper == null) {
            System.out.println("Timekeeper not found.");
            return;
        }

        timekeeperController.deleteTimekeeper(timekeeperId);
        System.out.println("Timekeeper deleted successfully!");
    }
}
