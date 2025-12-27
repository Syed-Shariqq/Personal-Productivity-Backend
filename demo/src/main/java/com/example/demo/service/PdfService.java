package com.example.demo.service;

import com.example.demo.entity.Note;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    public ByteArrayOutputStream exportNotesTOPdf(Note note) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);

        PdfDocument pdfDocument = new PdfDocument(writer);

        Document document = new Document(pdfDocument);

        document.add(new Paragraph(note.getTitle()));

        document.add(new Paragraph((note.getContent())));

        document.add(new Paragraph(String.valueOf(note.getDate())));

        document.close();

        return out;

    }

}