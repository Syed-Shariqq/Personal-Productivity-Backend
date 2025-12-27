package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "Notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String title;

    @NonNull
    @Column(length = 10000)
    private String content;

    private LocalDateTime date;

    private boolean pin = false;

   @ManyToOne
   @JoinColumn(foreignKey = @ForeignKey)
   @JsonBackReference
   private Folder folder;

   @OneToMany(mappedBy = "note",cascade = CascadeType.ALL, orphanRemoval = true)
   @JsonManagedReference
   private List<Media> mediaList = new ArrayList<>();
}
