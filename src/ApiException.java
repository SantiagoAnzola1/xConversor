
public class ApiException extends Exception {

	private int codigoRespuesta;

	public ApiException(int codigoRespuesta, String mensaje) {
		super(mensaje);
		this.codigoRespuesta = codigoRespuesta;
	}

	public int getCodigoRespuesta() {
		return codigoRespuesta;
	}
}
