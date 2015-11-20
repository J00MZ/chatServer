package DTO;

import Kivun.Infra.DTO.Annotations.GetProperty;
import Kivun.Infra.DTO.Annotations.SetProperty;
import Kivun.Infra.Interfaces.IDTO;

public class LoginDTO implements IDTO {
	String _username; 
	@GetProperty(PropName="UserName")
	public String get_username() {
		return _username;
	}
	@SetProperty(PropName="UserName")
	public void set_username(String _username) {
		this._username = _username;
	}
	String _password;
	@GetProperty(PropName="Password")
	public String get_password() {
		return _password;
	}
	@SetProperty(PropName="Password")
	public void set_password(String _password) {
		this._password = _password;
	}
}
