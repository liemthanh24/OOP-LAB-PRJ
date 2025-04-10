package main;

public class Aims {
    public static void main(String[] args) {
        // Create a new cart
        Cart Order = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        Order.add_DigitalVideoDisc(dvd1);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        Order.add_DigitalVideoDisc(dvd2);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);

        Order.add_DigitalVideoDisc(dvd3);
        Order.view_Cart();
        Order.rem_DigitalVideoDisc(dvd3);
        Order.add_DigitalVideoDisc(dvd3);
        Order.rem_DigitalVideoDisc(dvd2);
        Order.view_Cart();
        Order.rem_DigitalVideoDisc(dvd1);
        Order.rem_DigitalVideoDisc(dvd3);
        Order.view_Cart();
    }
}
