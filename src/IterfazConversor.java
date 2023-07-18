import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class IterfazConversor extends javax.swing.JFrame {

	private JPanel contentPane;

	private final Label lblCerrar = new Label("X");
	private JTextField txtCantidad;
	private JTextField txtCantidad_1;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private JTextField txtCantidad_2;
	private JTextField tfCantidad;
	private JTextField tfCantidad1;
	private String valorAcopiar = null;
	private String valorAcopiar1 = null;
	int x;
	int y;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IterfazConversor frame = new IterfazConversor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public IterfazConversor() {

		// Instancia FuncionesConversor
		FuncionesConversor funConversor = new FuncionesConversor();

		layeredPane.setToolTipText(null);
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(0, 0, 0));
		setSize(622, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setToolTipText(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPane);
		setIconImage(new ImageIcon(getClass().getResource("/images/logoConversor.png")).getImage());
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);

		JPanel Jpanel = new JPanel();
		Jpanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		Jpanel.setBorder(null);
		Jpanel.setForeground(Color.BLACK);
		Jpanel.setToolTipText(null);
		Jpanel.setBackground(Color.WHITE);
		Jpanel.setLayout(null);
		this.getContentPane().add(Jpanel);
		Jpanel.setToolTipText(null);
		JWindow window = new JWindow();

		JPanel jPtopBar = new JPanel();
		JSeparator jSTopseparator = new JSeparator();
		jSTopseparator.setVisible(false);
		jSTopseparator.setForeground(SystemColor.inactiveCaption);
		jSTopseparator.setBackground(SystemColor.inactiveCaption);
		jSTopseparator.setBounds(0, 22, 622, 1);
		jPtopBar.add(jSTopseparator);
		// Desplazamiento de la pestaña al arrastrar el topBar
		jPtopBar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		jPtopBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xx = e.getXOnScreen();
				int yy = e.getYOnScreen();

				setLocation(xx - x, yy - y);
			}

		});
		jPtopBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				x = e.getX();
				y = e.getY();
			}

			@Override
			public void mouseEntered(MouseEvent e) {

				jSTopseparator.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {

				jSTopseparator.setVisible(false);
			}
		});

		lblCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCerrar.setBackground(new Color(255, 0, 0));
				lblCerrar.setForeground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblCerrar.setBackground(new Color(255, 255, 255));
				lblCerrar.setForeground(new Color(0, 0, 0));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int salida = funConversor.createMessageJPane("¿Estas seguro que quieres abandonar el progrma?", 3);
				if (salida == 0) {
					System.exit(0);
				} else {
					lblCerrar.setBackground(new Color(255, 255, 255));
					lblCerrar.setForeground(new Color(0,0,0));
				}
			}
		});
		lblCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCerrar.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblCerrar.setAlignment(Label.CENTER);
		lblCerrar.setBounds(585, 0, 37, 22);
		Jpanel.add(lblCerrar);
		jPtopBar.setBackground(new Color(255, 255, 255));
		jPtopBar.setBounds(0, 0, 622, 23);
		Jpanel.add(jPtopBar);
		jPtopBar.setLayout(null);

		JLabel lblicon = new JLabel("");
		lblicon.setHorizontalAlignment(SwingConstants.CENTER);
		lblicon.setBounds(0, 0, 46, 23);
		jPtopBar.add(lblicon);
		lblicon.setIcon(funConversor.ico("images/logoConversor.png", 23, 23));

		JLabel lblNewLabel = new JLabel("xConversor");
		lblNewLabel.setVisible(false);
		lblNewLabel.setForeground(new Color(255, 204, 0));
		lblNewLabel.setBounds(37, 0, 93, 23);
		jPtopBar.add(lblNewLabel);

		JPanel jPMinimizar = new JPanel();
		jPMinimizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jPMinimizar.setBackground((new Color(220, 220, 220)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jPMinimizar.setBackground((new Color(255, 255, 255)));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setExtendedState(ICONIFIED);
			}
		});
		jPMinimizar.setBackground(new Color(255, 255, 255));
		jPMinimizar.setBounds(548, 0, 37, 22);
		jPtopBar.add(jPMinimizar);
		jPMinimizar.setLayout(null);

		JLabel lblMinimizarIcon = new JLabel("");
		lblMinimizarIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimizarIcon.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMinimizarIcon.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/minimizar3.png")));
		lblMinimizarIcon.setBounds(0, 0, 37, 22);
		jPMinimizar.add(lblMinimizarIcon);
		layeredPane.setBackground(new Color(255, 255, 255));
		layeredPane.setBounds(0, 23, 622, 377);
		Jpanel.add(layeredPane);

		layeredPane.setLayout(new CardLayout(0, 0));

		// Se inicializan Jpanels\
		JPanel jPMenu = new JPanel();
		jPMenu.setBackground(new Color(255, 255, 255));
		layeredPane.add(jPMenu, "name_86469158463900");
		jPMenu.setLayout(null);

		JPanel jPbtnConversorMoneda = new JPanel();
		jPbtnConversorMoneda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		jPbtnConversorMoneda.setToolTipText("Ir al Conversor Divisas");
		jPbtnConversorMoneda.setBorder(null);
		jPbtnConversorMoneda.setBackground(new Color(255, 204, 0));
		jPbtnConversorMoneda.setBounds(225, 193, 171, 35);
		jPMenu.add(jPbtnConversorMoneda);
		jPbtnConversorMoneda.setLayout(new BorderLayout(0, 0));

		JLabel lblConversorMoneda = new JLabel("Conversor Divisas");

		lblConversorMoneda.setToolTipText(null);
		lblConversorMoneda.setHorizontalAlignment(SwingConstants.CENTER);
		lblConversorMoneda.setForeground(new Color(0, 0, 0));
		lblConversorMoneda.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConversorMoneda.setAlignmentX(0.5f);
		jPbtnConversorMoneda.add(lblConversorMoneda, BorderLayout.CENTER);

		JPanel jPbtnConversorUnidades = new JPanel();
		jPbtnConversorUnidades.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jPbtnConversorUnidades.setToolTipText("Ir al Conversor de Unidades");
		jPbtnConversorUnidades.setBorder(null);
		jPbtnConversorUnidades.setBackground(Color.BLACK);
		jPbtnConversorUnidades.setBounds(225, 259, 171, 35);
		jPMenu.add(jPbtnConversorUnidades);
		jPbtnConversorUnidades.setLayout(new BorderLayout(0, 0));

		JLabel lblConversorUnidades = new JLabel("Conversor Unidades");
		lblConversorUnidades.setToolTipText(null);
		lblConversorUnidades.setHorizontalAlignment(SwingConstants.CENTER);
		lblConversorUnidades.setForeground(Color.WHITE);
		lblConversorUnidades.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConversorUnidades.setAlignmentX(0.5f);
		jPbtnConversorUnidades.add(lblConversorUnidades, BorderLayout.CENTER);

		JPanel jPFlecha1 = new JPanel();
		jPFlecha1.setVisible(false);
		jPFlecha1.setToolTipText(null);
		jPFlecha1.setBorder(null);
		jPFlecha1.setBackground(new Color(255, 204, 0));
		jPFlecha1.setBounds(395, 193, 46, 35);
		jPMenu.add(jPFlecha1);
		jPFlecha1.setLayout(new BorderLayout(0, 0));

		JLabel lblFlecha1Icon = new JLabel("");
		lblFlecha1Icon.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/right-arrow.png")));
		lblFlecha1Icon.setToolTipText(null);
		jPFlecha1.add(lblFlecha1Icon, BorderLayout.CENTER);

		JPanel jPFlecha2 = new JPanel();
		jPFlecha2.setVisible(false);
		jPFlecha2.setToolTipText(null);
		jPFlecha2.setBorder(null);
		jPFlecha2.setBackground(Color.BLACK);
		jPFlecha2.setBounds(395, 259, 46, 35);
		jPMenu.add(jPFlecha2);
		jPFlecha2.setLayout(new BorderLayout(0, 0));

		JLabel lblFlecha2Icon = new JLabel("");
		lblFlecha2Icon.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/right-arrowWhite.png")));
		lblFlecha2Icon.setToolTipText(null);
		jPFlecha2.add(lblFlecha2Icon, BorderLayout.CENTER);

		jPbtnConversorMoneda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jPFlecha1.setVisible(true);
				;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jPFlecha1.setVisible(false);
			}
		});

		jPbtnConversorUnidades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jPFlecha2.setVisible(true);
				;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jPFlecha2.setVisible(false);
			}
		});

		JLabel lblTitle = new JLabel(" xConversor");
		lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 35));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setBounds(63, 22, 549, 87);
		jPMenu.add(lblTitle);

		JLabel lblConvierteFcilmenteEntre = new JLabel(
				"<html><p>Convierte fácilmente entre diferentes unidades de longitud y realiza conversiones precisas de divisas con este programa todo en uno.</p></html>");
		lblConvierteFcilmenteEntre.setForeground(Color.GRAY);
		lblConvierteFcilmenteEntre.setHorizontalTextPosition(SwingConstants.CENTER);
		lblConvierteFcilmenteEntre.setHorizontalAlignment(SwingConstants.CENTER);
		lblConvierteFcilmenteEntre.setFont(new Font("Arial", Font.PLAIN, 13));
		lblConvierteFcilmenteEntre.setAlignmentX(0.5f);
		lblConvierteFcilmenteEntre.setBounds(115, 90, 389, 57);
		jPMenu.add(lblConvierteFcilmenteEntre);

		JLabel lblicon_1 = new JLabel("");
		lblicon_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblicon_1.setBounds(163, 36, 71, 62);
		lblicon_1.setIcon(funConversor.ico("images/logoConversor.png", 60, 60));
		jPMenu.add(lblicon_1);
		jPMenu.setVisible(true);// Menu visible por defecto

		JPanel jPConversorMoneda = new JPanel();
		layeredPane.add(jPConversorMoneda, "name_86451414251100");
		jPConversorMoneda.setVisible(false);// No visible al iniciar app

		// Se inicializan todos los elemtos del diseño para conversorMoneda
		txtCantidad_2 = new JTextField();
		Combobox comboBox_1 = new Combobox();
		JLabel danger = new JLabel("");
		tfCantidad = new JTextField();
		JLabel dangerIcon = new JLabel("");
		JSeparator separator_1 = new JSeparator();
		Combobox comboBox_2 = new Combobox();
		JLabel changeBtn_1 = new JLabel("");
		JLabel lblinfo1 = new JLabel("");
		JLabel lblinfo2 = new JLabel("");
		JPanel jPbtnConvertir_1 = new JPanel();
		JLabel lblConvertir_1 = new JLabel("Convertir");
		JLabel lblCopiar = new JLabel("");
		JLabel lblResetear = new JLabel("");
		JLabel lblHome = new JLabel("");
		JLabel lblNextPage = new JLabel("");
		JLabel lbNextPageInfo = new JLabel("<html><p>Conversor Unidades longitud</p></html>");
		JLabel lblXconversorMoneda = new JLabel("<html><p>Conversor  Divisas</p></html>");
		JLabel lblLogoMoneda = new JLabel("");
		JLabel lblCargando = new JLabel("");

		comboBox_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					valorAcopiar = funConversor.CambiarDivisaApi(comboBox_2, comboBox_1, tfCantidad, jPbtnConvertir_1,
							lblCopiar, lblResetear, lblinfo1, lblinfo2, lblCargando);
				}
			}
		});

		comboBox_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					valorAcopiar = funConversor.CambiarDivisaApi(comboBox_2, comboBox_1, tfCantidad, jPbtnConvertir_1,
							lblCopiar, lblResetear, lblinfo1, lblinfo2, lblCargando);
				}
			}
		});

		lblCargando.setVisible(false);

		JPanel jPConversorUnidadesLongitud = new JPanel();
		jPConversorUnidadesLongitud.setVisible(false);// No visible al iniciar app

		jPbtnConversorMoneda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				funConversor.cambiarPestanas(jPConversorMoneda, layeredPane);
				jPFlecha1.setVisible(false);
			}
		});

		jPbtnConversorUnidades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				funConversor.cambiarPestanas(jPConversorUnidadesLongitud, layeredPane);
				jPFlecha2.setVisible(false);
			}
		});

		jPConversorMoneda.setBackground(new Color(255, 255, 255));
		jPConversorMoneda.setToolTipText(null);
		jPConversorMoneda.setLayout(null);

		txtCantidad_2.setToolTipText(null);
		txtCantidad_2.setText("Cantidad:");
		txtCantidad_2.setHorizontalAlignment(SwingConstants.CENTER);
		txtCantidad_2.setForeground(Color.BLACK);
		txtCantidad_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCantidad_2.setEnabled(false);
		txtCantidad_2.setEditable(false);
		txtCantidad_2.setDisabledTextColor(Color.GRAY);
		txtCantidad_2.setColumns(10);
		txtCantidad_2.setBorder(null);
		txtCantidad_2.setBackground(Color.WHITE);
		txtCantidad_2.setBounds(270, 178, 86, 20);
		jPConversorMoneda.add(txtCantidad_2);

		comboBox_1.setToolTipText("Moneda objetivo");
		comboBox_1.setSelectedIndex(-1);
		comboBox_1.setLineColor(Color.BLACK);
		comboBox_1.setLabeText("a");
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "COP", "EUR", "USD", "GBP", "KRW", "JPY" }));
		comboBox_1.setSelectedIndex(-1);
		comboBox_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox_1.setBounds(339, 108, 110, 45);
		jPConversorMoneda.add(comboBox_1);

		danger.setHorizontalAlignment(SwingConstants.LEFT);
		danger.setForeground(new Color(255, 215, 0));
		danger.setFont(new Font("Tahoma", Font.PLAIN, 12));
		danger.setBounds(38, 337, 338, 29);
		danger.setToolTipText(null);
		jPConversorMoneda.add(danger);

		tfCantidad.setToolTipText("Ingrese la cantidad");
		tfCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		tfCantidad.setForeground(Color.BLACK);
		tfCantidad.setFont(new Font("Tahoma", Font.BOLD, 13));
		tfCantidad.setFocusable(true);
		tfCantidad.setColumns(10);
		tfCantidad.setBounds(new Rectangle(0, 2, 0, 0));
		tfCantidad.setBorder(null);
		tfCantidad.setAlignmentY(1.0f);
		tfCantidad.setBounds(172, 196, 277, 29);
		jPConversorMoneda.add(tfCantidad);

		dangerIcon.setVisible(false);
		dangerIcon.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/warning.png")));
		dangerIcon.setBounds(10, 337, 24, 29);
		dangerIcon.setToolTipText(null);
		jPConversorMoneda.add(dangerIcon);

		ActionListener clearTextAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				danger.setText("");
				dangerIcon.setVisible(false);
			}
		};
		Timer timer = new Timer(1000, clearTextAction);
		tfCantidad.addKeyListener(new KeyAdapter() {
			// Se valida unicamente que se escrtiban numeros, evitando letras y signos de
			// puntuacion que pueden ocacionar errores
			// al llevar a cabo las conversiones
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				// Si se oprime eliminar no retorna para evitar que ponga error
				if (c == KeyEvent.VK_BACK_SPACE) {
					return; // Boton de eliminar
				}
				if (c == KeyEvent.VK_ENTER) {
					return; // Boton de eliminar
				}
				// En caso que escriba en el campo de `Cantidad` un char
				if (!Character.isDigit(c)) {
					// Se muestra el texto advertencia en la parte inferior baja
					funConversor.dangerText(danger, dangerIcon, "Solo numeros");

					e.consume(); // se ignoran los valores
					timer.restart();// Unicamnete se muestra la advertencia por un 100ms,
									// despues se quita
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("eneter");
					valorAcopiar = funConversor.CambiarDivisaApi(comboBox_2, comboBox_1, tfCantidad, jPbtnConvertir_1,
							lblCopiar, lblResetear, lblinfo1, lblinfo2, lblCargando);

				}

			}
		});

		separator_1.setOpaque(true);
		separator_1.setForeground(Color.GRAY);
		separator_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setBounds(172, 224, 277, 3);
		separator_1.setToolTipText(null);
		jPConversorMoneda.add(separator_1);

		comboBox_2.setVerifyInputWhenFocusTarget(false);
		comboBox_2.setToolTipText("Moneda fuente");
		comboBox_2.setSelectedIndex(-1);
		comboBox_2.setLineColor(Color.BLACK);
		comboBox_2.setLabeText(" De:");
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "COP", "EUR", "USD", "GBP", "KRW", "JPY" }));
		comboBox_2.setSelectedIndex(-1);
		comboBox_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox_2.setAlignmentY(1.0f);
		comboBox_2.setBounds(172, 108, 110, 45);
		jPConversorMoneda.add(comboBox_2);

		changeBtn_1.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/intercambiar (2).png")));
		changeBtn_1.setHorizontalAlignment(SwingConstants.CENTER);
		changeBtn_1.setBounds(292, 118, 37, 35);
		changeBtn_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		changeBtn_1.setToolTipText("Intercambiar");
		jPConversorMoneda.add(changeBtn_1);
		changeBtn_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				changeBtn_1
						.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/intercambiar (2) (1).png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				changeBtn_1.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/intercambiar (2).png")));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				funConversor.intercambioBtn(comboBox_2, comboBox_1);
			}
		});

