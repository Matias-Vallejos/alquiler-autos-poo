package agencia;

import java.util.Iterator;

import javax.swing.JOptionPane;

public class Moto extends Vehiculo{
	private int cilindrada;
	
	public Moto(String patente, String marca, double tarifaBaseDiaria, int cilindrada) {
		super(patente, marca, tarifaBaseDiaria);
		this.cilindrada=cilindrada;
	}
	
	@Override
	public String toString() {
		return "Moto "+ super.toString() + " cilindrada: " + cilindrada + "cc";
		}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	@Override
	public double calcularCostoAlquiler(int dias) {	
		double costoFijo = 0;
		if (getCilindrada()>250) {
			costoFijo= 100;
		}
		double costoTotal=(costoFijo+getTarifaBaseDiaria())*dias;
		return costoTotal;
	}
	
	@Override
	public void modificarVehiculo(Agencia agencia) {
		String menuModificar="";
		while(!menuModificar.equals("Volver")) {
		switch(menuModificar=Funciones.seleccionMenu("Modificar Moto: " + toString())) {
		case "Modificar marca":
			String marca=Funciones.validarString("Elegir nueva marca\n Marca actual: " + getMarca());
			setMarca(marca);
			break;

		case "Modificar patente":
			String patente=Funciones.validarString("Elegir nueva patente\n Patente actual: " + getPatente());
			boolean validarPatente=false;
			while (!validarPatente) {
				validarPatente=true;
				for (Iterator<Vehiculo> iterator = agencia.getListaVehiculos().iterator(); iterator.hasNext();) {
					Vehiculo vehiculo = (Vehiculo) iterator.next();
					if (patente==getPatente()){
					} else if(patente.equals(vehiculo.getPatente())) {
						validarPatente=false;
						JOptionPane.showMessageDialog(null, "ya existe ese dni en el sistema");
						patente=Funciones.validarString("Elegir nueva patente\n Patente actual: " + getPatente());
					}
				}
		}
			setPatente(patente);
			break;
		case "Modificar tarifa": 
			double tarifa=Funciones.validarDoublePositivo("Elegir nueva tarifa\n Tarifa actual: " + getTarifaBaseDiaria());
			setTarifaBaseDiaria(tarifa);
			break;
			case "Modificar número de cilindrada":
				int cilindrada=Funciones.validarInt("Elegir número de cilindrada\n actualmente " + getCilindrada() + "cc", 1, 3000);
				setCilindrada(cilindrada);
				break;
			}
		}
	}
	@Override
	public boolean validarLicencia(String licencia) {
		boolean valido=true;
		String[] licencias=licencia.split("/");

			int cilindrada=getCilindrada();
				if(licencias[1].equals("Sin licencia de moto")) {
				JOptionPane.showMessageDialog(null, "Este cliente no tiene licencia de moto en el sistema, no puede alquilar una moto");
				valido=false;
			} else if (licencias[1].equals("A1")&&cilindrada>50) {
				JOptionPane.showMessageDialog(null, "Este cliente tiene licencia A1, puede alquilar motos de hasta 50cc");
				valido=false;
			}else if (licencias[1].equals("A2")&&cilindrada>300) {
				JOptionPane.showMessageDialog(null, "Este cliente tiene licencia A2, puede alquilar motos de hasta 300cc");
				valido=false;
			}
		return valido;
	}
	
}
