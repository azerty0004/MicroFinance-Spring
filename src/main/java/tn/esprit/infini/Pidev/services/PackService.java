package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.entities.Pack;
import tn.esprit.infini.Pidev.Repository.PackRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class PackService implements IPackService {
    PackRepository packRepository;

    @Override
    public List<Pack> retrieveAllPack() {
        return (List<Pack>) packRepository.findAll();    }

    @Override
    public Pack addPack(Pack p) {
        return packRepository.save(p);
    }

    @Override
    public Pack updatePack(Pack p) {
        return packRepository.save(p);
    }

    @Override
    public Pack retrievePack(Integer idPack) {
        return packRepository.findById(idPack).get();
    }

    @Override
    public void deletePack(Integer idPack) {
        packRepository.deleteById(idPack);
    }
}
