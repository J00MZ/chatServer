package InMemoryDAL;

import java.util.HashMap;

import DTO.Message;

public class InMemoryMessagesData {
	HashMap<String,MessageData> _data;
	private static InMemoryMessagesData _instance = null; 
	private InMemoryMessagesData(){
		_data = new HashMap<String,MessageData>(); 
	}
	public static InMemoryMessagesData  Instance(){
		if (_instance == null){
			_instance = new InMemoryMessagesData(); 
		}
		return _instance;
	}
	
	public void AddMessageData(String key,MessageData mData){
		_data.put(key, mData);
	}
	public void UpdateMessageData(String key,Message message){
		MessageData mData =  getMessageData(key);
		mData.Add(message);
	}
	public MessageData getMessageData(String key){
		return _data.get(key);
	}
	
	public boolean ContainsKey(String key){
		return _data.containsKey(key);
	}
	
	public HashMap<String,MessageData>getAllUsers(){
		return _data;
	}
	
	
	
}
