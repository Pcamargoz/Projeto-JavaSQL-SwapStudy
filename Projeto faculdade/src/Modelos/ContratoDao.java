package Modelos;

import program.PuxarContratos;

import java.util.List;

public interface ContratoDao {
    void insert(PuxarContratos obj);
    void update(PuxarContratos obj);
    void deleteById(Integer id);
    PuxarContratos findById(Integer id);
    List<PuxarContratos> findALL();
}
