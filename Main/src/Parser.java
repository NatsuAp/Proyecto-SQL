public class Parser {
    public static String parseInput(String input) {
        
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
            
        return "";
    }
}
