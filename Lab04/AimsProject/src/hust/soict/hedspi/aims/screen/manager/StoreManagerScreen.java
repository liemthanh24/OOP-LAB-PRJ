package hust.soict.hedspi.aims.screen.manager;
import java.util.ArrayList;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;
import java.awt.*;
import javax.swing.*;

public class StoreManagerScreen extends JFrame {
	private Store store;
	public StoreManagerScreen(Store store) {
		this.store = store;

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		cp.add(createNorth(), BorderLayout.NORTH);
		cp.add(createCenter(), BorderLayout.CENTER);

		setTitle("Store");
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	JPanel createNorth() {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader());
		return north;
	}

	JMenuBar createMenuBar() {
		JMenu menu = new JMenu("Options");

		JMenu smUpdateStore = new JMenu("Update Store");
		JMenuItem addBook = new JMenuItem("Add Book");
		addBook.addActionListener(e -> new AddBookToStoreScreen(store));

		JMenuItem addCD = new JMenuItem("Add CD");
		addCD.addActionListener(e -> new AddCompactDiscToStoreScreen(store));

		JMenuItem addDVD = new JMenuItem("Add DVD");
		addDVD.addActionListener(e -> new AddDigitalVideoDiscToStoreScreen(store));

		smUpdateStore.add(addBook);
		smUpdateStore.add(addCD);
		smUpdateStore.add(addDVD);

		JMenuItem viewStore = new JMenuItem("View store");
		viewStore.addActionListener(e -> {
			this.setVisible(false);
			new StoreManagerScreen(store);
		});
		menu.add(viewStore);
		menu.add(smUpdateStore);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuBar.add(menu);

		return menuBar;
	}

	JPanel createHeader() {
		JPanel header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

		JLabel title = new JLabel("AIMS");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
		title.setForeground(Color.BLUE);

		header.add(Box.createRigidArea(new Dimension(10, 10)));
		header.add(title);
		header.add(Box.createHorizontalGlue());
		header.add(Box.createRigidArea(new Dimension(10, 10)));

		return header;
	}

	JPanel createCenter() {
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(3, 3, 2, 2));

		ArrayList<Media> mediaInStore = store.getMediaInStore();
		int numberOfCells = Math.min(mediaInStore.size(), 9);

		for (int i = 0; i < numberOfCells; i++) {
			MediaStore cell = new MediaStore(mediaInStore.get(i));
			center.add(cell);
		}

		for (int i = numberOfCells; i < 9; i++) {
			center.add(new JPanel());
		}

		return center;
	}

	public static void main(String[] args) {
		Store store = new Store();

		store.addMedia(new Book(10, "Sapiens", "History", 13.99f));
		store.addMedia(new DigitalVideoDisc("Interstellar", "Sci-Fi", "Christopher Nolan", 169, 20.99f));
		store.addMedia(new CompactDisc(11, "Imagine Dragons", "Rock", 11.50f, "Alex da Kid", "Imagine Dragons"));
		store.addMedia(new Book(101, "Clean Code", "Programming", 25.99f));
		store.addMedia(new DigitalVideoDisc("Avatar", "Fantasy", "James Cameron", 162, 18.50f));
		store.addMedia(new CompactDisc(103, "Taylor Swift: Red", "Pop", 14.75f, "Max Martin", "Taylor Swift"));
		store.addMedia(new Book(104, "Effective Java", "Programming", 27.99f));

		new StoreManagerScreen(store);
	}
}