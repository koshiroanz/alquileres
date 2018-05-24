package Visual;

import java.awt.Color;
import javax.swing.JOptionPane;

public final class vtnReporte extends javax.swing.JFrame {
    private final ControladoraV unaControladora = new ControladoraV();
    private final long idEdificio;
    
    public vtnReporte(long idEdificio) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.idEdificio = idEdificio;
        this.setSize(600, 500);
        cargarObservaciones();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaAlquiler = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaExpensa = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelContadorExpensa = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabelContadorAlquiler = new javax.swing.JLabel();
        jPanelBotonGenerar = new javax.swing.JPanel();
        jLabelGenerar = new javax.swing.JLabel();

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel1.setText("Reportes");

        jTextAreaAlquiler.setColumns(20);
        jTextAreaAlquiler.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jTextAreaAlquiler.setRows(5);
        jTextAreaAlquiler.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextAreaAlquilerKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaAlquiler);

        jTextAreaExpensa.setColumns(20);
        jTextAreaExpensa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jTextAreaExpensa.setRows(5);
        jTextAreaExpensa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextAreaExpensaKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jTextAreaExpensa);

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setText("Nota alquiler");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel5.setText("Nota expensas");

        jLabelContadorExpensa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 10)); // NOI18N
        jLabelContadorExpensa.setText("255");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 10)); // NOI18N
        jLabel8.setText("máx. caracteres:");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 10)); // NOI18N
        jLabel9.setText("máx. caracteres:");

        jLabelContadorAlquiler.setFont(new java.awt.Font("Segoe UI Semibold", 0, 10)); // NOI18N
        jLabelContadorAlquiler.setText("255");

        jPanelBotonGenerar.setBackground(new java.awt.Color(51, 51, 51));
        jPanelBotonGenerar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelBotonGenerarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelBotonGenerarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelBotonGenerarMouseExited(evt);
            }
        });

        jLabelGenerar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabelGenerar.setForeground(new java.awt.Color(255, 255, 255));
        jLabelGenerar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelGenerar.setText("Generar Reporte");

        javax.swing.GroupLayout jPanelBotonGenerarLayout = new javax.swing.GroupLayout(jPanelBotonGenerar);
        jPanelBotonGenerar.setLayout(jPanelBotonGenerarLayout);
        jPanelBotonGenerarLayout.setHorizontalGroup(
            jPanelBotonGenerarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotonGenerarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelGenerar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelBotonGenerarLayout.setVerticalGroup(
            jPanelBotonGenerarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelGenerar, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelContadorAlquiler))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelContadorExpensa))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanelBotonGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabelContadorAlquiler))
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContadorExpensa)
                    .addComponent(jLabel8))
                .addGap(24, 24, 24)
                .addComponent(jPanelBotonGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jTextAreaAlquilerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaAlquilerKeyTyped
        int cantNota = jTextAreaAlquiler.getText().length();
        String c = jTextAreaAlquiler.getText();
        int cant = 255 - cantNota;
        jLabelContadorAlquiler.setText(String.valueOf(cant));
        if(cantNota > 254){
            String nuevo = c.substring(0, c.length()-1);
            jTextAreaAlquiler.setText(nuevo);
        }        
    }//GEN-LAST:event_jTextAreaAlquilerKeyTyped

    private void jTextAreaExpensaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaExpensaKeyTyped
        int cantNota = jTextAreaExpensa.getText().length();
        String c = jTextAreaExpensa.getText();
        int cant = 255 - cantNota;
        jLabelContadorExpensa.setText(String.valueOf(cant));
        if(cantNota > 254){
            String nuevo = c.substring(0, c.length()-1);
            jTextAreaExpensa.setText(nuevo);
        }
    }//GEN-LAST:event_jTextAreaExpensaKeyTyped

    private void jPanelBotonGenerarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBotonGenerarMouseClicked
        try{
            if(unaControladora.obtenerEdificio(idEdificio).getNotificaciones() != null){
                unaControladora.modificarNotificaciones(idEdificio, jTextAreaAlquiler.getText().toUpperCase(), jTextAreaExpensa.getText().toUpperCase());
            }else{
                unaControladora.altaNotificaciones(idEdificio, jTextAreaAlquiler.getText().toUpperCase(), jTextAreaExpensa.getText().toUpperCase());
            }
            
            Logica.Reporte reporte = new Logica.Reporte();
            
            if(reporte.generarReporte(idEdificio, jTextAreaAlquiler.getText(), jTextAreaExpensa.getText())){
                JOptionPane.showMessageDialog(null, "Se ha generado el documento exitosamente.");
                this.dispose();
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }//GEN-LAST:event_jPanelBotonGenerarMouseClicked

    private void jPanelBotonGenerarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBotonGenerarMouseEntered
        this.jPanelBotonGenerar.setBackground(new Color(255,255,255));
        this.jPanelBotonGenerar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(51,51,51)));
        this.jLabelGenerar.setForeground(new Color(51,51,51));
    }//GEN-LAST:event_jPanelBotonGenerarMouseEntered

    private void jPanelBotonGenerarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBotonGenerarMouseExited
        this.jPanelBotonGenerar.setBackground(new Color(51,51,51));
        this.jLabelGenerar.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_jPanelBotonGenerarMouseExited

    public void cargarObservaciones(){
        if(unaControladora.obtenerEdificio(idEdificio).getNotificaciones() != null){
            if(unaControladora.obtenerEdificio(idEdificio).getNotificaciones().getObsAlquiler() != null){
                int cantAlquiler = unaControladora.obtenerEdificio(idEdificio).getNotificaciones().getObsAlquiler().length();
                jTextAreaAlquiler.setText(unaControladora.obtenerEdificio(idEdificio).getNotificaciones().getObsAlquiler());
                if(cantAlquiler != 0){
                    int contAlquiler = 255-cantAlquiler;
                    jLabelContadorAlquiler.setText(String.valueOf(contAlquiler));
                }
            }
            if(unaControladora.obtenerEdificio(idEdificio).getNotificaciones().getObsExpensa() != null){
                int cantExpensa = unaControladora.obtenerEdificio(idEdificio).getNotificaciones().getObsExpensa().length();
                    jTextAreaExpensa.setText(unaControladora.obtenerEdificio(idEdificio).getNotificaciones().getObsExpensa());  
                    if(cantExpensa != 0){
                        int contExpensa = 255-cantExpensa;
                        jLabelContadorExpensa.setText(String.valueOf(contExpensa));
                    }
            }
        }else{
            jTextAreaAlquiler.setText(null);
            jTextAreaExpensa.setText(null);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelContadorAlquiler;
    private javax.swing.JLabel jLabelContadorExpensa;
    private javax.swing.JLabel jLabelGenerar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBotonGenerar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaAlquiler;
    private javax.swing.JTextArea jTextAreaExpensa;
    // End of variables declaration//GEN-END:variables
}
