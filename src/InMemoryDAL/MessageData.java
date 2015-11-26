package InMemoryDAL;

import java.util.ArrayList;

import DTO.Message;



public class MessageData {
	private String  _username;
   private ArrayList<Message>_messages;
   public MessageData(){
	   _messages = new  ArrayList<Message>();
   }
	public String get_username() {
		return _username;
	}
	public void set_username(String _username) {
		this._username = _username;
	}
	public ArrayList<Message> get_messages() {
		return _messages;
	}
	public void set_messages(ArrayList<Message> _messages) {
		this._messages = _messages;
	} 
	public void Add(Message message){
		_messages.add(message);
	}
	
}
