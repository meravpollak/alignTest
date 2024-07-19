package com.example.aligntest;

public class IsolatedCountries {
    private String country;
    private Integer count;

    public IsolatedCountries(String country, String count) {
        this.country = country;
        this.count = Integer.valueOf(count);
    }

    public IsolatedCountries() {}

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;

    }
    public Integer getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = Integer.valueOf(count);
    }

    public void setCount(Integer count) {
        this.count = count;
    }
    public String toString() {
        return "Country: " + country + ", with isolation degree: " + count;
    }

}
