package org.intecont.distrib;

import java.util.Scanner;


public class DistributeurBillet {
    private static final int MONTANT_MAX = 100;
    private static final int[] COUPURES = {50, 20, 10, 5};

    private static final String[] PINS = {"1234", "5678", "9012"};
    private static final String[] CB_NUMEROS = {"1111 2222 3333 4444", "5555 6666 7777 8888", "9999 0000 1111 2222"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Demander le numéro de carte bancaire et le code PIN
        System.out.print("Entrez le numéro de carte bancaire : ");
        String cbNumero = scanner.nextLine();
        System.out.print("Entrez le code PIN : ");
        String pin = scanner.nextLine();

        // Vérifier que le numéro de carte bancaire et le code PIN sont valides
        int index = -1;
        for (int i = 0; i < CB_NUMEROS.length; i++) {
            if (cbNumero.equals(CB_NUMEROS[i]) && pin.equals(PINS[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Carte bancaire invalide.");
            return;
        }

        // Demander le montant à retirer
        System.out.print("Entrez le montant à retirer (maximum " + MONTANT_MAX + " euros) : ");
        int montant = scanner.nextInt();
        if (montant > MONTANT_MAX) {
            System.out.println("Montant invalide.");
            return;
        }

        // Calculer le nombre de billets de chaque coupure
        int[] billets = new int[COUPURES.length];
        int reste = montant;
        for (int i = 0; i < COUPURES.length; i++) {
            billets[i] = reste / COUPURES[i];
            reste %= COUPURES[i];
        }

        // Vérifier que le distributeur a suffisamment de billets
        if (reste != 0 || billets[0] > 2) {
            System.out.println("Distributeur de billets en panne.");
            return;
        }

        // Distribuer les billets
        System.out.println("Veuillez patienter, vos billets sont en cours de distribution...");
        for (int i = 0; i < COUPURES.length; i++) {
            for (int j = 0; j < billets[i]; j++) {
                System.out.println("Billet de " + COUPURES[i] + " euros");
            }
        }
        System.out.println("Retrait de " + montant + " euros effectué avec succès.");
    }
}

