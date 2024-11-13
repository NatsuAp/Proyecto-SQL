import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Comando {
   public static ArrayList<String> tableNames =new ArrayList<>();
   
    public static String parseInput(String input) {
        String[] in = input.split(" ");
        if(in[0].equals("create") && in[1].equals("table")){   
            try{
                if(!in[2].equals("(")){
                    App.line = App.in.replace("create table ", "").trim();
            App.line = App.line + ",";

            return "create";
                }
            }catch(IndexOutOfBoundsException e){
            
                checkError(1, input);
                return "ERROR";
            }
            
        }

      
            
        
        if (input.length() >= 11 && input.substring(0, 11).equals("insert into ")){
            int x = 0;
            input= input.replace("insert into ", "").trim();
            for (int i = 0; i<=input.length();i++){
                if(String.valueOf(input.charAt(i)).equals(" ")){
                    x=i;
                    break;

                }            
            }

            for(String X : tableNames){
                if(input.substring(0, x).equals(X)){
                    
                }
            }
            
            return "insert";
            
        }

        return input;
    }

    public static boolean checkError(int x, String input) {

        if (x == 1) {
            
                System.out.println("Error " + x + ": Table name was not especified");
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

    public static void saludame() {
        System.out.println("te saludo cv");
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

        try (FileWriter fileWriter = new FileWriter(filepath)) {
            fileWriter.write(stringBuilder.toString());
            System.out.println("The table has been created successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
