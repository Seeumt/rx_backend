//package cn.seeumt.config;
//
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson.support.config.FastJsonConfig;
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    /*converters = converters.stream()
//            .filter((converter)-> !(converter instanceof MappingJackson2HttpMessageConverter))
//            .collect(Collectors.toList());
//    for (HttpMessageConverter<?> converter : converters) {
//        if (converter instanceof MappingJackson2HttpMessageConverter){
//            converters.remove(converter);
//        }
//    }*/
//        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
//        while(iterator.hasNext()){
//            HttpMessageConverter<?> converter = iterator.next();
//            if(converter instanceof MappingJackson2HttpMessageConverter){
//                iterator.remove();
//            }
//        }
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        //自定义配置...
//        FastJsonConfig config = new FastJsonConfig();
//        config.setSerializerFeatures(SerializerFeature.QuoteFieldNames,
//                SerializerFeature.WriteEnumUsingToString,
//                /*SerializerFeature.WriteMapNullValue,*/
//                SerializerFeature.WriteDateUseDateFormat,
//                SerializerFeature.DisableCircularReferenceDetect);
//        fastJsonHttpMessageConverter.setFastJsonConfig(config);
//        converters.add(fastJsonHttpMessageConverter);
//    }
//}
