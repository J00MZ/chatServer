package interfaces;

public interface IChatDAL {
	boolean IsUserExists(String username);
	boolean RegisterUser(String username,String name,String password);
	String GetPassword(String username);
	void UpdateUserStatus(String username, boolean status); 
	boolean GetUserStatus(String username);

}
