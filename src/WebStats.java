import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Scanner;

public class WebStats{



    public static void main(String[] args) {

        URL url;
        Scanner in = new Scanner(System.in);
        int count = 0, pageCount = 2, htmlCount = 0, headCount = 0, metaCount = 0,
                titleCount = 0, linkCount = 0, divCount = 0, ulCount = 0, liCount = 0,
                navCount = 0, pcount = 0;

        Parser p = new Parser();

        String[] a = new String[40];
        Arrays.fill(a, "");
        a[0]= "http://www.scs.ryerson.ca/~cbyu/index.html";







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
                    count += p.getCount(inputLine);
                    divCount += p.findDiv(inputLine);
                    System.out.println(p.findLink(inputLine));
                    a[1] = p.findLink(inputLine);
                }
                br.close();
                System.out.print(a[1]);


                System.out.println("Done");
                System.out.println("There are " + count + " starting HTML tags on this page");
                System.out.println("There are " + divCount + " div tags on this page");


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

}