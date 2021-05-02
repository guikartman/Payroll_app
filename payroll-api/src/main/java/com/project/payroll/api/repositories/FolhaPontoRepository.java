package com.project.payroll.api.repositories;

import com.project.payroll.api.entities.FolhaPonto;
import com.project.payroll.api.entities.PontoUsuarioPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FolhaPontoRepository extends JpaRepository<FolhaPonto, PontoUsuarioPK> {

    List<FolhaPonto> findByPontoUsuarioPKUsuarioIdAndPontoUsuarioPKDataPontoBetween(final Long usuarioId,final LocalDate start, final LocalDate end);
}
