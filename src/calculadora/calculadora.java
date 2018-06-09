package calculadora;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class calculadora {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Marco Marcocalculadora = new Marco();
		Marcocalculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Marcocalculadora.setVisible(true);

	}

}

class Marco extends JFrame {

	public Marco() {

		setTitle("Calculadora by MCSI by fia");

		setBounds(500, 300, 450, 300);

		Lamina lamina1 = new Lamina();

		add(lamina1);

	}

}

class Lamina extends JPanel {

	private String UltimaOperacion = "";
	private JPanel lamina2 = new JPanel();
	private JButton Pantalla = new JButton("0");
	private boolean primera = true;
	private boolean primera2 = true;
	private Double Acum = 0d;

	public Lamina() {
		primera = true;
		setLayout(new BorderLayout());
		// JButton Pantalla = new JButton ("0");
		Pantalla.setEnabled(false);
		add(Pantalla, BorderLayout.NORTH);

		lamina2.setLayout(new GridLayout(4, 4));

		String orden = new String("789/456*123+0.=-00");
		ActionListener evento = new Accion();
		AgregarBotones(orden, evento);
		add(lamina2, BorderLayout.CENTER);

	}

	private void AgregarBotones(String x, ActionListener y) {
		// System.out.println(x);
		String tecla = "";
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				tecla = Character.toString(x.charAt(0));
				JButton boton = new JButton(tecla);
				lamina2.add(boton);
				boton.addActionListener(y);
				x = x.substring(1);
			}

	}

	private void Calcular(Double x, String y) {
		// System.out.println(y);
		switch (y) {
		case "/":
			if (x != 0) {
				Acum /= x;
			} else
				System.out.println("No se puede dividir por 0");
			break;
		case "*":
			Acum *= x;
			break;
		case "-":
			Acum -= x;
			break;
		case "+":
			Acum += x;
			break;
		case "=":
			Pantalla.setText("" + Acum);
			break;
		default:
			System.out.println("Excepcion");
			break;
		}

	}

	private class Accion implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String entrada = e.getActionCommand();
			if (esUnaOperaciónLogica(e)) {
				if (entrada.equals("=")) {
					Calcular(Double.parseDouble(Pantalla.getText()), UltimaOperacion);
					Pantalla.setText(Acum.toString());
					UltimaOperacion = "";

				} else {
					UltimaOperacion = entrada;
					Acum = Double.parseDouble(Pantalla.getText());
				}
			}

			else {// Es un número
				if (primera) {
					Pantalla.setText(entrada);
					primera = false;
					Acum = Double.parseDouble(entrada);
					return;
				}

				if (UltimaOperacion.isEmpty()) {
					Pantalla.setText(Pantalla.getText() + entrada);
					return;
				} else {
					Pantalla.setText(entrada);
				}

				if (entrada.equals(".") && Pantalla.getText().contains(".")) {
					System.out.println("Error, no se puede poner mas de una coma");
				}
			}
		}

		private boolean esUnaOperaciónLogica(ActionEvent entrada) {
			List<String> operacionesLogicas = new ArrayList<String>();
			operacionesLogicas.add("+");
			operacionesLogicas.add("-");
			operacionesLogicas.add("*");
			operacionesLogicas.add("/");
			operacionesLogicas.add("=");
			return operacionesLogicas.contains(entrada.getActionCommand());
		}
	}

}
