package Modelo;

import java.sql.Date;

//Clase empleados hijo 
public class Empleados extends Usuario {

    //Declaracion de variables 
    private String idEmpleado;
    private String emplNombres;
    private String emplTelefono;
    private String emplEmail;
    private String emplArea;
    private String emplTemperatura;
    private Date emplFechaEntrada;
    private Date emplFechaSalida;

    //Constructor de la clase empleados
    public Empleados(String idEmpleado, String emplNombres, String emplTelefono, String emplEmail, String emplArea, String emplTemperatura, Date emplFechaEntrada, Date emplFechaSalida, String idUsuario, String usuaNombres, String usuaApellidos, String usuaClave) {
        super(idUsuario, usuaNombres, usuaApellidos, usuaClave);
        this.idEmpleado = idEmpleado;
        this.emplNombres = emplNombres;
        this.emplTelefono = emplTelefono;
        this.emplEmail = emplEmail;
        this.emplArea = emplArea;
        this.emplTemperatura = emplTemperatura;
        this.emplFechaEntrada = emplFechaEntrada;
        this.emplFechaSalida = emplFechaSalida;
    }

    //Metodos get(obtener o mostrar) y set (poner o modificar)
    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getEmplNombres() {
        return emplNombres;
    }

    public void setEmplNombres(String emplNombres) {
        this.emplNombres = emplNombres;
    }

    public String getEmplTelefono() {
        return emplTelefono;
    }

    public void setEmplTelefono(String emplTelefono) {
        this.emplTelefono = emplTelefono;
    }

    public String getEmplEmail() {
        return emplEmail;
    }

    public void setEmplEmail(String emplEmail) {
        this.emplEmail = emplEmail;
    }

    public String getEmplArea() {
        return emplArea;
    }

    public void setEmplArea(String emplArea) {
        this.emplArea = emplArea;
    }

    public String getEmplTemperatura() {
        return emplTemperatura;
    }

    public void setEmplTemperatura(String emplTemperatura) {
        this.emplTemperatura = emplTemperatura;
    }

    public Date getEmplFechaEntrada() {
        return emplFechaEntrada;
    }

    public void setEmplFechaEntrada(Date emplFechaEntrada) {
        this.emplFechaEntrada = emplFechaEntrada;
    }

    public Date getEmplFechaSalida() {
        return emplFechaSalida;
    }

    public void setEmplFechaSalida(Date emplFechaSalida) {
        this.emplFechaSalida = emplFechaSalida;
    }

}
