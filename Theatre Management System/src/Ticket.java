public class Ticket extends Person {
    private int rowNumber;
    private int seatNumber;
    private double ticket_price;
    private final Person person;
    public Ticket(int rowNumber, int seatNumber, double ticket_price, Person person) {    //Building a constructor
        super(person.getName(), person.getSurname(), person.getEmail());
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.ticket_price = ticket_price;
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public double getTicket_price() {
        return ticket_price;
    }
    public void print() {
        System.out.println("Name: " + person.getName());
        System.out.println("Surname: " + person.getSurname());
        System.out.println("Email: " + person.getEmail());
        System.out.println("Row number: " + getRowNumber());
        System.out.println("Seat number: " + getSeatNumber());
        System.out.println("Ticket price: " + getTicket_price());
    }
}
