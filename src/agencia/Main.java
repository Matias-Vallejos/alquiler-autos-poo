package agencia;

import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		Funciones funciones = new Funciones();
		Agencia agencia = new Agencia("Super Alquileres");
		funciones.clientesTesteo(agencia);
		funciones.autosTesteo(agencia);
		funciones.alquilerTesteo(agencia);
		
		agencia.bienvenida();
		String menuPrincipal="";
		
		do {
			menuPrincipal=Funciones.seleccionMenu("Menu principal");
		switch(menuPrincipal){
			case "Gestión de clientes":
				String menuClientes="";
				while(!menuClientes.equals("Volver")) {
				switch(menuClientes=Funciones.seleccionMenu("Gestión de clientes")) {
				case "Nuevo Cliente":
					agencia.agregarCliente();
					break;
				case "Modificar Cliente":
					agencia.seleccionarCliente().modificarCliente(agencia);;
					break;
				case "Ver datos cliente":
					JOptionPane.showMessageDialog(null, agencia.seleccionarCliente().toString());
					break;
				case "Nuevo Alquiler":
					Cliente cliente=agencia.seleccionarCliente();
					Vehiculo vehiculo=agencia.seleccionarVehiculoDisponible();
					Alquiler alquiler=cliente.cargarAlquiler(agencia, vehiculo);
					if (alquiler!=null) {
						alquiler.setCliente(cliente);
						String confirmar=Funciones.seleccionMenu("Confirmación: " + alquiler.toString());
						if(confirmar.equals("Confirmar")) {
							cliente.getListaAlquiler().add(alquiler);
							vehiculo.getListaAlquiler().add(alquiler);	
							vehiculo.setDisponibilidad(false);
							JOptionPane.showMessageDialog(null, "Alquiler realizado correctamente");
							} else {
								JOptionPane.showMessageDialog(null, "El alquiler se ha cancelado");
								}
							}
					break;
				case "Simular Alquiler":
					funciones.costoAlquiler(agencia);
					break;
				case "Historial de alquiler":
					agencia.seleccionarCliente().verAlquiler();
					break;
				case "Finalizar un alquiler":
					agencia.seleccionarCliente().finalizarAlquiler();
				}
				}
				break;
			case "Gestión de vehículos":
				String menuVehiculos="";
				while(!menuVehiculos.equals("Volver")) {
				switch(menuVehiculos=Funciones.seleccionMenu("Gestión de vehículos")) {
				case "Nuevo Vehículo":
					agencia.agregarVehiculo();
					break;
				case "Modificar datos Vehículo":
					agencia.seleccionarVehiculo().modificarVehiculo(agencia);
					break;
				case "Ver datos vehículo":
					JOptionPane.showMessageDialog(null, agencia.seleccionarVehiculo().toString());
					break;
				case "Nuevo Alquiler":
					Cliente cliente=agencia.seleccionarCliente();
					Vehiculo vehiculo=agencia.seleccionarVehiculoDisponible();
					Alquiler alquiler=cliente.cargarAlquiler(agencia, vehiculo);
					if (alquiler!=null) {
					alquiler.setCliente(cliente);
						String confirmar=Funciones.seleccionMenu("Confirmación: " + alquiler.toString());
						if(confirmar.equals("Confirmar")) {
					cliente.getListaAlquiler().add(alquiler);
					vehiculo.getListaAlquiler().add(alquiler);	
					vehiculo.setDisponibilidad(false);
					JOptionPane.showMessageDialog(null, "Alquiler realizado correctamente");
					} else {
						JOptionPane.showMessageDialog(null, "El alquiler se ha cancelado");
						}
					}
					break;
				case "Simular Alquiler":
					funciones.costoAlquiler(agencia);
					break;
				case "Historial de alquiler":
					agencia.seleccionarVehiculo().verAlquiler();
					break;
				}
				}
				break;			
		}
		} while (!menuPrincipal.equals("Salir"));
		
		agencia.despedida();		
		
	}
}
