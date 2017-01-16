import java.util.ArrayList;

public enum ClientList {
    INSTANCE;

    private ArrayList<Client> cl;

    private ClientList() {
        cl = new ArrayList<Client>();
        update(); // populate the Client list when creating it for the 1st time
    }

    public ArrayList<Client> getCL() {
        return cl;
    }

    public Client getClient(String clientID) {
        for (Client c: INSTANCE.cl) {
            if ((clientID.equals(c.getId()))) return c;
        }
        return null;
    }
    
    // update the Client list from the DB
    public void update() {
        cl = DBManager.getClientList();
    }

    public static void main(String[] args) {
        // testing
        for (Client c: INSTANCE.cl) {
            System.out.println(c);
        }

    }
}