package hust.soict.hedspi.test.cart;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.exception.LimitExceededException;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", "Guy Ritchie", 90, 18.99f);

        Book book1 = new Book(101, "The Art of War", "Strategy", 10.5f);
        Book book2 = new Book(102, "Clean Code", "Programming", 30.0f);

        CompactDisc cd1 = new CompactDisc(201, "Greatest Hits", "Pop", 15.0f, 45, "Various Artists", "Taylor Swift");
        cd1.addTrack(new Track("Shake It Off", 3));
        cd1.addTrack(new Track("Blank Space", 4));
        cd1.addTrack(new Track("Love Story", 5));

        CompactDisc cd2 = new CompactDisc(202, "Classical Collection", "Classical", 12.0f, 60, "Various Conductors", "Mozart");
        cd2.addTrack(new Track("Symphony No. 40", 8));
        cd2.addTrack(new Track("Eine kleine Nachtmusik", 6));

        try {
            cart.addMedia(dvd1);
            cart.addMedia(dvd2);
            cart.addMedia(dvd3);
            cart.addMedia(book1);
            cart.addMedia(book2);
            cart.addMedia(cd1);
            cart.addMedia(cd2);

        } catch (LimitExceededException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }


        System.out.println("\n==> Giỏ hàng ban đầu:");
        cart.printCart();

        System.out.println("\n==> Sắp xếp theo title:");
        cart.sort("title");
        cart.printCart();

        System.out.println("\n==> Sắp xếp theo cost (giảm dần):");
        cart.sort("cost");
        cart.printCart();
        
        System.out.println("\n==> Tìm theo ID (ví dụ tìm ID của cd1 - kiểm tra ID thực tế từ output):");
        Media foundCd = cart.searchById(cd1.getId()); // Sử dụng getId() của đối tượng để tìm đúng ID
        if (foundCd == null) {
            System.out.println("Không tìm thấy CD với ID: " + cd1.getId() + " (Lưu ý: ID này là ID thực tế của đối tượng).");
        }


        System.out.println("\n==> Tìm theo title = 'Clean Code':");
        cart.searchByTitle("Clean Code");

        System.out.println("\n==> Tìm theo title không tồn tại:");
        cart.searchByTitle("Non Existent Title");

        System.out.println("\n==> Tìm theo ID không tồn tại:");
        cart.searchById(999);
    }
}
