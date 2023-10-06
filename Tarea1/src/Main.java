import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
    }
}

class Cliente {
    private String nombre;
    private String rut;
    private Direccion direccion;
    public Cliente(String nombre, String rut, Direccion direccion) {
        this.nombre = nombre;
        this.rut = rut;
        this.direccion = direccion;
    }

    public String getNombre() {
        return this.nombre;
    }
    public String getRut() {
        return this.rut;
    }
    public String toString() {
        return this.nombre + " RUT: " + this.rut + " Dirección: " + this.direccion;
    }
}

class Direccion {
    private String direccion;
    public Direccion(String direccion) {
        this.direccion = direccion;
    }
    public String getDireccion() {
        return this.direccion;
    }
}

class Articulo {
    private String nombre;
    private String descripcion;
    private float precio;
    private float peso;
    public Articulo(String nombre, String descripcion, float precio, float peso) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.peso = peso;
    }
    public String getNombre() {
        return this.nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    public float getPrecio() {
        return this.precio;
    }
    public float getPeso() {
        return this.peso;
    }
    public String toString() {
        return this.nombre + "\nDescripción: " + this.descripcion + "\nPrecio: $" + this.precio + " Peso: " + this.peso;
    }
}

class DetalleOrden {
    private Articulo articulo;
    private int cantidad;
    public DetalleOrden(Articulo articulo, int cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
    }
    public float calcIVA() {
        return this.articulo.getPrecio() * this.cantidad * 19 / 100;
    }
    public float calcPrecioSinIVA() {
        return this.articulo.getPrecio() * this.cantidad;
    }
    public float calcPrecioConIVA() {
        return this.articulo.getPrecio() * this.cantidad - this.calcIVA();
    }
    public String toString() {
        return "Precio: " + this.calcPrecioConIVA() + "\nIVA: " + this.calcIVA();
    }
}

class DocTributario {
    private String numero;
    private String rut;
    private Date fecha;
    private Direccion direccion;
    public DocTributario(String numero, String rut, Date fecha, Direccion direccion) {
        this.numero = numero;
        this.rut = rut;
        this.fecha = fecha;
        this.direccion = direccion;
    }
    public String getNumero() {
        return this.numero;
    }
    public String getRut() {
        return this.rut;
    }
    public Date getFecha() {
        return this.fecha;
    }
    public String getDireccion() {
        return this.direccion.getDireccion();
    }
    public String toString() {
        return "Fecha: " + this.fecha + "\nNro: " + this.numero + "\nRUT: " + this.rut + "\nDirección: " + this.direccion.getDireccion();
    }
}
class Boleta extends DocTributario {
    public Boleta(String numero, String rut, Date fecha, Direccion direccion) {
        super(numero, rut, fecha, direccion);
    }
}
class Factura extends DocTributario {
    public Factura(String numero, String rut, Date fecha, Direccion direccion) {
        super(numero, rut, fecha, direccion);
    }
}