import java.util.ArrayList;

public enum ClientList {
    CL;

    private ArrayList<Client> cl;

    private ClientList() {
        cl = new ArrayList<Client>();
        update(); // populate the Client list when creating it for the 1st time
    }

    public ArrayList<Client> getCL() {
        return cl;
    }

    // update the Client list from the DB
    public void update() {
        cl = DBManager.getClientList();
    }

    public static void main(String[] args) {
        // testing
        for (Client c: CL.cl) {
            System.out.println(c);
        }

    }
}