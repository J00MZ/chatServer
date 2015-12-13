package interfaces;



import DTO.MessageDTO;

import InMemoryDAL.MessageData;


public interface IMessagesDAL {
	boolean IsKeyUserExists(String username);
	 
	boolean AddMessage(MessageDTO dto);
	MessageData GetAllMessages(String key);

}
