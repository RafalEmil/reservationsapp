<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- HTML - Strona główna: -->

<html>
    <head>
        <title>Reservations Application</title>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
    </head>
    <body>

        <h1>Parking: rezerwacje</h1>

        <div>
            <p>W celu przejścia do formularza rezerwacji wciśnij "Rezerwuj"</p>
            <form action="/backtoreservation" method="get">
                <input type="submit" value="Rezerwuj">
            </form>
        </div>

    </body>
</html>
