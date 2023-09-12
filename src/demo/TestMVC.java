package demo;


import controller.EmployeeControl;
import view.EmployeeView;

public class TestMVC {
	public static void main(String[] args) {
		// Lấy ra đối tượng Connection kết nối vào DB.
		EmployeeView v = new EmployeeView();
		EmployeeControl control = new EmployeeControl(v);
		control.run();
		control.exit();
	}
}
