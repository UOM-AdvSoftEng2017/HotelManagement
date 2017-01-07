public class Client {

    private String id;
    private String name;
    private int phone;

    public Client(String id, String name, int phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    // add the Client to the DB
    public int addToDB() {
        return DBManager.addClient(this);
    }

    // update the DB with the Client's details
    public int updateDB() {
        return DBManager.updateClient(this);
    }

    // delete the Client from the DB
    public int deleteFromDB() {
        return DBManager.deleteClient(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public static void main(String[] args) {
        // testing
        int retval;
        System.out.println("Creating a new Client:");
        Client c = new Client("XZ123546", "Mat", 12354313);
        retval = c.addToDB();
        if (retval == 0) {
            System.out.println("OK");
        } else {
            System.out.println("NOK");
        }
        System.out.println(c);
        System.out.println("Changing the name:");
        c.setName("Pat");
        retval = c.updateDB();
        if (retval == 0) {
            System.out.println("OK");
        } else {
            System.out.println("NOK");
        }
        System.out.println(c);
        System.out.println("Changing the phone number:");
        c.setPhone(987123653);
        retval = c.updateDB();
        if (retval == 0) {
            System.out.println("OK");
        } else {
            System.out.println("NOK");
        }
        System.out.println(c);
        System.out.println("Deleting from DB:");
        retval = c.deleteFromDB();
        if (retval == 0) {
            System.out.println("OK");
        } else {
            System.out.println("NOK");
        }

    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", phone=" + phone + "]";
    }

}