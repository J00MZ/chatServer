package InMemoryDAL;

import java.util.HashMap;

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
	
	public MessageData getUserData(String key){
		return _data.get(key);
	}
	
	public boolean ContainsKey(String key){
		return _data.containsKey(key);
	}
	
	public HashMap<String,MessageData>getAllUsers(){
		return _data;
	}
	
	
	
}