//		Info1_1.setToolTipText("null");
		lblinfo1.setHorizontalAlignment(SwingConstants.CENTER);
		lblinfo1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblinfo1.setBounds(38, 236, 562, 14);
		lblinfo1.setToolTipText(null);
		jPConversorMoneda.add(lblinfo1);

		lblinfo2.setForeground(new Color(0, 0, 0));
		lblinfo2.setHorizontalAlignment(SwingConstants.CENTER);
		lblinfo2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblinfo2.setBounds(10, 250, 602, 29);
		lblinfo2.setToolTipText(null);
		jPConversorMoneda.add(lblinfo2);

		jPbtnConvertir_1.setForeground(new Color(0, 0, 0));
		jPbtnConvertir_1.setBorder(null);
		jPbtnConvertir_1.setBackground(new Color(255, 204, 0));
		jPbtnConvertir_1.setBounds(227, 291, 115, 35);
		jPbtnConvertir_1.setToolTipText("Convertir");
		jPConversorMoneda.add(jPbtnConvertir_1);
		jPbtnConvertir_1.setLayout(new BorderLayout(0, 0));

		lblConvertir_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblConvertir_1.setForeground(new Color(0, 0, 0));
		lblConvertir_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConvertir_1.setAlignmentX(0.5f);
		lblConvertir_1.getFocusTraversalPolicy();
		lblConvertir_1.setToolTipText(null);

		lblCopiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (valorAcopiar == null) {
					funConversor.dangerText(danger, dangerIcon, "Resultado vacio, sin campo para copiar");
					timer.restart();
				} else {
					funConversor.copyToClipboard(valorAcopiar);
				}

			}
		});
		lblCopiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCopiar.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/copiar1.png")));
		lblCopiar.setToolTipText("Copiar");
		lblCopiar.setBounds(352, 290, 24, 36);
		jPConversorMoneda.add(lblCopiar);

		lblResetear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Se restean todos los campos

				funConversor.restButton(valorAcopiar, lblinfo2, lblinfo1, tfCantidad, comboBox_2, comboBox_1,
						lblConvertir_1, jPbtnConvertir_1, lblCopiar, lblResetear);
			}
		});
		lblResetear.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/redo.png")));
		lblResetear.setToolTipText("Resetear");
		lblResetear.setBounds(383, 290, 29, 36);
		jPConversorMoneda.add(lblResetear);

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				lblConvertir_1.requestFocus();
				funConversor.UbicationButtons(jPbtnConvertir_1, lblCopiar, lblResetear, 1);

			}
		});

		jPbtnConvertir_1.add(lblConvertir_1, BorderLayout.CENTER);

		jPbtnConvertir_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		jPbtnConvertir_1.addMouseListener(new MouseAdapter() {


			@Override
			public void mouseEntered(MouseEvent e) {
				jPbtnConvertir_1.setBackground(new Color(255, 223, 113));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				jPbtnConvertir_1.setBackground(new Color(255, 204, 0));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// Al oprimir - Boton de convertir

				valorAcopiar = funConversor.CambiarDivisaApi(comboBox_2, comboBox_1, tfCantidad, jPbtnConvertir_1,
						lblCopiar, lblResetear, lblinfo1, lblinfo2, lblCargando);
				System.out.println(valorAcopiar);
			}

		});

		lblHome.setBackground(new Color(0, 0, 0));
		lblHome.setToolTipText("Página principal");
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblHome.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/home 2.png")));
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHome.setBounds(10, 23, 45, 45);

		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblHome.setBackground(new Color(0, 0, 0));
				lblHome.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/home.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblHome.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/home 2.png")));
				lblHome.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				funConversor.cambiarPestanas(jPMenu, layeredPane);
				lblHome.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/home 2.png")));
			}
		});
		jPConversorMoneda.add(lblHome);

		lblNextPage.setForeground(new Color(128, 128, 128));
		lblNextPage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNextPage.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/right-arrow.png")));
		lblNextPage.setToolTipText("");
		lblNextPage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNextPage.setBackground(Color.WHITE);
		lblNextPage.setAlignmentX(0.5f);
		lblNextPage.setToolTipText("Ir al conversor de unidades de longitud");
		lblNextPage.setBounds(546, 337, 54, 29);

		jPConversorMoneda.add(lblNextPage);

		lbNextPageInfo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lbNextPageInfo.setVisible(false);
		lbNextPageInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lbNextPageInfo.setForeground(new Color(128, 128, 128));
		lbNextPageInfo.setBackground(new Color(255, 255, 255));
		lbNextPageInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbNextPageInfo.setHorizontalTextPosition(SwingConstants.CENTER);
		lbNextPageInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbNextPageInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lbNextPageInfo.setOpaque(true);
		lbNextPageInfo.setBounds(400, 337, 154, 29);
		lbNextPageInfo.setToolTipText(null);
		jPConversorMoneda.add(lbNextPageInfo);

		lblNextPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNextPage.setBackground(new Color(0, 0, 0));
				lblNextPage.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/right-arrow2.png")));
				lbNextPageInfo.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNextPage.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/right-arrow.png")));
				lbNextPageInfo.setVisible(false);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				funConversor.cambiarPestanas(jPConversorUnidadesLongitud, layeredPane);
			}
		});
