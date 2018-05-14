package Logica;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Collections;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.util.logging.Logger;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.RegionUtil;

public class Reporte {
    private int ultimaFila;
    private final SimpleDateFormat formatoMes = new SimpleDateFormat("MM"),
            formatoAnio2 = new SimpleDateFormat("yy"),
            formatoAnio = new SimpleDateFormat("yyyy"),
            formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
    private final DecimalFormat formatoDecimal = new DecimalFormat("#.00");
    private final ControladoraL unaControladora = new ControladoraL();
    private final String colu[] = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13"};
    private final DefaultTableModel tabla = new DefaultTableModel(null, colu);
    private XSSFFont fuente1, fuente2, fuente3;
    private CellStyle estilo, estilo2, estilo3, estilo4, estilo5, estilo6, estilo7, estilo8, estilo9, estilo10;
    
    public Reporte(){
    }
    
    public boolean generarReporte(long idEdificio){
        boolean respuesta = false;
        Date fechaActual = new Date();
        Edificio unEdificio = unaControladora.obtenerEdificio(idEdificio);
        XSSFWorkbook libro = crearLibro();                                      // Devuelve el libro con los Estilos y Fuentes configurados para TABLA ALQUILER
        
        libro = generarAlquileres(libro, unEdificio);                           // Parametros: Libro configurado para tabla ALQUILER, se genera la hoja "RESUMEN A COBRAR"
        
        for (Departamento unDepartamento : unEdificio.getDepartamentos()) {     // GENERA EXPENSAS POR DEPARTAMENTO
            if (unDepartamento.getUnInquilino() != null){                       // Si existe Inquilino se crea una hoja dentro del Libro Excel (Departamento-Expensa). De lo contrario.. no existe un Inquilino por ende no se genera Expensa.
                libro = generarExpensas(libro, unDepartamento, unEdificio);     // Parametros: Libro con alquileres, se generan las hojas "Expensas" por Departamentos de un Edificio.
            }
        }
        
        try{
            JFileChooser Reporte = new JFileChooser();
            String nombreArchivo = "Reporte Edificio "+unEdificio.getNombre()+" "+formatoFecha.format(fechaActual);
            
            Reporte.setSelectedFile(new File(nombreArchivo));
            if (Reporte.showDialog(null, "Guardar Reporte") == JFileChooser.APPROVE_OPTION) {
                
                File archivoXLSX = Reporte.getSelectedFile();
                String nombre = archivoXLSX.getName();
                if (nombre.indexOf('.') == -1) {                         
                    nombre += ".xlsx";                                          // Si en el nombre no existe la extensión Excel se agrega.
                    archivoXLSX = new File(archivoXLSX.getParentFile(), nombre);
                    FileOutputStream fileOut = new FileOutputStream(archivoXLSX);
                    libro.write(fileOut);                                       // Se van pisando los reportes
                    libro.close();
                    respuesta = true;
                }else{
                    JOptionPane.showMessageDialog(null, "No se ha creado el documento. Debido a que no es posible agregar punto (.) en el nombre del archivo");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error: "+ex);
        }
        
        return respuesta;
    }

    public XSSFWorkbook crearLibro(){
        XSSFWorkbook libro = new XSSFWorkbook(); 
        
        estilo5 = libro.createCellStyle();                                      // ESTILOS PARA TABLA ALQUILER
        estilo5.setBorderTop(BorderStyle.THIN);
        estilo5.setBorderLeft(BorderStyle.THIN);
        estilo5.setBorderBottom(BorderStyle.THIN);
        estilo5.setBorderRight(BorderStyle.THIN);
        
        estilo6 = libro.createCellStyle();
        estilo6.setBorderTop(BorderStyle.THIN);
        estilo6.setBorderLeft(BorderStyle.THIN);
        estilo6.setBorderBottom(BorderStyle.THIN);
        estilo6.setBorderRight(BorderStyle.THIN);
        estilo6.setAlignment(HorizontalAlignment.CENTER);
        
        estilo7 = libro.createCellStyle();
        fuente3 = libro.createFont();
        fuente3.setBold(true);
        estilo7.setBorderTop(BorderStyle.THIN);
        estilo7.setBorderLeft(BorderStyle.THIN);
        estilo7.setBorderBottom(BorderStyle.THIN);
        estilo7.setBorderRight(BorderStyle.THIN);
        estilo7.setAlignment(HorizontalAlignment.CENTER);
        estilo7.setVerticalAlignment(VerticalAlignment.CENTER);
        estilo7.setFont(fuente3);
        
        estilo8 = libro.createCellStyle();
        estilo8.setBorderTop(BorderStyle.THIN);
        estilo8.setBorderLeft(BorderStyle.THIN);
        estilo8.setBorderRight(BorderStyle.THIN);
               
        estilo9 = libro.createCellStyle();
        estilo9.setBorderLeft(BorderStyle.THIN);
        estilo9.setBorderRight(BorderStyle.THIN);
        
        estilo10 = libro.createCellStyle();
        estilo10.setAlignment(HorizontalAlignment.RIGHT);
        estilo10.setBorderTop(BorderStyle.THIN);
        estilo10.setBorderLeft(BorderStyle.THIN);
        estilo10.setBorderBottom(BorderStyle.THIN);
        estilo10.setBorderRight(BorderStyle.THIN);
        //
        estilo = libro.createCellStyle();                                       //ESTILOS TABLA EXPENSAS (GENERADOR DE HOJAS DENTRO DEL LIBRO)
        fuente1 = libro.createFont();
        fuente1.setBold(true);
        fuente1.setFontHeightInPoints((short)14);
        fuente1.setItalic(true);
        estilo.setFont(fuente1);
        
        estilo2 = libro.createCellStyle();                                      // Estilo 2: Columnas con valores.. Bold-Calibri-12
        fuente2 = libro.createFont();
        fuente2.setBold(true);
        fuente2.setFontHeightInPoints((short)12);
        estilo2.setFont(fuente2);
        
        estilo3 = libro.createCellStyle();                                      // Estilo 3: Encuadre de tabla. Nombre servicio y sus montos
        estilo3.setBorderTop(BorderStyle.THIN);
        estilo3.setBorderLeft(BorderStyle.THIN);
        estilo3.setBorderBottom(BorderStyle.THIN);
        estilo3.setBorderRight(BorderStyle.THIN);
        
        estilo4 = libro.createCellStyle();                                      // Estilo 4: Encuadre de fila y Negrita. Para col "CONCEPTO" y col "MONTO"
        estilo4.setFont(fuente2);
        estilo4.setBorderTop(BorderStyle.THIN);
        estilo4.setBorderLeft(BorderStyle.THIN);
        estilo4.setBorderBottom(BorderStyle.THIN);
        estilo4.setBorderRight(BorderStyle.THIN);
        
        return libro;
    }
    
    public XSSFWorkbook generarAlquileres(XSSFWorkbook libro, Edificio unEdificio){
        Date fechaActual = new Date();
        int mesActual = Integer.valueOf(formatoMes.format(fechaActual)),
            anioActual = Integer.valueOf(formatoAnio.format(fechaActual)),
            anioActual2 = Integer.valueOf(formatoAnio2.format(fechaActual)),
            mesExpensa, anioExpensa = anioActual2, fi;
        
        if(mesActual == 1){
            mesExpensa = 12;
            anioExpensa = anioActual-1;
        }else{
            mesExpensa = mesActual-1;
        }
        
        Sheet hojaAlquiler = libro.createSheet("RESUMEN A COB");                // Se genera la hoja de ALQUILER
        CellRangeAddress rango = new CellRangeAddress(1, 1, 1, 14), rango2 = new CellRangeAddress(2, 2, 1, 14);
        hojaAlquiler.addMergedRegion(rango);                                    // Fusiona las columnas de los 2 últimos parametros
        hojaAlquiler.addMergedRegion(rango2);
        
        short tamFila3 = 600, tamFilaDefault = 400;                             // Se setea el tamaño de filas
        
                                                                                // Se setea el tamaño de las columnas
        hojaAlquiler.setColumnWidth(1, 1500);                                   // Columna n° 1 (B), tamaño [DPTO]
        hojaAlquiler.setColumnWidth(2, 7500);                                   // Columna n° 2 (B), tamaño [INQUILINO]
        hojaAlquiler.setColumnWidth(3, 2700);                                   // Columna n° 3 (C), tamaño [ALQUILER]
        hojaAlquiler.setColumnWidth(4, 2700);                                   // Columna n° 4 (C), tamaño [OTRAS F.]
        hojaAlquiler.setColumnWidth(5, 3900);                                   // Columna n° 5 (C), tamaño [EXPENSAS]
        hojaAlquiler.setColumnWidth(6, 2700);                                   // Columna n° 6 (C), tamaño [COCHERAS]
        hojaAlquiler.setColumnWidth(7, 3500);                                   // Columna n° 7 (C), tamaño [INTERESESxATRASOS]
        hojaAlquiler.setColumnWidth(8, 3500);                                   // Columna n° 8 (C), tamaño [SALDOmesANTERIOR]
        hojaAlquiler.setColumnWidth(9, 2600);                                   // Columna n° 9 (C), tamaño [TOTAL]
        hojaAlquiler.setColumnWidth(10, 3900);                                  // Columna n° 10 (C), tamaño [PAGOEFECTIVO]
        hojaAlquiler.setColumnWidth(11, 3700);                                  // Columna n° 11 (C), tamaño [PAGOTARJETA]
        hojaAlquiler.setColumnWidth(12, 3500);                                  // Columna n° 12 (C), tamaño [PAGOBANCO]
        hojaAlquiler.setColumnWidth(13, 3200);                                  // Columna n° 13 (C), tamaño [SALDO]
        hojaAlquiler.setColumnWidth(14, 3200);                                  // Columna n° 13 (C), tamaño [FECHAPAGO]
                
        Row fila = hojaAlquiler.createRow(0);                                   // FILA 0
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("");
        
        fila = hojaAlquiler.createRow(1);                                       // FILA 1
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("EDIFICIO "+unEdificio.getNombre());
        //fila.getCell(1).setCellStyle(estilo8);
        
        RegionUtil.setBorderTop(BorderStyle.THIN, rango, hojaAlquiler);
        RegionUtil.setBorderLeft(BorderStyle.THIN, rango, hojaAlquiler);
        RegionUtil.setBorderRight(BorderStyle.THIN, rango, hojaAlquiler);
        
        fila = hojaAlquiler.createRow(2);                                       // FILA 2
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("PERIODO: "+mesActual+"/"+anioActual2);
        //fila.getCell(1).setCellStyle(estilo9);
        
        RegionUtil.setBorderLeft(BorderStyle.THIN, rango2, hojaAlquiler);
        RegionUtil.setBorderRight(BorderStyle.THIN, rango2, hojaAlquiler);
        RegionUtil.setBorderBottom(BorderStyle.THIN, rango2, hojaAlquiler);
        
        Row encabezado = hojaAlquiler.createRow(3);                             //FILA 3
        encabezado.setHeight(tamFila3);
        encabezado.createCell(0).setCellValue("");
        
        encabezado.createCell(1).setCellValue("DPTO");                          // COLUMNA 1, FILA 3
        encabezado.getCell(1).setCellStyle(estilo7);
        
        encabezado.createCell(2).setCellValue("INQUILINO");                     // COLUMNA 2, FILA 3
        encabezado.getCell(2).setCellStyle(estilo7);
            
        encabezado.createCell(3).setCellValue("ALQ. "+mesActual+"/"+anioActual2);// COLUMNA 3, FILA 3
        encabezado.getCell(3).setCellStyle(estilo7);
        
        encabezado.createCell(4).setCellValue("OTRAS F.");                      // COLUMNA 4, FILA 3
        encabezado.getCell(4).setCellStyle(estilo7);
        
        encabezado.createCell(5).setCellValue("EXPENSAS "+mesExpensa+"/"+anioExpensa);// COLUMNA 5, FILA 3
        encabezado.getCell(5).setCellStyle(estilo7);
        
        encabezado.createCell(6).setCellValue("COCHERAS");                      // COLUMNA 6, FILA 3
        encabezado.getCell(6).setCellStyle(estilo7);
        
        encabezado.createCell(7).setCellValue("interes por atr.");              // COLUMNA 7, FILA 3
        encabezado.getCell(7).setCellStyle(estilo7);
        
        encabezado.createCell(8).setCellValue("saldo mes ant.");                // COLUMNA 8, FILA 3
        encabezado.getCell(8).setCellStyle(estilo7);
            
        encabezado.createCell(9).setCellValue("TOTAL");                         // COLUMNA 9, FILA 3
        encabezado.getCell(9).setCellStyle(estilo7);
        
        encabezado.createCell(10).setCellValue("PAGO EFECTIVO");                // COLUMNA 10, FILA 3
        encabezado.getCell(10).setCellStyle(estilo7);
        
        encabezado.createCell(11).setCellValue("PAGO TARJETA");                 // COLUMNA 11, FILA 3
        encabezado.getCell(11).setCellStyle(estilo7);
        
        encabezado.createCell(12).setCellValue("PAGO BANCO");                   // COLUMNA 12, FILA 3
        encabezado.getCell(12).setCellStyle(estilo7);
        
        encabezado.createCell(13).setCellValue("SALDO "+mesActual+"/"+anioActual2);// COLUMNA 13, FILA 3
        encabezado.getCell(13).setCellStyle(estilo7);
        
        encabezado.createCell(14).setCellValue("FECHA PAGO");                   // COLUMNA 14, FILA 3
        encabezado.getCell(14).setCellStyle(estilo7);
        
        
        String col[] = new String[14];
        fi = 4;
        for(Inquilino unInquilino : unaControladora.obtenerInquilinosEdificio(unEdificio.getId())){
            /* Se debe obtener todos los Totales de los Alquileres + los Totales de las Expensas que no se pagaron.
            También obtener sus Intereses por Atrasos (de Total Alquiler n + Total Expensa n)generados a la FECHA ACTUAL */
            String ubicacionDepto = "";
            List<Alquiler> alquileresInpagos = unaControladora.obtenerAlquileresInpagos(unInquilino.getId());
            Departamento unDepto = unaControladora.obtenerDepartamentoInquilino(unInquilino.getId());
            Alquiler ultimoAlquiler;
            Expensa ultimaExpensa;
            
            float montoAlquiler = 0, otrasFacturas = 0, montoExpensa = 0, precioCochera = 0,
                  totalesPorIntereses = 0, saldoMesAnterior = 0, totales = 0, efectivo = 0,
                  tarjeta = 0, banco = 0, saldo = 0;
            
            if(alquileresInpagos.size() > 0){
                for(Alquiler unAlquilerInpago : alquileresInpagos){
                    totales += unAlquilerInpago.getTotal(); // Sumas el total Alquiler
                    int mesAlquiler = Integer.valueOf(formatoMes.format(unAlquilerInpago.getFecha())); // Mes Expensa
                    int anioExpensa2 = Integer.valueOf(formatoAnio.format(unAlquilerInpago.getFecha())); // Año Expensa
                    if(unDepto != null){
                        ubicacionDepto = unDepto.getUbicacion();
                        Expensa unaExpensaInpaga = unaControladora.obtenerExpensa(unDepto.getId(), mesAlquiler, anioExpensa2);
                        if(unaExpensaInpaga != null){
                            totales += unaExpensaInpaga.getMonto(); // Suma el total Expensa
                            float intereses = unaControladora.interesPorAtraso(fechaActual, totales, mesAlquiler);
                            totalesPorIntereses += intereses;
                            saldoMesAnterior = totales+totalesPorIntereses;
                            totales += totalesPorIntereses;
                        }
                    }
                }
            }else{
                List<Pago> pagosInquilino = unaControladora.obtenerPagosInquilino(unInquilino.getId());

                if(pagosInquilino.size() > 0){
                    Collections.sort(pagosInquilino, (Pago p1, Pago p2) -> p1.getFecha().compareTo(p2.getFecha()));
                    Pago ultimoPago = pagosInquilino.get(pagosInquilino.size()-1);
                    totales = ultimoPago.getMonto();
                    saldoMesAnterior = obtenerSaldoMesAnterior(unInquilino.getId());
                }
            }
            
            Date fechaPago = null;
            if(unInquilino.getAlquileres().size() > 0){
                ultimoAlquiler = obtenerUltiAlquilerInquilino(unInquilino);
                if(unDepto != null){
                    ubicacionDepto = unDepto.getUbicacion();
                    ultimaExpensa = unaControladora.obtenerExpensa(unDepto.getId(), Integer.valueOf(formatoMes.format(ultimoAlquiler.getFecha())), Integer.valueOf(formatoAnio.format(ultimoAlquiler.getFecha())));
                    montoExpensa = ultimaExpensa.getMonto();
                }
                montoAlquiler = ultimoAlquiler.getMonto();
                otrasFacturas = ultimoAlquiler.getOtraFactura();
                Cochera unaCochera = unaControladora.obtenerCochera(ultimoAlquiler.getCochera());
                
                if(unaCochera != null){
                    precioCochera = unaCochera.getPrecio();
                }

                if(ultimoAlquiler.getUnPago() != null){
                    if(totales == 0){
                        totales = ultimoAlquiler.getUnPago().getMonto();
                    }
                    efectivo = ultimoAlquiler.getUnPago().getEfectivo();
                    tarjeta = ultimoAlquiler.getUnPago().getTarjeta();
                    banco = ultimoAlquiler.getUnPago().getBanco();
                    saldo = unInquilino.getSaldoMesAnt();
                    fechaPago = ultimoAlquiler.getUnPago().getFecha();
                }else{
                    saldo = totales;
                    fechaPago = null;
                }
            }
            
            try{
                col[0] = ubicacionDepto;
                col[1] = unInquilino.getApellido()+", "+unInquilino.getNombre();
                col[2] = "$ "+String.valueOf(montoAlquiler);
                col[3] = "$ "+String.valueOf(otrasFacturas);
                col[4] = "$ "+unaControladora.reemplazarString(formatoDecimal.format(montoExpensa));
                col[5] = "$ "+String.valueOf(precioCochera);
                col[6] = "$ "+formatoDecimal.format(totalesPorIntereses);
                col[7] = "$ "+formatoDecimal.format(saldoMesAnterior);
                col[8] = "$ "+formatoDecimal.format(totales);
                col[9] = "$ "+String.valueOf(efectivo);
                col[10] = "$ "+String.valueOf(tarjeta);
                col[11] = "$ "+String.valueOf(banco);
                col[12] = "$ "+formatoDecimal.format(saldo);
                if(fechaPago != null){
                    col[13] = formatoFecha.format(fechaPago);
                }else{
                    col[13] = "";
                }
                
                tabla.addRow(col);
            }catch(Exception e){
                System.out.println("Error 1: "+e);
            }
            
        }
        
        try{
            if(tabla.getRowCount() > 0){
                int tam = tabla.getRowCount();
                String [][] tab = new String[tam][14];

                for(int i = 0; i < tam; i++){
                    for(int j = 0; j < 14; j++){
                        tab[i][j] = (String) tabla.getValueAt(i, j);
                    }
                }

                Arrays.sort(tab, (String[] t, String[] t1) -> t[0].compareTo(t1[0]));

                for(int i = 0; i < tam; i++){                        
                    fila = hojaAlquiler.createRow(fi++);
                    fila.setHeight(tamFilaDefault);
                    fila.createCell(1).setCellValue(tab[i][0]);
                    fila.getCell(1).setCellStyle(estilo5);
                    fila.createCell(2).setCellValue(tab[i][1]);
                    fila.getCell(2).setCellStyle(estilo5);
                    fila.createCell(3).setCellValue(tab[i][2]);
                    fila.getCell(3).setCellStyle(estilo10);
                    fila.createCell(4).setCellValue(tab[i][3]);
                    fila.getCell(4).setCellStyle(estilo10);
                    fila.createCell(5).setCellValue(tab[i][4]);
                    fila.getCell(5).setCellStyle(estilo10);
                    fila.createCell(6).setCellValue(tab[i][5]);
                    fila.getCell(6).setCellStyle(estilo10);
                    fila.createCell(7).setCellValue(tab[i][6]);
                    fila.getCell(7).setCellStyle(estilo10);
                    fila.createCell(8).setCellValue(tab[i][7]);
                    fila.getCell(8).setCellStyle(estilo10);
                    fila.createCell(9).setCellValue(tab[i][8]);
                    fila.getCell(9).setCellStyle(estilo10);
                    fila.createCell(10).setCellValue(tab[i][9]);
                    fila.getCell(10).setCellStyle(estilo10);
                    fila.createCell(11).setCellValue(tab[i][10]);
                    fila.getCell(11).setCellStyle(estilo10);
                    fila.createCell(12).setCellValue(tab[i][11]);
                    fila.getCell(12).setCellStyle(estilo10);
                    fila.createCell(13).setCellValue(tab[i][12]);          
                    fila.getCell(13).setCellStyle(estilo10);
                    fila.createCell(14).setCellValue(tab[i][13]);
                    fila.getCell(14).setCellStyle(estilo10);
                }
            }
        }catch(Exception e){
            System.out.println("Error 2: "+e);
        }
        
        
        return libro;
    }
    
    public XSSFWorkbook generarExpensas(XSSFWorkbook libro, Departamento unDepartamento, Edificio unEdificio){
        Sheet hoja = libro.createSheet("Expensa " + unDepartamento.getUbicacion()); // Se genera una hoja dentro del Libro Excel
        
        hoja.setColumnWidth(1, 13700);                                          // Columna n° 1 (B), tamaño
        hoja.setColumnWidth(2, 7350);                                           // Columna n° 2 (C), tamaño
    
        // FILA 1
        Row fila = hoja.createRow(1);
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("EDIFICIO "+unEdificio.getNombre());
        fila.getCell(1).setCellStyle(estilo);
        fila.createCell(2).setCellValue(unEdificio.getDireccion());

        // FILA 2
        fila = hoja.createRow(2);
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("RECIBO DE EXPENSA");

        // FILA 3
        fila = hoja.createRow(3);
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("NOMBRE LOCATARIO");
        fila.createCell(2).setCellValue(unDepartamento.getUnInquilino().toString());
        fila.getCell(2).setCellStyle(estilo2);

        // FILA 4
        fila = hoja.createRow(4);
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("DEPARTAMENTO");
        fila.createCell(2).setCellValue(unDepartamento.getUbicacion());
        fila.getCell(2).setCellStyle(estilo2);

        // FILA 5
        fila = hoja.createRow(5);
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("PERIODO");
        if(unDepartamento.getExpensas().size() > 0){
            int ultimaPos = unDepartamento.getExpensas().size();
            Expensa ultimaExpensa = unDepartamento.getExpensas().get(ultimaPos-1);
            int f = 7, indice, cantidadServicioExpensa = ultimaExpensa.getServiciosExpensa().size();

            fila.createCell(2).setCellValue("EXPENSAS "+unDepartamento.getExpensas().get(ultimaPos-1).getMes()+"/"+unDepartamento.getExpensas().get(ultimaPos-1).getAnio()); // Traer el último período y año de la Expensa
            fila.getCell(2).setCellStyle(estilo2);

            // FILA 6
            fila = hoja.createRow(6);
            fila.createCell(0).setCellValue("");
            fila.createCell(1).setCellValue("CONCEPTOS");
            fila.getCell(1).setCellStyle(estilo3);
            fila.createCell(2).setCellValue("MONTO");
            fila.getCell(2).setCellStyle(estilo3);

            // Se autogeneran las filas de servicios que posee esta expensa.
            for(indice = 0; indice < cantidadServicioExpensa; indice++){
                fila = hoja.createRow(f++);
                fila.createCell(0).setCellValue("");
                fila.createCell(1).setCellValue(ultimaExpensa.getServiciosExpensa().get(indice).getNombre());
                fila.getCell(1).setCellStyle(estilo3);
                String montoSinComa = formatoDecimal.format(ultimaExpensa.getServiciosExpensa().get(indice).getMonto()).replace(",", ".");
                float montoFinal = Float.parseFloat(montoSinComa);
                fila.createCell(2).setCellValue(montoFinal);
                fila.getCell(2).setCellStyle(estilo3);
                fila.getCell(2).getNumericCellValue();// Hacer algo al respecto con esto.. y con el Simbolo $ en montoFinal. Linea 331
            }
            
            ultimaFila = f;

            fila = hoja.createRow(f++);
            fila.createCell(0).setCellValue("");
            fila.createCell(1).setCellValue("TOTAL");
            fila.getCell(1).setCellStyle(estilo4);
            fila.createCell(2).setCellFormula("SUM(C8:C"+ultimaFila+")");
            fila.getCell(2).setCellStyle(estilo4);
        }
        
        return libro;
    }
    
    public Alquiler obtenerUltiAlquilerInquilino(Inquilino unInquilino){
        Date fechaActual = new Date();
        int mesAlquiler, anioAlquiler, mesActual = Integer.parseInt(formatoMes.format(fechaActual)), anioActual = Integer.parseInt(formatoAnio.format(fechaActual));
        Alquiler ultimoAlquiler = null;
        List<Alquiler> alquileres = new LinkedList();
        
        for(Alquiler unAlqui : unInquilino.getAlquileres()){
            alquileres.add(unAlqui);
        }
        
        if(alquileres.size() > 0){
            Collections.sort(alquileres, (Alquiler a1, Alquiler a2) -> a1.getFecha().compareTo(a2.getFecha()));
            int ultimoIndex = alquileres.size()-1;
            mesAlquiler = Integer.parseInt(formatoMes.format(alquileres.get(ultimoIndex).getFecha()));
            anioAlquiler = Integer.parseInt(formatoAnio.format(alquileres.get(ultimoIndex).getFecha()));
            if((mesAlquiler == mesActual) && (anioAlquiler == anioActual)){
                 ultimoAlquiler = alquileres.get(ultimoIndex);
            }
        }
        
        return ultimoAlquiler;
    }
    
    public float obtenerSaldoMesAnterior(long idInquilino){
        List<Pago> pagosInquilino = unaControladora.obtenerPagosInquilino(idInquilino);
        List<Pago> pagosOrdenados = new LinkedList();
        float saldoMesAnterior = 0;
        
        if(pagosInquilino.size() > 0){
            for(Pago unPago : pagosInquilino){
                pagosOrdenados.add(unPago);
            }
            
            Collections.sort(pagosOrdenados, (Pago p1, Pago p2) -> p1.getFecha().compareTo(p2.getFecha()));
            if(pagosInquilino.size() > 1){
                saldoMesAnterior = pagosOrdenados.get(pagosOrdenados.size()-2).getSaldo();
            }
        }
        
        return saldoMesAnterior;
    }
}
