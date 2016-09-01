package com.example.montoya.focalpoint;

import java.util.ArrayList;

/**
 * Created by Montoya on 5/8/2016.
 */
public class AppName {

    String name;
  //  Boolean blockedStatus;



    AppName(String name/*,Boolean blockedStatus*/){this.name=name; /*this.blockedStatus=blockedStatus;*/}
    AppName(){name=""; /*blockedStatus=false;*/}


    public String getName(){return name;}
   // public Boolean getBlockedStatus(){return /*blockedStatus;*/}
    public void setName(String name){this.name=name;}
    public void setBlockedStatus(Boolean blockedStatus){/*this.blockedStatus=blockedStatus;*/}

}
