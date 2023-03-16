package com.example.vinyllibrary.viewmodel;

import com.example.vinyllibrary.model.Model;

import java.util.ArrayList;

public class ViewModelFactory
{

    private MainViewModel mainViewModel;
    private ActionViewModel actionViewModel;
    private ArrayList<VinylCardViewModel> cardViewModelArrayList;
    public ViewModelFactory(Model model){
        mainViewModel = new MainViewModel(model, this);
        actionViewModel = new ActionViewModel(model, this);
        cardViewModelArrayList = new ArrayList<>();
    }

    public void addCardViewModel(VinylCardViewModel viewModel)
    {
        cardViewModelArrayList.add(viewModel);
    }

    public ArrayList<VinylCardViewModel> getCardViewModelArrayList()
    {
        return cardViewModelArrayList;
    }

    public MainViewModel getMainViewModel()
    {
        return mainViewModel;
    }
    public ActionViewModel getActionViewModel()
    {
        return actionViewModel;
    }
}
