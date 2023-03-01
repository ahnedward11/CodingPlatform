
package com.team1159ers.coffee_coder_db.service.report;

import com.team1159ers.coffee_coder_db.model.report.Report;
import com.team1159ers.coffee_coder_db.repository.report.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImplementation implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImplementation(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
}
