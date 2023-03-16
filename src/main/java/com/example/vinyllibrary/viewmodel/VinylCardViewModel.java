package com.example.vinyllibrary.viewmodel;

import com.example.vinyllibrary.model.Model;
import com.example.vinyllibrary.model.Vinyl;
import com.example.vinyllibrary.view.VinylCardController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class VinylCardViewModel
{
    private ObjectProperty<Image> imageProperty;
    private StringProperty descriptionProperty;
    private Model model;
    private ViewModelFactory viewModelFactory;
    private MainViewModel parentViewModel;

    public VinylCardViewModel(Model model, ViewModelFactory viewModelFactory)
    {
        this.model = model;
        imageProperty = new SimpleObjectProperty<>();
        descriptionProperty = new SimpleStringProperty();
        this.viewModelFactory = viewModelFactory;
    }

    public void setParentViewModel(MainViewModel parentViewModel)
    {
        this.parentViewModel = parentViewModel;
    }

    public void bindImage(ObjectProperty<Image> property)
    {
        property.bind(imageProperty);
    }


    public void setImageProperty(Image imageProperty)
    {
        this.imageProperty.set(imageProperty);
    }

    public void bindDescription(StringProperty property)
    {
        property.bind(descriptionProperty);
    }

    public void setDescription(String val)
    {
        descriptionProperty.set(val);
    }
}
