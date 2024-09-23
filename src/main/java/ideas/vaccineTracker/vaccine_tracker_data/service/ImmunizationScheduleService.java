package ideas.vaccineTracker.vaccine_tracker_data.service;

import ideas.vaccineTracker.vaccine_tracker_data.dto.ImmunizationScheduleProjection;
import ideas.vaccineTracker.vaccine_tracker_data.dto.PatientVaccinationDueProjection;
import ideas.vaccineTracker.vaccine_tracker_data.entity.ImmunizationSchedule;
import ideas.vaccineTracker.vaccine_tracker_data.exception.ResourceNotFoundException;
import ideas.vaccineTracker.vaccine_tracker_data.repository.ImmunizationScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImmunizationScheduleService {

    @Autowired
    private ImmunizationScheduleRepository immunizationScheduleRepository;

    public List<ImmunizationScheduleProjection> getAllImmunizationSchedules() {
        return immunizationScheduleRepository.findBy();
    }

    public List<ImmunizationScheduleProjection> getSchedulesByVaccineId(Integer vaccineId) {
        List<ImmunizationScheduleProjection> schedules = immunizationScheduleRepository.findByVaccineVaccineId(vaccineId);
        if (schedules.isEmpty()) {
            throw new ResourceNotFoundException("No immunization schedules found for vaccine ID: " + vaccineId);
        }
        return schedules;
    }

    public ImmunizationSchedule createImmunizationSchedule(ImmunizationSchedule schedule) {
        return immunizationScheduleRepository.save(schedule);
    }

    public ImmunizationSchedule updateImmunizationSchedule(Integer id, ImmunizationSchedule scheduleDetails) {
        ImmunizationSchedule schedule = immunizationScheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Immunization schedule not found with id: " + id));

        schedule.setAgeInDays(scheduleDetails.getAgeInDays());
        schedule.setVaccine(scheduleDetails.getVaccine());

        return immunizationScheduleRepository.save(schedule);
    }

    public void deleteImmunizationSchedule(Integer id) {
        ImmunizationSchedule schedule = immunizationScheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Immunization schedule not found with id: " + id));
        immunizationScheduleRepository.delete(schedule);
    }

    public List<PatientVaccinationDueProjection> getVaccinationsDueWithin7Days() {
        return immunizationScheduleRepository.findVaccinationsDueWithin7Days();
    }

    public long countVaccinationsDueWithin7Days() {
        return immunizationScheduleRepository.countVaccinationsDueWithin10Days();
    }
}
