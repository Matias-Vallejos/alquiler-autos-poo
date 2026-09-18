package agencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class Cliente {
	private String nombre;
	private int id;
	private int dni;
	private String licenciaConducir;
	private static int idCliente = 1;
	private ArrayList<Alquiler> listaAlquiler;
	
	public Cliente(String nombre,int dni, String licenciaConducir) {
		this.nombre = nombre;
		this.id = idCliente;
		idCliente++;
		this.dni = dni;
		this.licenciaConducir = licenciaConducir;
		this.listaAlquiler= new ArrayList<>();
	}
	
	public ArrayList<Alquiler> getListaAlquiler() {
		return listaAlquiler;
	}

	public void setListaAlquiler(ArrayList<Alquiler> listaAlquiler) {
		this.listaAlquiler = listaAlquiler;
	}

	@Override
	public String toString() {
		return "#" + id + " " + nombre + "/ dni=" + dni + "/ licenciaConducir=" + licenciaConducir;
	}
	public static int getIdCliente() {
		return idCliente;
	}
	public void modificarCliente(Agencia agencia) {
		String menuModificar="";
		while(!menuModificar.equals("Volver")) {
			
		switch(menuModificar=Funciones.seleccionMenu("Modificar Cliente: " + toString())) {
			case "Modificar nombre":
				setNombre(Funciones.validarString("Elegir nuevo nombre\nNombre actual: " + getNombre()));
				break;
			case "Modificar dni":
				int dni =Funciones.validarInt("Elegir nuevo dni\nDni actual: " + getDni(), 1, Integer.MAX_VALUE);

					boolean validarDni=false;
					while (!validarDni) {
						validarDni=true;
						for (Iterator<Cliente> iterator = agencia.getListaClientes().iterator(); iterator.hasNext();) {
							Cliente cliente = (Cliente) iterator.next();
							if(dni==getDni()){
							} else if(dni==cliente.getDni()) {
								validarDni=false;
								JOptionPane.showMessageDialog(null, "ya existe ese dni en el sistema");
								dni= Funciones.validarInt("Elegir nuevo dni\nDni actual: " + getDni(),1,Integer.MAX_VALUE);
							}
						}
				}
				setDni(dni);
				break;
			case "Modificar licencia": 
				String[] licencias= getLicenciaConducir().split("/");
				String licenciaAuto = Funciones.seleccionMenu("Licencia de Moto: Actualmente " + licencias[1]);
				String licenciaMoto = Funciones.seleccionMenu("Licencia de Auto: Actualmente " + licencias[0]);
				String licenciaConducir = licenciaAuto + "/" + licenciaMoto;
				setLicenciaConducir(licenciaConducir);
			}
		}
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getLicenciaConducir() {
		return licenciaConducir;
	}
	public void setLicenciaConducir(String licenciaConducir) {
		this.licenciaConducir = licenciaConducir;
	}
	
	public Alquiler cargarAlquiler(Agencia agencia, Vehiculo vehiculo) {
		Alquiler alquiler=null;

		if (vehiculo.validarLicencia(getLicenciaConducir())) {
		int dias = Funciones.validarInt("Ingrese cantidad de dias",1,Integer.MAX_VALUE);
		double costoTotal= vehiculo.calcularCostoAlquiler(dias);
		int[] fechaParseada=Funciones.validarFecha();	
		int anio = fechaParseada[0];
		int mes = fechaParseada[1];
		int dia = fechaParseada[2];
		LocalDate fechaInicio= LocalDate.of(anio, mes, dia);
		alquiler = new Alquiler(fechaInicio, dias, costoTotal, vehiculo);
		}
		return alquiler;
	}

	

	public void verAlquiler() {
		Alquiler a = null;
		if(listaAlquiler.isEmpty()) {
			JOptionPane.showMessageDialog(null, getNombre() + " no tiene alquileres en el historial");
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
	public void finalizarAlquiler() {
		Alquiler a = null;
		if(listaAlquiler.isEmpty()) {
			JOptionPane.showMessageDialog(null, getNombre() + " no tiene alquileres en el historial");
		} else {
		
			while(a==null) {
		 a= (Alquiler)JOptionPane.showInputDialog(null, "Seleccione un Alquiler", null, 
                JOptionPane.QUESTION_MESSAGE, null, listaAlquiler.toArray(), listaAlquiler.toArray()[0]);		
		if(a==null) {
			JOptionPane.showMessageDialog(null, "Seleccione un alquiler de la lista");
		}	else if (a.getEstado().equals("FINALIZADO")) {
			JOptionPane.showMessageDialog(null, "El alquiler ya esta finalizado");
		} else if (a.getEstado().equals("ACTIVO")) {
			String confirmacion=Funciones.seleccionMenu("Confirmación: Finalizar alquiler \n" + a.toString());
			if (confirmacion.equals("Confirmar")) {
				a.setEstado("FINALIZADO");
				a.getVehiculo().setDisponibilidad(true);
			}
		}
	}	
			JOptionPane.showMessageDialog(null, a.toString());
		}
	}		
}