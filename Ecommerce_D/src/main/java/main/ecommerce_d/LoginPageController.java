package main.ecommerce_d;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {

    @FXML
    TextField email;

    @FXML
    PasswordField password;

    @FXML
    public void login(MouseEvent e) throws SQLException, IOException {
        String query = String.format("Select * from user where emailID = '%s' AND pass = '%s'", email.getText(), password.getText());
        ResultSet res = Main.connection.executeQuery(query);
        if (res.next()){
            Main.emailId = res.getString("emailID");
            String userType = res.getString("usertype");
            if(userType.equals("Seller")){
                AnchorPane sellerPage = FXMLLoader.load(getClass().getResource("sellerpage.fxml"));
                Main.root.getChildren().add(sellerPage);
            }
            else {
                System.out.println("We are logged in as Buyer");
                ProductPage productPage = new ProductPage();

                Header header = new Header();
                AnchorPane productPane = new AnchorPane();
                productPane.getChildren().add(productPage.products());
                Main.root.getChildren().clear();
                productPane.setLayoutX(125);
                productPane.setLayoutY(100);
                Main.root.getChildren().addAll(header.root,productPane);
            }
            System.out.println("The user is present in the table");
        }
        else{
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Login");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Login Failed!!!! Please check username and password and try again");
            dialog.showAndWait();
        }
    }
}
