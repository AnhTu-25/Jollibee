/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entity;

import java.util.Date;

public class Bill {

    private Long id;
    private Long cardId;
    private String username;
    private Date checkin;
    private Date checkout;
    private int status; // 0 = Servicing, 1 = Completed, 2 = Canceled

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static enum Status {
        Servicing, Completed, Canceled
    }

    public Bill() {
    }

    public Bill(Long id, Long cardId, String username, Date checkin, Date checkout, int status) {
        this.id = id;
        this.cardId = cardId;
        this.username = username;
        this.checkin = checkin;
        this.checkout = checkout;
        this.status = status;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardId() {
        return cardId;
    }
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCheckin() {
        return checkin;
    }
    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }
    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
