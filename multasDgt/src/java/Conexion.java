
import java.sql.*;

public class Conexion {

    protected Connection conexion;
    protected PreparedStatement pstmt;

    public Conexion() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://localhost/serviciosdgt", "root", "");
    }

    //metodo que serive para login comparando con una tabla llamada usuarios 
    protected ResultSet usuario(String user, String password) throws Exception {
        pstmt = conexion.prepareStatement("select id,nombreuser,adm from usuarios where nombreuser=? and pass=?");
        pstmt.setString(1, user);
        pstmt.setString(2, password);
        return pstmt.executeQuery();
    }

    protected ResultSet representado(String nombreRep, String password) throws Exception {
        pstmt = conexion.prepareStatement("select r.idusuario as id, u.nombreuser, u.adm from usuarios u inner join representantes r on r.idusuario=u.id where nombrerepresentante=? and password=?");
        pstmt.setString(1, nombreRep);
        pstmt.setString(2, password);
        return pstmt.executeQuery();
    }
    protected ResultSet verMultas(int idusuario) throws Exception{
        pstmt = conexion.prepareStatement("select tipoinfraccion,cantidad from multas where idusuario=?");
        pstmt.setInt(1, idusuario);
        return pstmt.executeQuery();
    }
    protected int eliminarMultas(int idusuario) throws Exception{
        pstmt = conexion.prepareStatement("delete from multas where idusuario=?");
        pstmt.setInt(1, idusuario);
        return pstmt.executeUpdate();
    } 
    public void cerrar() {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Loguear el error
        }
    }    
}
