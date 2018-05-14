package Logica;

import java.util.Date;
import java.util.List;
import java.util.LinkedList;
import Persistencia.ControladoraP;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Collections;

public class ControladoraL {
    
    private int cantAlquileresGenerados = 0; // Cantidad de alquileres que se generaron automaticamento
    private final ControladoraP unaControladora = new ControladoraP();
    
/*------------------------------------------------------------------------------
                           NOTIFICACIONES
------------------------------------------------------------------------------*/
    public List<String> notificaciones(){
        List<String> notificaciones  = new LinkedList();
        Date fechaActual = new Date();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        int mes = Integer.valueOf(monthFormat.format(fechaActual)),
            anio = Integer.valueOf(yearFormat.format(fechaActual));
        
        List<Servicio> servicios = obtenerServicios();
        
        for(Servicio unServicio : servicios){
            if(unServicio.getMes() < mes && unServicio.getAnio() == anio){
                notificaciones.add("El Servicio "+unServicio.getNombre()+" debe ser actualizado - Edificio "+obtenerEdificioServicio(unServicio.getId()).getNombre());
            }
        }
        
        if(cantAlquileresGenerados  > 0){
            notificaciones.add("Se generaron "+cantAlquileresGenerados+" Alquileres.");
        }else{
            notificaciones.add("No se han generado nuevos alquileres.");
        }
        
        return notificaciones;
    }
/*------------------------------------------------------------------------------
                           INTERES POR ATRASO
------------------------------------------------------------------------------*/
    public float interesPorAtraso(Date fechaPago, float montoTotalPago, int mesAlquiler){
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        
        float coeficienteInteres = obtenerCoeficiente().getValor(),
              interesTotal = 0,
              interesMes = (30 * (coeficienteInteres/30) * montoTotalPago);
              
        int dia = Integer.parseInt(dayFormat.format(fechaPago)),
            difDias = dia - 10,
            mes = Integer.parseInt(monthFormat.format(fechaPago)),
            difMes = mes - mesAlquiler;
        
        if(difMes >= 0){
            if(difMes == 0){
                if(difDias > 0){
                    interesTotal = (difDias * (coeficienteInteres/30) * montoTotalPago);
                    if(interesTotal < 10){
                        interesTotal = 0;
                    }
                }
            }else if(difMes > 0){
                for(int i = 0; i < difMes; i++){
                    interesTotal += interesMes;
                }
            }
        }
        
        return interesTotal;
    }
    
    public float interesesPorAtraso(Date fechaPago, Date fechaAlquiler, float monto){
        float interesMes = 0;
        SimpleDateFormat formatoDia = new SimpleDateFormat("dd"),
                         formatoMes = new SimpleDateFormat("MM"),
                         formatoAnio = new SimpleDateFormat("yyyy"),
                         formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        int diaAlquiler = Integer.valueOf(formatoDia.format(fechaAlquiler)),
            mesAlquiler = Integer.valueOf(formatoMes.format(fechaAlquiler)),   // Se obtiene el día de la fecha Alquiler
            anioAlquiler = Integer.valueOf(formatoAnio.format(fechaAlquiler)),
            diaPago = Integer.valueOf(formatoDia.format(fechaPago)),
            mesPago = Integer.valueOf(formatoMes.format(fechaPago)),
            diferenciaMeses = mesPago - mesAlquiler;
        
        if(diferenciaMeses > 0){    // Si es > 0. Es un pago atrasado
            try{
                String fecha = "11-"+mesAlquiler+"-"+anioAlquiler;
                Date nuevaFechaAlquiler = formatoFecha.parse(fecha);
                long difDias = ((fechaPago.getTime()-nuevaFechaAlquiler.getTime())/86400000);
                System.out.println("Cantidad de dias: "+difDias);
                float coeficienteInteres = obtenerCoeficiente().getValor();
                interesMes = (difDias * (coeficienteInteres/30) * monto);
                System.out.println("Intereses generados: "+interesMes);
            }catch(Exception e){
                System.out.println("Error 1: "+e);
            }
        }else{  // Si es == 0. Entonces esta dentro del mismo mes. Se debe comprobar si esta fuera del rango alquiler de pago (1 al 10). Se calculará el interes.
            if(diaPago > 10){
                try{
                    String fecha = "11-"+mesAlquiler+"-"+anioAlquiler;
                    Date nuevaFechaAlquiler = formatoFecha.parse(fecha);
                    long difDias = ((fechaPago.getTime()-nuevaFechaAlquiler.getTime())/86400000);
                    System.out.println("Cantidad de dias: "+difDias);
                    float coeficienteInteres = obtenerCoeficiente().getValor();
                    interesMes = (difDias * (coeficienteInteres/30) * monto);
                    if(interesMes < 10){
                        interesMes = 0;
                    }
                    System.out.println("Intereses generados: "+interesMes);
                }catch(Exception e){
                    System.out.println("Error 2: "+e);
                }
            }
        }
        
        return interesMes;
    }
/*------------------------------------------------------------------------------
                           CONVERSOR STRING A FLOAT
------------------------------------------------------------------------------*/
    public String reemplazarString(String valor){
        String resultado = valor.replace(',', '.');
        
        return resultado;
    }   
    
/*------------------------------------------------------------------------------
                                ALQUILER
------------------------------------------------------------------------------*/
    public void altaAlquiler(Date fecha, long idCochera, long idDepartamento, float montoAlquiler, float otrasFactura, float total, String descripcion, Pago unPago, long idInquilino) throws Exception{
                
        Alquiler unAlquiler = new Alquiler(fecha, idCochera, idDepartamento, montoAlquiler, otrasFactura, total, descripcion, unPago);
        
        unaControladora.altaAlquiler(unAlquiler);
        
        Inquilino unInquilino = obtenerInquilino(idInquilino);
        unInquilino.getAlquileres().add(unAlquiler);
        
        unaControladora.modificarInquilino(unInquilino);
    }
    
    public void modificarAlquiler(long id, Date fecha, long idCochera, long idDepartamento, float montoAlquiler, float otraFactura, float total, String descripcion, Pago unPago) throws Exception{
        Alquiler unAlquiler = obtenerAlquiler(id);
        
        unAlquiler.setFecha(fecha);
        unAlquiler.setIdCochera(idCochera);
        unAlquiler.setIdDepartamento(idDepartamento);
        unAlquiler.setMonto(montoAlquiler);
        unAlquiler.setOtraFactura(otraFactura);
        unAlquiler.setTotal(total);
        unAlquiler.setDescripcion(descripcion);
        unAlquiler.setUnPago(unPago);
        
        unaControladora.modificarAlquiler(unAlquiler);
        
    }
    
    public void bajaAlquiler(long idAlquiler, long idInquilino) throws Exception{
        Alquiler unAlquiler = obtenerAlquiler(idAlquiler);
        Inquilino unInquilino = obtenerInquilino(idInquilino);
        int i = 0;
        
        while(unAlquiler != null){
            if(unInquilino.getAlquileres().get(i).getId() == unAlquiler.getId()){
                unInquilino.getAlquileres().remove(i);
                unAlquiler = null;
            }
            i++;
        }
        
        unaControladora.modificarInquilino(unInquilino);
        unaControladora.bajaAlquiler(idAlquiler);
    }
    
    public Alquiler obtenerAlquiler(long id){
        return unaControladora.obtenerAlquiler(id);
    }
    
    public List<Alquiler> obtenerAlquileres(){
        return unaControladora.obtenerAlquileres();
    }
    
    public List<Alquiler> obtenerAlquileresInpagos(long idInquilino){  // Para interfaz PAGO: Se ocupa esta función para cargar el combo ALQUILER
        List<Alquiler> alquileresInpagos = new LinkedList();
        
        for(Alquiler unAlquiler : obtenerInquilino(idInquilino).getAlquileres()){
            if(unAlquiler.getUnPago() == null){
                alquileresInpagos.add(unAlquiler);
            }
        }
        
        return alquileresInpagos;
    }
    
