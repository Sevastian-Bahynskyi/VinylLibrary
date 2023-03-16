package com.example.vinyllibrary;

import com.example.vinyllibrary.model.Model;
import com.example.vinyllibrary.model.Person;
import com.example.vinyllibrary.model.Vinyl;
import com.example.vinyllibrary.model.state.AvailableState;
import com.example.vinyllibrary.model.state.BorrowedState;
import com.example.vinyllibrary.model.state.ReservedState;
import com.example.vinyllibrary.viewmodel.ActionViewModel;
import com.example.vinyllibrary.viewmodel.ViewModelFactory;
import javafx.application.Platform;

import java.util.Random;

public class Bot extends Person implements Runnable
{
    private ActionViewModel viewModel;
    private Model model;
    public Bot(String name, String email, ActionViewModel viewModel, Model model)
    {
        super(name, email);
        this.viewModel = viewModel;
        this.model = model;
    }


    @Override
    public void run()
    {
        while (true)
        {

            Random random = new Random();
            int action = random.nextInt(3) + 1;
            Vinyl vinyl = model.getVinyls().get(random.nextInt(model.getVinyls().size()));

            try
            {
                switch (action)
                {
                    case 1 -> Platform.runLater(() -> viewModel.reserve(vinyl, this));
                    case 2 -> Platform.runLater(() -> viewModel.return_(vinyl));
                    case 3 -> Platform.runLater(() -> viewModel.borrow(vinyl, this));
                }
            }
            catch (Exception e)
            {

            }

            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }

        }
    }
}
