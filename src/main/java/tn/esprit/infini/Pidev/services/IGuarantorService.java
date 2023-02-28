package tn.esprit.infini.Pidev.services;

import tn.esprit.infini.Pidev.entities.Guarantor;

import java.util.List;

public interface IGuarantorService {
    List<Guarantor> retrieveAllGuarantor();

    Guarantor addGuarantor(Guarantor g);

    Guarantor updateGuarantor (Guarantor g );

    Guarantor retrieveGuarantor (Integer idGurantor);

    void deleteGuarantor( Integer idGurantor);
}

