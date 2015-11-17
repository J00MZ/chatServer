package InMemoryDAL;

import DTO.RegistrationDTO;

public class UserData {
	RegistrationDTO _registration;
	public RegistrationDTO get_registration() {
		return _registration;
	}
	public void set_registration(RegistrationDTO _registration) {
		this._registration = _registration;
	}
	boolean _status;
	public boolean get_status() {
		return _status;
	}
	public void set_status(boolean _status) {
		this._status = _status;
	} 
	
	
}
