package com.example.pessoa.api.documents;

import com.example.pessoa.api.dto.response.PersonResponseDTO;
import com.example.pessoa.api.service.impl.PersonServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void exportToCsv(HttpServletResponse response){
        response.setContentType("application/csv");
        String headerKey = "Context-Disposition";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:ss:mm");
        String currentDateTime = dateFormat.format(new Date());
        String headerValue = "atachment; filename=pdf_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        List<PersonResponseDTO> listUsers = personService.findAll();
        // TODO: Aplicar o método do CSV
    }

//    @GetMapping("/pdf/all")
//    public void exportToPdf(HttpServletResponse response) throws IOException {
//        this.pdfService.exportToPdf(response);
//    }


//    @GetMapping("/excel/all")
//    public void exportToExcel(HttpServletResponse response) throws IOException {
//        this.pdfService.exportToExcel(response);
//    }
}
