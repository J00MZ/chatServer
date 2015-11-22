package InMemoryDAL;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import DTO.RegistrationDTO;
import DTO.UserDTO;

import interfaces.IMessagesDAL;

public class MessagesDAL implements IMessagesDAL {

	//UserName will be the key of the hashmap 
	InMemoryData _data; 
	public  MessagesDAL(){
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

}
