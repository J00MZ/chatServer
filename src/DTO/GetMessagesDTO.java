package DTO;

import Kivun.Infra.DTO.Annotations.GetProperty;
import Kivun.Infra.DTO.Annotations.SetProperty;
import Kivun.Infra.Interfaces.IDTO;

public class GetMessagesDTO implements IDTO {
	String _username; 
	@GetProperty(PropName="UserName")
	public String get_username() {
		return _username;
	}
	@SetProperty(PropName="UserName")
	public void set_username(String _username) {
		this._username = _username;
	}
	
}
