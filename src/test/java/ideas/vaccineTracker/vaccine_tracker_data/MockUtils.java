package ideas.vaccineTracker.vaccine_tracker_data;

import ideas.vaccineTracker.vaccine_tracker_data.dto.VaccinationLogProjection;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MockUtils {
    private static VaccinationLogProjection getMockVaccinationLogProjection(Integer logId, Integer doseNumber, String vaccinationDate, String nextDueDate, String vaccinationStatus, Integer doctorId, String doctorName, String email, String specialization, String phoneNumber, Integer vaccineId, String vaccineName, String route, Integer maxRequiredDoses, Double effectivenessPercentage, Integer patientId, String patientName, String dateOfBirth, String gender, String address) {
        VaccinationLogProjection mockedVaccinationLogProjection = Mockito.mock(VaccinationLogProjection.class);
        VaccinationLogProjection.DoctorProjection mockedDoctorProjection = Mockito.mock(VaccinationLogProjection.DoctorProjection.class);
        VaccinationLogProjection.VaccineProjection mockedVaccineProjection = Mockito.mock(VaccinationLogProjection.VaccineProjection.class);
        VaccinationLogProjection.PatientProjection mockedPatientProjection = Mockito.mock(VaccinationLogProjection.PatientProjection.class);

        when(mockedDoctorProjection.getDoctorId()).thenReturn(doctorId);
        when(mockedDoctorProjection.getDoctorName()).thenReturn(doctorName);
        when(mockedDoctorProjection.getEmail()).thenReturn(email);
        when(mockedDoctorProjection.getSpecialization()).thenReturn(specialization);
        when(mockedDoctorProjection.getPhoneNumber()).thenReturn(phoneNumber);

        when(mockedVaccineProjection.getVaccineId()).thenReturn(vaccineId);
        when(mockedVaccineProjection.getVaccineName()).thenReturn(vaccineName);
        when(mockedVaccineProjection.getRoute()).thenReturn(route);
        when(mockedVaccineProjection.getMaxRequiredDoses()).thenReturn(maxRequiredDoses);
        when(mockedVaccineProjection.getEffectivenessPercentage()).thenReturn(effectivenessPercentage);

        when(mockedPatientProjection.getPatientId()).thenReturn(patientId);
        when(mockedPatientProjection.getPatientName()).thenReturn(patientName);
        when(mockedPatientProjection.getDateOfBirth()).thenReturn(dateOfBirth);
        when(mockedPatientProjection.getGender()).thenReturn(gender);
        when(mockedPatientProjection.getAddress()).thenReturn(address);
        when(mockedPatientProjection.getPhoneNumber()).thenReturn(phoneNumber);

        when(mockedVaccinationLogProjection.getLogId()).thenReturn(logId);
        when(mockedVaccinationLogProjection.getPatient()).thenReturn(mockedPatientProjection);
        when(mockedVaccinationLogProjection.getVaccine()).thenReturn(mockedVaccineProjection);
        when(mockedVaccinationLogProjection.getDoctor()).thenReturn(mockedDoctorProjection);
        when(mockedVaccinationLogProjection.getDoseNumber()).thenReturn(doseNumber);
        when(mockedVaccinationLogProjection.getVaccinationDate()).thenReturn(vaccinationDate);
        when(mockedVaccinationLogProjection.getNextDueDate()).thenReturn(nextDueDate);
        when(mockedVaccinationLogProjection.getVaccinationStatus()).thenReturn(vaccinationStatus);

        return mockedVaccinationLogProjection;
    }

    public static List<VaccinationLogProjection> getVaccinationLogProjectionList() {
        Integer expectedLogId = 1;
        Integer expectedDoseNumber = 2;
        String expectedVaccinationDate = "2024-01-15";
        String expectedNextDueDate = "2024-02-15";
        String expectedVaccinationStatus = "Completed";
        Integer expectedDoctorId = 101;
        String expectedDoctorName = "Dr. John Doe";
        String expectedEmail = "doctor@example.com";
        String expectedSpecialization = "Pediatrics";
        String expectedPhoneNumber = "1234567890";
        Integer expectedVaccineId = 501;
        String expectedVaccineName = "BCG";
        String expectedRoute = "Intra-dermal";
        Integer expectedMaxRequiredDoses = 1;
        Double expectedEffectivenessPercentage = 99.5;
        Integer expectedPatientId = 201;
        String expectedPatientName = "Jane Doe";
        String expectedDateOfBirth = "2023-01-01";
        String expectedGender = "Female";
        String expectedAddress = "123 Main Street";

        // Call the method
        VaccinationLogProjection actualLog = getMockVaccinationLogProjection(
                expectedLogId, expectedDoseNumber, expectedVaccinationDate, expectedNextDueDate, expectedVaccinationStatus,
                expectedDoctorId, expectedDoctorName, expectedEmail, expectedSpecialization, expectedPhoneNumber,
                expectedVaccineId, expectedVaccineName, expectedRoute, expectedMaxRequiredDoses, expectedEffectivenessPercentage,
                expectedPatientId, expectedPatientName, expectedDateOfBirth, expectedGender, expectedAddress
        );

        // Assert each field
        assertEquals(expectedLogId, actualLog.getLogId());
        assertEquals(expectedDoseNumber, actualLog.getDoseNumber());
        assertEquals(expectedVaccinationDate, actualLog.getVaccinationDate());
        assertEquals(expectedNextDueDate, actualLog.getNextDueDate());
        assertEquals(expectedVaccinationStatus, actualLog.getVaccinationStatus());
        assertEquals(expectedDoctorId, actualLog.getDoctor().getDoctorId());
        assertEquals(expectedDoctorName, actualLog.getDoctor().getDoctorName());
        assertEquals(expectedEmail, actualLog.getDoctor().getEmail());
        assertEquals(expectedSpecialization, actualLog.getDoctor().getSpecialization());
        assertEquals(expectedPhoneNumber, actualLog.getDoctor().getPhoneNumber());
        assertEquals(expectedVaccineId, actualLog.getVaccine().getVaccineId());
        assertEquals(expectedVaccineName, actualLog.getVaccine().getVaccineName());
        assertEquals(expectedRoute, actualLog.getVaccine().getRoute());
        assertEquals(expectedMaxRequiredDoses, actualLog.getVaccine().getMaxRequiredDoses());
        assertEquals(expectedEffectivenessPercentage, actualLog.getVaccine().getEffectivenessPercentage());
        assertEquals(expectedPatientId, actualLog.getPatient().getPatientId());
        assertEquals(expectedPatientName, actualLog.getPatient().getPatientName());
        assertEquals(expectedDateOfBirth, actualLog.getPatient().getDateOfBirth());
        assertEquals(expectedGender, actualLog.getPatient().getGender());
        assertEquals(expectedAddress, actualLog.getPatient().getAddress());
        assertEquals(expectedPhoneNumber, actualLog.getPatient().getPhoneNumber());
        assertEquals(actualLog.getVaccine(), actualLog.getVaccine());
        assertEquals(actualLog.getDoctor(), actualLog.getDoctor());
        assertEquals(actualLog.getPatient(), actualLog.getPatient());

        List<VaccinationLogProjection> mockedVaccinationLogProjections = new ArrayList<>();
        mockedVaccinationLogProjections.add(actualLog);

        return mockedVaccinationLogProjections;
    }
}
