package com.example.demo.controller;

import com.example.demo.entity.Note;
import com.example.demo.repository.NoteRepository;
import com.example.demo.service.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@Slf4j
public class PdfController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private PdfService pdfService;


    @GetMapping("/{id}/download")
    public void downloadToPdf(@PathVariable long id, HttpServletResponse response) throws IOException{
        Note note = noteRepository.findById(id).orElseThrow();

        ByteArrayOutputStream stream = pdfService.exportNotesTOPdf(note);


        response.setContentType("Application / pdf");

        response.setHeader("Content-Disposition", "attachment; filename=note.pdf");

        response.getOutputStream().write(stream.toByteArray());
    }
}
