package be.vdab.groenetenen.controllers;

import be.vdab.groenetenen.services.WerknemerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("werknemers")
public class WerknemerController {
    private final WerknemerService werknemerService;

    public WerknemerController(WerknemerService werknemerService) {
        this.werknemerService = werknemerService;
    }

    @GetMapping
    public ModelAndView lijst(){
        return new ModelAndView("werknemers", "werknemers", werknemerService.findAll());
    }
}
