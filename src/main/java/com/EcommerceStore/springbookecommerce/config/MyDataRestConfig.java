package com.EcommerceStore.springbookecommerce.config;

import com.EcommerceStore.springbookecommerce.dao.ProductRepository;
import com.EcommerceStore.springbookecommerce.entity.Product;
import com.EcommerceStore.springbookecommerce.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    private String theAllowedOrigins = "http://localhost:4200";

    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] theUnsupportedActions = {
                HttpMethod.POST,
                HttpMethod.DELETE,
                HttpMethod.PUT
        };

        config.exposeIdsFor(Product.class);
        config.exposeIdsFor(ProductRepository.class);

        disableHttpMethods(Product.class, config, theUnsupportedActions);
        disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);

        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);

    }

    /**
     * Disabling Http methods to allow read-only.
     * @param theClass
     * @param config
     * @param theUnsupportedMethods
     */
    public void disableHttpMethods(Class theClass,
                                   RepositoryRestConfiguration config,
                                   HttpMethod[] theUnsupportedMethods) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedMethods))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedMethods));
    }
}