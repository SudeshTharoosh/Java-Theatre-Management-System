import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Theatre {
    static int[] row1 = new int[12];    //Array size of 12
    static int[] row2 = new int[16];   //Array size of 16
    static int[] row3 = new int[20];   //Array size of 20
    static int seat_number;     //Used as a constant variable
    static int row_number;
    static ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    public static void main(String[] args) {
        System.out.println("\nWelcome to the New Theatre");
        System.out.println("-----------------------------------------------------");
        Scanner input = new Scanner(System.in);

        Arrays.fill(row1, 0);   //Initialising all seats in row1 to 0 (0 means free seats)
        Arrays.fill(row2, 0);   //Initialising all seats in row2 to 0 (0 means free seats)
        Arrays.fill(row3, 0);   //Initialising all seats in row3 to 0 (0 means free seats)

        int option = -1;

        while(option != 0){

            //Thread sleep method
            //https://www.geeksforgeeks.org/thread-sleep-method-in-java-with-examples/
            try {
                Thread.sleep(1500); // Wait for 1.5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("\n-----------------------------------------------------");
            System.out.println("Please select an option: ");    //Menu options
            System.out.println("1) Buy a ticket");
            System.out.println("2) Print seating area");
            System.out.println("3) Cancel ticket");
            System.out.println("4) List available seats");
            System.out.println("5) Save to file");
            System.out.println("6) Load from file");
            System.out.println("7) Print ticket information and total price");
            System.out.println("8) Sort tickets by price");
            System.out.println("        0) Quit");
            System.out.println("-----------------------------------------------------\n");

            try {
                System.out.print("Enter option: ");
                option = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid option, please try again");
                input.nextLine();
                continue;       //Code restarts
            }


            switch (option) {           //Enhanced switch case
                case 1 -> buy_ticket();
                case 2 -> print_seating_area();
                case 3 -> cancel_ticket();
                case 4 -> show_available();
                case 5 -> save();
                case 6 -> load();
                case 7 -> show_tickets_info();
                case 8 -> sort_tickets();
                case 0 -> System.out.println("Thank you for using our booking system, we hope you enjoy the show!");
                default -> System.out.println("Invalid selection. Please choose a valid option from the menu above.\n");
            }
        }
    }

    static void buy_ticket() {
        Scanner input = new Scanner(System.in);
        System.out.println("We need some details to complete your booking.");
        System.out.print("Please enter name: ");
        String name = input.next();
        System.out.print("Please enter surname: ");
        String surname = input.next();
        String email = "";
        while (true) {
            System.out.print("Please enter email: ");
            email = input.next();
            //https://stackoverflow.com/questions/38159568/how-do-i-use-the-contains-method-with-if-else-statement
            if (email.contains("@") && email.contains(".")) {
                System.out.println();
                break;
            } else {
                System.out.println("Invalid email, please enter it again\n");
            }
        }


        int ticket_price = 0;
        while (true) {
            try {
                System.out.print("Ticket prices are as follows for each row:\nRow 1: $30\nRow 2: $20\nRow 3: $10\nEnter ticket price: ");
                ticket_price = input.nextInt();
                if (ticket_price == 10 || ticket_price == 20 || ticket_price == 30) {
                    break;
                } else {
                    System.out.println("Sorry, the ticket price you selected is not available. Please choose a different price.\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid ticket price, please try again\n");
                input.nextLine();
            }
        }


        Person person = new Person(name, surname, email);
        Ticket ticket;

        row_number = 0;
        while (row_number < 1 || row_number > 3) {
            try {
                System.out.print("Enter row number: ");     //Get the user input for row number
                row_number = input.nextInt();
                if (row_number < 1 || row_number > 3) {
                    System.out.println("Error: This row number does not exist. Please select 1-3.\n");
                } else {
                    System.out.println("\nThese are the number of seats for each row:\nRow 1: 12 seats\nRow 2: 16 seats\nRow 3: 20 seats");
                }
            } catch (Exception e) {
                System.out.println("Invalid row number, please try again\n");
                input.nextLine();
            }
        }

        switch (row_number) {       //Enhanced switch case
            case 1 -> {
                check_seat(row1);   //Calling the method check_seat for row1
                ticket = new Ticket(row_number, seat_number, ticket_price, person);
                tickets.add(ticket);
            }
            case 2 -> {
                check_seat(row2);
                ticket = new Ticket(row_number, seat_number, ticket_price, person);
                tickets.add(ticket);
            }
            case 3 -> {
                check_seat(row3);
                ticket = new Ticket(row_number, seat_number, ticket_price, person);
                tickets.add(ticket);
            }
            default -> System.out.println("Error: This row number does not exist. Please select 1-3.\n");
        }
    }

    static void check_seat(int[] array) {   //Method used for validation and checking availability of seats
        Scanner input = new Scanner(System.in);
        seat_number = 0;
        boolean user_input = false;
        while (!user_input) {
            try {
                System.out.print("Enter seat number: ");    //Get user input for seat number
                seat_number = input.nextInt();
                if (seat_number < 1 || seat_number > array.length) {    //Validation check for seat numbers
                    System.out.println("Invalid seat number\n");
                } else if (array[seat_number - 1] == 0) {       //Array indexes start from 0 so (seat_number - 1) is implemented
                    System.out.println("Your seat has been successfully booked. Enjoy the show!");
                    array[seat_number - 1] = 1;         //This seat has been booked, 1 means seat booked
                    user_input = true;
                } else {
                    System.out.println("Sorry, the seat you selected is already booked. Please choose another seat.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid seat number, please try again\n");
                input.nextLine();
            }
        }
    }

    static void print_seating_area() {      //Method to display the seats
        System.out.println("        ***********");
        System.out.println("        *  STAGE  *");
        System.out.println("        ***********");
        System.out.print("       ");
        print_seat(row1);
        System.out.print("     ");
        print_seat(row2);
        System.out.print("   ");
        print_seat(row3);
    }

    static void print_seat(int[] seat) {        //Method used for looping through the arrays row1,2,3
        for (int i = 0; i < seat.length/2; i++) {       //Checks the first half of the array
            if (seat[i] == 0) {         //Check for seat availability
                System.out.print("O");      //Seats are free
            } else {
                System.out.print("X");      //Seats are booked
            }
        }
        System.out.print(" ");  //Leaves a space after the first loop
        for (int i = seat.length/2; i < seat.length; i++) {     //Checks the second half of the array
            if (seat[i] == 0) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
        }
        System.out.println();   //Leaves space
    }

    static void cancel_ticket() {       //Have to check
        System.out.println("In order to cancel the ticket");
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Please enter row number: ");
            row_number = input.nextInt();
            System.out.print("Please enter seat number: ");
            seat_number = input.nextInt();
            if (row_number == 1) {
                cancel(row1);
            } else if (row_number == 2) {
                cancel(row2);
            } else if (row_number == 3) {
                cancel(row3);
            } else {
                System.out.println("Invalid row number");
            }
        } catch (Exception e) {
            System.out.println("Sorry, the row or seat number you entered is not valid. Please try again.");
        }

    }

    static void cancel(int[] booked_seat) {     //Method to check whether seats are booked or not
        if (booked_seat[seat_number - 1] == 0) {        //seat_number - 1 because of array indexes as they start from 0
            System.out.println("This seat is available, do you want to book it now?");
        } else if (booked_seat[seat_number - 1] == 1) {     //Seat booked
            for (Ticket ticket: tickets) {
                if (ticket.getRowNumber() == row_number && ticket.getSeatNumber() == seat_number) {
                    tickets.remove(ticket);
                    booked_seat[seat_number - 1] = 0;
                    System.out.println("The ticket has been successfully removed from the booking system.");
                    break;
                }
            }
        } else {
            System.out.println("Sorry, that input is not valid. Please try again.");
        }
    }

    static void show_available() {
        System.out.print("Seats available in row 1: ");
        available_seats(row1);      //Calling the method available_seats
        System.out.print("\nSeats available in row 2: ");
        available_seats(row2);
        System.out.print("\nSeats available in row 3: ");
        available_seats(row3);
        System.out.println();
    }

    static void available_seats(int[] show_seat) {      //Method to print the available seat numbers
        for (int i = 0; i < show_seat.length; i++) {
            if (show_seat[i] == 0) {
                System.out.print(i + 1);    //(i + 1) since array starts from 0, prints the seat numbers
            } else continue;
            if (show_seat.length - 1 == i) {    //Loops till last element
                System.out.print(".");
            } else {
                System.out.print(", ");
            }
        }
    }

    //Referred Lecture Notes on File Handling
    //https://www.w3schools.com/java/java_files_create.asp
    static void save() {        //Store data to a file
        try {
            FileWriter writer = new FileWriter("data.txt");
            writer.write(Arrays.toString(row1) + "\n");
            writer.write(Arrays.toString(row2) + "\n");
            writer.write(Arrays.toString(row3) + "\n");
            System.out.println("File saved successfully");
            writer.close();     //Close the connection between the code and the external file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Referred Lecture Notes on File Handling
    //https://www.w3schools.com/java/java_files_read.asp
    static void load() {        //load data from a file
        try {
            File myfile = new File("data.txt");
            Scanner reader = new Scanner(myfile);
            System.out.println("The data in the file has been loaded into the system.");
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
        }catch (FileNotFoundException e){       //Checks whether the file is available or not
            System.out.println("File not found");
        }
    }

    static void show_tickets_info() {
        int total_price = 0;
        for(Ticket ticket:tickets){
            ticket.print();
            total_price += ticket.getTicket_price();
            System.out.println();   //Leave space
        }
        System.out.println("Total ticket price: " + total_price);
    }

    static void sort_tickets() {
        //Bubble sort
        //Size method is used on Array list
        //https://www.geeksforgeeks.org/bubble-sort/
        for (int i = 0; i < tickets.size(); i++) {      //Loops through all the elements in the list
            for (int j = 0; j < tickets.size() - i - 1; j++) {      //Compares the elements and swaps it if it's in wrong order
                if (tickets.get(j).getTicket_price() > tickets.get(j+1).getTicket_price()){
                    Ticket temp = tickets.get(j);       //Swapped here
                    tickets.set(j, tickets.get(j + 1));     //Setting places
                    tickets.set(j + 1, temp);
                }
            }
        }
        System.out.println("You can now view the sorted list: \n");
        for (Ticket ticket:tickets){
            ticket.print();
            System.out.println();
        }
    }
}
