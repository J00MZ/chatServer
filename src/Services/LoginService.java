package Services;

import interfaces.ILoginFaildResponse;
import interfaces.ILoginService;
import interfaces.ILoginSucceededResponse;
import interfaces.IRegistrationSucceededResponse;
import interfaces.IRegistrationUserExistsResponse;
import DTO.LoginDTO;
import DTO.LoginResultDTO;
import DTO.RegistrationResultDTO;
import InMemoryDAL.ChatDAL;
import InMemoryDAL.ChatDAL.UserNotExistsException;
import Kivun.Infra.DTO.ServiceMessage;
import Kivun.Infra.Interfaces.IDTO;
import Kivun.Infra.Interfaces.IServiceMessage;


public class LoginService implements ILoginService {

	LoginDTO _dto; 
	ChatDAL _dal; 
	IServiceMessage _response;  
	
	public LoginService(){
		_dal = new ChatDAL(); 
	}
	@Override
	public void set_Params(IDTO dto) {
		// TODO Auto-generated method stub
		_dto = (LoginDTO)dto; 
	}

	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		LoginResultDTO resultDTO = new LoginResultDTO();
		_response = new ServiceMessage();
		resultDTO.set_username(_dto.get_username());
		_response.set_DTO(resultDTO);
		try{
		_dal.UpdateUserStatus(_dto.get_username(),true);
		_response.set_Handler(ILoginSucceededResponse.class);
		}catch(UserNotExistsException e){
			_response.set_Handler(ILoginFaildResponse.class);
		}
	
		
	}

	@Override
	public IServiceMessage get_Response() {
		// TODO Auto-generated method stub
		
		return _response;
	}

	@Override
	public Class<?> get_DTOType() {
		// TODO Auto-generated method stub
		return LoginDTO.class;
	}

}
