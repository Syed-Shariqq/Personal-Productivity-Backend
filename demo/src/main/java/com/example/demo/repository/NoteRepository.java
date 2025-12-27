package com.example.demo.repository;

import com.example.demo.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);

    @Query("SELECT DISTINCT n FROM Note n LEFT JOIN FETCH n.mediaList WHERE n.id = :id")
    Note findNoteWithMedia(@Param("id") long id);


}
