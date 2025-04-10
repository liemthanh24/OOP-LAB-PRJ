package main;

public class Aims {
    public static void main(String[] args) {
        // Create a new cart
        Cart Order = new Cart();

        // Create a new DVD and add them to the cart
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", 18.99f);
        DigitalVideoDisc dvd4 = new DigitalVideoDisc("Avengers: Endgame", "Action", "Anthony Russo", 181, 29.99f);
        DigitalVideoDisc dvd5 = new DigitalVideoDisc("Joker", "Thriller", "Todd Phillips", 122, 14.99f);

        //thêm 1 đĩa
        Order.add_DigitalVideoDisc(dvd1);
        Order.add_DigitalVideoDisc(dvd2);

        //thêm 2 đĩa
        Order.add_DigitalVideoDisc(dvd3, dvd4);

        //thêm danh sách đĩa bằng mảng
        DigitalVideoDisc[] dvdList = { dvd5 };
        Order.add_DigitalVideoDisc(dvdList);

        Order.view_Cart();
        //xóa 1 đĩa
        Order.rem_DigitalVideoDisc(dvd5);
        Order.view_Cart();
    }
}
