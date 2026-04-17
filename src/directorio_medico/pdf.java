package directorio_medico;

import static directorio_medico.conexion.AbrirDB;
import static directorio_medico.conexion.conn;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class pdf {

    public File exportarFichaUsuario(String cedula) throws SQLException, IOException {
        AbrirDB();

        String sql = "SELECT CEDULA, NOMBRES, FECHA_NAC, SEXO, CORREO FROM USUARIOS WHERE CEDULA = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("No se encontro un usuario con cedula " + cedula);
                }

                String ced = rs.getString("CEDULA");
                String nombre = rs.getString("NOMBRES");
                String fechaNac = rs.getString("FECHA_NAC");
                String sexo = rs.getString("SEXO");
                String correo = rs.getString("CORREO");

                File carpeta = new File("src/reportes");
                if (!carpeta.exists() && !carpeta.mkdirs()) {
                    throw new IOException("No se pudo crear la carpeta de reportes");
                }

                File salida = new File(carpeta, "ficha_" + ced + ".html");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(salida))) {
                    writer.write("<!DOCTYPE html>");
                    writer.newLine();
                    writer.write("<html><head><meta charset=\"UTF-8\"><title>Ficha " + ced + "</title></head><body>");
                    writer.newLine();
                    writer.write("<h2>Ficha de Usuario</h2>");
                    writer.newLine();
                    writer.write("<p><strong>Cedula:</strong> " + ced + "</p>");
                    writer.newLine();
                    writer.write("<p><strong>Nombre:</strong> " + nombre + "</p>");
                    writer.newLine();
                    writer.write("<p><strong>Sexo:</strong> " + sexo + "</p>");
                    writer.newLine();
                    writer.write("<p><strong>Correo:</strong> " + (correo == null ? "" : correo) + "</p>");
                    writer.newLine();
                    writer.write("<p><strong>Ano de nacimiento:</strong> " + fechaNac + "</p>");
                    writer.newLine();
                    writer.write("</body></html>");
                }

                return salida;
            }
        }
    }
}
