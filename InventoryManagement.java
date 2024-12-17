import java.io.*;
import java.util.*;

class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    private int quantity;

    public Product(int id, String name, String category, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nCategory: " + category +
               "\nPrice: $" + price + "\nQuantity: " + quantity + "\n";
    }
}

class Inventory {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        for (Product p : products) {
            if (p.getId() == product.getId()) {
                System.out.println("ID already exists.");
                return;
            }
        }
        products.add(product);
        System.out.println("Product added successfully.");
        System.out.println("-----------------------------------------------------------");
    }

    public void removeProduct(int id) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getId() == id) {
                iterator.remove();
                System.out.println("Product removed successfully.");
                System.out.println("-----------------------------------------------------------");
                return;
            }
        }
        System.out.println("ID does not exist.");
    }

    public Product findProduct(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void updateProduct(int id, String name, String category, double price, int quantity) {
        for (Product p : products) {
            if (p.getId() == id) {
                p.setName(name);
                p.setCategory(category);
                p.setPrice(price);
                p.setQuantity(quantity);
                System.out.println("Product updated successfully.");
                System.out.println("-----------------------------------------------------------");
                return;
            }
        }
        System.out.println("ID does not exist.");
    }

    public void printProducts() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public void saveInventoryToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            for (Product p : products) {
                writer.println(p.getId() + "," + p.getName() + "," + p.getCategory() + ","
                        + p.getPrice() + "," + p.getQuantity());
            }
            System.out.println("Inventory saved to file.");
            System.out.println("-----------------------------------------------------------");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    public void loadInventoryFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String category = parts[2];
                double price = Double.parseDouble(parts[3]);
                int quantity = Integer.parseInt(parts[4]);
                products.add(new Product(id, name, category, price, quantity));
            }
            System.out.println("Inventory loaded from file.");
            System.out.println("-----------------------------------------------------------");
        } catch (IOException e) {
            System.out.println("Error: Could not open file " + filename);
        }
    }
}

public class InventoryManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        System.out.println("-----------------------------------------------------------");
        System.out.println("--------------- Inventory Management System ---------------");
        System.out.println("-----------------------------------------------------------");
        System.out.println("------------------------- Welcome! ------------------------");
        System.out.println("-----------------------------------------------------------");

        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Add a product");
            System.out.println("2. Remove a product");
            System.out.println("3. Find a product");
            System.out.println("4. Update a product");
            System.out.println("5. View all products");
            System.out.println("6. Save inventory to file");
            System.out.println("7. Load inventory from file");
            System.out.println("Q. Quit");

            char choice = scanner.next().charAt(0);

            switch (choice) {
                case '1': {
                    System.out.println("Enter ID: ");
                    int id = scanner.nextInt();
                    System.out.println("Enter product name: ");
                    String name = scanner.next();
                    System.out.println("Enter product category: ");
                    String category = scanner.next();
                    System.out.println("Enter product price: $ ");
                    double price = scanner.nextDouble();
                    System.out.println("Enter product quantity: ");
                    int quantity = scanner.nextInt();
                    Product product = new Product(id, name, category, price, quantity);
                    inventory.addProduct(product);
                    break;
                }
                case '2': {
                    System.out.println("Enter product id: ");
                    int id = scanner.nextInt();
                    inventory.removeProduct(id);
                    break;
                }
                case '3': {
                    System.out.println("Enter product id: ");
                    int id = scanner.nextInt();
                    Product product = inventory.findProduct(id);
                    if (product != null) {
                        System.out.println(product);
                    } else {
                        System.out.println("Product not found.");
                        System.out.println("-----------------------------------------------------------");
                    }
                    break;
                }
                case '4': {
                    System.out.println("Enter the product id: ");
                    int id = scanner.nextInt();
                    System.out.println("Enter new product name: ");
                    String name = scanner.next();
                    System.out.println("Enter new product category: ");
                    String category = scanner.next();
                    System.out.println("Enter new product price: $ ");
                    double price = scanner.nextDouble();
                    System.out.println("Enter new product quantity: ");
                    int quantity = scanner.nextInt();
                    inventory.updateProduct(id, name, category, price, quantity);
                    break;
                }
                case '5': {
                    inventory.printProducts();
                    break;
                }
                case '6': {
                    inventory.saveInventoryToFile("inventory.csv");
                    break;
                }
                case '7': {
                    inventory.loadInventoryFromFile("inventory.csv");
                    break;
                }
                case 'q':
                case 'Q': {
                    System.out.println("Goodbye!");
                    System.out.println("-----------------------------------------------------------");
                    scanner.close();
                    return;
                }
                default: {
                    System.out.println("Invalid Choice. Please Try again.");
                    System.out.println("-----------------------------------------------------------");
                }
            }
        }
    }
}
