package kata5p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 

/**
 * Lee del fichero de texto los emails y crear una
 * lista de correos válidos, ya que en el fichero
 * email.txt hay cadenas no validas con el formato email
 * 
 * @author angel
 */
public class MailListReader {
    
    public static List<String> read(String fileName) throws Exception{
        
        List<String> lista = new ArrayList<>();
        File fichero = new File(fileName);
        
        try{
            Scanner scan = new Scanner(fichero);
            
            
    
            Pattern pattern = Pattern
                    .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            
            while(scan.hasNextLine()){
                String linea =  scan.nextLine();
                Matcher mather = pattern.matcher(linea);
                if (mather.find() == true) {
                    lista.add(new String(linea));
                }
            }
        
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return lista;
    }
    
}
