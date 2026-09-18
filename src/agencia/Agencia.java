package agencia;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class Agencia {
	private String nombre;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Vehiculo> listaVehiculos;
	
	public Agencia(String nombre) {
		this.nombre=nombre;
		this.listaClientes= new ArrayList<>();
		this.listaVehiculos= new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<Vehiculo> getListaVehiculos() {
		return listaVehiculos;
	}

	public void setListaVehiculos(ArrayList<Vehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}
	
	public void bienvenida() {
		JOptionPane.showMessageDialog(null, "Bienvenido al sistema de la agencia " + getNombre());
	}
	public void despedida() {
		JOptionPane.showMessageDialog(null, "La agencia " + getNombre() + " te desea un buen día");
	}
	
	public void agregarCliente() {
		String nombre= Funciones.validarString("Ingrese el nombre del cliente");
		int dni= Funciones.validarDni(listaClientes);
		String licenciaMoto = Funciones.seleccionMenu("Licencia de Moto");
		String licenciaAuto = Funciones.seleccionMenu("Licencia de Auto");
		String licenciaConducir = licenciaAuto + "/" + licenciaMoto;
		Cliente cliente = new Cliente(nombre, dni, licenciaConducir);
		listaClientes.add(cliente);
		JOptionPane.showMessageDialog(null, "Se agrego un nuevo cliente");
	}
	
	public void agregarVehiculo() {
		String tipo=Funciones.seleccionMenu("Tipo de vehículo");
		String marca=Funciones.validarString("Ingrese la marca");
		String patente=Funciones.validarPatente(listaVehiculos);
		
		double tarifa=Funciones.validarDoublePositivo("Ingrese la tarifa base diaria");
		if (tipo.equals("Auto")){
			int cantPuertas=Funciones.validarInt("Ingrese la cantidad de puertas", 2, 6);
			String[] tiposTransmision= {"Automático", "Manual"};
			String transmision = null;
			while(transmision==null) {
				transmision=(String)JOptionPane.showInputDialog(null, "Seleccione el tipo de transmision", null, 
	                JOptionPane.QUESTION_MESSAGE, null, tiposTransmision, tiposTransmision[0]);
			}
			Auto nuevo=new Auto(patente, marca, tarifa, cantPuertas, transmision);
			listaVehiculos.add(nuevo);
		} else if (tipo.equals("Moto")) {
			int cilindrada=Funciones.validarInt("Ingrese el número de cilindrada", 1, 3000);
			Moto nueva=new Moto(patente, marca, tarifa, cilindrada);
			listaVehiculos.add(nueva);
		}
		JOptionPane.showMessageDialog(null, "Se agrego un nuevo vehículo a la flota");
	}
	
	@Override
	public String toString() {
		return "Agencia [nombre=" + nombre + ", listaClientes=" + listaClientes + ", listaVehiculos=" + listaVehiculos
				+ "]";
	}
	
	public Cliente seleccionarCliente() {
		Cliente a=null;
			while(a==null) {
				a=(Cliente)JOptionPane.showInputDialog(null, "Seleccione un cliente", null, 
		                JOptionPane.QUESTION_MESSAGE, null, listaClientes.toArray(), listaClientes.toArray()[0]);		
				if (a==null) {
					JOptionPane.showMessageDialog(null, "Seleccione un cliente de la lista");
				} 
			}
		return a;
	}
	
	public Vehiculo seleccionarVehiculo() {
		Vehiculo a=null;
		
			while(a==null) {
				a=(Vehiculo)JOptionPane.showInputDialog(null, "Seleccione un vehiculo", null, 
		                JOptionPane.QUESTION_MESSAGE, null, listaVehiculos.toArray(), listaVehiculos.toArray()[0]);		
				if (a==null) {
					JOptionPane.showMessageDialog(null, "Seleccione un vehiculo de la lista");
				} 
			}
		return a;
	}		
	public Vehiculo seleccionarVehiculoDisponible() {
		ArrayList<Vehiculo> listaVehiculosDisponibles=new ArrayList<>();
		for (Iterator<Vehiculo> iterator = listaVehiculos.iterator(); iterator.hasNext();) {
			Vehiculo vehiculo = (Vehiculo) iterator.next();
			if (vehiculo.isDisponibilidad()) {
				listaVehiculosDisponibles.add(vehiculo);
			}
		}
		Vehiculo a=null;
		
			while(a==null) {
				a=(Vehiculo)JOptionPane.showInputDialog(null, "Seleccione un vehiculo", null, 
		                JOptionPane.QUESTION_MESSAGE, null, listaVehiculosDisponibles.toArray(), listaVehiculosDisponibles.toArray()[0]);		
				if (a==null) {
					JOptionPane.showMessageDialog(null, "Seleccione un vehiculo de la lista");
				} 
			}
		return a;
	}		
	
}
