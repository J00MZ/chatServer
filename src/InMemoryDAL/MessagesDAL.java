package InMemoryDAL;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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

	



	

	public class UserExistsException extends RuntimeException{} 
	public class UserNotExistsException extends RuntimeException{}
	
	
	@Override
	public boolean AddMessage(MessageDTO dto) {
		for(String username: dto.get_recievers()){
			Message m = new Message();
			if(IsKeyUserExists(username)){
			
				
				m.set_message(dto.get_sender());
				m.set_sender(dto.get_sender());
			
				_data.UpdateMessageData(username, m);
			}else{
				MessageData md = _data.getMessageData(username);
			
				m.set_message(dto.get_sender());
				m.set_sender(dto.get_sender());
				_data.AddMessageData(username, md);
			}
		}
		
		return false;
	}

}
