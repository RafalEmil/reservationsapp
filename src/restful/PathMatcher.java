package restful;

import javax.servlet.ServletException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathMatcher {

    private Pattern allListPattern = Pattern.compile("/list");
    private Pattern oneListPattern = Pattern.compile("/list/([0-9]*)");
    private Integer spotNumber;

    public PathMatcher(String pathInfo) throws ServletException {

        Matcher matcher;

        matcher = oneListPattern.matcher(pathInfo);
        if (matcher.find()) {
            spotNumber = Integer.parseInt(matcher.group(1));
            return;
        }

        matcher = allListPattern.matcher(pathInfo);
        if (matcher.find()){
            return;
        }

        throw new ServletException("Invalid URI");
    }

    public Integer getSpotNumber() {
        return spotNumber;
    }
}