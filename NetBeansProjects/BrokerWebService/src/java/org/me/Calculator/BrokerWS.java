/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.Calculator;

import AllShares.*;
import docwebservices.CurrencyConversionWSService;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;



/**
 *
 * @author JCassio
 */
@WebService(serviceName = "BrokerWS")
@Stateless()
public class BrokerWS {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/CurrencyConvertor/CurrencyConversionWSService.wsdl")
    private CurrencyConversionWSService service;

    /**
     * This is a sample web service operation
     */

    /**
     * Web service operation
     */
    
    
    //Method to unmarshall file
    public List<Broker> UnmarshallToday() throws FileNotFoundException{
        
        List<Broker> activeShares = null;
        
        SharesToday thePrices = new SharesToday();
        
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(thePrices.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            thePrices = (SharesToday) unmarshaller.unmarshal(new java.io.File("/Users/JCassio/NetBeansProjects/Broker_WebService/shares_today.txt")); //NOI18N
            activeShares = thePrices.getBrokerCollection(); 
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        return activeShares;
    }   
    
    
    //Method to return user queries
    public String retrieveQueries(String firstQuery, String SecondQuery) throws FileNotFoundException{
      
        //Calling unmarshall method and object declaration
        BrokerWS callingClass = new BrokerWS();
        List<Broker> shares_list = callingClass.UnmarshallToday();
        
        Broker shareFilter = new Broker();
        
        String GoldQuery ="";
        
        int numberOfResults=0;
        
        if (firstQuery.equals("list") && SecondQuery.equals("shares")){
            try {    
                Iterator itr = shares_list.iterator();
                
                while(itr.hasNext()) {
                    numberOfResults += 1;
                    shareFilter = (Broker) itr.next();
                    SharePrice sharePrice = shareFilter.getSharePrice();
                    
                    GoldQuery += " Result("+ numberOfResults +")-> [Company name: " + shareFilter.getCompanyName() + " || Company Symbol: " + shareFilter.getCompanySymbol() + " || Available Shares: " + shareFilter.getAvailableShares() + " || Currency: "+ sharePrice.getCurrency() + " || last-update: " + sharePrice.getLastUpdate() + " || value: " + sharePrice.getValue() + "] ";
                    System.out.println(GoldQuery);
                    
                        }
                    }    
                catch(Exception e){
                    e.printStackTrace();
                }
        }
        
        else if (firstQuery.equals("show")){
        
            try {    
                Iterator itr = shares_list.iterator();
                while(itr.hasNext()) {
                    shareFilter = (Broker) itr.next();
                    SharePrice sharePrice = shareFilter.getSharePrice();
                    
                    if(SecondQuery.equals(shareFilter.getCompanyName())){
                        GoldQuery = "Company name: " + shareFilter.getCompanyName() + " || Company Symbol: " + shareFilter.getCompanySymbol() + " || Available Shares: " + shareFilter.getAvailableShares() + " || Currency: "+ sharePrice.getCurrency() + " || last-update: " + sharePrice.getLastUpdate() + " || value: " + sharePrice.getValue();
                        System.out.println("Found: " + GoldQuery);
                        break;
                    }
                    
                    else{
                        System.out.println("Query not found! of " + shareFilter.getCompanyName());
                        
                        if(GoldQuery.equals("")){
                            GoldQuery = "Query not found!";
                            }
                        }
                    }
                }    
                catch(Exception e){
                    e.printStackTrace();
                }
        }
        
        else if (firstQuery.equals("currency")){
            try {    
                Iterator itr = shares_list.iterator();
                while(itr.hasNext()) {
                    shareFilter = (Broker) itr.next();
                    SharePrice sharePrice = shareFilter.getSharePrice();
                    
                    if(SecondQuery.equals(sharePrice.getCurrency())){
                        GoldQuery = "Company name: " + shareFilter.getCompanyName() + " || Company Symbol: " + shareFilter.getCompanySymbol() + " || Available Shares: " + shareFilter.getAvailableShares() + " || Currency: "+ sharePrice.getCurrency() + " || last-update: " + sharePrice.getLastUpdate() + " || value: " + sharePrice.getValue();
                        System.out.println("Found: " + GoldQuery);
                        break;
                    }
                    
                    else{
                        System.out.println("Query not found! of " + sharePrice.getCurrency());
                        
                        if(GoldQuery.equals("")){
                            GoldQuery = "Query not found!";
                            }
                        }
                    }
                }    
                catch(Exception e){
                    e.printStackTrace();
                }
        }
        
        else if (firstQuery.equals("compare")){
            
            try {    
                Iterator itr = shares_list.iterator();
                
                int finalValue = 0;
                int lowestValue = Integer.MAX_VALUE;
                

                
                while(itr.hasNext()) {
                    shareFilter = (Broker) itr.next();
                    SharePrice sharePrice = shareFilter.getSharePrice();
                    
                    if(sharePrice.getValue() > finalValue && SecondQuery.equals("highest")){
                        GoldQuery = "Company name: " + shareFilter.getCompanyName() + " || Company Symbol: " + shareFilter.getCompanySymbol() + " || Available Shares: " + shareFilter.getAvailableShares() + " || Currency: "+ sharePrice.getCurrency() + " || last-update: " + sharePrice.getLastUpdate() + " || value: " + sharePrice.getValue();
                        System.out.println("Found: " + GoldQuery);
                        finalValue = sharePrice.getValue();
                    }
                    
                    else if(sharePrice.getValue() <= lowestValue && SecondQuery.equals("lowest")){
                        GoldQuery = "Company name: " + shareFilter.getCompanyName() + " || Company Symbol: " + shareFilter.getCompanySymbol() + " || Available Shares: " + shareFilter.getAvailableShares() + " || Currency: "+ sharePrice.getCurrency() + " || last-update: " + sharePrice.getLastUpdate() + " || value: " + sharePrice.getValue();
                        System.out.println("Found: " + GoldQuery);
                        lowestValue = sharePrice.getValue();
                    }
                    
                    else{
                        System.out.println("No " + SecondQuery + " found!");
                        }
                    }
                }    
                catch(Exception e){
                    e.printStackTrace();
                }
        }
        
        else if (firstQuery.equals("convert")){
            //!!!! YOU CAN DELETE THIS ELSE !!!
        }
        
        else{
            GoldQuery = "Query not found or recognized!";
        }

        return GoldQuery;
    }
    
    
  

    
    //Web Method
    @WebMethod(operationName = "userInput")
    public String userInput(@WebParam(name = "firstQuery") String query) throws FileNotFoundException {
        
        //Declaring object (non-static context)
        BrokerWS searcher = new BrokerWS();

        
        query = query.toLowerCase();
        
        String[] splitQuery = query.split(" ");        
        
        //Value returned to user
        String UserResult = "";
        
        //Variables to return user selected currencies
        List<Broker> shares_list = searcher.UnmarshallToday();
        double exchangeRate = 0.0;
        double resultOfRate = 0.0;
        int numberOfResults = 0;
        Broker shareFilter = new Broker();
        
        
        //Obtaining Query from user
        if (query.contains("list") && query.contains("shares")){
                    UserResult = searcher.retrieveQueries("list","shares");
        }
        
        else if (query.contains("show")){
                    UserResult = searcher.retrieveQueries("show",splitQuery[1]);
        }
        
        
        else if (query.contains("currency")){
                    UserResult = searcher.retrieveQueries("currency",splitQuery[1].toUpperCase());
        }
        
        else if (query.contains("compare") && query.contains("highest")){
                    UserResult = searcher.retrieveQueries("compare","highest");
        }
        
        else if (query.contains("compare") && query.contains("lowest")){
                    UserResult = searcher.retrieveQueries("compare","lowest");                 
        }
        
        else if (query.contains("all") && query.contains("currencies")){
                    UserResult = String.join(",", getCurrencyCodes());
        }
        
        else if (query.contains("rate")){

            UserResult = "Exchange rate is " + String.valueOf(getConversionRate(splitQuery[1].toUpperCase(), splitQuery[2].toUpperCase()));
        }
        
        else if (query.contains("convert") && query.contains("shares")){            
            try {    
                Iterator itr = shares_list.iterator();
                
                while(itr.hasNext()) {
                    numberOfResults += 1;
                    shareFilter = (Broker) itr.next();
                    SharePrice sharePrice = shareFilter.getSharePrice();
                    
                    exchangeRate = getConversionRate(sharePrice.getCurrency(), splitQuery[3].toUpperCase());
                    
                    resultOfRate = sharePrice.getValue()/exchangeRate;
                    
                    UserResult += " Result("+ numberOfResults +")-> [Company name: " + shareFilter.getCompanyName() + " || Company Symbol: " + shareFilter.getCompanySymbol() + " || Available Shares: " + shareFilter.getAvailableShares() + " || main currency: "+ sharePrice.getCurrency() + " || currency converted to ("+ splitQuery[3].toUpperCase() + ") using exchange rate of [ "+ exchangeRate + " ] || last-update: " + sharePrice.getLastUpdate() + " || value: " + resultOfRate + "] ";
                    System.out.println(UserResult);
                        }
                    }    
            catch(Exception e){
                    e.printStackTrace();
                }
        }
        
        else{
                    UserResult = "Command not recognized!";
        }

        return UserResult;
    } 
    

    private double getConversionRate(java.lang.String arg0, java.lang.String arg1) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        docwebservices.CurrencyConversionWS port = service.getCurrencyConversionWSPort();
        return port.getConversionRate(arg0, arg1);
    }

    private java.util.List<java.lang.String> getCurrencyCodes() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        docwebservices.CurrencyConversionWS port = service.getCurrencyConversionWSPort();
        return port.getCurrencyCodes();
    }
}
