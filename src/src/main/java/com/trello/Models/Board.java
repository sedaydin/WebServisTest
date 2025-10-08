package com.trello.Models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Trello Board model sınıfı
 * OOP prensiplerine uygun olarak encapsulation kullanılmıştır
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private String id;
    private String name;
    private String desc;
    private boolean closed;
    private String url;

    /**
     * Board'un silinip silinmediğini kontrol eder
     * @return Board silinmişse true
     */
    public boolean isDeleted() {
        return closed;
    }

    /**
     * Board bilgilerini yazdırır
     */
    public void printBoardInfo() {
        System.out.println("Board ID: " + id);
        System.out.println("Board Name: " + name);
        System.out.println("Board URL: " + url);
    }
}