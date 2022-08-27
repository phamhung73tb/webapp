package hp.com.controller;

import hp.com.dto.request.RequestLogin;
import hp.com.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController extends AbstractController{
    @Autowired
    private LoginService service;

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody RequestLogin requestLogin) {
        return response(service.login(requestLogin));
    }
}
