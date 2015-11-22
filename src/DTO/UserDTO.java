package DTO;

import Kivun.Infra.Interfaces.IDTO;

public class UserDTO implements IDTO {
 private String usename;

public String getUsename() {
	return usename;
}

public void setUsename(String usename) {
	this.usename = usename;
}
public boolean getStatus() {
	return status;
}

public void setStatus(boolean status) {
	this.status = status;
}
private boolean status;
}
