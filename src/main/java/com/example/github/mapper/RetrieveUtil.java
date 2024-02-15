//package com.example.github.mapper;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.util.EntityUtils;
//
//
//
//import java.io.IOException;
////import java.net.http.HttpResponse;
//import org.apache.http.HttpResponse;
//
//
//public class RetrieveUtil {
//
//    public static<T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz) throws IOException{
//        String jsonFromResponse = EntityUtils.toString(response.getEntity());
//        ObjectMapper mapper = new ObjectMapper()
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
//        return mapper.readValue(jsonFromResponse, clazz);
//    }
//}
