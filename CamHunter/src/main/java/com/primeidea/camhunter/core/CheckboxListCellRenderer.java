/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primeidea.camhunter.core;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author pro
 * Класс для отображения checkBox в List
 */
public class CheckboxListCellRenderer extends JCheckBox implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        return this;
    }

//    @Override
//    public Component getListCellRendererComponent(JList list, 
//                                                  Object value, 
//                                                  int index, 
//                                                  boolean isSelected, 
//                                                  boolean cellHasFocus) {
//
//        setComponentOrientation(list.getComponentOrientation());
//        setFont(list.getFont());
//        setBackground(list.getBackground());
//        setForeground(list.getForeground());
//        setSelected(isSelected);
//        setEnabled(list.isEnabled());
//
//        setText(value == null ? "" : value.toString());  
//
//        return this;
//    }
}
