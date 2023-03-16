package com.example.vinyllibrary.model.state;

import com.example.vinyllibrary.model.Vinyl;

public class AvailableState implements State
{
    @Override
    public void onReserve(Vinyl vinyl)
    {
        vinyl.setState(new ReservedState());
    }

    @Override
    public void onBorrow(Vinyl vinyl)
    {
        vinyl.setState(new BorrowedState());
    }

    @Override
    public void onReturn(Vinyl vinyl)
    {
        throw new IllegalArgumentException("You can't return available game.");
    }


    public String toString()
    {
        return "available";
    }
}
