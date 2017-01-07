import java.util.ArrayList;

public class HotelManagement {

	public static void main(String[] args) {
		ArrayList<Client> cl = ClientList.CL.getCL();
		for (Client c: cl) {
			System.out.println(c);
		}

		MainVIew main = new MainVIew();
		main.show();

		ClientListView cList = new ClientListView();
		cList.show();

	}

}
 