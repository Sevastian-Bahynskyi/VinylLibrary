package com.example.vinyllibrary.model.state;

import com.example.vinyllibrary.model.Vinyl;

public class BorrowedState implements State
{
    @Override
    public void onReserve(Vinyl vinyl)
    {
        vinyl.setState(new BorrowedAndReservedState());
    }

    @Override
    public void onBorrow(Vinyl vinyl)
    {
        throw new IllegalArgumentException("Vinyl is already borrowed.");
    }

    @Override
    public void onReturn(Vinyl vinyl)
    {
        vinyl.setState(new AvailableState());
        if(vinyl.toDelete)
            vinyl.markedAsDeleted = true;
    }

    public String toString()
    {
        return "borrowed";
    }
}
