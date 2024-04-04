package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.Customer;
import model.Invoice;
import model.Item;
import model.Pair;

public class InvoiceManagementSystem {

    private List<Customer> customers;
    private List<Item> items;
    List<Invoice> invoices;

    public InvoiceManagementSystem() {
        customers = new ArrayList<>();
        items = new ArrayList<>();
        invoices = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
        showItemList();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        showCustomerList();
    }

    public void createInvoice(int customerId) {
        Customer selectedCustomer = getCustomerById(customerId);
        if (selectedCustomer == null) {
            System.out.println("Invalid customer ID.");
            return;
        }

        List<Pair<Item, Integer>> purchaseItems = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the purchase information for the items: ");
        while (true) {
            System.out.print("Item ID: ");
            int itemId = scanner.nextInt();

            Item selectedItem = getItemById(itemId);
            if (selectedItem == null) {
                System.out.println("Invalid item ID. Please try again.");
                continue;
            }

            System.out.print("Quantity: ");
            int quantity = scanner.nextInt();

            purchaseItems.add(new Pair<>(selectedItem, quantity));
            scanner.nextLine();
            System.out.print("Continue input 1, stop input 0. Your choice is: ");
            int loop = scanner.nextInt();
            if (loop == 0) {
                break;
            }
        }

        float totalPrice = 0;
        int totalItems = 0;

        for (Pair<Item, Integer> pair : purchaseItems) {
            Item item = pair.getKey();
            Integer quantity = pair.getValue();
            totalPrice += item.getPrice() * quantity;
            totalItems += quantity;
        }

        Date date = new Date();
        Invoice invoice = new Invoice(selectedCustomer, purchaseItems, totalPrice, totalItems, date);

        invoices.add(invoice);

        System.out.println("Invoice created successfully.");
        showInvoiceDetails(invoice);
    }

    public void sortInvoicesByName() {
        Collections.sort(invoices, new Comparator<Invoice>() {
            @Override
            public int compare(Invoice invoice1, Invoice invoice2) {
                Customer customer1 = invoice1.getCustomer();
                Customer customer2 = invoice2.getCustomer();
                if (customer1 == null || customer2 == null) {
                    // Handle null case
                    return 0;
                }
                return customer1.getName().compareTo(customer2.getName());
            }
        });
        showInvoiceList();
    }

    public void sortInvoicesByTotalQuantity() {
        Collections.sort(invoices, new Comparator<Invoice>() {
            @Override
            public int compare(Invoice invoice1, Invoice invoice2) {
                return Integer.compare(invoice2.getTotalItems(), invoice1.getTotalItems());
            }
        });
        showInvoiceList();
    }

    public void sortInvoicesByTotalAmount() {
        Collections.sort(invoices, new Comparator<Invoice>() {
            @Override
            public int compare(Invoice invoice1, Invoice invoice2) {
                return Double.compare(invoice2.getTotalPrice(), invoice1.getTotalPrice());
            }
        });
        showInvoiceList();
    }

    public void searchInvoicesByCustomerName(String customerName) {
        List<Invoice> matchingInvoices = new ArrayList<>();
        for (Invoice invoice : invoices) {
            if (invoice.getCustomer() != null && invoice.getCustomer().getName() != null
                    && invoice.getCustomer().getName().equalsIgnoreCase(customerName)) {
                matchingInvoices.add(invoice);
            }
        }

        if (matchingInvoices.isEmpty()) {
            System.out.println("No invoices found for the given customer name.");
        } else {
            System.out.println("Matching invoices:");
            for (Invoice invoice : matchingInvoices) {
                showInvoiceDetails(invoice);
            }
        }
    }

