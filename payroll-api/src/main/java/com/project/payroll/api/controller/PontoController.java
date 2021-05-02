package com.project.payroll.api.controller;

import com.project.payroll.api.dto.BuscarPontoDTO;
import com.project.payroll.api.dto.FolhaPontoDTO;
import com.project.payroll.api.entities.FolhaPonto;
import com.project.payroll.api.services.PontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
public class PontoController {

    private PontoService service;

    @Autowired
    public PontoController(PontoService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{user_id}/pontos", method = RequestMethod.POST)
    public ResponseEntity<Void> baterPonto(@PathVariable(value = "user_id") Long id, @RequestBody FolhaPontoDTO folhaPonto) {
        FolhaPonto ponto = service.baterPonto(folhaPonto, id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{data}").buildAndExpand(ponto.getPontoUsuarioPK().getDataPonto()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{user_id}/pontos", method = RequestMethod.GET)
    public ResponseEntity<List<FolhaPontoDTO>> listarPontosByDate(@PathVariable(value = "user_id") Long id, @RequestBody BuscarPontoDTO datas) {
        List<FolhaPontoDTO> pontos = service.recuperarPontosByMonthAndUserId(id, datas);
        return ResponseEntity.ok(pontos);
    }

    @RequestMapping(value = "/{user_id}/pontos/totalHoras", method = RequestMethod.GET)
    public ResponseEntity<BigDecimal> recuperarTotalHorasTrabalhadasByDate(@PathVariable(value = "user_id") Long id, @RequestBody BuscarPontoDTO datas) {
        BigDecimal totalHoras = service.recuperarTotalHorasTrabalhadasByMonthAndUserId(id, datas);
        return ResponseEntity.ok(totalHoras);
    }
}