//-----------------------------------------------------------------------------------------------------------------------------------------

		lblNextPage.setLabelFor(jPConversorUnidadesLongitud);
		lbNextPageInfo.setLabelFor(jPConversorUnidadesLongitud);

		lblXconversorMoneda.setHorizontalTextPosition(SwingConstants.CENTER);
		lblXconversorMoneda.setHorizontalAlignment(SwingConstants.LEFT);
		lblXconversorMoneda.setFont(new Font("Arial", Font.BOLD, 20));
		lblXconversorMoneda.setAlignmentX(0.5f);
		lblXconversorMoneda.setBounds(241, 23, 221, 45);
		jPConversorMoneda.add(lblXconversorMoneda);

		lblLogoMoneda.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoMoneda.setBounds(172, 23, 67, 45);
		lblLogoMoneda.setIcon(funConversor.ico("images/LogoConversorMoneda1.png", 45, 45));
		jPConversorMoneda.add(lblLogoMoneda);

		lblCargando.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargando.setBounds(284, 238, 72, 35);
		jPConversorMoneda.add(lblCargando);

		jPConversorUnidadesLongitud.setBackground(new Color(255, 255, 255));
		layeredPane.add(jPConversorUnidadesLongitud, "name_86465386431100");
		jPConversorUnidadesLongitud.setLayout(null);

		txtCantidad_2 = new JTextField();
		txtCantidad_2.setToolTipText(null);
		txtCantidad_2.setText("Cantidad:");
		txtCantidad_2.setHorizontalAlignment(SwingConstants.CENTER);
		txtCantidad_2.setForeground(Color.BLACK);
		txtCantidad_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCantidad_2.setEnabled(false);
		txtCantidad_2.setEditable(false);
		txtCantidad_2.setDisabledTextColor(Color.GRAY);
		txtCantidad_2.setColumns(10);
		txtCantidad_2.setBorder(null);
		txtCantidad_2.setBackground(Color.WHITE);
		txtCantidad_2.setBounds(270, 178, 86, 20);
		jPConversorUnidadesLongitud.add(txtCantidad_2);

		Combobox comboBox_12 = new Combobox();
		comboBox_12.setToolTipText("Unidad objetivo");
		comboBox_12.setSelectedIndex(-1);
		comboBox_12.setLineColor(Color.BLACK);
		comboBox_12.setLabeText("a");
		comboBox_12.setModel(new DefaultComboBoxModel(new String[] { "Kilómetro", "Metro", "Decímetro", "Centímetro",
				"Milímetro", "Micrómetro", "Nanómetro", "Milla", "Yarda", "Pie", "Pulgada", "Milla náutica" }));
		comboBox_12.setSelectedIndex(-1);
		comboBox_12.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox_12.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox_12.setBounds(339, 108, 122, 45);
		jPConversorUnidadesLongitud.add(comboBox_12);

		JLabel lbldanger1 = new JLabel("");
		lbldanger1.setHorizontalAlignment(SwingConstants.LEFT);
		lbldanger1.setForeground(new Color(255, 0, 0));
		lbldanger1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbldanger1.setBounds(38, 337, 374, 29);
		lbldanger1.setToolTipText(null);
		jPConversorUnidadesLongitud.add(lbldanger1);

		tfCantidad1 = new JTextField();
		tfCantidad1.setToolTipText("Ingrese la cantidad");
		tfCantidad1.setHorizontalAlignment(SwingConstants.CENTER);
		tfCantidad1.setForeground(Color.BLACK);
		tfCantidad1.setFont(new Font("Tahoma", Font.BOLD, 13));
		tfCantidad1.setFocusable(true);
		tfCantidad1.setColumns(10);
		tfCantidad1.setBounds(new Rectangle(0, 2, 0, 0));
		tfCantidad1.setBorder(null);
		tfCantidad1.setAlignmentY(1.0f);
		tfCantidad1.setBounds(172, 196, 277, 29);
		jPConversorUnidadesLongitud.add(tfCantidad1);
		JLabel lbldangerIcon1 = new JLabel("");
		lbldangerIcon1.setVisible(false);
		lbldangerIcon1.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/warning.png")));
		lbldangerIcon1.setBounds(10, 337, 24, 29);
		lbldangerIcon1.setToolTipText(null);
		jPConversorUnidadesLongitud.add(lbldangerIcon1);

		ActionListener clearTextAction1 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lbldanger1.setText("");
				lbldangerIcon1.setVisible(false);
			}
		};
		Timer timer1 = new Timer(2000, clearTextAction1);
		tfCantidad1.addKeyListener(new KeyAdapter() {
			// Se valida unicamente que se escrtiban numeros, evitando letras y signos de
			// puntuacion que pueden ocacionar errores
			// al llevar a cabo las conversiones
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (c == KeyEvent.VK_BACK_SPACE) {
					return; // Boton de eliminar
				}

				if (c == KeyEvent.VK_ENTER) {
					return;
				}
				int actualChar1 = (tfCantidad1.getText().length());
				System.out.println(actualChar1);
				
				
				if (!Character.isDigit(c) && c != '.' && c != '-' && c != '+' && c != 'e' && c != 'E') {

					funConversor.dangerText(lbldanger1, lbldangerIcon1, "Caracter no valido");
					e.consume(); // se ignoran los valores
					timer1.restart();// Unicamnete se muestra la advertencia por un 100ms,
					return; // despues se quita

				} else {
					try {
						Double.parseDouble(tfCantidad1.getText() + e.getKeyChar());
					} catch (NumberFormatException er) {
						System.out.println(er.getMessage());
						if (actualChar1==0||c == 'e' && c == 'E') {
							System.out.println("Sin numeros previos a la notacion cientifica e o E" + actualChar1);
							funConversor.dangerText(lbldanger1, lbldangerIcon1,
									"Sin numeros previos a la notacion cientifica, recuerde 1e+,2e-,... ");
							e.consume();
							timer1.restart();
							}
						else if (c == '-' || c == '+') {
							char prevChar = tfCantidad1.getText().charAt(tfCantidad1.getText().length() - 1);
							if (prevChar != 'e' && prevChar != 'E') {
								System.out.println("No previous e o E" + prevChar);
								funConversor.dangerText(lbldanger1, lbldangerIcon1,
										"Notación cientifica no valida, recuerde e+,e- ");
								e.consume();
								timer1.restart();
							}
							return;
						} else if(c=='e' && c=='E') {
							char prevChar = tfCantidad1.getText().charAt(tfCantidad1.getText().length() - 1);
							System.out.println(prevChar);
							if (String.valueOf(prevChar).isEmpty()) {
								System.out.println("Sin numeros previos a la notacion cientifica e o E" + prevChar);
								funConversor.dangerText(lbldanger1, lbldangerIcon1,
										"Sin numeros previos a la notacion cientifica, recuerde 1e+,2e- ");
								e.consume();
								timer1.restart();
							}
						}else if ((tfCantidad1.getText().contains("e") || tfCantidad1.getText().contains("E"))) {
							funConversor.dangerText(lbldanger1, lbldangerIcon1, "Notación cientifica ya existente");
							e.consume();
							timer1.restart();
							return;
						} else if (c != 'e' && c != 'E' && c != '-' && c != '+') {
							funConversor.dangerText(lbldanger1, lbldangerIcon1, "Caracter no valido");
							e.consume();
							timer1.restart();
							return;
						}
					}
				}
			}
		});

		JSeparator separator_2 = new JSeparator();
		separator_2.setOpaque(true);
		separator_2.setForeground(Color.GRAY);
		separator_2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		separator_2.setBackground(Color.LIGHT_GRAY);
		separator_2.setBounds(172, 224, 277, 3);
		separator_2.setToolTipText(null);
		jPConversorUnidadesLongitud.add(separator_2);

		Combobox comboBox_22 = new Combobox();

		comboBox_22.setVerifyInputWhenFocusTarget(false);
		comboBox_22.setToolTipText("Unidad fuente");
		comboBox_22.setSelectedIndex(-1);
		comboBox_22.setLineColor(Color.BLACK);
		comboBox_22.setLabeText(" De:");
		comboBox_22.setModel(new DefaultComboBoxModel(new String[] { "Kilómetro", "Metro", "Decímetro", "Centímetro",
				"Milímetro", "Micrómetro", "Nanómetro", "Milla", "Yarda", "Pie", "Pulgada", "Milla náutica" }));
		comboBox_22.setSelectedIndex(-1);
		comboBox_22.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox_22.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox_22.setAlignmentY(1.0f);
		comboBox_22.setBounds(160, 108, 122, 45);
		jPConversorUnidadesLongitud.add(comboBox_22);

		JLabel lblchangeBtn_11 = new JLabel("");
		lblchangeBtn_11.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/intercambiar (2).png")));
		lblchangeBtn_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblchangeBtn_11.setBounds(292, 118, 37, 35);
		lblchangeBtn_11.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblchangeBtn_11.setToolTipText("Intercambiar");
		jPConversorUnidadesLongitud.add(lblchangeBtn_11);
		lblchangeBtn_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblchangeBtn_11
						.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/intercambiar (2) (1).png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblchangeBtn_11
						.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/intercambiar (2).png")));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				funConversor.intercambioBtn(comboBox_22, comboBox_12);
			}
		});
		JLabel lblinfo11 = new JLabel("");
