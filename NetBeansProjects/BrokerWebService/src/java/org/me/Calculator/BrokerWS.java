/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.Calculator;

import AllShares.*;
import docwebservices.CurrencyConversionWSService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.WebServiceRef;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



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
        
        
        
        else if(query.contains("buy") && query.contains("share")){
            try { 
                UserResult = " Purchase of shares at " + splitQuery[3] + " was a " + updateXMLfile(splitQuery[3].toLowerCase());
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
    

    //Updates XML file after user makes a purchase
    private String updateXMLfile( String companyWithShare) throws ParserConfigurationException, IOException, SAXException, TransformerConfigurationException, TransformerException{
        
        String isDone = "not successful";
        boolean wasFound = false;
        int newNumOfShares = 0;
        int rotate = 0;
        int loopCount = 0;
        
        String filepath = "/Users/JCassio/NetBeansProjects/Broker_WebService/shares_today_backup.txt";
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(filepath);
        
        //Get the root element
        Node broker_collection = doc.getFirstChild();
        
        while(loopCount < 4){

        //Get the staff element by tag name directly
        Node broker = doc.getElementsByTagName("broker_collection").item(rotate);
        
        //Loop the staff child node
        NodeList list = broker.getChildNodes();
        
        
        for (int i = 0; i < list.getLength(); i++){
            
            Node node = list.item(i);
            System.out.println("Node name is " + node.getNodeName() + " NOT SUCCESSFULL");
            System.out.println("Node value is " + node.getTextContent() + " NOT SUCCESSFULL");
            
            
            //Find the company name where user wishes to buy shares
            if(node.getTextContent().equals(companyWithShare)){
                
                wasFound = true;
                
                System.out.println("I came inside company name");
                System.out.println("Node get name = " + node.getNodeName());
                System.out.println("Value of first child = " + node.getFirstChild());
                System.out.println("User input was " + companyWithShare);
                
                isDone = "successfull";
            }
            
            else if (wasFound == true && "availableShares".equals(node.getNodeName()))
            {
                newNumOfShares = Integer.parseInt(node.getTextContent()) - 1;
                node.setTextContent(Integer.toString(newNumOfShares));
            }
        }
            if (rotate < 4){
                    rotate +=1;
                }
            else{
                break;}
                
        loopCount++;
    }

        
        //write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filepath));
        transformer.transform(source, result);
        
        return isDone;
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
