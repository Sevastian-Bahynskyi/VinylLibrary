package com.example.vinyllibrary.model;

import com.example.vinyllibrary.model.state.AvailableState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VinylModelManager implements Model
{
    private ArrayList<Vinyl> vinyls;
    private ArrayList<Person> people;
    public VinylModelManager(ArrayList<Vinyl> vinyls, ArrayList<Person> people)
    {
        this.vinyls = vinyls;
        this.people = people;
    }


    @Override
    public void remove(Vinyl vinyl)
    {
        for (Vinyl v: vinyls)
        {
            if(v.equals(vinyl))
            {
                if (v.getState() instanceof AvailableState)
                {
                    v.markedAsDeleted = true;//TODO -> check
                } else
                {
                    v.toDelete = true;
                }
                break;
            }
        }
    }

    @Override
    public void onBorrow(Vinyl vinyl, Person borrower)
    {
        if (vinyl != null && borrower != null)
        {
            vinyl.setBorrower(borrower);
            vinyl.onBorrow();
        }
    }

    @Override
    public void onReserve(Vinyl vinyl, Person reserver)
    {
        if (vinyl != null && reserver != null)
        {
            vinyl.setReserver(reserver);
            vinyl.onReserve();
        }
    }

    @Override
    public void onReturn(Vinyl vinyl)
    {
        if (vinyl != null)
        {
            vinyl.onReturn();
        }
    }

    @Override
    public ArrayList<Person> getPeople()
    {
        return people;
    }

    @Override
    public ArrayList<Vinyl> getVinyls()
    {
        return vinyls;
    }
}
