package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.infini.Pidev.entities.Settings;



public interface SettingsRepository extends JpaRepository<Settings,Long> {
}
