/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broker_webservice;

import BrokerInformation.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.JAXB;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author JCassio
 */



public class Broker_WebService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, DatatypeConfigurationException {
        
        //Convert date to XML date format
        GregorianCalendar theDate = new GregorianCalendar();
        theDate.set(GregorianCalendar.YEAR, 2018);
        theDate.set(GregorianCalendar.MONTH, 11);
        theDate.set(GregorianCalendar.DATE, 22);
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(theDate);
        
        //call objects
        SharesToday shares = new SharesToday();
        SharePrice shareDetails = new SharePrice();
        
        List<Broker> shares_today =  shares.getBrokerCollection();
        
        
        Broker brokers;
        
        brokers = new Broker();
        brokers.setCompanyName("Joselson .INC");
        brokers.setCompanySymbol("thisSymbol");
        brokers.setAvailableShares(300);
        //------
        shareDetails.setCurrency("GBP");
        shareDetails.setValue(340);
        shareDetails.setLastUpdate(xmlDate);
        brokers.setSharePrice(shareDetails);
        
        shares_today.add(brokers);
        
        
//        //Convenient way to marshall objects
//        StringWriter sw = new StringWriter();
//        JAXB.marshal(brokers, sw);
//        String xmlString = sw.toString();

        try {            
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(shares.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(shares, System.out);
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
//        //Writing data to file
//        FileOutputStream outputStream = new FileOutputStream("shares_today.txt");
//        byte[] strToBytes = xmlString.getBytes();
//        outputStream.write(strToBytes);
//        outputStream.close();
//        
//        //Delete me afterwards
//        System.out.println(xmlString);
//        
//        //Convenient way to unMarshall
//        Broker customer = JAXB.unmarshal(new StringReader(xmlString), Broker.class);
//        
//        System.out.println(customer);
        
        
        
        
    }


    
}
