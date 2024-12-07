public class Errors {

    public static boolean checkError(int x, String input) {

        if (x == 1) {
           
            System.out.println("Error " + x + ": Syntax error");
            return true;

        }
        if (x == 2) {
            if (input.split(",").length <= 2) {
                System.out.println("Error " + x + ": The table can not be empty when closed");
               
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
          
            return true;

        }
        if (x == 4) {
            if (!App.input.equals(");") && App.input.split(" ").length != 2) {
                System.out.println("Error " + x + ": The type of primitive variable was not assigned");
              
                return true;
            }

        }
        if (x == 5) {
            if (input.equals("")) {
                System.out.println("Error " + x + ": Input can not be null");
              
                return true;
            }

        }
        if(x==6){
            if(input.equals("")){
                System.out.println("Error " + 6 + ": CODE ERROR");
            }
        }
        return false;

    }
}
