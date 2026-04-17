package directorio_medico;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.RowFilter;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static directorio_medico.conexion.AbrirDB;
import static directorio_medico.conexion.conn;
public class consulta extends javax.swing.JFrame {
    DefaultTableModel TablaUsr;
    DefaultTableModel TablaEsp;
    private TableRowSorter<DefaultTableModel> sorterUsuarios;
    private String filtroBusquedaNombre = "";
    private String filtroEspecialidad = "Todos";
    private boolean filtrarNombresLargos = false;
    private boolean ordenAscendente = true;

    public consulta() {
        TablaUsr = new DefaultTableModel(null, getColumnas());
        TablaEsp = new DefaultTableModel(null, getColumnas2());    
        AbrirDB();
        setUsr();
        setEsp();
        initComponents();
        configurarAccionesListado();
        imprimirActividadStreamNombres();
    }
    
    private String[] getColumnas(){
        String columna[] = {"Cedula","Nombre","Tipo","Nacimiento","Sexo","Correo","Especialidad"};
        return columna;
    }
    private String[] getColumnas2(){
        String columna[] = {"Especialidad"};
        return columna;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBuscarNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbEspecialidad = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cmbOrden = new javax.swing.JComboBox<>();
        chkActividadStream = new javax.swing.JCheckBox();
        btnLimpiarFiltros = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setForeground(new java.awt.Color(102, 102, 102));
        jTable1.setModel(TablaEsp);
        jScrollPane1.setViewportView(jTable1);

        jTable2.setForeground(new java.awt.Color(102, 102, 102));
        jTable2.setModel(TablaUsr);
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Consulta de Datos");

        jLabel4.setText("Filtrar:");

        txtBuscarNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarNombreKeyReleased(evt);
            }
        });

        jLabel5.setText("Especialidad:");

        cmbEspecialidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));
        cmbEspecialidad.addActionListener(this::cmbEspecialidadActionPerformed);

        jLabel6.setText("Orden:");

        cmbOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre A-Z", "Nombre Z-A" }));
        cmbOrden.addActionListener(this::cmbOrdenActionPerformed);

        chkActividadStream.setText("Actividad stream (>5 caracteres)");
        chkActividadStream.addActionListener(this::chkActividadStreamActionPerformed);

        btnLimpiarFiltros.setText("Limpiar");
        btnLimpiarFiltros.addActionListener(this::btnLimpiarFiltrosActionPerformed);

        jLabel2.setText("Usuarios Registrados");

        jLabel3.setText("Especialidades");

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Menú");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkActividadStream)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiarFiltros))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cmbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cmbOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkActividadStream)
                    .addComponent(btnLimpiarFiltros))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }// </editor-fold>//GEN-END:initComponents

    private void setUsr() {
        Statement instruccion;
        String sql = "SELECT U.CEDULA, U.NOMBRES, U.TIPO_USUARIO, U.FECHA_NAC, U.SEXO, U.CORREO, E.NOM_ESPECIALIDAD "
                + "FROM USUARIOS U "
                + "LEFT JOIN USUARIO_ESPECIALIDAD UE ON UE.CEDULA = U.CEDULA "
                + "LEFT JOIN ESPECIALIDAD E ON E.ID_ESPECIALIDAD = UE.ID_ESPECIALIDAD";
        try {
            instruccion = conn.createStatement();
            try (ResultSet xrs = instruccion.executeQuery(sql)) {
                Object datos[] = new Object[7];
                while (xrs.next()) {
                    for (int i = 0; i < 7; i++) {
                        datos[i] = xrs.getObject(i + 1);
                    }
                    TablaUsr.addRow(datos);
                }
                xrs.close();
            }
        } catch (SQLException excepcionSql) {
            JOptionPane.showMessageDialog(null, excepcionSql.getMessage(),
                    "Mensaje Prop", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void setEsp() {
        Statement instruccion;
        String sql = "SELECT NOM_ESPECIALIDAD FROM ESPECIALIDAD";
        try {
            instruccion = conn.createStatement();
            try (ResultSet xrs = instruccion.executeQuery(sql)) {
                Object datos[] = new Object[1];
                while (xrs.next()) {
                    for (int i = 0; i < 1; i++) {
                        datos[i] = xrs.getObject(i + 1);
                    }
                    TablaEsp.addRow(datos);
                }
                xrs.close();
            }
        } catch (SQLException excepcionSql) {
            JOptionPane.showMessageDialog(null, excepcionSql.getMessage(),
                    "Mensaje Prop", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarAccionesListado() {
        sorterUsuarios = new TableRowSorter<>(TablaUsr);
        jTable2.setRowSorter(sorterUsuarios);
        cargarEspecialidadesEnCombo();

        // Reengancha doble clic porque el formulario actual no tiene el listener de mouse declarado.
        jTable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });

        JPopupMenu menu = new JPopupMenu();

        JMenuItem buscar = new JMenuItem("Buscar por nombre");
        buscar.addActionListener(e -> {
            String texto = JOptionPane.showInputDialog(this,
                    "Escribe nombre o fragmento a buscar:",
                    filtroBusquedaNombre);
            if (texto != null) {
                filtroBusquedaNombre = texto.trim();
                aplicarFiltrosYOrden();
            }
        });

        JMenuItem filtrarEsp = new JMenuItem("Filtrar por especialidad");
        filtrarEsp.addActionListener(e -> {
            String[] opciones = obtenerEspecialidadesParaFiltro();
            Object seleccion = JOptionPane.showInputDialog(
                    this,
                    "Selecciona una especialidad:",
                    "Filtro por especialidad",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    filtroEspecialidad);
            if (seleccion != null) {
                filtroEspecialidad = seleccion.toString();
                aplicarFiltrosYOrden();
            }
        });

        JMenuItem activarActividad = new JMenuItem("Aplicar actividad stream (>5 y A-Z)");
        activarActividad.addActionListener(e -> {
            filtrarNombresLargos = true;
            ordenAscendente = true;
            aplicarFiltrosYOrden();
            imprimirActividadStreamNombres();
        });

        JMenuItem ordenarAz = new JMenuItem("Ordenar nombres A-Z");
        ordenarAz.addActionListener(e -> {
            ordenAscendente = true;
            aplicarFiltrosYOrden();
        });

        JMenuItem ordenarZa = new JMenuItem("Ordenar nombres Z-A");
        ordenarZa.addActionListener(e -> {
            ordenAscendente = false;
            aplicarFiltrosYOrden();
        });

        JMenuItem limpiar = new JMenuItem("Quitar filtros");
        limpiar.addActionListener(e -> {
            reiniciarFiltros();
        });

        menu.add(buscar);
        menu.add(filtrarEsp);
        menu.add(activarActividad);
        menu.add(ordenarAz);
        menu.add(ordenarZa);
        menu.addSeparator();
        menu.add(limpiar);

        jTable2.setComponentPopupMenu(menu);
        sincronizarControlesVisuales();
        aplicarFiltrosYOrden();
    }

    private void cargarEspecialidadesEnCombo() {
        cmbEspecialidad.removeAllItems();
        cmbEspecialidad.addItem("Todos");
        for (int i = 0; i < TablaEsp.getRowCount(); i++) {
            Object valor = TablaEsp.getValueAt(i, 0);
            if (valor != null) {
                String esp = valor.toString().trim();
                if (!esp.isEmpty()) {
                    cmbEspecialidad.addItem(esp);
                }
            }
        }
    }

    private void reiniciarFiltros() {
        filtroBusquedaNombre = "";
        filtroEspecialidad = "Todos";
        filtrarNombresLargos = false;
        ordenAscendente = true;
        sincronizarControlesVisuales();
        aplicarFiltrosYOrden();
    }

    private void sincronizarControlesVisuales() {
        txtBuscarNombre.setText(filtroBusquedaNombre);
        cmbEspecialidad.setSelectedItem(filtroEspecialidad);
        cmbOrden.setSelectedIndex(ordenAscendente ? 0 : 1);
        chkActividadStream.setSelected(filtrarNombresLargos);
    }

    private String[] obtenerEspecialidadesParaFiltro() {
        List<String> opciones = new ArrayList<>();
        opciones.add("Todos");
        for (int i = 0; i < TablaEsp.getRowCount(); i++) {
            Object valor = TablaEsp.getValueAt(i, 0);
            if (valor != null) {
                String esp = valor.toString().trim();
                if (!esp.isEmpty() && !opciones.contains(esp)) {
                    opciones.add(esp);
                }
            }
        }
        return opciones.toArray(new String[0]);
    }

    private void aplicarFiltrosYOrden() {
        List<RowFilter<Object, Object>> filtros = new ArrayList<>();

        if (!filtroBusquedaNombre.isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + Pattern.quote(filtroBusquedaNombre), 1));
        }

        if (!"Todos".equalsIgnoreCase(filtroEspecialidad)) {
            filtros.add(RowFilter.regexFilter("(?i)^" + Pattern.quote(filtroEspecialidad) + "$", 6));
        }

        if (filtrarNombresLargos) {
            filtros.add(new RowFilter<Object, Object>() {
                @Override
                public boolean include(RowFilter.Entry<? extends Object, ? extends Object> entry) {
                    Object nombre = entry.getValue(1);
                    return nombre != null && nombre.toString().trim().length() > 5;
                }
            });
        }

        sorterUsuarios.setRowFilter(filtros.isEmpty() ? null : RowFilter.andFilter(filtros));

        SortOrder direccion = ordenAscendente ? SortOrder.ASCENDING : SortOrder.DESCENDING;
        sorterUsuarios.setSortKeys(Arrays.asList(new SortKey(1, direccion)));
        sorterUsuarios.sort();
    }

    private List<String> procesarNombresActividad() {
        List<String> nombresMinuscula = new ArrayList<>();
        for (int i = 0; i < TablaUsr.getRowCount(); i++) {
            Object valorNombre = TablaUsr.getValueAt(i, 1);
            if (valorNombre != null) {
                String nombre = valorNombre.toString().trim();
                if (!nombre.isEmpty()) {
                    nombresMinuscula.add(nombre.toLowerCase(Locale.ROOT));
                }
            }
        }

        return nombresMinuscula.stream()
                .filter(nombre -> nombre.length() > 5)
                .sorted()
                .collect(Collectors.toList());
    }

    private void imprimirActividadStreamNombres() {
        List<String> resultado = procesarNombresActividad();
        System.out.println("=== ACTIVIDAD LAMBDAS Y STREAMS ===");
        System.out.println("Lista final (nombre en minuscula, >5 chars, orden A-Z):");
        resultado.forEach(System.out::println);
        System.out.println("Total: " + resultado.size());
        System.out.println("===================================");
    }

    private void txtBuscarNombreKeyReleased(java.awt.event.KeyEvent evt) {
        filtroBusquedaNombre = txtBuscarNombre.getText().trim();
        aplicarFiltrosYOrden();
    }

    private void cmbEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {
        Object seleccion = cmbEspecialidad.getSelectedItem();
        filtroEspecialidad = seleccion == null ? "Todos" : seleccion.toString();
        aplicarFiltrosYOrden();
    }

    private void cmbOrdenActionPerformed(java.awt.event.ActionEvent evt) {
        ordenAscendente = cmbOrden.getSelectedIndex() != 1;
        aplicarFiltrosYOrden();
    }

    private void chkActividadStreamActionPerformed(java.awt.event.ActionEvent evt) {
        filtrarNombresLargos = chkActividadStream.isSelected();
        aplicarFiltrosYOrden();
        if (filtrarNombresLargos) {
            imprimirActividadStreamNombres();
        }
    }

    private void btnLimpiarFiltrosActionPerformed(java.awt.event.ActionEvent evt) {
        reiniciarFiltros();
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        Menu VerMenu = new Menu();
        VerMenu.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if (evt.getClickCount() != 2) {
            return;
        }

        int fila = jTable2.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario para abrir su ficha.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cedula = String.valueOf(jTable2.getValueAt(fila, 0));
        String nombre = String.valueOf(jTable2.getValueAt(fila, 1));
        String nacimiento = String.valueOf(jTable2.getValueAt(fila, 3));
        String sexo = String.valueOf(jTable2.getValueAt(fila, 4));

        imprimir ficha = new imprimir(nombre, sexo, cedula, nacimiento);
        ficha.setVisible(true);
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int fila = jTable2.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario para exportar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cedula = String.valueOf(jTable2.getValueAt(fila, 0));
        try {
            pdf reporte = new pdf();
            File archivo = reporte.exportarFichaUsuario(cedula);
            JOptionPane.showMessageDialog(this, "Reporte generado en: " + archivo.getAbsolutePath(), "Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al exportar", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new consulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimpiarFiltros;
    private javax.swing.JCheckBox chkActividadStream;
    private javax.swing.JComboBox<String> cmbEspecialidad;
    private javax.swing.JComboBox<String> cmbOrden;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtBuscarNombre;
    // End of variables declaration//GEN-END:variables
}
