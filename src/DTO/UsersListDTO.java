package DTO;

import java.util.ArrayList;

import Kivun.Infra.Interfaces.IDTO;

public class UsersListDTO implements IDTO {
  private ArrayList<UserDTO> _users;

public ArrayList<UserDTO> get_users() {
	return _users;
}

public void set_users(ArrayList<UserDTO> _users) {
	this._users = _users;
}
}