    public Alquiler obtenerAlquilerPago(long idPago){
        int i = 0;
        Alquiler unAlquiler = null,
                 unAlqui = null;
        
        while(unAlquiler == null && i < obtenerAlquileres().size()){
            unAlqui = obtenerAlquileres().get(i);
            if(unAlqui.getUnPago() != null){
                if(unAlqui.getUnPago().getId() == idPago){
                    unAlquiler = unAlqui;
                }
            }
            i++;
        }
        
        return unAlquiler;
    }
    
    public List<Alquiler> obtenerAlquileresEdificio(long idEdificio){
        List<Inquilino> inquilinos = obtenerInquilinosEdificio(idEdificio);
        List<Departamento> departamentos = obtenerEdificio(idEdificio).getDepartamentos();
        List<Alquiler> alquileres = new LinkedList();
        /*
        CAMBIE POR OBTENERINQUILINOSEDIFICIO PORQUE ESA FUNCION ME TRAE INQUILINOS TANTO DE DEPARTAMENTOS
        COMO DE COCHERAS. 29-4-18
        */
        for(Inquilino unInquilino : inquilinos){
            if(unInquilino.getAlquileres().size() > 0){
                for(Alquiler unAlquiler : unInquilino.getAlquileres()){
                    alquileres.add(unAlquiler);
                }
            }
        }
        /*
        for(Departamento unDepartamento : departamentos){
            if(unDepartamento.getUnInquilino() != null && unDepartamento.getUnInquilino().getAlquileres() != null){
                for(Alquiler unAlquiler : unDepartamento.getUnInquilino().getAlquileres()){
                    alquileres.add(unAlquiler);
                }
            }
        }*/
        
        return alquileres;
    }
    
    public void generarAlquiler() throws Exception{
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        Date fecha = new Date();
        int mesAlquiler, anioAlquiler,
            mesActual = Integer.valueOf(monthFormat.format(fecha))-1,
            anioActual = Integer.valueOf(yearFormat.format(fecha));
        Alquiler unAlquiler;
        
        List<Inquilino> inquilinos = obtenerInquilinos();
        
        if(mesActual == 0){
            mesActual = 12;
            anioActual = anioActual-1;
        }
        
        for(Inquilino unInquilino : inquilinos){
            if(unInquilino.getAlquileres().size() > 0){
                // ORDENAR ACA LA LISTA ALQUILERES
                List<Alquiler> alquileres = new LinkedList();
                for(Alquiler unAlqui : unInquilino.getAlquileres()){
                    alquileres.add(unAlqui);
                }
                
                Collections.sort(alquileres, (Alquiler a1, Alquiler a2) -> a1.getFecha().compareTo(a2.getFecha()));
                
                unAlquiler = alquileres.get(alquileres.size()-1);
                //unAlquiler = unInquilino.getAlquileres().get(unInquilino.getAlquileres().size()-1); // Se obtiene el último Alquiler del Inquilino
                if(unAlquiler.getGeneracionAuto() < 6){ // Si generacionAuto del último Alquiler < 6
                    mesAlquiler = Integer.valueOf(monthFormat.format(unAlquiler.fecha));
                    anioAlquiler = Integer.valueOf(yearFormat.format(unAlquiler.fecha));
                    
                    if(mesAlquiler == mesActual && anioAlquiler == anioActual){
                        Alquiler nuevoAlquiler = new Alquiler();
                        
                        nuevoAlquiler.setFecha(fecha);
                        nuevoAlquiler.setIdCochera(unAlquiler.getCochera());
                        nuevoAlquiler.setIdDepartamento(unAlquiler.getDepartamento());
                        nuevoAlquiler.setGeneracionAuto(unAlquiler.getGeneracionAuto()+1);
                        nuevoAlquiler.setMonto(unAlquiler.getMonto());
                        nuevoAlquiler.setOtraFactura(unAlquiler.getOtraFactura());
                        nuevoAlquiler.setTotal(unAlquiler.getTotal());
                        nuevoAlquiler.setDescripcion(unAlquiler.getDescripcion());
                        nuevoAlquiler.setUnPago(null);
                        
                        unaControladora.altaAlquiler(nuevoAlquiler);
                        //
                        List<Alquiler> alquileresInquilino = unInquilino.getAlquileres();
                        alquileresInquilino.add(nuevoAlquiler);
                        unInquilino.setAlquileres(alquileresInquilino);
                        unaControladora.modificarInquilino(unInquilino);
                        cantAlquileresGenerados++;
                    }
                }
                
            }
        }
        
    }
    
    
    
/*------------------------------------------------------------------------------
                                COCHERA
------------------------------------------------------------------------------*/
    public void altaCochera(String ubicacion, float precio, String descripcion, Inquilino unInquilino, long idEdificio) throws Exception{
        Cochera unaCochera = new Cochera(ubicacion, precio, descripcion, unInquilino);
        
        unaControladora.altaCochera(unaCochera);
        
        Edificio unEdificio = obtenerEdificio(idEdificio);
        unEdificio.getCocheras().add(unaCochera);
        
        unaControladora.modificarEdificio(unEdificio);
    }
    
    public void modificarCochera(long id, String ubicacion, float precio, String descripcion, Inquilino unInquilino) throws Exception{
        Cochera unaCochera = obtenerCochera(id);
        
        unaCochera.setUbicacion(ubicacion);
        unaCochera.setPrecio(precio);
        unaCochera.setDescripcion(descripcion);
        unaCochera.setUnInquilino(unInquilino);
        
        unaControladora.modificarCochera(unaCochera);
    }
    
    public void bajaCochera(long idCochera, long idEdificio) throws Exception{
        Cochera unaCochera = obtenerCochera(idCochera);
        Edificio unEdificio = obtenerEdificio(idEdificio);
        int i = 0;
        
        while(unaCochera != null){
            if(unEdificio.getCocheras().get(i).getId() == unaCochera.getId()){
                unEdificio.getCocheras().remove(i);
                unaCochera = null;
            }
            i++;
        }
        
        unaControladora.modificarEdificio(unEdificio);
        unaControladora.bajaCochera(idCochera);
    }
    
    public Cochera obtenerCochera(long id){
        return unaControladora.obtenerCochera(id);
    }
    
    public List<Cochera> obtenerCocheras(){
        return unaControladora.obtenerCocheras();
    }    
    
    public Cochera obtenerCocheraInquilino(long idEdificio, long idInquilino){
        Cochera unaCochera = null;
        
        for(Cochera cochera : obtenerEdificio(idEdificio).getCocheras()){
            if(cochera.getUnInquilino() != null)
                if(cochera.getUnInquilino().getId() == idInquilino)
                    unaCochera = cochera;            
        }
        
        return unaCochera;
    }
    
    public List<Cochera> obtenerCocherasDisponibles(long idEdificio){
        List<Cochera> cocheras = new LinkedList();
        
        for(Cochera unaCochera : obtenerEdificio(idEdificio).getCocheras()){
            if(unaCochera.getUnInquilino() == null){
                cocheras.add(unaCochera);
            }
        }
        
        return cocheras;
    }
    
    public boolean existeCochera(String ubicacion, long idEdificio){
        boolean existeCochera = false;
        List<Cochera> cocheras = obtenerEdificio(idEdificio).getCocheras();
        int i = 0, tam = cocheras.size();
        
        while(i < tam){
            if(cocheras.get(i).getUbicacion().equals(ubicacion)){
                existeCochera = true;
                i = tam;
            }else{
                i++;
            }
        }
        
        return existeCochera;
    }
    
    public int obtenerCocherasOcupadas(long idEdificio){
        int cant = 0;
        
        for(Logica.Cochera unaCochera : unaControladora.obtenerEdificio(idEdificio).getCocheras()){
            if(unaCochera.getUnInquilino() != null){
                cant++;
            }
        }
        
        return cant;
    }
/*------------------------------------------------------------------------------
                                COEFICIENTE
------------------------------------------------------------------------------*/
    public void altaCoeficiente(String nombre, float valor) throws Exception{
        Coeficiente unCoeficiente = new Coeficiente(nombre, valor);
        
        unaControladora.altaCoeficiente(unCoeficiente);
    }
    