//		Info1_1.setToolTipText("null");
		lblinfo11.setHorizontalAlignment(SwingConstants.CENTER);
		lblinfo11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblinfo11.setBounds(38, 236, 562, 14);
		lblinfo11.setToolTipText(null);
		jPConversorUnidadesLongitud.add(lblinfo11);

		JLabel lblinfo22 = new JLabel("");
		lblinfo22.setForeground(new Color(0, 0, 0));
		lblinfo22.setHorizontalAlignment(SwingConstants.CENTER);
		lblinfo22.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblinfo22.setBounds(10, 250, 602, 29);
		lblinfo22.setToolTipText(null);
		jPConversorUnidadesLongitud.add(lblinfo22);

		JPanel jPbtnConvertir_2 = new JPanel();
		jPbtnConvertir_2.setForeground(new Color(0, 0, 0));
		jPbtnConvertir_2.setBorder(null);
		jPbtnConvertir_2.setBackground(new Color(255, 204, 0));
		jPbtnConvertir_2.setBounds(227, 291, 115, 35);
		jPbtnConvertir_2.setToolTipText("Convertir");
		jPConversorUnidadesLongitud.add(jPbtnConvertir_2);
		jPbtnConvertir_2.setLayout(new BorderLayout(0, 0));

		JLabel lblConvertir_2 = new JLabel("Convertir");
		lblConvertir_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblConvertir_2.setForeground(new Color(0, 0, 0));
		lblConvertir_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConvertir_2.setAlignmentX(0.5f);
		lblConvertir_2.getFocusTraversalPolicy();
		lblConvertir_2.setToolTipText(null);

		JLabel lblCopiar1 = new JLabel("");
		lblCopiar1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (valorAcopiar1 == null) {
					funConversor.dangerText(lbldanger1, lbldangerIcon1, "Resultado vacio, sin campo para copiar");
					timer.restart();
				} else {
					funConversor.copyToClipboard(valorAcopiar1);
				}
			}
		});
		lblCopiar1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCopiar1.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/copiar1.png")));
		lblCopiar1.setToolTipText("Copiar");
		lblCopiar1.setBounds(352, 290, 24, 36);
		jPConversorUnidadesLongitud.add(lblCopiar1);

		JLabel lblRestear1 = new JLabel("");
		lblRestear1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Se restean todos los campos
				funConversor.restButton(valorAcopiar1, lblinfo22, lblinfo11, tfCantidad1, comboBox_22, comboBox_12,
						lblConvertir_2, jPbtnConvertir_2, lblCopiar1, lblRestear1);
			}
		});
		lblRestear1.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/redo.png")));
		lblRestear1.setToolTipText("Resetear");
		lblRestear1.setBounds(383, 290, 29, 36);
		jPConversorUnidadesLongitud.add(lblRestear1);

		comboBox_22.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					valorAcopiar1 = funConversor.ConvertirUnidades(comboBox_22, comboBox_12, tfCantidad1,
							jPbtnConvertir_2, lblCopiar1, lblRestear1, lblinfo11, lblinfo22);
				}
			}
		});
		comboBox_12.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					valorAcopiar1 = funConversor.ConvertirUnidades(comboBox_22, comboBox_12, tfCantidad1,
							jPbtnConvertir_2, lblCopiar1, lblRestear1, lblinfo11, lblinfo22);
				}
			}
		});
		tfCantidad1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					valorAcopiar1 = funConversor.ConvertirUnidades(comboBox_22, comboBox_12, tfCantidad1,
							jPbtnConvertir_2, lblCopiar1, lblRestear1, lblinfo11, lblinfo22);
				}
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				lblConvertir_2.requestFocus();
				funConversor.UbicationButtons(jPbtnConvertir_2, lblCopiar1, lblRestear1, 1);

			}
		});

		jPbtnConvertir_2.add(lblConvertir_2, BorderLayout.CENTER);

		jPbtnConvertir_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jPbtnConvertir_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jPbtnConvertir_2.setBackground(new Color(255, 223, 113));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				jPbtnConvertir_2.setBackground(new Color(255, 204, 0));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				valorAcopiar1 = funConversor.ConvertirUnidades(comboBox_22, comboBox_12, tfCantidad1, jPbtnConvertir_2,
						lblCopiar1, lblRestear1, lblinfo11, lblinfo22);
				System.out.println(valorAcopiar);
			}
		});

		JLabel lblHome1 = new JLabel("");
		lblHome1.setBackground(new Color(0, 0, 0));
		lblHome1.setToolTipText("Página principal");
		lblHome1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblHome1.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/home 2.png")));
		lblHome1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHome1.setBounds(10, 23, 45, 45);

		lblHome1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblHome1.setBackground(new Color(0, 0, 0));
				lblHome1.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/home.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblHome1.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/home 2.png")));
				lblHome1.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				funConversor.cambiarPestanas(jPMenu, layeredPane);
				lblHome1.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/home 2.png")));
			}
		});
		jPConversorUnidadesLongitud.add(lblHome1);

		JLabel lblNextPage1 = new JLabel("");
		lblNextPage1.setForeground(new Color(128, 128, 128));
		lblNextPage1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNextPage1.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/right-arrow.png")));
		lblNextPage1.setToolTipText("");
		lblNextPage1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNextPage1.setBackground(Color.WHITE);
		lblNextPage1.setAlignmentX(0.5f);
		lblNextPage1.setToolTipText("Ir al conversor de unidades de longitud");
		lblNextPage1.setBounds(546, 337, 54, 29);

		jPConversorUnidadesLongitud.add(lblNextPage1);

		JLabel lbNextPageInfo1 = new JLabel("<html><p>Conversor Divisas</p></html>");
		lbNextPageInfo1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lbNextPageInfo1.setVisible(false);
		lbNextPageInfo1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lbNextPageInfo1.setForeground(new Color(128, 128, 128));
		lbNextPageInfo1.setBackground(new Color(255, 255, 255));
		lbNextPageInfo1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbNextPageInfo1.setHorizontalTextPosition(SwingConstants.CENTER);
		lbNextPageInfo1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbNextPageInfo1.setHorizontalAlignment(SwingConstants.CENTER);
		lbNextPageInfo1.setOpaque(true);
		lbNextPageInfo1.setBounds(444, 337, 110, 29);
		lbNextPageInfo1.setToolTipText(null);
		jPConversorUnidadesLongitud.add(lbNextPageInfo1);

		JLabel lblXconversorDeUnidades = new JLabel(
				"<html><p>Conversor &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Unidades de longitud</p></html>");
		lblXconversorDeUnidades.setHorizontalTextPosition(SwingConstants.CENTER);
		lblXconversorDeUnidades.setHorizontalAlignment(SwingConstants.CENTER);
		lblXconversorDeUnidades.setFont(new Font("Arial", Font.BOLD, 20));
		lblXconversorDeUnidades.setAlignmentX(0.5f);
		lblXconversorDeUnidades.setBounds(227, 0, 234, 90);
		jPConversorUnidadesLongitud.add(lblXconversorDeUnidades);

		JLabel lblLogoUnidades = new JLabel("");
		lblLogoUnidades.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoUnidades.setBounds(160, 23, 67, 45);
		jPConversorUnidadesLongitud.add(lblLogoUnidades);
		lblLogoUnidades.setIcon(funConversor.ico("images/LogoConversorUnidades1.png", 45, 45));
		lblNextPage1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNextPage1.setBackground(new Color(0, 0, 0));
				lblNextPage1.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/right-arrow2.png")));
				lbNextPageInfo1.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNextPage1.setIcon(new ImageIcon(IterfazConversor.class.getResource("/images/right-arrow.png")));
				lbNextPageInfo1.setVisible(false);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				funConversor.cambiarPestanas(jPConversorMoneda, layeredPane);
			}
		});

	}
}
