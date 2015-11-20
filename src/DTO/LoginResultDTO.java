package DTO;

import Kivun.Infra.Interfaces.IDTO;

public class LoginResultDTO implements IDTO {
	private String _username;

	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}
	
}
