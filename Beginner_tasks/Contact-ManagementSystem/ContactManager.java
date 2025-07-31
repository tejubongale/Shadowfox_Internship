import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

class Contact {
    String name;
    String phone;
    String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public void display() {
        System.out.println("Name : " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("----------------------------");
    }
}

public class ContactManager {
    static ArrayList<Contact> contacts = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== CONTACT MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Search Contact by Name");
            System.out.println("6. Show Total Contacts");
            System.out.println("7. Exit");
            System.out.print("Choose an option (1-7): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: addContact(); break;
                case 2: viewContacts(); break;
                case 3: updateContact(); break;
                case 4: deleteContact(); break;
                case 5: searchContact(); break;
                case 6: System.out.println("Total Contacts: " + contacts.size()); break;
                case 7: System.out.println("Thank you for using Contact Manager!"); break;
                default: System.out.println("Invalid choice. Try again!");
            }
        } while (choice != 7);
    }

    public static void addContact() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        String phone;
        while (true) {
            System.out.print("Enter phone (10-12 digits): ");
            phone = scanner.nextLine();
            if (Pattern.matches("\\d{10,12}", phone)) break;
            else System.out.println(" Invalid phone number!");
        }

        String email;
        while (true) {
            System.out.print("Enter email (must end with @gmail.com): ");
            email = scanner.nextLine();
            if (email.endsWith("@gmail.com")) break;
            else System.out.println(" Email must end with @gmail.com!");
        }

        contacts.add(new Contact(name, phone, email));
        System.out.println(" Contact added successfully.");
    }

    public static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println(" No contacts found.");
        } else {
            System.out.println("\n Contact List:");
            for (Contact c : contacts) {
                c.display();
            }
        }
    }

    public static void updateContact() {
        System.out.print("Enter the name of the contact to update: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (Contact c : contacts) {
            if (c.name.equalsIgnoreCase(name)) {
                System.out.print("Enter new phone: ");
                String newPhone;
                while (true) {
                    newPhone = scanner.nextLine();
                    if (Pattern.matches("\\d{10,12}", newPhone)) break;
                    else System.out.println(" Invalid phone number!");
                }

                System.out.print("Enter new email: ");
                String newEmail;
                while (true) {
                    newEmail = scanner.nextLine();
                    if (newEmail.endsWith("@gmail.com")) break;
                    else System.out.println(" Email must end with @gmail.com!");
                }

                c.phone = newPhone;
                c.email = newEmail;
                System.out.println(" Contact updated.");
                found = true;
                break;
            }
        }

        if (!found) System.out.println("Contact not found.");
    }

    public static void deleteContact() {
        System.out.print("Enter the name of the contact to delete: ");
        String name = scanner.nextLine();
        boolean removed = contacts.removeIf(c -> c.name.equalsIgnoreCase(name));

        if (removed)
            System.out.println(" Contact deleted.");
        else
            System.out.println(" Contact not found.");
    }

    public static void searchContact() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (Contact c : contacts) {
            if (c.name.equalsIgnoreCase(name)) {
                System.out.println(" Contact Found:");
                c.display();
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println(" Contact not found.");
    }
}
