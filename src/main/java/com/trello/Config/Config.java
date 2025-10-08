package com.trello.Config;


public class Config {

    // Trello API yapılandırma bilgileri
    public static final String BASE_URL = "https://api.trello.com/1";

    // API Key ve Token buraya eklenecek
    // https://trello.com/app-key adresinden alınabilir
    public static final String API_KEY = "e4e5a4da347abde02a3526e090210c00";
    public static final String API_TOKEN = "ATTA43a34e4500b6f73c0225ab2a6caf8d842c8a86d34e8149ba6fb8532e7a2d4f2c72C14955";

    // API Endpoints
    public static final String BOARDS_ENDPOINT = "/boards";
    public static final String CARDS_ENDPOINT = "/cards";
    public static final String LISTS_ENDPOINT = "/lists";

    private Config() {
        // Utility class - instantiation engellendi
    }

    /**
     * API Key ve Token ile query parametreleri oluşturur
     * @return Query string
     */
    public static String getAuthParams() {
        return "key=" + API_KEY + "&token=" + API_TOKEN;
    }
}