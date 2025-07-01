package com.example.baithi.Controller;
import com.example.baithi.VEHICLE_RENTAL_E.Rental;
import com.example.baithi.Repository_VEHICLE.RentalRepository;
import com.example.baithi.Repository_VEHICLE.CustomerRepository;
import com.example.baithi.Repository_VEHICLE.VehicleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rentals")
public class RentalController {

    @Autowired private RentalRepository rentalRepo;
    @Autowired private CustomerRepository customerRepo;
    @Autowired private VehicleRepository vehicleRepo;

    @GetMapping
    public String list(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("rentals", rentalRepo.findAll(PageRequest.of(page, 5)));
        return "rental/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("rental", new Rental());
        model.addAttribute("customers", customerRepo.findAll());
        model.addAttribute("vehicles", vehicleRepo.findAll());
        return "rental/add";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Rental rental, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customers", customerRepo.findAll());
            model.addAttribute("vehicles", vehicleRepo.findAll());
            return "rental/add";
        }
        rentalRepo.save(rental);
        return "redirect:/rentals";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        rentalRepo.deleteById(id);
        return "redirect:/rentals";
    }
}
