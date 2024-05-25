package com.example.pessoa.api.documents;

import com.example.pessoa.api.dto.response.PersonResponseDTO;
import com.example.pessoa.api.service.PersonService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class CsvService {

    private final PersonService personService;

    public void export(HttpServletResponse response) throws IOException {

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), new CsvPreference.Builder('"', ';', "\n").build());
        String[] csvHeader = {"Id", "Name", "Age", "E-mail", "Id Address"};
        String[] nameMapping = {"id", "name", "age", "email", "idAddress"};

        csvWriter.writeHeader(csvHeader);

        List<PersonResponseDTO> listaDePessoas = personService.findAll();

        for (PersonResponseDTO person : listaDePessoas) {
            csvWriter.write(person, nameMapping);
        }

        csvWriter.close();
    }
}
