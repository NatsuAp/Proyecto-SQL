
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//com ousar system.out y system.flush
public class App {

    public static Boolean a = true;
    public static String input = "";
    public static String in = "";
    public static String line = "";
    public static String command;
     public static Scanner scanner = new Scanner(System.in);

     public static void setup(){
        File file = new File("tables/");
        File files[] = file.listFiles();
        ArrayList<String> tableNames = new ArrayList<>();
        for(File x: files){
            String temp = x.getName().split("\\.")[0];

            tableNames.add(temp);
            
        }
        Comando.tableNames=tableNames;
     }
    public static void main(String[] args) {
        setup();
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
                    
                    String line2 = scanner.nextLine();
                    if(UpdateParser.parseUpdate2(line2)){
                        String line3 = scanner.nextLine();
                        if(UpdateParser.parseUpdate3(line3)){
                           Comando.Update();
                        }
                        
                    }
                    
                    
                
                


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
