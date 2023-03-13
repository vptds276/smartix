/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primeidea.camhunter.core;

import com.primeidea.camhunter.gui.MainPanel;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pro
 */
public class InsertFileDataToJTable extends AbstractTableModel {

    Vector data;
    Vector columns;
    private String pathToFile;
    private static final String[] columnNames = {
        "Address",
        "TypeCam",
        "Log",
        "Pass",
        "Free",
        "Rec",
        "Private"
    };

    public InsertFileDataToJTable(String pathToFile) {
        String line;
        data = new Vector();
        columns = new Vector();
        this.pathToFile = pathToFile;
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < columnNames.length; i++) {

            //columns.addElement(i);
        }
        columns.addElement(1);
        columns.addElement(2);
        for (int i = 0; i < lines.size(); i++) {
            data.addElement(lines.get(i) + " ");
        }
//        try {
//            FileInputStream fis = new FileInputStream(pathToFile);
//            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
//            StringTokenizer st1 = new StringTokenizer(br.readLine(), ";");
////            while (st1.hasMoreTokens()) {
////                columns.addElement(st1.nextToken());
////            }
//            for (int i = 0; i < columnNames.length; i++) {
//
//                columns.addElement(i);
//            }
//            while ((line = br.readLine()) != null) {
//                StringTokenizer st2 = new StringTokenizer(line, ";");
//                while (st2.hasMoreTokens()) {
//                    data.addElement(st2.nextToken());
//                }
//            }
//            br.close();
//            fis.close();
//        } catch (Exception e) {
//        }
  
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return data.get(rowIndex);
// 
//              
//                case 1:
//                case 2:
//                case 3:
//                case 4:
//                case 5:
//                case 6:                 
//                    return data.get(rowIndex);
 
                default:
                    return null;
            }
        }

}
