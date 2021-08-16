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
        //exit - DONE
        //loop to keep calling displayMenu until hits exit - DONE

    //DONE
    //    Warn the user when she tries to enter a contact with an existing name.
    //    There's already a contact named Jane Doe. Do you want to overwrite it? (Yes/No)
    //    If the answer is No allow the user to enter the information again.

    //TODO
//    Format the phone numbers using dashes:
//    instead of 8675309, your output should display 867-5309
//    Allow formatting phone numbers with different lengths. For example, phone numbers can have 10 or 7 digits. You can be even more creative here and allow international phone numbers.

    //TODO
//    Format the output of the contacts, so that all of the columns have the same width.
//            Name       | Phone number |
//            ---------------------------
//    Jack Blank | 210-567-8923 |
//    Jane Doe   | 789-8902     |
//    Sam Space  | 210-581-8123 |
//    Hint: you can use format strings with the System.our.printf or String.format methods to ensure the columns have the same width.

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
                    System.out.println("Closing application now. Goodbye!");
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
        displayMenu();
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

        //checks if user already exists
        ArrayList<String> fileContents = new ArrayList<String>();
        List<String> contacts = Files.readAllLines(Paths.get("data", "contact.txt"));
        boolean alreadyExists = false;

        for (String line: contacts){

            //checks if user input already exists as a contact
            if(line.contains(firstName + " " + lastName)){
                System.out.println("User Already Exists. Do you want to continue?");
                String continueAddUser = sc.nextLine();

                //if user exists, checks if wants to continue
                if(continueAddUser.contains("y") || continueAddUser.contains("Y")){
                    System.out.println("User will be added!");
                    fileContents.add(contact.getFirstName() + " " + contact.getLastName() + "  |  " + contact.getNumber());
                }
                else{
                    fileContents.add(line);
                }
                alreadyExists = true;
            }
            else{
                fileContents.add(line);
            }
        }

        if(!alreadyExists){
            fileContents.add(contact.getFirstName() + " " + contact.getLastName() + "  |  " + contact.getNumber());
        }
        Files.write(Paths.get("data", "contact.txt"), fileContents);
        displayMenu();
    }

    //SEARCH CONTACTS BY NAME
    public static void searchContacts() throws IOException {
        System.out.println("Enter the name of the contact you want search for: ");
        Scanner sc = new Scanner(System.in);
        String contactToSearch = sc.nextLine();

        boolean inDatabase = false;
        List<String> contacts = Files.readAllLines(Paths.get("data", "contact.txt"));
        for (String contact  : contacts){
            if((contact.toLowerCase()).contains(contactToSearch.toLowerCase())){
                System.out.println(contact);
                inDatabase = true;
            }
        }
        if (!inDatabase) {
            System.out.println("User could not be found in Contacts.");
        }
        displayMenu();
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
        displayMenu();
    }

    //MAIN METHOD
    public static void main(String[] args) throws IOException {
        confirmDirectory();
        displayMenu();

    }


}
