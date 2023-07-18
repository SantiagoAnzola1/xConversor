import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class FuncionesConversor {

	public Icon ico(String path, int w, int h) {
		Icon img = new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage().getScaledInstance(w, h,
				java.awt.Image.SCALE_SMOOTH));
		return img;
	}

	// Funcion para crear JOptionPane
	public int createMessageJPane(String message, int type) {
		JFrame frame = new JFrame();
		if (type == 0) {
			JOptionPane.showMessageDialog(frame, message, "ERROR", JOptionPane.PLAIN_MESSAGE,
					ico("images/warning2.png", 32, 32));
		} else if (type == 3) {
			int salida = JOptionPane.showOptionDialog(frame, message, "Salir", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE, ico("images/warning2.png", 32, 32),
					new Object[] { "Si", "No", "Cancelar" }, "Si");
			System.out.println(salida);
			return salida;
		}
		return -1;
	}

	// Posicion para dar espacio al resultado de intercambio
	public void UbicationButtons(JPanel btnConvertir, JLabel lblCopiar, JLabel lblRestear, int organiza) {
		if (organiza == 0) {
			btnConvertir.setBounds(227, 291, 115, 35);
			lblCopiar.setBounds(352, 290, 24, 36);
			lblRestear.setBounds(383, 290, 29, 36);
		} else if (organiza == 1) {
			btnConvertir.setBounds(227, 261, 115, 35);
			lblCopiar.setBounds(352, 260, 24, 36);
			lblRestear.setBounds(383, 260, 29, 36);
		}
	}

	// Se inclyen `,` como signos de separacion de miles, hasta 5 decimales
	public static String formatNumber(double number) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		symbols.setGroupingSeparator(',');
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.#####", symbols);
		return decimalFormat.format(number);
	}


	public void dangerText(JLabel danger, JLabel icono, String text) {
		danger.setForeground(new Color(200, 0, 0));
		// Se muestra el texto advertencia en la parte inferior baja
		icono.setVisible(true);
		danger.setText(" " + text);

	}

	public String ConvertirUnidades(Combobox comboBox1, Combobox comboBox2, JTextField tfCantidad, JPanel btnConvertir,
			JLabel lblCopiar, JLabel lblRestear, JLabel lblinfo1, JLabel lblinfo2) {

		// Al oprimir - Boton de convertir
		String cboxInicial = (String) comboBox1.getSelectedItem();// Se obtiene el valor del comboBox inicial
		String cboxObjetivo = (String) comboBox2.getSelectedItem();// Se obtiene el valor del comboBox Objetivo

		// Se evalua si loa campos son validos
		// -Si algun Combox sigue en su poscion por default, es decir no se a
		// seleccionado ninguna Opcion
		if (comboBox1.getSelectedIndex() == -1 || comboBox2.getSelectedIndex() == -1) {
			createMessageJPane("Campos de cambio vacios, ingrese las unidades a convertir", 0);
			// -Si los dos campos de ComboBox son iguales, es decir contiene la misma dvisa
		} else if (cboxInicial.equals(cboxObjetivo)) {
			createMessageJPane("Misma divisa en ambos campos, ingrese las unidades a convertir ", 0);
			// -Si el campo de cantidad es vacio, ya que sin cantidad no se puede determinar
			// la conversion
		} else if (tfCantidad.getText().isEmpty()) {
			createMessageJPane("Cantidad vacia, ingrese una cantidad valida", 0);
			// -En caso de que los campos sean validos
		} else {
			// Se ubican los botones para dar espacio al resultado
			UbicationButtons(btnConvertir, lblCopiar, lblRestear, 0);
			// Se obtienen los valores de los campos
			double cantidad = Double.parseDouble(tfCantidad.getText());
			ConversorUnidadesLongitud unidadInicial = ConversorUnidadesLongitud.getUnidadBynombre(cboxInicial);
			System.out.println(unidadInicial);
			ConversorUnidadesLongitud unidadObjetivo = ConversorUnidadesLongitud.getUnidadBynombre(cboxObjetivo);
			System.out.println(unidadObjetivo);
			BigDecimal resultado = BigDecimal.valueOf(unidadInicial.getConversionTo(unidadObjetivo, cantidad));
			System.out.println(resultado);

			BigDecimal ResultIndividualBigD = BigDecimal.valueOf(unidadInicial.getConversionTo(unidadObjetivo, 1));
			String roundedValue;
			if (resultado.stripTrailingZeros().scale() <= 5) {
				roundedValue = resultado.round(new MathContext(6, RoundingMode.HALF_UP)).stripTrailingZeros()
						.toPlainString();
			} else {
				roundedValue = resultado.toString();
			}

			String info1 = 1 + " " + unidadInicial.getNombre() + " = " + ResultIndividualBigD + " "
					+ unidadObjetivo.getNombre();
			String conversion = (cantidad) + " " + unidadInicial.getAbreviacion() + " = " + resultado + " "
					+ unidadObjetivo.getAbreviacion();
			// Se da el valor a copiar, en caso de oprimior el boton de copiar
//			valorAcopiar1 = (roundedValue);
			// Se pone el resultado en los campos esstablecidos
			lblinfo1.setText(info1);
			lblinfo2.setText(conversion);

			return roundedValue;
		}
		return null;

	}

	public String CambiarDivisaApi(Combobox comboBox1, Combobox comboBox2, JTextField tfCantidad, JPanel btnConvertir,
			JLabel lblCopiar, JLabel lblRestear, JLabel lblinfo1, JLabel lblinfo2, JLabel lblCargando) {

		CambioMonedaApi cm = new CambioMonedaApi();// Instancia clase de obtencion API
		String cboxInicial = (String) comboBox1.getSelectedItem();// Se obtiene el valor del comboBox inicial
		String cboxObjetivo = (String) comboBox2.getSelectedItem();// Se obtiene el valor del comboBox Objetivo

		// Se evalua si loa campos son validos
		// -Si algun Combox sigue en su poscion por default, es decir no se a
		// seleccionado ninguna Opcion
		if (comboBox1.getSelectedIndex() == -1 || comboBox2.getSelectedIndex() == -1) {
			createMessageJPane("Campos de cambio vacios, ingrese las divisas a convertir", 0);
			// -Si los dos campos de ComboBox son iguales, es decir contiene la misma dvisa
		} else if (cboxInicial.equals(cboxObjetivo)) {
			createMessageJPane("Misma divisa en ambos campos, ingrese las divisas a convertir ", 0);
			// -Si el campo de cantidad es vacio, ya que sin cantidad no se puede determinar
			// la conversion
		} else if (tfCantidad.getText().isEmpty()) {
			createMessageJPane("Cantidad vacia, ingrese una cantidad valida", 0);
			// -En caso de que los campos sean validos
		} else {

			// Se ubican los botones para dar espacio al resultado
			UbicationButtons(btnConvertir, lblCopiar, lblRestear, 0);
			// Se obtienen los valos de los campos
			double cantidad = Double.parseDouble(tfCantidad.getText());
			// Se llama a funcion que retorna el valor del intercambio entre divisas en la
			// Clase `CambioMoneda`
			double resultado = cm.getIntercambio(cboxInicial, cboxObjetivo, cantidad);
			
			// Se obtiene la descricion de las divisas, es decir el nombre sin abreviacion
			// de la divisa
			String descriptionInicial = cm.getDescriptionBySymbol(cboxInicial);
			String descriptionObjetivo = cm.getDescriptionBySymbol(cboxObjetivo);
			// Se formate el resultado para mostar mas claramente la separacion de miles de
			// los resultados
			String formatResultado = formatNumber((resultado));
			String formatResultadoDesc = formatNumber((resultado / cantidad));
			// Se concatena la forma de mostar el resultado obtenido
			String info1 = 1 + " " + descriptionInicial + " = " + formatResultadoDesc + " " + descriptionObjetivo;
			String conversion = formatNumber(cantidad) + " " + cboxInicial + " = " + formatResultado + " "
					+ cboxObjetivo;
			// Se da el valor a copiar, en caso de oprimior el boton de copiar
//			valorAcopiar = formatResultado;
			// Se pone el resultado en los campos esstablecidos
			lblinfo1.setText(info1);
			//Validar COnversion
			if(formatResultado.equals("-200")) {
				conversion="Error de conexion en api, verifique su conexion a internet";
			}
			lblinfo2.setText(conversion);

			return formatResultado;
		}

		return null;
	}

	public void restButton(String valorAcopiar, JLabel Info2_1, JLabel Info1_1, JTextField tfCantidad,
			Combobox comboBox_1_1_1_1, Combobox comboBox_1_1_2, JLabel lblConvertir_1, JPanel btnConvertir_1,
			JLabel lblCopiar, JLabel lblRestear) {
		valorAcopiar = null;
		Info2_1.setText(null);
		Info1_1.setText(null);
		tfCantidad.setText(null);
		comboBox_1_1_1_1.setSelectedIndex(-1);
		comboBox_1_1_2.setSelectedIndex(-1);
		lblConvertir_1.requestFocus();
		UbicationButtons(btnConvertir_1, lblCopiar, lblRestear, 1);
	}

	public void intercambioBtn(Combobox de, Combobox a) {
		int indexDe = de.getSelectedIndex();
		int indexA = a.getSelectedIndex();

		if (indexDe == -1 || indexA == -1) {
			createMessageJPane("No se puede intercambiar campos vacios", 0);
		} else {
			de.setSelectedIndex(indexA);
			a.setSelectedIndex(indexDe);
		}
	}

	public void cambiarPestanas(JPanel panel, JLayeredPane layeredPane) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public void copyToClipboard(String text) {
		StringSelection selection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		if (text == null) {
			System.out.println("Esta vacio");
		} else {
			clipboard.setContents(selection, null);
		}
		
	}
	public static boolean esNumerico(String valor){     
	    try{
	        if(valor!= null){
	            Integer.parseInt(valor);
	        }
	    }catch(NumberFormatException nfe){
	         return false; 
	    }
	    return false;
	}

}
