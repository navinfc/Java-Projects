package main.ecommerce_d;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerPageController {

    @FXML
    TextField name, price, sellerID;

    @FXML
    public void AddProduct(MouseEvent e) throws SQLException {
        int productID = 1;
        ResultSet response2 = Main.connection.executeQuery("Select max(productID) as productID from product;");
        if(response2.next()){
            productID = response2.getInt("productID") + 1;
        }
        String query = String.format("Insert into product values(%s, '%s', %s, '%s')", productID, name.getText(), price.getText(), sellerID.getText());
        int response = Main.connection.executeUpdate(query);
        if(response > 0)
            System.out.println("New Product is ADDED");
    }
}
