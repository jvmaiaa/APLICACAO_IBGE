package com.example.pessoa.api.documents;

import com.example.pessoa.api.dto.response.PersonResponseDTO;
import com.example.pessoa.api.service.PersonService;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.util.List;
@Data
@RequiredArgsConstructor
@Component
public class PdfService {

    private final PersonService personService;

    public void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.gray);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Age", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Id Address", font));
        table.addCell(cell);
    }

    public void writeTableData(PdfPTable table){
        List<PersonResponseDTO> personsDto = personService.findAll();
        for (PersonResponseDTO user : personsDto){
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getName());
            table.addCell(String.valueOf(user.getAge()));
            table.addCell(user.getEmail());
            table.addCell(String.valueOf(user.getIdAddress()));
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.black);
        font.setSize(20);

        Paragraph title = new Paragraph("Lista de Usuários", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[] {1.5f, 2.5f, 2.5f, 2.5f, 1.5f});

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}
