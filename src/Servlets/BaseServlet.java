package Servlets;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import Kivun.Infra.Interfaces.IService;
import Kivun.Infra.Interfaces.IServicesFactory;
import Kivun.Infra.Services.ServicesFactory;

abstract public class BaseServlet extends HttpServlet{
	
	protected IServicesFactory _servicesFactory;
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		System.out.println("=====>>>>>Initialized Chat Server");
		_servicesFactory = 		ServicesFactory.Instance();
		super.init(servletConfig);
		Enumeration<String> servicesNames = servletConfig.getInitParameterNames();
		while (servicesNames.hasMoreElements()){
			String serviceName = servicesNames.nextElement();
			try {
				Class<?> iserviceClass = Class.forName(serviceName);
				Class<?> serviceClass = Class.forName(servletConfig.getInitParameter(serviceName));
				_servicesFactory.RegisterClass(iserviceClass,(IService)serviceClass.newInstance());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
