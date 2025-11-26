import models.Film;
import models.Seance;
import models.Spectateur;
import models.Ticket;

import java.sql.Connection;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        FilmDAO filmDAO = new FilmDAO();
        SpectateurDAO spectateurDAO = new SpectateurDAO();
        SeanceDAO seanceDAO = new SeanceDAO();
        TicketDAO ticketDAO = new TicketDAO();

        int choice;

        do {
            System.out.println("============== CINEMA MENU =================");
            System.out.println("1-   Add Film");
            System.out.println("2-   Add Seance");
            System.out.println("3-   Add Spectateur");
            System.out.println("4-   Add Ticket");
            System.out.println("5-   Show All Films");
            System.out.println("6-   Show All Séances");
            System.out.println("7-   Show All Spectateurs");
            System.out.println("8-   Show All Tickets");
            System.out.println("9-   Delete Spectateur");
            System.out.println("10-  update Spectateur");
            System.out.println("0-   Exit");
            System.out.println("============================================");
            System.out.print("Enter choice : ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("=== Add Film ===");
                    System.out.print("ID: ");
                    int idF = input.nextInt(); input.nextLine();
                    System.out.print("Titre: ");
                    String titre = input.nextLine();
                    System.out.print("Durée: ");
                    int duree = input.nextInt(); input.nextLine();
                    System.out.print("Catégorie: ");
                    String cat = input.nextLine();

                    Film film = new Film(idF, titre, duree, cat);
                    filmDAO.ajouterFilm(film);
                    break;
                case 2:
                    System.out.println("=== Add Séance ===");
                    System.out.print("ID: ");
                    int idS = input.nextInt(); input.nextLine();
                    System.out.print("Film ID: ");

                    int filmId = input.nextInt(); input.nextLine();
                    Film filmobj = null;

                    for (Film f : filmDAO.getAllFilms()) {
                        if (f.getId() == filmId) {
                            filmobj = f;
                            break;
                        }
                    }


                    if (filmobj == null) {
                        System.out.println("Film not found.");
                        break;
                    }
                    System.out.print("Horaire: ");
                    String horaire = input.nextLine();
                    System.out.print("Salle: ");
                    String salle = input.nextLine();
                    System.out.print("Capacité max: ");
                    int cap = input.nextInt(); input.nextLine();

                    Seance seance = new Seance(idS, filmobj, horaire, salle, cap);
                    seanceDAO.ajouterSeance(seance);
                    break;

                case 3:
                    System.out.println("=== Add Spectateur ===");
                    System.out.print("ID: ");
                    int idSp = input.nextInt(); input.nextLine();
                    System.out.print("Nom: ");
                    String nom = input.nextLine();
                    System.out.print("Email: ");
                    String email = input.nextLine();

                    Spectateur spect = new Spectateur(idSp, nom, email);
                    spectateurDAO.ajouterSpectateur(spect);
                    break;

                case 4:
                    System.out.println("=== Add Ticket ===");
                    System.out.print("Numéro ticket: ");
                    int numT = input.nextInt();
                    System.out.print("Séance ID: ");
                    int idSe = input.nextInt();
                    Seance sec = null;

                    for (Seance s : seanceDAO.getAllSeances()) {
                        if (s.getId() == idSe) {
                            sec = s;
                            break;
                        }
                    }


                    if (sec == null) {
                        System.out.println("Seance not found.");
                        break;
                    }
                    System.out.print("Spectateur ID: ");
                    int idSpect = input.nextInt();

                    Spectateur specte = null;

                    for (Spectateur spts : spectateurDAO.getAllSpectateurs()) {
                        if (spts.getId() == idSpect) {
                            specte = spts;
                            break;
                        }
                    }


                    if (specte == null) {
                        System.out.println("Spectateur not found.");
                        break;
                    }

                    System.out.print("Prix: ");
                    double prix = input.nextDouble();

                     Ticket ticket = new Ticket(numT, sec, specte, prix);

                    ticketDAO.ajouterTicket(ticket);
                    break;

                case 5:
                    System.out.println("=== FILMS ===");
                    for (Film f : filmDAO.getAllFilms()) {
                        System.out.println(f);
                    }
                    break;

                case 6:
                    System.out.println("=== Séances ===");
                    for (Seance sea : seanceDAO.getAllSeances()) {
                        System.out.println(sea);
                    }
                    break;

                case 7:
                    System.out.println("=== Spectateurs ===");

                    for (Spectateur se : spectateurDAO.getAllSpectateurs()) {
                        System.out.println(se);
                    }
                    break;

                case 8:
                    System.out.println("=== Tickets ===");
                    for (Ticket t : ticketDAO.getAllTickets()) {
                        System.out.println(t);
                    }
                    break;
                case 9:
                    System.out.println("=== Delete Spectateur ===");
                    System.out.print("ID Spectateur supprimer : ");
                    int id = input.nextInt();
                    input.nextLine();

                    boolean deleted = spectateurDAO.deleteSpectateur(id);

                    if (deleted) {
                        System.out.println("Spectateur supprime succes ");
                    } else {
                        System.out.println("Spectateur introuvable ");
                    }
                    break;
                case 10:
                    System.out.println("=== Update Spectateur ===");
                    System.out.print("ID du spectateur : ");
                    int idN = input.nextInt();
                    input.nextLine();

                    System.out.print("Nouveau nom : ");
                    String nomNew = input.nextLine();

                    System.out.print("Nouvel email : ");
                    String emailNew = input.nextLine();


                    Spectateur s = new Spectateur(idN, nomNew, emailNew);

                    boolean updated = spectateurDAO.updateSpectateur(s);

                    if (updated) {
                        System.out.println("Spectateur Update succes");
                    } else {
                        System.out.println("Aucun spectateur ");
                    }

                    break;


                case 0:
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Invalid choice !");
            }

            System.out.println("\n");

        } while (choice != 0);

        input.close();
    }
}
