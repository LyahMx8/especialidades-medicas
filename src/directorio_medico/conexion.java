package directorio_medico;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
public class conexion {
    //Parametros de conexion a db para usuarios
    static final String CONTROLADOR_JDBC = "com.mysql.cj.jdbc.Driver";
    public static Connection conn = null;
    public static Statement instruccion;
    public static ResultSet rs;
    //Metodo conexion a db para usuarios
    public static void AbrirDB() {
        try {
            DbConfig dbConfig = cargarConfiguracion();
            // cargar clase de controlador de base de datos
            Class.forName(CONTROLADOR_JDBC);
            // establecer conexión a la base de datos
            conn = DriverManager.getConnection(dbConfig.url, dbConfig.username, dbConfig.password);
            //instruccion = conexion.createStatement();
        } catch (IllegalStateException configInvalida) {
            JOptionPane.showMessageDialog(null, configInvalida.getMessage(),
                    "Configuracion invalida", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
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

    private static DbConfig cargarConfiguracion() {
        String contenido = leerEnvJson();
        if (contenido == null || contenido.trim().isEmpty()) {
            throw new IllegalStateException("No se pudo establecer una conexión con la base de datos.");
        }

        String url = extraerValor(contenido, "db_url");
        String username = extraerValor(contenido, "username");
        String password = extraerValor(contenido, "password");

        validarCampo(url, "db_url");
        validarCampo(username, "username");
        validarCampo(password, "password");

        return new DbConfig(url.trim(), username.trim(), password);
    }

    private static String leerEnvJson() {
        try {
            InputStream resource = conexion.class.getResourceAsStream("env.json");
            if (resource != null) {
                try (InputStream in = resource) {
                    byte[] data = in.readAllBytes();
                    return new String(data, StandardCharsets.UTF_8);
                }
            }

            // Fallback para ejecuciones desde raiz del proyecto.
            File local = new File("src/env.json");
            if (local.exists()) {
                byte[] data = Files.readAllBytes(local.toPath());
                return new String(data, StandardCharsets.UTF_8);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "No se pudo leer env.json", JOptionPane.WARNING_MESSAGE);
        }

        return "";
    }

    private static String extraerValor(String json, String clave) {
        String regex = "\\\"?" + Pattern.quote(clave) + "\\\"?\\s*:\\s*\\\"([^\\\"]*)\\\"";
        Matcher matcher = Pattern.compile(regex).matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static void validarCampo(String valor, String nombreCampo) {
        if (nombreCampo != "password" && (valor == null || valor.trim().isEmpty())) {
            throw new IllegalStateException("El campo '" + nombreCampo + "' en env.json es obligatorio.");
        }
    }

    private static class DbConfig {
        private final String url;
        private final String username;
        private final String password;

        private DbConfig(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }
    }
}
