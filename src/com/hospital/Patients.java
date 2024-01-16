package com.hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Patients {

	private Connection con;
	private Scanner sc;

	public Patients(Connection con, Scanner sc) {
		this.con=con;
		this.sc=sc;
	}
	public void addPatient() {
		//Enter the patient name (Ans) name will store in DB
		System.out.println("Enter Patient Name: ");
		String name=sc.next();
		//Enter patient Age
		System.out.println("Enter Patient Age: ");
		int age=sc.nextInt();
		//Enter patient gender
		System.out.println("Enter Patient Gender: ");
		String gender=sc.next();
		//enter the patient city
		System.out.println("Enter Patient City: ");
		String city=sc.next();

//-----Query for store data in DB
//-----JDBC setup here-------------------------------------------------------------------------------
		try {
			String query="insert into patients(name, age, gender,city) values(?,?,?,?)";
			//take query as a argument
			PreparedStatement pst=con.prepareStatement(query);
			//preparedstatement has set method then set id name and so on...
			pst.setString(1,name);
			pst.setInt(2, age);
			pst.setString(3, gender);
			pst.setString(4, city);

			//exceute update used to full data insert ddl cammond and return int data
			int count=pst.executeUpdate();
			if(count>0) {
				System.out.println("Patient Added Successfully !! ");
			}
			else {
				System.out.println("Patient Added Successfully !! ");
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	public void viewPatient() {
		String query="select * from patients";

		try {

			PreparedStatement pst=con.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			System.out.println("+------------+-------------+-------+---------+----------+");
			System.out.println("| Patient-Id |     Name    |  Age  |  Gender |   City   |");
			System.out.println("+------------+-------------+-------+---------+----------+");
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				int age=rs.getInt("age");
				String gender=rs.getString("gender");
				String city=rs.getString("city");
				System.out.printf("|%-12s|%-13s|%-7s|%-9s|%-10s|\n",id, name , age, gender,city);
				System.out.println("+------------+-------------+-------+---------+----------+");
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	public boolean checkPatientById(int id) {
		String query= "select * from patients where id=?";
		try {
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
