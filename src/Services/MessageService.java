package Services;

import interfaces.ILoginFaildResponse;
import interfaces.ILoginService;
import interfaces.ILoginSucceededResponse;
import interfaces.ISendingMessageFaildResponse;
import interfaces.ISendingMessageSucceededResponse;
import DTO.LoginDTO;
import DTO.LoginResultDTO;
import DTO.MessageDTO;
import DTO.MessageResultDTO;
import InMemoryDAL.ChatDAL;
import InMemoryDAL.ChatDAL.UserNotExistsException;
import InMemoryDAL.MessagesDAL;
import Kivun.Infra.DTO.ServiceMessage;
import Kivun.Infra.Interfaces.IDTO;
import Kivun.Infra.Interfaces.IServiceMessage;


public class MessageService implements ILoginService {

	MessageDTO _dto; 
	MessagesDAL _dal; 
	IServiceMessage _response;  
	
	public MessageService(){
		_dal = new MessagesDAL(); 
	}
	@Override
	public void set_Params(IDTO dto) {
		// TODO Auto-generated method stub
		_dto = (MessageDTO)dto; 
	}

	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		MessageResultDTO resultDTO = new MessageResultDTO();
		_response = new ServiceMessage();
		resultDTO.set_sender(_dto.get_sender());
		_response.set_DTO(resultDTO);
		try{
		_dal.AddMessage(_dto);
		_response.set_Handler(ISendingMessageSucceededResponse.class);
		}catch(UserNotExistsException e){
			_response.set_Handler(ISendingMessageFaildResponse.class);
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
		return MessageDTO.class;
	}

}
