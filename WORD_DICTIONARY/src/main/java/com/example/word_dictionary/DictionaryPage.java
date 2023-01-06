package com.example.word_dictionary;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DictionaryPage {
    public AnchorPane root;
    DictionaryPage() throws IOException{
        root = FXMLLoader.load(getClass().getResource("DictionaryPage.fxml"));
    }
}
