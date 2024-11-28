import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Helpers {
    int[] nums = {1,2,3,4,5,6,7,8,9};
    
        
    

    public static boolean checkTableExist(String table){
        int i =0;
        for(String x: Comando.tableNames){
            if(table.equals(x)){
              
                i++;
            }
        }
        if(i>0){
            return true;
        }
        return false;
    }

    public static boolean checkColumnExist(String table, String column){
           File file = new File("tables/"+table+".csv");
           int i=0;
        try (Scanner scanner = new Scanner(file)) {
           String columns[] = scanner.nextLine().split(",");
           for(String x: columns){
            if(x.equals(column)){
                i++;
            }
           }
           if(i>0){
            return true;
           }
           return false;
        } catch (FileNotFoundException e) {
            System.out.println("ERROR IN FUNCTION checkColumnExists()");
           return false;
        }
    }
}