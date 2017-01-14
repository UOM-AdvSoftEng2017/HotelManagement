
public class RoomType {

    private int id;
    private String name;
    private long price;
    
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
    
    // add the room to the DB
    public int addToDB() {
        return DBManager.addRoomType(this);
    }

    // update the DB with the room's details
    public int updateDB() {
        return DBManager.updateRoomType(this);
    }

    // delete the room from the DB
    public int deleteFromDB() {
        return DBManager.deleteRoomType(this);
    }

    @Override
    public String toString() {
        return "RoomType [id=" + id + ", name=" + name + ", price=" + price + "]";
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
    
    public long getPrice() {
        return price;
    }
    
    public void setPrice(long price) {
        this.price = price;
    }
    
    public static void main(String[] args) {
        

    }

}
