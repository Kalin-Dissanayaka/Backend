package lk.kalin.Backend;

import lk.kalin.Backend.dto.ConfigurationDTO;

public class Main {

    public static TicketPool ticketPool;

    public static void setConfiguration(ConfigurationDTO config) {
        TicketConfiguration configuration = new TicketConfiguration(config.getReleaseRate(), config.getTotalTickets(), config.getRetrievalRate(), config.getMaxCapacity());
        TicketConfiguration.saveFileConfig(configuration, "datafile.json");
        configuration = TicketConfiguration.loadFileConfig("datafile.json");

        ticketPool = new TicketPool(config.getMaxCapacity(), config.getTotalTickets());

        Vendor[] vendors = new Vendor[config.getNumVendors()];
        Thread vendorThread;
        for (int i = 0; i < config.getNumVendors(); i++) {
            vendors[i] = new Vendor("Vendor-" + (i + 1), config.getTotalTickets(), config.getReleaseRate(), ticketPool);
            vendorThread = new Thread(vendors[i], "Vendor-" + (i + 1));
            vendorThread.start();
        }

        Customer[] customers = new Customer[config.getNumCustomers()];
        Thread customerThread;
        for (int i = 0; i < config.getNumCustomers(); i++) {
            customers[i] = new Customer(i + 1, 5, config.getRetrievalRate(), ticketPool);
            customerThread = new Thread(customers[i], "Customer-" + (i + 1));
            customerThread.start();
        }

        System.out.println("Simulation running...");
        System.out.println("---------------------");

        // Wait until thread finish
        for (Vendor vendor : vendors) {
            System.out.println("working vendor");
            try {
                Thread vendorThreadObj = new Thread(vendor);
                vendorThreadObj.join();
            } catch (InterruptedException e) {
                //logger.error("Vendor thread interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        for (Customer customer : customers) {
            System.out.println("working Customer");
            try {
                Thread customerThreadObj = new Thread(customer);
                customerThreadObj.join();
            } catch (InterruptedException e) {
                //logger.error("Customer thread interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Simulation Ended properly!");
    }
}
