package com.primeidea.camhunter.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import com.primeidea.camhunter.config.Const;
import java.util.ArrayList;
import java.util.List;

public class Device implements Runnable {

    private String ip;
    private String port;
    private URLConnection conn;
    private int conn_time_out;
    private int read_time_out;
    private List<String> dev = new ArrayList<>();

    public List<String> getDev() {
        return dev;
    }
    public void clearDev() {
        this.dev.clear();
    }
    
    public void setConn_time_out(int conn_time_out) {
        this.conn_time_out = conn_time_out;
    }

    public void setRead_time_out(int read_time_out) {
        this.read_time_out = read_time_out;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    private String getURL() {
        return ip + ":" + port;
    }

    @Override
    public void run() {

        scan();

    }

    private void scan() {
        //System.out.println("Start"+Thread.currentThread().getId());
        //Зададим лимиты на подключение и ответ
        try {
            conn = new URL("http://" + getURL() + Const.Defeway).openConnection();
            //System.out.println(conn.toString());
            conn.setConnectTimeout(conn_time_out);
            conn.setReadTimeout(read_time_out);
            
            String result = "";
            String inputLine = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            while ((inputLine = in.readLine()) != null) {
                result = result + in.readLine();
            }

            in.close();

            if (result != null) {
                int i = result.lastIndexOf("rpermission errno=\"0\"");
                if (i > -1) {
                    
                    dev.add(getURL().trim()+" free");
                    System.out.println(getURL());
                } else {
                    dev.add(getURL().trim()+" prot");
                    
                }

            }
        } catch (IOException e) {

        }

    }

}
