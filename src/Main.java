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

    //SHOWS MENU TO USER
    public static void displayMenu() throws IOException {
        System.out.println("\n1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n");

        int userChoice = getInt(1, 5);

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

    //get user int choice, checks if int & if is a valid choice
    public static int getInt(int min, int max){
        System.out.println("Enter an option (1, 2, 3, 4 or 5):");
        Scanner sc = new Scanner(System.in);
        int choice;

        try{
            choice = Integer.valueOf(sc.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Error: Input is not an integer!");
            return getInt(min, max);
        }
        if(choice >= min && choice <= max){
            return choice;
        }
        else{
            System.out.println("Error: Choice is not Recognized!");
            return getInt(min, max);
        }
    }


    //VIEW ALL CONTACTS
    public static void viewAllContacts() throws IOException{
        Path contactPath = Paths.get("data","contact.txt");
        List<String> contactList = Files.readAllLines(contactPath);

        //gets the longest name/string and uses that to determine spacing
        int longest = 0;
        for(int i = 0; i < contactList.size(); i++){
            String fullContactInfo = contactList.get(i);
            String [] arrOfContacts = fullContactInfo.split("\\|", 2);

            String fullName = arrOfContacts[0];
            if(fullName.length() >= longest){
                longest = fullName.length();
            }
        }

        String name = "Name";
        String phoneNumber = "Phone Number";
        System.out.printf(("%-" + (longest + 1) + "s| %-12s |\n"), name, phoneNumber);

        for(int i = 0; i <= longest + 16; i++){
            System.out.print("-");
        }
        System.out.print("\n");

        //prints all contacts with formatting
        for (int i = 0; i < contactList.size(); i ++) {
            String fullContactInfo = contactList.get(i);
            String [] arrOfContacts = fullContactInfo.split("\\|", 2);

            name = arrOfContacts[0];
            phoneNumber = arrOfContacts[1];

            System.out.printf(("%-" + (longest + 1) + "s| %-12s |\n"), name, phoneNumber);
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

    //Gets String
    public static String getString(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    //gets phone number, checks if is valid number, checks length & formats with dashes
    public static String getPhoneNumber() {
        System.out.println("Enter Phone Number: ");
        Scanner sc = new Scanner(System.in);
        int number = 0;
        try {
            number = Integer.valueOf(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Phone Number must contain only numbers!");
            getPhoneNumber();
        }
        
        String phoneNumber = Integer.toString(number);

        if(phoneNumber.length() == 7) {
            phoneNumber = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3);
        }

        else if(phoneNumber.length() == 10) {
            phoneNumber = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3,6) + "-" + phoneNumber.substring(6);
        }
        else{
            System.out.println("Error: Phone Numbers must be 7 or 10 digits long!");
            getPhoneNumber();
        }
        return phoneNumber;
    }

    //checks yes or no answer
    public static boolean yesNo(){
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        if(answer.contains("y") || answer.contains("Y")){
            return true;
        }
        else if(!answer.contains("y") && !answer.contains("Y") && !answer.contains("n") && answer.contains("N")){
            System.out.println("Error: Choice not recognized. Please enter y or n!");
            yesNo();
        }
      return false;
    }

    //ADD A NEW CONTACT
    public static void addNewContact() throws IOException {
        //CREATES NEW CONTACT OBJECT
        System.out.println("Enter First Name: ");
        String firstName = getString();
        System.out.println("Enter last Name: ");
        String lastName = getString();
        String phoneNumber = getPhoneNumber();

        Contact contact = new Contact(firstName, lastName, phoneNumber);

        //checks if user already exists
        ArrayList<String> fileContents = new ArrayList<String>();
        List<String> contacts = Files.readAllLines(Paths.get("data", "contact.txt"));
        boolean alreadyExists = false;

        for (String line: contacts){
            //checks if user input already exists as a contact
            if(line.contains(firstName + " " + lastName)){
                System.out.println("User Already Exists. Do you want to override & continue? (y/n)");

                //if user exists, checks if wants to continue
                if(yesNo()){
                    System.out.println("User will be added!");
                    fileContents.add(contact.getFirstName() + " " + contact.getLastName() + "|" + contact.getNumber());
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
            fileContents.add(contact.getFirstName() + " " + contact.getLastName() + "|" + contact.getNumber());
        }
        Files.write(Paths.get("data", "contact.txt"), fileContents);

        displayMenu();
    }


    //SEARCH CONTACTS BY NAME
    public static void searchContacts() throws IOException {
        System.out.println("Enter the name of the contact you want search for: ");
        String contactToSearch = getString();

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
        String contactToDelete = getString();

        System.out.println("Are you sure you want to delete " + contactToDelete + " ?");
        if(yesNo()){
            List<String> contacts = Files.readAllLines(Paths.get("data", "contact.txt"));
            List<String> newContacts = new ArrayList<>();

            for (String contact  : contacts){
                if(!contact.contains(contactToDelete)){
                    newContacts.add(contact);
                }
            }

            Files.write(Paths.get("data", "contact.txt"), newContacts);
        }
        displayMenu();
    }

    //MAIN METHOD
    public static void main(String[] args) throws IOException {
        confirmDirectory();
        displayMenu();
    }
}
