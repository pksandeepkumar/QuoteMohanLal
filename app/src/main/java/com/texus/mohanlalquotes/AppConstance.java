package com.texus.mohanlalquotes;

/**
 * Created by sandeep on 06/02/16.
 */
public interface AppConstance {

    public static String BASE_URL = "http://texusapps.com/AppXmls/MohanLalQuotes/";

    public static final String INDEX_FILE_NAME_FILM = "film_index.xml";
    public static final String INDEX_FILE_NAME_QUOTE_IMAGE_TEXT = "quote_images_index.xml";
    public static final String INDEX_FILE_NAME_QUOTE_TEXT = "quote_index.xml";

    public static String INDEX_URL_QUOTE = BASE_URL + INDEX_FILE_NAME_QUOTE_TEXT;
    public static String INDEX_URL_IMAGE = BASE_URL + INDEX_FILE_NAME_QUOTE_IMAGE_TEXT;
    public static String INDEX_URL_FILMS = BASE_URL + INDEX_FILE_NAME_FILM;

    /**
     * This delay is to wait to show ripple effect to user
     */
    public static final int TIME_TO_WAIT_IN_MS = 500;

}
