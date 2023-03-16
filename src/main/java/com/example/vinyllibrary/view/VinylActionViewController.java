package com.example.vinyllibrary.view;

import com.example.vinyllibrary.model.Model;
import com.example.vinyllibrary.model.Person;
import com.example.vinyllibrary.model.Vinyl;
import com.example.vinyllibrary.model.VinylModelManager;
import com.example.vinyllibrary.viewmodel.ActionViewModel;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class VinylActionViewController implements PropertyChangeListener
{
    @FXML
    private Label descriptionVinyl;

    @FXML
    private ImageView imageVinyl;

    @FXML
    private Label errorLabel;

    @FXML private Button reserveButton, borrowButton, deleteButton, returnButton;

    @FXML
    private ChoiceBox<String> comboBox;
    private ActionViewModel viewModel;
    private ViewHandler viewHandler;
    private Region root;
    public void init(ViewHandler viewHandler, ActionViewModel viewModel, Region root)
    {
        this.root = root;
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;


        viewModel.bindValueProperty(comboBox.valueProperty());
        viewModel.bindPeople(comboBox.itemsProperty());
        comboBox.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->
                viewModel.setSelectedPersonIndex(comboBox.getSelectionModel().getSelectedIndex())));
        viewModel.addPropertyChangeListener("disable buttons", this);
        viewModel.bindImage(imageVinyl.imageProperty());
        viewModel.bindDescription(descriptionVinyl.textProperty());
        viewModel.bindError(errorLabel.textProperty());
        ArrayList<BooleanProperty> disabledProperties = new ArrayList<>();
        disabledProperties.add(borrowButton.disableProperty());
        disabledProperties.add(deleteButton.disableProperty());
        disabledProperties.add(returnButton.disableProperty());
        disabledProperties.add(reserveButton.disableProperty());
        viewModel.bindDisabledProperty(disabledProperties);
    }

    public void onActionTest(ActionEvent event)
    {
        System.out.println(comboBox.getValue());
    }


    @FXML
    public void onReserve()
    {
        viewModel.onReserve();
    }

    @FXML
    public void onReturn()
    {
        viewModel.onReturn();
    }

    @FXML
    public void onDelete()
    {
        viewModel.onDelete(viewHandler);
    }

    @FXML
    public void onBorrow()
    {
        viewModel.onBorrow();
    }

    public Region getRoot()
    {
        return root;
    }

    public void disableButtons()
    {
        borrowButton.disabledProperty();
        reserveButton.setDisable(true);
        deleteButton.setDisable(true);
        returnButton.setDisable(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        disableButtons();
    }
}
