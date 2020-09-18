package by.nikita.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${user.upload.path}")
    private String userUploadPath;

    @Value("${room.upload.path}")
    private String roomUploadPath;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/signIn_signUp/login");
        registry.addViewController("/registration").setViewName("/signIn_signUp/registration");
        registry.addViewController("/").setViewName("/greeting/index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/img/user/**")//обращение к этому пути
                .addResourceLocations("file:///" + userUploadPath + "/");//перенаправляет всё по этому пути
        registry.addResourceHandler("/img/room/**")//обращение к этому пути
                .addResourceLocations("file:///" + roomUploadPath + "/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}