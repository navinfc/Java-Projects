package main.ecommerce_d;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductPage {
    ListView<HBox> products;

    ListView<HBox> productsbySearch(String search) throws SQLException {
        products = new ListView<>();
        ObservableList<HBox> productsList = FXCollections.observableArrayList();
        ResultSet res = Main.connection.executeQuery("Select * from product ");
        while(res.next()){
            if(res.getString("productName").toLowerCase().contains(search.toLowerCase())){
                Label name = new Label();
                Label productId = new Label();
                Label price = new Label();
                Button buy = new Button();
                name.setMinWidth(50);
                productId.setMinWidth(50);
                price.setMinWidth(50);
                buy.setText("Buy");
                HBox productDetails  = new HBox();
                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(Main.emailId.equals("")){
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.setContentText("Please Login First");
                            dialog.showAndWait();
                        }
                        else{
                            System.out.println("You are logged in with " + Main.emailId);
                            Order order = new Order();
                            try {
                                order.placeOrder(productId.getText());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("Buy Button is getting clicked");
                    }
                });
                name.setText(res.getString("productName"));
                price.setText(res.getString("price"));
                productId.setText(res.getString("productId"));
                productDetails.getChildren().addAll(productId, name, price, buy);
                productsList.add(productDetails);
            }

        }
        products.setItems(productsList);
        return products;
    }

    ListView<HBox> products() throws SQLException {
        products = new ListView<>();
        ObservableList<HBox> productsList = FXCollections.observableArrayList();
        ResultSet res = Main.connection.executeQuery("Select * from product ");
        while(res.next()){
            Label name = new Label();
            Label productId = new Label();
            Label price = new Label();
            Button buy = new Button();
            name.setMinWidth(50);
            productId.setMinWidth(50);
            price.setMinWidth(50);
            buy.setText("Buy");
            HBox productDetails  = new HBox();
            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(Main.emailId.equals("")){
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("Order");
                        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.setContentText("Please Login First");
                        dialog.showAndWait();
                    }
                    else{
                        System.out.println("You are logged in with " + Main.emailId);
                        Order order = new Order();
                        try {
                            order.placeOrder(productId.getText());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("Buy Button is getting clicked");
                }
            });
            name.setText(res.getString("productName"));
            price.setText(res.getString("price"));
            productId.setText(res.getString("productId"));
            productDetails.getChildren().addAll(productId, name, price, buy);
            productsList.add(productDetails);
        }
        products.setItems(productsList);
        return products;
    }
}
