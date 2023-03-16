package com.example.vinyllibrary.model.state;

import com.example.vinyllibrary.model.Vinyl;

public class BorrowedAndReservedState implements State
{
    @Override
    public void onReserve(Vinyl vinyl)
    {
        throw new IllegalArgumentException("The vinyl is already reserved.");
    }

    @Override
    public void onBorrow(Vinyl vinyl)
    {
        throw new IllegalArgumentException("The vinyl is already borrowed.");
    }

    @Override
    public void onReturn(Vinyl vinyl)
    {
        vinyl.setState(new BorrowedState());
        vinyl.setBorrower(vinyl.getReserver());
        vinyl.setReserver(null);
    }

    public String toString()
    {
        return "borrowed and reserved";
    }
}
