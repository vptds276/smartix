/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primeidea.camhunter.core;

import com.primeidea.camhunter.gui.CurrentJob;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author pro
 */
public class JobController {
    
    private static HashMap<Integer, Serializable> jobList = new HashMap<>();
    private CurrentJob currentJob;
    
    
    public HashMap<Integer, Serializable> getJobList() {
        return jobList;
    }

    public void setJobList(int index, Serializable scanParam) {
        JobController.jobList.put(index,scanParam);
    }

   
    
    
    
}
