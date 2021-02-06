package esame; 
import java.io.*;

public class ProgrammaUno {

	public static void copiaFile(File input, File output) throws IOException{
	
		//prendo tutti i nodi figli e li porto in una nuova cartella for (File fileinput : input.listFiles()) {
		if(fileinput.isDirectory()) {
		copiaFile(fileinput, output); 
		}

		else {
			//se la cartella non esiste la creo
			if (!output.exists()) output.mkdir();
			ileinput.renameTo(new File("/Users/lorenzodigianvittorio/Desktop/Unzippare/" + fileinput.getName()));
		}
	} 

	public static void main(String[] args) throws IOException {
		File input = new File("/Users/lorenzodigianvittorio/***");
		File output= new File("/Users/lorenzodigianvittorio/***");
		copiaFile(input, output);

	}
}

