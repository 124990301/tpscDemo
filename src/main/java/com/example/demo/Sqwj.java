package com.example.demo;

/**
 * Created by xuyf on 2018/11/7.
 */
public class Sqwj {
    private String yssq;
    private String jmsq;
    private String sbsq;

    public void updateSqwj(String type,String data){
        if(type.equals("1")){
            setYssq(data);
        }else if(type.equals("2")){
            setJmsq(data);
        }else if(type.equals("3")){
            setSbsq(data);
        }
    }

    public String getYssq() {
        return yssq;
    }

    public void setYssq(String yssq) {
        this.yssq = yssq;
    }

    public String getSbsq() {
        return sbsq;
    }

    public void setSbsq(String sbsq) {
        this.sbsq = sbsq;
    }

    public String getJmsq() {
        return jmsq;
    }

    public void setJmsq(String jmsq) {
        this.jmsq = jmsq;
    }
}
