import java.util.Scanner;

public class Main {
    //create data directory
    //create data file if doesn't exit

    //application is opened
    //contacts preloaded
    //call main menu/show it to user
    //take user input and execute
        //view contacts - call and list contacts
        //add new contact
        // search by name
        //delete exiting contact
        //exit
        //loop to keep calling displayMenu until hits exit - later

    //SHOWS MENU TO USER
    public static void displayMenu(){
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

    //PRELOAD CONTACTS
    public static void loadContact(){
    }

    //VIEW ALL CONTACTS
    public static void viewAllContacts(){

    }

    //ADD A NEW CONTACT
    public static void addNewContact(){

    }

    //SEARCH CONTACTS BY NAME
    public static void searchContacts(){

    }

    //DELETES CONTACT
    public static void deleteContact(){

    }

    //MAIN METHOD
    public static void main(String[] args){
        displayMenu();
    }


}
