package Services;



import interfaces.IGetAllUsersFaildResponse;
import interfaces.IGetAllUsersSucceededResponse;
import interfaces.IUserNotFoundResponse;
import interfaces.IUsersListService;
import DTO.LoginDTO;
import DTO.UsernameDTO;
import DTO.UsersListResultDTO;
import InMemoryDAL.ChatDAL;
import InMemoryDAL.ChatDAL.UserNotExistsException;
import Kivun.Infra.DTO.ServiceMessage;
import Kivun.Infra.Interfaces.IDTO;
import Kivun.Infra.Interfaces.IServiceMessage;


public class UsersListService implements IUsersListService {

	UsernameDTO _dto; 
	ChatDAL _dal; 
	IServiceMessage _response;  
	
	public UsersListService(){
		_dal = new ChatDAL(); 
	}
	@Override
	public void set_Params(IDTO dto) {
		// TODO Auto-generated method stub
		_dto = (UsernameDTO)dto; 
	}

	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		UsersListResultDTO resultDTO = new UsersListResultDTO();
		_response = new ServiceMessage();	
		try{
		if(_dal.IsUserExists(_dto.get_username())){
			resultDTO.set_users(_dal.getAllUsers());
			_response.set_Handler(IGetAllUsersSucceededResponse.class);
		}else{
			_response.set_Handler(IUserNotFoundResponse.class);
		}
		_response.set_DTO(resultDTO);
	
		}catch(UserNotExistsException e){
			_response.set_Handler(IGetAllUsersFaildResponse.class);
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
		return UsernameDTO.class;
	}

}
