package it.diriveprojectbe.apigateway.client;

import it.diriveprojectbe.userservice.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service",fallbackFactory =UserClientFallback.class)
public interface UserClient extends UserApi {
}
