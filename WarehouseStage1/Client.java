import java.io.*;

public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    private String clientID;
    private String name;
    private String address;

    public Client(String clientID, String name, String address) {
        this.clientID = clientID;
        this.name = name;
        this.address = address;
    }

    public String getClientID() {
        return clientID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String toString() {
        return "Client ID: " + clientID +
               ", Name: " + name +
               ", Address: " + address;
    }
}