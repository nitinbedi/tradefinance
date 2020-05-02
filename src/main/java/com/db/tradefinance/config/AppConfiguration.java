package com.db.tradefinance.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("tradeparams")
public class AppConfiguration {
    private int recordfetchlimit;
    private String dateformat;
    private int recordsPerPage;
    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }




    public int getRecordfetchlimit() {
        return recordfetchlimit;
    }

    public void setRecordfetchlimit(int recordfetchlimit) {
        this.recordfetchlimit = recordfetchlimit;
    }

    public String getDateformat() {
        return dateformat;
    }

    public void setDateformat(String dateformat) {
        this.dateformat = dateformat;
    }


}
