package com.example.vinyllibrary.model.state;

import com.example.vinyllibrary.model.Vinyl;

public interface State
{
    String toString();

    void onReserve(Vinyl vinyl);
    void onBorrow(Vinyl vinyl);
    void onReturn(Vinyl vinyl);
}
