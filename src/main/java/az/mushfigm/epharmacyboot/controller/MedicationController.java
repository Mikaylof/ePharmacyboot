package az.mushfigm.epharmacyboot.controller;

import az.mushfigm.epharmacyboot.dto.request.ReqCustomer;
import az.mushfigm.epharmacyboot.dto.request.ReqMedication;
import az.mushfigm.epharmacyboot.dto.response.RespMedication;
import az.mushfigm.epharmacyboot.dto.response.Response;
import az.mushfigm.epharmacyboot.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@RestController
@RequestMapping("/medication")
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

    @GetMapping("/GetMedicationListByCustomerId/{customerId}")
    public Response<List<RespMedication>> getMedicationListByCustomerId(@PathVariable Long customerId){
        return medicationService.getMedicationListByCustomerId(customerId);
    }
    @PostMapping("/AddMedication")
    public Response addMedication(@RequestBody ReqMedication reqMedication){
        return medicationService.addMedication(reqMedication);
    }

}
