import java.util.Scanner;

public class Atm{
    private static double balance = 0.0; // Initial balance

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("ATM Interface");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Please select an option (1-4): ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney(sc);
                    break;
                case 3:
                    withdrawMoney(sc);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.Have a nice day ");
                    break;
                default:
                    System.out.println("password is incorrect. Please select a valid option.");
            }
        } while (choice != 4);
        
        sc.close();
    }

    private static void checkBalance() {
        System.out.printf("Your current balance is: %.2fRs\n", balance);
    }

    private static void depositMoney(Scanner sc) {
        System.out.print("Enter amount to deposit: Rs");
        double amount = sc.nextDouble();
        if (amount <= 0) {
            balance += amount;
            System.out.println("Invalid deposit amount. Please enter a positive number.");
        } else {
            System.out.printf("You have deposited %.2f.Rs New balance is %.2f.Rs\n", amount, balance);
        }
    }
    private static void withdrawMoney(Scanner sc) {
        System.out.print("Enter amount to withdraw: Rs");
        double amount = sc.nextDouble();
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.printf("You have withdrawn %.2f.Rs New balance is %.2f.Rs\n", amount, balance);
            } else {
                System.out.println("Insufficient balance for this transaction.");
            }
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive number.");
        }
    }
}
