/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.codavel.challenge;

import java.util.LinkedList; 
import java.text.SimpleDateFormat;  
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author JCassio
 */
public class CacheManager {
    
    private final LinkedList<String> connectionList = new LinkedList<String>();
    private final LinkedList<LocalDateTime> connectionDate = new LinkedList<LocalDateTime>();
    private int connectionLimit = 0;
    private int activeConnections = 0;
    
    
    public String Add(String connection) {
                
            if (activeConnections < connectionLimit) {
                this.activeConnections += 1;
            }
            
            else if (activeConnections >= connectionLimit) {
                RemoveConnection();
            }
            
        String connectionListData = connection;
        LocalDateTime connectionListDate = setConnectionDate();
        
        connectionList.add(connectionListData); 
        connectionDate.add(connectionListDate);
        return "Ok";
    }
        

    public LinkedList<String> Print() {
        return connectionList;
    }

    public void setConnectionLimit(int limit) {
        this.connectionLimit = limit;
    }
    
    private void RemoveConnection() {
        int removeIndex = CompareDates();
        
        connectionList.remove(removeIndex);
        connectionDate.remove(removeIndex);
    }
    
    private LocalDateTime setConnectionDate() {
        LocalDateTime getCurrentDate = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss");
        
        return getCurrentDate;
    }
    
    private int CompareDates() {
        
        SimpleDateFormat sdformat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        LocalDateTime oldestDate = null;
        int indexCount = 0;
       
      for(int i=0; i<connectionDate.size(); i++)
      {
        if (oldestDate == null) {
            oldestDate = connectionDate.getFirst();
        }
          
         int compareDate = oldestDate.compareTo(connectionDate.get(i));
         
         if(compareDate > 0) 
        {
            oldestDate = connectionDate.get(i);
            indexCount = i;
        } 
      }
      return indexCount;
    }
}
