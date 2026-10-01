import java.io.*;
import java.util.*;

public class Warehouse implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Warehouse warehouse;
    private ClientList clientList = new ClientList();
    private ProductList productList = new ProductList();
    private int clientCounter = 0;
    private int productCounter = 0;

    private Warehouse() {}

    public static Warehouse instance() {
        if (warehouse == null) {
            return warehouse = new Warehouse();
        } else {
            return warehouse;
        }
    }

    public Client addClient(String name, String address) {
        clientCounter++;
        String clientID = "C" + clientCounter;
        Client client = new Client(clientID, name, address);
        clientList.addClient(client);
        return client;
    }

    public Product addProduct(String name, int qty, double price) {
        productCounter++;
        String productID = "P" + productCounter;
        Product product = new Product(productID, name, qty, price);
        productList.addProduct(product);
        return product;
    }

    public void addProductToWishlist(String clientID, String productID, int qty) {
        Client client = clientList.findClient(clientID);
        Product product = productList.findProduct(productID);
        if (client == null || product == null) {
            System.out.println("Could not add to wishlist (check client/product ID)");
            return;
        }
        client.getWishlist().addProduct(product, qty);
    }

    public void displayAllClients() {
        Iterator<Client> allClients = clientList.getClients();
        while (allClients.hasNext()) {
            System.out.println(allClients.next());
        }
    }

    public void displayAllProducts() {
        Iterator<Product> allProducts = productList.getProducts();
        while (allProducts.hasNext()) {
            System.out.println(allProducts.next());
        }
    }

    public void displayWishlist(String clientID) {
        Client client = clientList.findClient(clientID);
        if (client == null) {
            System.out.println("Client not found");
            return;
        }
        System.out.println("Wishlist for client " + clientID + ":");
        System.out.println(client.getWishlist());
    }

    public boolean save() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("WarehouseData"));
            output.writeObject(warehouse);
            output.close();
            return true;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }

    public static Warehouse retrieve() {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("WarehouseData"));
            warehouse = (Warehouse) input.readObject();
            input.close();
            return warehouse;
        } catch (IOException ioe) {
            return null;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }
}