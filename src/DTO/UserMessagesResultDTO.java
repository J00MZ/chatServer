package DTO;

import java.util.ArrayList;







import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Kivun.Infra.DTO.Annotations.GetProperty;
import Kivun.Infra.DTO.Annotations.SetProperty;
import Kivun.Infra.Interfaces.IDTO;

public class UserMessagesResultDTO implements IDTO {
	private String _username;
	
	private  ArrayList<Message> _messages;
	
    public UserMessagesResultDTO(){
    	_messages = new ArrayList<Message>();
    }
	@GetProperty(PropName="messages")
	public String get_messages() {
		JSONArray arr= new JSONArray();
		for(Message m: _messages){
			JSONObject obj = new JSONObject();
			try {
				obj.put("sender",m.get_sender());
				obj.put("message",m.get_message());
				arr.put(obj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return arr.toString();
	}
	@SetProperty(PropName="messages")
	public void set_messages(ArrayList<Message> _messages) {
		this._messages = _messages;
	}
	@GetProperty(PropName="UserName",Type="String")
	public String get_username() {
		return _username;
	}
	@SetProperty(PropName="UserName")
	public void set_username(String _username) {
		this._username = _username;
	}
}
