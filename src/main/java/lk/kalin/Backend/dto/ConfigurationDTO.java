package lk.kalin.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConfigurationDTO {
    private int totalTickets;
    private int releaseRate;
    private int retrievalRate;
    private int maxCapacity;
    private int numVendors;
    private int numCustomers;

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getNumCustomers() {
        return numCustomers;
    }

    public int getNumVendors() {
        return numVendors;
    }

    public int getReleaseRate() {
        return releaseRate;
    }

    public int getRetrievalRate() {
        return retrievalRate;
    }

    public int getTotalTickets() {
        return totalTickets;
    }
}
