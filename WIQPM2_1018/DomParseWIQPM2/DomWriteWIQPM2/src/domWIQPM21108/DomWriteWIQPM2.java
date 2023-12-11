package domWIQPM21108;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DomWriteWIQPM2 {
    public static void main(String[] args) {
    	
        try {
        	File xml = new File("WIQPM2_kurzusfelvetel.xml");
            
            try (BufferedReader br = new BufferedReader(new FileReader(xml))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
            writeXmlContentToFile(xml);
                
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static void writeXmlContentToFile(File inputFile) {
        File outputFile = new File("kurzusfelvetel1_WIQPM2.xml");
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {
            String line;
            while ((line = br.readLine()) != null) {
                writer.write(line + System.lineSeparator());
            }
            System.out.println("Sikeres írás");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}