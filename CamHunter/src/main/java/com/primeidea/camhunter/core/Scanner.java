package com.primeidea.camhunter.core;

import com.primeidea.camhunter.gui.CurrentJob;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Scanner implements Runnable {
    
    private static String startIp;
    private static String endIp;
    private int[] ipFrom = new int[4];
    private int[] ipTo = new int[4];
    private Integer n1, n2, n3, n4;
    private ScanParam scanParam=new ScanParam();
    
    
    public void setScanParam(ScanParam scanParam) {
        this.scanParam = scanParam;
    }
    ExecutorService es = Executors.newFixedThreadPool(1020);
    //ExecutorService es = Executors.newCachedThreadPool();
    Future<?> sub = null;
    
    @Override
    public void run() {
        StartScan();
    }

    public void setIpFrom(int[] a) {
        ipFrom = Arrays.copyOf(a, a.length);        
    }
    
    public void setIpTo(int[] b) {
        ipTo = Arrays.copyOf(b, b.length);
    }
    
    
    
    private void StartScan() {
        System.out.println("Program started is: " + getCurrentTime());

        // Для 1020 потоков
        for (n1 = ipFrom[0]; n1 <= ipTo[0]; n1++) {
            for (n2 = ipFrom[1]; n2 <= ipTo[1]; n2++) {
                for (n3 = ipFrom[2]; n3 <= ipTo[2]; n3++) {
                    Integer xx = n3 + 3;
                    if (n3 % 4 == 0) {
                        startIp = n1.toString() + "." + n2.toString() + "." + n3.toString() + ".1";
                        endIp = n1.toString() + "." + n2.toString() + "." + xx.toString() + ".255";
                        //System.out.println( "Start: "+startIp+"-"+endIp);
                        Device device = null;
                        for (long n4 = ipToLong(startIp); n4 <= ipToLong(endIp); n4++) {
                            device = new Device();
                            device.setIp(longToIp(n4));
                            device.setPort("60001");
                            device.setConn_time_out(scanParam.getConn_time_out());
                            device.setRead_time_out(scanParam.getRead_time_out());
                            sub = es.submit(device);
                        }
                        
                        try {
                            sub.get();                            
                            //System.out.println( es.toString()+" ");
                            List res = device.getDev();
                            for (Object re : res) {
                                System.out.println(re.toString());
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    
                }
                //System.out.println(getCurrentTime() + " " + startIp + "-" + endIp);
            }
        }
        
        System.out.println("Program finish is: " + getCurrentTime());
        es.shutdown();
        
        
    }
    
    private static String longToIp(long ip) {
        StringBuilder sb = new StringBuilder(15);
        for (int i = 0; i < 4; i++) {
            sb.insert(0, Long.toString(ip & 0xff));
            if (i < 3) {
                sb.insert(0, '.');
            }
            ip = ip >> 8;
        }
        return sb.toString();
    }
    
    private static long ipToLong(String ipAddress) {
        String[] ipAddressInArray = ipAddress.split("\\.");
        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {
            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);
        }
        return result;
    }
    
    private static String getCurrentTime() {
        Date curDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy' 'kk.mm.ss.S");
        String currentDate = dateFormat.format(curDate);
        return currentDate;
    }
}
