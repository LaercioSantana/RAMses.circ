import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MontadorInterface extends JFrame {
	JTextPane entrada, saida;
	Montador montador;
	public static void main(String[] args) {
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {}
		
		new MontadorInterface();
	}
	public MontadorInterface() throws HeadlessException {
		entrada = new JTextPane();
		TextLineNumber line = new TextLineNumber(entrada);
		
		JScrollPane scrollEntrada = new JScrollPane(entrada);
		scrollEntrada.setRowHeaderView(line);
		scrollEntrada.setPreferredSize(new Dimension(300,280));
		scrollEntrada.setBorder(BorderFactory.createTitledBorder("Entrada"));
		
		saida = new JTextPane();
		saida.setEditable(false);
		TextLineNumber line2 = new TextLineNumber(saida);
		JScrollPane scrollSaida = new JScrollPane(saida);
		scrollSaida.setRowHeaderView(line2);
		scrollSaida.setPreferredSize(new Dimension(300,260));
		scrollSaida.setBorder(BorderFactory.createTitledBorder("Saida"));
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menuArquivo = new JMenu("Menu");
		JMenuItem miExport = new JMenuItem("Exportar");
		JMenuItem miAjuda = new JMenuItem("Ajuda");
		menuArquivo.add(miExport);
		menuBar.add(menuArquivo);
		menuArquivo.add(miAjuda);
		
		miExport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exportar();
			}
		});
		
		miAjuda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MontadorInterface.this, "Formato de comandos:\n"+
			"[INSTRUÇÃO REGISTRADOR MODO;] ou [ENDEREÇO;]\n"+
						"\nOnde os valores possiveis são:\n"+
			"MODO → DIR = direto, IMD = imediato, IND = indireto, IDX = indexado\n"+
			"REGISTRADOR → A = registrador A, B = registrador B, X = registrador de indice, ? = nenhum registrador selecionado\n ");
	
			}
		});

		this.getContentPane().add(scrollEntrada, BorderLayout.NORTH);
		this.getContentPane().add(scrollSaida, BorderLayout.SOUTH);
		this.setJMenuBar(menuBar);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
		montador = new MontadorRamses(entrada, saida);
	}
	private void exportar(){
		JFileChooser chooser = new JFileChooser();	
		FileFilter logisim = new FileNameExtensionFilter("Memoria Logisim", "ram");
		FileFilter source = new FileNameExtensionFilter("Codigo Fonte", "src");
		chooser.setFileFilter(logisim);
		chooser.setFileFilter(source);
		
		int result = chooser.showSaveDialog(this);
		if(result == JFileChooser.APPROVE_OPTION ){
			String fileName = chooser.getSelectedFile().getAbsolutePath();
			try{
				if(chooser.getFileFilter().getDescription() == logisim.getDescription() ){
					if(!fileName.endsWith(".ram"))
						fileName += ".ram"; 
					gravarArquivo(new File(fileName), toLogisim(montador.getSaida()));
				}
				else if(chooser.getFileFilter().getDescription() == source.getDescription() ){
					if(!fileName.endsWith(".src"))
						fileName += ".src"; 
					gravarArquivo(new File(fileName), montador.getEntrada());
				}
			}catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
	}
	private String toLogisim(String text){
		StringBuilder textMemoria = new StringBuilder("v2.0 raw\n");
		String dados[] = text.split("\n");
		for(String dado: dados){
			textMemoria.append(dado+" ");
		}
		return textMemoria.subSequence(0, textMemoria.length()).toString();
	}
    public static void gravarArquivo(File arquivo, String text) throws IOException{
        FileOutputStream stream = new FileOutputStream(arquivo);
       
        stream.write(text.getBytes(Charset.forName("UTF-8")));
        stream.close();
    }
	public MontadorInterface(GraphicsConfiguration gc) {
		super(gc);
	}

	public MontadorInterface(String title) throws HeadlessException {
		super(title);
	}

	public MontadorInterface(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

}
