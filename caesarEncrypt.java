import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        HashMap<String, String> argsHash = new HashMap<>();
        for(int i = 0; i < args.length - 1; i += 2){
            argsHash.put(args[i], args[i + 1]);
        }

        StringBuilder result = new StringBuilder();
        String message;

        if(argsHash.containsKey("-data")) {
            message = argsHash.get("-data");
        }else if(argsHash.containsKey("-in")){
            File file = new File(argsHash.get("-in"));
            Scanner scanner = new Scanner(file);
            message = scanner.nextLine();
        }else{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter data:");
            message = scanner.nextLine();
        }
        int s = Integer.parseInt(argsHash.get("-key"));

        if(argsHash.get("-alg").equals("unicode")) {

            result = unicode(argsHash, message, s);

        } else if(argsHash.get("-alg").equals("shift")){

            result = shift(argsHash, message, s);

        }

        if (argsHash.get("-out").equals("stout")) {
            System.out.println(result);
        }else {

            File file = new File(argsHash.get("-out"));
            FileWriter writer = new FileWriter(file);
            writer.write(result.toString());
            writer.close();
        }

    }

    private static StringBuilder unicode(HashMap<String, String> argsHash, String message, int s){
        StringBuilder result = new StringBuilder();
        if (argsHash.get("-mode").equals("enc")) {
            for (int i = 0; i < message.length(); i++) {
                result.append((char) ((int) message.charAt(i) + s));
            }

        } else {
            for (int i = 0; i < message.length(); i++) {
                result.append((char) ((int) message.charAt(i) - s));
            }
        }
        return result;
    }

    private static StringBuilder shift(HashMap<String, String> argsHash, String message, int s) {
        StringBuilder result = new StringBuilder();
        message = message.toLowerCase();
        if (argsHash.get("-mode").equals("enc")) {
            for (int i = 0; i < message.length(); i++) {
                if(message.charAt(i) == " ".charAt(0)){
                    result.append((char)32);
                }else {
                    char temp = (char) ((int) message.charAt(i) + s);
                    if ((int) temp > 122) {
                        temp = (char) ((int) temp - 26);
                    }
                    result.append(temp);
                }
            }

        } else {
            for (int i = 0; i < message.length(); i++) {
                if(message.charAt(i) == " ".charAt(0)){
                    result.append((char)32);
                }else {
                    char temp = (char) ((int) message.charAt(i) - s);
                    if ((int) temp < 97) {
                        temp = (char) ((int) temp + 26);
                    }
                    result.append(temp);
                }
            }
        }

        return result;
    }
}
