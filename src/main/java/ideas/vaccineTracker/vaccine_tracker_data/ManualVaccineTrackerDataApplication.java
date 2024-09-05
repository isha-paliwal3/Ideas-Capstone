//package ideas.vaccineTracker.vaccine_tracker_data;
//
//import ideas.vaccineTracker.vaccine_tracker_data.dto.*;
//import ideas.vaccineTracker.vaccine_tracker_data.entity.*;
//import ideas.vaccineTracker.vaccine_tracker_data.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.util.List;
//import java.time.LocalDate;
//import java.util.Optional;
//
//
//@SpringBootApplication
//public class ManualVaccineTrackerDataApplication implements CommandLineRunner {
//
//	@Autowired
//	private DoctorRepository doctorRepository;
//
//	@Autowired
//	private PatientRepository patientRepository;
//
//	@Autowired
//	private VaccineRepository vaccineRepository;
//
//	@Autowired
//	private VaccinationLogRepository vaccinationLogRepository;
//
//	@Autowired
//	private ImmunizationScheduleRepository immunizationScheduleRepository;
//
//	public static void main(String[] args) {
//		SpringApplication.run(ManualVaccineTrackerDataApplication.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		// TODO: Save Doctor
//		Doctor doctor1 = doctorRepository.save(new Doctor(0,"dramit@gmail.com", "password@123", "Dr.Amit Singhal", "Pediatrics", "9876541238"));
//		Doctor doctor2 = doctorRepository.save(new Doctor(0,"drrakesh@gmail.com", "password@123", "Dr.Rakesh Mittal", "General Medicine", "9887654512"));
//
//		// TODO: Save Patient
//		Patient patient1 = patientRepository.save(new Patient(0, "Shagun Agrawal", LocalDate.of(2024, 1, 15).toString(), "Male", "Pune", "9876543210"));
//		Patient patient2 = patientRepository.save(new Patient(0, "Smita Pathak", LocalDate.of(2023, 12, 5).toString(), "Female", "Mumbai", "9873216540"));
//		Patient patient3 = patientRepository.save(new Patient(0, "Karan Roy", LocalDate.of(2023, 9, 8).toString(), "Male", "Pimpri", "7894561230"));
//		Patient patient4 = patientRepository.save(new Patient(0, "Arti Rathi", LocalDate.of(2024, 8, 16).toString(), "Female", "Pune", "6895471237"));
//
//
//		// TODO: Save Vaccine and TODO: Save ImmunizationSchedule
//		Vaccine bcg = vaccineRepository.save(new Vaccine(0,"Bacillus Calmette-Guerin (BCG)", "Intra-dermal", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,0, bcg));
//
//		Vaccine opv0 = vaccineRepository.save(new Vaccine(0,"Oral Polio Vaccine (OPV)-0", "Oral", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,0, opv0));
//
//		Vaccine hepB = vaccineRepository.save(new Vaccine(0,"Hepatitis B birth dose", "Intra-muscular", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,0, hepB));
//
//		// At 6 Weeks (42 days)
//		Vaccine opv1 = vaccineRepository.save(new Vaccine(0,"Oral Polio Vaccine (OPV)-1", "Oral", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,42, opv1));
//
//		Vaccine penta1 = vaccineRepository.save(new Vaccine(0,"Pentavalent-1", "Intra-muscular", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,42, penta1));
//
//		Vaccine rvv1 = vaccineRepository.save(new Vaccine(0,"Rotavirus Vaccine (RVV)-1", "Oral", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,42, rvv1));
//
//		Vaccine fipv1 = vaccineRepository.save(new Vaccine(0,"Fractional dose of Inactivated Polio Vaccine (fIPV)-1", "Intra-dermal", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,42, fipv1));
//
//		Vaccine pcv1 = vaccineRepository.save(new Vaccine(0,"Pneumococcal Conjugate Vaccine (PCV)-1", "Intra-muscular", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,42, pcv1));
//
//		// At 10 Weeks (70 days)
//		Vaccine opv2 = vaccineRepository.save(new Vaccine(0,"Oral Polio Vaccine (OPV)-2", "Oral", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,70, opv2));
//
//		Vaccine penta2 = vaccineRepository.save(new Vaccine(0,"Pentavalent-2", "Intra-muscular", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,70, penta2));
//
//		Vaccine rvv2 = vaccineRepository.save(new Vaccine(0,"Rotavirus Vaccine (RVV)-2", "Oral", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,70, rvv2));
//
//		// At 14 Weeks (98 days)
//		Vaccine opv3 = vaccineRepository.save(new Vaccine(0,"Oral Polio Vaccine (OPV)-3", "Oral", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,98, opv3));
//
//		Vaccine penta3 = vaccineRepository.save(new Vaccine(0,"Pentavalent-3", "Intra-muscular", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,98, penta3));
//
//		Vaccine rvv3 = vaccineRepository.save(new Vaccine(0,"Rotavirus Vaccine (RVV)-3", "Oral", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,98, rvv3));
//
//		Vaccine fipv2 = vaccineRepository.save(new Vaccine(0,"Fractional dose of Inactivated Polio Vaccine (fIPV)-2", "Intra-dermal", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,98, fipv2));
//
//		Vaccine pcv2 = vaccineRepository.save(new Vaccine(0,"Pneumococcal Conjugate Vaccine (PCV)-2", "Intra-muscular", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,98, pcv2));
//
//		// At 9-12 Months (270-365 days)
//		Vaccine mr1 = vaccineRepository.save(new Vaccine(0,"Measles & Rubella (MR)-1", "Sub-cutaneous", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,270, mr1));
//
//		Vaccine pcvBooster = vaccineRepository.save(new Vaccine(0,"Pneumococcal Conjugate Vaccine (PCV)-Booster", "Intra-muscular", 1, 100.0));
//		immunizationScheduleRepository.save(new ImmunizationSchedule(0,270, pcvBooster));
//
//		// TODO: Save VaccinationLog
//		VaccinationLog log1 = vaccinationLogRepository.save(new VaccinationLog(0, patient1, bcg, doctor1, 1, LocalDate.of(2024,1,15).toString(), LocalDate.of(2024,1,15).toString(), "Completed"));
//		VaccinationLog log2 = vaccinationLogRepository.save(new VaccinationLog(0, patient1, opv0, doctor1, 1, LocalDate.of(2024,1,15).toString(), LocalDate.of(2024,1,15).toString(), "Completed"));
//		VaccinationLog log3 = vaccinationLogRepository.save(new VaccinationLog(0, patient1, hepB, doctor1, 1, LocalDate.of(2024,2,24).toString(), LocalDate.of(2024,1,15).toString(), "Completed"));
//
//		//TODO:: Authenticate Doctor
//		String email = "dramit@gmail.com";
//		String password = "password@123";
//		Optional<Doctor> doctor = doctorRepository.findByEmailAndPassword(email, password);
//		if (doctor.isPresent()) {
//			System.out.println("Doctor authenticated: " + doctor.get().getDoctorName());
//		} else {
//			System.out.println("Authentication failed, Wrong Email Id or Password");
//		}
//
//		//TODO:: Check if Email Exists
//		Boolean isEmailExist = doctorRepository.existsByEmail(email);
//		if(isEmailExist)
//		{
//			System.out.println("email already registered");
//		}
//
//		//TODO:: Search Vaccines by Name
//		String keyword = "Pentavalent";
//		List<VaccineProjection> vaccines = vaccineRepository.findByVaccineNameContainingIgnoreCase(keyword);
//		System.out.println("Vaccines matching keyword '" + keyword + "':");
//		for (VaccineProjection vaccine : vaccines) {
//			System.out.println("Vaccine Name: " + vaccine.getVaccineName());
//		}
//
//		// TODO: Get all Patients
//		List<Patient> patients = patientRepository.findAll();
//		for (Patient patient : patients) {
//			System.out.println("Patient Id=" + patient.getPatientId());
//			System.out.println("Patient Name=" + patient.getPatientName());
//		}
//
//		// TODO: Get distinct patients by their name
//		String searchName = "rathi";
//		List<PatientProjection> patientsByName = patientRepository.findDistinctPatientsByName(searchName);
//
//		System.out.println("Patients with name containing '" + searchName + "':");
//		for (PatientProjection patient : patientsByName) {
//			System.out.println("Patient Name: " + patient.getPatientName());
//		}
//
//		// TODO: Get distinct patients by their Phone
//		String phoneNumber = "9876543210";
//		Optional<PatientProjection> patientOptional = patientRepository.findDistinctByPhoneNumber(phoneNumber);
//
//		if (patientOptional.isPresent()) {
//			PatientProjection patient = patientOptional.get();
//			System.out.println("Patient found:");
//			System.out.println("Patient Name: " + patient.getPatientName());
//			System.out.println("Phone Number: " + patient.getPhoneNumber());
//		} else {
//			System.out.println("No patient found with phone number: " + phoneNumber);
//		}
//
//		// TODO: Get all Vaccination Logs for a Patient
//		List<VaccinationLogProjection> logs = vaccinationLogRepository.findByPatientPatientId(1);
//		for (VaccinationLogProjection log : logs) {
//			System.out.println("Vaccine Name=" + log.getVaccine().getVaccineName());
//			System.out.println("Doctor Name=" + log.getDoctor().getDoctorName());
//			System.out.println("Vaccination Date=" + log.getVaccinationDate());
//			System.out.println("Vaccination Status=" + log.getVaccinationStatus());
//		System.out.println("Next Due Date: " + log.getNextDueDate());
//		}
//
//		Integer patientAgeInDays = 52;
//
//		//TODO::Get Past Immunization Schedule for a Specific Patient by Age:
//		List<ImmunizationScheduleProjection> scheduleByAge = immunizationScheduleRepository.findByAgeInDaysLessThanEqualOrderByAgeInDays(patientAgeInDays);
//		System.out.println("Immunization Schedule for age " + patientAgeInDays + " days:");
//		for (ImmunizationScheduleProjection schedule : scheduleByAge) {
//			System.out.println("Vaccine Name: " + schedule.getVaccine().getVaccineName());
//			System.out.println("Route: " + schedule.getVaccine().getRoute());
//			System.out.println("Age in Days: " + schedule.getAgeInDays());
//		}
//
//		//TODO::Get upcoming Immunization Schedule for a Specific Patient by Age:
//		List<ImmunizationScheduleProjection> scheduleByAge1 = immunizationScheduleRepository.findByAgeInDaysGreaterThanEqualOrderByAgeInDays(patientAgeInDays);
//		System.out.println("Immunization Schedule for age " + patientAgeInDays + " days:");
//		for (ImmunizationScheduleProjection schedule : scheduleByAge1) {
//			System.out.println("Vaccine Name: " + schedule.getVaccine().getVaccineName());
//			System.out.println("Route: " + schedule.getVaccine().getRoute());
//			System.out.println("Age in Days: " + schedule.getAgeInDays());
//		}
//
//		// TODO::Get All Immunization Schedules
//		List<ImmunizationSchedule> allSchedules = immunizationScheduleRepository.findAll();
//		System.out.println("All Immunization Schedules:");
//		for (ImmunizationSchedule schedule : allSchedules) {
//			System.out.println("Vaccine Name: " + schedule.getVaccine().getVaccineName());
//			System.out.println("Route: " + schedule.getVaccine().getRoute());
//			System.out.println("Age in Days: " + schedule.getAgeInDays());
//		}
//
//		Integer patientId = 1;
//
//		//TODO:: Get Upcoming Vaccinations for a Patient
//		List<VaccinationLogProjection> upcomingVaccinations = vaccinationLogRepository.findByPatientPatientIdAndVaccinationStatusOrderByNextDueDateAsc(patientId, "Upcoming");
//		System.out.println("Upcoming Vaccinations for Patient ID " + patientId + ":");
//		for (VaccinationLogProjection log : upcomingVaccinations) {
//			System.out.println("Vaccine Name: " + log.getVaccine().getVaccineName());
//			System.out.println("Dose Number: " + log.getDoseNumber());
//			System.out.println("Next Due Date: " + log.getNextDueDate());
//		}
//
//		//TODO:: Check if a Vaccine is Due for a Patient
//		Integer vaccineId = 1;
//		Integer dueVaccinations = vaccinationLogRepository.countByPatientPatientIdAndVaccineVaccineIdAndVaccinationStatus(patientId, vaccineId, "Upcoming");
//		System.out.println("Is Vaccine ID " + vaccineId + " due for Patient ID " + patientId + ": " + (dueVaccinations > 0));
//
//		//TODO:: Count Total Vaccinations Administered
//		Integer totalVaccinations = vaccinationLogRepository.countByVaccinationStatus("Completed");
//		System.out.println("Total Completed Vaccinations: " + totalVaccinations);
//
//
//		//TODO:: Get Overdue Vaccinations
//		String currentDate = LocalDate.now().toString();
//		List<VaccinationLogProjection> overdueVaccinations = vaccinationLogRepository.findByNextDueDateBeforeAndVaccinationStatus(currentDate, "Upcoming");
//		System.out.println("Overdue Vaccinations:");
//		for (VaccinationLogProjection log : overdueVaccinations) {
//			System.out.println("Patient Name: " + log.getPatient().getPatientName());
//			System.out.println("Vaccine Name: " + log.getVaccine().getVaccineName());
//			System.out.println("Next Due Date: " + log.getNextDueDate());
//		}
//
//		// TODO:: Filter Patients by Age Range
//		String startAge = LocalDate.now().minusYears(1).toString();
//		String endAge = LocalDate.now().toString();
//		List<PatientProjection> patientsByAgeRange = patientRepository.findDistinctByDateOfBirthBetween(startAge, endAge);
//
//		System.out.println("Patients aged between " + startAge + " and " + endAge + " days:");
//		for (PatientProjection patient : patientsByAgeRange) {
//			String dateOfBirth = patient.getDateOfBirth();
//			LocalDate birthDate = LocalDate.parse(dateOfBirth);
//			LocalDate currentDate2 = LocalDate.now();
//			long ageInDays = java.time.temporal.ChronoUnit.DAYS.between(birthDate, currentDate2);
//
//			System.out.println("Patient Name: " + patient.getPatientName());
//			System.out.println("Age: " + ageInDays + " days");
//		}
//	}
//}
