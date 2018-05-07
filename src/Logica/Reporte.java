package Logica;

import java.io.File;
import java.util.Date;
import java.io.IOException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.util.logging.Logger;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Reporte {

    public Reporte(){
    }

    public boolean crearLibro(long idEdificio){
        ControladoraL unaControladora = new ControladoraL();
        Edificio unEdificio = unaControladora.obtenerEdificio(idEdificio);
        int ultimaFila;
        boolean bandera = false;
        SimpleDateFormat formatoMes = new SimpleDateFormat("MM"),
                         formatoAnio2 = new SimpleDateFormat("yy"),
                         formatoAnio = new SimpleDateFormat("yyyy");
        Date fechaActual = new Date();
        DecimalFormat formatoDecimal = new DecimalFormat("#.00");
        // Se crea el Libro Excel
        XSSFWorkbook libro = new XSSFWorkbook(); 
        // Se crean las variables de las fuentes a ocupar
        XSSFFont fuente1, fuente2, fuente3;
        // Se crean las variables de los estilos a ocupar
        CellStyle estilo, estilo2, estilo3, estilo4, estilo5, estilo6, estilo7, estilo8, estilo9;
        
        //ESTILOS TABLA EXPENSAS (GENERADOR DE HOJAS DENTRO DEL LIBRO)
        estilo = libro.createCellStyle();
        fuente1 = libro.createFont();
        fuente1.setBold(true);
        fuente1.setFontHeightInPoints((short)14);
        fuente1.setItalic(true);
        estilo.setFont(fuente1);
        // Estilo 2: Columnas con valores.. Bold-Calibri-12
        estilo2 = libro.createCellStyle();
        fuente2 = libro.createFont();
        fuente2.setBold(true);
        fuente2.setFontHeightInPoints((short)12);
        estilo2.setFont(fuente2);
        // Estilo 3: Encuadre de tabla. Nombre servicio y sus montos
        estilo3 = libro.createCellStyle();
        estilo3.setBorderTop(BorderStyle.THIN);
        estilo3.setBorderLeft(BorderStyle.THIN);
        estilo3.setBorderBottom(BorderStyle.THIN);
        estilo3.setBorderRight(BorderStyle.THIN);
        // Estilo 4: Encuadre de fila y Negrita. Para col "CONCEPTO" y col "MONTO"
        estilo4 = libro.createCellStyle();
        estilo4.setFont(fuente2);
        estilo4.setBorderTop(BorderStyle.THIN);
        estilo4.setBorderLeft(BorderStyle.THIN);
        estilo4.setBorderBottom(BorderStyle.THIN);
        estilo4.setBorderRight(BorderStyle.THIN);
        
        // ESTILOS PARA TABLA ALQUILER
        estilo5 = libro.createCellStyle();
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
        estilo7.setFont(fuente3);
        
        estilo8 = libro.createCellStyle();
        estilo8.setBorderTop(BorderStyle.THIN);
        estilo8.setBorderLeft(BorderStyle.THIN);
        estilo8.setBorderRight(BorderStyle.THIN);
        
        estilo9 = libro.createCellStyle();
        estilo9.setBorderLeft(BorderStyle.THIN);
        estilo9.setBorderRight(BorderStyle.THIN);
        
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
        
        // Se genera la hoja de ALQUILER
        Sheet hojaAlquiler = libro.createSheet("RESUMEN A COB");
        hojaAlquiler.addMergedRegion(new CellRangeAddress(1, 1, 1, 13));    // Fusiona las columnas de los 2 últimos parametros
        hojaAlquiler.addMergedRegion(new CellRangeAddress(2, 2, 1, 13));
        
        // Se setea el tamaño de las columnas
        hojaAlquiler.setColumnWidth(1, 1500); // Columna n° 1 (B), tamaño [DPTO]
        hojaAlquiler.setColumnWidth(2, 7500); // Columna n° 2 (B), tamaño [INQUILINO]
        hojaAlquiler.setColumnWidth(3, 2700); // Columna n° 3 (C), tamaño [ALQUILER]
        hojaAlquiler.setColumnWidth(4, 2700); // Columna n° 4 (C), tamaño [OTRAS F.]
        hojaAlquiler.setColumnWidth(5, 3900); // Columna n° 5 (C), tamaño [EXPENSAS]
        hojaAlquiler.setColumnWidth(6, 2700); // Columna n° 6 (C), tamaño [COCHERAS]
        hojaAlquiler.setColumnWidth(7, 3500); // Columna n° 7 (C), tamaño [INTERESESxATRASOS]
        hojaAlquiler.setColumnWidth(8, 3500); // Columna n° 8 (C), tamaño [SALDOmesANTERIOR]
        hojaAlquiler.setColumnWidth(9, 2600); // Columna n° 9 (C), tamaño [TOTAL]
        hojaAlquiler.setColumnWidth(10, 3900); // Columna n° 10 (C), tamaño [PAGOEFECTIVO]
        hojaAlquiler.setColumnWidth(11, 3700); // Columna n° 11 (C), tamaño [PAGOTARJETA]
        hojaAlquiler.setColumnWidth(12, 3500); // Columna n° 12 (C), tamaño [PAGOBANCO]
        hojaAlquiler.setColumnWidth(13, 3200); // Columna n° 13 (C), tamaño [SALDO]
        
        // FILA 0
        Row fila = hojaAlquiler.createRow(0);
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("");
        
        // FILA 1
        fila = hojaAlquiler.createRow(1);
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("EDIFICIO "+unEdificio.getNombre());
        fila.getCell(1).setCellStyle(estilo8);
        // FILA 2
        fila = hojaAlquiler.createRow(2);
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("PERIODO: "+mesActual+"/"+anioActual2);
        fila.getCell(1).setCellStyle(estilo9);
        //FILA 3
        fila = hojaAlquiler.createRow(3);
        fila.createCell(0).setCellValue("");
        // COLUMNA 1, FILA 3
        fila.createCell(1).setCellValue("DPTO");
        fila.getCell(1).setCellStyle(estilo7);
        // COLUMNA 2, FILA 3
        fila.createCell(2).setCellValue("INQUILINO");
        fila.getCell(2).setCellStyle(estilo7);
        // COLUMNA 3, FILA 3
        fila.createCell(3).setCellValue("ALQ. "+mesActual+"/"+anioActual2);
        fila.getCell(3).setCellStyle(estilo7);
        // COLUMNA 4, FILA 3
        fila.createCell(4).setCellValue("OTRAS F.");
        fila.getCell(4).setCellStyle(estilo7);
        // COLUMNA 5, FILA 3
        fila.createCell(5).setCellValue("EXPENSAS "+mesExpensa+"/"+anioExpensa);
        fila.getCell(5).setCellStyle(estilo7);
        // COLUMNA 6, FILA 3
        fila.createCell(6).setCellValue("COCHERAS");
        fila.getCell(6).setCellStyle(estilo7);
        // COLUMNA 7, FILA 3
        fila.createCell(7).setCellValue("interes por atr.");
        fila.getCell(7).setCellStyle(estilo7);
        // COLUMNA 8, FILA 3
        fila.createCell(8).setCellValue("saldo mes ant.");
        fila.getCell(8).setCellStyle(estilo7);
        // COLUMNA 9, FILA 3
        fila.createCell(9).setCellValue("TOTAL");
        fila.getCell(9).setCellStyle(estilo7);
        // COLUMNA 10, FILA 3
        fila.createCell(10).setCellValue("PAGO EFECTIVO");
        fila.getCell(10).setCellStyle(estilo7);
        // COLUMNA 11, FILA 3
        fila.createCell(11).setCellValue("PAGO TARJETA");
        fila.getCell(11).setCellStyle(estilo7);
        // COLUMNA 12, FILA 3
        fila.createCell(12).setCellValue("PAGO BANCO");
        fila.getCell(12).setCellStyle(estilo7);
        // COLUMNA 13, FILA 3
        fila.createCell(13).setCellValue("SALDO "+mesActual+"/"+anioActual2);
        fila.getCell(13).setCellStyle(estilo7);
        
        // DESDE FILA 3 SE GENERAN AUTOMATICAMENTE LAS FILAS
        fi = 4;
        Departamento unDepto;
        Cochera unaCochera;
        Expensa unaExpensa = null;
        
        for(Inquilino unInquilino : unaControladora.obtenerInquilinosEdificio(idEdificio)){
            Alquiler ultimoAlquiler = unaControladora.obtenerUltimoAlquiInquilino(unInquilino.getId());
            if(ultimoAlquiler != null){
                fila = hojaAlquiler.createRow(fi++);
                int mesAlquiler = Integer.valueOf(formatoMes.format(ultimoAlquiler.getFecha())), anioAlquiler = Integer.valueOf(formatoAnio.format(ultimoAlquiler.getFecha()));
                if(ultimoAlquiler.getDepartamento() > 0){
                    unDepto = unaControladora.obtenerDepartamento(ultimoAlquiler.getDepartamento());
                    fila.createCell(1).setCellValue(unDepto.getUbicacion());
                    fila.getCell(1).setCellStyle(estilo5);
                    unaExpensa = unaControladora.obtenerExpensa(unDepto.getId(), mesAlquiler, anioAlquiler);
                }else{
                    fila.createCell(1).setCellValue("");
                    fila.getCell(1).setCellStyle(estilo5);
                }
                fila.createCell(2).setCellValue(unInquilino.getApellido()+", "+unInquilino.getNombre());
                fila.getCell(2).setCellStyle(estilo5);
                fila.createCell(3).setCellValue("$ "+ultimoAlquiler.getMonto());
                fila.getCell(3).setCellStyle(estilo5);
                fila.createCell(4).setCellValue("$ "+ultimoAlquiler.getOtraFactura());
                fila.getCell(4).setCellStyle(estilo5);
                
                if(unaExpensa != null){
                    fila.createCell(5).setCellValue("$ "+formatoDecimal.format(unaExpensa.getMonto()));
                    fila.getCell(5).setCellStyle(estilo5);
                }else{
                    fila.createCell(5).setCellValue("");    // No debería entrar nunca.. Pero por las moscas
                    fila.getCell(5).setCellStyle(estilo5);
                }
                
                if(ultimoAlquiler.getCochera() > 0){
                    unaCochera = unaControladora.obtenerCochera(ultimoAlquiler.getCochera());
                    fila.createCell(6).setCellValue("$ "+unaCochera.getPrecio());
                    fila.getCell(6).setCellStyle(estilo5);
                }else{
                    fila.createCell(6).setCellValue("");
                    fila.getCell(6).setCellStyle(estilo5);
                }
                
                fila.createCell(7).setCellValue(""); // Interes x atraso.
                fila.getCell(7).setCellStyle(estilo5);
                fila.createCell(8).setCellValue(""); // Saldo mes ant.
                fila.getCell(8).setCellStyle(estilo5);
                fila.createCell(9).setCellValue("$ "+ultimoAlquiler.getTotal());
                fila.getCell(9).setCellStyle(estilo5);
                fila.createCell(10).setCellValue("PAGO EFECTIVO");
                fila.getCell(10).setCellStyle(estilo5);
                fila.createCell(11).setCellValue("PAGO TARJETA");
                fila.getCell(11).setCellStyle(estilo5);
                fila.createCell(12).setCellValue("PAGO BANCO");
                fila.getCell(12).setCellStyle(estilo5);
                fila.createCell(13).setCellValue("SALDO "+mesActual+"/"+anioActual2);
                fila.getCell(13).setCellStyle(estilo5);
            }
        }
        
        for (Departamento unDepartamento : unEdificio.getDepartamentos()) {
            if (unDepartamento.getUnInquilino() != null){  // Si existe Inquilino se crea una hoja dentro del Libro Excel (Departamento-Expensa)
                Sheet hoja = libro.createSheet("Expensa " + unDepartamento.getUbicacion()); // Se genera una hoja dentro del Libro Excel
                hoja.setColumnWidth(1, 13700); // Columna n° 1 (B), tamaño
                hoja.setColumnWidth(2, 7350); // Columna n° 2 (C), tamaño
                
                // FILA 1
                fila = hoja.createRow(1);
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
                        String montoSinComa = (formatoDecimal.format(ultimaExpensa.getServiciosExpensa().get(indice).getMonto())).replace(',', '.');
                        Double montoFinal = Double.valueOf(montoSinComa);
                        fila.createCell(2).setCellValue(montoFinal);
                        fila.getCell(2).setCellStyle(estilo3);
                        fila.getCell(2).getNumericCellValue();
                        bandera = true;
                    }
                    ultimaFila = f;
                    
                    fila = hoja.createRow(f++);
                    fila.createCell(0).setCellValue("");
                    fila.createCell(1).setCellValue("TOTAL");
                    fila.getCell(1).setCellStyle(estilo4);
                    fila.createCell(2).setCellFormula("SUM(C8:C"+ultimaFila+")");
                    fila.getCell(2).setCellStyle(estilo4);
                }
            }
            
        }
        try{
            JFileChooser Reporte = new JFileChooser();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
            String nombreEdi = unaControladora.obtenerEdificio(idEdificio).getNombre();
            String nombreArchivo = "Expensas "+nombreEdi+" "+formatoFecha.format(fechaActual);
            
            Reporte.setSelectedFile(new File(nombreArchivo));
            if (Reporte.showDialog(null, "Guardar Reporte") == JFileChooser.APPROVE_OPTION) {
                
                File archivoXLSX = Reporte.getSelectedFile();
                String nombre = archivoXLSX.getName();
                if (nombre.indexOf('.') == -1) {
                // Si en el nombre no existe la extensión Excel se agrega.
                    nombre += ".xlsx";
                    archivoXLSX = new File(archivoXLSX.getParentFile(), nombre);
                    FileOutputStream fileOut = new FileOutputStream(archivoXLSX);
                    // Se van pisando los reportes
                    libro.write(fileOut);
                    libro.close();
                }else{
                    JOptionPane.showMessageDialog(null, "No se ha creado el documento. Debido a que no es posible agregar punto (.) en el nombre del archivo");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error: "+ex);
        }
        
        return bandera;
    }
}
