package com.exam.model;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

// Entity bruges til at beskrive en entitet i et system.
@Entity
public class Supervisor implements Serializable {

    // Viser, at ID'et bruges til at identificere en supervisor.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Refererer tilbage til Student klassen.
    // En studerende kan have en supervisor, mens en Supervisor kan have mange studerende.
    // Da vi bruger JPA, sætter vi derfor forholdet til OneToMany fra supervisors side.
    @JsonBackReference
    @OneToMany(mappedBy = "supervisor", fetch = FetchType.LAZY, orphanRemoval = true)
    Set<Student> student = new HashSet<>();

    public Supervisor() {}

    public Supervisor(String name) {
        this.name = name;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }

}
