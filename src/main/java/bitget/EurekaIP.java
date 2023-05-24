//package com.bitget;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.InputStreamReader;
//
//public class EurekaIP {
//
//    private void callScript(String script, String args, String... workspace){
//        try {
//            String cmd = "sh " + script + " " + args;
////        	String[] cmd = {"sh", script, "4"};
//            File dir = null;
//            if(workspace[0] != null){
//                dir = new File(workspace[0]);
//                System.out.println(workspace[0]);
//            }
//            String[] evnp = {"val=2", "call=Bash Shell"};
//            process = Runtime.getRuntime().exec(cmd, evnp, dir);
////            process = Runtime.getRuntime().exec(cmd);
//            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line = "";
//            while ((line = input.readLine()) != null) {
//                System.out.println(line);
//            }
//            input.close();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        CallShell call = new CallShell();
//        call.callScript("test.sh", "4", "/root/experiment/");
//    }
//}
