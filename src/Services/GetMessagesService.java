package Services;

import java.util.ArrayList;

import interfaces.IGetMessagesFaildResponse;
import interfaces.IGetMessagesService;
import interfaces.IGetMessagesSucceededResponse;

import interfaces.IUserNotFoundResponse;
import interfaces.IUserNotLoggedinResponse;
import DTO.GetMessagesDTO;

import DTO.Message;
import DTO.UserMessagesResultDTO;
import InMemoryDAL.ChatDAL;
import InMemoryDAL.ChatDAL.UserNotExistsException;
import Kivun.Infra.DTO.ServiceMessage;
import Kivun.Infra.Interfaces.IDTO;
import Kivun.Infra.Interfaces.IServiceMessage;


public class GetMessagesService implements IGetMessagesService {

	GetMessagesDTO _dto; 
	ChatDAL _dal; 
	IServiceMessage _response;  
	
	public GetMessagesService(){
		_dal = new ChatDAL(); 
	}
	@Override
	public void set_Params(IDTO dto) {
		// TODO Auto-generated method stub
		_dto = (GetMessagesDTO)dto; 
	}

	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		UserMessagesResultDTO resultDTO = new UserMessagesResultDTO();
		_response = new ServiceMessage();
		resultDTO.set_username(_dto.get_username());
		
	
		_response.set_DTO(resultDTO);
		try{
			if (_dal.IsUserExists(_dto.get_username())) {
				if (_dal.IsUserOnline(_dto.get_username())) {
			ArrayList<Message> messages=_dal.GetAllMessages(_dto.get_username());
			resultDTO.set_messages(messages);
		_response.set_Handler(IGetMessagesSucceededResponse.class);
		_response.set_DTO(resultDTO);
				}else{
					_response.set_Handler(IUserNotLoggedinResponse.class);
				}
			}else{
				_response.set_Handler(IUserNotFoundResponse.class);
			}
		}catch(UserNotExistsException e){
			_response.set_Handler(IGetMessagesFaildResponse.class);
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
		return GetMessagesDTO.class;
	}

}
