package hust.soict.hedspi.test.cart;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;

import java.util.Collection;

public class CartTest {
        public static void main(String[] args) {
                Cart cart = new Cart();

                // Tạo DVD
                DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
                DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
                DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", "Guy Ritchie", 90, 18.99f);

                // Tạo Book
                Book book1 = new Book(4, "The Art of War", "Strategy", 10.5f);
                Book book2 = new Book(5, "Clean Code", "Programming", 30.0f);

                // Tạo CD với track
                CompactDisc cd1 = new CompactDisc(6, "Greatest Hits", "Pop", 15.0f, 45, "Various", "Taylor Swift");
                cd1.addTrack(new Track("Shake It Off", 3));
                cd1.addTrack(new Track("Blank Space", 4));
                cd1.addTrack(new Track("Love Story", 5));

                CompactDisc cd2 = new CompactDisc(7, "Classical Collection", "Classical", 12.0f, 60, "Mozart", "Various");
                cd2.addTrack(new Track("Symphony No. 40", 8));
                cd2.addTrack(new Track("Eine kleine Nachtmusik", 6));

                // Thêm vào cart
                cart.addMedia(dvd1);
                cart.addMedia(dvd2);
                cart.addMedia(dvd3);
                cart.addMedia(book1);
                cart.addMedia(book2);
                cart.addMedia(cd1);
                cart.addMedia(cd2);

                System.out.println("\n==> Giỏ hàng ban đầu:");
                cart.printCart();

                System.out.println("\n==> Sắp xếp theo title:");
                cart.sort("title");
                cart.printCart();

                System.out.println("\n==> Sắp xếp theo cost (giảm dần):");
                cart.sort("cost");
                cart.printCart();

                System.out.println("\n==> Tìm theo ID = 6:");
                cart.searchById(6);

                System.out.println("\n==> Tìm theo title = 'Clean Code':");
                cart.searchByTitle("Clean Code");
        }
}
