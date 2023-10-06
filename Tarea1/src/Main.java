import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Cliente 1", "12345", new Direccion("Calle 1"));
        Cliente cliente2 = new Cliente("Cliente 2", "67890", new Direccion("Calle 2"));

        Articulo articulo1 = new Articulo("Artículo 1", "Descripción 1", 1.5F, 10.0F);
        Articulo articulo2 = new Articulo("Artículo 2", "Descripción 2", 2.0F, 15.0F);
        Articulo articulo3 = new Articulo("Artículo 3", "Descripción 3", 0.5F, 5.0F);
        Articulo articulo4 = new Articulo("Artículo 4", "Descripción 4", 1.0F, 20.0F);
        Articulo articulo5 = new Articulo("Artículo 5", "Descripción 5", 0.8F, 12.0F);

        OrdenCompra compra1 = new OrdenCompra(cliente1);
        OrdenCompra compra2 = new OrdenCompra(cliente2);
        OrdenCompra compra3 = new OrdenCompra(cliente1);

        DetalleOrden orden1 = new DetalleOrden(articulo1, 2);
        DetalleOrden orden2 = new DetalleOrden(articulo2, 3);
        DetalleOrden orden3 = new DetalleOrden(articulo3, 4);
        DetalleOrden orden4 = new DetalleOrden(articulo4, 1);
        DetalleOrden orden5 = new DetalleOrden(articulo5, 2);

        compra1.agregarOrden(orden1);
        compra1.agregarOrden(orden2);
        compra2.agregarOrden(orden3);
        compra3.agregarOrden(orden4);
        compra3.agregarOrden(orden5);

        DocTributario boleta1 = new Boleta("12345-1", cliente1.getRut(), compra1.getFecha(), cliente1.getDireccion());
        DocTributario factura1 = new Factura("67890-1", cliente2.getRut(), compra2.getFecha(), cliente2.getDireccion());

        compra1.asociarDocTributario(boleta1);
        compra2.asociarDocTributario(factura1);
        compra3.asociarDocTributario(boleta1);

        Pago pago1 = new Efectivo(50.0F, compra1.getFecha(), compra1);
        Pago pago2 = new Transferencia(30.0F, compra3.getFecha(), "BancoEstado", "43457423", compra3);
        Pago pago3 = new Tarjeta(25.0F, compra2.getFecha(), "Visa", "8274923", compra2);
        Pago pago4 = new Efectivo(15.0F, compra1.getFecha(), compra1);

        compra1.agregarPago(pago1);
        compra1.agregarPago(pago2);
        compra2.agregarPago(pago3);
        compra3.agregarPago(pago4);


        System.out.println("Orden de Compra 1:");
        System.out.println("Precio(IVA incluido): $" + compra1.calcPrecioConIVA());
        System.out.println("IVA total: $" + compra1.calcIVA());
        System.out.println("\nOrden de Compra 2:");
        System.out.println("Precio(IVA incluido): $" + orden2.calcPrecioConIVA());
        System.out.println("IVA total: $" + compra2.calcIVA());
        System.out.println("\nOrden de Compra 3:");
        System.out.println("Precio(IVA incluido): $" + orden3.calcPrecioConIVA());
        System.out.println("IVA total: $" + compra3.calcIVA());
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
    public Direccion getDireccion() {
        return this.direccion;
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
        return this.articulo.getPrecio() * this.cantidad + this.calcIVA();
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
    public Direccion getDireccion() {
        return this.direccion;
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
    public Pago(float monto, Date fecha, OrdenCompra oc) {
        this.monto = monto;
        this.fecha = oc.getFecha();
        this.metodo = metodo;
        this.oc = oc;
    }
    public Date getFecha() {
        return this.fecha;
    }
    public float getMonto() {
        return this.monto;
    }
    public OrdenCompra getOrden() {
        return this.oc;
    }
    public String toString() {
        return "Método de pago: " + this.metodo + "Total a pagar" + this.monto + "Fecha: " + this.fecha;
    }
}
class Efectivo extends Pago {

    public Efectivo(float monto, Date fecha, OrdenCompra oc) {
        super(monto, fecha, oc);
    }
    public float calcDevolucion() {
        float totalOrden = getOrden().calcPrecioConIVA();
        if (getMonto() >= totalOrden) {
            return getMonto() - totalOrden;
        }
        else {
            return 0;
        }
    }
}
class Transferencia extends Pago {
    private String banco;
    private String numCuenta;
    public Transferencia(float monto, Date fecha, String banco, String numCuenta, OrdenCompra oc) {
        super(monto, fecha, oc);
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
    public Tarjeta(float monto, Date fecha, String tipo, String numTransaccion, OrdenCompra oc) {
        super(monto, fecha, oc);
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
    public void agregarPago(Pago pago) {
        pagos.add(pago);
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