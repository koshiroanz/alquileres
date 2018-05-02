package Visual;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import static java.awt.event.KeyEvent.VK_COMMA;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_PERIOD;
import static java.awt.event.KeyEvent.VK_DECIMAL;
import static java.awt.event.KeyEvent.VK_BACK_SPACE;

public final class PanelAlquiler extends javax.swing.JPanel {
    private int fila;
    private final long idEdificio;
    private boolean eliminar = false, modificar = false;
    private long idAlquiler, idDepartamento = 0, idCochera = 0;
    private float montoCochera = 0, montoAlquiler = 0, otrasFacturas = 0;
    private final ControladoraV unaControladora = new ControladoraV();
    private final DefaultComboBoxModel comboCochera = new DefaultComboBoxModel();
    private final DefaultComboBoxModel comboInquilino = new DefaultComboBoxModel();
    private final DefaultComboBoxModel comboAnio = new DefaultComboBoxModel();
    private final DefaultComboBoxModel comboDepartamento = new DefaultComboBoxModel();
    private final String colTablaAlquiler[] = {"Id", "Fecha", "Monto", "Otras Facturas", "Total", "Departamento", "Cochera", "Inquilino"};
    private final DefaultTableModel tablaAlquiler = new DefaultTableModel(null, colTablaAlquiler);
    
    public PanelAlquiler(long idEdificio){
        initComponents();
        this.idEdificio = idEdificio;
        cargarComboInquilino(idEdificio,0);
        cargarComboAnio();
        cargarTablaAlquiler(0,0);
        this.jTextFieldTotal.setText("Haga click aquí para calcular el TOTAL");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelFecha = new javax.swing.JLabel();
        jDateChooserFecha = new com.toedter.calendar.JDateChooser();
        jLabelMonto = new javax.swing.JLabel();
        jTextFieldMonto = new javax.swing.JTextField();
        jSeparatorMonto = new javax.swing.JSeparator();
        jLabelDepartamento = new javax.swing.JLabel();
        jComboBoxDepartamento = new javax.swing.JComboBox<>();
        jLabelInquilino = new javax.swing.JLabel();
        jLabelCochera = new javax.swing.JLabel();
        jComboBoxCochera = new javax.swing.JComboBox<>();
        jLabelDescripcion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaDescripcion = new javax.swing.JTextArea();
        jComboBoxInquilino = new javax.swing.JComboBox<>();
        jLabelOtraFactura = new javax.swing.JLabel();
        jTextFieldOtraFactura = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelButtonRefresh = new javax.swing.JPanel();
        jLabelRefresh = new javax.swing.JLabel();
        jLabelTotal = new javax.swing.JLabel();
        jTextFieldTotal = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAlquiler = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanelButtonAgregar = new javax.swing.JPanel();
        jLabelAceptar = new javax.swing.JLabel();
        jPanelButtonEliminar = new javax.swing.JPanel();
        jLabelEliminar = new javax.swing.JLabel();
        jPanelButtonBuscar = new javax.swing.JPanel();
        jLabelBuscar = new javax.swing.JLabel();
        jComboBoxAnio = new javax.swing.JComboBox<>();
        jComboBoxMesBusqueda = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(890, 315));

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabelTitulo.setText("Alquiler");

        jLabelFecha.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabelFecha.setText("Fecha");

        jDateChooserFecha.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooserFecha.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        jLabelMonto.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabelMonto.setText("Monto");

