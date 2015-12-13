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

import interfaces.IChatDAL;

public class ChatDAL implements IChatDAL {

	//UserName will be the key of the hashmap 
	InMemoryData _data; 
	
	public  ChatDAL(){
		_data = InMemoryData.Instance();
	}
	@Override
	public boolean IsUserExists(String username) {
		// TODO Auto-generated method stub
		return _data.ContainsKey(username);
			
	}
 
	@Override
	public boolean RegisterUser(String username, String name, String password) {
		boolean rs = false;
		if (IsUserExists(username)){
		
			throw new UserExistsException(); 
		}
		else{
			RegistrationDTO registration = new RegistrationDTO();
			registration.set_name(name);
			registration.set_username(username);
			registration.set_password(password);
			UserData userData = new UserData(); 
			userData.set_registration(registration);
			userData.set_status(false);//not logged on
			_data.AddUserData(username, userData);
			rs = true;
			
		}
		return rs;	
		
	}

	@Override
	public String GetPassword(String username) {
		// TODO Auto-generated method stub
		if (IsUserExists(username)){
			UserData userData = _data.getUserData(username);
			return userData.get_registration().get_password();
		}
		else{
			throw new UserNotExistsException();
		}
	}

	@Override
	public void UpdateUserStatus(String username, boolean status) {
		// TODO Auto-generated method stub
		if (IsUserExists(username)){
			UserData userData = _data.getUserData(username);
			userData.set_status(status);
		}
		else{
			throw new UserNotExistsException();
		}
		
	}

	@Override
	public boolean GetUserStatus(String username) {
		// TODO Auto-generated method stub
		if (IsUserExists(username)){
			UserData userData = _data.getUserData(username);
			return userData.get_status();
		}
		else{
			throw new UserNotExistsException();
		}
		
	}
	public class UserExistsException extends RuntimeException{} 
	public class UserNotExistsException extends RuntimeException{}
	@Override
	public ArrayList<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		ArrayList<UserDTO> retVal = new ArrayList<UserDTO>();
		HashMap<String,UserData> data =  _data.getAllUsers();
		Iterator<Entry<String, UserData>> iterator = data.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String,UserData> entry = (Map.Entry<String,UserData>) iterator.next();
			UserDTO userDTO = new UserDTO();
			 userDTO.setUsename(entry.getKey()); 
			UserData ud = entry.getValue();
			userDTO.setStatus(ud.get_status());
			retVal.add(userDTO);
		}
		return retVal;
	}
	@Override
	public boolean IsKeyUserExists(String username) {
		// TODO Auto-generated method stub
		return _data._mData.containsKey(username);
	}
	@Override
	public boolean AddMessage(MessageDTO dto) throws AddMessageFaildException {
	 boolean res = true ; 
		for(String username: dto.get_recievers()){
			if(IsUserExists(username)){
			Message m = new Message();
			if(IsKeyUserExists(username)){
			
				
				m.set_message(dto.get_message());
				m.set_sender(dto.get_sender());
			
				_data.UpdateMessageData(username, m);
			}else{
				MessageData md = new MessageData();
			
				m.set_message(dto.get_message());
				m.set_sender(dto.get_sender());
				md.Add(m);
				_data.AddMessageData(username, md);
			}
		}
		}
		return res;
	}
	@Override
	public ArrayList<Message> GetAllMessages(String key) {
		if(!IsKeyUserExists(key)){
			throw new UserKeyMessagesNotFoundException();
		}
		return _data.getMessageData(key).get_messages();
	}
	public class UserKeyMessagesNotFoundException extends RuntimeException{} 
	public class AddMessageFaildException extends RuntimeException{}
	@Override
	public boolean IsUserOnline(String username) {
		return _data.getUserData(username).get_status();
		
	}
}
