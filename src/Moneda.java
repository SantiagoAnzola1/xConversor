
public class Moneda {
	double tasaCambio;
	String divisa;
	String abreviacion;

	public Moneda(String divi, String abreviacion, double tasaCambio) {
		this.divisa = divi;
		this.tasaCambio = tasaCambio;
		this.abreviacion = abreviacion;
	}

	public double getTasaCambio() {
		return this.tasaCambio;
	}

	public void setTasaCambio(double valor) {
		this.tasaCambio = valor;
	}

	public String getDivisa() {
		return this.divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	public String getAbreviacion() {
		return abreviacion;
	}

	public void setAbreviacion(String abreviacion) {
		this.abreviacion = abreviacion;
	}

	public double cambioMoneda(double valor, Moneda divisaObjetivo) {
		double cambio = valor * (divisaObjetivo.getTasaCambio() / this.tasaCambio);
		// Se redondea el resultado con 2 decimales
//			return redondearDecimales (cambio,2);
		return cambio;
	}

	public double cambioMoneda(double valor, Moneda divisaInicial, Moneda divisaObjetivo) {

		double cambio = (valor * divisaObjetivo.getTasaCambio()) / divisaInicial.getTasaCambio();
		// Se redondea el resultado con 4 decimales
//			return redondearDecimales (cambio,4);
		return cambio;
	}

	public static double redondearDecimales(double valorInicial, int numeroDecimales) {
		double parteEntera, resultado;
		resultado = valorInicial;
		parteEntera = Math.floor(resultado);
		resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
		resultado = Math.round(resultado);
		resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
		return resultado;
	}

}
