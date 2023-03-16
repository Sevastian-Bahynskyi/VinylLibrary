package com.example.vinyllibrary;

import com.example.vinyllibrary.model.Model;
import com.example.vinyllibrary.model.StartSimulator;
import com.example.vinyllibrary.model.VinylModelManager;
import com.example.vinyllibrary.view.ViewFactory;
import com.example.vinyllibrary.view.ViewHandler;
import com.example.vinyllibrary.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class VinylApplication extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        Model model = new VinylModelManager(Data.vinyls(), Data.people());
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        StartSimulator startSimulator = new StartSimulator(viewModelFactory.getActionViewModel(), model);
        ViewHandler view = new ViewHandler(viewModelFactory);
        view.start(primaryStage);
    }
}
