package agencia;

import java.time.LocalDate;

public class Alquiler {
	private int id;
	private LocalDate fechaInicio;
	private int dias;
	private double costoTotal;
	private static int idAlquiler = 1;
	private Cliente cliente;
	private Vehiculo vehiculo;
	private String estado;
	
	public Alquiler(LocalDate fechaInicio, int dias, double costoTotal, Vehiculo vehiculo) {
		this.id=idAlquiler;
		idAlquiler++;
		this.fechaInicio=fechaInicio;
		this.dias=dias;
		this.costoTotal=costoTotal;
		this.vehiculo=vehiculo;
		this.estado="ACTIVO";
	}
	public Alquiler(LocalDate fechaInicio, int dias, double costoTotal, Cliente cliente, Vehiculo vehiculo) {
		this.id=idAlquiler;
		idAlquiler++;
		this.fechaInicio=fechaInicio;
		this.dias=dias;
		this.costoTotal=costoTotal;
		this.cliente=cliente;
		this.vehiculo=vehiculo;
		estado="FINALIZADO";
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public double getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}
	@Override
	public String toString() {
		return "#" + id + ", fechaInicio:" + fechaInicio + ", duración:" + dias + " dias, costo total: $" + costoTotal
				+ "\n cliente " + cliente.getNombre() + " dni: " + cliente.getDni() + "\n vehiculo patente:" + vehiculo.getPatente() + " marca: "+vehiculo.getMarca()
				+ "\n ESTADO: " + estado;
	}
	
}
