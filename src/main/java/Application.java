import it.unicam.cs.ids25.model.Produttore;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Benvenuto, scrivi il nome dell'azienda: ");
        String nome = scanner.nextLine();
        System.out.println("Scrivi l'indirizzo della sede: ");
        String sede = scanner.nextLine();

        Produttore p = new Produttore(nome, sede);
        int input;

        do{
        System.out.println("\n\n1 - Crea Prodotto \n2 - Vedi prodotti caricati \n3 - Esci");

            input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:
                    p.creaProdotto();
                    break;
                case 2:
                    p.vediProdottiCaricati();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Selezione non valida");
                    break;
            }
        }while(input != 3);
    }
}