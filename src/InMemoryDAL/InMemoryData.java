package InMemoryDAL;

import java.util.HashMap;

import DTO.Message;

public class InMemoryData {
	HashMap<String,UserData> _data;
	HashMap<String,MessageData> _mData;
	private static InMemoryData _instance = null; 
	private InMemoryData(){
		_data = new HashMap<String,UserData>(); 
		_mData= new HashMap<String,MessageData>(); 
	}
	public static InMemoryData  Instance(){
		if (_instance == null){
			_instance = new InMemoryData(); 
		}
		return _instance;
	}
	
	public void AddUserData(String key,UserData userData){
		_data.put(key, userData);
	}
	
	public UserData getUserData(String key){
		return _data.get(key);
	}
	
	public boolean ContainsKey(String key){
		return _data.containsKey(key);
	}
	
	public HashMap<String,UserData>getAllUsers(){
		return _data;
	}
	public void AddMessageData(String key,MessageData mData){
		_mData.put(key, mData);
	}
	public void UpdateMessageData(String key,Message message){
		MessageData mData =  getMessageData(key);
		mData.Add(message);
	}
	public MessageData getMessageData(String key){
		return _mData.get(key);
	}
	
	public boolean MessaeDataContainsKey(String key){
		return _data.containsKey(key);
	}
	
	public HashMap<String,MessageData>getAllUsersMessages(){
		return _mData;
	}
	
	
}
