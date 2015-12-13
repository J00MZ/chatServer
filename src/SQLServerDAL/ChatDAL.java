package SQLServerDAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.Message;
import DTO.MessageDTO;
import DTO.UserDTO;
import interfaces.IChatDAL;

public class ChatDAL implements IChatDAL {

	Connection _connection;
	String _url;
	public ChatDAL(){
		_url = "jdbc:odbc:ChatDB;Uid=sa;Pwd=Kivun1234";
	}
	@Override
	public boolean IsUserExists(String username) {
		// TODO Auto-generated method stub
		getConnection();
		try {
			Statement stmt = _connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users where username='"+username+"'");
			return rs.next(); 
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			throw new IsUserExistsException(); 
			
		}
		
		finally{
			try {
				_connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new ConnectionCloseException(); 
			}
		}
	
	
	}
	private void  getConnection(){
		try {
			_connection = DriverManager.getConnection(_url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ConnectioOpenException();
		}
	}
	@Override
	public boolean RegisterUser(String username, String name, String password) {
		Statement stmt;
		boolean rs;
		try {
			stmt = _connection.createStatement();
			
			 rs = stmt.execute("INSERT INTO users VALUES('"+username+"','"+name+"','"+password+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rs = false;
		}
		
		return rs;
	}

	@Override
	public String GetPassword(String username) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean GetUserStatus(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public class IsUserExistsException extends RuntimeException{}
	public class ConnectionCloseException extends RuntimeException{}
	public class ConnectioOpenException extends RuntimeException{}
	@Override
	public ArrayList<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean IsKeyUserExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean AddMessage(MessageDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public ArrayList<Message> GetAllMessages(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean IsUserOnline(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean validateuserAndPass(String uname, String pass) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void UpdateUserStatus(String username, String password,
			boolean status) {
		// TODO Auto-generated method stub
		
	} 

}
