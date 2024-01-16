package com.hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BookAppointment {
	
	private Connection con;
	private Scanner sc;
	private Doctors doc;
	private Patients p;
	//this is constructor when object is create then constructore get called
	public BookAppointment( Patients p, Doctors doc,Connection con, Scanner sc) {
		this.con=con;
		this.sc=sc;
		this.doc=doc;
		this.p=p;
	}
	//here method to book appointment 
	public void bookAppointment() {
		System.out.println("Enter Patient ID ");
		int p_id=sc.nextInt();
		System.out.println("Enter Doctor ID ");
		int doc_id=sc.nextInt();
		
		System.out.println("Enter Appointment Date (date : YYYY-MM-DD) ");
		String appointmentDate=sc.next();
		//get p_id as well as doctor id and then continue...
		if(p.checkPatientById(p_id) && doc.checkDoctorById(doc_id)) {
			
		//check doctor availability doctor available or not 
			if(checkDoctorAvailable(doc_id,appointmentDate)) {
				String appointmentQuery="insert into appointments(patient_id, doctor_id ,appointment_date) values  (? ,? ,?)";
				try {
					PreparedStatement pst=con.prepareStatement(appointmentQuery);
					pst.setInt(1, p_id);
					pst.setInt(2, doc_id);
					pst.setString(3, appointmentDate);
					//preparedstament always execute the query roweffected
					int value=pst.executeUpdate();
					if(value>0) {
						System.out.println("Appointment Booked SuccessFully ! ");
					}
					else {
						System.out.println("Appointment Booking Failed ! ");
					}
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		else{
			System.out.println("Doctor & Patient detail doesn't recorded/exists. ");
		}
	}

	public boolean checkDoctorAvailable(int doc_id,String appointmentDate) {
		String query="select count(*) from appointments where doctor_id=? and appointment_date=?";
		try {
			PreparedStatement pst=con.prepareStatement(query);
			pst.setInt(1, doc_id);
			pst.setString(2, appointmentDate);
			
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				int count=rs.getInt(1);
				if(count==0) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		return false;
	}
}
