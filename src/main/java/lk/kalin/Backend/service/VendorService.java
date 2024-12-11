package lk.kalin.Backend.service;

import lk.kalin.Backend.entity.Vendor;
import lk.kalin.Backend.repository.VendorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    // Save or update a vendor
    public Vendor saveVendor(Vendor vendor) {

        return vendorRepository.save(vendor);
    }

    // Retrieve all vendors
    public List<Vendor> getAllVendors() {

        return vendorRepository.findAll();
    }


}

