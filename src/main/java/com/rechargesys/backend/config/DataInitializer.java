package com.rechargesys.backend.config;

import com.rechargesys.backend.entity.Plan;
import com.rechargesys.backend.entity.Recharge;
import com.rechargesys.backend.entity.User;
import com.rechargesys.backend.repository.PlanRepository;
import com.rechargesys.backend.repository.RechargeRepository;
import com.rechargesys.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final UserRepository userRepository;
    private final PlanRepository planRepository;
    private final RechargeRepository rechargeRepository;

    public DataInitializer(UserRepository userRepository,
                           PlanRepository planRepository,
                           RechargeRepository rechargeRepository) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
        this.rechargeRepository = rechargeRepository;
    }

    @Override
    public void run(String... args) {
        // Seed users if empty
        if (userRepository.count() == 0) {
            log.info("Seeding initial subscribers...");
            userRepository.saveAll(List.of(
                new User("gRPYi40dmzA", "vijay", "0000000001", "1"),
                new User("5zR02RgAY9U", "Siva", "0000000002", "2"),
                new User("4xrxEEFVqYo", "ravi", "3333333333", "333"),
                new User("xcRzX0eBQjo", "Sudharsan", "9998887777", "987"),
                new User("DoOjlqhERks", "vijay", "0101010101", "01")
            ));
        }

        // Seed telecom plans if empty
        if (planRepository.count() == 0) {
            log.info("Seeding telecom tariff catalog...");
            planRepository.saveAll(List.of(
                // Airtel
                new Plan("H5-qO2RMK2A", "Airtel", 299, "1.5GB/day", "28 days"),
                new Plan("y7h7vb1INkU", "Airtel", 379, "2GB/day", "28 days"),
                new Plan("b2PVH6gidrU", "Airtel", 579, "1.5GB/day", "56 days"),
                new Plan("SyQ0JCYAfHw", "Airtel", 649, "2GB/day", "56 days"),
                new Plan("9sJ_SFFdkr8", "Airtel", 859, "1.5GB/day", "84 days"),
                new Plan("VG9uXp3Z8Vc", "Airtel", 979, "2GB/day", "84 days"),
                new Plan("5flIvKqdg3M", "Airtel", 3599, "2GB/day", "365 days"),

                // Jio
                new Plan("Tz4wC67w-q4", "Jio", 299, "1.5GB/day", "28 days"),
                new Plan("_CsTft4t5eg", "Jio", 349, "2GB/day", "28 days"),
                new Plan("KTzXA8hrtx0", "Jio", 579, "1.5GB/day", "56 days"),
                new Plan("AY8IDX9aHc4", "Jio", 629, "2GB/day", "56 days"),
                new Plan("2Dj75Xjy3cA", "Jio", 889, "1.5GB/day", "84 days"),
                new Plan("lwllgXoZvls", "Jio", 859, "2GB/day", "84 days"),
                new Plan("HmkW3wbwY7w", "Jio", 3599, "2GB/day", "365 days"),

                // Vi
                new Plan("ah_meLL0aHI", "Vi", 349, "1.5GB/day", "28 days"),
                new Plan("TcuL2y1Y38s", "Vi", 365, "2GB/day", "28 days"),
                new Plan("4Ea27mPpoKE", "Vi", 579, "1.5GB/day", "56 days"),
                new Plan("XE5uElSlp9I", "Vi", 649, "2GB/day", "56 days"),
                new Plan("idIlfhKVwlo", "Vi", 859, "1.5GB/day", "84 days"),
                new Plan("gNS5wxnlECc", "Vi", 979, "2GB/day", "84 days"),
                new Plan("bEmYqsBybRc", "Vi", 3599, "2GB/day", "365 days"),

                // BSNL
                new Plan("VSKI7m1CyTM", "BSNL", 187, "1.5GB/day", "28 days"),
                new Plan("BQgZ2ySgwh4", "BSNL", 199, "2GB/day", "28 days"),
                new Plan("F6bl7TMXJaE", "BSNL", 329, "1.5GB/day", "56 days"),
                new Plan("V8umJREDujc", "BSNL", 379, "2GB/day", "56 days"),
                new Plan("3tPxcYPcXDg", "BSNL", 485, "1.5GB/day", "84 days"),
                new Plan("XRYUBVIT4Es", "BSNL", 521, "2GB/day", "84 days"),
                new Plan("rUxEAgHEFVI", "BSNL", 2025, "2GB/day", "365 days")
            ));
        }

        // Seed sample recharges if empty
        if (rechargeRepository.count() == 0) {
            log.info("Seeding sample transaction history...");
            rechargeRepository.saveAll(List.of(
                new Recharge("AV_474L5l1g", "9922334412", "BSNL", 2025, "UPI", "Success", "4/18/2026, 4:23:58 PM", "0101010101"),
                new Recharge("kjFs_kOxk5A", "1221312112", "Jio", 349, "UPI", "Success", "4/18/2026, 4:24:39 PM", "0000000002"),
                new Recharge("Jz1FLKdzOD0", "1221222222", "Jio", 299, "UPI", "Success", "4/18/2026, 4:32:19 PM", "0000000001"),
                new Recharge("IIOHI8EYpKs", "3242324234", "Vi", 979, "UPI", "Success", "4/18/2026, 5:38:54 PM", "9998887777"),
                new Recharge("TfTpvOThWLc", "6576565765", "Vi", 979, "Card", "Success", "4/18/2026, 5:39:29 PM", "9998887777")
            ));
        }
    }
}

