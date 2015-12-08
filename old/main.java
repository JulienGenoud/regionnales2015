import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class DeleteHtml {
   public static void main (String[] args){
     BufferedReader br = null;
         try {
             br = new BufferedReader(new FileReader("results2"));
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
         try {
             StringBuilder sb = new StringBuilder();
             String line = br.readLine();

             while (line != null) {
                 sb.append(line.replaceAll("\\s+",""));
                 sb.append(System.lineSeparator());
                 line = br.readLine();
             }
             String everything = sb.toString();

             String target = everything.replaceAll("<[^>]*>", "");
             System.out.println(target);


         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             try {
                 assert br != null;
                 br.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
       }
    }
