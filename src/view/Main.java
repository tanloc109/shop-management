package view;

import java.util.Scanner;
import model.Customer;
import model.Item;

public class Main {


    public static void main(String args[]) {
        InvoiceManagementSystem system = new InvoiceManagementSystem();

        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("=============================Menu===================================");
            System.out.println("1 - Add a new item then show a list of items contained.");
            System.out.println("2 - Add a new customer then show a list of customers.");
            System.out.println("3 - Create an invoice for the selected customer by entering the customer code.");
            System.out.println("4 - Sort the invoice list by Customer name (a-z).");
            System.out.println("5 - Sort the invoice list by Total quantity of goods purchased by customers (descending).");
            System.out.println("6 - Sort the invoice list by Total amount to be paid by the customer (decreasing).");
            System.out.println("7 - Search invoices by Customer name.");
            System.out.println("8 - Search invoices by item name purchased.");
            System.out.println("9 - Exit.");
            System.out.print("Input your choice: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Input price: ");
                    float priceNewItem = (float) Double.parseDouble(sc.nextLine());
                    String typeNewItem=null;
                    do {
                        System.out.print("Input type of Item only include in (transportation, garments, household appliances, high-tech equipment, food): ");
                        typeNewItem = sc.nextLine();
                    } while (!(typeNewItem.equalsIgnoreCase("transportation") || typeNewItem.equalsIgnoreCase("garments") || typeNewItem.equalsIgnoreCase("household appliances") || typeNewItem.equalsIgnoreCase("high-tech equipment") || typeNewItem.equalsIgnoreCase("food"))
                            );
                    System.out.print("Input name: ");
                    String nameNewItem = sc.nextLine();
                    Item newItem = new Item(priceNewItem, typeNewItem, nameNewItem);
                    system.addItem(newItem);
                    break;
                case 2:
                    System.out.print("Input name of customer: ");
                    String name = sc.nextLine();
                    System.out.print("Input address of customer: ");
                    String address = sc.nextLine();
                    System.out.print("Input email of customer: ");
                    String email = sc.nextLine();
                    System.out.print("Input phoneNum of customer: ");
                    String phoneNum = sc.nextLine();
                    Customer newCus = new Customer(name, address, email, phoneNum);
                    system.addCustomer(newCus);
                    break;
                case 3:
                    System.out.print("Input code of customer: ");
                    int code = Integer.parseInt(sc.nextLine());
                    system.createInvoice(code);
                    break;
                case 4:
                    system.sortInvoicesByName();
                    break;
                case 5:
                    system.sortInvoicesByTotalQuantity();
                    break;
                case 6:
                    system.sortInvoicesByTotalAmount();
                    break;
                case 7:
                    System.out.print("Input customer's name: ");
                    String customerName = sc.nextLine();
                    system.searchInvoicesByCustomerName(customerName);
                    break;
                case 8:
                    System.out.print("Input item's name: ");
                    String itemName = sc.nextLine();
                    system.searchInvoicesByItemName(itemName);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
                case 9:
                    System.out.println("Goodbye.");

            }
        } while (choice != 9);
    }

}
