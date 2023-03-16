package com.example.vinyllibrary.view;

import com.example.vinyllibrary.VinylApplication;
import com.example.vinyllibrary.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOException;

public class ViewFactory
{
    public static final String MAIN = "main";
    public static final String ACTION = "action";
    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory;
    private MainViewController mainViewController;
    private VinylActionViewController actionViewController;
    public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
    {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
    }

    public Region loadMainView()
    {
        if (mainViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainView.fxml"));
            try {
                Region root = loader.load();
                mainViewController = loader.getController();
                mainViewController.init(viewHandler, viewModelFactory.getMainViewModel(), root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mainViewController.getRoot();
    }

    public Region loadActionView()
    {
        if (actionViewController == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ActionView.fxml"));
            try
            {
                Region root = loader.load();
                actionViewController = loader.getController();
                actionViewController.init(viewHandler, viewModelFactory.getActionViewModel(), root);
                return root;
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return actionViewController.getRoot();
    }



    public Region load(String id)
    {
        return switch (id)
                {
                    case MAIN -> loadMainView();
                    case ACTION -> loadActionView();
                    default -> throw new IllegalArgumentException("Unknown view: " + id);
                };
    }
}
