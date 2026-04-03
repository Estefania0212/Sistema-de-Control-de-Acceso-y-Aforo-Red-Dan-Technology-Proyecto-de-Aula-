package Modelo;

//Clase Usuario Padre
public class Usuario {
    
    //Declaracion de varibles privadas
    private String idUsuario;
    private String usuaNombres;
    private String usuaApellidos;
    private String usuaClave;

    //Metodo constructor 
    public Usuario(String idUsuario, String usuaNombres, String usuaApellidos, String usuaClave) {
        this.idUsuario = idUsuario;
        this.usuaNombres = usuaNombres;
        this.usuaApellidos = usuaApellidos;
        this.usuaClave = usuaClave;
    }
    
    //Metodos get(obtener o mostrar) y set (poner o modificar)
    
    
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuaNombres() {
        return usuaNombres;
    }

    public void setUsuaNombres(String usuaNombres) {
        this.usuaNombres = usuaNombres;
    }

    public String getUsuaApellidos() {
        return usuaApellidos;
    }

    public void setUsuaApellidos(String usuaApellidos) {
        this.usuaApellidos = usuaApellidos;
    }

    public String getUsuaClave() {
        return usuaClave;
    }

    public void setUsuaClave(String usuaClave) {
        this.usuaClave = usuaClave;
    }
    

}
