package lk.kalin.Backend.service;

import jakarta.transaction.Transactional;
import lk.kalin.Backend.Main;
import lk.kalin.Backend.dto.ConfigurationDTO;
import lk.kalin.Backend.dto.SimData;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ConfigurationService {
    public ConfigurationDTO setConfiguration(ConfigurationDTO configurationDTO) {
        Main.setConfiguration(configurationDTO);
        return configurationDTO;
    }

    public SimData getData() {
        SimData simData = new SimData();
        simData.setAvailable(Main.ticketPool.available());
        simData.setSold(Main.ticketPool.getSold());
        simData.setDue(Main.ticketPool.dueTicket());
        simData.setTickets(Main.ticketPool.getTickets());
        return simData;
    }
}
