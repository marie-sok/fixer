import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RequestService;

@Controller
public class MasterController {

    private final RequestService service;

    public MasterController(RequestService service){
        this.service = service;
    }

    @PostMapping("/take/{id}")
    @ResponseBody
    public String take(@PathVariable Long id){

        boolean ok = service.takeRequest(id);

        if(!ok){
            return "409 Conflict";
        }

        return "OK";
    }
}