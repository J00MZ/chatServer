package Services;

import interfaces.IRegistrationService;
import interfaces.IRegistrationSucceededResponse;

import interfaces.IRegistrationUserExistsResponse;
import DTO.RegistrationDTO;
import DTO.RegistrationResultDTO;
import InMemoryDAL.ChatDAL;
import Kivun.Infra.DTO.ServiceMessage;
import Kivun.Infra.Interfaces.IDTO;

import Kivun.Infra.Interfaces.IServiceMessage;

public class RegistrationService implements IRegistrationService {

	RegistrationDTO _dto; 
	ChatDAL _dal; 
	IServiceMessage _response;  
	
	public RegistrationService(){
		_dal = new ChatDAL(); 
	}
	@Override
	public void set_Params(IDTO dto) {
		// TODO Auto-generated method stub
		_dto = (RegistrationDTO)dto; 
	}

	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		RegistrationResultDTO resultDTO = new RegistrationResultDTO();
		_response = new ServiceMessage();
		resultDTO.set_username(_dto.get_username());
		_response.set_DTO(resultDTO);
		if (_dal.IsUserExists(_dto.get_username())){
			//user already exists. 

			_response.set_Handler(IRegistrationUserExistsResponse.class);
			
			
		}
		else{
			//registration succeeded
			_response.set_Handler(IRegistrationSucceededResponse.class);
		}
		
	}

	@Override
	public IServiceMessage get_Response() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Class<?> get_DTOType() {
		// TODO Auto-generated method stub
		return RegistrationDTO.class;
	}

}
