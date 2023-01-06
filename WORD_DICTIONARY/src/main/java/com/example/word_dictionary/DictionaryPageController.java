package com.example.word_dictionary;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class DictionaryPageController {

    private DictionarySaver db;
    @FXML
    TextField search;

    @FXML
    TextField newword;

    @FXML
    TextField meaning;

    @FXML
    TextField findword;

    @FXML
    public void searchButton(MouseEvent event) throws  ClassNotFoundException {

        try {


            db = new DictionarySaver();
            //db.serializeHashMap();
            db.deserializeHashMap();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Dictionary");

            if(search.getText().equals("")){
                alert.setContentText("Please enter a word to search");
                alert.showAndWait();
            }

            else if (db.getDictionaryList().containsKey(search.getText().toLowerCase())) {
                alert.setContentText("The Word is available in the dictionary");
                alert.showAndWait();
            } else {
                alert.setContentText("The Word is not available in the dictionary");
                alert.showAndWait();
            }
        }
        catch(IOException ex){
            db.serializeHashMap();
            System.out.println("Exception Resolved ");
        }
    }

    @FXML
    public void Add(MouseEvent event) throws IOException, ClassNotFoundException {
        db = new DictionarySaver();
        db.deserializeHashMap();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Dictionary");

        if(newword.getText().equals("") || meaning.getText().equals("")){
            alert.setContentText("Please enter a word/meaning  to Add to the Dictionary");
            alert.showAndWait();
        }
        else if(db.getDictionaryList().containsKey(newword.getText().toLowerCase())){
            alert.setContentText("The word is already present in the dictionary");
            alert.showAndWait();
        }
        else{
            db.getDictionaryList().put(newword.getText().toLowerCase(), meaning.getText());
            db.serializeHashMap();

            alert.setContentText("New Word is Added to the Dictionary");
            alert.showAndWait();
        }

    }

    @FXML
    public void Meaning(MouseEvent event) throws IOException, ClassNotFoundException {
        db = new DictionarySaver();
        db.deserializeHashMap();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Meaning");

        if(findword.getText().equals("")){
            alert.setContentText("Please enter a Word to Find Meaning");
            alert.showAndWait();
        }
        else if(db.getDictionaryList().containsKey(findword.getText().toLowerCase())){

            alert.setContentText(db.getDictionaryList().get(findword.getText().toLowerCase()));
            alert.showAndWait();
        }
        else {
            alert.setContentText("The word is not present in the dictionary");
            alert.showAndWait();
        }

    }
}
