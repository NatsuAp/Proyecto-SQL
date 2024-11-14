import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Scanner;

public class Parser {
    public static String parseInput(String input) {
        if (parseCreate(input)) {
            return "create";
        }
        if (input.equals("help")) {
            return "help";
        }
        if (input.equals("end")) {
            return "end";
        }
        if (ParseInsert(input)) {
            return "insert";
        }
        return "";
    }

    public static boolean parseCreate(String input) {
        String[] in = input.split("\\s");
        if (in.length > 1 && in[0].equals("create") && in[1].equals("table")) {

            try {
                if (!in[2].equals("(")) {
                    if (in[2].charAt(in[2].length() - 1) == '(' || in[3].equals("(")) {
                        App.line = App.in.replace("create table ", "").trim();
                        App.line = App.line + ",";

                        return true;
                    }

                } else {

                    Errors.checkError(1, input);
                    return false;
                }
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {

                Errors.checkError(1, input);
                return false;
            }

        }
        return false;
    }

   
    public static int getPosition(String in, ArrayList<String> list) {
        for (int i = 0; i <= list.size(); i++) {
            if (list.get(i).equals(in)) {
                return i;
            }
        }
        return 0;

    }

    public static boolean ParseInsert(String input) {
        input = input.replace("(", " ");
        input = input.replace(")", " ");
        input = input.replace(",", " ");
        String[] in2 = input.split("\\s");
        if (in2[0].equals("insert") && in2[1].equals("into")) {

            if (in2[2].isEmpty() || in2[3].isEmpty()) {
                Errors.checkError(1, input);
                return false;
            }

            if (!Comando.tableNames.contains(in2[2])) {
                Errors.checkError(1, input);
                return false;
            }
            File file = new File(in2[2] + ".csv");
            Scanner scanner;
            try {
                scanner = new Scanner(file);
                String col = scanner.nextLine().toLowerCase();

                System.out.println(col);
                scanner.close();
                for (int i = 3; i < in2.length; i++) {
                    if (!col.contains(in2[i])) {
                        System.out.println(in2[i]);
                        Errors.checkError(1, input);
                        return false;
                    }
                }

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return true;
        }
        return false;
    }
}
