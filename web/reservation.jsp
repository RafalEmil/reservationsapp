<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- HTML - Formularz rezerwacji: -->

<html>
    <head>
        <title>reservation</title>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
    </head>
    <body>

        <h1>Formularz rezerwacji:</h1>

        <div>
            <form id="reservationform" action="/reservation" method="post">
                <span>Data rozpoczęcia: </span> <input id="datestart" type="date" name="datestart"><br>
                <span>Data zakończenia: </span> <input type="date" name="dateend"><br>
                <span>Nr miejsca parkingowego: </span> <input type="number" name="spotnumber"><br>
                <span>Nr telefonu: </span> <input type="text" name="phonenumber"><br>
                <span>Imię i nazwisko: </span> <input type="text" name="fullname"><br>
                <input type="submit" value="Dokonaj rezerwacji">
            </form>
            <p id="errormessage">${errorMessage}</p>
        </div>

        <!-- JavaScript & jQuery - Walidacja poprawności formularza rezerwacji: -->

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/additional-methods.js"></script>

        <script>

            $(document).ready(function () {

                jQuery.validator.addMethod("dateLogic", function(value, element, params) {
                        if (!/Invalid|NaN/.test(new Date(value))) {
                            return new Date(value) >= new Date($(params).val());
                        }
                        return isNaN(value) && isNaN($(params).val())
                            || (Number(value) >= Number($(params).val()))
                });

                $("#reservationform").validate({ // initialize the plugin
                    rules: {
                        datestart: {
                            required: true,
                            date: true
                        },
                        dateend: {
                            required: true,
                            date: true,
                            dateLogic: "#datestart"
                        },
                        spotnumber: {
                            required: true,
                            integer : true,
                            range: [0, 999999999]
                        },
                        phonenumber: {
                            required: true,
                            range: [110000000, 999999999]
                        },
                        fullname: {
                            required: true,
                            maxlength: 45
                        }
                    },
                    messages: {
                        datestart: {
                            required: " należy wprowadzić datę",
                            date: " należy wprowadzić poprawną datę"
                        },
                        dateend: {
                            required: " należy wprowadzić datę",
                            date: " należy wprowadzić poprawną datę",
                            dateLogic: " niepoprawna data zakończenia"
                        },
                        spotnumber: {
                            required: " należy wybrać nr miejsca",
                            integer : " należy wybrać nr miejsca (cyfrę)",
                            range: " miejsca są numerowane od 0 do 99999999999"
                        },
                        phonenumber: {
                            required: " należy wprowadzić nr telefonu",
                            range: " wprowadź poprawny 9-cyfrowy nr telefonu"
                        },
                        fullname: {
                            required: " należy wprowadzić imię i nazwisko",
                            maxlength: " należy wprowadzić imię i nazwisko krótsze niż 45 znaków"
                        }
                    }
                });
            });

        </script>

    </body>
</html>
