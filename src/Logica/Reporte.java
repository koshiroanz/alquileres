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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Reporte {

    public Reporte(){
    }

    public void crearLibro(long idEdificio){
        ControladoraL unaControladora = new ControladoraL();
        Edificio unEdificio = unaControladora.obtenerEdificio(idEdificio);
        int ultimaFila = 0;
        SimpleDateFormat formatoMes = new SimpleDateFormat("MM"), formatoAnio = new SimpleDateFormat("yyyy");
        Date fechaActual = new Date();
        DecimalFormat formatoDecimal = new DecimalFormat("#.00");
        formatoDecimal.setMinimumFractionDigits(2);
        
        XSSFWorkbook libro = new XSSFWorkbook(); // Se crea el Libro Excel
        CellStyle estilo, estilo2, estilo3, estilo4, estilo5, estilo6, estilo7;
        XSSFFont fuente1, fuente2, fuente3, fuente4, fuente5;
        
        // Se genera una hoja para la Tabla ALQUILERES
        Sheet hojaAlquiler = libro.createSheet("RESUMEN A COB"); // Se crea la hoja para la Tabla ALQUILERES
        // Se definen estilos para la Tabla ALQUILERES
        
        // Se generan automaticamente las filas
        int mesActual = Integer.valueOf(formatoMes.format(fechaActual)), anioActual = Integer.valueOf(formatoAnio.format(fechaActual));
        
        // FILA 0
        Row fila = hojaAlquiler.createRow(0);
        fila.createCell(0).setCellValue("EDIFICIO "+unEdificio.getNombre());
        
        fila = hojaAlquiler.createRow(1);
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("PERIODO "+mesActual);
        
        
        for(Inquilino unInquilino : unaControladora.obtenerInquilinosEdificio(idEdificio)){
            int posUltimoAlquiler = unInquilino.getAlquileres().size()-1;
            int mesAlquiler = Integer.valueOf(formatoMes.format(unInquilino.getAlquileres().get(posUltimoAlquiler).getFecha()));
            int anioAlquiler = Integer.valueOf(formatoAnio.format(unInquilino.getAlquileres().get(posUltimoAlquiler).getFecha()));
            Alquiler ultimoAlquiler = unInquilino.getAlquileres().get(posUltimoAlquiler);
            
            if(mesAlquiler == mesActual && anioAlquiler == anioActual){
                
            }
            
        }
        
        //ESTILOS TABLA EXPENSAS (GENERADOR DE HOJAS DENTRO DEL LIBRO)
        // EXPANDIR el tamaño de la Columna n° 2 y 3 de todas las filas que abarquen la Expensa.
        // Estilo 1 Para "Nombre edificio": Bold-Calibri-14-italic
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
        
        for (Departamento unDepartamento : unEdificio.getDepartamentos()) {
            if (unDepartamento.getUnInquilino() != null){  // Si existe Inquilino se crea una hoja dentro del Libro Excel (Departamento-Expensa)
                Sheet hoja = libro.createSheet("Expensa " + unDepartamento.getUbicacion()); // Se genera una hoja dentro del Libro Excel
                hoja.setColumnWidth(1, 13700); // Columna n° 1 (B), tamaño
                hoja.setColumnWidth(2, 7350); // Columna n° 2 (C), tamaño
                
                // FILA 0
                Row fila = hoja.createRow(0);  // Se genera una Fila dentro de la hoja
                
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
                    }
                    //JOptionPane.showMessageDialog(null, "tipo de dato: "+fila.get);
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
        try {
            JFileChooser Reporte = new JFileChooser();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
            String nombreEdi = unaControladora.obtenerEdificio(idEdificio).getNombre();
            String nombreArchivo = "Expensas "+nombreEdi+" "+formatoFecha.format(fechaActual);
            
            Reporte.setSelectedFile(new File(nombreArchivo));
            if (Reporte.showDialog(null, "Guardar Reporte") == JFileChooser.APPROVE_OPTION) {
                
                File archivoXLSX = Reporte.getSelectedFile();
                String nombre = archivoXLSX.getName();
                if (nombre.indexOf('.') == -1) {
                //De no ser asi le agregamos
                    nombre += ".xlsx";
                    archivoXLSX = new File(archivoXLSX.getParentFile(), nombre);
                    FileOutputStream fileOut = new FileOutputStream(archivoXLSX);
                    // 0 problema. Se van pisando los reportes
                    libro.write(fileOut);
                    libro.close();
                }else{
                    JOptionPane.showMessageDialog(null, "No se ha creado el Reporte. Debido a que no es posible agregar 'punto' (.) en el nombre de archivo");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error: "+ex);
        }
        
    }
}
