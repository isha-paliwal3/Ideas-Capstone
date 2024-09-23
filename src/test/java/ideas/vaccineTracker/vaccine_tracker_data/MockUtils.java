package ideas.vaccineTracker.vaccine_tracker_data;

import ideas.vaccineTracker.vaccine_tracker_data.dto.VaccinationLogProjection;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MockUtils {
    public static VaccinationLogProjection getMockVaccinationLogProjection(Integer logId, Integer doseNumber, String vaccinationDate, String nextDueDate, String vaccinationStatus, Integer doctorId, String doctorName, String email, String specialization, String phoneNumber, Integer vaccineId, String vaccineName, String route, Integer maxRequiredDoses, Double effectivenessPercentage, Integer patientId, String patientName, String dateOfBirth, String gender, String address) {
        VaccinationLogProjection mockedVaccinationLogProjection = Mockito.mock(VaccinationLogProjection.class);
        VaccinationLogProjection.DoctorProjection mockedDoctorProjection = Mockito.mock(VaccinationLogProjection.DoctorProjection.class);
        VaccinationLogProjection.VaccineProjection mockedVaccineProjection = Mockito.mock(VaccinationLogProjection.VaccineProjection.class);
        VaccinationLogProjection.PatientProjection mockedPatientProjection = Mockito.mock(VaccinationLogProjection.PatientProjection.class);

        Mockito.lenient().when(mockedDoctorProjection.getDoctorId()).thenReturn(doctorId);
        Mockito.lenient().when(mockedDoctorProjection.getDoctorName()).thenReturn(doctorName);
        Mockito.lenient().when(mockedDoctorProjection.getEmail()).thenReturn(email);
        Mockito.lenient().when(mockedDoctorProjection.getSpecialization()).thenReturn(specialization);
        Mockito.lenient().when(mockedDoctorProjection.getPhoneNumber()).thenReturn(phoneNumber);

        Mockito.lenient().when(mockedVaccineProjection.getVaccineId()).thenReturn(vaccineId);
        Mockito.lenient().when(mockedVaccineProjection.getVaccineName()).thenReturn(vaccineName);
        Mockito.lenient().when(mockedVaccineProjection.getRoute()).thenReturn(route);
        Mockito.lenient().when(mockedVaccineProjection.getMaxRequiredDoses()).thenReturn(maxRequiredDoses);
        Mockito.lenient().when(mockedVaccineProjection.getEffectivenessPercentage()).thenReturn(effectivenessPercentage);

        Mockito.lenient().when(mockedPatientProjection.getPatientId()).thenReturn(patientId);
        Mockito.lenient().when(mockedPatientProjection.getPatientName()).thenReturn(patientName);
        Mockito.lenient().when(mockedPatientProjection.getDateOfBirth()).thenReturn(dateOfBirth);
        Mockito.lenient().when(mockedPatientProjection.getGender()).thenReturn(gender);
        Mockito.lenient().when(mockedPatientProjection.getAddress()).thenReturn(address);
        Mockito.lenient().when(mockedPatientProjection.getPhoneNumber()).thenReturn(phoneNumber);

        Mockito.lenient().when(mockedVaccinationLogProjection.getLogId()).thenReturn(logId);
        Mockito.lenient().when(mockedVaccinationLogProjection.getPatient()).thenReturn(mockedPatientProjection);
        Mockito.lenient().when(mockedVaccinationLogProjection.getVaccine()).thenReturn(mockedVaccineProjection);
        Mockito.lenient().when(mockedVaccinationLogProjection.getDoctor()).thenReturn(mockedDoctorProjection);
        Mockito.lenient().when(mockedVaccinationLogProjection.getDoseNumber()).thenReturn(doseNumber);
        Mockito.lenient().when(mockedVaccinationLogProjection.getVaccinationDate()).thenReturn(vaccinationDate);
        Mockito.lenient().when(mockedVaccinationLogProjection.getNextDueDate()).thenReturn(nextDueDate);
        Mockito.lenient().when(mockedVaccinationLogProjection.getVaccinationStatus()).thenReturn(vaccinationStatus);

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

        VaccinationLogProjection actualLog = getMockVaccinationLogProjection(
                expectedLogId, expectedDoseNumber, expectedVaccinationDate, expectedNextDueDate, expectedVaccinationStatus,
                expectedDoctorId, expectedDoctorName, expectedEmail, expectedSpecialization, expectedPhoneNumber,
                expectedVaccineId, expectedVaccineName, expectedRoute, expectedMaxRequiredDoses, expectedEffectivenessPercentage,
                expectedPatientId, expectedPatientName, expectedDateOfBirth, expectedGender, expectedAddress
        );

        List<VaccinationLogProjection> mockedVaccinationLogProjections = new ArrayList<>();
        mockedVaccinationLogProjections.add(actualLog);

        return mockedVaccinationLogProjections;
    }
}

