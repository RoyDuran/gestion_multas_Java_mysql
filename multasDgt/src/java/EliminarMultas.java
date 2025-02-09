/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Roy
 */
public class EliminarMultas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesion = request.getSession();
            try {
                out.println("<link rel=\"stylesheet\" href=\"styles.css\">");
                if (sesion.getAttribute("id") != null) {
                    Conexion conexion=new Conexion();
                    int id=(Integer)sesion.getAttribute("id");
                    ResultSet rsSet = conexion.verMultas(id);
                    out.println("<div class='tarjeta'>");
                    if (!rsSet.next()) {
                        out.println("No hay multas");
                    }
                    int multas = conexion.eliminarMultas(id);
                    out.println("se eliminaron " + multas);
                    out.println("</div>");
                    conexion.cerrar();
                }else response.sendRedirect("index.html");
            } catch (Exception e) {
                out.println(e.getMessage());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
