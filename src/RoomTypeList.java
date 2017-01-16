import java.util.ArrayList;

public enum RoomTypeList {
    INSTANCE;
    
    private ArrayList<RoomType> rtl;
    
    private RoomTypeList() {
        rtl = new ArrayList<RoomType>();
        update(); // populate the room type list when creating it for the 1st time
    }
    
    public ArrayList<RoomType> getRTL() {
        return rtl;
    }
    
    public RoomType getRoomType(int id) {
        for (RoomType rt: INSTANCE.rtl) {
            if (id == rt.getId()) return rt;
        }
        return null;
    }
    
    // update the room type list from the DB
    public void update() {
        rtl = DBManager.getRoomTypeList();
    }
    
    public static void main(String[] args) {
        // testing
        for (RoomType rt: INSTANCE.rtl) {
            System.out.println(rt);
        }
    }

}
