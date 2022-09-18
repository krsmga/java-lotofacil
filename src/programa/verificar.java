package programa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class verificar {
	public static void main(String[] args) throws IOException{		
		try {
			File objFile = new File("d:/zTemp/lotofacil/resultados_todos.txt");
			Scanner objText = new Scanner(objFile);
			ArrayList<String[]> arrLoto = new ArrayList();

			// Lê o arquivo texto e cria um Array para armazenar e manipular os resultado
			objText.useDelimiter(System.getProperty("line.separator"));
			while (objText.hasNext()) {
				arrLoto.add(objText.next().split(" "));
			}
			objText.close();
			
			// Cria um Array para comparação dos resultados
			int[][] arrRslt = new int[arrLoto.size()][26];
			String[][] arrIndx = new String[arrLoto.size()][2];
			int numValu;
			
			for (int nI = 0; nI < arrLoto.size(); nI++) {
				arrIndx[nI][0] = arrLoto.get(nI)[0];
				arrIndx[nI][1] = arrLoto.get(nI)[1];
				
				for (int nJ = 1; nJ <= 15; nJ++) {
					numValu = Integer.parseInt(arrLoto.get(nI)[nJ+1]);
					if (numValu % 2 == 0) { // Par
						arrRslt[nI][numValu] = 2;
					} else {
						arrRslt[nI][numValu] = 1;						
					}
				}
			}
			
			
			// Le o arquivo texto de apostas
			File objFileApst = new File("d:/zTemp/lotofacil/aposta.txt");
			Scanner objTextApst = new Scanner(objFileApst);
			ArrayList<String[]> arrApst = new ArrayList();

			// Lê o arquivo texto e cria um Array para armazenar e manipular os resultado
			objTextApst.useDelimiter(System.getProperty("line.separator"));
			while (objTextApst.hasNext()) {
				arrApst.add(objTextApst.next().split(" "));
			}
			objTextApst.close();

			FileWriter objAposta = new FileWriter("d:/zTemp/lotofacil/verificacao.txt");
			PrintWriter objTextLine = new PrintWriter(objAposta);
			
			int numAcerto;
			int numDezena;
			for (int nI = 0; nI < arrApst.size(); nI++) {
				for (int nJ = 0; nJ < arrRslt.length; nJ++) {
					numAcerto = 0;
					for (int nK = 0; nK < 15; nK++) {
						numDezena = Integer.parseInt(arrApst.get(nI)[nK]);
						if (arrRslt[nJ][numDezena] > 0) {
							numAcerto++;
						}
					}
					if (numAcerto >= 11) {
						System.out.println(String.format("%05d",Integer.parseInt(arrIndx[nJ][0])) + " " + arrIndx[nJ][1].replaceAll("[(|)]", "") + " " + String.format("%02d",numAcerto) + " " + String.format("%02d",nI+1));
						objTextLine.printf(String.format("%05d",Integer.parseInt(arrIndx[nJ][0])) + " " + arrIndx[nJ][1].replaceAll("[(|)]", "") + " " + String.format("%02d",numAcerto) + " " + String.format("%02d",nI+1));
						objTextLine.println("");
					}
				}
				
				
			}
			objTextLine.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
