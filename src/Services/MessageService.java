package Services;


import interfaces.IMessageService;
import interfaces.ISendingMessageFaildResponse;
import interfaces.ISendingMessageSucceededResponse;
import interfaces.IUserNotFoundResponse;
import interfaces.IUserNotLoggedinResponse;

import DTO.MessageDTO;
import DTO.MessageResultDTO;
import InMemoryDAL.ChatDAL;
import InMemoryDAL.ChatDAL.UserNotExistsException;
import Kivun.Infra.DTO.ServiceMessage;
import Kivun.Infra.Interfaces.IDTO;
import Kivun.Infra.Interfaces.IServiceMessage;

public class MessageService implements IMessageService {

	MessageDTO _dto;
	ChatDAL _dal;
	IServiceMessage _response;

	public MessageService() {
		_dal = new ChatDAL();
	}

	@Override
	public void set_Params(IDTO dto) {
		// TODO Auto-generated method stub
		_dto = (MessageDTO) dto;
	}

	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		MessageResultDTO resultDTO = new MessageResultDTO();
		_response = new ServiceMessage();
		if (_dal.IsUserExists(_dto.get_sender())) {
			if (_dal.IsUserOnline(_dto.get_sender())) {

				resultDTO.set_sender(_dto.get_sender());
				_response.set_DTO(resultDTO);
				try {
					_dal.AddMessage(_dto);
					_response
							.set_Handler(ISendingMessageSucceededResponse.class);
				} catch (UserNotExistsException e) {
					_response.set_Handler(ISendingMessageFaildResponse.class);
				}
			} else {
				_response.set_Handler(IUserNotLoggedinResponse.class);
				
			}
		} else {
			_response.set_Handler(IUserNotFoundResponse.class);
			
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
