package kr.lleejong.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * Created by lleejong on 2017. 6. 4..
 */
public class SCDParser {
    public class SCDDocField{
        private String fieldName;
        private String content;
        public SCDDocField(String fieldName, String content){
            this.fieldName = fieldName;
            this.content = content;
        }
        public String getFieldName(){
            return fieldName;
        }
        
        public String getContent(){
            return content;
        }
    }
    
    public class SCDDoc{
        private ArrayList<SCDDocField> fields = new ArrayList<SCDDocField>();
        
    }
    
    public static class Result{
        LinkedHashSet<String> fields = new LinkedHashSet<String>();
    }
    
    
    public static void main(String args[]) throws IOException {
        String path = "src/main/resources/sample_collections/sample_bbs/B-99-197001010101-00000-I-C.SCD";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)),"UTF-8"));
        boolean fieldnameFlag = false;
        StringBuffer fieldNameBuffer = new StringBuffer();
        StringBuffer contentBuffer = new StringBuffer();
        while(true){
           char ch = (char)reader.read();
           if(ch == -1) {
               break;
           }
           if(ch == '<'){
               if(!fieldnameFlag){
                   fieldnameFlag = true;
                   if(contentBuffer.length() > 0){
                       String content = contentBuffer.toString();
                       contentBuffer.setLength(0);
                       
                       System.out.println("content : " + content);
                   }
               }
           }
           else if(ch == '>'){
               String fieldName = fieldNameBuffer.toString();
               fieldNameBuffer.setLength(0);
               fieldnameFlag = false;
               System.out.println("field : " + fieldName);
           }
           else{
              if(fieldnameFlag)
                  fieldNameBuffer.append(ch);
              else{
                  contentBuffer.append(ch);
              }
           }
           
        }
    }
}
