package com.jerry.common;

public class XmlTag{

    public static final String MENU  = "menu";

    public static final String FOOD  = "food";

    public static final String NAME  = "name";

    public static final String PRICE = "price";

    public static final String DESC  = "description";

    public static String toString(String ...content){
        StringBuilder builder = new StringBuilder();
        for(String s:content){
            builder.append(s).append("\n");
        }
        return builder.toString();
    }
}