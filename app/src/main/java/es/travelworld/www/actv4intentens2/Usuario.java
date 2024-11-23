package es.travelworld.www.actv4intentens2;

public class Usuario {

    private String nombre;
    private String apellido;
    private int edades;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdades() {
        return edades;
    }

    public void setEdades(int edades) {
        this.edades = edades;
    }

    public Usuario(String nombre, String apellido, int edades) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edades = (edades);
    }
    public Usuario() {

    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edades=" + edades +
                '}';
    }
}




