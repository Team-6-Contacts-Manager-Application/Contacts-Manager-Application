import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //create data directory - DONE
    //create data file if doesn't exit - DONE

    //application is opened - DONE
    //contacts preloaded
    //call main menu/show it to user - DONE
    //take user input and execute
        //view contacts - call and list contacts
        //add new contact - DONE-ISH
        // search by name
        //delete exiting contact
        //exit
        //loop to keep calling displayMenu until hits exit - later

    //SHOWS MENU TO USER
    public static void displayMenu() throws IOException {
        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):");
        Scanner sc = new Scanner(System.in);
        int userChoice = sc.nextInt();
        System.out.println(userChoice);

            switch(userChoice) {
                case 1:
                    viewAllContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    searchContacts();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    break;
            }
    }


    //CREATE/CONFIRM DIRECTORY EXISTS
    public static void confirmDirectory(){

    }

    //VIEW ALL CONTACTS
    public static void viewAllContacts(){

    }

    //PRELOAD CONTACTS
    public static void loadContact() throws IOException {
        String directory = "data";
        String filename = "contact.txt";

        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);

        if (Files.notExists(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }

        if (! Files.exists(dataFile)) {
            Files.createFile(dataFile);
        }
    }

    //ADD A NEW CONTACT
    public static void addNewContact() throws IOException {
        //CREATES NEW CONTACT OBJECT
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.println("Enter last Name: ");
        String lastName = sc.nextLine();
        System.out.println("Enter Phone Number: ");
        String phoneNumber = sc.nextLine();

        Contact contact = new Contact(firstName, lastName, phoneNumber);

        //ADD CONTACT OBJECT TO CONTACT.TEXT
        String directory = "data";
        String filename = "contact.txt";

        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);

        ArrayList<String> fileContents = new ArrayList<String>();
        fileContents.add(contact.getFirstName() + " " + contact.getLastName() + "  |  " + contact.getNumber());

        Files.write(dataFile, fileContents);

    }

    //SEARCH CONTACTS BY NAME
    public static void searchContacts(){

    }

    //DELETES CONTACT
    public static void deleteContact(){

    }

    //MAIN METHOD
    public static void main(String[] args) throws IOException {
        loadContact();
        displayMenu();
    }


}
