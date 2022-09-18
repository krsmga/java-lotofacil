package programa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class calcular {
	
	public static void main(String[] args) throws IOException{
		try {
			File objFile = new File("d:/zTemp/lotofacil/resultados_todos.txt");
			Scanner objText = new Scanner(objFile);
			ArrayList<String[]> arrLoto = new ArrayList();

			objText.useDelimiter(System.getProperty("line.separator"));
			while (objText.hasNext()) {
				arrLoto.add(objText.next().split(" "));
			}
			objText.close();
			
			
			int[] nSoma = new int[26];			
			int nIndex = 0;	
			int[] nParImp = new int[arrLoto.size()+1];
			
			for (int nI = 0; nI < arrLoto.size(); nI++) {
				for (int nJ = 2; nJ < 17; nJ++) {
					nIndex = Integer.parseInt(arrLoto.get(nI)[nJ]);
					if (nIndex <= 25) {
						nSoma[nIndex]++;
						if (nIndex % 2 == 0) {
							nParImp[nI]++;
						}
					}
				}
			}
			System.out.println("QUANTIDADE DE VEZES QUE CADA NUMERO SAIU");
			System.out.println("");
			System.out.print("DEZENAS |");
			for (Integer nI = 1; nI <= 25; nI++) {
				System.out.print(" " + String.format("%04d", nI) + " |");
			}
			System.out.println("");
			System.out.print("-------- ");
			
			for (Integer nI = 1; nI <= 25; nI++) {
				System.out.print("------ ");
			}
			System.out.println("");
			System.out.print("QTDENUM |");
			
			for (Integer nI = 1; nI <= 25; nI++) {
				System.out.print(" " + String.format("%04d", nSoma[nI]) + " |");
			}
			
			String[] arrTemp = new String[26];
			arrTemp[0] = "000000";
			for (Integer nI = 1; nI <= 25; nI++) {
				arrTemp[nI] = String.format("%04d", nSoma[nI]) + String.format("%04d", nI);
			}
			
			Arrays.sort(arrTemp);
						
			System.out.println("\r");
			System.out.println("QUANTIDADE DE VEZES EM ORDEM CRESCENTE");
			System.out.println("");
			System.out.print("DEZENAS |");
			for (Integer nI = 1; nI <= 25; nI++) {
				System.out.print(" " + arrTemp[nI].substring(4, 8)  + " |");
			}
			
			
			System.out.println("");
			System.out.print("-------- ");
			for (Integer nI = 1; nI <= 25; nI++) {
				System.out.print("------ ");
			}
			System.out.println("");
			System.out.print("QTDENUM |");
			for (Integer nI = 1; nI <= 25; nI++) {
				System.out.print(" " + arrTemp[nI].substring(0, 4)  + " |");
			}
			
			System.out.println("");  
			System.out.print("        |  DESCARTE   |                                                                                                                                           |        FIXO        |");
			
			Integer numDes1 = Integer.parseInt(arrTemp[1].substring(4, 8));
			Integer numDes2 = Integer.parseInt(arrTemp[2].substring(4, 8));
			Integer numFix1 = Integer.parseInt(arrTemp[23].substring(4, 8));
			Integer numFix2 = Integer.parseInt(arrTemp[24].substring(4, 8));
			Integer numFix3 = Integer.parseInt(arrTemp[25].substring(4, 8));
			
			int[] arrDoze = new int[20];
			for (int nI = 0; nI < 20; nI++) {
				arrDoze[nI] = Integer.parseInt(arrTemp[nI+3].substring(4, 8));
			}
			
			int[][] arrProb = {{1,2,3},{1,2,4},{1,2,5},{1,3,4},{1,3,5},{1,4,5},{2,3,4},{2,3,5},{2,4,5},{3,4,5}};
			int[][] arrGrup = new int[5][4];
			
			int nU = 0;
			for (int nI = 0; nI < 5; nI++) {
				for (int nJ = 0; nJ < 4; nJ++) {
					arrGrup[nI][nJ] = arrDoze[nU];
					nU++;
				}
			}
			
			int[][] arrJogo = new int[10][15];
			
			for (int nI = 0; nI < 10; nI++) {
				int nL = 0;
				for (int nJ = 0; nJ < 3; nJ++) {
					for (int nK = 0; nK < 4; nK++) {
						arrJogo[nI][nL] = arrGrup[arrProb[nI][nJ]-1][nK];
						nL++;						
					}
				}
				arrJogo[nI][12] = numFix1;
				arrJogo[nI][13] = numFix2;
				arrJogo[nI][14] = numFix3;
			}
			
			System.out.println("\r\r");
			
			FileWriter objAposta = new FileWriter("d:/zTemp/lotofacil/aposta.txt");
			PrintWriter objTextLine = new PrintWriter(objAposta);			
			
			// PRIMEIRA COMBINAÇÃO
			
			int numPar = 0;
			for (int nI = 0; nI < 10; nI++) {
				Arrays.sort(arrJogo[nI]);
				System.out.print("JOGO " + String.format("%02d", nI+1) + " - "); 		// Imprime na tela
				for (int nJ = 0; nJ < 15; nJ++) {
					if (arrJogo[nI][nJ] % 2 == 0) {
						numPar++;
					}
					System.out.print(String.format("%04d", arrJogo[nI][nJ]) + " | ");	// Imprime na tela
					objTextLine.printf(String.format("%01d", arrJogo[nI][nJ]) + " ");	// Imprime no arquivo
				}
				System.out.print("Par (" + String.format("%02d", numPar) + ") / Impar (" + String.format("%02d", 15-numPar) +")");
				numPar = 0;
				System.out.println("\r");		// Imprime na tela
				objTextLine.println("");		// Imprime no arquivo
			}
			
			
			Arrays.sort(arrDoze);
			nU = 0;
			for (int nI = 0; nI < 5; nI++) {
				for (int nJ = 0; nJ < 4; nJ++) {
					arrGrup[nI][nJ] = arrDoze[nU];
					nU++;
				}
			}
			
			for (int nI = 0; nI < 10; nI++) {
				int nL = 0;
				for (int nJ = 0; nJ < 3; nJ++) {
					for (int nK = 0; nK < 4; nK++) {
						arrJogo[nI][nL] = arrGrup[arrProb[nI][nJ]-1][nK];
						nL++;						
					}
				}
				arrJogo[nI][12] = numFix1;
				arrJogo[nI][13] = numFix2;
				arrJogo[nI][14] = numFix3;
			}
			
			// SEGUNDA COMBINAÇÃO 
			numPar = 0;
			for (int nI = 0; nI < 10; nI++) {
				Arrays.sort(arrJogo[nI]);
				System.out.print("JOGO " + String.format("%02d", nI+11) + " - "); 		// Imprime na tela
				for (int nJ = 0; nJ < 15; nJ++) {
					if (arrJogo[nI][nJ] % 2 == 0) {
						numPar++;
					}
					System.out.print(String.format("%04d", arrJogo[nI][nJ]) + " | ");	// Imprime na tela
					objTextLine.printf(String.format("%01d", arrJogo[nI][nJ]) + " ");	// Imprime no arquivo					
				}
				System.out.print("Par (" + String.format("%02d", numPar) + ") / Impar (" + String.format("%02d", 15-numPar) +")");
				numPar = 0;
				System.out.println("\r");		// Imprime na tela
				objTextLine.println("");		// Imprime no arquivo
			}
			
			System.out.println("\r\r");		
			
			objAposta.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}