module com.example.vinyllibrary {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.vinyllibrary to javafx.fxml;
    opens com.example.vinyllibrary.view to javafx.fxml;
    opens com.example.vinyllibrary.image to javafx.fxml;
    opens com.example.vinyllibrary.model to javafx.fxml;
    exports com.example.vinyllibrary;
    exports com.example.vinyllibrary.view;
}