    public void modificarCoeficiente(long id, String nombre, float valor) throws Exception{
        Coeficiente unCoeficiente = obtenerCoeficiente(id);
        
        unCoeficiente.setNombre(nombre);
        unCoeficiente.setValor(valor);
        
        unaControladora.modificarCoeficiente(unCoeficiente);
    }
    
    public void bajaCoeficiente(long idCoeficiente) throws Exception{
        unaControladora.bajaCoeficiente(idCoeficiente);
    }
    
    public Coeficiente obtenerCoeficiente(long id){
        return unaControladora.obtenerCoeficiente(id);
    }
    
    public Coeficiente obtenerCoeficiente(){
        List <Coeficiente> coeficientes = obtenerCoeficientes();
        Coeficiente unCoeficiente = null;
        
        for(int i = 0; i < coeficientes.size(); i++){
            unCoeficiente = coeficientes.get(i);
        }
        
        return unCoeficiente;
    }
    
    public List<Coeficiente> obtenerCoeficientes(){
         return unaControladora.obtenerCoeficientes();
    }
    
/*------------------------------------------------------------------------------
                            COEFICIENTE DORMITORIO
------------------------------------------------------------------------------*/
    public void altaCoeficienteDorm(String nombre, float valor, int cantDorm, String descripcion, long idEdificio) throws Exception{
        CoeficienteDorm unCoeficienteDorm = new CoeficienteDorm(nombre, valor, cantDorm, descripcion);
        
        unaControladora.altaCoeficienteDorm(unCoeficienteDorm);
        
        Edificio unEdificio = obtenerEdificio(idEdificio);
        unEdificio.getCoeficientesDorm().add(unCoeficienteDorm);
        
        unaControladora.modificarEdificio(unEdificio);
    }
    
    public void modificarCoeficienteDorm(long idCoeficienteDorm, int cantDorm, String nombre, float valor, String descripcion) throws Exception{
        CoeficienteDorm unCoeficienteDorm = obtenerCoeficienteDorm(idCoeficienteDorm);
        
        unCoeficienteDorm.setCantDorm(cantDorm);
        unCoeficienteDorm.setNombre(nombre);
        unCoeficienteDorm.setValor(valor);
        unCoeficienteDorm.setDescripcion(descripcion);
        
        unaControladora.modificarCoeficienteDorm(unCoeficienteDorm);
    }
    
    public void bajaCoeficienteDorm(long idCoeficienteDorm, long idEdificio) throws Exception{
        CoeficienteDorm unCoeficienteDorm = obtenerCoeficienteDorm(idCoeficienteDorm);
        Edificio unEdificio = obtenerEdificio(idEdificio);
        int i = 0;
        
        while(unCoeficienteDorm != null){
            if(unEdificio.getCoeficientesDorm().get(i).getId() == unCoeficienteDorm.getId()){
                unEdificio.getCoeficientesDorm().remove(i);
                unCoeficienteDorm = null;
            }
            i++;
        }
        
        unaControladora.modificarEdificio(unEdificio);
        unaControladora.bajaCoeficienteDorm(idCoeficienteDorm);
    }
    
    public CoeficienteDorm obtenerCoeficienteDorm(long idCoeficienteDorm){
        return unaControladora.obtenerCoeficienteDorm(idCoeficienteDorm);
    }
    
    public List<CoeficienteDorm> obtenerCoeficientesDorm(){
        return unaControladora.obtenerCoeficientesDorm();
    }
    
    public CoeficienteDorm obtenerCoeficienteDorm(long idEdificio, Departamento unDepartamento){
        CoeficienteDorm unCoeficiente = new CoeficienteDorm();
        List<CoeficienteDorm> coeficientesDorm = obtenerEdificio(idEdificio).getCoeficientesDorm();
        
        for(CoeficienteDorm CoeficienteDorm : coeficientesDorm){
            if(CoeficienteDorm.getCantDorm() == unDepartamento.getCantDormitorios()){
                unCoeficiente = CoeficienteDorm;
            }
        }
        
        return unCoeficiente;
    }    
    
    public boolean existeCoeficienteDorm(String nombreCoeficiente, long idEdificio){
        boolean existe = false;
        List<CoeficienteDorm> coeficientesDorm = obtenerEdificio(idEdificio).getCoeficientesDorm();
        int i = 0, tam = coeficientesDorm.size();
        
        while(i < tam){
            if(coeficientesDorm.get(i).getNombre().equals(nombreCoeficiente)){
                existe = true;
                i = tam;
            }else{
                i++;
            }
        }
        
        return existe;
    }
/*------------------------------------------------------------------------------
                                DEPARTAMENTO
------------------------------------------------------------------------------*/
    public void altaDepartamento(String ubicacion, int cantDormitorios, String descripcion, List<Expensa> expensas, Inquilino unInquilino, long idEdificio) throws Exception{
        Departamento unDepartamento = new Departamento(ubicacion, cantDormitorios, descripcion, expensas, unInquilino);
        
        unaControladora.altaDepartamento(unDepartamento);
        
        Edificio unEdificio = obtenerEdificio(idEdificio);
        unEdificio.getDepartamentos().add(unDepartamento);
        
        unaControladora.modificarEdificio(unEdificio);
    }
    
    public void modificarDepartamento(long id, String ubicacion, int cantDormitorios, String descripcion, List<Expensa> expensas, Inquilino unInquilino) throws Exception{
        Departamento unDepartamento = obtenerDepartamento(id);
        
        unDepartamento.setUbicacion(ubicacion);
        unDepartamento.setCantDormitorios(cantDormitorios);
        unDepartamento.setDescripcion(descripcion);
        unDepartamento.setExpensas(expensas);
        unDepartamento.setUnInquilino(unInquilino);
        
        unaControladora.modificarDepartamento(unDepartamento);
    }
    
    public void bajaDepartamento(long idDepartamento, long idEdificio) throws Exception{
        Departamento unDepartamento = obtenerDepartamento(idDepartamento);
        Edificio unEdificio = obtenerEdificio(idEdificio);
        int i = 0;
        
        while(unDepartamento != null){
            if(unEdificio.getDepartamentos().get(i).getId() == unDepartamento.getId()){
                unEdificio.getDepartamentos().remove(i);
                unDepartamento = null;
            }
            i++;
        }
        
        unaControladora.modificarEdificio(unEdificio);
        unaControladora.bajaDepartamento(idDepartamento);
    }
    
    public Departamento obtenerDepartamento(long id){
        return unaControladora.obtenerDepartamento(id);
    }
    
    public List<Departamento> obtenerDepartamentos(){
        return unaControladora.obtenerDepartamentos();
    }
    
    public Departamento obtenerDepartamento(long idEdificio, String ubicacionDepto){
        List<Departamento> departamentos = obtenerEdificio(idEdificio).getDepartamentos();
        Departamento unDepartamento = null;
        
        for(Departamento unDepa : departamentos){
            if(unDepa.getUbicacion().equals(ubicacionDepto)){
                unDepartamento = unDepa;
            }
        }
        
        return unDepartamento;
    }
    
    // Debería devolver solo 1 DEPARTAMENTO por INQUILINO
    public Departamento obtenerDepartamentoInquilino(long idInquilino){
        Departamento unDepartamento = null;
        List<Departamento> departamentos = obtenerDepartamentos();
        int i = 0, tam = departamentos.size();
        
        while(i < tam){
            if(departamentos.get(i).getUnInquilino() != null){
                if(departamentos.get(i).getUnInquilino().getId() == idInquilino){
                    unDepartamento = departamentos.get(i);
                    i = tam;
                }else{
                    i++;
                }
            }else{
                i++;
            }
        }
        
        return unDepartamento;
    }
    
    public int departamentosOcupados(long idEdificio){
        int cantDeptosOcupados = 0;
        List<Departamento> departamentos = obtenerEdificio(idEdificio).getDepartamentos();
        
        for(Departamento unDepartamento : departamentos){
            if(unDepartamento.getUnInquilino() != null){
                cantDeptosOcupados++;
            }
        }
        
        return cantDeptosOcupados;
    }
    
