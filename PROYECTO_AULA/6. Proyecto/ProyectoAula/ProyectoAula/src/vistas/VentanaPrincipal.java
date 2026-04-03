
package vistas;

import javax.swing.JOptionPane;


public class VentanaPrincipal extends javax.swing.JFrame {

 
    public VentanaPrincipal() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jdp_escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menu_visitantes = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menu_empleados = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menu_salir = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jdp_escritorioLayout = new javax.swing.GroupLayout(jdp_escritorio);
        jdp_escritorio.setLayout(jdp_escritorioLayout);
        jdp_escritorioLayout.setHorizontalGroup(
            jdp_escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1396, Short.MAX_VALUE)
        );
        jdp_escritorioLayout.setVerticalGroup(
            jdp_escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 715, Short.MAX_VALUE)
        );

        jMenu1.setText("Visitantes ");

        menu_visitantes.setText("Registrar Visitantes");
        menu_visitantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_visitantesActionPerformed(evt);
            }
        });
        jMenu1.add(menu_visitantes);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Empleados");

        menu_empleados.setText("Registrar Empleados");
        menu_empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_empleadosActionPerformed(evt);
            }
        });
        jMenu2.add(menu_empleados);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Salir");

        menu_salir.setText("Salir del Sistema");
        menu_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_salirActionPerformed(evt);
            }
        });
        jMenu3.add(menu_salir);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdp_escritorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jdp_escritorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_visitantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_visitantesActionPerformed
        form_visitantes objvisi=new form_visitantes();
        jdp_escritorio.add(objvisi);
        objvisi.show();
        
    }//GEN-LAST:event_menu_visitantesActionPerformed

    private void menu_empleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_empleadosActionPerformed
        form_empleados objempl=new form_empleados();
        jdp_escritorio.add(objempl);
        objempl.show();
    }//GEN-LAST:event_menu_empleadosActionPerformed

    private void menu_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_salirActionPerformed
        
       if(JOptionPane.showConfirmDialog(null, "Deseas salir del Sistema?",
       "login", JOptionPane.YES_NO_CANCEL_OPTION)==JOptionPane.YES_OPTION){ 
            System.exit(0);
       }
    }//GEN-LAST:event_menu_salirActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JDesktopPane jdp_escritorio;
    private javax.swing.JMenuItem menu_empleados;
    private javax.swing.JMenuItem menu_salir;
    private javax.swing.JMenuItem menu_visitantes;
    // End of variables declaration//GEN-END:variables
}
