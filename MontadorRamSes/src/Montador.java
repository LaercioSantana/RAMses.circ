import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public abstract class Montador {
	private JTextPane entrada, saida;
	public Montador(JTextPane entrada, JTextPane saida) {
		this.entrada = entrada;
		this.saida = saida;
		entrada.getDocument().addDocumentListener(new EntradaDocument());
		//saida.setEditable(false);
		//saida.setText(montar(entrada.getText()));
		
	}
	public abstract String montar(String entrada);
	class EntradaDocument implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			saida.setText(montar(entrada.getText()));
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			saida.setText(montar(entrada.getText()));
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
		}
		
	}
	public String getSaida(){
		return saida.getText();
	}
	public String getEntrada(){
		return entrada.getText();
	}
	
	
}