    public List <Departamento> departamentosDisponibles(long idEdificio){
        List <Departamento> depDisponibles = new LinkedList();
        
        for(Departamento unDepartamento : obtenerEdificio(idEdificio).getDepartamentos()){
            if(unDepartamento.getUnInquilino() == null){
                depDisponibles.add(unDepartamento);
            }
        }
        
        return depDisponibles;
    }
    
    public List <Departamento> departamentosSinExpensas(int mes, long idEdificio){
        
        Date fechaActual = new Date();
        SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
        int anioActual = Integer.valueOf(formatoAnio.format(fechaActual)), tamExpensas;
        List <Departamento> departamentos = obtenerEdificio(idEdificio).getDepartamentos();
        List<Departamento> departamentosSinExpensa = new LinkedList();
        boolean tieneEsteMes = false;
        
        for(Departamento unDepartamento : departamentos){
            if(unDepartamento.getExpensas().size() > 0){
                int i = 0;
                tamExpensas = unDepartamento.getExpensas().size();
                while(i < tamExpensas){
                    if(unDepartamento.getExpensas().get(i).getMes() == mes && unDepartamento.getExpensas().get(i).getAnio() == anioActual){
                        tieneEsteMes = true;
                        i = tamExpensas;
                    }else{
                        i++;
                    }
                }
                
                if(!tieneEsteMes){
                    departamentosSinExpensa.add(unDepartamento);
                }
                tieneEsteMes = false;
            }else{
                departamentosSinExpensa.add(unDepartamento); 
            }
        }
        return departamentosSinExpensa;
    }
    
    public Departamento obtenerDepartamentoPorExpensa(long idEdificio, long idExpensa){
        Departamento DepartamentoExpensa = null;
        
        for(Departamento unDepartamento : obtenerEdificio(idEdificio).getDepartamentos()){
            for(Expensa unaExpensa : unDepartamento.getExpensas()){
                if(unaExpensa.getId() == idExpensa){
                    DepartamentoExpensa = unDepartamento;
                }
            }
        }
        
        return DepartamentoExpensa;
    }
    
    public Departamento obtenerDepartamentoPorAlquiler(long idAlquiler, long idEdificio){
        Departamento unDepartamentoAlquiler = null;
        Departamento unDepartamento;
        int i = 0, j = 0;
        Alquiler unAlquiler;        
        
        while(unDepartamentoAlquiler == null){
            unDepartamento = obtenerEdificio(idEdificio).getDepartamentos().get(i);
            while(unDepartamentoAlquiler == null){
                unAlquiler = unDepartamento.getUnInquilino().getAlquileres().get(j);
                if(unAlquiler.getId() == idAlquiler){
                    unDepartamentoAlquiler = unDepartamento;
                }
                j++;
            }
            i++;
        }
        
        return unDepartamentoAlquiler;
    }
    
    public Departamento obtenerDepartamentoPorAlquiler(long idAlquiler, long idEdificio, long idInquilino){
        Departamento unDepto = null;
        int i = 0, j = 0;
        
        while(i < obtenerEdificio(idEdificio).getDepartamentos().size()){
            if(obtenerEdificio(idEdificio).getDepartamentos().get(i).getUnInquilino().getId() == idInquilino){
                List<Alquiler> alquileresInquilino = obtenerEdificio(idEdificio).getDepartamentos().get(i).getUnInquilino().getAlquileres();
                while(j < alquileresInquilino.size()){
                    if(alquileresInquilino.get(j).getId() == idAlquiler){
                        unDepto = obtenerEdificio(idEdificio).getDepartamentos().get(i);
                        i = obtenerEdificio(idEdificio).getDepartamentos().size();
                        j = alquileresInquilino.size();
                    }
                    j++;
                }
            }
            
            i++;
        }
        
        return unDepto;
    }
    
    public boolean existeDepartamento(String ubicacion, long idEdificio){
        boolean respuesta = false;
        
        if(obtenerDepartamento(idEdificio, ubicacion) != null){
            respuesta = true;
        }
        
        return respuesta;
    }
    
/*------------------------------------------------------------------------------
                                EDIFICIO
------------------------------------------------------------------------------*/
    public void altaEdificio(String nombre, String direccion, String descripcion, List<Departamento> departamentos, List<Cochera> cocheras, List<Servicio> servicios, List<CoeficienteDorm> coeficientesDorm) throws Exception{
        Edificio unEdificio = new Edificio(nombre, direccion, descripcion, departamentos, cocheras, servicios, coeficientesDorm);
           
        unaControladora.altaEdificio(unEdificio);
    }
    
    public void modificarEdificio(long id, String nombre, String direccion, String descripcion, List<Departamento> departamentos, List<Cochera> cocheras, List<Servicio> servicios, List<CoeficienteDorm> coeficientesDorm) throws Exception{
        Edificio unEdificio = obtenerEdificio(id);
        
        unEdificio.setNombre(nombre);
        unEdificio.setDireccion(direccion);
        unEdificio.setDescripcion(descripcion);
        unEdificio.setDepartamentos(departamentos);
        unEdificio.setCocheras(cocheras);
        unEdificio.setServicios(servicios);
        unEdificio.setCoeficientesDorm(coeficientesDorm);
        
        unaControladora.modificarEdificio(unEdificio);
    }
    
    public void bajaEdificio(long idEdificio) throws Exception{
        unaControladora.bajaEdificio(idEdificio);
    }
    
    public Edificio obtenerEdificio(long id){
        return unaControladora.obtenerEdificio(id);
    }
    
    public List<Edificio> obtenerEdificios(){
        return unaControladora.obtenerEdificios();
    }
    
    public Edificio buscarEdificioPorNombre(String nombreEdificio){
        Edificio unEdificio = new Edificio();
        List<Edificio> edificios = unaControladora.obtenerEdificios();
        
        for(Edificio unEdi : edificios){
            if(unEdi.getNombre().equals(nombreEdificio)){
                unEdificio = unEdi;
            }
        }
        
        return unEdificio;
    }
    
    // Función utilizada en la Ventana "Departamento"
    public Logica.Edificio obtenerEdificioSegunDepartamento(long idDepartamento){
        List<Edificio> edificios = unaControladora.obtenerEdificios();
        Edificio unEdificio = new Edificio();
        
        for(Edificio unEdi : edificios){
            for(Departamento unDepartamento : unEdi.getDepartamentos()){
                if(unDepartamento.getId() == idDepartamento){
                    unEdificio = unEdi;
                }
            }
        }
        
        return unEdificio;
    }
    
    public int obtenerCantidadPersonas(long idEdificio){    // Este método lo invoca CalcularExpensa (ARRIBA)
        int cantPersonas = 0;
        List<Departamento> departamentos = obtenerEdificio(idEdificio).getDepartamentos();
        
        for(Departamento unDepartamento : departamentos){
            if(unDepartamento.getUnInquilino() != null){
                cantPersonas += unDepartamento.getUnInquilino().getCantidadPersonas();
            }
        }
        
        return cantPersonas;        
    }
    
    public Edificio obtenerEdificioServicio(long idServicio){
        Edificio unEdificio = null;
        
        for(Edificio unEdif : obtenerEdificios()){
            for(Servicio unServicio : unEdif.getServicios()){
                if(unServicio.getId() == idServicio){
                    unEdificio = unEdif;
                    break;
                }
            }
        }
        
        return unEdificio;
    }
    
    public boolean existeEdificio(String nombre){
        boolean existeEdi = false;
        List<Edificio> edificios = obtenerEdificios();
        int i = 0, tam = edificios.size();
        
        while(i < tam){
            if(edificios.get(i).getNombre().equals(nombre)){
                existeEdi = true;
                i = tam;    // Encontre el Edificio, igualo i con tam para dejar de recorrer.
            }else{
                i++;
            }
        }
        
        return existeEdi;
    }
    
/*------------------------------------------------------------------------------
                                EXPENSA
------------------------------------------------------------------------------*/
    public void altaExpensa(float monto, int periodo, int anio, String descripcion, List<ServicioExpensa> serviciosExpensa, long idDepartamento) throws Exception{
        Expensa unaExpensa = new Expensa(monto, periodo, anio, descripcion, serviciosExpensa);
        
        unaControladora.altaExpensa(unaExpensa);
        
        Departamento unDepartamento = obtenerDepartamento(idDepartamento);
        unDepartamento.getExpensas().add(unaExpensa);
        
        unaControladora.modificarDepartamento(unDepartamento);
    }
    
