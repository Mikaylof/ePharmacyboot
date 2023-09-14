package az.mushfigm.epharmacyboot.service;

import az.mushfigm.epharmacyboot.dto.request.ReqMedication;
import az.mushfigm.epharmacyboot.dto.response.RespMedication;
import az.mushfigm.epharmacyboot.dto.response.Response;

import java.util.List;

public interface MedicationService {
    Response<List<RespMedication>> getMedicationListByCustomerId(Long customerId);

    Response addMedication(ReqMedication reqMedication);
}
