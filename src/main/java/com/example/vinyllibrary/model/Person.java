package com.example.vinyllibrary.model;

public class Person
{
    private String name;
    private String email;


    public Person(String name, String email)
    {
        this.email = email;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public boolean equals(Object obj)
    {
        if(obj == null || obj.getClass() != getClass())
            return false;

        Person p = (Person) obj;
        return name.equals(p.name) && email.equals(p.email);
    }

    public String toString()
    {
        return name;
    }
}
