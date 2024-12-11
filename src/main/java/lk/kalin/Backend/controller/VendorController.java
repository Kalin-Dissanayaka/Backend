package lk.kalin.Backend.controller;

import lk.kalin.Backend.entity.Vendor;
import lk.kalin.Backend.service.VendorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @PostMapping("/addvendor")
    public ResponseEntity<Vendor> addVendor(@RequestBody Vendor vendor) {
            Vendor savedVendor = vendorService.saveVendor(vendor);
            return new ResponseEntity<>(savedVendor, HttpStatus.CREATED);
    }

    @GetMapping("/getAllVendor")
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }
}
