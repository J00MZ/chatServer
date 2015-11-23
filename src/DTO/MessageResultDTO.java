package DTO;

import Kivun.Infra.Interfaces.IDTO;

public class MessageResultDTO implements IDTO {
	private String _sender;

	public String set_sender() {
		return _sender;
	}

	public void set_sender(String _sender) {
		this._sender = _sender;
	}
	public String get_message() {
		return _message;
	}

	public void set_message(String _message) {
		this._message = _message;
	}
	private String _message;
}
