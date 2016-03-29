import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextPane;


public class MontadorRamses extends Montador {
	private Map<String, Integer> dict;

	private String quebraLinha = ";";
	public MontadorRamses(JTextPane entrada, JTextPane saida) {
		super(entrada, saida);
		dict = new HashMap<String, Integer>();
		criarDicionario();
		
	}
	private void criarDicionario(){
		int instPeso = 16;
		dict.put("NOP", 0 * instPeso);
		dict.put("STR", 1 * instPeso);
		dict.put("LDR", 2 * instPeso);
		dict.put("ADD", 3 * instPeso);
		dict.put("OR", 4 * instPeso);
		dict.put("AND", 5 * instPeso);
		dict.put("NOT", 6 * instPeso);
		dict.put("SUB", 7 * instPeso);
		dict.put("JMP", 8 * instPeso);
		dict.put("JN", 9 * instPeso);
		dict.put("JZ", 10 * instPeso);
		dict.put("JC", 11 * instPeso);
		dict.put("JSR", 12 * instPeso);
		dict.put("NEG", 13 * instPeso);
		dict.put("SHR", 14 * instPeso);
		dict.put("HLT", 15 * instPeso);
		
		int modoPeso = 1;
		dict.put("DIR", 0 * modoPeso);
		dict.put("IND", 1 * modoPeso);
		dict.put("IMD", 2 * modoPeso);
		dict.put("IDX", 3 * modoPeso);
		
		int regPeso = 4;
		dict.put("A", 0 * regPeso);
		dict.put("B", 1 * regPeso);
		dict.put("X", 2 * regPeso);
		dict.put("?", 3 * regPeso);
		
	}

	@Override
	public String montar(String entrada) {
		entrada = entrada.replaceAll("\n", "");
		String linhas[] = entrada.split(quebraLinha);
		String saida = "";
		String separaKeyWord = " ";
		
		for(int i = 0; i < linhas.length;i++){
			String keyWords[] = linhas[i].split(separaKeyWord);
			
			Integer linha = 0;
			if(keyWords.length == 3 && dict.containsKey(keyWords[0]) && 
					dict.containsKey(keyWords[1]) && dict.containsKey(keyWords[2]) ){
				linha = dict.get(keyWords[0]) + dict.get(keyWords[1]) + 
						dict.get(keyWords[2]);
			}
			else if(keyWords.length == 1){
				try{
					linha = Integer.parseInt(keyWords[0]);
				}catch(Exception e){
					saida+= "erro linha: "+i;
					System.out.println("endereço erro");
					continue;
				}
			}
			else{
				saida+= "erro linha: "+i;
				continue;
			}

			saida += Integer.toHexString(linha.intValue())+"\n";
		}
		
		return saida;
	}

}
