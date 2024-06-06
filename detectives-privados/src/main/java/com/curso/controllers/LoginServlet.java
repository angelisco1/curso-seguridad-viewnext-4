package com.curso.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.MySQLCodec;

import com.curso.models.Usuario;
import com.curso.utils.DatabaseUtil;
import com.curso.utils.PasswordUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		String usernameSaneado = ESAPI.encoder().encodeForSQL(new MySQLCodec(MySQLCodec.Mode.STANDARD), username);
		String passwordSaneada = ESAPI.encoder().encodeForSQL(new MySQLCodec(MySQLCodec.Mode.STANDARD), password);
		
		
		Connection conn = null;

			try {
				conn = DatabaseUtil.getConnection();
				
				//String sql = "SELECT * FROM usuarios WHERE username='" + username + "' and password='" + password + "'";
				
				//String sql = "SELECT * FROM usuarios WHERE username='" + usernameSaneado + "' AND password='" + passwordSaneada + "'";
//				System.out.println("[+] Consulta SQL: " + sql);
				
//				String sql = "SELECT * FROM usuarios WHERE username=? AND password=?";
				String sql = "SELECT * FROM usuarios WHERE username=?";
				
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, username);
//				pst.setString(2, password);
				
				System.out.println("[+] Consulta: " + pst);
				
				Statement st = conn.createStatement();
				System.out.println("[+] Consulta: " + st);
				
				
				// ResultSet rs = st.executeQuery(sql);
				ResultSet rs = pst.executeQuery();
				
				if (rs.next()) {
					String hashedPassword = rs.getString("password");
					
					System.out.println("--- Password hasheada: " + hashedPassword);
					
					if (!PasswordUtil.checkPassword(password, hashedPassword)) {
						resp.sendRedirect("login.html");
						return;
					}
					
					HttpSession session = null;
					
					//session = req.getSession();
					session = req.getSession(false);
					
					if (session != null) {
						session.invalidate();
					}
					
					session = req.getSession(true);
					
					
					Integer id = rs.getInt("id");
					String nombre = rs.getString("nombre");
					String web = rs.getString("web");
					String rol = rs.getString("rol");
					
					Usuario usuario = new Usuario(id, nombre, username, null, web, rol);
					System.out.println(usuario);
					
					
					Cookie galleta = new Cookie("mi-cookie", "Una_cookie_con_pepitas_de_chocolate");
					galleta.setHttpOnly(true);
					galleta.setMaxAge(50);
					
					resp.addCookie(galleta);
					
					session.setAttribute("usuario", usuario);
					
					
//					req.setAttribute("usuario", usuario);
//					req.getRequestDispatcher("home.jsp").forward(req, resp);
					
					resp.sendRedirect("authenticated/home.jsp");
					
				} else {
					resp.sendRedirect("login.html");
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DatabaseUtil.closeConnection(conn);
			}
			
		
	}
	
}
