package SQLServerDAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import interfaces.IChatDAL;

public class ChatDAL implements IChatDAL {

	Connection _connection; 
	public ChatDAL(){
		try {
			String url = "jdbc:odbc:chatDB";
			Connection conn = DriverManager.getConnection(url);
			
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM students");
			while (rs.next()){
				System.out.println(rs.getString("name"));
			}
			if  (!studentExists(conn,"az@oodidactics.com")){
				stmt.executeUpdate("insert into students (id,name,address) select 'az@oodidactics.com','avi','blas alasal '");
			}
			else{
				System.out.println("username or nickname already exists");
			}
			conn.close();
			

		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public boolean IsUserExists(String username) {
		// TODO Auto-generated method stub
		
		int cnt = 0; 
		try {
			Statement stmt = _connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT count(*) as cnt FROM users where username='"+username+"'");
			while (rs.next()){
				cnt = rs.getInt("cnt");
				
			
			}
			_connection.close();
			return cnt == 1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			throw new IsUserExistsException(); 
			
		}
	
	
	}

	@Override
	public void RegisterUser(String username, String name, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String GetPassword(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void UpdateUserStatus(String username, boolean status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean GetUserStatus(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public class IsUserExistsException extends RuntimeException{}

}
