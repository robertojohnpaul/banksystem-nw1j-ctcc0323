import java.util.Scanner;

class Account {
  // Encapsulation: Private fields to hide the internal state
  private String accountNumber;
  private String accountHolder;
  private double balance;

  // Abstraction: Constructor to initialize an account
  public Account(String accountNumber, String accountHolder, double balance) {
    this.accountNumber = accountNumber;
    this.accountHolder = accountHolder;
    this.balance = balance;
  }

  // Public getter methods to provide controlled access
  public String getAccountNumber() {
    return accountNumber;
  }

  public String getAccountHolder() {
    return accountHolder;
  }

  public double getBalance() {
    return balance;
  }

  // Public methods to perform operations
  public void deposit(double amount) {
    balance += amount;
    System.out.println("Deposit successful. New balance: " + balance);
  }

  public void withdraw(double amount) {
    if (balance >= amount) {
      balance -= amount;
      System.out.println("Withdrawal successful. New balance: " + balance);
    } else {
      System.out.println("Insufficient balance.");
    }
  }
}

public class BankingSystem {
  // Private static fields for managing accounts
  private static Account[] accounts = new Account[10];
  private static int accountCount = 0;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Banking System Menu:");
      System.out.println("1. Create Account");
      System.out.println("2. Deposit");
      System.out.println("3. Withdraw");
      System.out.println("4. Check Balance");
      System.out.println("5. Exit");
      System.out.print("Enter your choice: ");

      int choice = scanner.nextInt();
      scanner.nextLine();
      // Consume newline

      switch (choice) {
        case 1:
          createAccount(scanner);
          break;
        case 2:
          deposit(scanner);
          break;
        case 3:
          withdraw(scanner);
          break;
        case 4:
          checkBalance(scanner);
          break;
        case 5:
          System.out.println("Goodbye!");
          scanner.close();
          return;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  // Private static methods to manage account operations
  private static void createAccount(Scanner scanner) {
    if (accountCount >= accounts.length) {
      System.out.println("Account limit reached. Cannot create more accounts.");
      return;
    }

    System.out.print("Enter account number: ");
    String accountNumber = scanner.nextLine();
    for (Account account : accounts) {
      if (account != null && account.getAccountNumber().equals(accountNumber)) {
        System.out.println("Account number already exists. Please try again.");
        return;
      }
    }

    System.out.print("Enter account holder name: ");
    String accountHolder = scanner.nextLine();
    System.out.print("Enter initial balance: ");
    double balance = scanner.nextDouble();
    scanner.nextLine();
    // Consume newline

    Account account = new Account(accountNumber, accountHolder, balance);
    accounts[accountCount++] = account;
    System.out.println("Account created successfully.");
  }

  private static void deposit(Scanner scanner) {
    System.out.print("Enter account number: ");
    String accountNumber = scanner.nextLine();
    System.out.print("Enter amount to deposit: ");
    double amount = scanner.nextDouble();
    scanner.nextLine();
    // Consume newline

    for (Account account : accounts) {
      if (account != null && account.getAccountNumber().equals(accountNumber)) {
        account.deposit(amount);
        return;
      }
    }
    System.out.println("Account not found.");
  }

  private static void withdraw(Scanner scanner) {
    System.out.print("Enter account number: ");
    String accountNumber = scanner.nextLine();
    System.out.print("Enter amount to withdraw: ");
    double amount = scanner.nextDouble();
    scanner.nextLine();
    // Consume newline

    for (Account account : accounts) {
      if (account != null && account.getAccountNumber().equals(accountNumber)) {
        account.withdraw(amount);
        return;
      }
    }
    System.out.println("Account not found.");
  }

  private static void checkBalance(Scanner scanner) {
    System.out.print("Enter account number: ");
    String accountNumber = scanner.nextLine();

    for (Account account : accounts) {
      if (account != null && account.getAccountNumber().equals(accountNumber)) {
        System.out.println("Balance: " + account.getBalance());
        return;
      }
    }
    System.out.println("Account not found.");
  }
}      