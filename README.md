The software is a Java-based car reservation system created using the NetBeans IDE. The software follows the MVC (Model-View-Controller) model and represents a client-server architecture.

It consists of a Client that includes the user interface, which is made up of jForm and jDialog, as well as the Communication class that handles interaction with the server, where also receives a response from the server. Additionally, separate table model classes (Abstract Table Model) have been created for each object that requires listing and manipulation.

On the server side, within the package, there are classes responsible for starting the server, refreshing threads, and a request processing class that identifies which client request should be forwarded to the DBBroker for execution.

The 'logic' subpackage contains the Controller class, which forwards each execution request to the DBBroker and implements the Singleton design pattern.

In the 'database' package, there is the Connection class, which implements the JDBC API for database communication and query execution. The database is relational, and SQLYog was used to define all attributes, their types, constraints, and interdependencies.

The 'Common' project contains the 'transfer', 'classes', and 'constants' packages.

The 'constants' package consists of an interface defining operations that are invoked when sending a client request.
The 'transfer' package contains the ClientRequest and ServerResponse classes, which facilitate communication between the client and server.
The 'classes' package includes the following entities:

-Employee (Zaposleni)

-WorkShift (RadnaSmena)

-WorkSchedule (ZRs - displaying employee shift schedules)

-Location (Mesto)

-Client (Klijent)

-Vehicle (Vozilo)

-Reservations (Rezervacije)

-ReservationItem (StavkaRezervacije)

Each class implements the Serializable interface to ensure successful communication.
