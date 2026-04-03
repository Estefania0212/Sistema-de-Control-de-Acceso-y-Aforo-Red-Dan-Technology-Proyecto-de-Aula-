package vistas;

import javax.swing.JOptionPane;
import conexionBD.ConexionBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTextField;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

public class form_empleados extends javax.swing.JInternalFrame {

    //Conexion
    ConexionBD con = new ConexionBD();
    Connection conx = con.getConnection();

    public form_empleados() {
        initComponents();
        etq_obligatorio.setVisible(false);
        etq_obligatorio1.setVisible(false);
        etq_obligatorio2.setVisible(false);
        etq_obligatorio3.setVisible(false);

    }

    /* METODO DE INSERTAR LOS DATOS*/
    public void insertarDatos() {
        try {
            //Se declara una variable para insertar registros a la base de datos
            String insertarSQL = "INSERT INTO empleados(empl_cedula,empl_nombres,empl_apellidos,empl_telefono,empl_correo,empl_area_tra,empl_temperatura,empl_fecha_ingreso,empl_fecha_salida,fk_idusuario) VALUES (?,?,?,?,?,?,?,?,?,?);";

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

            pst.setString(7, txt_temperatura.getText());

            //   int Seleccionado_tem = cbox_temperatura.getSelectedIndex();
            //   pst.setString(7, txt_temperatura.getItemAt(Seleccionado_tem));
            pst.setString(8, ((JTextField) jdc_fechaingreso.getDateEditor().getUiComponent()).getText());

            pst.setString(9, ((JTextField) jdc_fechasalida.getDateEditor().getUiComponent()).getText());

            int Seleccionado_usua = cbox_usuario.getSelectedIndex();
            pst.setInt(10, Integer.parseInt(cbox_usuario.getItemAt(Seleccionado_usua)));

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
        String[] titulos = {"N° Documento", "Nombre", "Apellidos", "Telefono", "Email", "Area de Trabajo", "Temperatura", "Fecha Ingreso", "Fecha Salida", "Usuario"};

        //Creamos un array para los registros con una longitud del numero de columnas de la tabla
        String[] registros = new String[10];

        //Asignamos los titulos a la tabla
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        //Creamos una variable donde tendremos la sentencia SQL
        //Donde vamos a consultar todo los datos de la tabla empleados
        String mostrarSQL = "SELECT empleados.* FROM empleados;";

        try {
            //Generamos el envio de la consulta SQL
            Statement st = conx.createStatement();

            //Adquirimos el resultado de la consulta
            ResultSet rs = st.executeQuery(mostrarSQL);

            //Creamos un While para que recorra todos los registros de la tabla empleados
            while (rs.next()) {

                //Queremos que los registros se muestren en JTable llamado tabla_Mostrar
                registros[0] = rs.getString("empl_cedula"); //Donde el nombre que va dentro del getString sea el de la tabla 
                registros[1] = rs.getString("empl_nombres");
                registros[2] = rs.getString("empl_apellidos");
                registros[3] = rs.getString("empl_telefono");
                registros[4] = rs.getString("empl_correo");
                registros[5] = rs.getString("empl_area_tra");
                registros[6] = rs.getString("empl_temperatura");
                registros[7] = rs.getString("empl_fecha_ingreso");
                registros[8] = rs.getString("empl_fecha_salida");
                registros[9] = rs.getString("fk_idusuario");

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
        String[] titulos = {"N° Documento", "Nombre", "Apellidos", "Telefono", "Email", "Area de Trabajo", "Temperatura", "Fecha Ingreso", "Fecha Salida", "Usuario"};

        //Creamos un array para los registros con una longitud del numero de columnas de la tabla
        String[] registros = new String[10];

        //Asignamos los titulos a la tabla
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        //Creamos una variable donde tendremos la sentencia SQL
        //Donde vamos a consultar todo los datos de la tabla empleados
        String listaSQL = "SELECT empleados.* FROM empleados WHERE empl_nombres LIKE'" + buscar + "%' OR empl_apellidos LIKE'" + buscar + "%' ;";

        try {
            //Generamos el envio de la consulta SQL
            Statement st = conx.createStatement();

            //Adquirimos el resultado de la consulta
            ResultSet rs = st.executeQuery(listaSQL);

            //Creamos un While para que recorra todos los registros de la tabla alumnos
            while (rs.next()) {

                //Queremos que los registros se muestren en JTable
                //Donde el nombre que va dentro del getString sea el de la tabla alumnos
                registros[0] = rs.getString("empl_cedula");
                registros[1] = rs.getString("empl_nombres");
                registros[2] = rs.getString("empl_apellidos");
                registros[3] = rs.getString("empl_telefono");
                registros[4] = rs.getString("empl_correo");
                registros[5] = rs.getString("empl_area_tra");
                registros[6] = rs.getString("empl_temperatura");
                registros[7] = rs.getString("empl_fecha_ingreso");
                registros[8] = rs.getString("empl_fecha_salida");
                registros[9] = rs.getString("fk_idusuario");

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
            String eliminarSQL = "DELETE FROM empleados WHERE empl_cedula=" + tabla_Mostrar.getValueAt(filaSeleccionada, 0) + ";";

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
        txt_temperatura.setText("");
        jdc_fechaingreso.setDateFormatCalendar(null);
        jdc_fechasalida.setDateFormatCalendar(null);
        cbox_usuario.setSelectedItem(null);
    }

    public void validarCampos() {
        if (txt_cedula.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Diligencia los campos Obligatorios!!!", ""
                    + "ERROR", JOptionPane.WARNING_MESSAGE);
            etq_obligatorio.setVisible(true);

        } else if (txt_nombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Diligencia los campos Obligatorios!!!", ""
                    + "ERROR", JOptionPane.WARNING_MESSAGE);
            etq_obligatorio1.setVisible(true);

        } else if (txt_apellidos.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Diligencia los campos Obligatorios!!!", ""
                    + "ERROR", JOptionPane.WARNING_MESSAGE);
            etq_obligatorio2.setVisible(true);
            
        } else if (txt_temperatura.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Diligencia los campos Obligatorios!!!", ""
                    + "ERROR", JOptionPane.WARNING_MESSAGE);
            etq_obligatorio3.setVisible(true);
            
        } else {
            etq_obligatorio.setVisible(false);
            etq_obligatorio1.setVisible(false);
            etq_obligatorio2.setVisible(false);
            etq_obligatorio3.setVisible(false);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_insertar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_mostrar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_cedula = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        txt_apellidos = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        cbox_area = new javax.swing.JComboBox<>();
        jdc_fechaingreso = new com.toedter.calendar.JDateChooser();
        jdc_fechasalida = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cbox_usuario = new javax.swing.JComboBox<>();
        txt_temperatura = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_Mostrar = new javax.swing.JTable();
        etq_obligatorio = new javax.swing.JLabel();
        etq_obligatorio1 = new javax.swing.JLabel();
        etq_obligatorio2 = new javax.swing.JLabel();
        etq_obligatorio3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tempsnip.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));

        btn_insertar.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btn_insertar.setText("REGISTRAR ");
        btn_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertarActionPerformed(evt);
            }
        });

        btn_eliminar.setBackground(new java.awt.Color(153, 153, 153));
        btn_eliminar.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btn_eliminar.setText("ELIMINAR");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/empleado.jpeg"))); // NOI18N
        jLabel1.setText("IMAGEN");

        btn_mostrar.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
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
            .addComponent(btn_insertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_eliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_mostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_limpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btn_insertar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btn_mostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("N° Documento : *");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Apellidos: * ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Nombres: * ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Email :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Area de Trabajo : *");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Teléfono: ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Temperatura : * ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Fecha Ingreso: * ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Fecha Salida:");

        txt_cedula.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_apellidos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_telefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });

        cbox_area.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbox_area.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Área gestión", "Área de recursos humanos", "Área comercialización", "Área contabilidad y finanzas", "Área producción", "Área administración", "Área aspectos legales" }));
        cbox_area.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_areaActionPerformed(evt);
            }
        });

        jdc_fechaingreso.setDateFormatString("yyyy/MM/dd HH:mm:ss");

        jdc_fechasalida.setDateFormatString("yyyy/MM/dd HH:mm:ss");

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 782, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Usuario *: ");

        cbox_usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbox_usuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1005105673", "1005161030", "1095822479", "1098697248", "37747784" }));

        jLabel13.setText("BUSCAR: ");

        txt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscarActionPerformed(evt);
            }
        });

        tabla_Mostrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Documento", "Nombre", "Apellidos", "Teléfono", "Email", "Área de Trabajo", "Temperatura", "Fecha Ingreso", "Fecha Salida", "Usuario"
            }
        ));
        tabla_Mostrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_MostrarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_Mostrar);

        etq_obligatorio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etq_obligatorio.setForeground(new java.awt.Color(255, 51, 51));
        etq_obligatorio.setText("Campo Obligatorio (*)");

        etq_obligatorio1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etq_obligatorio1.setForeground(new java.awt.Color(255, 51, 51));
        etq_obligatorio1.setText("Campo Obligatorio (*)");

        etq_obligatorio2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etq_obligatorio2.setForeground(new java.awt.Color(255, 51, 51));
        etq_obligatorio2.setText("Campo Obligatorio (*)");

        etq_obligatorio3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etq_obligatorio3.setForeground(new java.awt.Color(255, 51, 51));
        etq_obligatorio3.setText("Campo Obligatorio (*)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel8)
                                .addComponent(jLabel4)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9)
                                .addComponent(jLabel15)
                                .addComponent(jLabel5))
                            .addGap(40, 40, 40)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_nombre, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_cedula, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_apellidos, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_telefono, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_email, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                .addComponent(cbox_area, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jdc_fechaingreso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jdc_fechasalida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbox_usuario, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_temperatura, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(etq_obligatorio)
                            .addGap(11, 11, 11))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(307, 307, 307)
                            .addComponent(etq_obligatorio1)))
                    .addComponent(etq_obligatorio2)
                    .addComponent(etq_obligatorio3))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(etq_obligatorio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(etq_obligatorio1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(etq_obligatorio2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbox_area, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etq_obligatorio3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_temperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jdc_fechaingreso, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdc_fechasalida, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbox_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("DATOS DEL EMPLEADO: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel12)
                        .addGap(157, 157, 157)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1360, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        limpiarCajas();
        mostrarDatos();
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void btn_insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertarActionPerformed
        validarCampos();
        insertarDatos();
        mostrarDatos();
    }//GEN-LAST:event_btn_insertarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        eliminarDatos();
        mostrarDatos();
        limpiarCajas();
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_mostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mostrarActionPerformed
        mostrarDatos();
    }//GEN-LAST:event_btn_mostrarActionPerformed

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
        //  cbox_temperatura.setSelectedItem(tabla_Mostrar.getValueAt(filaSeleccionada, 6));
        txt_temperatura.setText(tabla_Mostrar.getValueAt(filaSeleccionada, 6).toString());
        //   jdc_fechaingreso.setText(((tabla_Mostrar.getDateEditor().setUiComponent()(filaSeleccionada, 7).toString()));
        // jdc_fechasalida.setText((tabla_Mostrar.getDate(filaSeleccionada, 8).toString()));
        //  jdc_fechasalida.setString(8, ((JTextField) tabla_Mostrar.getDateEditor().setUiComponent()).toString());
        cbox_usuario.setSelectedItem(tabla_Mostrar.getValueAt(filaSeleccionada, 9).toString());
    }//GEN-LAST:event_tabla_MostrarMouseClicked

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void cbox_areaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_areaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_areaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_insertar;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_mostrar;
    private javax.swing.JComboBox<String> cbox_area;
    private javax.swing.JComboBox<String> cbox_usuario;
    private javax.swing.JLabel etq_obligatorio;
    private javax.swing.JLabel etq_obligatorio1;
    private javax.swing.JLabel etq_obligatorio2;
    private javax.swing.JLabel etq_obligatorio3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdc_fechaingreso;
    private com.toedter.calendar.JDateChooser jdc_fechasalida;
    private javax.swing.JTable tabla_Mostrar;
    private javax.swing.JTextField txt_apellidos;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_cedula;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_temperatura;
    // End of variables declaration//GEN-END:variables
}
