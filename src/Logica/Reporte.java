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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.RegionUtil;


public class Reporte{
    private int ultimaFila;
    private final SimpleDateFormat formatoMes = new SimpleDateFormat("MM"),
            formatoAnio2 = new SimpleDateFormat("yy"),
            formatoAnio = new SimpleDateFormat("yyyy"),
            formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
    private final DecimalFormat formatoDecimal = new DecimalFormat("#.00");
    private final ControladoraL unaControladora = new ControladoraL();
    private XSSFFont fuente1, fuente2, fuente3;
    private CellStyle estilo, estilo2, estilo3, estilo4, estilo5, estilo6, estilo7, estilo8, estilo9, estilo10, estilo11, moneda;
    
    public Reporte(){
    }
    
    public boolean generarReporte(long idEdificio, String obsAlquiler, String obsExpensa){
        boolean respuesta = false;
        Date fechaActual = new Date();
        long inicio = System.currentTimeMillis();
        Edificio unEdificio = unaControladora.obtenerEdificio(idEdificio);
        XSSFWorkbook libro = crearLibro();                                      // Devuelve el libro con los Estilos y Fuentes configurados para TABLA ALQUILER
        
        libro = generarAlquileres(libro, unEdificio, obsAlquiler);                           // Parametros: Libro configurado para tabla ALQUILER, se genera la hoja "RESUMEN A COBRAR"
        
        for (Departamento unDepartamento : unEdificio.getDepartamentos()) {     // GENERA EXPENSAS POR DEPARTAMENTO
            if (unDepartamento.getUnInquilino() != null){                       // Si existe Inquilino se crea una hoja dentro del Libro Excel (Departamento-Expensa). De lo contrario.. no existe un Inquilino por ende no se genera Expensa.
                libro = generarExpensas(libro, unDepartamento, unEdificio, obsExpensa/*getObsExpensa()*/);     // Parametros: Libro con alquileres, se generan las hojas "Expensas" por Departamentos de un Edificio.
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
            
            long fin = System.currentTimeMillis();
            double tiempo = (double)((fin-inicio)/1000);
            System.out.println("El tiempo de ejecución del Reporte es de: "+tiempo+"s");
        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error: "+ex);
        }
        
        return respuesta;
    }

    public XSSFWorkbook crearLibro(){
        XSSFWorkbook libro = new XSSFWorkbook(); 
        DataFormat df = libro.createDataFormat();
        
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
        estilo10.setDataFormat(df.getFormat("$#,#0.00"));
        
        estilo11 = libro.createCellStyle();
        estilo11.setWrapText(true);
        estilo11.setAlignment(HorizontalAlignment.LEFT);
        estilo11.setVerticalAlignment(VerticalAlignment.CENTER);
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
        estilo3.setDataFormat(df.getFormat("$#,#0.00"));
        
        estilo4 = libro.createCellStyle();                                      // Estilo 4: Encuadre de fila y Negrita. Para col "CONCEPTO" y col "MONTO"
        estilo4.setFont(fuente2);
        estilo4.setBorderTop(BorderStyle.THIN);
        estilo4.setBorderLeft(BorderStyle.THIN);
        estilo4.setBorderBottom(BorderStyle.THIN);
        estilo4.setBorderRight(BorderStyle.THIN);
        
        /*moneda = libro.createCellStyle();
        moneda.setDataFormat((short)8);*/
        
        /*CellStyle dollarStyle=wb.createCellStyle();
        DataFormat df = wb.createDataFormat();
        dollarStyle.setDataFormat(df.getFormat("$#,#0.00"));*/
        
        return libro;
    }
    
    public XSSFWorkbook generarAlquileres(XSSFWorkbook libro, Edificio unEdificio, String obsAlquiler){
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
        hojaAlquiler = configurarHojaAlquiler(hojaAlquiler, unEdificio, mesActual, anioActual2, mesExpensa, anioExpensa);
        short tamFilaDefault = 400;
        fi = 4;
        
        for(Inquilino unInquilino : unaControladora.obtenerInquilinosEdificio(unEdificio.getId())){
            String fechaPago = "";
            long idDepartamento;
            float montoAlquiler = 0, otrasFacturas = 0, montoExpensa = 0, totalAlquilerExpensa = 0, precioCochera = 0,
                  totalesPorIntereses = 0, interesPorAtraso, saldoMesAnterior = 0, totales = 0, saldo = 0, efectivo = 0, tarjeta = 0, banco = 0;
            Alquiler ultimoAlquiler = unaControladora.obtenerUltiAlquilerInquilino(unInquilino); // Ultimo Alquiler (pagado o no).
            String ubicacionDepto = "";
            Departamento unDepto = unaControladora.obtenerDepartamentoInquilino(unEdificio.getId(), unInquilino.getId());
            if(unDepto != null){
                ubicacionDepto = unDepto.getUbicacion();
            }
            
            if(ultimoAlquiler != null){
                montoAlquiler = ultimoAlquiler.getMonto();
                otrasFacturas = ultimoAlquiler.getOtraFactura();
                int mesAlquiler = Integer.valueOf(formatoMes.format(ultimoAlquiler.getFecha())),
                    anioAlquiler = Integer.valueOf(formatoAnio.format(ultimoAlquiler.getFecha()));
                if(ultimoAlquiler.getDepartamento() > 0){
                    Expensa ultimaExpensa = unaControladora.obtenerExpensa(ultimoAlquiler.getDepartamento(), mesAlquiler, anioAlquiler);
                    if(ultimaExpensa != null){
                        montoExpensa = ultimaExpensa.getMonto();
                    }
                }
                
                if(ultimoAlquiler.getUnPago() != null){ // Si getUnPago != null el inquilino esta al día..
                    
                    if(ultimoAlquiler.getCochera() > 0){
                        precioCochera = unaControladora.obtenerCochera(ultimoAlquiler.getCochera()).getPrecio();
                        totalAlquilerExpensa += precioCochera;
                        totales += precioCochera;
                    }
                    fechaPago = formatoFecha.format(ultimoAlquiler.getUnPago().getFecha());
                    totalesPorIntereses = ultimoAlquiler.getUnPago().getInteresPorAtraso();
                    // Debería de obtener el Saldo mes ant. del último pago...¿?
                    int tam = unaControladora.obtenerPagosInquilino(unInquilino.getId()).size();
                    Pago ultimoPago = unaControladora.obtenerPagosInquilino(unInquilino.getId()).get(tam-1);
                    
                    saldoMesAnterior = ultimoPago.getSaldo();
                    totales = ultimoPago.getMonto();
                    saldo = ultimoPago.getMonto()-(ultimoPago.getEfectivo()+ultimoPago.getTarjeta()+ultimoPago.getBanco());
                    if(ultimoAlquiler.getUnPago().getEfectivo() > 0){
                        efectivo = ultimoAlquiler.getUnPago().getEfectivo();
                    }
                    if(ultimoAlquiler.getUnPago().getTarjeta()> 0){
                        tarjeta = ultimoAlquiler.getUnPago().getTarjeta();
                    }
                    if(ultimoAlquiler.getUnPago().getBanco()> 0){
                        banco = ultimoAlquiler.getUnPago().getBanco();
                    }
                }else{  // Sino adeuda el último o más alquileres..
                    List<Alquiler> alquileresInpago = unaControladora.obtenerAlquileresInpagos(unInquilino.getId());
                    if(alquileresInpago.size() > 0){
                        int i = 0, ultimoIndiceAlquiler = (alquileresInpago.size())-1;
                        saldoMesAnterior = unaControladora.obtenerSaldoUltimoPago(unInquilino.getId()); // Obtengo el saldo del último pago..
                        totales += saldoMesAnterior;
                        for(Alquiler unAlquilerInpago : alquileresInpago){
                            if(unAlquilerInpago.getDepartamento() > 0){
                                idDepartamento = unAlquilerInpago.getDepartamento();
                                mesAlquiler = Integer.valueOf(formatoMes.format(unAlquilerInpago.getFecha()));
                                anioAlquiler = Integer.valueOf(formatoAnio.format(unAlquilerInpago.getFecha()));
                                Expensa unaExpensaInpaga = unaControladora.obtenerExpensa(idDepartamento, mesAlquiler, anioAlquiler);
                                if(unaExpensaInpaga != null){
                                    totalAlquilerExpensa += unaExpensaInpaga.getMonto();
                                    totales += unaExpensaInpaga.getMonto();
                                }
                            }
                            if(unAlquilerInpago.getCochera() > 0){
                                precioCochera = unaControladora.obtenerCochera(ultimoAlquiler.getCochera()).getPrecio();
                            }
                            totalAlquilerExpensa += unAlquilerInpago.getTotal();
                            totales += unAlquilerInpago.getTotal();
                            interesPorAtraso = unaControladora.interesesPorAtraso(fechaActual, unAlquilerInpago.getFecha(), totalAlquilerExpensa);
                            totalAlquilerExpensa += interesPorAtraso;
                            totalesPorIntereses += interesPorAtraso;
                            if(i != ultimoIndiceAlquiler){
                                saldoMesAnterior += (totalAlquilerExpensa);
                            }
                            totalAlquilerExpensa = 0;
                            totales += interesPorAtraso;
                            saldo = totales;
                            i++;
                        }
                    }
                }
            }else{
                saldoMesAnterior = unaControladora.obtenerSaldoUltimoPago(unInquilino.getId());
            }
            
            //System.out.println("Dpto: "+ubicacionDepto+" - Inqui: "+unInquilino.getApellido()+", "+unInquilino.getNombre()+" - Alquiler: $"+montoAlquiler+" - Otras F.: $"+otrasFacturas+" - Expensa: $"+formatoDecimal.format(montoExpensa)+" - Cochera: $"+precioCochera+" - IntxAtr.: $"+totalesPorIntereses+" - Saldo M.A: $"+saldoMesAnterior+" - Total: $"+totales+" - Saldo: $"+saldo);                     
            Row fila = hojaAlquiler.createRow(fi++);
            fila.setHeight(tamFilaDefault);
            fila.createCell(1).setCellValue(ubicacionDepto);
            fila.getCell(1).setCellStyle(estilo5);
            
            fila.createCell(2).setCellValue(unInquilino.getApellido()+", "+unInquilino.getNombre());
            fila.getCell(2).setCellStyle(estilo5);
            
            fila.createCell(3).setCellValue(montoAlquiler);
            fila.getCell(3).setCellStyle(estilo10);
            fila.getCell(3).getNumericCellValue();
            
            fila.createCell(4).setCellValue(otrasFacturas);
            fila.getCell(4).setCellStyle(estilo10);
            fila.getCell(4).getNumericCellValue();
            
            fila.createCell(5).setCellValue(montoExpensa);
            fila.getCell(5).setCellStyle(estilo10);
            fila.getCell(5).getNumericCellValue();
            
            fila.createCell(6).setCellValue(precioCochera);
            fila.getCell(6).setCellStyle(estilo10);
            fila.getCell(6).getNumericCellValue();
            
            fila.createCell(7).setCellValue(totalesPorIntereses);
            fila.getCell(7).setCellStyle(estilo10);
            fila.getCell(7).getNumericCellValue();
            
            fila.createCell(8).setCellValue(saldoMesAnterior);
            fila.getCell(8).setCellStyle(estilo10);
            fila.getCell(8).getNumericCellValue();
            
            fila.createCell(9).setCellValue(totales);
            fila.getCell(9).setCellStyle(estilo10);
            fila.getCell(9).getNumericCellValue();
            
            fila.createCell(10).setCellValue(efectivo);
            fila.getCell(10).setCellStyle(estilo10);
            fila.getCell(10).getNumericCellValue();
            
            fila.createCell(11).setCellValue(tarjeta);
            fila.getCell(11).setCellStyle(estilo10);
            fila.getCell(11).getNumericCellValue();
            
            fila.createCell(12).setCellValue(banco);
            fila.getCell(12).setCellStyle(estilo10);
            fila.getCell(12).getNumericCellValue();
            
            fila.createCell(13).setCellValue(saldo);          
            fila.getCell(13).setCellStyle(estilo10);
            fila.getCell(13).getNumericCellValue();
            
            fila.createCell(14).setCellValue(fechaPago);
            fila.getCell(14).setCellStyle(estilo10);
        }
        
        if(!obsAlquiler.isEmpty()){
            Row fila = hojaAlquiler.createRow(fi+1);
            fila.createCell(0).setCellValue("");
            fila.getCell(0).setCellValue("");

            CellRangeAddress rango3 = new CellRangeAddress(fila.getRowNum(), fila.getRowNum()+3, 1, 7);

            hojaAlquiler.addMergedRegion(rango3);
            fila.createCell(1).setCellValue("");
            fila.getCell(1).setCellValue(obsAlquiler);
            fila.getCell(1).setCellStyle(estilo11);
            RegionUtil.setBorderTop(BorderStyle.THIN, rango3, hojaAlquiler);
            RegionUtil.setBorderLeft(BorderStyle.THIN, rango3, hojaAlquiler);
            RegionUtil.setBorderRight(BorderStyle.THIN, rango3, hojaAlquiler);
            RegionUtil.setBorderBottom(BorderStyle.THIN, rango3, hojaAlquiler);
        }
        
        return libro;
    }
    
    public Sheet configurarHojaAlquiler(Sheet hojaAlquiler, Edificio unEdificio, int mesActual, int anioActual2, int mesExpensa, int anioExpensa){
        CellRangeAddress rango = new CellRangeAddress(1, 1, 1, 14), rango2 = new CellRangeAddress(2, 2, 1, 14);
        hojaAlquiler.addMergedRegion(rango);                                    // Fusiona las columnas de los 2 últimos parametros
        hojaAlquiler.addMergedRegion(rango2);
        
        short tamFila3 = 600;                                                   // Se setea el tamaño de filas
        
                                                                                // Se setea el tamaño de las columnas
        hojaAlquiler.setColumnWidth(1, 1500);                                   // Columna n° 1 (B), tamaño [DPTO]
        hojaAlquiler.setColumnWidth(2, 7800);                                   // Columna n° 2 (B), tamaño [INQUILINO]
        hojaAlquiler.setColumnWidth(3, 2700);                                   // Columna n° 3 (C), tamaño [ALQUILER]
        hojaAlquiler.setColumnWidth(4, 2700);                                   // Columna n° 4 (C), tamaño [OTRAS F.]
        hojaAlquiler.setColumnWidth(5, 3900);                                   // Columna n° 5 (C), tamaño [EXPENSAS]
        hojaAlquiler.setColumnWidth(6, 2700);                                   // Columna n° 6 (C), tamaño [COCHERAS]
        hojaAlquiler.setColumnWidth(7, 4000);                                   // Columna n° 7 (C), tamaño [INTERESESxATRASOS]
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
        
        RegionUtil.setBorderTop(BorderStyle.THIN, rango, hojaAlquiler);
        RegionUtil.setBorderLeft(BorderStyle.THIN, rango, hojaAlquiler);
        RegionUtil.setBorderRight(BorderStyle.THIN, rango, hojaAlquiler);
        
        fila = hojaAlquiler.createRow(2);                                       // FILA 2
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("PERIODO: "+mesActual+"/"+anioActual2);
        
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
        
        encabezado.createCell(7).setCellValue("intereses por atr.");              // COLUMNA 7, FILA 3
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
        
        return hojaAlquiler;
    }
    
    public XSSFWorkbook generarExpensas(XSSFWorkbook libro, Departamento unDepartamento, Edificio unEdificio, String obsExpensa){
        Sheet hojaExpensa = libro.createSheet("Expensa " + unDepartamento.getUbicacion()); // Se genera una hoja dentro del Libro Excel
        hojaExpensa = configurarHojaExpensa(hojaExpensa, unDepartamento, unEdificio);
        
        // FILA 5
        Row fila = hojaExpensa.createRow(5);
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("PERIODO");
        
        if(unDepartamento.getExpensas().size() > 0){
            //int ultimaPos = unDepartamento.getExpensas().size();
            Expensa ultimaExpensa = unaControladora.obtenerUltimaExpensa(unDepartamento.getId());
            int f = 7, indice, cantidadServicioExpensa = ultimaExpensa.getServiciosExpensa().size();

            fila.createCell(2).setCellValue("EXPENSAS "+ultimaExpensa.getMes()+"/"+ultimaExpensa.getAnio()); // Traer el último período y año de la Expensa
            fila.getCell(2).setCellStyle(estilo2);

            // FILA 6
            fila = hojaExpensa.createRow(6);
            fila.createCell(0).setCellValue("");
            fila.createCell(1).setCellValue("CONCEPTOS");
            fila.getCell(1).setCellStyle(estilo3);
            fila.createCell(2).setCellValue("MONTO");
            fila.getCell(2).setCellStyle(estilo3);

            // Se autogeneran las filas de servicios que posee esta expensa.
            for(indice = 0; indice < cantidadServicioExpensa; indice++){
                fila = hojaExpensa.createRow(f++);
                fila.createCell(0).setCellValue("");
                fila.createCell(1).setCellValue(ultimaExpensa.getServiciosExpensa().get(indice).getNombre());
                fila.getCell(1).setCellStyle(estilo3);
                String montoSinComa = unaControladora.reemplazarString(formatoDecimal.format(ultimaExpensa.getServiciosExpensa().get(indice).getMonto()));
                Double montoFinal = Double.valueOf(montoSinComa);
                fila.createCell(2).setCellValue(montoFinal);
                fila.getCell(2).setCellStyle(estilo3);
                fila.getCell(2).getNumericCellValue();// Hacer algo al respecto con esto.. y con el Simbolo $ en montoFinal. Linea 331
            }
            
            ultimaFila = f;
            
            fila = hojaExpensa.createRow(f++);
            fila.createCell(0).setCellValue("");
            fila.createCell(1).setCellValue("TOTAL");
            fila.getCell(1).setCellStyle(estilo4);
            fila.createCell(2).setCellFormula("SUM(C8:C"+ultimaFila+")");
            fila.getCell(2).setCellStyle(estilo4);
            if(!obsExpensa.isEmpty()){
                fila = hojaExpensa.createRow(f+1);
                fila.createCell(0).setCellValue("");
                fila.getCell(0).setCellValue("");

                CellRangeAddress rango = new CellRangeAddress(fila.getRowNum(), fila.getRowNum()+3, 1, 2);
                
                hojaExpensa.addMergedRegion(rango);
                fila.createCell(1).setCellValue("");
                fila.getCell(1).setCellValue(obsExpensa);
                fila.getCell(1).setCellStyle(estilo11);
                RegionUtil.setBorderTop(BorderStyle.THIN, rango, hojaExpensa);
                RegionUtil.setBorderLeft(BorderStyle.THIN, rango, hojaExpensa);
                RegionUtil.setBorderRight(BorderStyle.THIN, rango, hojaExpensa);
                RegionUtil.setBorderBottom(BorderStyle.THIN, rango, hojaExpensa);
            }
        }
        
        return libro;
    }
    
    public Sheet configurarHojaExpensa(Sheet hojaExpensa, Departamento unDepartamento, Edificio unEdificio){
        hojaExpensa.setColumnWidth(1, 13700);                                          // Columna n° 1 (B), tamaño
        hojaExpensa.setColumnWidth(2, 7350);                                           // Columna n° 2 (C), tamaño
        // FILA 1
        Row fila = hojaExpensa.createRow(1);
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("EDIFICIO "+unEdificio.getNombre());
        fila.getCell(1).setCellStyle(estilo);
        fila.createCell(2).setCellValue(unEdificio.getDireccion());
        // FILA 2
        fila = hojaExpensa.createRow(2);
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("RECIBO DE EXPENSA");
        // FILA 3
        fila = hojaExpensa.createRow(3);
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("NOMBRE LOCATARIO");
        fila.createCell(2).setCellValue(unDepartamento.getUnInquilino().toString());
        fila.getCell(2).setCellStyle(estilo2);
        // FILA 4
        fila = hojaExpensa.createRow(4);
        fila.createCell(0).setCellValue("");
        fila.createCell(1).setCellValue("DEPARTAMENTO");
        fila.createCell(2).setCellValue(unDepartamento.getUbicacion());
        fila.getCell(2).setCellStyle(estilo2);
        
        return hojaExpensa;
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
