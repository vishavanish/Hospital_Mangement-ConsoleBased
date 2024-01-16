package com.hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class HospitalManager {

	private static final String url="jdbc:mysql://localhost:3306/hospital";
	private static final String user="root";
	private static final String password="Student@123#";

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,user,password);

			Patients p= new Patients(con ,sc);
			Doctors doc= new Doctors(con);
			BookAppointment appointment=new	BookAppointment(p,doc,con, sc);
			while(true) {
				System.out.println("WELCOME TO HOSPITAL MANAGEMENT SYSTEM :) ");
				System.out.println("1. ADD PATIENTS");
				System.out.println("2. VIEW PATIENTS ");
				System.out.println("3. VIEW DOCTORS ");
				System.out.println("4. BOOK APPOINTMENTS");
				System.out.println("5. EXIT ");
				System.out.println("ENTER YOU CHOISE: ");
				int choise=sc.nextInt();
				switch(choise) {
				case 1:
					//add patients
					p.addPatient();
					return;
					
				case 2:
					//view patients
					p.viewPatient();
					return;
					
				case 3:
					//view doctors
					doc.viewPatient();
					return;
					
				case 4:
					//book appointments
					appointment.bookAppointment();
					return;
					
				case 5:
					//exit
					System.exit(choise);
				
				default:
					System.out.println("ENTER THE VALID CHOISE :) ");
					
				}
				
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
