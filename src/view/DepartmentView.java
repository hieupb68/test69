package view;

import model.Department;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DepartmentView {

    static Scanner scan;

    public DepartmentView() {
        scan = new Scanner(System.in);
    }

    public void showMessage(String smg) {
        System.out.println("-----------");
        System.out.println(smg);
        System.out.println("-----------");
    }

    public static int menu() {
        System.out.println("1. Hien thi tat ca phong ban");
        System.out.println("2. Hien thi phong ban theo ten");
        System.out.println("3. Them phong ban");
        System.out.println("4. Sua phong ban");
        int choice = 0;
        choice = scan.nextInt();
        return choice;
    }

    public Department inputDepartment(int choice) {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        Department department = new Department();
        switch (choice) {
            case 2:
                System.out.println("Nhập tên phòng ban cần tìm: ");
                String departmentName = null;
                try {
                    departmentName = scanner.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                department.setDeptName(departmentName);
                break;
            case 3:
                try {
                    System.out.println("Thêm mới phòng ban");

                    System.out.print("Dept_ID: ");
                    int deptId = 0;
                    deptId = Integer.parseInt(scanner.readLine());
                    department.setDeptId(deptId);

                    System.out.print("Dept_Name: ");
                    String deptName = scanner.readLine();
                    department.setDeptName(deptName);

                    System.out.print("Dept_No: ");
                    String deptNo = scanner.readLine();
                    department.setDeptNo(deptNo);

                    System.out.print("Location: ");
                    String location = scanner.readLine();
                    department.setLocation(location);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            case 4:
                try {
                    System.out.println("Sửa thông tin phòng ban");

                    System.out.print("Dept_ID: ");
                    int updatedDeptId = 0;
                    updatedDeptId = Integer.parseInt(scanner.readLine());
                    department.setDeptId(updatedDeptId);

                    System.out.print("Dept_Name: ");
                    String updatedDeptName = scanner.readLine();
                    department.setDeptName(updatedDeptName);

                    System.out.print("Dept_No: ");
                    String updatedDeptNo = scanner.readLine();
                    department.setDeptNo(updatedDeptNo);

                    System.out.print("Location: ");
                    String updatedLocation = scanner.readLine();
                    department.setLocation(updatedLocation);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            default:
                break;
        }
        return department;
    }

    public void exit() {
        this.scan.close();
    }
}
