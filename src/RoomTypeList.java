import java.util.ArrayList;

public enum RoomTypeList {
    RTL;
    
    private ArrayList<RoomType> rtl;
    
    private RoomTypeList() {
        rtl = new ArrayList<RoomType>();
        update(); // populate the room list when creating it for the 1st time
    }
    
    public ArrayList<RoomType> getRTL() {
        return rtl;
    }
    
    public RoomType getRoomType(int id) {
        for (RoomType rt: RTL.rtl) {
            if (id == rt.getId()) return rt;
        }
        return null;
    }
    
    // update the room list from the DB
    public void update() {
        rtl = DBManager.getRoomTypeList();
    }
    
    public static void main(String[] args) {
        // testing
        for (RoomType rt: RTL.rtl) {
            System.out.println(rt);
        }
    }

}
