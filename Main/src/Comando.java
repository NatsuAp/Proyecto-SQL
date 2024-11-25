import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;

public class Comando {
    public static ArrayList<String> tableNames = new ArrayList<>();

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
        System.out.println("             column_1 datatype");
        System.out.println("             column_2 datatype");
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

    public static void select(String line) {

        ArrayList<Integer> order = new ArrayList<>();
        ArrayList<String> rows = new ArrayList<>();
        
        String input = "";
        input = line.replace("select", "").trim();

        String columns = input.substring(0, input.toLowerCase().indexOf("from")).trim();
        String table = input.substring(input.toLowerCase().indexOf("from") + 4).trim();
        File file = new File(table + ".csv");
        String[] columnIn = columns.replaceAll("\\s", "").split(",");
        try (Scanner scanner = new Scanner(file)) {

            String[] columnFile = scanner.nextLine().trim().split(",");
            for (int i = 0; i < columnIn.length; i++) {
                for (int j = 0; j < columnFile.length; j++) {
                    if (columnIn[i].equals(columnFile[j])) {
                        order.add(j + 1);
                    }
                }
            }

            while (scanner.hasNextLine()) {
               
                for (int i = 0; i < order.size(); i++) {
                    String[] temp = scanner.nextLine().split(",");
                    String tempString = "";
                    for (int x : order) {

                        tempString = tempString + "," + temp[x - 1];
                        
                    }
                    rows.add(tempString.substring(1));
                }
            }
            System.out.println(columns);
            for(int i=0;i<rows.size();i++){
                System.out.println(rows.get(i));
            }
           

        } catch (FileNotFoundException e) {

        }

    }
}
