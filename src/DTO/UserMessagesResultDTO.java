package DTO;

import java.util.ArrayList;


import Kivun.Infra.Interfaces.IDTO;

public class UserMessagesResultDTO implements IDTO {
	private String _username;
	
	private  ArrayList<Message> _messages;
    public UserMessagesResultDTO(){
    	_messages = new ArrayList<Message>();
    }
	public ArrayList<Message> get_messages() {
		return _messages;
	}

	public void set_messages(ArrayList<Message> _messages) {
		this._messages = _messages;
	}

	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}
}
