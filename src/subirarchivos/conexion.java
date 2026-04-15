package subirarchivos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
public class conexion {
    //Parametros de conexion a db para usuarios
    static final String CONTROLADOR_JDBC = "org.apache.derby.jdbc.ClientDriver";
    static final String URL_BASEDEDATOS = "jdbc:derby://localhost:1527/Archivos";
    private Connection conexion;
    public static Connection conn = null;
    public static Statement instruccion;
    public static ResultSet rs;
    //Metodo conexion a db para usuarios
    public static void AbrirDB() {
        try {
            // cargar clase de controlador de base de datos
            Class.forName(CONTROLADOR_JDBC);
            // establecer conexión a la base de datos
            conn = DriverManager.getConnection(URL_BASEDEDATOS, "root", "12345");
            //instruccion = conexion.createStatement();
        } catch (SQLException excepcionSql) {
            JOptionPane.showMessageDialog(null, excepcionSql.getMessage(),
                    "Error en base de datos2", JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        } // detectar posibles problemas al cargar el controlador de la base de datos
        catch (ClassNotFoundException claseNoEncontrada) {
            JOptionPane.showMessageDialog(null, claseNoEncontrada.getMessage(),
                    "No se encontró el controlador", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
