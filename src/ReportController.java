
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import com.mysql.jdbc.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ReportController implements Initializable {

	@FXML
	private Button SaveBT;

	@FXML
	private ComboBox<String> combBoxR;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		combBoxR.getItems().addAll("Employees", "Customers","last Orders","last Deliverys");

	}

	@FXML
	void makeReport(ActionEvent event) {// throws NumberFormatException, SQLException, ClassNotFoundException {
		Stage stage = new Stage();
		if (combBoxR.getValue().equals("Employees")) {

			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showOpenDialog(stage);
			if (file != null) {
				try { 
					
					FileWriter fileWriter = new FileWriter(file);
					PrintWriter printW = new PrintWriter(fileWriter);
					String SQL = "select * from Employees";
					System.out.println(SQL);

					Connection con = DBConnecter.connectDB();
					Statement state = (Statement) con.createStatement();
					ResultSet rs = state.executeQuery(SQL);
					printW.println(
							"id,vacation days,ename,contact,active,job title,birthday,managerID,salary,did,hire date");
					while (rs.next()) {
						boolean act = false;
						if (rs.getString(5).trim().equals("1"))
							act = true;
						SimpleDateFormat dateFormate = new SimpleDateFormat("dd/MM/yyyy");

						printW.println(Integer.parseInt(rs.getString(1)) + "," + rs.getString(2) + "," + rs.getString(3)
								+ "," + rs.getString(4) + "," + act + "," + rs.getString(6) + ","
								+ dateFormate.format(new java.util.Date(rs.getDate(7).getTime())) + ","
								+ Integer.parseInt(rs.getString(8)) + "," + Double.parseDouble(rs.getString(9)) + ","
								+ Integer.parseInt(rs.getString(10)) + ","
								+ dateFormate.format(new java.util.Date(rs.getDate(11).getTime())) + ","
								+  "");
					}
					printW.close();
					Alert alertLoc = new Alert(AlertType.INFORMATION);
					alertLoc.setTitle("Save Files");
					alertLoc.setHeaderText(" File Save successfully ");
					alertLoc.setContentText("Employee Save successfully to " + file.getName());
					alertLoc.showAndWait();
				} catch (Exception e1) {

					Alert alertLoc = new Alert(AlertType.ERROR);
					alertLoc.setTitle("Save File");
					alertLoc.setHeaderText("Error: Save File !!!");
					alertLoc.setContentText(""+e1.getMessage());
					alertLoc.showAndWait();

				}

			}
		} else if (combBoxR.getValue().equals("Customers")) {
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showOpenDialog(stage);
//			Scanner input= new Scanner(input);
			if (file != null) {
				try {
					FileWriter fileWriter = new FileWriter(file);
				
					PrintWriter printW = new PrintWriter(fileWriter);
					String SQL = "select * from customers";
					System.out.println(SQL);
					printW.println("id,last payment date , Email , last Order , name , Phone , debt");
					Connection con = DBConnecter.connectDB();
					Statement state = (Statement) con.createStatement();
					ResultSet rs = state.executeQuery(SQL);
					while (rs.next()) {

						SimpleDateFormat dateFormate = new SimpleDateFormat("dd/MM/yyyy");

						printW.println(
								rs.getString(1) + "," + dateFormate.format(new java.util.Date(rs.getDate(2).getTime()))
										+ "," + rs.getString(3) + ","
										+ dateFormate.format(new java.util.Date(rs.getDate(4).getTime())) + ","
										+ rs.getString(5) + "," + rs.getString(6) + "," + rs.getString(7) + "");
					}
					printW.close();
					Alert alertLoc = new Alert(AlertType.INFORMATION);
					alertLoc.setTitle("Save Files");
					alertLoc.setHeaderText(" File Save successfully ");
					alertLoc.setContentText("Customer Save successfully to " + file.getName());
					alertLoc.showAndWait();
				} catch (Exception ep) {

					Alert alertLoc = new Alert(AlertType.ERROR);
					alertLoc.setTitle("Save File");
					alertLoc.setHeaderText("Error: Save File !!!");
					alertLoc.setContentText(""+ep.getMessage());
					alertLoc.showAndWait();

				}

			}

		}
		else if (combBoxR.getValue().equals("last Orders")) {
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showOpenDialog(stage);
//			Scanner input= new Scanner(input);
			if (file != null) {
				try {
		            FileWriter fileWriter = new FileWriter(file);

					PrintWriter printW = new PrintWriter(fileWriter);
					String SQL = "select * from Orders";
					System.out.println(SQL);
					
					printW.println("id,order Discount ,Order Date ,total Price ,Department ID,customerID,Employee id,ware house");
					Connection con = DBConnecter.connectDB();
					Statement state = (Statement) con.createStatement();
					ResultSet rs = state.executeQuery(SQL);
					while (rs.next()) {

						SimpleDateFormat dateFormate = new SimpleDateFormat("dd/MM/yyyy");

						printW.println(
								rs.getString(1) +"," + rs.getString(2) + "," + dateFormate.format(new java.util.Date(rs.getDate(3).getTime()))
										+ "," + rs.getString(4) + "," + rs.getString(5) + ","+ rs.getString(6) + ","+ rs.getString(7) + ","
										+ rs.getString(8) + "");
					}
					printW.close();
					Alert alertLoc = new Alert(AlertType.INFORMATION);
					alertLoc.setTitle("Save Files");
					alertLoc.setHeaderText(" File Save successfully ");
					alertLoc.setContentText("Orders Save successfully to " + file.getName());
					alertLoc.showAndWait();
				} catch (Exception ep) {

					Alert alertLoc = new Alert(AlertType.ERROR);
					alertLoc.setTitle("Save File");
					alertLoc.setHeaderText("Error: Save File !!!");
					alertLoc.setContentText(""+ep.getMessage());
					alertLoc.showAndWait();

				}

			}
			
		}
		else if (combBoxR.getValue().equals("last Deliverys")) {
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showOpenDialog(stage);
//			Scanner input= new Scanner(input);
			if (file != null) {
				try {
		            FileWriter fileWriter = new FileWriter(file);

					PrintWriter printW = new PrintWriter(fileWriter);
					String SQL = "select * from Delivery";
					System.out.println(SQL);
					
					printW.println("id ,arrival , delievery status , delievery location");
					Connection con = DBConnecter.connectDB();
					Statement state = (Statement) con.createStatement();
					ResultSet rs = state.executeQuery(SQL);
					while (rs.next()) {

						SimpleDateFormat dateFormate = new SimpleDateFormat("dd/MM/yyyy");

						printW.println(
								rs.getString(1) +","  + dateFormate.format(new java.util.Date(rs.getDate(2).getTime()))
										+ "," + rs.getString(3) + "," + rs.getString(4) + "");
					}
					printW.close();
					Alert alertLoc = new Alert(AlertType.INFORMATION);
					alertLoc.setTitle("Save Files");
					alertLoc.setHeaderText(" File Delivery successfully ");
					alertLoc.setContentText("Orders Save successfully to " + file.getName());
					alertLoc.showAndWait();
				} catch (Exception ep) {

					Alert alertLoc = new Alert(AlertType.ERROR);
					alertLoc.setTitle("Save File");
					alertLoc.setHeaderText("Error: Save File !!!");
					alertLoc.setContentText(""+ep.getMessage());
					alertLoc.showAndWait();

				}

			}
		}
	}

}
