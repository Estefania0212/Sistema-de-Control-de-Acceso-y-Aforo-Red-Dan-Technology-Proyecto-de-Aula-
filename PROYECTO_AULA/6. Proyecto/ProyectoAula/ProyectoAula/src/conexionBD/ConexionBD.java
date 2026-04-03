package conexionBD;

//Importacion de las librerias
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import vistas.login;
import java.sql.Connection;

//Clase ConexionBD
public class ConexionBD {

    //Declaramos una variable statica privada ConexionSQL
    private static Connection ConexionSQL;

    public ConexionBD() {

        //Deracion de las variables
        ConexionSQL = null;

        //Base de datos
        String bd = "jdbc:postgresql://localhost:5432/Sistema";

        //Usuario
        String usuario = "postgres";

        //Contraseña que ingresamos al instalar el Gestor 
        String password = "root";

        try {

            Class.forName("org.postgresql.Driver");

            //ConexionSQL = a las variables
            ConexionSQL = DriverManager.getConnection(bd, usuario, password);
            if (ConexionSQL != null) {
                JOptionPane.showMessageDialog(null, "BIENVENID@ !!");
                //    new login().setVisible(true);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error la conectar" + e);
        }

    }
    
    //Retornar la conexion
    public Connection getConnection() {
        return ConexionSQL;
    }

}
