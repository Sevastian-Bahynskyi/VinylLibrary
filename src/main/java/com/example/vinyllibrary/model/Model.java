package com.example.vinyllibrary.model;

import java.util.ArrayList;
import java.util.Map;

public interface Model
{
    ArrayList<Vinyl> getVinyls();
    void remove(Vinyl vinyl);
    void onBorrow(Vinyl vinyl, Person borrower);
    void onReserve(Vinyl vinyl, Person reserver);
    void onReturn (Vinyl vinyl);
    ArrayList<Person> getPeople();
}
