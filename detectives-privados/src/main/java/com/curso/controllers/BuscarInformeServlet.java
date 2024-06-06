package com.curso.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.EncodingException;

import com.curso.models.Informe;
import com.curso.utils.DatabaseUtil;

@WebServlet("/authenticated/buscar-informes")
public class BuscarInformeServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String busqueda = req.getParameter("busqueda");
		
//		Con esto evitamos el XSS Reflected
		String busquedaSaneada = null;
		try {
			busquedaSaneada = ESAPI.encoder().encodeForURL(busqueda);
		} catch (EncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		List<Informe> informes = new ArrayList<>();
		
		try {
			conn = DatabaseUtil.getConnection();
			
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM informes WHERE titulo LIKE ? OR contenido LIKE ?");
//			pst.setString(1, "%"+busqueda+"%");
//			pst.setString(2, "%"+busqueda+"%");
			pst.setString(1, "%"+busquedaSaneada+"%");
			pst.setString(2, "%"+busquedaSaneada+"%");
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String titulo = rs.getString("titulo");
				String contenido = rs.getString("contenido");
				String descripcion = rs.getString("descripcion");
				String temaColor = rs.getString("temaColor");
				Integer userId = rs.getInt("userId");
				
				informes.add(new Informe(id, titulo, contenido, descripcion, temaColor, userId));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeConnection(conn);
		}
		
//		req.setAttribute("busqueda", busqueda);
		req.setAttribute("busqueda", busquedaSaneada);
		req.setAttribute("informes", informes);
		req.getRequestDispatcher("busqueda-informes.jsp").forward(req, res);
	}
}
