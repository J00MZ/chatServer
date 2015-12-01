package DTO;

import java.util.ArrayList;

import Kivun.Infra.DTO.Annotations.GetProperty;
import Kivun.Infra.DTO.Annotations.SetProperty;
import Kivun.Infra.Interfaces.IDTO;

public class UsersListResultDTO implements IDTO {
  private ArrayList<UserDTO> _users;

 @GetProperty(PropName = "users")
public ArrayList<UserDTO> get_users() {
	return _users;
}
@SetProperty(PropName = "sender")
public void set_users(ArrayList<UserDTO> _users) {
	this._users = _users;
}
}
