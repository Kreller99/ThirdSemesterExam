package com.exam.model;


import com.fasterxml.jackson.annotation.*;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.core.serializer.Serializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

// Entity bruges til at beskrive en entitet i et system.
@Entity
public class Student implements Serializable {

    // Viser, at ID'et bruges til at identificere en student.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // Jeg har valgt at kommentere ManagedReference ud,
    // da jeg ellers ikke ville kunne bruge post og put metoderne i RestStudentController.
    //@JsonManagedReference

    // En studerende kan have en supervisor, mens en Supervisor kan have mange studerende.
    // Da vi bruger JPA, sætter vi derfor forholdet til ManyToOne, hvori vi siger,
    // at supervisor og student skal merge ved opdatering.
    @ManyToOne(cascade = CascadeType.DETACH)
    // Vi sætter et supervisor_id på den studerende, da den studerende kun har en supervisor.
    @JoinColumn(name = "supervisor_id")
    private Supervisor supervisor;

    public Student() {}

    // Vi indsætter ikke Supervisor i parametrene,
    // da Supervisor er ekstern (kommer fra Supervisor klassen, og skal derfor ikke skrives i Student construktoren)
    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Student(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.supervisor = supervisor;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }
}
