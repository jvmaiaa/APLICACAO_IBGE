package com.example.pessoa.api.documents;

import com.example.pessoa.api.service.impl.PersonServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class CsvService {

    private final PersonServiceImpl personService;
}
