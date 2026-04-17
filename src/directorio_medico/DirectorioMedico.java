package directorio_medico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class DirectorioMedico extends javax.swing.JFrame {

    private DefaultTableModel modelo;

    public DirectorioMedico() {
        initComponents();
        setLocationRelativeTo(null);
        configurarModeloTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cantidadCampo = new javax.swing.JTextField();
        cargarBtn = new javax.swing.JButton();
        cerrarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Directorio Medico (API RandomUser)");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Cantidad de medicos:");

        cantidadCampo.setText("10");

        cargarBtn.setText("Cargar desde API");
        cargarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarBtnActionPerformed(evt);
            }
        });

        cerrarBtn.setText("Cerrar");
        cerrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarBtnActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Genero", "Pais", "Email", "Telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantidadCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cargarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cerrarBtn)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cantidadCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cargarBtn)
                    .addComponent(cerrarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cargarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarBtnActionPerformed
        cargarDirectorio();
    }//GEN-LAST:event_cargarBtnActionPerformed

    private void cerrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cerrarBtnActionPerformed

    private void configurarModeloTabla() {
        modelo = new DefaultTableModel(new String[] {"Nombre", "Genero", "Pais", "Email", "Telefono"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla.setModel(modelo);
    }

    private void cargarDirectorio() {
        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadCampo.getText().trim());
            if (cantidad <= 0 || cantidad > 100) {
                JOptionPane.showMessageDialog(this, "Usa un valor entre 1 y 100.", "Dato invalido", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser numerica.", "Dato invalido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        cargarBtn.setEnabled(false);
        modelo.setRowCount(0);

        final int total = cantidad;
        SwingWorker<List<MedicoInfo>, Void> worker = new SwingWorker<List<MedicoInfo>, Void>() {
            protected List<MedicoInfo> doInBackground() throws Exception {
                return consumirRandomUser(total);
            }

            protected void done() {
                cargarBtn.setEnabled(true);
                try {
                    List<MedicoInfo> medicos = get();
                    for (MedicoInfo medico : medicos) {
                        modelo.addRow(new Object[] {
                            medico.nombre,
                            medico.genero,
                            medico.pais,
                            medico.email,
                            medico.telefono
                        });
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DirectorioMedico.this, "No se pudo cargar la API: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }

    private List<MedicoInfo> consumirRandomUser(int cantidad) throws IOException {
        String endpoint = "https://randomuser.me/api/?results=" + cantidad + "&nat=es,us,mx";
        HttpURLConnection conexion = (HttpURLConnection) new URL(endpoint).openConnection();
        conexion.setRequestMethod("GET");
        conexion.setConnectTimeout(12000);
        conexion.setReadTimeout(12000);

        int status = conexion.getResponseCode();
        InputStream stream = status >= 200 && status < 300 ? conexion.getInputStream() : conexion.getErrorStream();
        String json = leerTexto(stream);

        if (status < 200 || status >= 300) {
            throw new IOException("HTTP " + status + " al consultar RandomUser");
        }

        String resultados = extraerArregloResultados(json);
        if (resultados == null) {
            throw new IOException("Respuesta JSON invalida: no se encontro el arreglo results");
        }

        List<String> objetosUsuario = dividirObjetosJson(resultados);
        List<MedicoInfo> salida = new ArrayList<MedicoInfo>();

        for (String usuarioJson : objetosUsuario) {
            String genero = extraerValorJson(usuarioJson, "gender");
            String email = extraerValorJson(usuarioJson, "email");
            String telefono = extraerValorJson(usuarioJson, "phone");

            String nombre = (extraerValorJson(usuarioJson, "first") + " " + extraerValorJson(usuarioJson, "last")).trim();
            String pais = extraerValorJson(usuarioJson, "country");

            salida.add(new MedicoInfo(nombre, genero, pais, email, telefono));
        }

        return salida;
    }

    private static String leerTexto(InputStream input) throws IOException {
        if (input == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        }
        return builder.toString();
    }

    private static String extraerArregloResultados(String json) {
        int indiceResults = json.indexOf("\"results\"");
        if (indiceResults < 0) {
            return null;
        }

        int inicioArreglo = json.indexOf('[', indiceResults);
        if (inicioArreglo < 0) {
            return null;
        }

        int finArreglo = encontrarCierre(json, inicioArreglo, '[', ']');
        if (finArreglo < 0) {
            return null;
        }

        return json.substring(inicioArreglo + 1, finArreglo);
    }

    private static int encontrarCierre(String texto, int inicio, char abre, char cierra) {
        int profundidad = 0;
        boolean enCadena = false;
        boolean escape = false;

        for (int i = inicio; i < texto.length(); i++) {
            char ch = texto.charAt(i);

            if (escape) {
                escape = false;
                continue;
            }

            if (ch == '\\') {
                escape = true;
                continue;
            }

            if (ch == '"') {
                enCadena = !enCadena;
                continue;
            }

            if (enCadena) {
                continue;
            }

            if (ch == abre) {
                profundidad++;
            } else if (ch == cierra) {
                profundidad--;
                if (profundidad == 0) {
                    return i;
                }
            }
        }

        return -1;
    }

    private static List<String> dividirObjetosJson(String arregloJson) {
        List<String> objetos = new ArrayList<String>();
        int inicioObjeto = -1;
        int profundidad = 0;
        boolean enCadena = false;
        boolean escape = false;

        for (int i = 0; i < arregloJson.length(); i++) {
            char ch = arregloJson.charAt(i);

            if (escape) {
                escape = false;
                continue;
            }

            if (ch == '\\') {
                escape = true;
                continue;
            }

            if (ch == '"') {
                enCadena = !enCadena;
                continue;
            }

            if (enCadena) {
                continue;
            }

            if (ch == '{') {
                if (profundidad == 0) {
                    inicioObjeto = i;
                }
                profundidad++;
            } else if (ch == '}') {
                profundidad--;
                if (profundidad == 0 && inicioObjeto >= 0) {
                    objetos.add(arregloJson.substring(inicioObjeto, i + 1));
                    inicioObjeto = -1;
                }
            }
        }

        return objetos;
    }

    private static String extraerValorJson(String json, String clave) {
        String regex = "\\\"" + Pattern.quote(clave) + "\\\"\\s*:\\s*\\\"((?:\\\\.|[^\\\"\\\\])*)\\\"";
        Matcher matcher = Pattern.compile(regex).matcher(json);
        if (matcher.find()) {
            return desescaparJson(matcher.group(1));
        }
        return "";
    }

    private static String desescaparJson(String valor) {
        return valor
            .replace("\\\\\"", "\"")
            .replace("\\\\/", "/")
            .replace("\\\\n", "\n")
            .replace("\\\\r", "\r")
            .replace("\\\\t", "\t")
            .replace("\\\\\\\\", "\\");
    }

    private static class MedicoInfo {
        private final String nombre;
        private final String genero;
        private final String pais;
        private final String email;
        private final String telefono;

        private MedicoInfo(String nombre, String genero, String pais, String email, String telefono) {
            this.nombre = nombre;
            this.genero = genero;
            this.pais = pais;
            this.email = email;
            this.telefono = telefono;
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DirectorioMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DirectorioMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DirectorioMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DirectorioMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DirectorioMedico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cantidadCampo;
    private javax.swing.JButton cargarBtn;
    private javax.swing.JButton cerrarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
