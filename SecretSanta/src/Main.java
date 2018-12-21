import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hashtable<String, String> emailTable = new Hashtable<>();
        CircularLinkedList santaGenerator = new CircularLinkedList();

        Scanner scanner = new Scanner(System.in);
        System.out.println("How many participants?");
        int numberOfPeople = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfPeople; i++) {
            System.out.println("Name #" + (i + 1) + ":");
            String name = scanner.nextLine();

            System.out.println("Email #" + (i + 1) + ":");
            String email = scanner.nextLine();

            santaGenerator.add(name);
            emailTable.put(name, email);
        }

        santaGenerator.generateSantas(emailTable);

    }
}

