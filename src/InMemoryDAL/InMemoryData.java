package InMemoryDAL;

import java.util.HashMap;

public class InMemoryData {
	HashMap<String,UserData> _data;
	private static InMemoryData _instance = null; 
	private InMemoryData(){
		_data = new HashMap<String,UserData>(); 
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
	
	
	
	
	
}
