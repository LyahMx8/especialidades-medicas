package directorio_medico;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import static directorio_medico.conexion.AbrirDB;
import static directorio_medico.conexion.conn;
public class cargarUsuario extends javax.swing.JFrame {
    private static final Pattern REGEX_CEDULA = Pattern.compile("^\\d{6,20}$");
    private static final Pattern REGEX_NOMBRE = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{3,120}$");
    private static final Pattern REGEX_CORREO = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern REGEX_EDAD = Pattern.compile("^(?:[1-9][0-9]?|1[01][0-9]|120)$");
    private static final Color COLOR_ERROR = new Color(220, 53, 69);
    //Variables de archivos
    String Tipousuario;
    File Archivo;
    String rutaFotoGuardada;
    ImageIcon icon;
    //Variables de objeto
    int mcedula, medad;
    String mnombres;
    char msexo;
    public PreparedStatement instruc = null;
    Statement instruccion;
    private Border bordeCedula;
    private Border bordeNombre;
    private Border bordeCorreo;
    private Border bordeEdad;
    private Border bordePaciente;
    private Border bordeEspecialidad;
    private Border bordeFoto;
    private Color colorSexoNormal;
    Date date = new Date();
    DateFormat hourdateFormat = new SimpleDateFormat("yyyy");
    int fecha = Integer.parseInt(hourdateFormat.format(date));
    public cargarUsuario() {
        initComponents();
        especialBox.setVisible(false);
        Actualizar.setVisible(false);
        AbrirDB();
        updateColumnaTipoUsuario();
        bordeCedula = cedula.getBorder();
        bordeNombre = nombre.getBorder();
        bordeCorreo = correo.getBorder();
        bordeEdad = edad.getBorder();
        bordePaciente = paciente.getBorder();
        bordeEspecialidad = especialidad.getBorder();
        bordeFoto = jButton1.getBorder();
        colorSexoNormal = jLabel3.getForeground();
        configurarValidacionTiempoReal();
        String select = "select NOM_ESPECIALIDAD from ESPECIALIDAD";
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()) { 
                String str1 = rs.getString("NOM_ESPECIALIDAD");
                especialidad.addItem(str1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cargarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sexo = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblFoto = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        paciente = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        hombre = new javax.swing.JRadioButton();
        mujer = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        edad = new javax.swing.JTextField();
        cedula = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        correo = new javax.swing.JTextField();
        especialBox = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        especialidad = new javax.swing.JComboBox<>();
        Registro = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        Actualizar = new javax.swing.JButton();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setText("Cargar Foto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setText("Tipo de usuario");

        paciente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Paciente", "Doctor", "Enfermero" }));
        paciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pacienteActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre");

        jLabel3.setText("Sexo");

        sexo.add(hombre);
        hombre.setText("Hombre");

        sexo.add(mujer);
        mujer.setText("Mujer");

        jLabel4.setText("Edad");

        cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulaActionPerformed(evt);
            }
        });

        jLabel6.setText("Cedula");

