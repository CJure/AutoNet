/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autonet;

import java.io.IOException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author jur_1
 */
public class RunnableCrawlerControl implements Runnable
{
    CrawlerListener listener;
    URL[] searchURLs;

    public RunnableCrawlerControl(CrawlerListener context, URL[] searchURL) 
    {
        System.out.println("Cralwer start");
        this.listener = context;
        this.searchURLs = searchURL;
    }
    
    
    @Override
    public void run() 
    {
        System.out.println("crawler run");
        int numOfCars = 30;
        Car[][] cars = new Car[5][numOfCars];
        URL url;
        for(int i = 0; i < 5; i++)
        {
            try
            {
                url = searchURLs[i];
                if(url != null)
                {
                    System.out.println("i: " + i  +", url: " + url);
                    if(url.getHost() != null)
                    {
                        System.out.println("URL OK!!!!!!!!!!!!!!");
                        cars[i] = getCarsForURL(url, numOfCars);
//                        outputCars(cars[i]);
                    }
                }
            }
            catch(Exception ex)
            {
                System.out.println("exception on crawler run: " + ex.toString());
                ex.printStackTrace();
            }
        }
        listener.newCars(cars);
    }

    private Car[] getCarsForURL(URL url, int size) 
    {
        Car[] cars = new Car[size];
        try 
        {
//            Document doc = Jsoup.connect("https://www.avto.net/Ads/results.asp?znamka=Ford&model=&modelID=&tip=katerikoli%20tip&znamka2=&model2=&tip2=katerikoli%20tip&znamka3=&model3=&tip3=katerikoli%20tip&cenaMin=1500&cenaMax=27500&letnikMin=0&letnikMax=2090&bencin=0&starost2=999&oblika=0&ccmMin=0&ccmMax=99999&mocMin=&mocMax=&kmMin=0&kmMax=9999999&kwMin=0&kwMax=999&motortakt=&motorvalji=&lokacija=0&sirina=&dolzina=&dolzinaMIN=&dolzinaMAX=&nosilnostMIN=&nosilnostMAX=&lezisc=&presek=&premer=&col=&vijakov=&EToznaka=&vozilo=&airbag=&barva=&barvaint=&EQ1=1000000000&EQ2=1000000000&EQ3=1000000000&EQ4=100000000&EQ5=1000000000&EQ6=1000000000&EQ7=1110100120&EQ8=1010000001&EQ9=100000000&KAT=1010000000&PIA=&PIAzero=&PSLO=&akcija=&paketgarancije=&broker=&prikazkategorije=&kategorija=&zaloga=&arhiv=&presort=&tipsort=&stran=").get();
            Document doc = Jsoup.connect(url.toString()).get();
            System.out.println(doc.title());
            Elements adverts = doc.getElementsByClass("row bg-white GO-Shadow-B position-relative GO-Results-Row");
            Element advert;
            String advertData;
            for(int i = 0; i < adverts.size(); i++)
            {
                advert = adverts.get(i);
//                System.out.println("ad: " + element.toString());
                advertData = advert.selectFirst("a.stretched-link").attr("href");
                cars[i] = new Car(advertData);
                System.out.println("advertLink: " + advertData);
            }
            
//            Elements adsData = doc.select("div.ResultsAd");
//            System.out.println("resultsAd: " + adsData.toString());
//            Elements links = doc.select("a.Adlink");
//            for (int i = 0; i < adsData.size(); i++) 
//            {
//                Element ad =adsData.get(i);
//                Element link = links.get(i);
//                System.out.println("title: " + ad.text() + "\n" + link);
//                if(ad.toString().length() > 0 && link.toString().length() > 0) cars[i] = new Car(ad.toString(), link.toString());
////                System.out.println("title: " + ad.text() + "\n" + link);
//                if(i == size - 1)break;
//            }
            outputCars(cars);
        }
        catch (IOException ex) 
        {
        }
        return cars;
    }

    private void outputCars(Car[] cars) 
    {
        if(cars != null)
        {
            for(Car car : cars)
            {
                if(car != null) System.out.println("Car: " + car.toString() + "\n");
            }
        }
    }    
}
