package DTO;

import Kivun.Infra.DTO.Annotations.GetProperty;
import Kivun.Infra.DTO.Annotations.SetProperty;
import Kivun.Infra.Interfaces.IDTO;

public class MessageDTO implements IDTO {
	private String _sender;

	@GetProperty(PropName = "sender")
	public String get_sender() {
		return _sender;
	}

	@SetProperty(PropName = "sender")
	public void set_sender(String _sender) {
		this._sender = _sender;
	}

	private String _message;

	@GetProperty(PropName = "message")
	public String get_message() {
		return _message;
	}

	@SetProperty(PropName = "message")
	public void set_message(String _message) {
		this._message = _message;
	}

	private String[] _recievers;

	@GetProperty(PropName = "recievers")
	public String[] get_recievers() {
		return _recievers;
	}

	@SetProperty(PropName = "recievers")
	public void set_recievers(String recieversStr) {
		this._recievers = recieversStr.split(",");
	}

}
