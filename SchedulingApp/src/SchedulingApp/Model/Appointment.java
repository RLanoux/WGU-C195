/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulingApp.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author P00306944
 */
public class Appointment {
    private IntegerProperty appointmentId = new SimpleIntegerProperty();
    private IntegerProperty customerId = new SimpleIntegerProperty();
    private IntegerProperty userId = new SimpleIntegerProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty location = new SimpleStringProperty();
    private StringProperty contact = new SimpleStringProperty();
    private StringProperty type = new SimpleStringProperty();
    private StringProperty url = new SimpleStringProperty();
    private ZonedDateTime start;
    private ZonedDateTime end;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdateBy;
    private Customer customer = new Customer();
    
    public Appointment() {
    }

    public final int getAppointmentId() {
        return appointmentId.get();
    }

    public final void setAppointmentId(int appointmentId) {
        this.appointmentId.set(appointmentId);
    }
    
    public IntegerProperty appointmentIdProperty() {
        return appointmentId;
    }

    public final int getCustomerId() {
        return customerId.get();
    }

    public final void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }
    
    public IntegerProperty customerIdProperty() {
        return customerId;
    }

    public final int getUserId() {
        return userId.get();
    }

    public final void setUserId(int userId) {
        this.userId.set(userId);
    }
    
    public IntegerProperty userIdProperty() {
        return userId;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }
    
    public StringProperty titleProperty() {
        return title;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
    
    public StringProperty descriptionProperty() {
        return description;
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }
    
    public StringProperty locationProperty() {
        return location;
    }

    public String getContact() {
        return contact.get();
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }
    
    public StringProperty contactProperty() {
        return contact;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }
    
    public StringProperty typeProperty() {
        return type;
    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(String url) {
        this.url.set(url);
    }
    
    public StringProperty urlProperty() {
        return url;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
