package com.example.shop.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class CurrencyService {
    public double getEurPrice(double bynPrice){
        try {
            URL url = new URL("https://www.nbrb.by/api/exrates/rates/EUR?parammode=2");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            double course = jsonObject.getDouble("Cur_OfficialRate");
            return Math.ceil(bynPrice/course*Math.pow(10, 2))/Math.pow(10, 2);
        }catch (Exception e){
            return 9999;
        }
    }
    public double getUsdPrice(double bynPrice){
        try {
            URL url = new URL("https://www.nbrb.by/api/exrates/rates/USD?parammode=2");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            double course = jsonObject.getDouble("Cur_OfficialRate");
            return Math.ceil(bynPrice/course*Math.pow(10, 2))/Math.pow(10, 2);
        }catch (Exception e){
            return 9999;
        }
    }
}
