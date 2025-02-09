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
public class AreaUsuario1 extends HttpServlet {

    protected String login(Conexion con, HttpServletRequest request, HttpSession sesion) {
        try {
            ResultSet rsSet;
            String rep = request.getParameter("representante");
            String user = request.getParameter("user");
            String pass = request.getParameter("password");
            if (rep == null || rep.isEmpty()) {
                rsSet = con.usuario(user, pass);
            } else {
                rsSet = con.representado(user, pass);
            }
            if (rsSet.next()) {
                sesion.setAttribute("id", rsSet.getInt("id"));
                sesion.setAttribute("adm", rsSet.getBoolean("adm"));
                return "<h3>Bienvenido, " + rsSet.getString("nombreuser") + "</h3>";
            } else {
                return "<html>\n"
                        + "    <head>\n"
                        + "        <title>Gestión de Multas</title>\n"
                        + "        <meta charset=\"UTF-8\">\n"
                        + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "        <link rel=\"stylesheet\" href=\"styles.css\">\n"
                        + "    </head>\n"
                        + "    <body>\n"
                        + "        <div class='tarjeta'><form action=\"areaUsuario1\" method=\"post\">\n"
                        + "            <p>Usuario: <input type=\"text\" name=\"user\"></p>\n"
                        + "            <p>Contraseña: <input type=\"password\" name=\"password\"></p>\n"
                        + "            <p><label><input type=\"checkbox\" name=\"representante\" value=\"1\"> Soy Representante</label></p>\n"
                        + "            <input type=\"submit\" value=\"Enviar\">\n"
                        + "        </form></div>\n"
                        + "    </body>\n"
                        + "</html>";
            }
        } catch (Exception e) {
            return "<p class=\"error\">" + e.getMessage() + "</p>";
        }
    }

    protected String mostrarMultas(Conexion con, HttpServletRequest request, HttpSession sesion) {
        try {
            StringBuilder cadena = new StringBuilder();
            int id = (Integer) sesion.getAttribute("id");
            ResultSet rsSet = con.verMultas(id);
            cadena.append("<p>Multas:<p>");
            while (rsSet.next()) {
                cadena.append("<p>Infracción: ").append(rsSet.getString("tipoinfraccion")).append("</p>");
                cadena.append("<p>Sanción: ").append(rsSet.getDouble("cantidad")).append(" €</p><br>");
            }
            return cadena.toString();
        } catch (Exception e) {
            return "<p class=\"error\">" + e.getMessage() + "</p>";
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        sesion.removeAttribute("id");
        sesion.removeAttribute("adm");
        try (PrintWriter out = response.getWriter()) {
            try {
                Conexion con = new Conexion();
                out.println("<link rel=\"stylesheet\" href=\"styles.css\">");
                out.println(login(con, request, sesion));
                if (sesion.getAttribute("id") != null) {
                    out.println("<div class='tarjeta'><form action='eliminarMultas' method='post'>");
                    out.println(mostrarMultas(con, request, sesion));                 
                    if ((Boolean) sesion.getAttribute("adm")) {
                        out.println("<input type='submit' value='Eliminar multas'>");
                    }
                    out.println("</form></div>");
                }
                con.cerrar();
            } catch (Exception e) {
                out.println("<p class=\"error\">" + e.getMessage() + "</p>");
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
