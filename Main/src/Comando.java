import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Comando {
   public static ArrayList<String> tableNames =new ArrayList<>();
   
    public static String parseInput(String input) {
        String[] in = input.split("\\s");
        if(in[0].equals("create") && in[1].equals("table") ){   
            
                try{
                    if(!in[2].equals("(")){
                        if(in[2].charAt(in[2].length()-1)=='('|| in[3].equals("(")){
                            App.line = App.in.replace("create table ", "").trim();
                            App.line = App.line + ",";
                            
                    return "create";
                        }
                        
                    }else{
                       
                        checkError(1, input);
                        return "ERROR";
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                
                    checkError(1, input);
                    return "ERROR";
                } 
            
            }
            
        return input;
    }

    public static boolean checkError(int x, String input) {

        if (x == 1) {
            
                System.out.println("Error " + x + ": Syntax error");
                return true;
            

        }
        if (x == 2) {
            if (App.input.equals(");")) {
                return false;
            }
            if (App.input.split(" ").length != 2) {
                System.out.println("Error " + x + ":The type of primitive variable was not assigned");
                return true;
            }

        }
        if (x == 3) {
            String[] types = { "string", "int", "double", "byte", "char", "short", "float", "boolean" };
            String[] type = input.split(" ");
            for (String X : types) {
                if (input.equals(");") || type[1].toLowerCase().contains(X)) {
                    return false;
                }

            }
            System.out.println("Error " + x + ": The type of primitive variable was not recognized");
        }
        
        return false;
        
    }
    public static void end() {
        System.out.print("Program ended");
        App.a = false;
    }

    public static void createTable(String line) {
        
        StringBuilder stringBuilder = new StringBuilder();
        String[] in = line.split(",");
        int x = in.length - 2;
        String tableName = in[0].replace("(", "");
        tableNames.add(tableName);
        String filepath = tableName + ".csv";

        String[] temp = new String[x];
        for (int i = 0; i < x; i++) {
            temp[i] = in[i + 1];
        }
        String[] columnNames = new String[x];
        String[] columnType = new String[x];

        for (int i = 0; i < temp.length; i++) {
            String[] parts = temp[i].trim().split(" ");
            columnNames[i] = parts[0];
            columnType[i] = parts[1];
        }
        for (String X : columnNames) {
            stringBuilder.append(X).append(",");

        }
       stringBuilder.deleteCharAt(stringBuilder.length()-1);
        try (FileWriter fileWriter = new FileWriter(filepath)) {
            fileWriter.write(stringBuilder.toString());
            System.out.println("The table has been created successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
