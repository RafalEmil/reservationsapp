package restful;

import database.DBservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "rest")
public class rest extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        try{
            PathMatcher pathMatcher = new PathMatcher(request.getPathInfo());
            DBservice DBserviceObject = new DBservice();
            out.println(DBserviceObject.getReservationsList(String.valueOf(pathMatcher.getSpotNumber())));
        }catch (ServletException e) {
            e.printStackTrace();
        }
        out.close();
    }
}
