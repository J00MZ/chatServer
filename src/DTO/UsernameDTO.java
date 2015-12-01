package DTO;

import Kivun.Infra.DTO.Annotations.GetProperty;
import Kivun.Infra.DTO.Annotations.SetProperty;
import Kivun.Infra.Interfaces.IDTO;

public class UsernameDTO implements IDTO {
 private String username;
@GetProperty(PropName = "username")
public String get_username() {
	return username;
}
@SetProperty(PropName = "username")
public void set_username(String usename) {
	this.username = usename;
}

}
