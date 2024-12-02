
import java.util.Scanner;

//com ousar system.out y system.flush
public class App {

    public static Boolean a = true;
    public static String input = "";
    public static String in = "";
    public static String line = "";
    public static String command;
     public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Program Initialized");
        
        while (a) {
            input = scanner.nextLine();

            in = input.toLowerCase();
            command = Parser.parseInput(in);

            switchLabel: switch (command) {
                case "help":
                    Comando.help();
                    break;
                case "end":
                    Comando.end();
                    break;
                case "create":

                    while (!input.trim().equals(");")) {
                        input = scanner.nextLine();
                        line = line + input + ",";

                        if (Errors.checkError(5, input) || Errors.checkError(4, input) || Errors.checkError(3, input)) {
                            
                            break switchLabel;
                        }
                    }
                    if(!Errors.checkError(2, line)){
                        Comando.createTable(line);
                    }
                        
                    

                    break;
                case "insert":

                    input = scanner.nextLine();
                    String en = input.toLowerCase();
                    if(!en.contains("values")){
                        Errors.checkError(1, en);
                    }else{
                        Comando.insert(input, in);
                    }
                    
                    break;
                case "select":
                    Comando.select(in);
                    break;
                case "clear":
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    break;
                case "tables":
                Comando.listTables();
                break;
                case "columns":
                Comando.showColumns(in);
                break;
                case "delete":
                Comando.Delete(in);
                break;
                case "update":
                input= scanner.nextLine();
                


                break;
                default:
                    if (!command.equals("ERROR")) {
                        System.out.println("Unknown command");
                    }

                    break;

            }

        }
        scanner.close();

    }

}