        jTextFieldMonto.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jTextFieldMonto.setText("0");
        jTextFieldMonto.setBorder(null);
        jTextFieldMonto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldMontoMouseClicked(evt);
            }
        });
        jTextFieldMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldMontoKeyTyped(evt);
            }
        });

        jLabelDepartamento.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabelDepartamento.setText("Departamento");

        jComboBoxDepartamento.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jComboBoxDepartamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opción", "Sin departamento" }));
        jComboBoxDepartamento.setBorder(null);

        jLabelInquilino.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabelInquilino.setText("Inquilino");

        jLabelCochera.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabelCochera.setText("Cochera");

        jComboBoxCochera.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jComboBoxCochera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opción", "Sin cochera" }));
        jComboBoxCochera.setBorder(null);

        jLabelDescripcion.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabelDescripcion.setText("Descripción");

        jTextAreaDescripcion.setColumns(20);
        jTextAreaDescripcion.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jTextAreaDescripcion.setRows(2);
        jScrollPane2.setViewportView(jTextAreaDescripcion);

        jComboBoxInquilino.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jComboBoxInquilino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opción" }));
        jComboBoxInquilino.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxInquilinoItemStateChanged(evt);
            }
        });

        jLabelOtraFactura.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabelOtraFactura.setText("Otras Facturas");

        jTextFieldOtraFactura.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jTextFieldOtraFactura.setText("0");
        jTextFieldOtraFactura.setBorder(null);
        jTextFieldOtraFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldOtraFacturaMouseClicked(evt);
            }
        });
        jTextFieldOtraFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldOtraFacturaKeyTyped(evt);
            }
        });

        jPanelButtonRefresh.setBackground(new java.awt.Color(51, 51, 51));
        jPanelButtonRefresh.setPreferredSize(new java.awt.Dimension(100, 49));
        jPanelButtonRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelButtonRefreshMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelButtonRefreshMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelButtonRefreshMouseExited(evt);
            }
        });

        jLabelRefresh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Visual/img/cargando_blanco.png"))); // NOI18N

        javax.swing.GroupLayout jPanelButtonRefreshLayout = new javax.swing.GroupLayout(jPanelButtonRefresh);
        jPanelButtonRefresh.setLayout(jPanelButtonRefreshLayout);
        jPanelButtonRefreshLayout.setHorizontalGroup(
            jPanelButtonRefreshLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelButtonRefreshLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelButtonRefreshLayout.setVerticalGroup(
            jPanelButtonRefreshLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        jLabelTotal.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabelTotal.setText("Total");

        jTextFieldTotal.setBackground(new java.awt.Color(204, 204, 255));
        jTextFieldTotal.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jTextFieldTotal.setText("Haga click aquí para calcular el TOTAL");
        jTextFieldTotal.setBorder(null);
        jTextFieldTotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTotalMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDepartamento)
                            .addComponent(jLabelMonto)
                            .addComponent(jLabelTotal)
                            .addComponent(jLabelFecha))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldMonto)
                                    .addComponent(jTextFieldTotal)
                                    .addComponent(jSeparatorMonto, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator2))
                                .addGap(114, 114, 114))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBoxDepartamento, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooserFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(113, 113, 113)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelInquilino)
                                    .addComponent(jLabelCochera))
                                .addGap(52, 52, 52)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxInquilino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxCochera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelOtraFactura)
                                    .addComponent(jLabelDescripcion))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldOtraFactura))))))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitulo)
                    .addComponent(jPanelButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jComboBoxInquilino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelInquilino)
                    .addComponent(jDateChooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFecha))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jComboBoxCochera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCochera)
                    .addComponent(jComboBoxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDepartamento))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTextFieldOtraFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMonto)
                    .addComponent(jLabelOtraFactura))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparatorMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTotal))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelDescripcion))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(837, 315));

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
        jTableAlquiler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAlquilerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAlquiler);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(355, 60));

        jPanelButtonAgregar.setBackground(new java.awt.Color(51, 51, 51));
        jPanelButtonAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelButtonAgregar.setPreferredSize(new java.awt.Dimension(100, 49));
        jPanelButtonAgregar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanelButtonAgregarMouseMoved(evt);
            }
        });
        jPanelButtonAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelButtonAgregarMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelButtonAgregarMouseExited(evt);
            }
        });

        jLabelAceptar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabelAceptar.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAceptar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAceptar.setText("Aceptar");

        javax.swing.GroupLayout jPanelButtonAgregarLayout = new javax.swing.GroupLayout(jPanelButtonAgregar);
        jPanelButtonAgregar.setLayout(jPanelButtonAgregarLayout);
        jPanelButtonAgregarLayout.setHorizontalGroup(
            jPanelButtonAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelButtonAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelButtonAgregarLayout.setVerticalGroup(
            jPanelButtonAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        jPanelButtonEliminar.setBackground(new java.awt.Color(51, 51, 51));
        jPanelButtonEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelButtonEliminar.setPreferredSize(new java.awt.Dimension(100, 49));
        jPanelButtonEliminar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanelButtonEliminarMouseMoved(evt);
            }
        });
        jPanelButtonEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelButtonEliminarMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelButtonEliminarMouseExited(evt);
            }
        });

        jLabelEliminar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabelEliminar.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEliminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEliminar.setText("Eliminar");

        javax.swing.GroupLayout jPanelButtonEliminarLayout = new javax.swing.GroupLayout(jPanelButtonEliminar);
        jPanelButtonEliminar.setLayout(jPanelButtonEliminarLayout);
        jPanelButtonEliminarLayout.setHorizontalGroup(
            jPanelButtonEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelButtonEliminarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelButtonEliminarLayout.setVerticalGroup(
            jPanelButtonEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelButtonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelButtonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanelButtonBuscar.setBackground(new java.awt.Color(51, 51, 51));
        jPanelButtonBuscar.setPreferredSize(new java.awt.Dimension(100, 49));
        jPanelButtonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelButtonBuscarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelButtonBuscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelButtonBuscarMouseExited(evt);
            }
        });

        jLabelBuscar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Visual/img/lupa_blanca.png"))); // NOI18N

        javax.swing.GroupLayout jPanelButtonBuscarLayout = new javax.swing.GroupLayout(jPanelButtonBuscar);
        jPanelButtonBuscar.setLayout(jPanelButtonBuscarLayout);
        jPanelButtonBuscarLayout.setHorizontalGroup(
            jPanelButtonBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelButtonBuscarLayout.setVerticalGroup(
            jPanelButtonBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        jComboBoxAnio.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jComboBoxAnio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione año", "2018" }));
        jComboBoxAnio.setBorder(null);
        jComboBoxAnio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jComboBoxMesBusqueda.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jComboBoxMesBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione mes", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jComboBoxMesBusqueda.setBorder(null);
        jComboBoxMesBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxMesBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jComboBoxMesBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelButtonAgregarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonAgregarMouseMoved
        this.jPanelButtonAgregar.setBackground(new Color(255,255,255));
        this.jPanelButtonAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(51,51,51)));
        this.jLabelAceptar.setForeground(new Color(51,51,51));
    }//GEN-LAST:event_jPanelButtonAgregarMouseMoved

    private void jPanelButtonAgregarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonAgregarMouseExited
        this.jPanelButtonAgregar.setBackground(new Color(51,51,51));
        this.jLabelAceptar.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_jPanelButtonAgregarMouseExited

    private void jPanelButtonEliminarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonEliminarMouseMoved
        this.jPanelButtonEliminar.setBackground(new Color(255,255,255));
        this.jPanelButtonEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(51,51,51)));
        this.jLabelEliminar.setForeground(new Color(51,51,51));
    }//GEN-LAST:event_jPanelButtonEliminarMouseMoved

    private void jPanelButtonEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonEliminarMouseExited
        this.jPanelButtonEliminar.setBackground(new Color(51,51,51));
        this.jLabelEliminar.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_jPanelButtonEliminarMouseExited

    private void jTableAlquilerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAlquilerMouseClicked
        fila = jTableAlquiler.getSelectedRow();
        
        idAlquiler = Long.valueOf(jTableAlquiler.getValueAt(fila, 0).toString());
        
        if(evt.getClickCount() == 1){
            eliminar = true;
        }else if(evt.getClickCount() == 2){
            cargarPanelDatos(idAlquiler);
            eliminar = false;
       }
    }//GEN-LAST:event_jTableAlquilerMouseClicked

    private void jPanelButtonAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonAgregarMouseClicked
        if(validar()){
            try {
                Date fecha = jDateChooserFecha.getDate();
                String descripcion = jTextAreaDescripcion.getText();
                float /*precioCochera = 0, */monto = Float.valueOf(jTextFieldMonto.getText()), otraFactura = Float.valueOf(jTextFieldOtraFactura.getText());
                //long idCochera = 0, idDepartamento = 0;
                
                Logica.Inquilino unInquilino = (Logica.Inquilino) comboInquilino.getElementAt(jComboBoxInquilino.getSelectedIndex());
                
                /*if(jComboBoxCochera.getSelectedIndex() > 0){
                    try{
                        Logica.Cochera unaCochera = (Logica.Cochera) comboCochera.getSelectedItem();
                        idCochera = unaCochera.getId();
                        precioCochera = unaCochera.getPrecio();
                    }catch(Exception e){
                        System.out.print("Sin Cochera.");
                    }
                }
                
                if(jComboBoxDepartamento.getSelectedIndex() > 0){
                    try{
                        Logica.Departamento unDepartamento = (Logica.Departamento)comboDepartamento.getSelectedItem();
                        idDepartamento = unDepartamento.getId();
                    }catch(Exception e){
                        System.out.print("Sin Departamento.");
                    }
                }*/
                float total = Float.valueOf(jTextFieldTotal.getText());
                //float total = monto+otraFactura+precioCochera;
                
                if(!modificar){
                    unaControladora.altaAlquiler(fecha, idCochera, idDepartamento, monto, otraFactura, total, descripcion, null, unInquilino.getId());
                    limpiarComponentes();
                    cargarTablaAlquiler(0,0);
                    JOptionPane.showMessageDialog(null, "Se ha cargado exitosamente.");
                }else{
                    int confirmacion = JOptionPane.showConfirmDialog(null, "Desea realizar esta operación?", "Actualizar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(confirmacion == 0){
                        Logica.Pago unPago = unaControladora.obtenerAlquiler(idAlquiler).getUnPago();
                        unaControladora.modificarAlquiler(idAlquiler, fecha, idCochera, idDepartamento, monto, otraFactura, total, descripcion, unPago);
                        modificar = false;
                        cargarTablaAlquiler(0,0);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(Logica.Alquiler.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación. Error: "+ex);
            }
        }
    }//GEN-LAST:event_jPanelButtonAgregarMouseClicked

    private void jPanelButtonRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonRefreshMouseClicked
        if(modificar){
            modificar = false;
        }
        cargarTablaAlquiler(0,0);
    }//GEN-LAST:event_jPanelButtonRefreshMouseClicked

    private void jPanelButtonRefreshMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonRefreshMouseEntered
        icono("adentro");
    }//GEN-LAST:event_jPanelButtonRefreshMouseEntered

    private void jPanelButtonRefreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonRefreshMouseExited
        icono("afuera");
    }//GEN-LAST:event_jPanelButtonRefreshMouseExited

    private void jPanelButtonBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonBuscarMouseClicked
        if(jComboBoxMesBusqueda.getSelectedIndex() == 0 || jComboBoxAnio.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Seleccione un mes y año para buscar.");
        }else{
            int anio = Integer.parseInt((String)jComboBoxAnio.getSelectedItem());
            cargarTablaAlquiler(jComboBoxMesBusqueda.getSelectedIndex(), anio);
        }
    }//GEN-LAST:event_jPanelButtonBuscarMouseClicked

    private void jPanelButtonBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonBuscarMouseEntered
        ImageIcon buscar = new ImageIcon(getClass().getResource("/Visual/img/lupa_negra.png"));
        this.jPanelButtonBuscar.setBackground(new Color(255,255,255));
        this.jPanelButtonBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(51,51,51)));
        this.jLabelBuscar.setIcon(buscar);
    }//GEN-LAST:event_jPanelButtonBuscarMouseEntered

    private void jPanelButtonBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonBuscarMouseExited
        ImageIcon buscar = new ImageIcon(getClass().getResource("/Visual/img/lupa_blanca.png"));
        this.jPanelButtonBuscar.setBackground(new Color(51,51,51));
        this.jLabelBuscar.setIcon(buscar);
    }//GEN-LAST:event_jPanelButtonBuscarMouseExited

    private void jComboBoxInquilinoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxInquilinoItemStateChanged
        if(jComboBoxInquilino.getSelectedIndex() > 0){
            Logica.Inquilino unInquilino = (Logica.Inquilino) comboInquilino.getElementAt(jComboBoxInquilino.getSelectedIndex());
            cargarCombos(unInquilino.getId());
        }
    }//GEN-LAST:event_jComboBoxInquilinoItemStateChanged

    private void jPanelButtonEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelButtonEliminarMouseClicked
        if(eliminar){
            try{
                int confirmacion = JOptionPane.showConfirmDialog(null, "Desea realizar esta operación?", "Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(confirmacion == 0){
                    Logica.Inquilino unInquilino = unaControladora.obtenerInquilinoPorAlquiler(idEdificio, idAlquiler);
                    unaControladora.bajaAlquiler(idAlquiler, unInquilino.getId());
                    cargarTablaAlquiler(0,0);
                    eliminar = false;
                }
            }catch(Exception e){
                Logger.getLogger(Logica.Departamento.class.getName()).log(Level.SEVERE, null, e);
                JOptionPane.showMessageDialog(null, "No se ha podido eliminar.");
            }
        }else{
             JOptionPane.showMessageDialog(null, "Debe seleccionar un Alquiler.");
        }
    }//GEN-LAST:event_jPanelButtonEliminarMouseClicked

    private void jTextFieldMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMontoKeyTyped
        if(!Character.isDigit(evt.getKeyChar()) && (evt.getKeyChar() != VK_SPACE) && (evt.getKeyChar() != VK_PERIOD) && (evt.getKeyChar() != VK_BACK_SPACE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldMontoKeyTyped

    private void jTextFieldOtraFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldOtraFacturaKeyTyped
        if(!Character.isDigit(evt.getKeyChar()) && (evt.getKeyChar() != VK_SPACE) && (evt.getKeyChar() != VK_PERIOD) && (evt.getKeyChar() != VK_BACK_SPACE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldOtraFacturaKeyTyped

    private void jTextFieldTotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTotalMouseClicked
        if(!modificar){
            float total = 0;
            if(jComboBoxInquilino.getSelectedItem() == "Seleccione una opción" || jComboBoxDepartamento.getSelectedItem() == "Seleccione una opción" || jComboBoxCochera.getSelectedItem() == "Seleccione una opción"){
                JOptionPane.showMessageDialog(null, "No es posible calcular el Total. Debido a que no se encuentra seleccionado un Inquilino, Departamento o Cochera");
            }else{
                if(!jTextFieldMonto.getText().isEmpty() && !jTextFieldOtraFactura.getText().isEmpty()){
                    if(comboCochera.getSelectedItem() != "Sin Cochera."){
                        Logica.Cochera unaCochera = (Logica.Cochera)comboCochera.getSelectedItem();
                        montoCochera = unaCochera.getPrecio();
                    }else{
                        montoCochera = 0;
                    }

                    if(!jTextFieldMonto.getText().isEmpty()){
                        montoAlquiler = Float.valueOf(jTextFieldMonto.getText());   // Es 0
                    }

                    if(!jTextFieldMonto.getText().isEmpty()){
                        otrasFacturas = Float.valueOf(jTextFieldOtraFactura.getText());
                    }

                    total = montoCochera+montoAlquiler+otrasFacturas;
                    jTextFieldTotal.setText(null);

                    jTextFieldTotal.setText(String.valueOf(total));
                    modificar = false;
                }else{
                    JOptionPane.showMessageDialog(null, "Debe completar los campos Monto y Otras Facturas");
                }
            }
        }
    }//GEN-LAST:event_jTextFieldTotalMouseClicked

    private void jTextFieldMontoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldMontoMouseClicked
        jTextFieldMonto.setText(null);
    }//GEN-LAST:event_jTextFieldMontoMouseClicked

    private void jTextFieldOtraFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldOtraFacturaMouseClicked
        jTextFieldOtraFactura.setText(null);
    }//GEN-LAST:event_jTextFieldOtraFacturaMouseClicked

    private boolean validar(){
        boolean validar = false, departamento = false, cochera = false, validarTotal = false;
        Date fecha = jDateChooserFecha.getDate();
        
        if(jComboBoxDepartamento.getSelectedItem() != "Seleccione una opción"){
            departamento = true;
            if(jComboBoxDepartamento.getSelectedItem() != "Sin Departamento."){
                Logica.Departamento unDepartamento = (Logica.Departamento)comboDepartamento.getSelectedItem();
                idDepartamento = unDepartamento.getId();
            }else{
                idDepartamento = 0;
            }
        }
        
        if(jComboBoxCochera.getSelectedItem() != "Seleccione una opción"){
            cochera = true;
            if(jComboBoxCochera.getSelectedItem() != "Sin Cochera."){
                Logica.Cochera unaCochera = (Logica.Cochera)comboCochera.getSelectedItem();
                idCochera = unaCochera.getId();
            }else{
                idCochera = 0;
            }
        }
        
        if(!jTextFieldTotal.getText().isEmpty()){
            try{
                float total = Float.valueOf(jTextFieldTotal.getText());
                validarTotal = true;
            }catch(Exception e){
                System.out.println("Error: "+e);
            }
        }
        //float total = Float.valueOf(jTextFieldTotal.getText());
        
        if(fecha != null && !jTextFieldMonto.getText().isEmpty() && !jTextFieldOtraFactura.getText().isEmpty() && departamento && cochera && validarTotal){
            validar = true;
        }else if(fecha == null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una Fecha.");
        }else if(jTextFieldMonto.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe cargar un valor en Monto.");
        }else if(jTextFieldOtraFactura.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe cargar un valor en Otras Facturas.");
        }else if(!departamento && !cochera){
            JOptionPane.showMessageDialog(null, "Debe seleccionar una opción en Departamento y Cochera.");
        }else if(!validarTotal){
            JOptionPane.showMessageDialog(null, "Debe cliquear en Total para obtener su resultado antes de Guardar.");
        }
        
        return validar;
    }
    
    private void cargarPanelDatos(long idAlquiler){
        this.jLabelAceptar.setText("Actualizar");
        modificar = true;
        Logica.Alquiler unAlquiler = unaControladora.obtenerAlquiler(idAlquiler);
        long idInquilino = unaControladora.obtenerInquilinoPorAlquiler(idEdificio, idAlquiler).getId();
        
        jDateChooserFecha.setDate(unAlquiler.getFecha());
        jTextFieldMonto.setText(String.valueOf(unAlquiler.getMonto()));
        jTextFieldOtraFactura.setText(String.valueOf(unAlquiler.getOtraFactura()));
        jTextFieldTotal.setText(String.valueOf(unAlquiler.getTotal()));
        cargarComboInquilino(idEdificio, idInquilino);
        jTextAreaDescripcion.setText(unAlquiler.getDescripcion());
        cargarCombos(idInquilino);
        
        icono("afuera");
    }
    
    private void cargarTablaAlquiler(int mesBusqueda, int anioBusqueda){
        limpiarComponentes();
        int mesAlquiler, anioAlquiler;
        String datos[] = new String[8];
        List<Logica.Alquiler> alquileres = new LinkedList();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM"),
                         yearFormat = new SimpleDateFormat("yyyy"),
                         dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        if(mesBusqueda != 0 && anioBusqueda != 0){
            for(Logica.Alquiler unAlquiler : unaControladora.obtenerAlquileresEdificio(idEdificio)){
                mesAlquiler = Integer.parseInt(monthFormat.format(unAlquiler.getFecha()));
                anioAlquiler = Integer.parseInt(yearFormat.format(unAlquiler.getFecha()));
                if(anioAlquiler == anioBusqueda && mesAlquiler == mesBusqueda){
                    alquileres.add(unAlquiler);
                }
            }
            
            if(alquileres.isEmpty()){
                JOptionPane.showMessageDialog(null, "No se ha encontrado el Alquiler.");
                alquileres = unaControladora.obtenerAlquileresEdificio(idEdificio);
            }
        }else{
            alquileres = unaControladora.obtenerAlquileresEdificio(idEdificio);
        }
        
        for(Logica.Alquiler unAlquiler : alquileres){
            datos[0] = String.valueOf(unAlquiler.getId());
            datos[1] = String.valueOf(dateFormat.format(unAlquiler.getFecha()));
            datos[2] = String.valueOf(unAlquiler.getMonto());
            datos[3] = String.valueOf(unAlquiler.getOtraFactura());
            datos[4] = String.valueOf(unAlquiler.getTotal());
            
            if(unAlquiler.getDepartamento() != 0){
                datos[5] = unaControladora.obtenerDepartamento(unAlquiler.getDepartamento()).getUbicacion();
            }else{
                datos[5] = "Sin Departamento.";
            }
            
            if(unAlquiler.getCochera() != 0){
                datos[6] = String.valueOf(unaControladora.obtenerCochera(unAlquiler.getCochera()).toString());
            }else{
                datos[6] = "Sin Cochera.";
            }
            
            datos[7] = unaControladora.obtenerInquilinoPorAlquiler(idEdificio, unAlquiler.getId()).toString();
            
            tablaAlquiler.addRow(datos);
        }
        
        //System.out.print("Total de Monto: $"+totalMonto+" - Total de Otras Facturas: $"+totalOtrasFacturas);
        
        this.jTableAlquiler.setModel(tablaAlquiler);
    }
    
    public void icono(String lugar){
        ImageIcon refresh = null;
        
        switch(lugar){
            case "adentro":
                refresh = new ImageIcon(getClass().getResource("/Visual/img/cargando.png"));
                if(modificar){
                    refresh = new ImageIcon(getClass().getResource("/Visual/img/cancelar.png"));
                }
                this.jPanelButtonRefresh.setBackground(new Color(255,255,255));
                this.jPanelButtonRefresh.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(51,51,51)));
                break;
            case  "afuera":
                refresh = new ImageIcon(getClass().getResource("/Visual/img/cargando_blanco.png"));
                if(modificar){
                    refresh = new ImageIcon(getClass().getResource("/Visual/img/cancelar_blanco.png"));
                }
                this.jPanelButtonRefresh.setBackground(new Color(51,51,51));
                break;
        }
        
        this.jLabelRefresh.setIcon(refresh);
    }
    
    public void limpiarComponentes(){
        jLabelAceptar.setText("Aceptar");
        jDateChooserFecha.setDate(null);
        jTextFieldMonto.setText("0");
        jTextFieldOtraFactura.setText("0");
        jTextFieldTotal.setText("Haga click aquí para calcular el TOTAL");
        jTextAreaDescripcion.setText(null);
        cargarComboInquilino(idEdificio, 0);
        cargarCombos(0);
        limpiarTabla();
        cargarComboAnio();
        
        jDateChooserFecha.requestFocus();
        icono("afuera");
    }
    
    public void limpiarTabla(){
        int tamanio = tablaAlquiler.getRowCount();
        
        for(int i = 0; i < tamanio; i++){
            tablaAlquiler.removeRow(0);
        }
        
        this.jTableAlquiler.setModel(tablaAlquiler);
    }
    
    public void cargarComboInquilino(long idEdificio, long idInquilino){
        List<Logica.Inquilino> inquilinos = new LinkedList();
        jComboBoxInquilino.removeAllItems();
        
        if(idInquilino != 0){
            inquilinos.add(unaControladora.obtenerInquilino(idInquilino));
        }else{
            //inquilinos = unaControladora.obtenerInquilinosSinAlquiler(idEdificio);
            inquilinos = unaControladora.obtenerInquilinosEdificio(idEdificio);
            comboInquilino.addElement("Seleccione una opción");
        }
        
        for(Logica.Inquilino unInquilino : inquilinos){
            comboInquilino.addElement(unInquilino);
        }
        
        this.jComboBoxInquilino.setModel(comboInquilino);
    }
    
    public void cargarCombos(long idInquilino){
        jComboBoxDepartamento.removeAllItems();
        jComboBoxCochera.removeAllItems();
        
        if(idInquilino != 0){
            Logica.Departamento unDepartamento = unaControladora.obtenerDepartamentoInquilino(idInquilino);
            if(unDepartamento != null){
                comboDepartamento.addElement(unDepartamento);
            }else{
                comboDepartamento.addElement("Sin Departamento.");
            }

            Logica.Cochera unaCochera = unaControladora.obtenerCocheraInquilino(idEdificio, idInquilino);
            if(unaCochera != null){
                comboCochera.addElement(unaCochera);
            }else{
                comboCochera.addElement("Sin Cochera.");
            }
            
        }else{
            comboDepartamento.addElement("Seleccione una opción");
            comboCochera.addElement("Seleccione una opción");
        }
        
        this.jComboBoxDepartamento.setModel(comboDepartamento);
        this.jComboBoxCochera.setModel(comboCochera);
    }
    
    public void cargarComboAnio(){
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        Date fechaActual = new Date();
        int anioActual = Integer.valueOf(yearFormat.format(fechaActual));
        
        if(anioActual > 2018){
            for(int i = 2018; i < anioActual;i++){
                comboAnio.addElement(i);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxAnio;
    private javax.swing.JComboBox<String> jComboBoxCochera;
    private javax.swing.JComboBox<String> jComboBoxDepartamento;
    private javax.swing.JComboBox<String> jComboBoxInquilino;
    private javax.swing.JComboBox<String> jComboBoxMesBusqueda;
    private com.toedter.calendar.JDateChooser jDateChooserFecha;
    private javax.swing.JLabel jLabelAceptar;
    private javax.swing.JLabel jLabelBuscar;
    private javax.swing.JLabel jLabelCochera;
    private javax.swing.JLabel jLabelDepartamento;
    private javax.swing.JLabel jLabelDescripcion;
    private javax.swing.JLabel jLabelEliminar;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelInquilino;
    private javax.swing.JLabel jLabelMonto;
    private javax.swing.JLabel jLabelOtraFactura;
    private javax.swing.JLabel jLabelRefresh;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelButtonAgregar;
    private javax.swing.JPanel jPanelButtonBuscar;
    private javax.swing.JPanel jPanelButtonEliminar;
    private javax.swing.JPanel jPanelButtonRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparatorMonto;
    private javax.swing.JTable jTableAlquiler;
    private javax.swing.JTextArea jTextAreaDescripcion;
    private javax.swing.JTextField jTextFieldMonto;
    private javax.swing.JTextField jTextFieldOtraFactura;
    private javax.swing.JTextField jTextFieldTotal;
    // End of variables declaration//GEN-END:variables
}
