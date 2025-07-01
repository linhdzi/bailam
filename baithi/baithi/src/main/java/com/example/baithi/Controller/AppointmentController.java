package com.example.baithi.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentRepository appointmentRepo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("appointments", appointmentRepo.findAll());
        return "appointment/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", patientRepo.findAll());
        model.addAttribute("doctors", doctorRepo.findAll());
        return "appointment/add";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Appointment appointment,
                       BindingResult result,
                       Model model) {

        if (appointment.getDoctor() != null && appointment.getAppointmentDate() != null) {
            List<Appointment> existing = appointmentRepo.findByDoctorAndAppointmentDate(
                    appointment.getDoctor(), appointment.getAppointmentDate());
            if (!existing.isEmpty()) {
                result.rejectValue("appointmentDate", "error.appointment",
                        "Doctor already has an appointment at this date.");
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("patients", patientRepo.findAll());
            model.addAttribute("doctors", doctorRepo.findAll());
            return "appointment/add";
        }

        appointment.setStatus("Scheduled");
        appointmentRepo.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        appointmentRepo.deleteById(id);
        return "redirect:/appointments";
    }
}
