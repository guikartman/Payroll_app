package com.project.payroll.api.services;

import com.project.payroll.api.dto.BuscarPontoDTO;
import com.project.payroll.api.dto.FolhaPontoDTO;
import com.project.payroll.api.entities.FolhaPonto;
import com.project.payroll.api.entities.PontoUsuarioPK;
import com.project.payroll.api.entities.Usuario;
import com.project.payroll.api.repositories.FolhaPontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PontoService {

    private FolhaPontoRepository repository;
    private UserService usuarioService;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Autowired
    public PontoService(FolhaPontoRepository repository, UserService usuarioService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
    }

    public FolhaPonto baterPonto(FolhaPontoDTO dto, Long funcionarioId) {
        FolhaPonto ponto = fromDTO(dto, funcionarioId);
        return this.repository.save(ponto);
    }

    public List<FolhaPontoDTO> recuperarPontosByMonthAndUserId(Long userId, BuscarPontoDTO datas) {
        LocalDate inicio = formatDate(datas.getInicio());
        LocalDate fim = formatDate(datas.getFim());
        List<FolhaPonto> pontos = repository.findByPontoUsuarioPKUsuarioIdAndPontoUsuarioPKDataPontoBetween(userId,inicio, fim);
        return pontos.stream().map(ponto -> new FolhaPontoDTO(ponto)).collect(Collectors.toList());
    }

    public BigDecimal recuperarTotalHorasTrabalhadasByMonthAndUserId(Long userId, BuscarPontoDTO datas) {
        LocalDate inicio = formatDate(datas.getInicio());
        LocalDate fim = formatDate(datas.getFim());
        List<FolhaPonto> pontos = repository.findByPontoUsuarioPKUsuarioIdAndPontoUsuarioPKDataPontoBetween(userId,inicio, fim);
        BigDecimal sum = BigDecimal.ZERO;
        for (FolhaPonto ponto: pontos) {
            long periodoTrabalhado = ChronoUnit.HOURS.between(ponto.getHoraEntrada(), ponto.getHoraSaida());
            long periodoPausa = ChronoUnit.HOURS.between(ponto.getHoraInicioAlmoco(), ponto.getHoraFimAlmoco());
            sum = sum.add(BigDecimal.valueOf(periodoTrabalhado));
            sum = sum.subtract(BigDecimal.valueOf(periodoPausa));
        }
        return sum;
    }

    private FolhaPonto fromDTO(FolhaPontoDTO dto, Long funcionarioId) {
        Usuario usuario = usuarioService.findUsuarioById(funcionarioId);
        LocalDate dataPonto = formatDate(dto.getDataPonto());
        return new FolhaPonto(
                new PontoUsuarioPK(funcionarioId,dataPonto),
                usuario,
                dto.getEntrada(),
                dto.getInicioPausa(),
                dto.getFimPausa(),
                dto.getSaida() );
    }

    private LocalDate formatDate(String date) {
        return LocalDate.parse(date, dateFormatter);
    }
}