    public void searchInvoicesByItemName(String itemName) {
        List<Invoice> matchingInvoices = new ArrayList<>();
        for (Invoice invoice : invoices) {
            boolean foundItem = false;
            if (invoice.getItems() != null) {
                for (Pair<Item, Integer> pair : invoice.getItems()) {
                    if (pair != null && pair.getKey() != null && pair.getKey().getName() != null
                            && pair.getKey().getName().equalsIgnoreCase(itemName)) {
                        foundItem = true;
                        break;
                    }
                }
            }
            if (foundItem) {
                matchingInvoices.add(invoice);
            }
        }

        if (matchingInvoices.isEmpty()) {
            System.out.println("No invoices found for the given item name.");
        } else {
            System.out.println("Matching invoices:");
            for (Invoice invoice : matchingInvoices) {
                showInvoiceDetails(invoice);
            }
        }
    }


    
    public void showItemList() {
        System.out.println("Items list:");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-25s | %-10s |\n", "Item ID", "Name", "Type", "Price");
        System.out.println("------------------------------------------------------------------------------");
        for (Item item : items) {
            System.out.printf("| %-10s | %-20s | %-25s | $%-9.2f |\n", item.getId(), item.getName(), item.getType(), item.getPrice());
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    public void showCustomerList() {
        System.out.println("Customers list:");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-20s | %-30s | %-20s |\n", "Customer ID", "Name", "Address", "Email");
        System.out.println("-----------------------------------------------------------------------------------------------");
        for (Customer customer : customers) {
            System.out.printf("| %-12s | %-20s | %-30s | %-20s |\n", customer.getId(), customer.getName(), customer.getAddress(), customer.getEmail());
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
    }

    public void showInvoiceList() {
        System.out.println("Invoices list:");
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("| %-12s | %-20s | %-12s | %-11s |\n", "Invoice ID", "Customer Name", "Total Items", "Total Price");
        System.out.println("--------------------------------------------------------------------");
        for (Invoice invoice : invoices) {
            Customer customer = invoice.getCustomer();
            System.out.printf("| %-12s | %-20s | %-12d | $%-10.2f |\n", invoice.getId(), customer != null ? customer.getName() : "N/A", invoice.getTotalItems(), invoice.getTotalPrice());
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public void showInvoiceDetails(Invoice invoice) {
        System.out.println("Invoice Details:");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("| %-22s | %-30s |\n", "Invoice ID", invoice.getId());

        // Handle null date
        if (invoice.getDate() != null) {
            System.out.printf("| %-22s | %-30s |\n", "Date", invoice.getDate());
        } else {
            System.out.printf("| %-22s | %-30s |\n", "Date", "N/A");
        }

        Customer customer = invoice.getCustomer();
        if (customer != null) {
            System.out.printf("| %-22s | %-30s |\n", "Customer Name", customer.getName());
            System.out.printf("| %-22s | %-30s |\n", "Customer Address", customer.getAddress());
            System.out.printf("| %-22s | %-30s |\n", "Customer Email", customer.getEmail());
            System.out.printf("| %-22s | %-30s |\n", "Customer Phone", customer.getPhoneNum());
        } else {
            System.out.printf("| %-22s | %-30s |\n", "Customer Name", "N/A");
            System.out.printf("| %-22s | %-30s |\n", "Customer Address", "N/A");
            System.out.printf("| %-22s | %-30s |\n", "Customer Email", "N/A");
            System.out.printf("| %-22s | %-30s |\n", "Customer Phone", "N/A");
        }

        System.out.println("-------------------------------------------------------------------------------");

        System.out.println("Items:");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-15s | %-10s | %-8s |\n", "Item ID", "Name", "Type", "Price", "Quantity");
        System.out.println("-------------------------------------------------------------------------------");
        for (Pair<Item, Integer> pair : invoice.getItems()) {
            Item item = pair.getKey();
            int quantity = pair.getValue();
            System.out.printf("| %-10s | %-20s | %-15s | $%-9.2f | %-8s |\n", item.getId(), item.getName(), item.getType(), item.getPrice(), quantity);
        }
        System.out.println("-------------------------------------------------------------------------------");

        System.out.printf("| %-12s | %-17s |\n", "Total Items", invoice.getTotalItems());
        System.out.printf("| %-12s | $%-16.2f |\n", "Total Price", invoice.getTotalPrice());
        System.out.println("-------------------------------------------------------------------------------");
    }

    public Customer getCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    public Item getItemById(int itemId) {
        for (Item item : items) {
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }
}
