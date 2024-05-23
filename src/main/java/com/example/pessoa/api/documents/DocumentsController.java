package com.example.pessoa.api.documents;

import com.example.pessoa.api.dto.response.PersonResponseDTO;
import com.example.pessoa.api.entity.Person;
import com.example.pessoa.api.service.PersonService;
import com.example.pessoa.api.service.impl.PersonServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/documents")
public class DocumentsController {

    private final PersonService personService;
    private final PdfService pdfService;
    private final CsvService csvService;

    @GetMapping("/pdf/export")
    public void exportToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:ss:mm");
        String currentDateTime = dateFormatter.format(new Date());
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);
        List<PersonResponseDTO> personResponseDTOS = personService.findAll();

        pdfService.export(response, personResponseDTOS);
    }

    @GetMapping("/csv/export")
    public void exportToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:ss:mm");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Context-Disposition";
        String headerValue = "atachment; filename=csv_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        csvService.export(response);
    }

}
