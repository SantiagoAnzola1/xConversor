import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CambioMonedaApi {

	FuncionesConversor funConversor=new FuncionesConversor();
	
	public double getIntercambio(String divisaInicial, String divisaObjetivo, double cantidad) {

		try {
			// Url de la API exchangerate.host => https://exchangerate.host/
//	            String apiUrl = "https://api.exchangerate.host/convert?from=EUR&to=COP";
			String apiUrl = "https://api.exchangerate.host/convert?from=" + divisaInicial + "&to=" + divisaObjetivo;
			URL url = new URL(apiUrl);

			// Inicia conexion con API
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// Codigo de respuesta
			int responseCode = connection.getResponseCode();
			// Se lee y alamcena respuesta de API
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			// Se verifica si la respuesta de conexion fue exitosa
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// Respuesta a JSON
				JSONParser parser = new JSONParser();
				JSONObject jsonResponse = (JSONObject) parser.parse(response.toString());

				// // Se accede a los campos json
				double result = (double) jsonResponse.get("result");
				System.out.println("resultado " + result);
				return cantidad * result;
			} else {
				System.out.println("Pedido API fallido: " + responseCode);
//				funConversor.createMessageJPane("Error de conexion con API, "+responseCode, 0);
				throw new ApiException(responseCode, "Pedido API fallido: " + responseCode);
			}
		} catch (IOException | ParseException | ApiException e) {
			if (e instanceof ApiException) {
				ApiException apiException = (ApiException) e;
				int responseCode = apiException.getCodigoRespuesta();
				System.out.println("Codigo respuesta: " + responseCode);
				System.out.println("API Exception: " + e.getMessage());
//				funConversor.createMessageJPane("Error de conexion con API, "+e.getMessage(), 0);
			}
			e.printStackTrace();
		}
		
		return -200;
	}

	public String getDescriptionBySymbol(String simbolo) {

		int responseCode;
		try {
			// Url de la API exchangerate.host => https://exchangerate.host/
			String apiUrl = "https://api.exchangerate.host/symbols";
			URL url = new URL(apiUrl);

			// Inicia coonexion con API
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// Codigo de respuesta
			responseCode = connection.getResponseCode();

			// Se lee y alamcena respuesta de API
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			// Se verifica si la respuesta de conexion fue exitosa
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// Respuesta a JSON
				JSONParser parser = new JSONParser();
				JSONObject jsonResponse = (JSONObject) parser.parse(response.toString());

				// Se accede a los campos json// // Se accede a los campos json
				JSONObject symbols = (JSONObject) jsonResponse.get("symbols");
				String msg = (String) jsonResponse.get("msg");
				String url1 = (String) jsonResponse.get("url");

				// Se itera cada simbolo hasta encontrar el especificado
				for (Object symbol : symbols.keySet()) {
					String symbolCode = (String) symbol;

					if (symbolCode.equals(simbolo)) {
						JSONObject symbolData = (JSONObject) symbols.get(symbolCode);
//		                	String code = (String) symbolData.get("code");
						String description = (String) symbolData.get("description");
						System.out.println("Symbol: " + symbolCode);
						System.out.println("Description: " + description);
						return description;
					}
				}
			} else {
				System.out.println("Error de conexion con API: " + responseCode);
//				funConversor.createMessageJPane("Error de conexion con API para encontrar los parametros, "+responseCode, 0);
				throw new ApiException(responseCode, "Error de conexion con API: " + responseCode);
			}

			// Cierra conexion con Api
			connection.disconnect();
			
		} catch (IOException | ParseException | ApiException e) {
			if (e instanceof ApiException) {
				ApiException apiException = (ApiException) e;
				int responseCode1 = apiException.getCodigoRespuesta();
				System.out.println("Codigo de respuesta: " + responseCode1);
				System.out.println("API Exception: " + e.getMessage());
			}
			e.printStackTrace();
			funConversor.createMessageJPane("Error de conexion con API, "+e.getMessage(), 0);
			return e.toString();
		}
		return "Ocurrio un error, codigo de respuesta: " + responseCode;

	}

}