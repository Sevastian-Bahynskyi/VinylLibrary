package com.example.vinyllibrary.model;

import com.example.vinyllibrary.model.state.AvailableState;
import com.example.vinyllibrary.model.state.BorrowedState;
import com.example.vinyllibrary.model.state.ReservedState;
import com.example.vinyllibrary.model.state.State;
import javafx.scene.image.Image;

import java.beans.PropertyChangeSupport;

public class Vinyl
{
    private State state;
    private String title;
    private String artist;
    private int year;
    private Person reserver;
    private Person borrower;
    private Image image;
    public boolean markedAsDeleted;
    public boolean toDelete;
    public PropertyChangeSupport support;


    public Vinyl(String title, String artist, int year)
    {
        this.artist = artist;
        this.title = title;
        this.year = year;
        reserver = null;
        borrower = null;
        this.state = new AvailableState();
        support = new PropertyChangeSupport(this);
        image = null;
    }

    public Vinyl(String title, String artist, int year, String imageUrl)
    {
        this.artist = artist;
        this.title = title;
        this.year = year;
        reserver = null;
        borrower = null;
        support = new PropertyChangeSupport(this);
        this.state = new AvailableState();
        image = new Image(getClass().getResourceAsStream(imageUrl));
    }

    public void onReserve()
    {
        state.onReserve(this);
    }

    public void onBorrow()
    {
        state.onBorrow(this);
    }

    public void onReturn()
    {
        state.onReturn(this);
    }

    public void setImage(String url)
    {
        this.image = new Image(getClass().getResourceAsStream(url));
    }

    public int getYear()
    {
        return year;
    }

    public Image getImage()
    {
        return image;
    }

    public State getState()
    {
        return state;
    }

    public String getArtist()
    {
        return artist;
    }

    public String getTitle()
    {
        return title;
    }

    public void setState(State state)
    {
        this.state = state;
    }

    public Person getBorrower()
    {
        return borrower;
    }

    public Person getReserver()
    {
        return reserver;
    }

    public void setBorrower(Person borrower)
    {
        this.borrower = borrower;
    }

    public void setReserver(Person reserver)
    {
        this.reserver = reserver;
    }

    public String toString()
    {
        String str = "";
        str += "Title: " + title + "\nArtist: " + artist  + "\nYear: " +
                year + "; Status: " + (markedAsDeleted? "deleted" : state);
        return str;
    }


}
