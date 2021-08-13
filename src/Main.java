import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    //create data directory - DONE
    //create data file if doesn't exit - DONE

    //application is opened - DONE
    //call main menu/show it to user - DONE
    //take user input and execute
        //view contacts - call and list contacts - DONE
        //add new contact - DONE
        //search by name - DONE
        //delete exiting contact - DONE
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


    //VIEW ALL CONTACTS
    public static void viewAllContacts() throws IOException{
        Path contactPath = Paths.get("data","contact.txt");
        List<String> contactList = Files.readAllLines(contactPath);

        for (int i = 0; i < contactList.size(); i += 1) {
            System.out.println(contactList.get(i));

        }
    }

    //CREATE/CONFIRM DIRECTORY EXISTS
    public static void confirmDirectory() throws IOException {
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

            Files.write(dataFile,  fileContents, StandardOpenOption.APPEND);

        //displayMenu();
    }

    //SEARCH CONTACTS BY NAME
    public static void searchContacts() throws IOException {
        System.out.println("Enter the name of the contact you want search for: ");
        Scanner sc = new Scanner(System.in);
        String contactToSearch = sc.nextLine();

        List<String> contacts = Files.readAllLines(Paths.get("data", "contact.txt"));
        for (String contact  : contacts){
            if(contact.contains(contactToSearch)){
                System.out.println(contact);
            }
        }
    }

    //DELETES CONTACT
    public static void deleteContact() throws IOException {

        System.out.println("Enter the first name of the contact you want to delete: ");
        Scanner sc = new Scanner(System.in);
        String contactToDelete = sc.nextLine();

        List<String> contacts = Files.readAllLines(Paths.get("data", "contact.txt"));
        List<String> newContacts = new ArrayList<>();

        for (String contact  : contacts){
            if(!contact.contains(contactToDelete)){
                newContacts.add(contact);
            }
        }

        Files.write(Paths.get("data", "contact.txt"), newContacts);

    }

    //MAIN METHOD
    public static void main(String[] args) throws IOException {
        confirmDirectory();
        displayMenu();

    }


}
