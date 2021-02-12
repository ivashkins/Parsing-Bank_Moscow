package com.swa.exchangerate.SecondListActivity;

import android.util.Log;

import com.swa.exchangerate.SecondListActivity.BankRateDay;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ParsingBank {

    Document doc;
    String exeption;

    public  String parseBank(String site, ArrayList<BankRateDay> arrayList){
        try {
            doc = Jsoup.connect(site).get();
            Elements elements = doc.getElementsByAttributeValue("class", "quote__office__one js-one-office");
            for (Element element: elements){
                Element element1 = element.child(1);
                String bankData = element1.text();
                Element element2 = element.child(3);
                String buy1 = element2.text();
                buy1=buy1.replace("%","");
                Double buy=Double.parseDouble(buy1);
                Element element3 = element.child(5);
                String sell1 = element3.text();
                sell1 = sell1.replace("от", "");
                sell1=sell1.replace("%","");
                Double sell=Double.parseDouble(sell1);
                Element element4 = element.child(7);
                String time = element4.text();
                Element element5 = element.child(8);
                String reserve = element5.text();
                Element element6 = element.child(9);
                String metro = element6.text();
                Element element7 = element.child(10);
                String telefon = element7.text();
                arrayList.add(new BankRateDay(buy,sell,bankData,metro,reserve,time,telefon));

            }
        } catch (Exception e) {
            exeption=e.toString();
            Log.e("mylog", "exeption " + e);
        }
        return exeption;
    }


}
