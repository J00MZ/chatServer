package InMemoryDAL;



import DTO.RegistrationDTO;
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

}
