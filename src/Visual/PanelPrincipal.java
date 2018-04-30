package Visual;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class PanelPrincipal extends javax.swing.JPanel {
    private final long idEdificio;
    private final String nombreEdificio;
    private final ControladoraV unaControladora = new ControladoraV();
    private final DefaultComboBoxModel comboEdficio = new DefaultComboBoxModel();
    private final String colTablaExpensa[] = {"SERVICIOS", "VENCIMIENTO", "TOTAL"};
    private final String colTablaAlquiler[] = {"DEPARTAMENTO", "INQUILINO", "FECHA", "ALQUILER", "OTRAS FACTURAS", "EXPENSA", "COCHERA", "INTERES POR ATRASO", "SALDO MES ANT.", "TOTAL"};
    private final DefaultTableModel tablaExpensa = new DefaultTableModel(null, colTablaExpensa);
    private final DefaultTableModel tablaAlquiler = new DefaultTableModel(null, colTablaAlquiler);
    
    public PanelPrincipal(long idEdificio, String nombreEdificio) {
        initComponents();
        this.idEdificio = idEdificio;
        this.nombreEdificio = nombreEdificio;
        this.jLabelTituloEdificio.setText(nombreEdificio);
        //cargarTablaAlquiler(idEdificio);
        //cargarTablaExpensa(idEdificio);
        this.jTableAlquiler.setRowSelectionAllowed(true);
        this.jTableExpensa.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelAlquileres = new javax.swing.JPanel();
        jPanelPrincipalTabla1 = new javax.swing.JPanel();
        jLabelTituloEdificio = new javax.swing.JLabel();
        jLabelAlquiler = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAlquiler = new javax.swing.JTable();
        jPanelButtonRefrescar = new javax.swing.JPanel();
        jLabelRefrescar = new javax.swing.JLabel();
        jPanelExpensas = new javax.swing.JPanel();
        jLabelExpensa = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableExpensa = new javax.swing.JTable();
        jPanelButtonReporte = new javax.swing.JPanel();
        jLabelReporte = new javax.swing.JLabel();

        jPanelAlquileres.setBackground(new java.awt.Color(255, 255, 255));

        jPanelPrincipalTabla1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPrincipalTabla1.setPreferredSize(new java.awt.Dimension(890, 315));

        jLabelTituloEdificio.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabelTituloEdificio.setText("jLabel1");

        jLabelAlquiler.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabelAlquiler.setText("Alquileres");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        jTableAlquiler.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jTableAlquiler = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        jTableAlquiler.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableAlquiler.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableAlquiler.setGridColor(new java.awt.Color(255, 255, 255));
        jTableAlquiler.setSelectionBackground(new java.awt.Color(102, 153, 255));
        jTableAlquiler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAlquilerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAlquiler);

        jPanelButtonRefrescar.setBackground(new java.awt.Color(51, 51, 51));
        jPanelButtonRefrescar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelButtonRefrescar.setPreferredSize(new java.awt.Dimension(100, 49));
        jPanelButtonRefrescar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanelButtonRefrescarMouseMoved(evt);
            }
        });
        jPanelButtonRefrescar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelButtonRefrescarMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelButtonRefrescarMouseExited(evt);
            }
        });

        jLabelRefrescar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Visual/img/cargando_blanco.png"))); // NOI18N

        javax.swing.GroupLayout jPanelButtonRefrescarLayout = new javax.swing.GroupLayout(jPanelButtonRefrescar);
        jPanelButtonRefrescar.setLayout(jPanelButtonRefrescarLayout);
        jPanelButtonRefrescarLayout.setHorizontalGroup(
            jPanelButtonRefrescarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonRefrescarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelRefrescar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelButtonRefrescarLayout.setVerticalGroup(
            jPanelButtonRefrescarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelRefrescar, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelPrincipalTabla1Layout = new javax.swing.GroupLayout(jPanelPrincipalTabla1);
        jPanelPrincipalTabla1.setLayout(jPanelPrincipalTabla1Layout);
        jPanelPrincipalTabla1Layout.setHorizontalGroup(
            jPanelPrincipalTabla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalTabla1Layout.createSequentialGroup()
                .addGroup(jPanelPrincipalTabla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrincipalTabla1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelTituloEdificio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelButtonRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPrincipalTabla1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanelPrincipalTabla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAlquiler)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE))))
                .addGap(22, 22, 22))
        );
        jPanelPrincipalTabla1Layout.setVerticalGroup(
            jPanelPrincipalTabla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalTabla1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPrincipalTabla1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTituloEdificio)
                    .addComponent(jPanelButtonRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jLabelAlquiler)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelAlquileresLayout = new javax.swing.GroupLayout(jPanelAlquileres);
        jPanelAlquileres.setLayout(jPanelAlquileresLayout);
        jPanelAlquileresLayout.setHorizontalGroup(
            jPanelAlquileresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipalTabla1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelAlquileresLayout.setVerticalGroup(
            jPanelAlquileresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlquileresLayout.createSequentialGroup()
                .addComponent(jPanelPrincipalTabla1, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanelExpensas.setBackground(new java.awt.Color(255, 255, 255));
        jPanelExpensas.setPreferredSize(new java.awt.Dimension(496, 315));

        jLabelExpensa.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabelExpensa.setText("Expensas");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        jTableExpensa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jTableExpensa = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        jTableExpensa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableExpensa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableExpensa.setGridColor(new java.awt.Color(255, 255, 255));
        jTableExpensa.setPreferredSize(new java.awt.Dimension(300, 312));
        jScrollPane2.setViewportView(jTableExpensa);

        jPanelButtonReporte.setBackground(new java.awt.Color(51, 51, 51));
        jPanelButtonReporte.setPreferredSize(new java.awt.Dimension(100, 49));
        jPanelButtonReporte.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanelButtonReporteMouseMoved(evt);
            }
        });
        jPanelButtonReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelButtonReporteMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelButtonReporteMouseExited(evt);
            }
        });

        jLabelReporte.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabelReporte.setForeground(new java.awt.Color(255, 255, 255));
        jLabelReporte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelReporte.setText("Generar Reporte");

        javax.swing.GroupLayout jPanelButtonReporteLayout = new javax.swing.GroupLayout(jPanelButtonReporte);
        jPanelButtonReporte.setLayout(jPanelButtonReporteLayout);
        jPanelButtonReporteLayout.setHorizontalGroup(
            jPanelButtonReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelButtonReporteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelButtonReporteLayout.setVerticalGroup(
            jPanelButtonReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelReporte, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelExpensasLayout = new javax.swing.GroupLayout(jPanelExpensas);
        jPanelExpensas.setLayout(jPanelExpensasLayout);
        jPanelExpensasLayout.setHorizontalGroup(
            jPanelExpensasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelExpensasLayout.createSequentialGroup()
                .addGroup(jPanelExpensasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelExpensasLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelButtonReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelExpensasLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanelExpensasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
                            .addGroup(jPanelExpensasLayout.createSequentialGroup()
                                .addComponent(jLabelExpensa)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(22, 22, 22))
        );
        jPanelExpensasLayout.setVerticalGroup(
            jPanelExpensasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExpensasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelExpensa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelButtonReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelAlquileres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelExpensas, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelAlquileres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelExpensas, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTableAlquilerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAlquilerMouseClicked
        if(evt.getClickCount() == 2){
            if(idEdificio != 0){
                try{
                    int fila = jTableAlquiler.getSelectedRow();
                    String depto = jTableAlquiler.getValueAt(fila, 0).toString();
                    String periodo = jTableAlquiler.getValueAt(fila, 2).toString(); // Periodo = "03/18"
                    cargarExpensaDepartamento(depto, periodo);

                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Seleccione un Alquiler");
                }
            }else{
                JOptionPane.showMessageDialog(null, "No existe registro de ningún Edificio");
            }
        }
    }//GEN-LAST:event_jTableAlquilerMouseClicked

    private void jPanelButtonRefrescarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonRefrescarMouseClicked
        cargarTablaAlquiler(this.idEdificio);
        cargarTablaExpensa(this.idEdificio);
    }//GEN-LAST:event_jPanelButtonRefrescarMouseClicked

    private void jPanelButtonRefrescarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonRefrescarMouseExited
        ImageIcon refresh = new ImageIcon(getClass().getResource("/Visual/img/cargando_blanco.png"));
        this.jPanelButtonRefrescar.setBackground(new Color(51,51,51));
        this.jLabelRefrescar.setIcon(refresh);
    }//GEN-LAST:event_jPanelButtonRefrescarMouseExited

    private void jPanelButtonRefrescarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonRefrescarMouseMoved
        ImageIcon refresh = new ImageIcon(getClass().getResource("/Visual/img/cargando.png"));
        this.jPanelButtonRefrescar.setBackground(new Color(255,255,255));
        this.jPanelButtonRefrescar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(51,51,51)));
        this.jLabelRefrescar.setIcon(refresh);
    }//GEN-LAST:event_jPanelButtonRefrescarMouseMoved

    private void jPanelButtonReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonReporteMouseClicked
        if(idEdificio != 0){
            if(unaControladora.existenExpensas(idEdificio)){
                try{
                    Logica.Reporte generarReporte = new Logica.Reporte();
                    generarReporte.crearLibro(idEdificio);
                    JOptionPane.showMessageDialog(null, "Se ha generado con éxito el Excel.");
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Ocurrio un error: "+e);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No es posible generar el reporte debido a que no existe ninguna expensa registrada.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No es posible generar el reporte debido a que no se encuentra seleccionado un Edificio. Por favor Seleccione un Edificio");
        }        
    }//GEN-LAST:event_jPanelButtonReporteMouseClicked

    private void jPanelButtonReporteMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonReporteMouseMoved
        this.jPanelButtonReporte.setBackground(new Color(255,255,255));
        this.jPanelButtonReporte.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(51,51,51)));
        this.jLabelReporte.setForeground(new Color(51,51,51));
    }//GEN-LAST:event_jPanelButtonReporteMouseMoved

    private void jPanelButtonReporteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonReporteMouseExited
        this.jPanelButtonReporte.setBackground(new Color(51,51,51));
        this.jLabelReporte.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_jPanelButtonReporteMouseExited

    public void limpiarVentana(){
        if(tablaAlquiler.getRowCount() > 0){
            while(tablaAlquiler.getRowCount() != 0){
                tablaAlquiler.removeRow(0);
            }
        }
        
        if(tablaExpensa.getRowCount() > 0){
            while(tablaExpensa.getRowCount() != 0){
                tablaExpensa.removeRow(0);
            }
        }
    }
    
    public void cargarTablaAlquiler(long idEdificio){
            Logica.Edificio unEdificio = unaControladora.obtenerEdificio(idEdificio);
            this.jLabelTituloEdificio.setText(unEdificio.getNombre());
            List<Logica.Inquilino> inquilinosEdificio = unaControladora.obtenerInquilinosEdificio(idEdificio);
            List<Logica.Alquiler> alquileres = new LinkedList();

            for(Logica.Inquilino unInquilino : inquilinosEdificio){
                if(unInquilino.getAlquileres().isEmpty()){
                    int ultimoAlquiler = unInquilino.getAlquileres().size()-1;
                    Logica.Departamento unDepto = unaControladora.obtenerDepartamentoInquilino(unInquilino.getId());
                    
                    if(unDepto != null){
                        System.out.println("Alquiler idDepto: "+unInquilino.getAlquileres().get(ultimoAlquiler).getDepartamento()+". Departamento idDepto: "+unDepto.getId());
                        System.out.println("Alquiler idCochera: "+unInquilino.getAlquileres().get(ultimoAlquiler).getCochera()+". Departamento idDepto: "+unDepto.getId());
                    }
                    
                }
                
                
            }
            
            //        PRUEBA       
            //ArrayList<Logica.Inquilino> alquileres2 = unaControladora.obtenerInquilinosEdificio2(unEdificio);
            
            List<Logica.ServicioExpensa> serviciosExpensa;
            String datos[] = new String[10];
            Date fecha = new Date();
            SimpleDateFormat fechaActual = new SimpleDateFormat("dd-MM-yy");
            float total,
                  montoTotalAlquiler;

            limpiarVentana();

            /*for(int i = 0; i < inquilinosEdificio.size(); i++){
                unaControladora.obtenerAlquileresInpagos(inquilinosEdificio.get(i)).forEach(alquileres::add);
            }

            if(alquileres.size() > 0){
                for(Logica.Alquiler unAlquiler : alquileres){
                    total = 0;
                    Logica.Departamento unDepto = unaControladora.obtenerDepartamento(unAlquiler.getDepartamento());
                    
                        datos[0] = unDepto.getUbicacion();
                        datos[1] = unDepto.getUnInquilino().getApellido()+", "+unDepto.getUnInquilino().getNombre();
                        datos[2] = String.valueOf(unAlquiler.getFecha()); // Setear la fecha: dd/mm/aaaa
                        datos[3] = String.valueOf(unAlquiler.getMontoAlquiler());
                        datos[4] = String.valueOf(unAlquiler.getOtraFactura()); // Duda si va en Alquiler..
                        serviciosExpensa = unaControladora.obtenerServiciosExpensaDepartamento(alquileres.get(i).getUnInquilino().getUnDepartamento(), alquileres.get(i).getPeriodo(), alquileres.get(i).getAnio());
                        datos[5] = String.valueOf(unaControladora.calcularMonto(serviciosExpensa));
                        if(unAlquiler.getCochera() != 0){
                            datos[6] = String.valueOf(unaControladora.obtenerCochera(unAlquiler.getCochera()));
                        }else{
                            datos[6] = String.valueOf(0);
                        }
                        montoTotalAlquiler = Float.valueOf(datos[5])+alquileres.get(i).getMontoAlquiler()+alquileres.get(i).getOtraFactura();
                        datos[7] = String.valueOf(unaControladora.interesPorAtraso(fecha, montoTotalAlquiler, alquileres.get(i).getPeriodo()));
                        datos[8] = String.valueOf(alquileres.get(i).getUnInquilino().getSaldoMesAnt());

                        for(int j = 3; j < 9; j++){
                            total += Float.parseFloat(datos[j]);
                        }

                        datos[9] = String.valueOf(total);

                        tablaAlquiler.addRow(datos);
                }
                this.jTableAlquiler.setModel(tablaAlquiler);
            }*/
            
            /*
            for(int i = 0; i < alquileres.size(); i++){
                total = 0;

                if(alquileres.get(i).getUnInquilino().getUnDepartamento().getUnEdificio().getId() == unEdificio.getId()){
                    datos[0] = alquileres.get(i).getUnInquilino().getUnDepartamento().getUbicacion();
                    datos[1] = alquileres.get(i).getUnInquilino().getApellido()+", "+alquileres.get(i).getUnInquilino().getNombre();
                    datos[2] = String.valueOf(alquileres.get(i).getPeriodo())+"/"+String.valueOf(alquileres.get(i).getAnio());
                    datos[3] = String.valueOf(alquileres.get(i).getMontoAlquiler());
                    datos[4] = String.valueOf(alquileres.get(i).getOtraFactura());
                    serviciosExpensa = unaControladora.obtenerServiciosExpensaDepartamento(alquileres.get(i).getUnInquilino().getUnDepartamento(), alquileres.get(i).getPeriodo(), alquileres.get(i).getAnio());
                    datos[5] = String.valueOf(unaControladora.calcularMonto(serviciosExpensa));
                    if(alquileres.get(i).getUnaCochera() != null){
                        datos[6] = String.valueOf(alquileres.get(i).getUnaCochera().getPrecio());
                    }else{
                        datos[6] = String.valueOf(0);
                    }
                    montoTotalAlquiler = Float.valueOf(datos[5])+alquileres.get(i).getMontoAlquiler()+alquileres.get(i).getOtraFactura();
                    datos[7] = String.valueOf(unaControladora.interesPorAtraso(fecha, montoTotalAlquiler, alquileres.get(i).getPeriodo()));
                    datos[8] = String.valueOf(alquileres.get(i).getUnInquilino().getSaldoMesAnt());

                    for(int j = 3; j < 9; j++){
                        total += Float.parseFloat(datos[j]);
                    }

                    datos[9] = String.valueOf(total);

                    tablaAlquiler.addRow(datos);
                }
            }
            */
            
        
    }
    
    public void cargarTablaExpensa(long idEdificio){
        /*if(idEdificio != 0){
            Logica.Edificio unEdificio = unaControladora.obtenerEdificio(idEdificio);
            String datos[] = new String[3];
            SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yy");
            Calendar calendario = Calendar.getInstance();
            int periodo = calendario.get(Calendar.MONTH),
                anio = calendario.get(Calendar.YEAR);
            List <Logica.Servicio> servicios = unaControladora.obtenerServEdifPeriodo(periodo, unEdificio);

            //jLabelPeriodo.setText(periodo+"/"+anio);
            //jLabelDepartamento.setText("N° DEPTOS: "+unaControladora.numeroDepartamentoEdificio(unEdificio)+"  ");

            for(Logica.Servicio unServicio : servicios){

                datos[0] = unServicio.getNombre();

                if(unServicio.getFechaVencimiento() == null){
                    datos[1] = "";
                }else{
                    datos[1] = fecha.format(unServicio.getFechaVencimiento());
                }

                datos[2] = String.valueOf(unServicio.getMonto());

                tablaExpensa.addRow(datos);
            }
            this.jTableExpensa.setModel(tablaExpensa);
        }*/
    }
    
    public void cargarExpensaDepartamento(String depto, String periodoTabla){
        /*String colTablaExpensaDep[] = {"SERVICIOS", "TOTAL"};
        DefaultTableModel tablaExpensaDep = new DefaultTableModel(null, colTablaExpensaDep);
        String datos[] = new String[2],
               periodoString[] = periodoTabla.split("/");;
        this.jTableExpensa.setModel(tablaExpensaDep);
        Logica.Edificio unEdificio = unaControladora.obtenerEdificio(idEdificio);
        
        Logica.Departamento unDepartamento = unaControladora.obtenerDepartamento(depto, unEdificio);
        List<Logica.ServicioExpensa> expensaDepartamento = unaControladora.obtenerServiciosExpensaDepartamento(unDepartamento, Integer.parseInt(periodoString[0]), Integer.parseInt(periodoString[1]));
        
        
        while(tablaExpensaDep.getRowCount() != 0){
            tablaExpensaDep.removeRow(0);
        }
        
        for(Logica.ServicioExpensa unServicioExpensa : expensaDepartamento){
                datos[0] = unServicioExpensa.getNombre();
                datos[1] = String.valueOf(unServicioExpensa.getMonto());
                
                tablaExpensaDep.addRow(datos);
        }*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAlquiler;
    private javax.swing.JLabel jLabelExpensa;
    private javax.swing.JLabel jLabelRefrescar;
    private javax.swing.JLabel jLabelReporte;
    private javax.swing.JLabel jLabelTituloEdificio;
    private javax.swing.JPanel jPanelAlquileres;
    private javax.swing.JPanel jPanelButtonRefrescar;
    private javax.swing.JPanel jPanelButtonReporte;
    private javax.swing.JPanel jPanelExpensas;
    private javax.swing.JPanel jPanelPrincipalTabla1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableAlquiler;
    private javax.swing.JTable jTableExpensa;
    // End of variables declaration//GEN-END:variables
}