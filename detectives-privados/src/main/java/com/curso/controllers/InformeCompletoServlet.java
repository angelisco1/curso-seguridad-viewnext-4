package com.curso.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.errors.AccessControlException;
import org.owasp.esapi.reference.RandomAccessReferenceMap;

import com.curso.models.Informe;
import com.curso.models.Usuario;
import com.curso.utils.DatabaseUtil;

@WebServlet("/authenticated/informe")
public class InformeCompletoServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
//		String informeId = req.getParameter("id");
		String indirectId = req.getParameter("id");
		Connection conn = null;
		Informe informe = null;
		
		Integer userId = null;
		HttpSession session = req.getSession(false);
		if (session != null) {
			Usuario autor = (Usuario) session.getAttribute("usuario");
			if (autor != null) {
				userId = autor.getId();
			}
		}
		
		RandomAccessReferenceMap armap = (RandomAccessReferenceMap) session.getAttribute("armap");
		String informeId = null;
		try {
			informeId = armap.getDirectReference(indirectId);
		} catch (AccessControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			conn = DatabaseUtil.getConnection();
			
			
			String sql = "SELECT * FROM informes WHERE id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, informeId);
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				String id = rs.getString("id");
				String titulo = rs.getString("titulo");
				String contenido = rs.getString("contenido");
				String descripcion = rs.getString("descripcion");
				String temaColor = rs.getString("temaColor");
				Integer userIdBd = rs.getInt("userId");
				
				if (userIdBd != userId) {
//					Sería mejor un 401/403
					res.sendRedirect("../404.html");
					return;
				}
				
				
				informe = new Informe(id, titulo, contenido, descripcion, temaColor, userId);
				
				
			} else {
				res.sendRedirect("../404.html");
				return;
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeConnection(conn);
		}
		
		req.setAttribute("informe", informe);
		req.getRequestDispatcher("informe.jsp").forward(req, res);
		
	}

}
