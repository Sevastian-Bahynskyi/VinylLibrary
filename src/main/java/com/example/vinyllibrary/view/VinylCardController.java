package com.example.vinyllibrary.view;

import com.example.vinyllibrary.model.Vinyl;
import com.example.vinyllibrary.viewmodel.ActionViewModel;
import com.example.vinyllibrary.viewmodel.MainViewModel;
import com.example.vinyllibrary.viewmodel.VinylCardViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;


public class VinylCardController
{
    @FXML
    private ImageView image;
    @FXML
    private Label description;
    private PropertyChangeSupport support;
    private ViewHandler viewHandler;
    private MainViewModel viewModel;
    private VinylCardViewModel cardViewModel;
    private Region root;


    public void init(ViewHandler viewHandler, MainViewModel viewModel, Region root)
    {
        support = new PropertyChangeSupport(this);
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        cardViewModel = new VinylCardViewModel(viewModel.getModel(), viewModel.getViewModelFactory());
        cardViewModel.setParentViewModel(viewModel);
        cardViewModel.bindDescription(description.textProperty());
        support.addPropertyChangeListener("pass vinyl to action view model", viewModel.getViewModelFactory().getActionViewModel());
        support.addPropertyChangeListener("set value for a choice box", viewModel.getViewModelFactory().getActionViewModel());
        this.root = root;
    }
    @FXML
    public void onClick(MouseEvent e)
    {
        // todo fire property for selected node
        VBox source = (VBox)e.getSource();
        MyGridPane gridPane = (MyGridPane) source.getParent();
        int index = gridPane.getChildren().indexOf(source);

        Vinyl vinyl = viewModel.tableProperty().get(index);
        support.firePropertyChange("pass vinyl to action view model", null, vinyl);
        support.firePropertyChange("set value for a choice box", null, "Choose a person");
        viewModel.loadDataToActionView(vinyl);
        viewHandler.startView(ViewFactory.ACTION);
    }


    public VinylCardViewModel getCardViewModel()
    {
        return cardViewModel;
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
    {
        support.addPropertyChangeListener(propertyName, listener);
    }



    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener)
    {
        support.removePropertyChangeListener(propertyName, listener);
    }
}
