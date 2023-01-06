module com.example.word_dictionary {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.word_dictionary to javafx.fxml;
    exports com.example.word_dictionary;
}