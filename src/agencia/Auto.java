package agencia;

import java.util.Iterator;

import javax.swing.JOptionPane;

public class Auto extends Vehiculo{
	private int cantidadPuertas;
	private String transmision;
	
	public Auto(String patente, String marca, double tarifaBaseDiaria, int cantidadPuertas, String transmision) {
		super(patente, marca, tarifaBaseDiaria);
		this.cantidadPuertas=cantidadPuertas;
		this.transmision=transmision;
	}
	
	@Override
	public String toString() {
		return "Auto "+ super.toString() + " " + cantidadPuertas + " puertas, transmision=" + transmision;
	}
	
	public int getCantidadPuertas() {
		return cantidadPuertas;
	}

	public void setCantidadPuertas(int cantidadPuertas) {
		this.cantidadPuertas = cantidadPuertas;
	}

	public String getTransmision() {
		return transmision;
	}

	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}

	@Override
	public double calcularCostoAlquiler(int dias) {	
		double costoDiario=getTarifaBaseDiaria()+getTarifaBaseDiaria()*0.15;
		double costoTotal=costoDiario*dias;
		return costoTotal;
	}
	
	@Override
	public void modificarVehiculo(Agencia agencia) {
		String menuModificar="";
		while(!menuModificar.equals("Volver")) {
		switch(menuModificar=Funciones.seleccionMenu("Modificar Auto: " + toString())) {
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
							JOptionPane.showMessageDialog(null, "ya existe esa patente en el sistema");
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
			case "Modificar cantidad de puertas":
				int cantPuertas=Funciones.validarInt("Elegir cantidad de puertas\n Cantidad de puertas: " + getCantidadPuertas(), 2, 6);
				setCantidadPuertas(cantPuertas);
				break;
			case "Modificar tipo de transmision":
				String[] tiposTransmision= {"Automático", "Manual"};
				String transmision = null;
				while(transmision==null) {
					transmision=(String)JOptionPane.showInputDialog(null, "Seleccione el tipo de transmision\n Transmisión actual: " + getTransmision(), null, 
		                JOptionPane.QUESTION_MESSAGE, null, tiposTransmision, tiposTransmision[0]);
				}
				setTransmision(transmision);
				break;
			}
		}
	}
	@Override
	public boolean validarLicencia(String licencia) {
		boolean valido=true;
		String[] licencias=licencia.split("/");
			
			if(licencias[0].equals("Sin licencia de auto")) {
				JOptionPane.showMessageDialog(null, "Este cliente no tiene licencia de auto en el sistema, no puede alquilar un auto");
				valido= false;
			}
		return valido;
	}
	
	
}
