package interfaces;

import java.util.ArrayList;

import DTO.MessageDTO;
import DTO.UserDTO;
import InMemoryDAL.MessageData;


public interface IMessagesDAL {
	boolean IsKeyUserExists(String username);
	 
	boolean AddMessage(MessageDTO dto);
	MessageData GetAllMessages(String key);

}
