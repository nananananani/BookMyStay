public class BookMyStay {
    
    public static void main(String[] args) {

        uc1_welcome();
        uc2_roomInitialization();

    }

    // UC1
    public static void uc1_welcome() {

        System.out.println("===================================");
        System.out.println("   Welcome to Book My Stay App   ");
        System.out.println("   Hotel Booking System v1.0     ");
        System.out.println("===================================");

        System.out.println("Application started successfully.");
    }
    // ================= UC2 =================
    public static void uc2_roomInitialization() {

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("\n===== Room Details =====");

        single.displayDetails();
        System.out.println("Available: " + singleAvailable);

        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailable);

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable);
    }
    
}
// ================= ABSTRACT CLASS =================
abstract class Room {

    String type;
    int beds;
    double price;

    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("\nRoom Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: $" + price);
    }
}
// ================= SINGLE ROOM =================
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 1000);
    }
}
// ================= DOUBLE ROOM =================
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 1800);
    }
}
// ================= SUITE ROOM =================
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 3000);
    }
}
