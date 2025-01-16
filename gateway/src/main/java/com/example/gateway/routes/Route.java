package com.example.gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Route {

    @Value("${routes.client-service-url}")
    private String clientServiceUrl;

    @Value("${routes.vehicle-service-url}")
    private String vehicleServiceUrl;

    @Value("${routes.maintenance-service-url}")
    private String maintenanceServiceUrl;

    @Value("${routes.notification-service-url}")
    private String notificationServiceUrl;

    @Value("${routes.facturation-service-url}")
    private String facturationServiceUrl;

    // Configuration des routes du client
    @Bean
    public RouterFunction<ServerResponse> routerClient() {
        return GatewayRouterFunctions.route("clientsRoute")
                .route(RequestPredicates.path("/clients/**"), HandlerFunctions.http(clientServiceUrl))
                .build();
    }

    // Configuration des routes du véhicule
    @Bean
    public RouterFunction<ServerResponse> routerVehicle() {
        return GatewayRouterFunctions.route("VehiculeRoute")
                .route(RequestPredicates.path("/vehicules/**"), HandlerFunctions.http(vehicleServiceUrl))
                .build();
    }

    // Configuration des routes de l'atelier
    @Bean
    public RouterFunction<ServerResponse> routerMaintenance() {
        return GatewayRouterFunctions.route("MaintenanceRouter")
                .route(RequestPredicates.path("/maintenance/taches/**"), HandlerFunctions.http(maintenanceServiceUrl))
                .build();
    }

    // Configuration des routes de notification
    @Bean
    public RouterFunction<ServerResponse> routerNotification() {
        return GatewayRouterFunctions.route("NotificationRouter")
                .route(RequestPredicates.path("/notifications/**"), HandlerFunctions.http(notificationServiceUrl))
                .build();
    }

    // Configuration des routes de facturation
    @Bean
    public RouterFunction<ServerResponse> routerFacturation() {
        return GatewayRouterFunctions.route("FacturationRouter")
                .route(RequestPredicates.path("/facturations/**"), HandlerFunctions.http(facturationServiceUrl))
                .build();
    }
}
