package com.team1159ers.coffee_coder_db.service.report;

import com.team1159ers.coffee_coder_db.model.report.Report;

import java.util.List;

public interface ReportService {
    Report saveReport(Report report);
    List<Report> getAllReports();
}
