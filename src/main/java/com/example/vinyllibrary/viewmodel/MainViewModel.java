package com.example.vinyllibrary.viewmodel;

import com.example.vinyllibrary.model.Model;
import com.example.vinyllibrary.model.Vinyl;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MainViewModel implements PropertyChangeListener
{
    private ViewModelFactory viewModelFactory;
    private PropertyChangeSupport support;
    private SimpleListProperty<Vinyl> tableProperty;
    private Model model;
    public MainViewModel(Model model, ViewModelFactory viewModelFactory)
    {
        this.viewModelFactory = viewModelFactory;
        tableProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.model = model;
        support = new PropertyChangeSupport(this);
        refreshTable();
    }

    public void refreshTable()
    {
        tableProperty.clear();
        tableProperty.addAll(model.getVinyls());
    }

    public void bindTable(SimpleListProperty<Vinyl> property)
    {
        property.bind(tableProperty);
    }

    public SimpleListProperty<Vinyl> tableProperty()
    {
        return tableProperty;
    }

    public ViewModelFactory getViewModelFactory()
    {
        return viewModelFactory;
    }

    public void loadDataToActionView(Vinyl vinyl)
    {
        viewModelFactory.getActionViewModel().setImage(vinyl.getImage());
        viewModelFactory.getActionViewModel().setDescriptionProperty(vinyl.toString());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {


    }

    public Model getModel()
    {
        return model;
    }
}
