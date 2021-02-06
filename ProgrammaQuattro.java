package esame;

import java.io.*;
import java.util.*;

public class ProgrammaQuattro {
	
	static final String dir = new String ("/Users/lorenzodigianvittorio/Desktop/formatoTXT");
	static final String out = new String ("/Users/lorenzodigianvittorio/Desktop/risultati.txt");
	
	//la mappa testi prende i file in inglese e l'encoding corretto, 
	//la mappa dictionary prende le parole e le relative frequenze
	private Map <File, String> testi = new HashMap <File, String>();
	private Map<String, Integer> dictionary = new TreeMap<String, Integer>();
	
	long start_time =System.currentTimeMillis();
    long stop_time;
	
	private ProgrammaQuattro() throws IOException {
		ricerca(dir);
		elabora(dictionary, out);
	}
	
	/*metodo che prende il path dei file in inglese e il relativo encoding 
	 per rileggerli poi correttamente e creare un TreeMap con un dizionario delle parole e le relative frequenze */
	private void ricerca(String dir2) throws IOException {
			
			File input = new File(dir2);
			String path=null;
			String encod=null;
			File percorso =null;
			
			//stringhe che mi servono per togliere le due porzioni di testo
			String start1 = "START OF";
			String end1 = "END OF";
			
			for (File file : input.listFiles()) {
				
				try {
					BufferedReader reader = new BufferedReader(new FileReader (file));
					String line = reader.readLine();
					
					start_time = System.currentTimeMillis();
		
					while (line != null) {
						
						if(line.equals("Language: English")) path= file.getPath();
						
						if (line.contains("Character set encoding:")) {
							StringTokenizer st = new StringTokenizer (line);	
							st.nextToken();
							st.nextToken();
							st.nextToken();
							//prendo il quarto token corrispondente all'encoding
							encod =st.nextToken();
							
						}
						line = reader.readLine();
					}
					
					percorso = new File(path);
					if (encod.endsWith("ISO-646-US")) encod= "ASCII";
					testi.put(percorso, encod);
					
					reader.close();
					
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			//contatore totale parole
			int totParole=0;
			
			//in questo ciclo apro i file in inglese con il giusto encoding 
			for ( File t : testi.keySet()) {

					try {
						BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream (t), encod));
						String line2 = reader2.readLine();
						
						//non considero la parte fino a *** START OF THIS PROJECT GUTENBERG
						while (!line2.contains(start1)) {
							line2 = reader2.readLine();
						}
						
						//analizzo fino ad *** END OF THIS PROJECT GUTENBERG
						while (!line2.contains(end1)) {
							
							StringTokenizer st = new StringTokenizer(line2);
							while(st.hasMoreTokens()) {
								String token = st.nextToken();
								//conto tutte le parole
								m++;
								
								if(dictionary.containsKey(token)) { //Se lo ho già aggiunto
									dictionary.put(token, dictionary.get(token)+1); //Aggiungo +1
								}
								else {
									dictionary.put(token , 1); //Altrimenti l'ho salvo e inizializzo ad 1
								}
							}
							// ferma il cronometro
							stop_time = (System.currentTimeMillis() - start_time) / 1000;
							line2 = reader2.readLine();
							 n++;
					    } 
					    reader2.close();
					} 
					catch(IOException e) {
					e.printStackTrace();
					}
			}
			
			System.out.println("i file in inglese analizzati sono: "+ testi.size());
			System.out.println("le parole diverse sono: "+dictionary.size());
			System.out.println("totale parole: "+m);
			System.out.println("Impegati: " + stop_time + "secondi");
			
	}

	//metodo per contare la frequenza delle frequenze e ne stampo il risultato su di un file.txt
	private void elabora(Map<String, Integer> dictionary2, String out2) {
		
		Map <Integer, Integer> map = new TreeMap<Integer, Integer>();
		
		for (String key : dictionary2.keySet()) add(map, dictionary2.get(key) );
		
		try {
			PrintWriter out3 = new PrintWriter (new BufferedWriter (new FileWriter (out2)));
			for (Integer key : map.keySet()) out3.println(key+" "+map.get(key));
			out3.close();
			
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	//vado a cercare l'intero nella mappa 
	private void add(Map<Integer,Integer> values, int chiave) {

		Integer f = values.get(chiave);
		if(f==null) values.put(chiave, 1);
		else values.put(chiave, f+1);
	}
	
	public static void main(String[] args) throws IOException {
	
		new ProgrammaQuattro();

	}

}