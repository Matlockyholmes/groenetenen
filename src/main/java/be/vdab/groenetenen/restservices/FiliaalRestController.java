package be.vdab.groenetenen.restservices;

import be.vdab.groenetenen.services.FiliaalService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filialen")
public class FiliaalRestController {
    private final FiliaalService filiaalService;

    public FiliaalRestController(FiliaalService filiaalService) {
        this.filiaalService = filiaalService;
    }
}
