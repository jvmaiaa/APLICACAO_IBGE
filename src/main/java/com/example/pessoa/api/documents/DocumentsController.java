package com.example.pessoa.api.documents;

import com.example.pessoa.api.dto.response.PersonResponseDTO;
import com.example.pessoa.api.service.impl.PersonServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/documents")
public class DocumentsController {

    private final PdfService pdfService;
    private final PersonServiceImpl personService;
//    private final CsvService csvService;

    @GetMapping("/pdf/export")
    public void exportToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:ss:mm");
        String currentDateTime = dateFormatter.format(new Date());
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        List<PersonResponseDTO> listUsers = personService.findAll();

        pdfService.export(response, listUsers);
    }

    @GetMapping("/csv/export")
    public void exportToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:ss:mm");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Context-Disposition";
        String headerValue = "atachment; filename=csv_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<PersonResponseDTO> listaDePessoas = personService.findAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Id", "Name", "Age", "E-mail", "Id Address"};
        String[] nameMapping = {"id", "name", "age", "email", "idAddress"};

        csvWriter.writeHeader(csvHeader);

        for (PersonResponseDTO person : listaDePessoas) {
            csvWriter.write(person, nameMapping);
        }

        csvWriter.close();
    }

}
