import java.io.FileWriter;
import java.io.IOException;

public class Comando {
    public static boolean checkError(int x){
        if(x==1){
            if(App.line.equals("(,")|| App.line.equals( " (,")){
                System.out.println("Error " + x +": El nombre de la tabla no fue especificado");
               return true;
            }
            
        }
        if(x==2){
            if(App.input.equals(");")){
                return false;
            }
            if(App.input.split(" ").length!= 2){
                System.out.println("Error "+ x +": No se especifico el nombre o el tipo de variable");
                return true;
            }
            
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
        StringBuilder stringBuilder =new StringBuilder();
        String[] in = line.split(",");
        int x = in.length - 2;
        String tableName = in[0].replace("(", "");
       
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
        for(String X : columnNames){
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
