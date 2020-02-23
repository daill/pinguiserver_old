/*
 * Copyright 2019 Christian Kramer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files
 *  (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge,
 *  publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do
 *  so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


package de.daill.pinguiserver.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Institution {
    @Id
    private String id;

    private String name;
    private int plz;
    private LocalDate creationDate;

    private ArrayList<Group> groups;

    public Institution() {
    }

    public Institution(String name, int plz, ArrayList<Group> groups) {
        this(name, plz, LocalDate.now(), groups);
    }

    public Institution(String name, int plz, LocalDate date, ArrayList<Group> groups) {
        this.name = name;
        this.plz = plz;
        this.creationDate = date;
        this.groups = groups;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public int getPlz() {
        return plz;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
