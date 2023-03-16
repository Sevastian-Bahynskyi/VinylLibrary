package com.example.vinyllibrary.view;

import com.example.vinyllibrary.model.Vinyl;
import com.example.vinyllibrary.viewmodel.VinylCardViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MyGridPane extends GridPane
{

    private SimpleListProperty<Vinyl> itemsProperty = new SimpleListProperty<>(FXCollections.observableArrayList());

    public MyGridPane(ObservableList<Node> children, ArrayList<VinylCardViewModel> viewModels)
    {
        getChildren().addAll(children);
        itemsProperty.addListener((observable, oldList, newList) ->
        {

            if(newList != null)
            {
                for (int i = 0; i < newList.size(); i++)
                {
                    VinylCardViewModel viewModel = viewModels.get(i);

                    System.out.println(viewModel);
                    VBox cell = (VBox) getChildren().get(i);
                    ImageView img = (ImageView) cell.getChildren().get(0);
                    viewModel.bindImage(img.imageProperty());
                    viewModel.setImageProperty(itemsProperty.get(i).getImage());
//                    img.setImage(itemsProperty.get(i).getImage());
//                    cell.getChildren().set(0, img);

                    HBox hbox = (HBox) cell.getChildren().get(1);
                    Label lbl = (Label) hbox.getChildren().get(0);
                    viewModel.bindDescription(lbl.textProperty());
                    viewModel.setDescription(itemsProperty.get(i).toString());
//                    lbl.setText(itemsProperty.get(i).toString());
//                    cell.getChildren().set(1, hbox);
                }
            }
            //todo -> remember that getChildren returns node
            //     -> remember that TableView stores data as children, and GridPane - nodes
            //     ->
        });
    }

    public ObservableList<Vinyl> getItems()
    {
        return itemsProperty.get();
    }

    public void setItems(ObservableList<Vinyl> items)
    {
        itemsProperty.set(items);
    }

    public SimpleListProperty<Vinyl> itemsProperty()
    {
        return itemsProperty;
    }
}
