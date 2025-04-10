package main;

import java.util.ArrayList;

public class Cart {
    private ArrayList<DigitalVideoDisc> items;
    public Cart() {
        this.items = new ArrayList<>();
    }

    //thêm 1 đơn
    public void add_DigitalVideoDisc(DigitalVideoDisc disc) {
        if (disc == null) {
            System.out.println("Đĩa không hợp lệ.");
            return;
        }
        if (!items.contains(disc)) {
            items.add(disc);
            System.out.println("Đã thêm đĩa: " + disc.getTitle());
        } else {
            System.out.println("Đĩa này đã có trong giỏ hàng");
        }
    }

    //xóa 1 đơn hàng
    public void rem_DigitalVideoDisc(DigitalVideoDisc disc) {
        if (disc == null) {
            System.out.println("Đĩa không hợp lệ.");
            return;
        }
        if (items.contains(disc)) {
            items.remove(disc);
            System.out.println("Đã xóa đĩa: " + disc.getTitle());
        } else {
            System.out.println("Đĩa không có trong giỏ hàng");
        }
    }

    //tổng giá trị của giỏ hàng
    public float totalCost() {
        if (items.isEmpty()) {
            System.out.println("Giỏ hàng trống.");
            return 0;
        }
        float total = 0;
        for (DigitalVideoDisc disc : items) {
            total += disc.getCost();
        }
        return total;
    }

    //xem giỏ
    public void view_Cart() {
        if (items.isEmpty()) {
            System.out.println("Giỏ hàng trống.");
        } else {
            System.out.println("Danh sách đĩa trong giỏ hàng:");
            for (int i = 0; i < items.size(); i++) {
                DigitalVideoDisc disc = items.get(i);
                System.out.println((i + 1) + ". " + disc.getTitle() + " - " + disc.getCategory() + " - " + disc.getCost() + "$");
            }
        }
    }
}
