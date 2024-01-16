package com.hospital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Doctors {

		private Connection con;
		//Dcotor constructor 
		public Doctors(Connection con) {
			this.con=con;

		}
		public void viewPatient() {
			String query="select * from doctors";

			try {

				PreparedStatement pst=con.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				//print the data in DB formate
				System.out.println("Doctors:  ");
				System.out.println("+------------+----------------+-----------------+---------------+------------+");
				System.out.println("| Doctors-Id |   Name         | specialization  | Qualification | Experience |");
				System.out.println("+------------+----------------+-----------------+---------------+------------+");
				while(rs.next()) {
					int id=rs.getInt("id");
					String name=rs.getString("name");
					String specialization=rs.getString("specialization");
					String quali=rs.getString("qualification");
					int exp=rs.getInt("experience");
					System.out.printf("|%-12s|%-16s|%-17s|%-15s|%-12s|\n", id,name,specialization,quali,exp);
					System.out.println("+------------+----------------+-----------------+---------------+------------+");
				}

			}
			catch(Exception e) {
				e.printStackTrace();
			}

		}
		public boolean checkDoctorById(int id) {
			String query= "select * from doctors where id=?";
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
