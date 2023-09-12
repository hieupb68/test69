package controller;

import java.sql.SQLException;

import controller.dao.DAOEmployee;
import controller.utils.ConnectionUtils;
import model.Employee;
import view.EmployeeView;

public class EmployeeControl {
	EmployeeView view;
	DAOEmployee dao;
	Employee[] emlopyees;
	public EmployeeControl(EmployeeView view) {
		try {
			dao = new DAOEmployee(ConnectionUtils.getMyConnection());
			this.view = view;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.dao.closeConnection();
			System.exit(0);
		}
	}

	public void run(){
		int choice = -1;
		do {
			choice = view.menu();
			switch (choice) {
			case 1:
				emlopyees=this.displayAll();
				if(!emlopyees.equals(null)){
					for (int i = 0; i < emlopyees.length; i++) {
						this.view.showMessage(emlopyees[i].toString());
					}
				}
				break;

			case 2:
				Employee e = this.view.input(2);
				emlopyees=this.displayByName(e.getEmpName());
				if(!emlopyees.equals(null)){
					for (int i = 0; i < emlopyees.length; i++) {
						this.view.showMessage(emlopyees[i].toString());
					}
				}
				break;
			case 3: 
				Employee insertE = this.view.input(3);
				int rowCout = this.dao.insert(insertE);
				this.view.showMessage(rowCout+ " row has been inserted");
				break;
			case 4:
				Employee upE = this.view.input(4);
				int updateCount = this.dao.update(upE);
				this.view.showMessage(updateCount+ " row has been inserted");
				break;
			default:
				break;
			}
		} while (choice!=0);
	}
	
	private Employee[] displayAll(){
		return this.dao.selectAll();
	}
	private Employee[] displayByName(String name){
		return this.dao.selectByName(name);
	}
	public void exit(){
		this.dao.closeConnection();
		this.view.exit();
	}
}
