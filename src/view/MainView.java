package view;

import java.util.Scanner;

import controller.DepartmentController;
import controller.EmployeeControl;

public class MainView {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMainMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                	EmployeeControl employeeControl = new EmployeeControl(null);
//                    EmployeeView.main(args);
                	employeeControl.run();
                    break;
                case 2:
                	DepartmentController departmentController = new DepartmentController(null);
                	departmentController.run();
//                    DepartmentView.main(args);
                    break;
                case 3:
                    SalaryGradeView.main(args);
                    break;
                case 4:
                    TimekeeperView.main(args);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1. Employees Management");
        System.out.println("2. Departments Management");
        System.out.println("3. Salary Grades Management");
        System.out.println("4. Timekeepers Management");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
}