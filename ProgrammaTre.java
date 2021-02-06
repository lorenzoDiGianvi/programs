package esame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProgrammaTre {
	 
	    //metodo per prendere i file in formato .txt dopo averli estratti e portali in una nuova cartella
		public static void prendiTxt(File input, File output) throws IOException  {
			
			//parte il cronometro
			long start_time =System.currentTimeMillis();

			FileInputStream fis;
			byte[] buffer = new byte[1024];

			for (File in : input.listFiles()) {
				//se il nome del file finisce con .txt
				if(in.getName().endsWith(".txt")) {
					
					fis= new FileInputStream(in);
					File newFile = new File(output + File.separator + in.getName() );
					new File(newFile.getParent()).mkdirs();
					FileOutputStream fos = new FileOutputStream(newFile);
					int len;

			        while ((len = fis.read(buffer)) > 0) {
			            fos.write(buffer, 0, len);
			        }
			        
			        fos.close();
			        fis.close();
				}
			}

			//ferma il cronometro
			long stop_time = (System.currentTimeMillis() - start_time) / 1000;
			System.out.println("secondi impiegati"+stop_time);
		}

		public static void main(String[] args) throws IOException {
			// TODO Auto-generated method stub

			File input = new File ("/Users/lorenzodigianvittorio/Desktop/Unzippati");
			File output = new File ("/Users/lorenzodigianvittorio/Desktop/formatoTXT");
			prendiTxt(input, output);
		}
}