import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ParseHtml {
  public static void main (String[] args){
    List<String> StringTAB = new ArrayList<>();


    BufferedReader br = null;

    try {
      br = new BufferedReader(new FileReader("tempresults"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        StringTAB.add(line.replaceAll("<[^>]*>", "").replaceAll("\\s+",""));
        sb.append(line.replaceAll("<[^>]*>", "").replaceAll("\\s+",""));
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      String everything = sb.toString();
      //System.out.println(everything);

      for(int i=0; i<StringTAB.size(); i++) {
        if (StringTAB.get(i).contains(args[0]) && StringTAB.get(i+3).contains("Inscrits")) {
          if (tryParse(StringTAB.get(i + 1)) != -1 && tryParse(StringTAB.get(i + 4)) != -1) {
            int sizecomm = Integer.parseInt(StringTAB.get(i + 1));
            float percentfn = Integer.parseInt(StringTAB.get(i + 1)) * 100 / Integer.parseInt(StringTAB.get(i + 4));
            //System.out.println(StringTAB.get(i + 1)); // votes FN
            //System.out.println(StringTAB.get(i + 4)); // Taille de la commune
            System.out.println(StringTAB.get(i + 4)+","+String.valueOf(percentfn));
          }
        }
      }
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

  public static int tryParse(String text) {
    try {
      return Integer.parseInt(text);
    } catch (NumberFormatException e) {
      return -1;
    }
  }
}
