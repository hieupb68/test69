package view;

import controller.SalaryGradeController;
import model.SalaryGrade;

import java.util.List;
import java.util.Scanner;

public class SalaryGradeView {
    private static SalaryGradeController salaryGradeController = new SalaryGradeController();

    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nSalary Grade Management Menu:");
            System.out.println("1. List All Salary Grades");
            System.out.println("2. Add Salary Grade");
            System.out.println("3. Edit Salary Grade");
            System.out.println("4. Delete Salary Grade");
            System.out.println("5. Return to Home Page");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    listAllSalaryGrades();
                    break;
                case 2:
                    addSalaryGrade(scanner);
                    break;
                case 3:
                    editSalaryGrade(scanner);
                    break;
                case 4:
                    deleteSalaryGrade(scanner);
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

    private static void listAllSalaryGrades() throws ClassNotFoundException {
        List<SalaryGrade> salaryGrades = salaryGradeController.getAllSalaryGrades();
        System.out.println("\nList of All Salary Grades:");
        for (SalaryGrade salaryGrade : salaryGrades) {
            System.out.println("Grade: " + salaryGrade.getGrade());
            System.out.println("High Salary: " + salaryGrade.getHighSalary());
            System.out.println("Low Salary: " + salaryGrade.getLowSalary());
            System.out.println("-------------");
        }
    }

    private static void addSalaryGrade(Scanner scanner) throws ClassNotFoundException {
        SalaryGrade salaryGrade = new SalaryGrade();
        System.out.println("\nEnter Salary Grade Details:");

        System.out.print("Grade: ");
        salaryGrade.setGrade(scanner.nextInt());
        scanner.nextLine(); // Consume the newline character

        System.out.print("High Salary: ");
        salaryGrade.setHighSalary(scanner.nextFloat());

        System.out.print("Low Salary: ");
        salaryGrade.setLowSalary(scanner.nextFloat());

        salaryGradeController.addSalaryGrade(salaryGrade);
        System.out.println("Salary Grade added successfully!");
    }

    private static void editSalaryGrade(Scanner scanner) throws ClassNotFoundException {
        System.out.print("\nEnter Grade to edit: ");
        int grade = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        SalaryGrade existingSalaryGrade = salaryGradeController.getSalaryGradeByGrade(grade);
        if (existingSalaryGrade == null) {
            System.out.println("Salary Grade not found!");
            return;
        }

        System.out.println("Editing Salary Grade " + grade + ":");
        System.out.print("New High Salary: ");
        float newHighSalary = scanner.nextFloat();
        existingSalaryGrade.setHighSalary(newHighSalary);

        System.out.print("New Low Salary: ");
        float newLowSalary = scanner.nextFloat();
        existingSalaryGrade.setLowSalary(newLowSalary);

        salaryGradeController.updateSalaryGrade(existingSalaryGrade);
        System.out.println("Salary Grade updated successfully!");
    }

    private static void deleteSalaryGrade(Scanner scanner) throws ClassNotFoundException {
        System.out.print("\nEnter Grade to delete: ");
        int grade = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        SalaryGrade existingSalaryGrade = salaryGradeController.getSalaryGradeByGrade(grade);
        if (existingSalaryGrade == null) {
            System.out.println("Salary Grade not found!");
            return;
        }

        salaryGradeController.deleteSalaryGrade(grade);
        System.out.println("Salary Grade deleted successfully!");
    }
}

