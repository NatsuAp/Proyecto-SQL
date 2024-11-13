
import java.util.Scanner;

//com ousar system.out y system.flush
public class App {

    public static Boolean a = true;
    public static String input = "";
    public static String in = "";
    public static String line = "";
    public static String command;

    public static void main(String[] args){
        System.out.println("Program Initialized");
        Scanner scanner = new Scanner(System.in);
        while (a) {
            input = scanner.nextLine();
            in = input.toLowerCase();
            command = Comando.parseInput(in);

            switchLabel: switch (command) {
                case "end":
                    Comando.end();
                    break;
                case "create":

                    while (!input.contains(");")) {
                        input = scanner.nextLine();
                        line = line + input + ",";
                        if (Comando.checkError(2, input) || Comando.checkError(3, input)) {

                            break switchLabel;
                        }
                    }
                    Comando.createTable(line);

                    break;
                    case "insert":
                    Comando.checkError(4, input);

                default:
                    if(!command.equals("ERROR")){
                        System.out.println("Unknown command");
                    }
                    
                    break;

            }

        }
        scanner.close();

    }

}
