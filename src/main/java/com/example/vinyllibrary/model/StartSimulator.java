package com.example.vinyllibrary.model;

import com.example.vinyllibrary.Bot;
import com.example.vinyllibrary.viewmodel.ActionViewModel;

public class StartSimulator
{
    private Bot bob;
    private Bot wendy;

    public StartSimulator(ActionViewModel viewModel, Model model)
    {
        bob = new Bot("Bob bobson", "frfrgufir@fks.ef", viewModel, model);
        wendy = new Bot("Wendy D", "wendypkf@fojf.dfe",viewModel, model);
        Thread threadBob = new Thread(bob);
        Thread threadWendy = new Thread(wendy);
        threadWendy.start();
        threadBob.start();
    }
}
