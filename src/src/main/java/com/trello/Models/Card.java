package com.trello.Models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Trello Card model sınıfı
 * OOP prensiplerine uygun olarak encapsulation kullanılmıştır
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    private String id;
    private String name;
    private String desc;
    private String idList;
    private String idBoard;
    private boolean closed;
    private String url;

    /**
     * Kart bilgilerini yazdırır
     */
    public void printCardInfo() {
        System.out.println("Card ID: " + id);
        System.out.println("Card Name: " + name);
        System.out.println("Card Description: " + desc);
    }

    /**
     * Kartın aktif olup olmadığını kontrol eder
     * @return Kart aktifse true
     */
    public boolean isActive() {
        return !closed;
    }
}
