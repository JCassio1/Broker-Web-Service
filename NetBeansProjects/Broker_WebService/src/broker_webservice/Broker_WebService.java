/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broker_webservice;

import BrokerInformation.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import javax.xml.bind.JAXB;

/**
 *
 * @author JCassio
 */



public class Broker_WebService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        String myCompanyName = "";
        
        SharesToday shares = new SharesToday();
        List<Broker> shares_today =  shares.getBrokerCollection();
        
        
        Broker brokers;
        
        brokers = new Broker();
        brokers.setCompanyName("Joselson .INC");
        brokers.setCompanySymbol("thisSymbol");
        brokers.setAvailableShares(300);
        shares_today.add(brokers);
        
        
        //Convenient way to marshall objects
        StringWriter sw = new StringWriter();
        JAXB.marshal(brokers, sw);
        String xmlString = sw.toString();
        
        //Writing data to file
        FileOutputStream outputStream = new FileOutputStream("shares_today.txt");
        byte[] strToBytes = xmlString.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
        
        //Delete me afterwards
        System.out.println(xmlString);
        
        //Convenient way to unMarshall
        Broker customer = JAXB.unmarshal(new StringReader(xmlString), Broker.class);
        
        System.out.println(customer);
        
        
        
        
    }


    
}