    public void modificarExpensa(long idExpensa, float monto, int mes, int anio, String descripcion, List<ServicioExpensa> serviciosExpensa) throws Exception{
        Expensa unaExpensa = obtenerExpensa(idExpensa);
        
        unaExpensa.setMonto(monto);
        unaExpensa.setMes(mes);
        unaExpensa.setAnio(anio);
        unaExpensa.setDescripcion(descripcion);
        unaExpensa.setServiciosExpensa(serviciosExpensa);
        
        unaControladora.modificarExpensa(unaExpensa);
    }
    
    public void bajaExpensa(long idExpensa, long idDepartamento) throws Exception{
        Expensa unaExpensa = obtenerExpensa(idExpensa);
        Departamento unDepartamento = obtenerDepartamento(idDepartamento);
        int i = 0;
        
        while(unaExpensa != null){
            if(unDepartamento.getExpensas().get(i).getId() == unaExpensa.getId()){
                unDepartamento.getExpensas().remove(i);
                unaExpensa = null;
            }
            i++;
        }
        
        unaControladora.modificarDepartamento(unDepartamento);
        unaControladora.bajaExpensa(idExpensa);
    }
    
    public Expensa obtenerExpensa(long idExpensa){
        return unaControladora.obtenerExpensa(idExpensa);
    }
    
    public List<Expensa> obtenerExpensas(){
        return unaControladora.obtenerExpensas();
    }
    
    public float calcularMonto(List<ServicioExpensa> serviciosExpensa){
        float monto = 0;
        
        for(int i = 0; i < serviciosExpensa.size(); i++){
            monto += serviciosExpensa.get(i).getMonto();
        }
        
        return monto;
    }
    
    public List <ServicioExpensa> calcularExpensa(long idEdificio, Departamento unDepartamento, int mes, int anio, boolean alta) throws Exception{
        List <ServicioExpensa> expensas = new LinkedList();
        List <Servicio> serviciosEdif = obtenerEdificio(idEdificio).getServicios();
        float importeServicio,
               coeficiente = obtenerCoeficienteDorm(idEdificio,unDepartamento).getValor(),
               montoExpensa;
        float porcentajeDeptosOcupados;
        int cantDeptos = obtenerEdificio(idEdificio).getDepartamentos().size(), 
            cantDeptosOcupados = departamentosOcupados(idEdificio);
        
        porcentajeDeptosOcupados = (float)cantDeptosOcupados/cantDeptos;
        
        for(int i = 0;i<serviciosEdif.size();i++){
            if(serviciosEdif.get(i).getMes() == mes && serviciosEdif.get(i).getAnio() == anio){
                if("AGUA Y CLOACA".equals(serviciosEdif.get(i).getNombre())){
                    montoExpensa = serviciosEdif.get(i).getMonto()/obtenerCantidadPersonas(idEdificio);
                    montoExpensa *= unDepartamento.getUnInquilino().getCantidadPersonas();
                }else{
                    importeServicio = serviciosEdif.get(i).getMonto();
                    montoExpensa = (importeServicio/cantDeptos*coeficiente)*porcentajeDeptosOcupados;
                }
                
                ServicioExpensa unServicioExpensa = new ServicioExpensa(serviciosEdif.get(i).getNombre(), serviciosEdif.get(i).getMes(), serviciosEdif.get(i).getAnio(), montoExpensa, serviciosEdif.get(i).getDescripcion());
                if(alta){
                    unaControladora.altaServicioExpensa(unServicioExpensa);
                }
                
                expensas.add(unServicioExpensa);
            }
        }
        
        return expensas;
    }
    
    public List<Expensa> obtenerExpensasFechaActual(long idEdificio){
        List<Expensa> expensas = new LinkedList();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        Date fechaActual = new Date();
        int mesActual = Integer.valueOf(monthFormat.format(fechaActual)),
            anioActual = Integer.valueOf(yearFormat.format(fechaActual));
        
        for(Departamento unDepartamento : obtenerEdificio(idEdificio).getDepartamentos()){
            for(Expensa unaExpensa : unDepartamento.getExpensas()){
                if(unaExpensa.getMes() == mesActual && unaExpensa.getAnio() == anioActual){
                    expensas.add(unaExpensa);
                }
            }
        }
        
        if(expensas.isEmpty()){
            
            if(mesActual == 1){
                mesActual = 12;
                anioActual -= 1;
            }
            
            for(Departamento unDepartamento : obtenerEdificio(idEdificio).getDepartamentos()){
                for(Expensa unaExpensa : unDepartamento.getExpensas()){
                    if(unaExpensa.getMes() == (mesActual-1) && unaExpensa.getAnio() == anioActual){
                        expensas.add(unaExpensa);
                    }
                }
            }
        }
        
        return expensas;
    }
    
    public List<Expensa> obtenerExpensasEdificio(long idEdificio){
        List<Expensa> expensas = new LinkedList();
        
        for(Departamento unDepartamento : obtenerEdificio(idEdificio).getDepartamentos()){
            for(Expensa unaExpensa : unDepartamento.getExpensas()){
                expensas.add(unaExpensa);
            }
        }
        
        return expensas;
    }
    
    public Expensa obtenerExpensa(long idDepartamento, int mesAlquiler, int anioAlquiler){
        Expensa unaExpensa = null;
        mesAlquiler = mesAlquiler - 1;
        
        if(mesAlquiler == 0){
                mesAlquiler = 12;
                anioAlquiler -= 1;
        }
        List<Expensa> expensas = obtenerDepartamento(idDepartamento).getExpensas();
        if(expensas.size() > 0){
            for(Expensa unaExp : expensas){
                if(unaExp.getMes() == mesAlquiler && unaExp.getAnio() == anioAlquiler){
                    unaExpensa = unaExp;
                }
            }
        }
        
        return unaExpensa;
    }
    
    // SE OCUPA PARA COMPROBAR LA EXISTENCIA EN PANEL EXPENSA
    public boolean existeExpensa(long idDepto, int mesExpensa, int anioExpensa){
        boolean respuesta = false;
        Departamento unDepto;
        int i = 0;
        unDepto = obtenerDepartamento(idDepto);
        
        if(unDepto != null){
            if(unDepto.getExpensas().size() > 0){
                int tam = unDepto.getExpensas().size();
                while(i < tam){
                    if(unDepto.getExpensas().get(i).getMes() == mesExpensa && unDepto.getExpensas().get(i).getAnio() == anioExpensa){
                        respuesta = true;
                        i = tam;
                    }else{
                        i++;
                    }
                }
            }
        }
        
        return respuesta;
    }
    
    // SE OCUPA EN EL PANEL PRINCIPAL PARA GENERAR EL REPORTE
    public boolean existenExpensas(long idEdificio){
        boolean respuesta = false;
        int i = 0, tam = obtenerEdificio(idEdificio).getDepartamentos().size()-1;
        List<Departamento> departamentos = obtenerEdificio(idEdificio).getDepartamentos();
        
        while(i < tam){
            if(departamentos.get(i).getUnInquilino() != null){
                if(departamentos.get(i).getExpensas().size() > 0){  // Con que exista un Inquilino en un Departamento que tenga al menos 1 Expensa se da TRUE.
                    respuesta = true;
                    i = tam;
                }
            }
            i++;
        }
        
        return respuesta;
    }
    
/*------------------------------------------------------------------------------
                                GARANTE
------------------------------------------------------------------------------*/
    public void altaGarante(String apellido, String nombre, String dni, String direccion, String telefono, String cuit, String email, String descripcion, long idInquilino) throws Exception{
        Garante unGarante = new Garante(apellido, nombre, dni, direccion, telefono, cuit, email, descripcion);
        
        unaControladora.altaGarante(unGarante);
        
        if(idInquilino != 0){
            Inquilino unInquilino = obtenerInquilino(idInquilino);
            unInquilino.setUnGarante(unGarante);

            unaControladora.modificarInquilino(unInquilino);
        }
        
        //unaControladora.modificarInquilino(unInquilino);
    }
    
