/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autonet;

import org.jsoup.nodes.Element;

/**
 *
 * @author jur_1
 */
public class Car 
{
    public String text;
    public String link;

    public Car(String text, String link) 
    {
        this.text = text;
        this.link = link;
    }

    Car(String advertData) 
    {
        link = getLinkFromData(advertData);
        text = getTextFromData(advertData);
        System.out.println("text: " + text);
    }
    
    @Override
    public String toString()
    {
        return text + " ink: " + link;
    }

    private String getLinkFromData(String advertData) 
    {
        int startIndex = 3;
        int endIndex = advertData.indexOf('&');
        return  "https://www.avto.net/" + advertData.substring(startIndex, endIndex);
    }
    
    private String getTextFromData(String link) 
    {
        int startIndex = link.indexOf('&') + 9;
        int endIdex = link.length() - 3;
        return link.substring(startIndex, endIdex);
    }

    
}
