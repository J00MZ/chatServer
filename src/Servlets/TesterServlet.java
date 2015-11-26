package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Kivun.Infra.DTO.Annotations.GetProperty;
import Kivun.Infra.Interfaces.IService;
import Kivun.Infra.Interfaces.IServicesFactory;
import Kivun.Infra.Services.ServicesFactory;

public class TesterServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> _serviceList; 
	private final String SERVICENAME = "ServiceName";
	private IServicesFactory _servicesFactory;
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		_servicesFactory = 		ServicesFactory.Instance();
		super.init(servletConfig);
		Enumeration<String> servicesNames = servletConfig.getInitParameterNames();
		_serviceList = new ArrayList<String>();  
		while (servicesNames.hasMoreElements()){
			String serviceName = servicesNames.nextElement();
			try {
				Class<?> iserviceClass = Class.forName(serviceName);
				Class<?> serviceClass = Class.forName(servletConfig.getInitParameter(serviceName));
				_servicesFactory.RegisterClass(iserviceClass,(IService)serviceClass.newInstance());
				_serviceList.add(serviceName);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	private Class<?> getDTO(String serviceName){
		IService service = _servicesFactory.get_Service(serviceName);
		return service.get_DTOType();
		
	}
	private void HandleRequests(HttpServletRequest request, HttpServletResponse response){
		
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		
		sb.append("<script language='javascript'>");
		
		String script = MakeScript(request);
		
		sb.append(script);
		
		sb.append("</script>");
		sb.append("</head>");
		
		String inputFields = MakeInputFields(request);
		String formHeader = ""
				+ "<form action='ChatServlet' onsubmit='DoSomething()' name='myForm' method='POST'>";
		
		sb.append("<body>");
		sb.append(inputFields);
		sb.append(formHeader);
		sb.append("<input type='hidden' name='ServiceMessage'/>");
		sb.append("<input type='submit' />");
		sb.append("</form>");
		sb.append("</body>");
		sb.append("</html>");
		
		try {
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private String MakeInputFields(HttpServletRequest request){
		String serviceName = request.getParameter("ServiceName");
	    Class<?> dtoType = getDTO(serviceName);
	    String propertyPlaceHolder = "$$Property$$";
	    String inputLine  = "$$Property$$:<input type='text' name='$$Property$$'/><br/>";
	    StringBuilder sb = new StringBuilder(); 
	    for (Method m:dtoType.getDeclaredMethods()){
			if (m.isAnnotationPresent(GetProperty.class)){
				GetProperty prop = m.getAnnotation(GetProperty.class);
				String propName = prop.PropName();
				
				String myString = inputLine.replace(propertyPlaceHolder,propName);
				sb.append(myString);
			}
		}
		
	    return sb.toString();
	    
	    
	}
	private String MakeScript(HttpServletRequest request){
		
		
		//generate  html headers and body.
		//generate script
		
		String propertyPlaceHolder = "$$Property$$";
		String serviceNamePlaceHolder = "$$serviceName$$";
		String dtoPlaceHolder  = "$$DTO$$";
		
	    StringBuilder sb = new StringBuilder();
	    String functionHeader = "function DoSomething(){";
	    sb.append(functionHeader);
	    String serviceName = request.getParameter("ServiceName");
	    Class<?> dtoType = getDTO(serviceName);
	    sb.append("var obj = {};");
	    sb.append("var dto = {};"); 
	    String dtoTypeLine = "dto.DTOType = '$$DTO$$';";
	    dtoTypeLine  = dtoTypeLine.replace(dtoPlaceHolder,dtoType.getName());
	    
	    String serviceNameLine = "obj.ServiceName = '$$serviceName$$';";
		serviceNameLine = serviceNameLine.replace(serviceNamePlaceHolder, serviceName);
		
		
	    sb.append(dtoTypeLine );
		sb.append(serviceNameLine);
		
		
		sb.append("obj['DTO'] = dto; ");
		String propLine = "dto['$$Property$$'] =  document.all['$$Property$$'].value;";
		for (Method m:dtoType.getDeclaredMethods()){
			if (m.isAnnotationPresent(GetProperty.class)){
				GetProperty prop = m.getAnnotation(GetProperty.class);
				String propName = prop.PropName();
				String myString = propLine.replace(propertyPlaceHolder,propName);
				
				sb.append(myString);
			}
		}
		
		String formServiceMessage= "document.forms['myForm']['ServiceMessage'].value";
		String jsonData = "JSON.stringify(obj);";
		String formData = formServiceMessage + "=" + jsonData;
		sb.append(formData);
		if (request.getParameter("AlertJson")!=null){
			String alertMsg = "alert("+formServiceMessage+");"; 
			sb.append(alertMsg);  
		}
		sb.append("return true;");
		sb.append("}");
		return sb.toString();
	
	}
	private void GetServiceNames(HttpServletRequest request, HttpServletResponse response){
			String beginHTML = "<html>";
			String beginHEAD = "<head><title>List Of Users and Messaging Services</title>";
			String endHEAD = "</head>";
			String endHTML = "</html>";
			String h1 = "<h1> Services List for Users and Messaging</h1>";
			String beginBody = "<body>"+ h1;
			String endBody = "</body>";
			String body = "";
			for (String serviceName:_serviceList){
				body += "<a href='TesterServlet?ServiceName="+serviceName+"'>"
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
