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
    public float calcPeso() {
        return this.articulo.getPeso() * this.cantidad;
    }
    public String toString() {
        return "Artículo: " + this.articulo + " x " + this.cantidad + "\nPrecio: " + this.calcPrecioConIVA() + " IVA: " + this.calcIVA() + "\nPeso: " + this.calcPeso();
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

class Pago {
    private float monto;
    private Date fecha;
    private String metodo;
    private OrdenCompra oc;
    public Pago(float monto, Date fecha,String metodo, OrdenCompra oc) {
        this.oc = oc;
        this.monto = oc.calcPrecioConIVA();
        this.fecha = oc.getFecha();
        this.metodo = metodo;

    }
    public Date getFecha() {
        return this.fecha;
    }
    public float getMonto() {
        return this.monto;
    }
    public String toString() {
        return "Método de pago: " + this.metodo + "Total a pagar" + this.monto + "Fecha: " + this.fecha;
    }
}
class Efectivo extends Pago {

    public Efectivo(float monto, Date fecha, String metodo, OrdenCompra oc) {
        super(monto, fecha, "Efectivo", oc);
    }

    public float calcDevolucion(float DineroAbonado) {
        return DineroAbonado - getMonto();
    }
}
class Transferencia extends Pago {
    private String banco;
    private String numCuenta;
    public Transferencia(float monto, Date fecha, String metodo, String banco, String numCuenta, OrdenCompra oc) {
        super(monto, fecha, "Transferencia", oc);
        this.banco = banco;
        this.numCuenta = numCuenta;
    }
    public String getBanco() {
        return this.banco;
    }
    public String getNumCuenta() {
        return this.numCuenta;
    }
}
class Tarjeta extends Pago {
    private String tipo;
    private String numTransaccion;
    public Tarjeta(float monto, Date fecha, String metodo, String tipo, String numTransaccion, OrdenCompra oc) {
        super(monto, fecha, "Tarjeta", oc);
        this.tipo = tipo;
        this.numTransaccion = numTransaccion;
    }
    public String getTipo() {
        return this.tipo;
    }
    public String getNumTransaccion() {
        return this.numTransaccion;
    }
}

class OrdenCompra {
    private Date fecha;
    private String estado;
    private Cliente cliente;
    private ArrayList<DetalleOrden> ordenes;
    private DocTributario docTributario;
    private ArrayList<Pago> pagos;
    public OrdenCompra(Cliente cliente) {
        this.fecha = new Date();
        this.estado = "En proceso";
        this.cliente = cliente;
        this.ordenes = new ArrayList<>();
        this.pagos = new ArrayList<>();
    }
    public Date getFecha() {
        return this.fecha;
    }
    public void agregarOrden(DetalleOrden detalle) {
        ordenes.add(detalle);
    }
    public void asociarDocTributario(DocTributario docTributario) {
        this.docTributario = docTributario;
    }
    public float calcPrecioSinIVA() {
        float precio = 0;
        for(DetalleOrden detalle : ordenes) {
            precio += detalle.calcPrecioSinIVA();
        }
        return precio;
    }
    public float calcIVA() {
        float IVA = 0;
        for(DetalleOrden detalle : ordenes) {
            IVA += detalle.calcIVA();
        }
        return IVA;
    }
    public float calcPrecioConIVA() {
        float PrecioFinal = 0;
        for(DetalleOrden detalle : ordenes) {
            PrecioFinal += detalle.calcPrecioConIVA();
        }
        return PrecioFinal;
    }
    public float CalcPeso() {
        float PesoTotal = 0;
        for(DetalleOrden detalle : ordenes) {
            PesoTotal += detalle.calcPeso();
        }
        return PesoTotal;
    }
}