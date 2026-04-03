package vistas;

import conexionBD.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class form_visitantes extends javax.swing.JInternalFrame {

    //Conexion
    ConexionBD con = new ConexionBD();
    Connection conx = con.getConnection();

    public form_visitantes() {
        initComponents();
        etq_obligatorio.setVisible(false);
        etq_obligatorio1.setVisible(false);
        etq_obligatori2.setVisible(false);
        etq_obligatori3.setVisible(false);
        etq_obligatori4.setVisible(false);
    }

    /* METODO DE INSERTAR LOS DATOS*/
    public void insertarDatos() {
        try {
            //Se declara una variable para insertar registros a la base de datos
            String insertarSQL = "INSERT INTO visitantes(visi_cedula,visi_nombres,visi_apellidos,visi_telefono,visi_correo,visi_area_dirige,visi_motivo_desc,visi_temperatura,visi_fecha_ingreso,visi_fecha_salida,fk_idusuario) VALUES (?,?,?,?,?,?,?,?,?,?,?);";

            //Se realiza la consulta llamada pst
            PreparedStatement pst = conx.prepareStatement(insertarSQL);

            //Se envia los datos segun el tipo de dato
            pst.setInt(1, Integer.parseInt(txt_cedula.getText()));

            pst.setString(2, txt_nombre.getText());

            pst.setString(3, txt_apellidos.getText());

            pst.setString(4, txt_telefono.getText());

            pst.setString(5, txt_email.getText());

            int Seleccionado_area = cbox_area.getSelectedIndex();
            pst.setString(6, cbox_area.getItemAt(Seleccionado_area));

            pst.setString(7, txt_motivo.getText());

            pst.setString(8, txt_temperatura.getText());
            //  int Seleccionado_tem = cbox_temperatura.getSelectedIndex();
            // pst.setString(8, cbox_temperatura.getItemAt(Seleccionado_tem));

            pst.setString(9, ((JTextField) jdc_fecha_ingreso.getDateEditor().getUiComponent()).getText());

            pst.setString(10, ((JTextField) jdc_fecha_salida.getDateEditor().getUiComponent()).getText());

            int Seleccionado_usua = cbox_usuario.getSelectedIndex();
            pst.setInt(11, Integer.parseInt(cbox_usuario.getItemAt(Seleccionado_usua)));

            //Ejecutamos
            pst.execute();

            //Mensaje de los datos ingresados
            JOptionPane.showMessageDialog(null, "Datos ingresados");

        } catch (Exception e) {
            //Mensaje de error
            JOptionPane.showMessageDialog(null, "Datos no ingresados " + e.getMessage());
        }
    }

    /* METODO DE MOSTRAR DATOS*/
    public void mostrarDatos() {
        //Por medio de un array creamos los titulos de la tabla
        String[] titulos = {"N° Documento", "Nombre", "Apellidos", "Telefono", "Email", "Area Dirije", "Motivo Visita", "Temperatura", "Fecha Ingreso", "Fecha Salida", "Usuario"};

        //Creamos un array para los registros con una longitud del numero de columnas de la tabla
        String[] registros = new String[11];

        //Asignamos los titulos a la tabla
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        //Creamos una variable donde tendremos la sentencia SQL
        //Donde vamos a consultar todo los datos de la tabla visitantes
        String mostrarSQL = "SELECT visitantes.* FROM visitantes;";

        try {
            //Generamos el envio de la consulta SQL
            Statement st = conx.createStatement();

            //Adquirimos el resultado de la consulta
            ResultSet rs = st.executeQuery(mostrarSQL);

            //Creamos un While para que recorra todos los registros de la tabla alumnos
            while (rs.next()) {
                //Queremos que los registros se muestren en JTable
                registros[0] = rs.getString("visi_cedula"); //Donde el nombre que va dentro del getString sea el de la tabla 
                registros[1] = rs.getString("visi_nombres");
                registros[2] = rs.getString("visi_apellidos");
                registros[3] = rs.getString("visi_telefono");
                registros[4] = rs.getString("visi_correo");
                registros[5] = rs.getString("visi_area_dirige");
                registros[6] = rs.getString("visi_motivo_desc");
                registros[7] = rs.getString("visi_temperatura");
                registros[8] = rs.getString("visi_fecha_ingreso");
                registros[9] = rs.getString("visi_fecha_salida");
                registros[10] = rs.getString("fk_idusuario");

                //Enviamos los registros a la JTable
                //Agregamos a modelo las filas q obtenemos de registro
                modelo.addRow(registros);
            }
            //Mandamos el modelo a la tabla
            tabla_Mostrar.setModel(modelo);
        } catch (SQLException e) {
            //En caso de error
            JOptionPane.showMessageDialog(null, "Error al mostrar datos: " + e.getMessage());
        }
    }

    /* METODO BUSCAR DATOS */
    public void mostrarDatosListar(String buscar) {
        //Por medio de un array creamos los titulos de la tabla
        String[] titulos = {"N° Documento", "Nombre", "Apellidos", "Telefono", "Email", "Area Dirije", "Motivo Visita", "Temperatura", "Fecha Ingreso", "Fecha Salida", "Usuario"};

        //Creamos un array para los registros con una longitud del numero de columnas de la tabla
        String[] registros = new String[11];

        //Asignamos los titulos a la tabla
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        //Creamos una variable donde tendremos la sentencia SQL
        //Donde vamos a consultar todo los datos de la tabla visitantes
        String listaSQL = "SELECT visitantes.* FROM visitantes WHERE visi_nombres LIKE'" + buscar + "%' OR visi_apellidos LIKE'" + buscar + "%' ;";

        //Realizamos la visualizacion de la tabla consultada
        try {
            //Generamos el envio de la consulta SQL
            Statement st = conx.createStatement();

            //Adquirimos el resultado de la consulta
            ResultSet rs = st.executeQuery(listaSQL);

            //Creamos un While para que recorra todos los registros de la tabla alumnos
            while (rs.next()) {
                //Queremos que los registros se muestren en JTable
                registros[0] = rs.getString("visi_cedula"); //Donde el nombre que va dentro del getString sea el de la tabla alumnos
                registros[1] = rs.getString("visi_nombres");
                registros[2] = rs.getString("visi_apellidos");
                registros[3] = rs.getString("visi_telefono");
                registros[4] = rs.getString("visi_correo");
                registros[5] = rs.getString("visi_area_dirige");
                registros[6] = rs.getString("visi_motivo_desc");
                registros[7] = rs.getString("visi_temperatura");
                registros[8] = rs.getString("visi_fecha_ingreso");
                registros[9] = rs.getString("visi_fecha_salida");
                registros[10] = rs.getString("fk_idusuario");
                //Enviamos los registros a la JTable
                //Agregamos a modelo las filas q obtenemos de registro
                modelo.addRow(registros);
            }
            //Mandamos el modelo a la tabla
            tabla_Mostrar.setModel(modelo);
        } catch (SQLException e) {
            //En caso de error
            JOptionPane.showMessageDialog(null, "Error al mostrar datos: " + e.getMessage());
        }
    }

    

    /* METODO ELIMINAR DATOS */
    public void eliminarDatos() {
        //Creamos una variable para hacer referencia a nuestra fila seleccionada
        //Guardamos en una variable el punto seleccionado
        int filaSeleccionada = tabla_Mostrar.getSelectedRow();

        //Generamos el capturador de errores
        try {
            //Declaramos una variable tipo string donde almacenaremos 
            //la sentencia SQL getValueAt(filaSeleccionada,0) es el numero de identificacion del alumno
            String eliminarSQL = "DELETE FROM visitantes WHERE visi_cedula=" + tabla_Mostrar.getValueAt(filaSeleccionada, 0) + ";";

            //Creamos un objeto statement
            Statement st = conx.createStatement();

            //ejecutamos la consulta
            int ejecucion = st.executeUpdate(eliminarSQL);

            //Necesitamos crear un mensaje cuando la variable ejecucion se ejecute
            if (ejecucion >= 0) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar al empleado: " + e.getMessage());
        }
    }

    /* METODO LIMPIAR CAJAS */
    public void limpiarCajas() {

        txt_cedula.setText("");
        txt_nombre.setText("");
        txt_apellidos.setText("");
        txt_telefono.setText("");
        txt_email.setText("");
        cbox_area.setSelectedItem(null);
        txt_motivo.setText("");
        txt_temperatura.setText("");
        jdc_fecha_ingreso.setDateFormatCalendar(null);
        jdc_fecha_salida.setDateFormatCalendar(null);
        cbox_usuario.setSelectedItem(null);
    }

    public void validarCampos() {
        if (txt_cedula.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Diligencia los campos Obligatorios", ""
                    + "ERROR", JOptionPane.WARNING_MESSAGE);

            etq_obligatorio.setVisible(true);
        } else if (txt_nombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Diligencia los campos Obligatorios", ""
                    + "ERROR", JOptionPane.WARNING_MESSAGE);
            etq_obligatorio1.setVisible(true);
        } else if (txt_apellidos.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Diligencia los campos Obligatorios", ""
                    + "ERROR", JOptionPane.WARNING_MESSAGE);
            etq_obligatori2.setVisible(true);

        } else if (txt_temperatura.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Diligencia los campos Obligatorios", ""
                    + "ERROR", JOptionPane.WARNING_MESSAGE);
            etq_obligatori3.setVisible(true);

        } else if (txt_motivo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Diligencia los campos Obligatorios", ""
                    + "ERROR", JOptionPane.WARNING_MESSAGE);

            etq_obligatori4.setVisible(true);
        } else {
            etq_obligatorio.setVisible(false);
            etq_obligatorio1.setVisible(false);
            etq_obligatori2.setVisible(false);
            etq_obligatori3.setVisible(false);
            etq_obligatori4.setVisible(false);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_cedula = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        txt_apellidos = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        cbox_area = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_motivo = new javax.swing.JTextArea();
        jdc_fecha_ingreso = new com.toedter.calendar.JDateChooser();
        jdc_fecha_salida = new com.toedter.calendar.JDateChooser();
        cbox_usuario = new javax.swing.JComboBox<>();
        txt_temperatura = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_Mostrar = new javax.swing.JTable();
        etq_obligatorio = new javax.swing.JLabel();
        etq_obligatorio1 = new javax.swing.JLabel();
        etq_obligatorio2 = new javax.swing.JLabel();
        etq_obligatori2 = new javax.swing.JLabel();
        etq_obligatori3 = new javax.swing.JLabel();
        etq_obligatori4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_registrar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_mostrar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 204, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tempsnip.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("DATOS DEL VISITANTE: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("N° Documento *: ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Nombre *:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Apellidos *: ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Teléfono :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Email :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Área Dirige *:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Motivo Visita *:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Temperatura *:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Fecha Ingreso *: ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Fecha Salida: ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Usuario: ");

        cbox_area.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Área gestión", "Área de recursos humanos", "Área comercialización", "Área contabilidad y finanzas", "Área producción", "Área administración", "Área aspectos legales" }));

        txt_motivo.setColumns(20);
        txt_motivo.setRows(5);
        jScrollPane1.setViewportView(txt_motivo);

        jdc_fecha_ingreso.setDateFormatString("yyyy/MM/dd HH:mm:ss");

        jdc_fecha_salida.setDateFormatString("yyyy/MM/dd HH:mm:ss");

        cbox_usuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1005105673", "1005161030", "1095822479", "1098697248", "37747784" }));

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Buscar: ");

        txt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscarActionPerformed(evt);
            }
        });

        tabla_Mostrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Documento", "Nombre", "Apellidos", "Teléfono", "Email", "Área", "Motivo Visita", "Temperatura", "Fecha Ingreso", "Fecha Salida", "Usuario"
            }
        ));
        tabla_Mostrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_MostrarMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_Mostrar);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(28, 28, 28)
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                        .addGap(25, 25, 25))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );

        etq_obligatorio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etq_obligatorio.setForeground(new java.awt.Color(255, 51, 51));
        etq_obligatorio.setText("Campo Obligatorio (*)");

        etq_obligatorio1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etq_obligatorio1.setForeground(new java.awt.Color(255, 51, 51));
        etq_obligatorio1.setText("Campo Obligatorio (*)");

        etq_obligatorio2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etq_obligatorio2.setForeground(new java.awt.Color(255, 51, 51));

        etq_obligatori2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etq_obligatori2.setForeground(new java.awt.Color(255, 51, 51));
        etq_obligatori2.setText("Campo Obligatorio (*)");

        etq_obligatori3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etq_obligatori3.setForeground(new java.awt.Color(255, 51, 51));
        etq_obligatori3.setText("Campo Obligatorio (*)");

        etq_obligatori4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etq_obligatori4.setForeground(new java.awt.Color(255, 51, 51));
        etq_obligatori4.setText("Campo Obligatorio (*)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(165, 165, 165)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etq_obligatorio)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                        .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(etq_obligatori2)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel12)
                                                    .addComponent(jLabel13)
                                                    .addComponent(jLabel14))
                                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jdc_fecha_salida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jdc_fecha_ingreso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txt_temperatura, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cbox_area, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txt_email, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txt_telefono, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txt_cedula, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                                    .addComponent(cbox_usuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txt_apellidos, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addComponent(etq_obligatori4)))))
                                .addComponent(etq_obligatorio1)
                                .addComponent(etq_obligatori3)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 595, Short.MAX_VALUE)
                    .addComponent(etq_obligatorio2)
                    .addGap(0, 596, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel3))
                    .addComponent(jLabel1))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(etq_obligatorio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_cedula, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4))
                        .addGap(3, 3, 3)
                        .addComponent(etq_obligatorio1)
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etq_obligatori2)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbox_area, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etq_obligatori3)
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etq_obligatori4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_temperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                    .addComponent(jdc_fecha_ingreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13))
                            .addComponent(jdc_fecha_salida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cbox_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(232, 232, 232))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 451, Short.MAX_VALUE)
                    .addComponent(etq_obligatorio2)
                    .addGap(0, 452, Short.MAX_VALUE)))
        );

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/visitantes.jpeg"))); // NOI18N

        btn_registrar.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btn_registrar.setText("REGISTRAR");
        btn_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarActionPerformed(evt);
            }
        });

        btn_eliminar.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btn_eliminar.setText("ELIMINAR");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_mostrar.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btn_mostrar.setText("MOSTRAR");
        btn_mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mostrarActionPerformed(evt);
            }
        });

        btn_limpiar.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btn_limpiar.setText("LIMPIAR");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_registrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_eliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_limpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_mostrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addGap(37, 37, 37)
                .addComponent(btn_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btn_mostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarActionPerformed
        validarCampos();
        insertarDatos();
        mostrarDatos();
    }//GEN-LAST:event_btn_registrarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        eliminarDatos();
        mostrarDatos();
        limpiarCajas();
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_mostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mostrarActionPerformed
        mostrarDatos();
    }//GEN-LAST:event_btn_mostrarActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        limpiarCajas();
        mostrarDatos();
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        mostrarDatosListar(txt_buscar.getText());
    }//GEN-LAST:event_txt_buscarActionPerformed

    private void tabla_MostrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_MostrarMouseClicked
        //Guardamos en una variable el punto seleccionado
        int filaSeleccionada = tabla_Mostrar.rowAtPoint(evt.getPoint());

        //Ahora enviamos la informacion a nuestros objetos del formulario
        txt_cedula.setText(tabla_Mostrar.getValueAt(filaSeleccionada, 0).toString());

        //Deshabilitamos caja del numero de identificacion para que el usuario no lo pueda modificar
        txt_cedula.setEnabled(false);

        txt_nombre.setText(tabla_Mostrar.getValueAt(filaSeleccionada, 1).toString());
        txt_apellidos.setText(tabla_Mostrar.getValueAt(filaSeleccionada, 2).toString());
        txt_telefono.setText(tabla_Mostrar.getValueAt(filaSeleccionada, 3).toString());
        txt_email.setText(tabla_Mostrar.getValueAt(filaSeleccionada, 4).toString());
        cbox_area.setSelectedItem(tabla_Mostrar.getValueAt(filaSeleccionada, 5));
        txt_motivo.setText(tabla_Mostrar.getValueAt(filaSeleccionada, 6).toString());
        txt_temperatura.setText(tabla_Mostrar.getValueAt(filaSeleccionada, 7).toString());

        //   jdc_fechaingreso.setText(((tabla_Mostrar.getDateEditor().setUiComponent()(filaSeleccionada, 8).toString()));
        // jdc_fechasalida.setText((tabla_Mostrar.getDate(filaSeleccionada, 9).toString()));
        //  jdc_fechasalida.setString(9, ((JTextField) tabla_Mostrar.getDateEditor().setUiComponent()).toString());
        cbox_usuario.setSelectedItem(tabla_Mostrar.getValueAt(filaSeleccionada, 10).toString());
    }//GEN-LAST:event_tabla_MostrarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_mostrar;
    private javax.swing.JButton btn_registrar;
    private javax.swing.JComboBox<String> cbox_area;
    private javax.swing.JComboBox<String> cbox_usuario;
    private javax.swing.JLabel etq_obligatori2;
    private javax.swing.JLabel etq_obligatori3;
    private javax.swing.JLabel etq_obligatori4;
    private javax.swing.JLabel etq_obligatorio;
    private javax.swing.JLabel etq_obligatorio1;
    private javax.swing.JLabel etq_obligatorio2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdc_fecha_ingreso;
    private com.toedter.calendar.JDateChooser jdc_fecha_salida;
    private javax.swing.JTable tabla_Mostrar;
    private javax.swing.JTextField txt_apellidos;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_cedula;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextArea txt_motivo;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_temperatura;
    // End of variables declaration//GEN-END:variables
}
