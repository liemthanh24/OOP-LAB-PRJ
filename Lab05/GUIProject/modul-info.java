module GUIProject { // Ví dụ: module PainterApp hoặc tên project của bạn

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    // Thêm các module JavaFX khác nếu cần, ví dụ: javafx.media, javafx.web

    // Mở package chứa Controller cho javafx.fxml để nó có thể truy cập
    // Thay hust.soict.hedspi.javafx bằng package chứa PainterController.java của bạn
    opens hust.soict.hedspi.javafx to javafx.fxml;

    // Export package chứa lớp Application chính (Painter.java)
    // Thay hust.soict.hedspi.javafx nếu lớp Painter.java của bạn ở package khác
    exports hust.soict.hedspi.javafx;
}