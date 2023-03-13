/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primeidea.camhunter.core;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author pro
 * Класс для задания параметров сканирования диапазона
 */
public class ScanParam implements Serializable{
    
    private static final long serialVersionUID = 1L;    
    
    public static final String Defeway = "/cgi-bin/gw.cgi?xml=%3Cjuan%20ver=%22%22%20squ=%22%22%20dir=%220%22%3E%3Crpermission%20usr=%22admin%22%20pwd=%22%22%3E%3Cconfig%20base=%22%22/%3E%3Cplayback%20base=%22%22/%3E%3C/rpermission%3E%3C/juan%3E";
    public static final String Hipcam = "/tmpfs/auto.jpg?usr=user&pwd=user";
    public static final String PASS_DEFEWAY_LOGIN       = "/cgi-bin/gw.cgi?xml=%3Cjuan%20ver=%22%22%20squ=%22%22%20dir=%220%22%3E%3Crpermission%20usr=%22admin%22%20pwd=%22";
    public static final String PASS_DEFEWAY_PASSWORD    = "%22%3E%3Cconfig%20base=%22%22/%3E%3Cplayback%20base=%22%22/%3E%3C/rpermission%3E%3C/juan%3E";
    
    //static String strRequest = "/cgi-bin/gw.cgi?xml=<juan ver="0" squ="abcdef" dir="0" enc="1"><devinfo camcnt="" sensorcnt="" httpport=""/></juan>";
    public static final String FILE_IN_NAME = "f:\\soft\\VNC\\1.txt";
    public static final String FILE_OUT_NAME = "f:\\soft\\VNC\\out.txt";
    public static final String FILE_OUT_NAME_PROT = "f:\\soft\\VNC\\out_prot.txt";
    
    public static final int MIN_IP_PER_THREAD = 128;
    public static final int MAX_THREADS = 1024; 
    
    public static  final int CONN_TIME_OUT =300;
    public static  final int READ_TIME_OUT = 1000;
            
    private static String saveDir;
    private String startIp;
    private String stopIp;
    private int oktet1st;
    private int typeScan;
    private int typeCam;
    private int conn_time_out;
    private int read_time_out;
    private int countThreads;
    
    public ScanParam(ScanParam scanParams) {
        startIp         = scanParams.startIp;
        stopIp          = scanParams.stopIp;
        typeScan        = scanParams.typeScan;
        typeCam         = scanParams.typeCam;
        conn_time_out   = scanParams.conn_time_out;
        read_time_out   = scanParams.read_time_out;
        countThreads    = scanParams.countThreads;
        
    }
    
    public ScanParam() {
    }
    
    /**
     * 
     * @param startIp
     * @param stopIp
     * @param typeScan
     * @param typeCam
     * @param conn_time_out
     * @param read_time_out
     * @param countThreads
     * 
     */
    public ScanParam(String startIp, String stopIp, int typeScan, int typeCam, int conn_time_out, int read_time_out, int countThreads) {
        this.startIp = startIp;
        this.stopIp = stopIp;
        this.typeScan = typeScan;
        this.typeCam = typeCam;
        this.conn_time_out = conn_time_out;
        this.read_time_out = read_time_out;
        this.countThreads = countThreads;
    }

    public String getStartIp() {
        return startIp;
    }

    public void setStartIp(String startIp) {
        this.startIp = startIp;
    }

    public String getStopIp() {
        return stopIp;
    }

    public void setStopIp(String stopIp) {
        this.stopIp = stopIp;
    }

    public int getTypeScan() {
        return typeScan;
    }

    public void setTypeScan(int typeScan) {
        this.typeScan = typeScan;
    }

    public int getTypeCam() {
        return typeCam;
    }

    public void setTypeCam(int typeCam) {
        this.typeCam = typeCam;
    }

    public int getConn_time_out() {
        return conn_time_out;
    }

    public void setConn_time_out(int conn_time_out) {
        this.conn_time_out = conn_time_out;
    }

    public int getRead_time_out() {
        return read_time_out;
    }

    public void setRead_time_out(int read_time_out) {
        this.read_time_out = read_time_out;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.startIp);
        hash = 17 * hash + Objects.hashCode(this.stopIp);
        hash = 17 * hash + this.typeScan;
        hash = 17 * hash + this.typeCam;
        hash = 17 * hash + this.conn_time_out;
        hash = 17 * hash + this.read_time_out;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ScanParam other = (ScanParam) obj;
        if (this.typeScan != other.typeScan) {
            return false;
        }
        if (this.typeCam != other.typeCam) {
            return false;
        }
        if (!Objects.equals(this.startIp, other.startIp)) {
            return false;
        }
        if (!Objects.equals(this.stopIp, other.stopIp)) {
            return false;
        }
        return true;
    }
    
    
    
}
