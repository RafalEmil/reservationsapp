package webapp;

import database.DBservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "reservation")
public class reservation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DBservice DBserviceObject = new DBservice();
        String dateStart = request.getParameter("datestart");
        String dateEnd = request.getParameter("dateend");
        String spotNumber = request.getParameter("spotnumber");
        String phoneNumber = request.getParameter("phonenumber");
        String fullName = request.getParameter("fullname");

        if(DBserviceObject.checkAvailability(spotNumber, dateStart, dateEnd)) { // sprawdzanie dostępności terminu
            DBserviceObject.addReservation(fullName, phoneNumber, spotNumber, dateStart, dateEnd);
            request.setAttribute("datestart", dateStart);
            request.setAttribute("dateend", dateEnd);
            request.setAttribute("spotnumber", spotNumber);
            request.setAttribute("phonenumber", phoneNumber);
            request.setAttribute("fullname", fullName);
            request.getRequestDispatcher("/confirmation.jsp").forward(request, response);
        }
        else{
            request.setAttribute("errorMessage", "Termin zajęty, proszę wybrać inny.");
            request.getRequestDispatcher("/reservation.jsp").forward(request, response);
        }
    }
}
