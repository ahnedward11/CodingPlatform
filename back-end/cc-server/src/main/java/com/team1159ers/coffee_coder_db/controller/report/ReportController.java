
package com.team1159ers.coffee_coder_db.controller.report;

import com.team1159ers.coffee_coder_db.model.report.Report;
import com.team1159ers.coffee_coder_db.service.report.ReportService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/report", consumes = MediaType.ALL_VALUE)
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/add")
    public int add(@RequestBody Report Report) {
        try{
            reportService.saveReport(Report);

            return 0;
        // Duplicate report
        } catch (
                DataIntegrityViolationException e)  {
            return -1;
        }
    }

    @GetMapping("/getAll")
    public List<Report> list(){
        return reportService.getAllReports();
    }
}
