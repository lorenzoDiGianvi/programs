package esame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ProgrammaDue {
	
	//metodo per decomprimere i file ricorsivamente e portarli in una nuova cartella
	public static  void Unzip(File source, File out)
		    throws IOException {
		
		File input = source;
		FileInputStream fis;
		String entryName;
	    byte[] buffer = new byte[1024];
	    
	    //parte il cronometro
	    long start_time =System.currentTimeMillis();
	    
	    try {
	    	
	    	for (File in : input.listFiles()) {
	    		//prendo i file con estensione .zip e .ZIP poichè non tutti i file sono zippati
	    		if (in.getName().endsWith(".zip") || in.getName().endsWith(".ZIP")) {
	    			
	    			fis= new FileInputStream(in);
	    			ZipInputStream zis = new ZipInputStream(fis);
	    			ZipEntry ze = zis.getNextEntry();
	    			while(ze != null && !ze.isDirectory()){
		        	
	    				entryName = ze.getName();

	    				//creo un nuovo file 
	    				File newFile = new File(out + File.separator + entryName);
		            
	    				//creo directories per sub directories in zip
	    				new File(newFile.getParent()).mkdirs();
	    				FileOutputStream fos = new FileOutputStream(newFile);

	    				int len;
	    				//leggo e scrivo
	    				while ((len = zis.read(buffer)) > 0) {
	    					fos.write(buffer, 0, len);
	    				}

	    				fos.close();
	    				//chiudo lo ZipEntry
	    				zis.closeEntry();
	    				ze = zis.getNextEntry(); 
	    			}
	    			zis.closeEntry();
	    			zis.close();
	    			fis.close();	
	    		}
	    	}

		} catch (IOException e) {
	        e.printStackTrace();
	    }	 
	    
	 // ferma il cronometro
		long stop_time = (System.currentTimeMillis() - start_time) / 1000;
		System.out.println("secondi impiegati"+stop_time);		
	}

	public static void main(String[] args) throws IOException {
	
		File zipFilePath = new File("/Users/lorenzodigianvittorio/Desktop/Unzippare1");
		File destinazioneDir = new File("/Users/lorenzodigianvittorio/Desktop/Unzippati");
		Unzip(zipFilePath, destinazioneDir);

	}

}