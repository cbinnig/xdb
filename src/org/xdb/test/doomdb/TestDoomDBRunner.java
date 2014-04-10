package org.xdb.test.doomdb;

public class TestDoomDBRunner {

    public static void main(String[] args) throws Exception {
        if ( args.length > 0) {
            final Integer numberOfRuns = Integer.valueOf(args[0]); 
            final Integer queryNumber = Integer.valueOf(args[1]); 
            final Integer partsNumber = Integer.valueOf(args[2]);
           
            System.out.println("Running test " + numberOfRuns);
            TestDoomDB test = new TestDoomDB();
            if(queryNumber ==1 & partsNumber ==10 ){
            	test.testQ1With10Parts();
            } 
            else if(queryNumber ==5 & partsNumber ==2) {
            	test.testQ5With2Parts(); 
            } else if (queryNumber ==5 & partsNumber ==10) {
            	test.testQ5With10Parts();
            }
        }
    }
}