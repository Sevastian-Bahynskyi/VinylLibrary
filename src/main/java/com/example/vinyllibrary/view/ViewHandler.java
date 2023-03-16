package com.example.vinyllibrary.view;

import com.example.vinyllibrary.viewmodel.ViewModelFactory;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

public class ViewHandler
{
    private Stage primaryStage;
    private Scene currentScene;
    private Scene tempScene;
    private Stage tempStage;
    private ViewFactory viewFactory;


    public ViewHandler(ViewModelFactory viewModelFactory)
    {
        viewFactory = new ViewFactory(this, viewModelFactory);
    }

    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        currentScene = new Scene(viewFactory.load(ViewFactory.MAIN));
        tempScene = new Scene(viewFactory.load(ViewFactory.ACTION));
        openView(ViewFactory.MAIN);
    }

    public void startView(String id)
    {
        Region root = viewFactory.load(ViewFactory.ACTION);
        tempScene.setRoot(root);
        tempStage = new Stage();
        if (root.getUserData() == null) {
            tempStage.setTitle("");
        } else {
            tempStage.setTitle(root.getUserData().toString());
        }
        tempStage.setScene(tempScene);
        tempStage.sizeToScene();
        tempStage.show();
    }

    public void openView(String id)
    {
        Region root = viewFactory.load(id);
        currentScene.setRoot(root);
        if (root.getUserData() == null) {
            primaryStage.setTitle("");
        } else {
            primaryStage.setTitle(root.getUserData().toString());
        }
        primaryStage.setScene(currentScene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public void closeView()
    {
        tempStage.close();
    }
}
