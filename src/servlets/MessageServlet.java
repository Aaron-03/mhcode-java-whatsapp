package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber; 



@WebServlet("/message")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ACCOUNT_SID = ""; // Tu ACCOUNT SID de Twilio
    public static final String AUTH_TOKEN = ""; // Tu AuthToken de Twilio	

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String number = request.getParameter("number");
		String sms = request.getParameter("message");
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator( 
                new com.twilio.type.PhoneNumber("whatsapp:" + number), 
                new com.twilio.type.PhoneNumber("whatsapp:"), // El n�mero Twilio que les proporcione
                sms)      
            .create();
		
		response.sendRedirect("/mensajes-whatsapp/index.jsp");
	}
}
