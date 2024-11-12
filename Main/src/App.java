
import java.util.Scanner;


//com ousar system.out y system.flush
public class App {

    public static Boolean a = true;
    public static String input;
    public static String in;
    public static String line;

    public static void main(String[] args) throws Exception {
        System.out.println("Program Initialized");
        Scanner scanner = new Scanner(System.in);
        while (a) {
            input = scanner.nextLine();

            in = input.toLowerCase();
            switchLabel:
            switch (in) {
                case "saludame":
                    Comando.saludame();
                    break;
                case "end":
                    Comando.end();
                    break;
                default:
                    if (in.contains("create table")) {
                        line = in.replace("create table", "").trim();
                        line = line + ",";
                        if(Comando.checkError(1)){
                            break;
                        };
                        
                        
                        while (!input.contains(");")) {
                            input = scanner.nextLine();
                            line = line + input + ",";
                            if(Comando.checkError(2)){
                                
                                break switchLabel;
                            }
                        }
                        Comando.createTable(line);
                    
                    break;
            } else {
                System.out.println("Unknown command");
                break;
            }


        }
        

    }
    scanner.close();
}

}
