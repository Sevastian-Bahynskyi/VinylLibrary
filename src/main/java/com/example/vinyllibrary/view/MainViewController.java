package com.example.vinyllibrary.view;

import com.example.vinyllibrary.model.Vinyl;
import com.example.vinyllibrary.viewmodel.MainViewModel;
import com.example.vinyllibrary.viewmodel.ViewModelFactory;
import com.example.vinyllibrary.viewmodel.VinylCardViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

public class MainViewController
{
    @FXML
    private GridPane vinylTable;
    @FXML
    private VBox mainPane;
    private MainViewModel viewModel;
    private ViewHandler viewHandler;
    private Region root;
    private MyGridPane gridPane;

    public void init(ViewHandler viewHandler, MainViewModel viewModel, Region root)
    {
        this.root = root;
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;


        gridPane = new MyGridPane(vinylTable.getChildren(), tableInit());
        gridPane.setHgap(gridPane.getHgap());
        gridPane.setVgap(gridPane.getVgap());
        gridPane.setPadding(gridPane.getPadding());
        gridPane.setAlignment(gridPane.getAlignment());
        gridPane.getColumnConstraints().addAll(vinylTable.getColumnConstraints());
        gridPane.getRowConstraints().addAll(vinylTable.getRowConstraints());
        VBox.setVgrow(gridPane, Priority.ALWAYS);
        mainPane.getChildren().remove(vinylTable);
        mainPane.getChildren().add(gridPane);
        viewModel.bindTable(gridPane.itemsProperty());
    }


    public ArrayList<VinylCardViewModel> tableInit()
    {
        ArrayList<VinylCardViewModel> cardViewModels = new ArrayList<>();
        try{
            for (int i = 0; i < viewModel.tableProperty().size(); i++)
            {
                FXMLLoader loader = new FXMLLoader(MainViewController.class.getResource("VinylCard.fxml"));
                Parent parent = loader.load();
                VinylCardController cardController = loader.getController();
                cardController.init(viewHandler, viewModel, root);
                cardViewModels.add(cardController.getCardViewModel());
                viewModel.getViewModelFactory().addCardViewModel(cardViewModels.get(i));
                int round = i/3;
                vinylTable.add(parent, i%3, round);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return cardViewModels;
    }


    public ViewHandler getViewHandler()
    {
        return viewHandler;
    }

    public MainViewModel getViewModel()
    {
        return viewModel;
    }

    public Region getRoot()
    {
        return root;
    }
}
