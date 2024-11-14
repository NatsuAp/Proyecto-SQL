import java.util.ArrayList;

public class Parser {
    public static String parseInput(String input) {
        input = input.replace("(", "");
        input = input.replace(")", "");
        input = input.replace(",", "");
        String[] in = input.split("\\s");
        if(in.length>1 &&in[0].equals("create") && in[1].equals("table") ){   
            
                try{
                    if(!in[2].equals("(")){
                        if(in[2].charAt(in[2].length()-1)=='('|| in[3].equals("(")){
                            App.line = App.in.replace("create table ", "").trim();
                            App.line = App.line + ",";
                            
                            
                    return "create";
                        }
                        
                    }else{
                       
                        Errors.checkError(1, input);
                        return "ERROR";
                    }
                }catch(ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e){
                
                    Errors.checkError(1, input);
                    return "ERROR";
                } 

            
            }
            if(input.equals("help")){
                return "help";
            }
            if(input.equals("end")){
                return "end";
            }
            if(in[0].equals("insert") && in[1].equals("into")  ){

                if(in[2].isEmpty()||in[3].isEmpty()){
                    Errors.checkError(60, input);
                    return "ERROR";
                }
                
                if(!Comando.tableNames.contains(in[2])){
                    Errors.checkError(61, input);
                    return "ERROR";
                }
                for(int i = 3; i<= in.length; i++){
                    if(!Comando.tableNames.get(getPosition(in[2], Comando.tableNames)).contains(in[i])){
                        Errors.checkError(62, input);
                        return "ERROR";
                    }
                }
                
            }
            
        return "insert";
    }
    //insert into hola (Dia, Edad, yes)
    public static int getPosition(String in, ArrayList<String> list){
        for(int i = 0; i<=list.size(); i++){
            if(list.get(i).equals(in)){
                return i;
            }
        }
        return 0;


    }
}
