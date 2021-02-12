package com.swa.exchangerate.SecondListActivity;

import android.net.Uri;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ParsingStreet {
    Document doc;
    String exeption;

    public String parseStreet(String bankName){
        try {
            Log.e("mylog", " parsing bnk na " + bankName );
            Document doc= Jsoup.connect("https://yandex.ru/maps/213/moscow/?text="+bankName).get();
            Elements elements=doc.getElementsByAttributeValue("class","business-contacts-view__address");
            Log.e("mylog", " parsing bnk na " + elements.text());
            return elements.text();
        }catch (Exception e){
            Log.e("mylog", "exeption parsing " + e );
            exeption=e.toString();
            e.printStackTrace();
            return exeption;
        }



    }
}
