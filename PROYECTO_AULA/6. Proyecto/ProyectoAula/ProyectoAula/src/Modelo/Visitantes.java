package Modelo;

import java.sql.Date;

//Clase visitantes hijo 
public class Visitantes extends Usuario {

    //Declaracion de variables 
    private String idVisitantes;
    private String visiNombres;
    private String visiTelefono;
    private String visiEmail;
    private String visiAreaDirige;
    private String visiTemperatura;
    private Date visiFechaEntrada;
    private Date visiFechaSalida;

    
    //Constructor de la clase empleados
    public Visitantes(String idVisitantes, String visiNombres, String visiTelefono, String visiEmail, String visiAreaDirige, String visiTemperatura, Date visiFechaEntrada, Date visiFechaSalida, String idUsuario, String usuaNombres, String usuaApellidos, String usuaClave) {
        super(idUsuario, usuaNombres, usuaApellidos, usuaClave);
        this.idVisitantes = idVisitantes;
        this.visiNombres = visiNombres;
        this.visiTelefono = visiTelefono;
        this.visiEmail = visiEmail;
        this.visiAreaDirige = visiAreaDirige;
        this.visiTemperatura = visiTemperatura;
        this.visiFechaEntrada = visiFechaEntrada;
        this.visiFechaSalida = visiFechaSalida;
    }

    //Metodos get(obtener o mostrar) y set (poner o modificar)
    public String getIdVisitantes() {
        return idVisitantes;
    }

    public void setIdVisitantes(String idVisitantes) {
        this.idVisitantes = idVisitantes;
    }

    public String getVisiNombres() {
        return visiNombres;
    }

    public void setVisiNombres(String visiNombres) {
        this.visiNombres = visiNombres;
    }

    public String getVisiTelefono() {
        return visiTelefono;
    }

    public void setVisiTelefono(String visiTelefono) {
        this.visiTelefono = visiTelefono;
    }

    public String getVisiEmail() {
        return visiEmail;
    }

    public void setVisiEmail(String visiEmail) {
        this.visiEmail = visiEmail;
    }

    public String getVisiAreaDirige() {
        return visiAreaDirige;
    }

    public void setVisiAreaDirige(String visiAreaDirige) {
        this.visiAreaDirige = visiAreaDirige;
    }

    public String getVisiTemperatura() {
        return visiTemperatura;
    }

    public void setVisiTemperatura(String visiTemperatura) {
        this.visiTemperatura = visiTemperatura;
    }

    public Date getVisiFechaEntrada() {
        return visiFechaEntrada;
    }

    public void setVisiFechaEntrada(Date visiFechaEntrada) {
        this.visiFechaEntrada = visiFechaEntrada;
    }

    public Date getVisiFechaSalida() {
        return visiFechaSalida;
    }

    public void setVisiFechaSalida(Date visiFechaSalida) {
        this.visiFechaSalida = visiFechaSalida;
    }

}
