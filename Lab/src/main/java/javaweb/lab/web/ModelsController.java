package javaweb.lab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/models")
public class ModelsController {

    @GetMapping()
    public String getAddModelPage() {
        return "model-add";
    }


}
