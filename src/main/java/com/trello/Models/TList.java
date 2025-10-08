package com.trello.Models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Trello List model sınıfı
 * Board içerisindeki liste yapısını temsil eder
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TList {

    private String id;
    private String name;
    private boolean closed;
    private String idBoard;

    /**
     * Liste bilgilerini yazdırır
     */
    public void printListInfo() {
        System.out.println("List ID: " + id);
        System.out.println("List Name: " + name);
    }
}