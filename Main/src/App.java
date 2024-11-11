import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;


//com ousar system.out y system.flush
public class App {
   
    public static Boolean a = true;
    
    public static void main(String[] args) throws Exception {
       
        Scanner scanner = new Scanner(System.in);
        while(a){
            String comandoo = scanner.nextLine();
            switch (comandoo) {
                case "saludame":
                Comando.saludame();
                    break;
                    case "end":
                    Comando.end();
            
                default:
                    break;
            }
        }
            
        
        
    
        
        
    }
}
