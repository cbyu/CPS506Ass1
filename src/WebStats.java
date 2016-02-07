import com.sun.deploy.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map.Entry;

public class WebStats {

   public static Map<String, Integer> tags = new HashMap<String, Integer>();
    public static void main(String[] args) {


        URL url;
        Scanner in = new Scanner(System.in);
        int count = 0, pageCount = 2, htmlCount = 0, headCount = 0, metaCount = 0,
                titleCount = 0, linkCount = 0, divCount = 0, ulCount = 0, liCount = 0,
                navCount = 0, pcount = 0;


        Parser p = new Parser();

        String[] a = new String[40];
        Arrays.fill(a, "");
        a[0] = "http://www.scs.ryerson.ca/~cbyu";
        //a[0] = "http://www.scs.ryerson.ca/~smsegal/simple.html";


        try {
            // get URL content


            url = new URL(a[0]);

            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                // System.out.println(inputLine);
               String tagFinal = getAllTags(inputLine,tags);

                //System.out.println(findLink(inputLine));


            }
            br.close();

            //Iterate through map.
            for (Map.Entry<String, Integer> entry : WebStats.tags.entrySet()) {
                System.out.println("key is: " + entry.getKey() + " & the value is: " + entry.getValue());
            }
            
            System.out.println("There are " + count + " starting HTML tags on this page");
            System.out.println("There are " + divCount + " div tags on this page");


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String getAllTags(String inputLine, Map tags) {
        try {

            if (inputLine.contains("<")) {
                inputLine = inputLine.substring(inputLine.indexOf("<") + 1);
                inputLine = inputLine.substring(0, inputLine.indexOf(">"));
                if (inputLine.contains(" ")) {
                    inputLine = inputLine.substring(0, inputLine.indexOf(" "));
                    //System.out.println(inputLine);
                }
                if (inputLine.contains("/")) {
                    inputLine = inputLine.substring(1, inputLine.length());
                }

            if(!tags.containsKey(inputLine)){
                tags.put(inputLine,1);
            }
                else {

                    tags.put(inputLine, (Integer)(tags.get(inputLine)) +1);
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            //System.err.println("Unrecognized tag by my code");
        }

        return inputLine;
    }


    public static String findLink(String inputLine)
    {
        String pattern = "\\\"https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)\\\"";
        String webSite = "";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(inputLine);

        if(m.find())
        {
            webSite = m.group(0);
        }
        return webSite;
    }


}