        jLabel7.setText("Correo");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paciente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombre)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mujer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hombre, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(edad))
                    .addComponent(cedula)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(correo))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(hombre))
                .addGap(18, 18, 18)
                .addComponent(mujer)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(edad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        especialBox.setBackground(new java.awt.Color(255, 255, 255));
        especialBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel5.setText("Especialidad médica");

        javax.swing.GroupLayout especialBoxLayout = new javax.swing.GroupLayout(especialBox);
        especialBox.setLayout(especialBoxLayout);
        especialBoxLayout.setHorizontalGroup(
            especialBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(especialBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(especialBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(especialBoxLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(especialidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        especialBoxLayout.setVerticalGroup(
            especialBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(especialBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(especialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Registro.setBackground(new java.awt.Color(102, 204, 255));
        Registro.setForeground(new java.awt.Color(255, 255, 255));
        Registro.setText("Registrarse");
        Registro.setBorder(null);
        Registro.setBorderPainted(false);
        Registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistroActionPerformed(evt);
            }
        });

        jButton3.setText("Menú");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Reiniciar");
        jButton4.setBorder(null);

        Actualizar.setBackground(new java.awt.Color(102, 204, 255));
        Actualizar.setForeground(new java.awt.Color(255, 255, 255));
        Actualizar.setText("Actualizar");
        Actualizar.setBorder(null);
        Actualizar.setBorderPainted(false);
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(Registro, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(especialBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(especialBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Registro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int resultado;
        Subida Subir = new Subida();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG", "jpg", "png","jpeg");
        Subir.jFChCargarFoto.setFileFilter(filtro);
        resultado = Subir.jFChCargarFoto.showOpenDialog(null);
        if (JFileChooser.APPROVE_OPTION == resultado) {
            Archivo = Subir.jFChCargarFoto.getSelectedFile();
            icon = new ImageIcon(Archivo.toString());
            Icon icono = new ImageIcon(icon.getImage().getScaledInstance
            (lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
            lblFoto.setText("");
            lblFoto.setIcon(icono);
            lblFoto.updateUI();
            jButton1.setBorder(bordeFoto);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void RegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistroActionPerformed
        if (!validarCamposFormulario(true)) {
            return;
        }

        String sql = "insert into USUARIOS (CEDULA, NOMBRES, SEXO, FOTO, FECHA_NAC, RUTA_FOTO, CORREO, TIPO_USUARIO) values (?,?,?,?,?,?,?,?)";
        String sex = obtenerSexoSeleccionado();
        String tipoUsuario = paciente.getSelectedItem() == null ? "Paciente" : paciente.getSelectedItem().toString().trim();
        int nacimiento = fecha - Integer.parseInt(edad.getText().trim());
        try{
            String rutaFoto = guardarFotoEnDirectorio(cedula.getText().trim());
            File fotoDestino = new File(rutaFoto);
            try (FileInputStream fotoStream = new FileInputStream(fotoDestino)) {
                instruc = conn.prepareStatement(sql);
                instruc.setString(1, cedula.getText());
                instruc.setString(2, nombre.getText());
                instruc.setString(3, sex);
                instruc.setBlob(4, fotoStream);
                instruc.setInt(5, nacimiento);
                instruc.setString(6, rutaFoto);
                instruc.setString(7, correo.getText().trim());
                instruc.setString(8, tipoUsuario);
                instruc.executeUpdate();
            }

            sincronizarEspecialidadUsuario(cedula.getText().trim());
            rutaFotoGuardada = rutaFoto;
            Component panel = null;
            JOptionPane.showMessageDialog(panel, "El usuario "+nombre.getText()+" ha sido agregado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
        } catch (SQLException | HeadlessException | IOException e) {
             JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_RegistroActionPerformed

    private void pacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pacienteActionPerformed
        String tipo = (String)paciente.getSelectedItem();
        switch (tipo){
            case "Paciente":
                especialBox.setVisible(false);
            break;
            case "Doctor":
                especialBox.setVisible(true);
            break;
            case "Enfermero":
                especialBox.setVisible(true);
            break;
        }
    }//GEN-LAST:event_pacienteActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
        Menu VerMenu = new Menu();
        VerMenu.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulaActionPerformed
        String ced = cedula.getText() == null ? "" : cedula.getText().trim();
        cedula.setBorder(bordeCedula);
        if (!REGEX_CEDULA.matcher(ced).matches()) {
            cedula.setBorder(BorderFactory.createLineBorder(COLOR_ERROR));
            return;
        }

        String sql = "SELECT U.NOMBRES, U.FECHA_NAC, U.SEXO, U.RUTA_FOTO, U.CORREO, U.TIPO_USUARIO, E.NOM_ESPECIALIDAD "
            + "FROM USUARIOS U "
            + "LEFT JOIN USUARIO_ESPECIALIDAD UE ON UE.CEDULA = U.CEDULA "
            + "LEFT JOIN ESPECIALIDAD E ON E.ID_ESPECIALIDAD = UE.ID_ESPECIALIDAD "
            + "WHERE U.CEDULA = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ced);
            try (ResultSet xrs = ps.executeQuery()) {
                while (xrs.next()){
                    nombre.setText(xrs.getString("NOMBRES"));
                    edad.setText(xrs.getString("FECHA_NAC"));
                    correo.setText(xrs.getString("CORREO"));

                    String sexoBD = xrs.getString("SEXO");
                    if ("Hombre".equalsIgnoreCase(sexoBD)) {
                        hombre.setSelected(true);
                    } else if ("Mujer".equalsIgnoreCase(sexoBD)) {
                        mujer.setSelected(true);
                    }

                    rutaFotoGuardada = xrs.getString("RUTA_FOTO");
                    mostrarFotoDesdeRuta(rutaFotoGuardada);

                    String tipoUsuario = xrs.getString("TIPO_USUARIO");
                    if (tipoUsuario == null || tipoUsuario.trim().isEmpty()) {
                        tipoUsuario = "Paciente";
                    }

                    if ("Doctor".equalsIgnoreCase(tipoUsuario) || "Enfermero".equalsIgnoreCase(tipoUsuario)) {
                        paciente.setSelectedItem(tipoUsuario);
                        especialBox.setVisible(true);
                    } else {
                        paciente.setSelectedItem("Paciente");
                        especialBox.setVisible(false);
                    }

                    String especialidadUsuario = xrs.getString("NOM_ESPECIALIDAD");
                    if (especialidadUsuario != null && !especialidadUsuario.trim().isEmpty()) {
                        especialidad.setSelectedItem(especialidadUsuario);
                    } else {
                        especialidad.setSelectedIndex(-1);
                    }
                }
                Registro.setVisible(false);
                Actualizar.setVisible(true);
            }
        } catch (SQLException excepcionSql) {
            JOptionPane.showMessageDialog(null, excepcionSql.getMessage(),
                    "Mensaje Prop", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cedulaActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        if (!validarCamposFormulario(false)) {
            return;
        }

        String cedulaTxt = cedula.getText() == null ? "" : cedula.getText().trim();
        String sex = obtenerSexoSeleccionado();
        String tipoUsuario = paciente.getSelectedItem() == null ? "Paciente" : paciente.getSelectedItem().toString().trim();
        int nacimiento = fecha - Integer.parseInt(edad.getText().trim());

        boolean actualizarFoto = Archivo != null;
        String sqlSinFoto = "UPDATE USUARIOS SET NOMBRES = ?, SEXO = ?, FECHA_NAC = ?, CORREO = ?, TIPO_USUARIO = ? WHERE CEDULA = ?";
        String sqlConFoto = "UPDATE USUARIOS SET NOMBRES = ?, SEXO = ?, FECHA_NAC = ?, CORREO = ?, TIPO_USUARIO = ?, FOTO = ?, RUTA_FOTO = ? WHERE CEDULA = ?";

        try {
            if (actualizarFoto) {
                String nuevaRuta = guardarFotoEnDirectorio(cedulaTxt);
                File fotoDestino = new File(nuevaRuta);
                try (FileInputStream fotoStream = new FileInputStream(fotoDestino);
                     PreparedStatement ps = conn.prepareStatement(sqlConFoto)) {
                    ps.setString(1, nombre.getText());
                    ps.setString(2, sex);
                    ps.setInt(3, nacimiento);
                    ps.setString(4, correo.getText().trim());
                    ps.setString(5, tipoUsuario);
                    ps.setBlob(6, fotoStream);
                    ps.setString(7, nuevaRuta);
                    ps.setString(8, cedulaTxt);
                    int filas = ps.executeUpdate();
                    if (filas == 0) {
                        JOptionPane.showMessageDialog(this, "No se encontro un usuario con esa cedula.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    rutaFotoGuardada = nuevaRuta;
                }
            } else {
                try (PreparedStatement ps = conn.prepareStatement(sqlSinFoto)) {
                    ps.setString(1, nombre.getText());
                    ps.setString(2, sex);
                    ps.setInt(3, nacimiento);
                    ps.setString(4, correo.getText().trim());
                    ps.setString(5, tipoUsuario);
                    ps.setString(6, cedulaTxt);
                    int filas = ps.executeUpdate();
                    if (filas == 0) {
                        JOptionPane.showMessageDialog(this, "No se encontro un usuario con esa cedula.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
            }

            sincronizarEspecialidadUsuario(cedulaTxt);

            JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al actualizar", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ActualizarActionPerformed

    private boolean validarCamposFormulario(boolean requiereFoto) {
        limpiarEstadoValidacion();

        String cedulaTxt = cedula.getText() == null ? "" : cedula.getText().trim();
        String nombreTxt = nombre.getText() == null ? "" : nombre.getText().trim();
        String correoTxt = correo.getText() == null ? "" : correo.getText().trim();
        String edadTxt = edad.getText() == null ? "" : edad.getText().trim();
        boolean valido = true;

        if (!REGEX_CEDULA.matcher(cedulaTxt).matches()) {
            cedula.setBorder(BorderFactory.createLineBorder(COLOR_ERROR));
            valido = false;
        }
        if (!REGEX_NOMBRE.matcher(nombreTxt).matches()) {
            nombre.setBorder(BorderFactory.createLineBorder(COLOR_ERROR));
            valido = false;
        }
        if (!REGEX_CORREO.matcher(correoTxt).matches()) {
            correo.setBorder(BorderFactory.createLineBorder(COLOR_ERROR));
            valido = false;
        }
        if (!REGEX_EDAD.matcher(edadTxt).matches()) {
            edad.setBorder(BorderFactory.createLineBorder(COLOR_ERROR));
            valido = false;
        }
        if (!hombre.isSelected() && !mujer.isSelected()) {
            jLabel3.setForeground(COLOR_ERROR);
            valido = false;
        }

        String tipo = paciente.getSelectedItem() == null ? "" : paciente.getSelectedItem().toString().trim();
        if (!("Paciente".equals(tipo) || "Doctor".equals(tipo) || "Enfermero".equals(tipo))) {
            paciente.setBorder(BorderFactory.createLineBorder(COLOR_ERROR));
            valido = false;
        }

        if (("Doctor".equals(tipo) || "Enfermero".equals(tipo)) && especialidad.getSelectedItem() == null) {
            especialidad.setBorder(BorderFactory.createLineBorder(COLOR_ERROR));
            valido = false;
        }

        if (requiereFoto && Archivo == null) {
            jButton1.setBorder(BorderFactory.createLineBorder(COLOR_ERROR));
            valido = false;
        }
        return valido;
    }

    private void limpiarEstadoValidacion() {
        cedula.setBorder(bordeCedula);
        nombre.setBorder(bordeNombre);
        correo.setBorder(bordeCorreo);
        edad.setBorder(bordeEdad);
        paciente.setBorder(bordePaciente);
        especialidad.setBorder(bordeEspecialidad);
        jButton1.setBorder(bordeFoto);
        jLabel3.setForeground(colorSexoNormal);
    }

    private void limpiarFormulario() {
        cedula.setText("");
        nombre.setText("");
        correo.setText("");
        edad.setText("");
        sexo.clearSelection();
        paciente.setSelectedIndex(0);
        especialidad.setSelectedIndex(-1);
        especialBox.setVisible(false);
        lblFoto.setText("");
        lblFoto.setIcon(null);
        Archivo = null;
        rutaFotoGuardada = null;
        Registro.setVisible(true);
        Actualizar.setVisible(false);
        limpiarEstadoValidacion();
    }

    private String obtenerSexoSeleccionado() {
        if (hombre.isSelected()) {
            return hombre.getText();
        }
        return mujer.getText();
    }

    private void configurarValidacionTiempoReal() {
        DocumentListener listenerCampos = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                validarTiempoReal();
            }

            public void removeUpdate(DocumentEvent e) {
                validarTiempoReal();
            }

            public void changedUpdate(DocumentEvent e) {
                validarTiempoReal();
            }
        };

        cedula.getDocument().addDocumentListener(listenerCampos);
        nombre.getDocument().addDocumentListener(listenerCampos);
        correo.getDocument().addDocumentListener(listenerCampos);
        edad.getDocument().addDocumentListener(listenerCampos);

        paciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarTiempoReal();
            }
        });

        especialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarTiempoReal();
            }
        });

        hombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarTiempoReal();
            }
        });

        mujer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarTiempoReal();
            }
        });
    }

    private void validarTiempoReal() {
        String cedulaTxt = cedula.getText() == null ? "" : cedula.getText().trim();
        String nombreTxt = nombre.getText() == null ? "" : nombre.getText().trim();
        String correoTxt = correo.getText() == null ? "" : correo.getText().trim();
        String edadTxt = edad.getText() == null ? "" : edad.getText().trim();
        String tipo = paciente.getSelectedItem() == null ? "" : paciente.getSelectedItem().toString().trim();

        cedula.setBorder(REGEX_CEDULA.matcher(cedulaTxt).matches() || cedulaTxt.isEmpty()
                ? bordeCedula : BorderFactory.createLineBorder(COLOR_ERROR));

        nombre.setBorder(REGEX_NOMBRE.matcher(nombreTxt).matches() || nombreTxt.isEmpty()
                ? bordeNombre : BorderFactory.createLineBorder(COLOR_ERROR));

        correo.setBorder(REGEX_CORREO.matcher(correoTxt).matches() || correoTxt.isEmpty()
                ? bordeCorreo : BorderFactory.createLineBorder(COLOR_ERROR));

        edad.setBorder(REGEX_EDAD.matcher(edadTxt).matches() || edadTxt.isEmpty()
                ? bordeEdad : BorderFactory.createLineBorder(COLOR_ERROR));

        jLabel3.setForeground((hombre.isSelected() || mujer.isSelected()) ? colorSexoNormal : COLOR_ERROR);

        paciente.setBorder(("Paciente".equals(tipo) || "Doctor".equals(tipo) || "Enfermero".equals(tipo))
                ? bordePaciente : BorderFactory.createLineBorder(COLOR_ERROR));

        boolean especialidadRequerida = "Doctor".equals(tipo) || "Enfermero".equals(tipo);
        boolean especialidadValida = !especialidadRequerida || especialidad.getSelectedItem() != null;
        especialidad.setBorder(especialidadValida ? bordeEspecialidad : BorderFactory.createLineBorder(COLOR_ERROR));
    }

    private void updateColumnaTipoUsuario() {
        String actualizarNulos = "UPDATE USUARIOS SET TIPO_USUARIO = 'Paciente' WHERE TIPO_USUARIO IS NULL OR TRIM(TIPO_USUARIO) = ''";
        try (Statement st = conn.createStatement()) {
            st.executeUpdate(actualizarNulos);
        } catch (SQLException ex) {
            Logger.getLogger(cargarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sincronizarEspecialidadUsuario(String cedulaUsuario) throws SQLException {
        String tipo = paciente.getSelectedItem() == null ? "" : paciente.getSelectedItem().toString().trim();
        Integer idEspecialidad = obtenerIdEspecialidadSeleccionada();

        try (PreparedStatement delete = conn.prepareStatement("DELETE FROM USUARIO_ESPECIALIDAD WHERE CEDULA = ?")) {
            delete.setString(1, cedulaUsuario);
            delete.executeUpdate();
        }

        if (("Doctor".equals(tipo) || "Enfermero".equals(tipo)) && idEspecialidad != null) {
            try (PreparedStatement insert = conn.prepareStatement("INSERT INTO USUARIO_ESPECIALIDAD (CEDULA, ID_ESPECIALIDAD) VALUES (?, ?)")) {
                insert.setString(1, cedulaUsuario);
                insert.setInt(2, idEspecialidad);
                insert.executeUpdate();
            }
        }
    }

    private Integer obtenerIdEspecialidadSeleccionada() throws SQLException {
        Object valor = especialidad.getSelectedItem();
        if (valor == null) {
            return null;
        }

        String nombreEspecialidad = valor.toString().trim();
        if (nombreEspecialidad.isEmpty()) {
            return null;
        }

        String sql = "SELECT ID_ESPECIALIDAD FROM ESPECIALIDAD WHERE NOM_ESPECIALIDAD = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombreEspecialidad);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_ESPECIALIDAD");
                }
            }
        }
        return null;
    }

    private String guardarFotoEnDirectorio(String cedulaUsuario) throws IOException {
        File carpeta = new File("src/Fotos");
        if (!carpeta.exists() && !carpeta.mkdirs()) {
            throw new IOException("No se pudo crear el directorio de fotos");
        }

        String nombreOriginal = Archivo.getName();
        String extension = "";
        int punto = nombreOriginal.lastIndexOf('.');
        if (punto >= 0) {
            extension = nombreOriginal.substring(punto).toLowerCase();
        }
        if (extension.isEmpty()) {
            extension = ".jpg";
        }

        String nombreDestino = "USR_" + cedulaUsuario + "_" + System.currentTimeMillis() + extension;
        File destino = new File(carpeta, nombreDestino);
        Files.copy(Archivo.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return destino.getPath();
    }

    private void mostrarFotoDesdeRuta(String rutaFoto) {
        if (rutaFoto == null || rutaFoto.trim().isEmpty()) {
            lblFoto.setIcon(null);
            return;
        }

        File archivoFoto = new File(rutaFoto);
        if (!archivoFoto.exists()) {
            lblFoto.setIcon(null);
            JOptionPane.showMessageDialog(this, "No se encontro la foto en la ruta almacenada: " + rutaFoto, "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ImageIcon localIcon = new ImageIcon(rutaFoto);
        Icon icono = new ImageIcon(localIcon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
        lblFoto.setText("");
        lblFoto.setIcon(icono);
        lblFoto.updateUI();
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(cargarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cargarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cargarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cargarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cargarUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JButton Registro;
    private javax.swing.JTextField cedula;
    private javax.swing.JTextField correo;
    private javax.swing.JTextField edad;
    private javax.swing.JPanel especialBox;
    private javax.swing.JComboBox<String> especialidad;
    private javax.swing.JRadioButton hombre;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JRadioButton mujer;
    private javax.swing.JTextField nombre;
    private javax.swing.JComboBox<String> paciente;
    private javax.swing.ButtonGroup sexo;
    // End of variables declaration//GEN-END:variables
}
