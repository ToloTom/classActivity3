package com.example.classactivity3;

public class Weather {

    private String date;
    private String description;
    private String feelsLike;

    public Weather(String day, String info, String feels){
        this.date = day;
        this.description = info;
        this.feelsLike = feels;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
