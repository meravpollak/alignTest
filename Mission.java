package com.example.aligntest;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("Mission")
public class Mission {

    @Id
    @Column("id")
    private String id;

    @Column("agent")
    private String agent;

    @Column("country")
    private String country;

    @Column("address")
    private String address;

    @Column("date")
    private String date;

    public Mission(String agent, String country, String address, String date) {
        this.agent = agent;
        this.country = country;
        this.address = address;
        this.date = date;
    }

    public Mission() {}

    public String getId() {return this.id;}

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "mission [agent=" + agent + ", country=" + country + ", address=" + address + ", date=" + date + "]";
    }
}

