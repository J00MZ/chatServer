package DTO;

import java.util.ArrayList;




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
	public ArrayList<Message> get_messages() {
		return _messages;
	}
	@SetProperty(PropName="messages")
	public void set_messages(ArrayList<Message> _messages) {
		this._messages = _messages;
	}
	@SetProperty(PropName="UserName")
	public String get_username() {
		return _username;
	}
	@GetProperty(PropName="UserName")
	public void set_username(String _username) {
		this._username = _username;
	}
}
