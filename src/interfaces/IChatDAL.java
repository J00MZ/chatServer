package interfaces;

import java.util.ArrayList;

import DTO.Message;
import DTO.MessageDTO;
import DTO.UserDTO;
import InMemoryDAL.MessageData;


public interface IChatDAL {
	boolean IsUserExists(String username);
	boolean RegisterUser(String username,String name,String password);
	String GetPassword(String username);
	void UpdateUserStatus(String username, boolean status); 
	boolean GetUserStatus(String username);
	ArrayList<UserDTO>getAllUsers();
	boolean IsKeyUserExists(String username);
	boolean IsUserOnline(String username);
	boolean AddMessage(MessageDTO dto);
	ArrayList<Message> GetAllMessages(String key);

}
