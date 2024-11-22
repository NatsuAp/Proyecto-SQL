import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Scanner;

public class Parser {

    public static String parseInput(String input) {

        if (parseCreate(input)) {
            return "create";
        }
        if (input.equals("help")) {
            return "help";
        }
        if (input.equals("end")) {
            return "end";
        }
        if (ParseInsert(input)) {
            return "insert";
        }
        if (ParseSelect(input)) {
            return "select";
        }
        if (input.equals("clear")) {
            return "clear";
        }

        return "";
    }

    public static boolean parseCreate(String input) {
        String[] in = input.split("\\s");
        if (in.length > 1 && in[0].equals("create") && in[1].equals("table")) {

            try {
                if (!in[2].equals("(")) {
                    if (in[2].charAt(in[2].length() - 1) == '(' || in[3].equals("(")) {
                        App.line = App.in.replace("create table ", "").trim();
                        App.line = App.line + ",";

                        return true;
                    }

                } else {

                    Errors.checkError(1, input);
                    return false;
                }
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {

                Errors.checkError(1, input);
                return false;
            }

        }
        return false;
    }

    public static int getPosition(String in, ArrayList<String> list) {
        for (int i = 0; i <= list.size(); i++) {
            if (list.get(i).equals(in)) {
                return i;
            }
        }
        return 0;

    }

    public static boolean ParseInsert(String input) {
        input = input.replace("(", " ");
        input = input.replace(")", " ");
        input = input.replace(",", " ");
        String[] in2 = input.split("\\s");
        if (in2[0].equals("insert") && in2[1].equals("into")) {

            if (in2[2].isEmpty() || in2[3].isEmpty()) {
                Errors.checkError(1, input);
                return false;
            }

            if (!Comando.tableNames.contains(in2[2])) {
                Errors.checkError(1, input);
                return false;
            }
            File file = new File(in2[2] + ".csv");
            Scanner scanner;
            try {
                scanner = new Scanner(file);
                String col = scanner.nextLine().toLowerCase();

                System.out.println(col);
                scanner.close();
                for (int i = 3; i < in2.length; i++) {
                    if (!col.contains(in2[i])) {
                        System.out.println(in2[i]);
                        Errors.checkError(1, input);
                        return false;
                    }
                }

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return true;
        }
        return false;
    }

    public static boolean ParseSelect(String input) {
        String command = "";
        Boolean j = false;
        String columns = "";
        String table = "";
        int X = 0;
        if (!input.toLowerCase().startsWith("select")) {
            return false;
        }

        command = input.replace("select", "");
        command = command.trim();

        // separa las columnas mencionadas en el input
        for (int i = 0; i < command.length(); i++) {
            columns += command.charAt(i);

            if (columns.length() > 4 && columns.substring(columns.length() - 4).equals("from")) {
                j = true;
                break;

            }
        }

        if (!j) {
            Errors.checkError(1, input);

            return false;
        }
        table = command.replace(columns, "").trim();
        String[] column = columns.split(",\\s*");
        column[column.length-1]=column[column.length-1].replace("from", "").trim();
        File filepath = new File(table + ".csv");
        
        try (Scanner scanner = new Scanner(filepath)) {
            
            
            
            String[] tableColumns = scanner.nextLine().split(",");

            scanner.close();
            for (String x : tableColumns) {
                for(String y:column){
                    if(x.equals(y)){
                        X+=1;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR 7: Table does not exist");
            
            return false;
            
        }
        if(column.length==X){
            
            return true;
        }else{
            System.out.println("ERROR 8: A column or columns do not exist");
        }

        return false;
    }

}
