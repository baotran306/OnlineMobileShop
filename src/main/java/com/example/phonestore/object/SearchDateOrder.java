package com.example.phonestore.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class SearchDateOrder {
    @JsonProperty("from_date")
    private String fromDate;
    @JsonProperty("to_date")
    private String toDate;

    public SearchDateOrder() {
    }

    public SearchDateOrder(String fromDate, String toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }


    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "SearchDateOrder{" +
                "fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
