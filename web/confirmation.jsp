<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- HTML - Potwierdzenie sukcesu rezerwacji: -->

<html>
    <head>
        <title>confirmation</title>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
    </head>
    <body>

        <h1>Rezerwacja udana!</h1>

        <div>
            <p>Podsumowanie rezerwacji:</p>
            <p>Data rozpoczęcia: ${datestart}</p>
            <p>Data zakończenia: ${dateend}</p>
            <p>Nr miejsca parkingowego: ${spotnumber}</p>
            <p>Nr telefonu: ${phonenumber}</p>
            <p>Imię i nazwisko: ${fullname}</p>
            <form action="/backtoreservation" method="get">
                <input type="submit" value="Powrót do formularza rezerwacji">
            </form>
        </div>

    </body>
</html>
