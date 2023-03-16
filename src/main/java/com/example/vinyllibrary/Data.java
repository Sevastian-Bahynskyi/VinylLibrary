package com.example.vinyllibrary;

import com.example.vinyllibrary.model.Person;
import com.example.vinyllibrary.model.Vinyl;

import java.util.ArrayList;
import java.util.List;

public class Data
{
    public static ArrayList<Vinyl> vinyls()
    {
        String startUrl = "/com/example/vinyllibrary/image/";
        ArrayList<Vinyl> vinyls = new ArrayList<>();
        vinyls.addAll(List.of(
                new Vinyl("Thriller", "Michael Jackson", 1982,
                startUrl + "michael_jackson.jpg"),

                new Vinyl("Pet Sounds", "The Beach Boys", 1966,
                        startUrl + "pet_sounds.jpg"),

                new Vinyl("MTV Unplugged in New York", "Nirvana", 1994,
                        startUrl + "mtv_nirvana.jpg"),

                new Vinyl("Purple Rain", "Prince and The Revolution", 1984,
                        startUrl + "purple_rain.jpg"),

                new Vinyl("Blonde on Blonde", "Bob Dylan", 1966,
                        startUrl + "blonde_on_blonde.jpg"),

                new Vinyl("Back in Black", "AC/DC", 1980,
                        startUrl + "back_in_black.jpg"),

                new Vinyl("Nevermind", "Nirvana", 1991,
                        startUrl + "nevermind.jpg"),

                new Vinyl("The Velvet Underground & Nico", "The Velvet Underground", 1967,
                        startUrl + "velvet_underground.jpg"),

                new Vinyl("OK Computer", "Radiohead", 1997,
                        startUrl + "ok_computer.jpg")
        ));
        return vinyls;
    }

    public static ArrayList<Person> people()
    {
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Liam Smith", "liam.smith@example.com"));
        people.add(new Person("Olivia Johnson", "olivia.johnson@example.com"));
        people.add(new Person("Noah Williams", "noah.williams@example.com"));
        people.add(new Person("Emma Jones", "emma.jones@example.com"));
        people.add(new Person("Aiden Brown", "aiden.brown@example.com"));
        people.add(new Person("Sophia Garcia", "sophia.garcia@example.com"));
        people.add(new Person("Caleb Martinez", "caleb.martinez@example.com"));
        people.add(new Person("Isabella Davis", "isabella.davis@example.com"));
        people.add(new Person("Ethan Wilson", "ethan.wilson@example.com"));
        people.add(new Person("Mia Anderson", "mia.anderson@example.com"));
        return people;
    }
}