    public void modificarGarante(long idGarante, String apellido, String nombre, String dni, String direccion, String telefono, String cuit, String email, String descripcion, long idInquilino) throws Exception{
        Garante unGarante = obtenerGarante(idGarante);
        
        unGarante.setApellido(apellido);
        unGarante.setNombre(nombre);
        unGarante.setDni(dni);
        unGarante.setDireccion(direccion);
        unGarante.setTelefono(telefono);
        unGarante.setCuit(cuit);
        unGarante.setEmail(email);
        unGarante.setDescripcion(descripcion);
        
        unaControladora.modificarGarante(unGarante);
        
        if(idInquilino != 0){
            Inquilino unInqui = unaControladora.obtenerInquilino(idInquilino);
            unInqui.setUnGarante(unGarante);
            unaControladora.modificarInquilino(unInqui);
        }        
    }
    
    public void bajaGarante(long idGarante, long inquilino) throws Exception{
        Inquilino unInquilino = obtenerInquilino(inquilino);
        
        if(unInquilino != null){
            unInquilino.setUnGarante(null);
            unaControladora.modificarInquilino(unInquilino);
        }
        
        unaControladora.bajaGarante(idGarante);
    }
    
    public Garante obtenerGarante(long idGarante){
        return unaControladora.obtenerGarante(idGarante);
    }
    
    public List<Garante> obtenerGarantes(){
        return unaControladora.obtenerGarantes();
    }
    
    public List<Garante> obtenerGarantesInquilinos(List<Inquilino> inquilinos){
        List<Garante> garantes = new LinkedList();
        
        for(int i = 0; i < inquilinos.size(); i++){
            for(int j = 0; j < obtenerGarantes().size(); j++){
                if(obtenerGarantes().get(j).getId() == inquilinos.get(i).getId()){
                    garantes.add(obtenerGarantes().get(j));
                }
            }
        }
        
        return garantes;
    }
    
    public List<Garante> obtenerGarantesEdificio(long idEdificio){
        List<Departamento> departamentosEdificio = obtenerEdificio(idEdificio).getDepartamentos();
        List<Garante> garantesEdificio = new LinkedList();
        
        for(Departamento unDepartamento : departamentosEdificio){
            if(unDepartamento.getUnInquilino() != null){
                if(unDepartamento.getUnInquilino().getUnGarante() != null){
                    garantesEdificio.add(unDepartamento.getUnInquilino().getUnGarante());
                }
            }
        }
        
        return garantesEdificio;
    }
    
    public Inquilino obtenerInquilinoGarante(long idGarante, long idEdificio){
        Inquilino unInquilino = new Inquilino();
        
        for(Inquilino unInqui : obtenerInquilinosEdificio(idEdificio)){
            if(unInqui.getUnGarante() != null)
                if(unInqui.getUnGarante().getId() == idGarante)
                    unInquilino = unInqui;
        }
        
        return unInquilino;
    }
    
    public boolean existeGarante(String dniGarante, long idEdificio){
        boolean existe = false;
        List<Garante> garantes = obtenerGarantesEdificio(idEdificio);
        int i = 0, tam = garantes.size();
        
        while(i < tam){
            if(garantes.get(i).getDni().equals(dniGarante)){
                existe = true;
                i = tam;
            }else{
                i++;
            }
        }
        
        return existe;
    }
    
/*------------------------------------------------------------------------------
                                INQUILINO
------------------------------------------------------------------------------*/
    public void altaInquilino(int cantidadPersonas, String apellido, String nombre, String dni, String email, String telefono, String cuit, float saldoMesAnt, String descripcion, Garante unGarante, List<Alquiler> alquileres, long idDepartamento, long idCochera) throws Exception{
        Inquilino unInquilino = new Inquilino(cantidadPersonas, apellido, nombre, dni, email, telefono, cuit, 0, descripcion, unGarante, alquileres);
        unaControladora.altaInquilino(unInquilino);
        
        if(idDepartamento != 0){
            Departamento unDepartamento = obtenerDepartamento(idDepartamento);
            unDepartamento.setUnInquilino(unInquilino);
            
            /*  POSIBILIDAD DE USO EN OTRO PROGRAMA -- NO BORRAR
            if(unDepartamento.getUnInquilino() == null){
                unDepartamento.setUnInquilino(unInquilino);
            }else{
                int confirmacion = JOptionPane.showConfirmDialog(null, "Desea realizar esta operación?", "Remplazar Inquilino: "+unInquilino.toString(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(confirmacion == 0){
                    unDepartamento.setUnInquilino(unInquilino);
                    JOptionPane.showMessageDialog(null, "Se ha modificado exitosamente.");
                }
            }*/

            unaControladora.modificarDepartamento(unDepartamento);
        }
        
        if(idCochera != 0){
            Cochera unaCochera = obtenerCochera(idCochera);
            unaCochera.setUnInquilino(unInquilino);

            unaControladora.modificarCochera(unaCochera);
        }
    }
    
    public void modificarInquilino(long idInquilino, int cantidadPersonas, String apellido, String nombre, String dni, String email, String telefono, String cuit, float saldoMesAnt, String descripcion, Garante unGarante, List<Alquiler> alquileres, long idDepartamento, long idCochera) throws Exception{
        Inquilino unInquilino = obtenerInquilino(idInquilino);
        
        unInquilino.setCantidadPersonas(cantidadPersonas);
        unInquilino.setApellido(apellido);
        unInquilino.setNombre(nombre);
        unInquilino.setDni(dni);
        unInquilino.setEmail(email);
        unInquilino.setTelefono(telefono);
        unInquilino.setCuit(cuit);
        unInquilino.setSaldoMesAnt(saldoMesAnt);
        unInquilino.setDescripcion(descripcion);
        unInquilino.setUnGarante(unGarante);
        unInquilino.setAlquileres(alquileres);
        
        unaControladora.modificarInquilino(unInquilino);
        
        Inquilino unInqui = unaControladora.obtenerInquilino(idInquilino);
        
        if(idDepartamento != 0){
            Departamento unDepartamento = obtenerDepartamento(idDepartamento);
            unDepartamento.setUnInquilino(unInqui);
            unaControladora.modificarDepartamento(unDepartamento);
        }else{
            try{
                Departamento unDepartamento = obtenerDepartamentoInquilino(idInquilino);
                if(unDepartamento != null){
                    unDepartamento.setUnInquilino(null);
                    unaControladora.modificarDepartamento(unDepartamento);
                }
            }catch(Exception e){
                System.out.println("Error: "+e);
            }
        }
        
        if(idCochera != 0){
            Cochera unaCochera = obtenerCochera(idCochera);
            unaCochera.setUnInquilino(unInqui);
            unaControladora.modificarCochera(unaCochera);
        }else{
            for(Cochera unaCochera : obtenerCocheras()){
                if(unaCochera.getUnInquilino() != null){
                    if(unaCochera.getUnInquilino().getId() == unInqui.getId()){
                        Cochera cocher = unaCochera;
                        cocher.setUnInquilino(null);
                        unaControladora.modificarCochera(cocher);
                    }
                }
            }
        }
        
    }
    
    public void modificarSaldo(long idInquilino, float saldoMesAnt) throws Exception{
        Inquilino unInquilino = obtenerInquilino(idInquilino);
        
        unInquilino.setSaldoMesAnt(saldoMesAnt);
        
        unaControladora.modificarInquilino(unInquilino);
    }
    
    public void bajaInquilino(long idInquilino, long idDepartamento, long idCochera) throws Exception{
        Departamento unDepartamento = obtenerDepartamento(idDepartamento);
        Cochera unaCochera = obtenerCochera(idCochera);
        
        if(unDepartamento != null){
            unDepartamento.setUnInquilino(null);
            unaControladora.modificarDepartamento(unDepartamento);
        }
        if(unaCochera != null){
            unaCochera.setUnInquilino(null);
            unaControladora.modificarCochera(unaCochera);
        }
        
        unaControladora.bajaInquilino(idInquilino);
    }
    
    public Inquilino obtenerInquilino(long idInquilino){
        return unaControladora.obtenerInquilino(idInquilino);
    }
    
