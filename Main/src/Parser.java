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
        if(parseListTables(input)){
            return "tables";
        }
        if(parseShowColumns(input)){
            return "columns";
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


        input = input.replace("(", "");
        input = input.replace(")", "");
        input = input.replace(",", "");
        String[] in2 = input.split(" ");
        
        if (in2[0].equals("insert") && in2[1].equals("into")) {

            System.out.println(in2[2]);
            System.out.println(in2[3]);
            if (in2[2].isEmpty() || in2[3].isEmpty()) {
                Errors.checkError(62, input);
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

<<<<<<< HEAD
=======
    public static boolean ParseSelect(String input) {
        String command = "";
        Boolean j = false;
        String columns = "";
        String table = "";
        int X = 0;
        if (!input.toLowerCase().startsWith("select")) {
            return false;
        }
        
       try {
        command = input.replace("select", "");
        command = command.trim();
      
        String temp = command.substring(0, input.toLowerCase().indexOf("from")-1).trim();
        temp = temp.replaceAll("\\s","");
        
        
            
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
        File filepath = new File("tables/"+ table + ".csv");
        if(columns.charAt(0)!='*'){

        
        String[] column = columns.split(",\\s*");
        column[column.length-1]=column[column.length-1].replace("from", "").trim();
        
        
        try (Scanner scanner = new Scanner(filepath)) {
            
            
            
            String[] tableColumns = scanner.nextLine().split(",");
            
            
            for (String x : tableColumns) {
                for(String y:column){
                    if(x.equals(y)){
                        X+=1;
                    }
                }
            }
            if(column.length==X){
            
                return true;
            }else{
                System.out.println("ERROR 8: A column or columns do not exist");
            }
        } catch (FileNotFoundException e) {
           
            System.out.println("ERROR 7: Table does not exist");
            
            return false;
            
        }
        
        }else{
           try(Scanner scanner = new Scanner(filepath)) {
            return true;
           } catch (FileNotFoundException e) {
            System.out.println("ERROR 7: Table does not exist");
            return false;
           }
        }
       } catch (Exception e) {
        Errors.checkError(1, input);
        return false;
       }

        
       
        

        return false;
    }

    public static boolean parseListTables(String input){
        String in = input.replaceAll("\\s", "");
        return in.equals("listtables")||in.equals("showtables");
         
        
        
    }

    public static boolean parseShowColumns(String input){
        input = input.replaceAll("\\s", "");
        if(input.length()>14){  
            String in = input.substring(0,15);
           
            if(in.toLowerCase().equals("showcolumnsfrom")){
               in = input.substring(15);
               for(String x: Comando.tableNames){
                if(in.equals(x)){
                    
                    return true;
                }
                
               }
               System.out.println("Error 8: Table does not exist");
            }
        }else{
           
            return false;
        }
        
        return false;
    }
>>>>>>> 24d4256b75a6078327ad973c6058f4ff362e02a4
}

