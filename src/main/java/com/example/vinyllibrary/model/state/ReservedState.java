package com.example.vinyllibrary.model.state;

import com.example.vinyllibrary.model.Vinyl;

public class ReservedState implements State
{

    @Override
    public void onReserve(Vinyl vinyl)
    {
        throw new IllegalArgumentException("The vinyl is already reserved.");
    }

    @Override
    public void onBorrow(Vinyl vinyl)
    {
        if(vinyl.getBorrower() != null && vinyl.getBorrower().equals(vinyl.getReserver()) )
        {
            vinyl.setState(new BorrowedAndReservedState());
            vinyl.setBorrower(vinyl.getReserver());
            vinyl.setReserver(null);
        }
        else
            throw new IllegalArgumentException("You can't borrow the vinyl that was reserved by other person.");
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
        return "reserved";
    }
}
