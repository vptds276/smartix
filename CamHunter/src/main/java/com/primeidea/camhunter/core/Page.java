/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primeidea.camhunter.core;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yurich
 */
public class Page {

    private final String InHTML;
    private final String OutHTML;

   

    private List<String> ipList;
    private FileWriter fw;
    private String height;
    private String width;

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public Page(String InHTML, String OutHTML) {
        this.OutHTML = OutHTML;
        this.InHTML = InHTML;
        ipList = new ArrayList<>();
        FileInputStream fstream = null;

        try {
            fstream = new FileInputStream(InHTML);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                ipList.add(strLine);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Page.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Page.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Page(String OutHTML, List<String> ipList) {
        this.InHTML = "";
        this.OutHTML = OutHTML;
        this.ipList = ipList;
        
    }

    public void createPage() {
        generateHTML(ipList);
    }

    /**
     *
     * @param ipList
     */
    public void generateHTML(List<String> ipList) {

        try {
            // TODO add your handling code here:
            fw = new FileWriter(OutHTML, true);
            fw.write("<script type=\"text/javascript\">"+ System.lineSeparator());
            fw.write("function getCheckedCheckBoxes() {"+ System.lineSeparator());
            fw.write("var selectedCheckBoxes = document.querySelectorAll('input.checkbox');"+ System.lineSeparator());
            fw.write("var checkedValues = Array.from(selectedCheckBoxes).map(cb => cb.value+\"_\"+cb.checked);"+ System.lineSeparator());
            fw.write("//console.log(checkedValues);"+ System.lineSeparator());
            fw.write("return checkedValues;}"+ System.lineSeparator());
           
            
            fw.write("function save(argument) {" + System.lineSeparator());
            fw.write("var html =  getCheckedCheckBoxes();"+ System.lineSeparator());
            fw.write("var blob = new Blob([html], {type: \"text/html;charset=utf-8\"});"+ System.lineSeparator());
            fw.write("window.saveAs(blob, 'page.txt');}"+ System.lineSeparator());
            fw.write("</script>"+ System.lineSeparator());
            fw.write("<html>");
            fw.write("<head>"+ System.lineSeparator());
            fw.write("<script type=\"text/javascript\" src=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/14082/FileSaver.js\"></script>"+ System.lineSeparator());
            fw.write("</head>");
            fw.write("<body>"+ System.lineSeparator());
            Integer ii=1;
            for (String line : ipList) {
                
                String param1 = "<img height=\"" + height + "\" width=\"" + width + "\" src=\"http://";
                String param2 = " \"onerror=\" this.style.display='none'\" onclick=\"window.open('http://";
                String param3 = "', '_blank');\" />";
                String addr = null;
                //находим индекс первого вхождения символа ":" в подстроке
                int pos = line.indexOf(":");
                int port = Integer.parseInt(line.substring(pos+1,line.length()));
                
                switch (port) {
                    case 80:
                    case 81:
                    case 1024:
                            //fw.write(ii.toString()+" <input type=\"checkbox\" name=\""+line+"\" value=\""+line+"\" checked>");
                            addr = param1 + line + ScanParam.Hipcam + param2 + line + ScanParam.Hipcam + param3;
                            fw.write(addr.trim() + System.lineSeparator());
                        break;
                    case 60001:
                        //isWork
                        fw.write(ii.toString()+" <input class=\"checkbox\" type=\"checkbox\" name=\"brand[]\" value=\""+line+"\" id=\"checkbox"+ii.toString()+"\" checked>" + line +" "); //+ "<br>" + System.lineSeparator());
                        //isPers
                        fw.write(ii.toString()+" <input class=\"checkbox\" type=\"checkbox\" name=\"brand[]\" value=\""+line+"\" id=\"checkbox"+ii.toString()+"\" >" + line +" "); // "<br>" + System.lineSeparator());
                        //isView
                        fw.write(ii.toString()+" <input class=\"checkbox\" type=\"checkbox\" name=\"brand[]\" value=\""+line+"\" id=\"checkbox"+ii.toString()+"\" >" + line +" "); // "<br>" + System.lineSeparator());
                        //isProtect
                        fw.write(ii.toString()+" <input class=\"checkbox\" type=\"checkbox\" name=\"brand[]\" value=\""+line+"\" id=\"checkbox"+ii.toString()+"\" >" + line + "<br>" + System.lineSeparator());
                        
                        fw.write("<nobr>" + System.lineSeparator());
                        for (int channel = 0; channel < 8; channel++) {    
                            String addrPart1 = "/cgi-bin/snapshot.cgi?chn=";
                            String addrPart2 = "&u=admin&p=&q=0&d=1\\";
                            addr = param1 + line + addrPart1 + channel + addrPart2 + param2 + line + param3;
                            fw.write(addr.trim() + System.lineSeparator());
                        }
                        fw.write("<br><hr>" + System.lineSeparator());
                        break;
                }
                
            ii++;    
            }
            fw.write("<button onclick=\"save()\"> Сохранить </button>"); 
            fw.write("</body>");
            fw.write("</html>");
            fw.close();
        } catch (IOException ex) {
            
        }

    }

}
