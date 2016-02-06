//import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by Chad on 2016-02-04.
 */
public class Parser {

    //Get total tag count
    public int getCount(String inputLine)
    {
        int count = 0;
        if(inputLine.contains("<")){
            count++;
        }
        return count;
    }

    //Get the number of div counts
    public int findDiv(String inputLine)
    {
        int divCount = 0;

        if(inputLine.contains("<div") || inputLine.contains("/div")){
            divCount++;
        }

        return divCount;
    }

    //Get the string of link
    public String findLink(String inputLine)
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
