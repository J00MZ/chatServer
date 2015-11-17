package Servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Kivun.Infra.DTO.Annotations.GetProperty;
import Kivun.Infra.Interfaces.IService;

public class TesterServlet extends BaseServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> _serviceList; 
	private final String SERVICENAME = "ServiceName";
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		_serviceList = new ArrayList<String>();  
		
		Enumeration<String> servicesNames = servletConfig.getInitParameterNames();
		while (servicesNames.hasMoreElements()){
			String serviceName = servicesNames.nextElement();
				_serviceList.add(serviceName);
		}
	}
		
		
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String serviceName = request.getParameter(SERVICENAME);
		if (serviceName == null){
			GetServiceNames(request,response);
		}
		else{
			HandleRequests(request,response);
			
		}
	}
			
	

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In here POST");
		//HandleRequest(request, response);
	}
	
	public void HandleRequests(HttpServletRequest request, HttpServletResponse response){
		String serviceName = request.getParameter("ServiceName");
		//generate  html headers and body.
		//generate script 
		StringBuilder bodyBuilder = new StringBuilder(); 
		bodyBuilder.append("<body>");
		bodyBuilder.append(MakeInputFields(request));
		bodyBuilder.append("<form action='ChatServlet' onsubmit='DoSomething()' name='myForm' method='POST'>");
		bodyBuilder.append("<input type='hidden' name='ServiceMessage'/>");
		bodyBuilder.append("<input type='submit'/>");
		bodyBuilder.append("</form>");
		bodyBuilder.append("</body>");
		
		try {
			PrintWriter printWriter = (PrintWriter)response.getWriter();
			printWriter.println(bodyBuilder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	
	private String MakeInputFields(HttpServletRequest request){
		String retVal = "";
		String serviceName = request.getParameter(SERVICENAME);
		Class<?> cls = getDTO(serviceName);
		String propPlaceHolder = "!!property!!"; 
		String inputLine = "<input type='text' name='!!property!!'/><br/>";
		StringBuilder sb = new StringBuilder(); 
		for (Method m:cls.getDeclaredMethods()){
			if (m.isAnnotationPresent(GetProperty.class)){
				GetProperty prop = m.getAnnotation(GetProperty.class);
				String propName = prop.PropName();
				String input = inputLine.replace(propPlaceHolder,propName);
				sb.append(input);
			}
		}
		retVal = sb.toString();
		return retVal; 
		
	}
	
	private Class<?> getDTO(String serviceName){
		IService service = _servicesFactory.get_Service(serviceName);
		Class<?> retVal = service.get_DTOType();
		
		return retVal; 
	}
	private void GetServiceNames(HttpServletRequest request, HttpServletResponse response){
			String beginHTML = "<html>";
			String beginHEAD = "<head><title>List Of Chat Services</title>";
			String endHEAD = "</head>";
			String endHTML = "</html>";
			String h1 = "<h1> Services List for Chat</h1>";
			String beginBody = "<body>"+ h1;
			String endBody = "</body>";
			String body = "";
			for (String serviceName:_serviceList){
				body += "<a href='ChatTester?ServiceName="+serviceName+"'>"
						+serviceName+"</a><br/>";
			}
			String html = beginHTML+ beginHEAD + endHEAD + beginBody + body +endBody + endHTML;
			try {
				PrintWriter out = response.getWriter();
				out.println(html);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

					
			
	}

}
