package com.curso.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;

import com.curso.models.Usuario;
import com.curso.utils.DatabaseUtil;

@WebServlet("/authenticated/nuevo-informe")
public class NuevoInformeServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		
		HttpSession session = req.getSession(false);
		String tokenCSRF = UUID.randomUUID().toString();
		session.setAttribute("tokenCSRF", tokenCSRF);
		
		
		req.getRequestDispatcher("nuevo-informe.jsp").forward(req, res);
	}
	

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String titulo = req.getParameter("titulo");
		String descripcion = req.getParameter("descripcion");
		String contenido = req.getParameter("contenido");
		String temaColor = req.getParameter("temaColor");
		
		String tokenCSRFPagina = req.getParameter("tokenCSRF");
		
		String descripcionSaneada = ESAPI.encoder().encodeForHTML(descripcion);
		String temaColorSaneado = ESAPI.encoder().encodeForCSS(temaColor);
		
		
		Integer userId = null;
		
		HttpSession session = req.getSession(false);
		if (session != null) {
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			if (usuario != null) {
				userId = usuario.getId();
			}
		}
		
		String tokenGenerado = (String) session.getAttribute("tokenCSRF");
		if (tokenCSRFPagina == tokenGenerado) {
			res.sendRedirect("../login.html");
			return;
		}
		
		
		Connection conn = null;
		
		try {
			
			conn = DatabaseUtil.getConnection();
			
			String sql = "INSERT INTO informes (titulo, contenido, descripcion, temaColor, userId) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, titulo);
			pst.setString(2, contenido);
//			pst.setString(3, descripcion);
//			pst.setString(4, temaColor);
			pst.setString(3, descripcionSaneada);
			pst.setString(4, temaColorSaneado);
			pst.setInt(5, userId);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeConnection(conn);
		}
		
		res.sendRedirect("informes");
		
	}
	
	
}
