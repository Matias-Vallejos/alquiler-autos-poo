package agencia;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public abstract class Vehiculo {
	private String patente;
	private String marca;
	private boolean disponibilidad;
	private double tarifaBaseDiaria;
	private ArrayList<Alquiler> listaAlquiler;
	
	public Vehiculo(String patente, String marca, double tarifaBaseDiaria) {
		this.patente=patente;
		this.marca=marca;
		this.tarifaBaseDiaria=tarifaBaseDiaria;
		disponibilidad=true;
		this.listaAlquiler= new ArrayList<>();
	}
	
	public ArrayList<Alquiler> getListaAlquiler() {
		return listaAlquiler;
	}

	public void setListaAlquiler(ArrayList<Alquiler> listaAlquiler) {
		this.listaAlquiler = listaAlquiler;
	}

	public abstract double calcularCostoAlquiler(int dias);

	@Override
	public String toString() {
		return "marca " + marca + " patente " + patente + " tarifa diaria base $" + tarifaBaseDiaria + " disponible: " + disponibilidad; 
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public double getTarifaBaseDiaria() {
		return tarifaBaseDiaria;
	}

	public void setTarifaBaseDiaria(double tarifaBaseDiaria) {
		this.tarifaBaseDiaria = tarifaBaseDiaria;
	}
	public abstract void modificarVehiculo(Agencia agencia);
	
	public void verAlquiler() {
		Alquiler a = null;
		if(listaAlquiler.isEmpty()) {
			JOptionPane.showMessageDialog(null, getPatente() + " no tiene alquileres en el historial");
		} else {
			while(a==null) {
		 a= (Alquiler)JOptionPane.showInputDialog(null, "Seleccione un Alquiler", null, 
                JOptionPane.QUESTION_MESSAGE, null, listaAlquiler.toArray(), listaAlquiler.toArray()[0]);		
		if(a==null) {
			JOptionPane.showMessageDialog(null, "Seleccione un alquiler de la lista");
		}
	}
			JOptionPane.showMessageDialog(null, a.toString());
		}
	}
	public abstract boolean validarLicencia(String licencia);
}
