package InMemoryDAL;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import DTO.Message;
import DTO.MessageDTO;
import DTO.RegistrationDTO;
import DTO.UserDTO;
import interfaces.IMessagesDAL;

public class MessagesDAL implements IMessagesDAL {

	//UserName will be the key of the hashmap 
	InMemoryMessagesData _data; 
	public  MessagesDAL(){
		_data = InMemoryMessagesData.Instance();
	}
	@Override
	public boolean IsKeyUserExists(String username) {
		// TODO Auto-generated method stub
		return _data.ContainsKey(username);
			
	}

	



	

	public class UserKeyMessagesNotFoundException extends RuntimeException{} 
	public class AddMessageFaildException extends RuntimeException{}
	
	
	@Override
	public boolean AddMessage(MessageDTO dto) throws AddMessageFaildException {
		for(String username: dto.get_recievers()){
			Message m = new Message();
			if(IsKeyUserExists(username)){
			
				
				m.set_message(dto.get_sender());
				m.set_sender(dto.get_sender());
			
				_data.UpdateMessageData(username, m);
			}else{
				MessageData md = new MessageData();
			
				m.set_message(dto.get_sender());
				m.set_sender(dto.get_sender());
				md.Add(m);
				_data.AddMessageData(username, md);
			}
		}
		
		return false;
	}
	@Override
	public MessageData GetAllMessages(String key) {
		if(!IsKeyUserExists(key)){
			throw new UserKeyMessagesNotFoundException();
		}
		return _data.getMessageData(key);
	}

}
