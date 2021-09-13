/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autonet;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author jur_1
 */
public class AutoNet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try {
//            Document doc = Jsoup.connect("https://www.avto.net/Ads/results.asp?znamka=Ford&model=&modelID=&tip=katerikoli%20tip&znamka2=&model2=&tip2=katerikoli%20tip&znamka3=&model3=&tip3=katerikoli%20tip&cenaMin=1500&cenaMax=27500&letnikMin=0&letnikMax=2090&bencin=0&starost2=999&oblika=0&ccmMin=0&ccmMax=99999&mocMin=&mocMax=&kmMin=0&kmMax=9999999&kwMin=0&kwMax=999&motortakt=&motorvalji=&lokacija=0&sirina=&dolzina=&dolzinaMIN=&dolzinaMAX=&nosilnostMIN=&nosilnostMAX=&lezisc=&presek=&premer=&col=&vijakov=&EToznaka=&vozilo=&airbag=&barva=&barvaint=&EQ1=1000000000&EQ2=1000000000&EQ3=1000000000&EQ4=100000000&EQ5=1000000000&EQ6=1000000000&EQ7=1110100120&EQ8=1010000001&EQ9=100000000&KAT=1010000000&PIA=&PIAzero=&PSLO=&akcija=&paketgarancije=&broker=&prikazkategorije=&kategorija=&zaloga=&arhiv=&presort=&tipsort=&stran=").get();
//            Document doc = Jsoup.connect("https://www.off---white.com/en/NL/search?utf8=%E2%9C%93&q=nike").userAgent("Mozilla").get();
//            System.out.println(doc.title());
            Map<String, String> cookies = Jsoup.connect("https://www.off---white.com/en/NL/search?utf8=%E2%9C%93&q=nike").execute().cookies();
            Document doc = Jsoup.connect("https://www.off---white.com/en/NL/search?utf8=%E2%9C%93&q=nike").cookies(cookies).get();
//            System.out.println(doc.html());
//            System.out.println("test: " + doc.html());
            Document doc2 = Jsoup.parse(doc.html());
            System.out.println("doc2: " + doc2.html());
            Elements adsData = doc.select("div.ResultsAd");
            Elements links = doc.select("a.Adlink");
//            for(Element adLink : links)
//            {
//                System.out.println("a");
//            }
            
            for (int i = 0; i < adsData.size(); i++) 
            {
                Element ad =adsData.get(i);
                Element link = links.get(i);
                System.out.println("title: " + ad.text() + "\n" + link);
            }       } catch (IOException ex) {
            Logger.getLogger(AutoNet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
