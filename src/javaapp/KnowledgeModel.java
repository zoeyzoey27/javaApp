/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapp;

import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.RemovePercentage;

/**
 *
 * @author Admin
 */
public class KnowledgeModel {
    DataSource source;
    Instances dataset;
    String[] model_options;
    String[] data_options;
    Instances trainset;
    Instances testset;
    
    public KnowledgeModel() {
    }
    
    public KnowledgeModel(String filename, String m_opts, String d_opts) throws Exception {
        if (!filename.isEmpty()){
            this.source = new ConverterUtils.DataSource(filename);
            this.dataset = source.getDataSet();
        }
        if (m_opts != null){
            this.model_options = weka.core.Utils.splitOptions(m_opts);
        }
        if (d_opts != null){
            this.data_options = weka.core.Utils.splitOptions(d_opts);
        }
    }
    
    //chia train test
    public Instances divideTrainTest(Instances originalSet,
            double percent, boolean isTest) throws Exception{
        RemovePercentage rp = new RemovePercentage();
        rp.setPercentage(percent);
        rp.setInvertSelection(isTest);
        rp.setInputFormat(originalSet);
        return Filter.useFilter(originalSet, rp);
    }
    
    public void setTrainSet(String filename) throws Exception{
        ConverterUtils.DataSource trainSource = new ConverterUtils.DataSource(filename);
        this.trainset = trainSource.getDataSet();
    }
    public void setTestSet(String filename) throws Exception{
        ConverterUtils.DataSource testSource = new ConverterUtils.DataSource(filename);
        this.testset = testSource.getDataSet();
    }
    
    @Override
    public String toString() {
        return dataset.toSummaryString();
    }
}
