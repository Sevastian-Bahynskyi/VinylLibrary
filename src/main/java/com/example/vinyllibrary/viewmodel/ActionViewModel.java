package com.example.vinyllibrary.viewmodel;

import com.example.vinyllibrary.model.Model;
import com.example.vinyllibrary.model.Person;
import com.example.vinyllibrary.model.Vinyl;
import com.example.vinyllibrary.view.ViewHandler;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.image.Image;
import javafx.util.FXPermission;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ActionViewModel implements PropertyChangeListener
{
    private ViewModelFactory viewModelFactory;
    private ObjectProperty<Image> image;
    private PropertyChangeSupport support;
    private Vinyl selectedVinyl;
    private SimpleListProperty<String> people;
    private StringProperty descriptionProperty;
    private StringProperty errorProperty;

    private int selectedPersonIndex;
    private ObjectProperty<String> valueProperty;
    private ArrayList<SimpleBooleanProperty> disabledProperties;
    private Model model;

    public ActionViewModel(Model model, ViewModelFactory viewModelFactory)
    {
        this.viewModelFactory = viewModelFactory;
        this.model = model;
        valueProperty = new SimpleObjectProperty<>("Choose the person");
        image = new SimpleObjectProperty<>();
        descriptionProperty = new SimpleStringProperty();
        disabledProperties = new ArrayList<>();
        disabledProperties.add(new SimpleBooleanProperty());
        disabledProperties.add(new SimpleBooleanProperty());
        disabledProperties.add(new SimpleBooleanProperty());
        disabledProperties.add(new SimpleBooleanProperty());
        valueProperty = new SimpleObjectProperty<>();
        errorProperty = new SimpleStringProperty();
        people = new SimpleListProperty<>(FXCollections.observableArrayList());
        support = new PropertyChangeSupport(this);

        people.addAll(getPeopleToString());
    }


    public void selectedVinyl(Vinyl vinyl)
    {
        selectedVinyl = vinyl;
    }

    public void setDescriptionProperty(String descriptionProperty)
    {
        this.descriptionProperty.set(descriptionProperty);
    }

    public void setImage(Image image)
    {
        this.image.set(image);
    }


    public void bindValueProperty(ObjectProperty<String> property)
    {
        property.bindBidirectional(valueProperty);
    }


    public void bindPeople(ObjectProperty<ObservableList<String>> property)
    {
        people.bind(property);
    }

    public void bindDisabledProperty(ArrayList<BooleanProperty> properties)
    {
        for (int i = 0; i < properties.size(); i++)
        {
            properties.get(i).bindBidirectional(disabledProperties.get(i));
        }
    }

    public void bindDescription(StringProperty property)
    {
        property.bind(descriptionProperty);
    }

    public void bindImage(ObjectProperty<Image> property)
    {
        property.bind(image);
    }

    public void bindError(StringProperty property)
    {
        property.bind(errorProperty);
    }

    public void setSelectedPersonIndex(int selectedPersonIndex)
    {
        this.selectedPersonIndex = selectedPersonIndex;
    }

    public void onReserve()
    {
        int index = model.getVinyls().indexOf(selectedVinyl);
        Vinyl vinyl = model.getVinyls().get(index);
        errorProperty.set("");
        valueProperty.set("Choose the person");
        if (selectedPersonIndex == -1)
        {
            errorProperty.set("Choose the person");
            return;
        }
        Person person = model.getPeople().get(selectedPersonIndex);
        reserve(vinyl, person);
    }

    public void reserve(Vinyl vinyl, Person person)
    {
        int index = model.getVinyls().indexOf(vinyl);
        try
        {
            model.onReserve(vinyl, person);
            viewModelFactory.getCardViewModelArrayList().get(index).setDescription(vinyl.toString());
            descriptionProperty.set(vinyl.toString());
            System.out.println(vinyl + " was reserved by " + person.toString());

        }
        catch (Exception e)
        {
            errorProperty.set(e.getMessage());
        }
    }


    public void onBorrow()
    {
        int index = model.getVinyls().indexOf(selectedVinyl);
        Vinyl vinyl = model.getVinyls().get(index);
        errorProperty.set("");
        valueProperty.set("Choose the person");
        if (selectedPersonIndex == -1)
        {
            errorProperty.set("Choose the person");
            return;
        }
        Person person = model.getPeople().get(selectedPersonIndex);
        borrow(vinyl, person);
    }

    public void borrow(Vinyl vinyl, Person person)
    {
        int index = model.getVinyls().indexOf(vinyl);
        try
        {
            model.onBorrow(vinyl, person);
            viewModelFactory.getCardViewModelArrayList().get(index).setDescription(vinyl.toString());
            descriptionProperty.set(vinyl.toString());
            System.out.println(vinyl + " was borrowed by " + person.toString());

        }
        catch (Exception e)
        {
            errorProperty.set(e.getMessage());
        }
    }

    public void onReturn()
    {
        int index = model.getVinyls().indexOf(selectedVinyl);
        Vinyl vinyl = model.getVinyls().get(index);
        errorProperty.set("");
        valueProperty.set("Choose the person");
        if (selectedPersonIndex == -1)
        {
            errorProperty.set("Choose the person");
            return;
        }
        Person person = model.getPeople().get(selectedPersonIndex);
        return_(vinyl);
    }

    public void return_(Vinyl vinyl)
    {
        int index = model.getVinyls().indexOf(vinyl);
        try
        {
            model.onReturn(vinyl);
            viewModelFactory.getCardViewModelArrayList().get(index).setDescription(vinyl.toString());
            descriptionProperty.set(vinyl.toString());
            System.out.println(vinyl + " was returned by");

        }
        catch (Exception e)
        {
            errorProperty.set(e.getMessage());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if(evt.getPropertyName().equals("set value for a choice box"))
        {
            valueProperty.set((String) evt.getNewValue());
        }
        else
        {
            selectedVinyl((Vinyl) evt.getNewValue());
            for (SimpleBooleanProperty property : disabledProperties)
            {
                property.set(selectedVinyl.markedAsDeleted);
            }
        }
        people.clear();
        people.addAll(getPeopleToString());
        errorProperty.set("");
    }

    private ArrayList<String> getPeopleToString()
    {
        ArrayList<String> strings = new ArrayList<>();
        for (Person p:
             model.getPeople())
        {
            strings.add(p.toString());
        }
        return strings;
    }

    public void onDelete(ViewHandler viewHandler)
    {
        int index = model.getVinyls().indexOf(selectedVinyl);
        Vinyl vinyl = model.getVinyls().get(index);
        try
        {
            model.remove(vinyl);
            viewModelFactory.getMainViewModel().refreshTable();
            viewModelFactory.getCardViewModelArrayList().get(index).setDescription(vinyl.toString());
            descriptionProperty.set(vinyl.toString());
            support.firePropertyChange("disable buttons", null, true);
            viewHandler.closeView();
        }
        catch (Exception e)
        {
            errorProperty.set(e.getMessage());
        }
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
