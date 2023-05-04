package com.group.constants;

public class EndPoints {
    /**
     * Uygulamaniz icinde kullanilan tum erisim noktalarinin listesi burada tuturlur, boylece
     * farkli ortamlar icin kullanilacak end pointler tek bir noktadan degistirilebilir.
     */


    public static final String VERSION = "/v1";
    public static final String DEV = "/dev";
    public static final String API = "/api";


    public static final String ADVANCEPAYMENT =API+VERSION+"/advancepayment";
    public static final String DAYOFF =API+VERSION+"/dayoff";
    public static final String EXPENDITURE =API+VERSION+"/expenditure";

    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String GETALL = "/getall";
    public static final String GETONE = "/getone";
    public static final String BYPERSONALID = "/{personalId}}";
    public static final String GETALLPAGE = "/getallpage";


}
