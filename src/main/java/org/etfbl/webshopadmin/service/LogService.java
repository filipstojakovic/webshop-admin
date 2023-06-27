package org.etfbl.webshopadmin.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogService {

    public List<String> readLogs() {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("/webshop/logs/spring.log"));
            Collections.reverse(lines);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lines;
    }
}
