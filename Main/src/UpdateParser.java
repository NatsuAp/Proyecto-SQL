import java.util.ArrayList;

public class UpdateParser {
    public static String columnTemp = "";
    public static String tableTemp = "";
    public static String conditionTemp = "";
    public static ArrayList<String> columns = new ArrayList<>();
    public static ArrayList<String> newInfo = new ArrayList<>();
    public static String toChangeColumn ="";
    public static String toChangeColumnWhere="";
    public static boolean parseUpdate(String input) {

        String in[] = input.split(" ");
       
        try {
            if (in[0].toLowerCase().equals("update") && Helpers.checkTableExist(in[1])) {
                tableTemp = in[1];
                return true;
            }

        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public static boolean parseUpdate2(String input) {
        
        try {
            String line = "";
        int i=0;
            if (input.substring(0, 4).toLowerCase().equals("set ")) {
                line = input.substring(4);
                String info[] = line.split(",");
                for (String x : info) {
                    String temp[] = x.split("=");
                    if (Helpers.checkColumnExist(tableTemp, temp[0])) {
    
                        columns.add(temp[0]);
                        newInfo.add(temp[1]);
                        i++;
                    }
    
                }
                if(i==info.length){
                    return true;
                }
            }
        } catch (Exception e) {
          return false;
        }
       
        
        return false;
    }
    
    public static boolean parseUpdate3(String input){
        
        try {
            String line="";
            if (input.substring(0, 6).toLowerCase().equals("where ")) {
                line = input.substring(6);
                String temp2[] = line.split("=");
                if(Helpers.checkColumnExist(tableTemp, temp2[0])){
                   toChangeColumn = temp2[0];
                    toChangeColumnWhere =temp2[1];
                    return true;
                }
    
    
            }
        } catch (Exception e) {
            return false;
        }
        
        return false;
    }
}
