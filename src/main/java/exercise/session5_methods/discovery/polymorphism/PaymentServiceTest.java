package exercise.session5_methods.discovery.polymorphism;

public class PaymentServiceTest {

    private PaymentService service;

    public static void beforeClass() {
        System.out.println ("Before class");
        // Called in the beginning of the test suite only once
        // Used for all tests need to share computationally expensive setup
    }

    public void setupTest() {
        System.out.println ("Setup test");
        // Called before every test
        // Used for setting up resource before every test
    }

    public void testCreditCardPayment() {
        System.out.println ("Running the test method");
        // Test case 1
    }

    public void testWireTransfer() {
        System.out.println ("Running the test method");
    }

    public void testInsufficientFunds() {
        System.out.println ("Running the test method");
    }

    public static void afterClass() {
        System.out.println ("Running After class");
        // Called once in the end of the entire test suite
        // Used for closing and cleaning up common resources
    }
}
