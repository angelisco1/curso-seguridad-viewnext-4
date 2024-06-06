package com.curso.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.reference.RandomAccessReferenceMap;

import com.curso.models.Informe;
import com.curso.utils.DatabaseUtil;

@WebServlet("/authenticated/informes")
public class InformesServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Connection conn = null;
		List<Informe> informes = new ArrayList<>();
		
		try {
			conn = DatabaseUtil.getConnection();
			
			
			String sql = "SELECT * FROM informes";
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			
			RandomAccessReferenceMap armap = new RandomAccessReferenceMap();
			
			while (rs.next()) {
				String id = rs.getString("id");
				String titulo = rs.getString("titulo");
				String contenido = rs.getString("contenido");
				String descripcion = rs.getString("descripcion");
				Integer userId = rs.getInt("userId");
				String temaColor = rs.getString("temaColor");
				
				String indirectId = armap.addDirectReference(id);
				
				Informe informe = new Informe(indirectId, titulo, contenido, descripcion, temaColor, userId);
				
				informes.add(informe);
			}
			
			HttpSession session = req.getSession(true);
			session.setAttribute("armap", armap);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeConnection(conn);
		}
		
		req.setAttribute("informes", informes);
		req.getRequestDispatcher("informes.jsp").forward(req, res);
		
	}
	
}
