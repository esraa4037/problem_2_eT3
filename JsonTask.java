import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class JsonTask {
    public static float[] parseLine(String line){
        int n = line.length();
        String s = "";
        float result[] = new float[5];
        int index = 0;
        for(int i = 0; i < n; i++){
            if (line.charAt(i) != ' '){
                s += line.charAt(i);
            } else {
                // push element to the array and empty the string
                result[index] = Float.parseFloat(s);
                s = "";
                index++;
            }
            if (i==n-1){
                result[index] = Float.parseFloat(s);
                break;
            }
        }
        return result;
    }
  
    public static String formatJson() throws FileNotFoundException{
        // getting file path from the user
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file path: ");
        String inputFilePath = sc.nextLine();
        File file = new File(inputFilePath);
        Scanner input = new Scanner(file);
          
        // creating json
        String result = "{\n";
        result += "  \"annotations\": [\n";
        result += "    {\n";
        result += "      \"result\": [\n";
        
        // loop over the data line by line and extract it to convert it to json
        while (input.hasNextLine()) {
            String line = input.nextLine();
            float imageParams[] = parseLine(line);
            result += formatObject(imageParams);
            if(input.hasNextLine()){
                result += ",\n";
            } else {
                result += "\n";
            }
        }
        result += "      ]\n";
        result += "    }\n";
        result += "  ]\n";
        result += "}\n";
        
        return result;
    }
    
    public static String formatObject(float[] arr) {
	String result = "        {\n";
        result += "          \"image_rotation\": ";
        result += arr[0] + ",\n";
        result += "          \"value\": {\n";
        result += "            \"x\": ";
        result += arr[1] + ",\n";
        result += "            \"y\": ";
        result += arr[2] + ",\n";
        result += "            \"width\": ";
        result += arr[3] + ",\n";
        result += "            \"height\": ";
        result += arr[4] + ",\n";
        result += "            \"rotation\": ";
        result += 0 + ",\n";
        result += "            \"rectanglelabels\": ";
        result += "[\"object\"]\n";
        result += "          }\n";
        result += "        }";

        return result;
    }
    
    public static void solve() throws FileNotFoundException {
        String jsonString = formatJson();
        String absolutePath = new File("").getAbsolutePath();
        try(FileWriter writer = new FileWriter(absolutePath + "/output.json")){
            writer.write(jsonString);
            writer.flush();
            System.out.println("JSON file is created successfully.");
        } catch(Exception e) {

        }
    }

    public static void main(String[] args) throws FileNotFoundException {  
        solve();
    }  
}

