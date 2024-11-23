import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Comando {
    public static ArrayList<String> tableNames = new ArrayList<>();

    public static void insert(String linea2, String linea1) {

        linea2 = linea2.replace("(", "");
        linea2 = linea2.replace(")", "");
        linea2 = linea2.replace(",", "");
        String[] line2 = linea2.split(" ");

        linea1 = linea1.replace("(", "");
        linea1 = linea1.replace(")", "");
        linea1 = linea1.replace(",", "");
        String[] line1 = linea1.split(" ");

        String[] cols = new String[line1.length - 3];
        for (int i = 3; i < line1.length; i++) {
            cols[i - 3] = line1[i];

        }
        String ColsNeeded = "";

        for (String x : cols) {
            ColsNeeded = ColsNeeded + x + " ";
        }

        String fileCols = "";
        try {
            Scanner scanner = new Scanner(getFile(linea1));
            fileCols = scanner.nextLine();

            scanner.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        fileCols = fileCols.toLowerCase();
        String[] filesCols = fileCols.split(",");

        for(String x: line2){
            System.out.println(x);
        }
        String line = "";
        int t = 0;
        for (int i = 0; i < filesCols.length; i++) {
            
            if (ColsNeeded.contains(filesCols[i])) {
                if(i!=0){
                    line = line +   ", " + line2[t] ;
                    t = t + 1;
                }else{
                    line=line+line2[t];
                    t=t+1;
                }
                
            } else {
                if(i!=0){
                    line =  " , "+line ;
                }else{
                    line= line+" ";
                }
                
            }
        }
        System.out.println(line);
        FileWriter fileWriter;
        try {
            // Open the file in append mode by passing 'true' as the second argument
            fileWriter = new FileWriter(getName(linea1) + ".csv", true);
            fileWriter.write( "\n"+line ); // Add a newline to separate lines
            fileWriter.close(); // Always close the writer after use
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Listo");
    }

    public static void end() {
        System.out.print("Program ended");
        App.a = false;
    }

    public static void createTable(String line) {

        StringBuilder stringBuilder = new StringBuilder();
        String[] in = line.split(",");

        int x = in.length - 2;
        String tableName = in[0].replace("(", "").trim();
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
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        try (FileWriter fileWriter = new FileWriter(filepath)) {

            fileWriter.write(stringBuilder.toString());
            System.out.println("The table has been created successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void help() {
        System.out.println("Available Commands:");
        System.out.println("-------------------");

        // Command 1: CREATE TABLE
        System.out.println("1. CREATE TABLE");
        System.out.println("   Usage: CREATE TABLE table_name (");
        System.out.println("             column_1 datatype,");
        System.out.println("             column_2 datatype,");
        System.out.println("             column_3 datatype");
        System.out.println("          );");
        System.out.println("   Description: Creates a new table with the specified columns and data types.");
        System.out.println("                - Replace 'table_name' with the name of the table.");
        System.out.println("                - Replace 'column_1', 'column_2', etc., with column names.");
        System.out.println("                - Replace 'datatype' with the data type of each column.");

        System.out.println();

        // Command 2: END
        System.out.println("2. END");
        System.out.println("   Usage: END");
        System.out.println("   Description: Ends the program.");

        System.out.println();

        // Prompt to type 'help' again
        System.out.println("Type 'help' anytime to see this list again.");

    }

    public static File getFile(String line) {
        String[] lines = line.split(" ");
        File fileError = new File("");
        for (int i = 0; i < lines.length; i++) {
            if (tableNames.contains(lines[i])) {
                File file = new File(lines[2] + ".csv");
                return file;
            }
        }
        return fileError;
    }

    public static String getName(String line) {
        String[] lines = line.split(" ");
        for (int i = 0; i < lines.length; i++) {
            if (tableNames.contains(lines[i])) {
                return lines[i];
            }
        }
        return lines[0];
    }
}
