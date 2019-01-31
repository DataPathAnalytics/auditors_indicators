package com.datapath.integration.scheduling;

import com.datapath.integration.services.ContractUpdatesManager;
import com.datapath.integration.services.TenderUpdatesManager;
import com.datapath.integration.utils.ServiceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TendersUpdateScheduler {

    private static final int TENDERS_UPDATES_RATE = 100_000;
    private static final int TENDERS_UPDATES_DELAY = 100_000;

    private static final int CONTRACTS_UPDATES_RATE = 100_000;
    private static final int CONTRACTS_UPDATES_DELAY = 500_000;

    private TenderUpdatesManager tenderUpdatesManager;
    private ContractUpdatesManager contractUpdatesManager;

    public TendersUpdateScheduler(TenderUpdatesManager tenderUpdatesManager,
                                  ContractUpdatesManager contractUpdatesManager) {
        this.tenderUpdatesManager = tenderUpdatesManager;
        this.contractUpdatesManager = contractUpdatesManager;
    }

    @Scheduled(fixedRate = TENDERS_UPDATES_RATE, initialDelay = TENDERS_UPDATES_DELAY)
    public void checkTendersForUpdates() {
        try {
            if (tenderUpdatesManager.getServiceStatus() == ServiceStatus.ENABLED) {
                this.tenderUpdatesManager.loadLastModifiedTenders();
            } else {
                log.info("TendersUpdateManager disabled.");
            }
        } catch (Exception ex) {
            log.error("Error CheckTendersForUpdates failed {}", ex.getMessage());
            this.tenderUpdatesManager.changeServiceStatus(ServiceStatus.ENABLED);
        }
    }

    @Scheduled(fixedRate = CONTRACTS_UPDATES_RATE, initialDelay = CONTRACTS_UPDATES_DELAY)
    public void checkContractsForUpdates() {
        try {
            if (contractUpdatesManager.getServiceStatus() == ServiceStatus.ENABLED) {
                this.contractUpdatesManager.loadLastModifiedContracts();
            } else {
                log.info("ContractsUpdateManager disabled.");
            }
        } catch (Exception ex) {
            log.error("Error CheckContractsForUpdates failed {}", ex.getMessage());
            this.contractUpdatesManager.changeServiceStatus(ServiceStatus.ENABLED);
        }
    }
}