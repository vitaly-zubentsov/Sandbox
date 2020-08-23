/**
 * MyServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ru.zubentsov.service;

public interface MyServiceService extends javax.xml.rpc.Service {
    public java.lang.String getMyServiceAddress();

    public ru.zubentsov.service.MyService getMyService() throws javax.xml.rpc.ServiceException;

    public ru.zubentsov.service.MyService getMyService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
