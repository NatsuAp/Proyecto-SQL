import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Comando {
    public static ArrayList<String> tableNames = new ArrayList<>();
    public static String tableFolder = "tables";
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

        // Create folder
        File baseFolder = new File(tableFolder);
        baseFolder.mkdirs();

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
        try (FileWriter fileWriter = new FileWriter(baseFolder + "/" + filepath)) {
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

    // Command 3: SELECT
    System.out.println("3. SELECT");
    System.out.println("   Usage: SELECT column_1, column_2 FROM table_name;");
    System.out.println("          SELECT * FROM table_name;");
    System.out.println("   Description: Retrieves specific columns from the specified table.");
    System.out.println("                - Replace 'column_1, column_2' with the columns you want to retrieve.");
    System.out.println("                - Replace 'table_name' with the name of the table.");
    System.out.println("   Example: SELECT name, age FROM employees;");

    System.out.println();

    // Command 4: SHOW COLUMNS
    System.out.println("4. SHOW COLUMNS");
    System.out.println("   Usage: SHOW COLUMNS FROM table_name;");
    System.out.println("   Description: Displays the column names (headers) of the specified table.");
    System.out.println("                - Replace 'table_name' with the name of the table.");
    System.out.println("   Example: SHOW COLUMNS FROM employees;");

    System.out.println();

    // Command 5: SHOW TABLES
    System.out.println("5. SHOW TABLES");
    System.out.println("   Usage: SHOW TABLES;");
    System.out.println("   Description: Lists all available tables in the current workspace.");
    System.out.println("   Example: SHOW TABLES;");

    System.out.println();

    // Reminder to type 'help' again
    System.out.println("Type 'help' anytime to see this list again.");

    }

    public static void select(String line) {

        ArrayList<Integer> order = new ArrayList<>();
        ArrayList<String> rows = new ArrayList<>();

        String input = "";
        input = line.replace("select", "").trim();

        String columns = input.substring(0, input.toLowerCase().indexOf("from")).trim();
        String table = input.substring(input.toLowerCase().indexOf("from") + 4).trim();
        File file = new File("tables/"+ table + ".csv");
        
       

            String[] columnIn = columns.replaceAll("\\s", "").split(",");
            if (!columns.equals("*")) {
                
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
                        try {
                            for (int i = 0; i < order.size(); i++) {

                                String[] temp = scanner.nextLine().split(",");
                                String tempString = "";
                                for (int x : order) {

                                    tempString = tempString + "," + temp[x - 1];
                                    tempString = tempString.replaceAll("\\s", "");

                                }
                                rows.add(tempString.substring(1));
                            }
                        } catch (NoSuchElementException e) {
                            System.out.println(columns);
                            for (int i = 0; i < rows.size(); i++) {
                                System.out.println(rows.get(i));
                            }
                            return;
                        }

                    }
                    columns = columns.replaceAll("\\s", "");
                    System.out.println(columns);
                    for (int i = 0; i < rows.size(); i++) {
                        System.out.println(rows.get(i));
                    }

                } catch (FileNotFoundException e) {
                   
                }
            } else {
               
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()){
                        System.out.println(scanner.nextLine());
                        
                    }
                    
                  
                   
                } catch (FileNotFoundException e) {

                }
            }
       
    }

    public static void listTables(){

        
        if(tableNames.isEmpty()){
            System.out.println("You do not have any tables at the moment");
        }else{
            System.out.println("\\s");
        System.out.println("Available Tables:");
        System.out.println("-----------------");
        for(String x: tableNames){
            System.out.println("-"+x+".csv");
        }
        }
    
        

    }

    public static void showColumns(String line){
        String in = line.replaceAll("\\s", "");
        String table = in.replace("showcolumnsfrom", "");
        File file = new File("tables/"+table+".csv");
         
         
        try(Scanner scanner = new Scanner(file)){
            String[]columns = scanner.nextLine().split(",");
            System.out.println("Table: " + table+".csv");
         System.out.println("Columns:");
         System.out.println("------------------------------");
         for(String x: columns){
            System.out.println("- " + x.trim());
         }

        }
        catch(FileNotFoundException e){
            System.out.println("There was an error accessing the File: "+ table+".csv");
        }
           
    }
}
