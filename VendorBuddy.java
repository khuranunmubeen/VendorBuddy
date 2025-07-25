import java.util.*;

class Product {
    private int id;
    private String name;
    private int price; // in rupees

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void display() {
        System.out.printf("%d. %s - Rs-%d%n", id, name, price);
    }
}

class VendingMachine {
    private List<Product> products;

    public VendingMachine() {
        products = new ArrayList<>();
        loadProducts();
    }

    private void loadProducts() {
        products.add(new Product(1, "Chips", 15));
        products.add(new Product(2, "Soda", 12));
        products.add(new Product(3, "Candy", 10));
    }

    public void displayMenu() {
        System.out.println("\nAvailable Products:");
        for (Product product : products) {
            product.display();
        }
    }

    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            System.out.print("\nSelect product by number (or 0 to exit): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Thank you! Exiting...");
                break;
            }

            Product selected = getProductById(choice);
            if (selected == null) {
                System.out.println("Invalid selection. Try again.");
                continue;
            }

            System.out.printf("You selected: %s (Rs.%d)%n", selected.getName(), selected.getPrice());
            System.out.print("Insert money in rupees (e.g., 20): Rs.");
            int insertedRupees = scanner.nextInt();

            if (insertedRupees < selected.getPrice()) {
                System.out.println("Insufficient funds. Transaction cancelled.");
            } else {
                int change = insertedRupees - selected.getPrice();
                System.out.printf("Dispensing %s...%n", selected.getName());
                if (change > 0) {
                    System.out.printf("Returning change: Rs.%d%n", change);
                }
                System.out.println("Thank you for your purchase!");
            }
        }
        scanner.close();
    }
}
public class Vendorbuddy{
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        machine.start();
    }
}