    public List<Inquilino> obtenerInquilinos(){
        return unaControladora.obtenerInquilinos();
    }
    
    // Obtiene todos los inquilinos de un Edificio dado.
    public List<Inquilino> obtenerInquilinosEdificio(long idEdificio){
        List<Departamento> departamentos = obtenerEdificio(idEdificio).getDepartamentos();
        List<Cochera> cocheras = obtenerEdificio(idEdificio).getCocheras();
        List<Inquilino> inquilinos = new LinkedList();
        List<Inquilino> inquilinosFinal = new LinkedList();
        int cont = 0;
        
        for(Departamento unDepartamento : departamentos){
            if(unDepartamento.getUnInquilino() != null){
                inquilinos.add(unDepartamento.getUnInquilino());
            }
        }
        
        for(Inquilino unInquilino : inquilinos){
            inquilinosFinal.add(unInquilino);
        }
        
        for(Cochera unaCochera : cocheras){
            for(Inquilino unInquilino : inquilinos){
                if(unaCochera.getUnInquilino() != null){
                    if(unaCochera.getUnInquilino().getId() != unInquilino.getId()){
                        cont++;
                    }
                    if(cont == inquilinos.size()){
                        inquilinosFinal.add(unaCochera.getUnInquilino());
                        cont = 0;
                    }
                }
            }
            cont = 0;
        }
        
        return inquilinosFinal;
    }
    
    public Inquilino obtenerInquilinoPorAlquiler(long idEdificio, long idAlquiler){
        Inquilino unInquilinoAlquiler = null;
        int cantidadPersonas = 0;
        /* 
        CAMBIE POR ESTE PORQUE EL OTRO SOLO BUSCABA AL INQUILINO POR DEPARTAMENTO..
        CUANDO PUEDE SOLO ALQUILAR COCHERA. CHAAN 29-4-18
        */
        for(Inquilino unInquilino : obtenerInquilinosEdificio(idEdificio)){
            cantidadPersonas += unInquilino.getCantidadPersonas();
            
            if(unInquilino.getAlquileres().size() > 0){
                for(Alquiler unAlquiler : unInquilino.getAlquileres()){
                    if(unAlquiler.getId() == idAlquiler){
                        unInquilinoAlquiler = unInquilino;
                    }
                }
            }
        }
        /*for(Departamento unDepartamento : obtenerEdificio(idEdificio).getDepartamentos()){
            if(unDepartamento.getUnInquilino() != null && !unDepartamento.getUnInquilino().getAlquileres().isEmpty()){
                for(Alquiler unAlquiler : unDepartamento.getUnInquilino().getAlquileres()){
                    if(unAlquiler.getId() == idAlquiler){
                        unInquilinoAlquiler = unDepartamento.getUnInquilino();
                    }
                }
            }
        }*/
        
        return unInquilinoAlquiler;
    }
    
    public List<Inquilino> obtenerInquilinosSinAlquiler(long idEdificio){
        List<Inquilino> inquilinos = obtenerInquilinosEdificio(idEdificio);
        List<Inquilino> inquilinosFinal = new LinkedList();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        Alquiler unAlquiler;
        Date fechaActual = new Date();
        boolean borro0 = false;
        int mesAlquiler, anioAlquiler, i = 0,
            mesActual = Integer.valueOf(monthFormat.format(fechaActual)),
            anioActual = Integer.valueOf(yearFormat.format(fechaActual));
        
        for(Inquilino unInquilino : inquilinos){
            if(unInquilino.getAlquileres() != null){
                if(unInquilino.getAlquileres().size() > 0){
                    unAlquiler = unInquilino.getAlquileres().get(unInquilino.getAlquileres().size()-1);
                    mesAlquiler = Integer.valueOf(monthFormat.format(unAlquiler.getFecha()));
                    anioAlquiler = Integer.valueOf(yearFormat.format(unAlquiler.getFecha()));
                    if(mesAlquiler == mesActual && anioAlquiler == anioActual){
                       inquilinosFinal.add(unInquilino);
                    }
                }else{
                    inquilinosFinal.add(unInquilino);
                }
            }
        }
        
        return inquilinosFinal;
    }
    
    public boolean obtenerInquilinoPorDni(String dni, long idEdificio){
        boolean respuesta = false;
        int i = 0, tam = obtenerInquilinosEdificio(idEdificio).size();
        List<Inquilino> inquilinosEdificio = obtenerInquilinosEdificio(idEdificio);
        
        while(i < tam){
            if(inquilinosEdificio.get(i).getDni().equals(dni)){
                respuesta = true;
                i = tam;
            }else{
                i++;
            }
        }
        
        return respuesta;
    }
    
    public Inquilino obtenerInquilinoPago(long idEdificio, long idPago){
        Inquilino unInquilino = new Inquilino();
        List<Inquilino> inquilinos = obtenerInquilinosEdificio(idEdificio);
        
        for(Inquilino unInqui : inquilinos){
            if(unInqui.getAlquileres().size() > 0){
                for(Alquiler unAlquiler : unInqui.getAlquileres()){
                    if(unAlquiler.getUnPago() != null){
                        if(unAlquiler.getUnPago().getId() == idPago){
                            unInquilino = unInqui;
                        }
                    }
                }
            }
        }
        
        
        return unInquilino;
    }
/*------------------------------------------------------------------------------
                                PAGO
------------------------------------------------------------------------------*/
    public void altaPago(Date fecha, float efectivo, float tarjeta, float banco, float saldoMesAnt, float interesPorAtraso, float monto, String descripcion, long idAlquiler, long idExpensa, long idInquilino) throws Exception{
        Pago unPago = new Pago(fecha, efectivo, tarjeta, banco, saldoMesAnt, interesPorAtraso, monto, descripcion, idAlquiler, idExpensa);
        
        unaControladora.altaPago(unPago);
        
        Inquilino unInquilino = obtenerInquilino(idInquilino);
        unInquilino.setSaldoMesAnt(saldoMesAnt);
        unaControladora.modificarInquilino(unInquilino);
        
        Alquiler unAlquiler = obtenerAlquiler(idAlquiler);
        unAlquiler.setUnPago(unPago);
        unaControladora.modificarAlquiler(unAlquiler);
    }
    
    public void modificarPago(long idPago, Date fecha, float efectivo, float tarjeta, float banco, float saldoMesAnt, float interesPorAtraso, float monto, String descripcion, long idAlquiler, long idExpensa, long idInquilino) throws Exception{
        Inquilino unInquilino = obtenerInquilino(idInquilino);
        unInquilino.setSaldoMesAnt(saldoMesAnt);
        unaControladora.modificarInquilino(unInquilino);
        
        Pago unPago = obtenerPago(idPago);
        
        unPago.setFecha(fecha);
        unPago.setEfectivo(efectivo);
        unPago.setTarjeta(tarjeta);
        unPago.setBanco(banco);
        unPago.setSaldo(saldoMesAnt);
        unPago.setInteresPorAtraso(interesPorAtraso);
        unPago.setMonto(monto);
        unPago.setDescripcion(descripcion);
        unPago.setIdAlquiler(idAlquiler);
        unPago.setIdExpensa(idExpensa);
        
        unaControladora.modificarPago(unPago);
    }
    
