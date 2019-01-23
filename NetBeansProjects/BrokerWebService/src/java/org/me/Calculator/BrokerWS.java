/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.Calculator;

import AllShares.*;
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
        BrokerWS runningUnmarshal = new BrokerWS();
        List<Broker> shares_list = runningUnmarshal.UnmarshallToday();
        
        
        Broker shareFilter = new Broker();
        //SharePrice shareDetails = new SharePrice();
        
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
        
        else{
            GoldQuery = "Query not recognized!";
        }

        return GoldQuery;
    }
          

    
    @WebMethod(operationName = "userInput")
    public String userInput(@WebParam(name = "firstQuery") String firstCommand, @WebParam(name = "secondQuery") String secondCommand) throws FileNotFoundException {
        
        //Declaring object
        BrokerWS searcher = new BrokerWS();
        
        
        //Value returned to user
        String UserResult = "";
        
        
        //Obtaining Query from user
        if (firstCommand.equals("list") && secondCommand.equals("shares")){
                    UserResult = searcher.retrieveQueries(firstCommand,secondCommand);
        }
        
        else if (firstCommand.equals("show")){
                    UserResult = searcher.retrieveQueries(firstCommand,secondCommand);
        }
        
        else if (firstCommand.equals("currency")){
                    UserResult = searcher.retrieveQueries(firstCommand,secondCommand);
        }
        
        else if (firstCommand.equals("compare")){
                    UserResult = searcher.retrieveQueries(firstCommand,secondCommand);
        }
        
        else if (firstCommand.equals("codes") && secondCommand.equals("currency")){
                    UserResult = searcher.retrieveQueries(firstCommand,secondCommand);
        }
        
        else{
            UserResult = "Command not recognized!";
        }

        return UserResult;
    }
}
