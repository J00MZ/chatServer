package interfaces;

import java.util.ArrayList;

import DTO.MessageDTO;
import DTO.UserDTO;


public interface IMessagesDAL {
	boolean IsKeyUserExists(String username);
	 
	boolean AddMessage(MessageDTO dto);
	

}