    public void bajaPago(long idPago, long idEdificio) throws Exception{
        /* ESTAMOS EN MES 5
        
        SI ES EL ULTIMO -> BOORO MES 5
            ASIGNO EN INQUILINO EL SALDO DEL ANTERIOR.
        SINO -> BORRO MES 3
            SUMAR SALDO DEL ANTERIOR AL QUE BORRE A LO QUE YA TIENE INQUILINO
        
        BORRO MES 3:
            VENIS A PAGAR EL ALQUILER DEL MES TRES, EL SALDO VA A SER EL QUE TENIAS
            HASTA EL MOMENTO O MAS EL ACTUAL?
        */
        
        Inquilino unInquilino = obtenerInquilinoPago(idEdificio, idPago);
        List<Alquiler> alquileres = new LinkedList();
        Alquiler unAlquiler = null;
        int i = 0;
        
        for(Alquiler unAlqui : unInquilino.getAlquileres()){
            alquileres.add(unAlqui);
        }
        
        Collections.sort(alquileres, (Alquiler t, Alquiler t1) -> t.getFecha().compareTo(t1.getFecha()));
        
        while(i < alquileres.size()){
            unAlquiler = alquileres.get(i);
            if(unAlquiler.getUnPago() != null){
                if(unAlquiler.getUnPago().getId() == idPago){
                    unAlquiler.setUnPago(null);
                    if(alquileres.size() > 1){
                        unAlquiler = alquileres.get(i-1);

                        if(i == alquileres.size()-1){
                            unInquilino.setSaldoMesAnt(unAlquiler.getUnPago().getSaldo());
                        }else{
                            float saldo = unInquilino.getSaldoMesAnt()+ unAlquiler.getUnPago().getSaldo();
                            unInquilino.setSaldoMesAnt(saldo);
                        }
                    }else{
                        unInquilino.setSaldoMesAnt(0);
                    }
                    
                    unaControladora.modificarAlquiler(unAlquiler);        
                    unaControladora.bajaPago(idPago);
                    unaControladora.modificarInquilino(unInquilino);
                }
            }
            
            i++;
        }
    }
    
    public Pago obtenerPago(long idPago){
        return unaControladora.obtenerPago(idPago);
    }
    
    public List<Pago> obtenerPagos(){
        return unaControladora.obtenerPagos();
    }
    
    // Obtiene todos los pagos que realizó el Inquilino ("Registro historico")
    public List<Pago> obtenerPagosInquilino(long idInquilino){
        List<Pago> pagos = new LinkedList();
        Inquilino unInquilino = obtenerInquilino(idInquilino);
        
        for(Alquiler unAlquiler : unInquilino.getAlquileres()){
            if(unAlquiler.getUnPago() != null){
                pagos.add(unAlquiler.getUnPago());
            }
        }
        
        return pagos;
    }
    
    public List<Pago> obtenerPagosEdificio(long idEdificio){
        List<Departamento> departamentos = obtenerEdificio(idEdificio).getDepartamentos();
        List<Pago> pagos = new LinkedList();
        
        for(Departamento unDepartamento : departamentos){
            for(Alquiler unAlquiler : unDepartamento.getUnInquilino().getAlquileres()){
                if(unAlquiler.getUnPago() != null){
                    pagos.add(unAlquiler.getUnPago());
                }
            }
        }
        
        return pagos;
    }
    
    public float obtenerTotalPago(){
        float total = 0;
        
        return total;
    }
    
/*------------------------------------------------------------------------------
                                SERVICIO
------------------------------------------------------------------------------*/
    public void altaServicio(String nombre, int mes, int anio, Date fechaEmision, Date fechaVencimiento, float monto, String descripcion, long idEdificio) throws Exception{
        Servicio unServicio = new Servicio(nombre, mes, anio, fechaEmision, fechaVencimiento, monto, descripcion);
        
        unaControladora.altaServicio(unServicio);
        ServicioExpensa unServicioExpensa = new ServicioExpensa(nombre, mes, anio, monto, descripcion);
        unaControladora.altaServicioExpensa(unServicioExpensa);
        
        Edificio unEdificio = obtenerEdificio(idEdificio);
        unEdificio.getServicios().add(unServicio);
        
        unaControladora.modificarEdificio(unEdificio);
    }
    
    public void modificarServicio(long idServicio, String nombre, int mes, int anio, Date fechaEmision, Date fechaVencimiento, float monto, String descripcion) throws Exception{
        Servicio unServicio = obtenerServicio(idServicio);
        
        unServicio.setNombre(nombre);
        unServicio.setMes(mes);
        unServicio.setAnio(anio);
        unServicio.setFechaEmision(fechaEmision);
        unServicio.setFechaVencimiento(fechaVencimiento);
        unServicio.setMonto(monto);
        unServicio.setDescripcion(descripcion);
        
        unaControladora.modificarServicio(unServicio);
    }
    
    public void bajaServicio(long idServicio, long idEdificio) throws Exception{
        Servicio unServicio = obtenerServicio(idServicio);
        Edificio unEdificio = obtenerEdificio(idEdificio);
        int i = 0;
        
        while(unServicio != null){
            if(unEdificio.getServicios().get(i).getId() == unServicio.getId()){
                unEdificio.getServicios().remove(i);
                unServicio = null;
            }
            i++;
        }
        
        unaControladora.modificarEdificio(unEdificio);
        unaControladora.bajaServicio(idServicio);
    }
    
    public Servicio obtenerServicio(long idServicio){
        return unaControladora.obtenerServicio(idServicio);
    }
    
    public Servicio obtenerServicio(long idEdificio, String nombre){
        Servicio unServicio = null;
        
        for(Servicio unServi : obtenerEdificio(idEdificio).getServicios()){
            if(nombre.equals(unServi.getNombre())){
                unServicio = unServi;
            }
        }
        
        return unServicio;
    }
    
    public List<Servicio> obtenerServicios(){
        return unaControladora.obtenerServicios();
    }
    
    public List<Servicio> obtenerServEdifMes(int mes, long idEdificio){  // Obtiene los servicios del Edificio según el mes actual.
        Edificio unEdificio = obtenerEdificio(idEdificio);
        List<Servicio> servicios = new LinkedList();
        
        for(Servicio unServicio : unEdificio.getServicios()){
            if(unServicio.getMes() == mes){
                servicios.add(unServicio);
            }
        }
        
        return servicios;
    }
    
/*------------------------------------------------------------------------------
                                SERVICIOEXPENSA
------------------------------------------------------------------------------*/
    public void altaServicioExpensa(String nombre, int mes, int anio, float monto, String descripcion) throws Exception{
        ServicioExpensa unServicio = new ServicioExpensa(nombre, mes, anio, monto, descripcion);
        
        unaControladora.altaServicioExpensa(unServicio);
    }
    
    public void modificarServicioExpensa(long id, String nombre, int mes, int anio, float monto, String descripcion) throws Exception{
        ServicioExpensa unServicio = obtenerServicioExpensa(id);
        
        unServicio.setNombre(nombre);
        unServicio.setMes(mes);
        unServicio.setAnio(anio);
        unServicio.setMonto(monto);
        unServicio.setDescripcion(descripcion);
        
        unaControladora.modificarServicioExpensa(unServicio);
    }
    
    public void bajaServicioExpensa(long idServicioExpensa) throws Exception{
        unaControladora.bajaServicioExpensa(idServicioExpensa);
    }
    
    public ServicioExpensa obtenerServicioExpensa(long id){
        return unaControladora.obtenerServicioExpensa(id);
    }
    
    public List<ServicioExpensa> obtenerServiciosExpensa(){
        return unaControladora.obtenerServiciosExpensa();
    }
    
    public List<ServicioExpensa> obtenerServiciosExpensaDepartamento(Departamento unDepartamento, int mes, int anio){
        List<ServicioExpensa> serviciosExpensa = new LinkedList();
        
        if(mes == 1){
            mes = 12;
            anio -= 1;
        }else{
            mes -= 1;
        }
        
        if(unDepartamento.getExpensas().size() > 0){
            for(Expensa unaExpensa : unDepartamento.getExpensas()){
                if(unaExpensa.getMes() == mes && unaExpensa.getAnio() == anio){
                    if(unaExpensa.getServiciosExpensa().size() > 0){
                        for(ServicioExpensa unServicioExpensa : unaExpensa.getServiciosExpensa()){
                            serviciosExpensa.add(unServicioExpensa);
                        }
                    }
                }
            }
        }
        
        return serviciosExpensa;
    }
    
    // Se utiliza en PanelExpensa para crear una lista de ServiciosExpensa para una Expensa.
    public ServicioExpensa cargarListaServicioExpensa(String nombre, int mes, int anio, float monto, String descripcion) throws Exception{
        ServicioExpensa unServicioExpensa = new ServicioExpensa(nombre, mes, anio, monto, descripcion);
        
        unaControladora.altaServicioExpensa(unServicioExpensa);
        
        return unServicioExpensa;
    }
    
}
