
public class RoomType {

    private int id;
    private String name;
    private double price;
    
    public RoomType(String name, long price) {
        this.id = -1;
        this.name = name;
        this.price = price;
    }
    
    public RoomType(int id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    // add the room type to the DB
    public int addToDB() {
        return DBManager.addRoomType(this);
    }

    // update the DB with the room type's details
    public int updateDB() {
        return DBManager.updateRoomType(this);
    }

    // delete the room type from the DB
    public int deleteFromDB() {
        return DBManager.deleteRoomType(this);
    }

    // This isn't only for testing. We're actually using it to show the id and name
    // in room type selection comboboxes.
    @Override
    public String toString() {
        return "(" + id + ") " + name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(long price) {
        this.price = price;
    }
    
    public static void main(String[] args) {
        

    }

}
