/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broker_service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.xml.bind.JAXBContext;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author JCassio
 */
public class Broker_service {
    

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, DatatypeConfigurationException {
        
        LocalDate date = LocalDate.now();
        GregorianCalendar gcal = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
        XMLGregorianCalendar xmlcalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        
        
//        shareInformation.Broker context = new shareInformation.Broker();
//        
//        context.setCompanyName("Joselson Inc.");
//        context.setCompanySymbol("Joselson.png");
//        context.setAvailableShares(200);
//        context.setCurrency("GBP");
//        context.setValue(400);
//        context.setLastUpdate(xmlcalendar);
//        
//        try {            
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(context.getClass().getPackage().getName());
//            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
//            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            marshaller.marshal(context, System.out);
//        } catch (javax.xml.bind.JAXBException ex) {
//            // XXXTODO Handle exception
//            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
//        }
        
    }
    
}
