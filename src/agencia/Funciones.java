package agencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class Funciones {
	
	public static String[] menu(String tipoMenu) {
		String tipo[]= tipoMenu.split(":");
		switch(tipo[0]) {
		case "Menu principal":
			String[] menu= {"Gestión de clientes", "Gestión de vehículos", "Salir"};
			return menu;
		case "Gestión de clientes": 
			String[] menuCliente = {"Nuevo Cliente", "Modificar Cliente", "Ver datos cliente",
					"Nuevo Alquiler", "Simular Alquiler", "Historial de alquiler", "Finalizar un alquiler", "Volver"};
			return menuCliente;
		case "Gestión de vehículos":
			String[] menuVehiculos = {"Nuevo Vehículo", "Modificar datos Vehículo",
					"Ver datos vehículo", "Nuevo Alquiler", "Simular Alquiler", "Historial de alquiler", "Volver"};
			return menuVehiculos;
		case "Modificar Cliente":
			String[] menuModificarCliente= {"Modificar nombre", "Modificar dni", "Modificar licencia", "Volver"};
			return menuModificarCliente;
		case "Modificar Auto":
			String[] menuModificarAuto= {"Modificar marca", "Modificar patente", "Modificar tarifa", "Modificar cantidad de puertas", "Modificar tipo de transmision", "Volver"};
			return menuModificarAuto;
		case "Modificar Moto":
			String[] menuModificarMoto= {"Modificar marca", "Modificar patente", "Modificar tarifa", "Modificar número de cilindrada", "Volver"};
			return menuModificarMoto;
		case "Licencia de Moto":
			String[] menuLicenciaMoto= {"Sin licencia de moto", "A1", "A2", "A3"};
			return menuLicenciaMoto;
		case "Licencia de Auto":
			String[] menuLicenciaAuto= {"Sin licencia de auto", "B1", "B2"};
			return menuLicenciaAuto;
		case "Tipo de vehículo":
			String[] tipoVehiculo = {"Auto","Moto"};
			return tipoVehiculo;
		case "Confirmación":
			String[] confirmacion = {"Confirmar", "Cancelar"};
			return confirmacion;
		}
		return null;
	}
	public static String seleccionMenu(String tipo) {
		String[] menu=menu(tipo);
		int menuNum = JOptionPane.showOptionDialog(null, tipo, null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, menu, menu);
		while (menuNum==-1) {
			menuNum = JOptionPane.showOptionDialog(null, tipo, null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, menu, menu);
		}
		return menu[menuNum];
	}
	
	public static String validarString(String tituloJOP) {
		String validado=JOptionPane.showInputDialog(tituloJOP);
		while (validado==null||validado.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Ingrese un valor válido");
			validado=JOptionPane.showInputDialog(tituloJOP);
		}
		return validado.trim();
	}
	
	public static int validarInt(String tituloJOP, int min, int maximo) {
		boolean valido = false;
		String paraParsear = "";
		int numero=0;
		while(!valido) {
			paraParsear = JOptionPane.showInputDialog(tituloJOP);
			if (paraParsear == null) {
				paraParsear = "";
			}
			try {
				numero=Integer.parseInt(paraParsear.trim());
				if (numero>=min && numero<=maximo) {
					valido=true;
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese un número válido");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Ingrese un número válido");
			}
		}
		return numero;
	}
	
	
	public static double validarDoublePositivo(String tituloJOP) {
		boolean valido = false;
		String paraParsear = "";
		double numero=0;
		while(!valido) {
			paraParsear = JOptionPane.showInputDialog(tituloJOP);
			if (paraParsear == null) {
				paraParsear = "";
			}
			try {
				numero=Double.parseDouble(paraParsear.trim());
				if (numero>0) {
					valido=true;
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese un número válido");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Ingrese un número válido");
			}
		}
		return numero;
	}
	
	public static int[] validarFecha() {
		boolean valido = false;
		String paraParsear = "";
		int[] fechaParseada = new int[3];
		while(!valido) {
			paraParsear = JOptionPane.showInputDialog("Ingrese la fecha en formato AAAA-MM-DD");	
			if (paraParsear == null) {
				paraParsear = "";
			}
			try {
				String[] paraParsearArray = paraParsear.split("-");
				if (paraParsearArray.length==3) {
				int anio=Integer.parseInt(paraParsearArray[0].trim());
				int mes=Integer.parseInt(paraParsearArray[1].trim());
				int dia=Integer.parseInt(paraParsearArray[2].trim());
				
				if(anio>1950 && anio<2150 && mes > 0 && mes<13 && dia >0 && dia <32) {
					valido=true;
					fechaParseada[0]=anio;
					fechaParseada[1]=mes;
					fechaParseada[2]=dia;
				} else {
					JOptionPane.showMessageDialog(null, "Fecha inválida");
				}
			} else { 
				JOptionPane.showMessageDialog(null, "Fecha inválida");
			}
				} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Fecha inválida");
			}
		}
		return fechaParseada;
	}
	
	public void clientesTesteo(Agencia a) {
		Cliente prueba1 = new Cliente("Juan", 40734792, "B2/A3");
		Cliente prueba2 = new Cliente("Micaela", 39734792, "B2/A3");
		Cliente prueba3 = new Cliente("Maxi", 40734722, "Sin licencia de auto/A3");
		a.getListaClientes().add(prueba1);
		a.getListaClientes().add(prueba2);
		a.getListaClientes().add(prueba3);
	}
	
	public void autosTesteo(Agencia a) {
		Moto prueba1 = new Moto("A123BCD", "Honda", 300, 150);
		Moto prueba2 = new Moto("B456EFC", "Yamaha", 400, 650);
		Moto prueba3 = new Moto("C752ABC", "Kawasaki", 500, 400);

		Auto prueba5 = new Auto("AD397ZC", "Renault", 500, 5, "manual");
		Auto prueba4 = new Auto("AB123CD", "Toyota", 600, 3, "automatico");
		Auto prueba6 = new Auto("ZC234QW", "Peugeot", 560, 5,"automatico");
		
		a.getListaVehiculos().add(prueba1);
		a.getListaVehiculos().add(prueba2);
		a.getListaVehiculos().add(prueba3);
		a.getListaVehiculos().add(prueba4);
		a.getListaVehiculos().add(prueba5);
		a.getListaVehiculos().add(prueba6);
	}
	
	public void alquilerTesteo(Agencia a) {
		Cliente cliente1=a.getListaClientes().get(0);
		Vehiculo vehiculo1= a.getListaVehiculos().get(0);
		Vehiculo vehiculo4= a.getListaVehiculos().get(3);
		
		Alquiler alquiler1 = new Alquiler(LocalDate.of(2024, 12, 3), 3, 1000, cliente1, vehiculo1);
		cliente1.getListaAlquiler().add(alquiler1);
		vehiculo1.getListaAlquiler().add(alquiler1);
		
		Alquiler alquiler2 = new Alquiler(LocalDate.of(2025, 11, 5), 5, 1000, cliente1, vehiculo4);
		cliente1.getListaAlquiler().add(alquiler2);
		vehiculo4.getListaAlquiler().add(alquiler2);
		
		Cliente cliente2=a.getListaClientes().get(1);
		Vehiculo vehiculo2= a.getListaVehiculos().get(1);
		Vehiculo vehiculo5= a.getListaVehiculos().get(4);
		
		Alquiler alquiler3 = new Alquiler(LocalDate.of(2026, 1, 2), 7, 1000, cliente2, vehiculo2);
		cliente2.getListaAlquiler().add(alquiler3);
		vehiculo2.getListaAlquiler().add(alquiler3);
		
		Alquiler alquiler4 = new Alquiler(LocalDate.of(2025, 12, 1), 15, 1000, cliente2, vehiculo5);
		alquiler4.setEstado("ACTIVO");
		cliente2.getListaAlquiler().add(alquiler4);
		vehiculo5.getListaAlquiler().add(alquiler4);
		vehiculo5.setDisponibilidad(false);
		
		Cliente cliente3=a.getListaClientes().get(2);
		Vehiculo vehiculo3= a.getListaVehiculos().get(2);
		Vehiculo vehiculo6= a.getListaVehiculos().get(5);
		
		Alquiler alquiler5 = new Alquiler(LocalDate.of(2026, 5, 1), 15, 1000, cliente3, vehiculo3);
		cliente3.getListaAlquiler().add(alquiler5);
		vehiculo3.getListaAlquiler().add(alquiler5);
		
		Alquiler alquiler6 = new Alquiler(LocalDate.of(2024, 3, 3), 12, 1000, cliente3, vehiculo6);
		alquiler6.setEstado("ACTIVO");
		cliente3.getListaAlquiler().add(alquiler6);
		vehiculo6.getListaAlquiler().add(alquiler6);
		vehiculo6.setDisponibilidad(false);
	}
	
	public double costoAlquiler(Agencia a) {
		int diasSimulacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad de dias"));
		Vehiculo b=a.seleccionarVehiculo();
		double costoTotal=b.calcularCostoAlquiler(diasSimulacion);
		String vehiculo= "el auto ";
		if (b instanceof Moto) {
			vehiculo = "la moto ";
		}
		JOptionPane.showMessageDialog(null, "El costo de alquilar " + vehiculo + b.getMarca() + " " + b.getPatente() +" "+ diasSimulacion + " dias"+ " es de $" + costoTotal);
		return costoTotal;
	}

	public static int validarDni(ArrayList<Cliente> listaClientes) {
		int dni= Funciones.validarInt("Ingrese el dni del cliente",1,Integer.MAX_VALUE);
		boolean validarDni=false;
		while (!validarDni) {
			validarDni=true;
			for (Iterator<Cliente> iterator = listaClientes.iterator(); iterator.hasNext();) {
				Cliente cliente = (Cliente) iterator.next();
				cliente.getDni();
				if(dni==cliente.getDni()) {
					validarDni=false;
					JOptionPane.showMessageDialog(null, "ya existe ese dni en el sistema");
					dni= Funciones.validarInt("Ingrese el dni del cliente",1,Integer.MAX_VALUE);
				}
			}
		}
		return dni;
	}
	public static String validarPatente(ArrayList<Vehiculo> listaVehiculos) {
		String patente=Funciones.validarString("Ingrese la patente");
		boolean validarPatente=false;
		while (!validarPatente) {
			validarPatente=true;
			for (Iterator<Vehiculo> iterator = listaVehiculos.iterator(); iterator.hasNext();) {
				Vehiculo vehiculo = (Vehiculo) iterator.next();
				if(patente.equals(vehiculo.getPatente())) {
					validarPatente=false;
					JOptionPane.showMessageDialog(null, "ya existe esa patente en el sistema");
					patente=Funciones.validarString("Ingrese la patente");
				}
			}
		}
		return patente;
	}
	
	
	
	
	
}
