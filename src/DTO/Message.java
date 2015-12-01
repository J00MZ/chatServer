package DTO;

import Kivun.Infra.DTO.Annotations.GetProperty;
import Kivun.Infra.DTO.Annotations.SetProperty;


public class Message {
 private String _sender;
 @GetProperty(PropName="sender",Type="String")
public String get_sender() {
	return _sender;
}
 @SetProperty(PropName="sender")
public void set_sender(String _sender) {
	this._sender = _sender;
}
@GetProperty(PropName="message",Type="String")
public String get_message() {
	return _message;
}
@SetProperty(PropName="message")
public void set_message(String _message) {
	this._message = _message;
}
private String _message;
}
