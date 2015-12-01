package DTO;

import Kivun.Infra.DTO.Annotations.GetProperty;
import Kivun.Infra.DTO.Annotations.SetProperty;
import Kivun.Infra.Interfaces.IDTO;

public class UserDTO implements IDTO {
 private String usename;
 @GetProperty(PropName="username")
public String getUsename() {
	return usename;
}
 @SetProperty(PropName="username")
public void setUsename(String usename) {
	this.usename = usename;
}
 @GetProperty(PropName="status")
public boolean getStatus() {
	return status;
}
@SetProperty(PropName="status")
public void setStatus(boolean status) {
	this.status = status;
}
private boolean status;
}
