package  ma.zyn.app.ws.facade.admin.mensuel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelTypeEvenement;
import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelTypeEvenementCriteria;
import ma.zyn.app.service.facade.admin.mensuel.RapportEvenementMensuelTypeEvenementAdminService;
import ma.zyn.app.ws.converter.mensuel.RapportEvenementMensuelTypeEvenementConverter;
import ma.zyn.app.ws.dto.mensuel.RapportEvenementMensuelTypeEvenementDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/rapportEvenementMensuelTypeEvenement/")
public class RapportEvenementMensuelTypeEvenementRestAdmin {




    @Operation(summary = "Finds a list of all rapportEvenementMensuelTypeEvenements")
    @GetMapping("")
    public ResponseEntity<List<RapportEvenementMensuelTypeEvenementDto>> findAll() throws Exception {
        ResponseEntity<List<RapportEvenementMensuelTypeEvenementDto>> res = null;
        List<RapportEvenementMensuelTypeEvenement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<RapportEvenementMensuelTypeEvenementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a rapportEvenementMensuelTypeEvenement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RapportEvenementMensuelTypeEvenementDto> findById(@PathVariable Long id) {
        RapportEvenementMensuelTypeEvenement t = service.findById(id);
        if (t != null) {
            converter.init(true);
            RapportEvenementMensuelTypeEvenementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  rapportEvenementMensuelTypeEvenement")
    @PostMapping("")
    public ResponseEntity<RapportEvenementMensuelTypeEvenementDto> save(@RequestBody RapportEvenementMensuelTypeEvenementDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            RapportEvenementMensuelTypeEvenement myT = converter.toItem(dto);
            RapportEvenementMensuelTypeEvenement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                RapportEvenementMensuelTypeEvenementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  rapportEvenementMensuelTypeEvenement")
    @PutMapping("")
    public ResponseEntity<RapportEvenementMensuelTypeEvenementDto> update(@RequestBody RapportEvenementMensuelTypeEvenementDto dto) throws Exception {
        ResponseEntity<RapportEvenementMensuelTypeEvenementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RapportEvenementMensuelTypeEvenement t = service.findById(dto.getId());
            converter.copy(dto,t);
            RapportEvenementMensuelTypeEvenement updated = service.update(t);
            RapportEvenementMensuelTypeEvenementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of rapportEvenementMensuelTypeEvenement")
    @PostMapping("multiple")
    public ResponseEntity<List<RapportEvenementMensuelTypeEvenementDto>> delete(@RequestBody List<RapportEvenementMensuelTypeEvenementDto> dtos) throws Exception {
        ResponseEntity<List<RapportEvenementMensuelTypeEvenementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<RapportEvenementMensuelTypeEvenement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified rapportEvenementMensuelTypeEvenement")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }

    @Operation(summary = "find by rapportEvenementMensuel id")
    @GetMapping("rapportEvenementMensuel/id/{id}")
    public List<RapportEvenementMensuelTypeEvenementDto> findByRapportEvenementMensuelId(@PathVariable Long id){
        return findDtos(service.findByRapportEvenementMensuelId(id));
    }
    @Operation(summary = "delete by rapportEvenementMensuel id")
    @DeleteMapping("rapportEvenementMensuel/id/{id}")
    public int deleteByRapportEvenementMensuelId(@PathVariable Long id){
        return service.deleteByRapportEvenementMensuelId(id);
    }
    @Operation(summary = "find by typeEvenement code")
    @GetMapping("typeEvenement/code/{code}")
    public List<RapportEvenementMensuelTypeEvenementDto> findByTypeEvenementCode(@PathVariable String code){
        return findDtos(service.findByTypeEvenementCode(code));
    }
    @Operation(summary = "delete by typeEvenement code")
    @DeleteMapping("typeEvenement/code/{code}")
    public int deleteByTypeEvenementCode(@PathVariable String code){
        return service.deleteByTypeEvenementCode(code);
    }

    @Operation(summary = "Finds a rapportEvenementMensuelTypeEvenement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RapportEvenementMensuelTypeEvenementDto> findWithAssociatedLists(@PathVariable Long id) {
        RapportEvenementMensuelTypeEvenement loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        RapportEvenementMensuelTypeEvenementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds rapportEvenementMensuelTypeEvenements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RapportEvenementMensuelTypeEvenementDto>> findByCriteria(@RequestBody RapportEvenementMensuelTypeEvenementCriteria criteria) throws Exception {
        ResponseEntity<List<RapportEvenementMensuelTypeEvenementDto>> res = null;
        List<RapportEvenementMensuelTypeEvenement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<RapportEvenementMensuelTypeEvenementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated rapportEvenementMensuelTypeEvenements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RapportEvenementMensuelTypeEvenementCriteria criteria) throws Exception {
        List<RapportEvenementMensuelTypeEvenement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<RapportEvenementMensuelTypeEvenementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets rapportEvenementMensuelTypeEvenement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RapportEvenementMensuelTypeEvenementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<RapportEvenementMensuelTypeEvenementDto> findDtos(List<RapportEvenementMensuelTypeEvenement> list){
        converter.initObject(true);
        List<RapportEvenementMensuelTypeEvenementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<RapportEvenementMensuelTypeEvenementDto> getDtoResponseEntity(RapportEvenementMensuelTypeEvenementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public RapportEvenementMensuelTypeEvenementRestAdmin(RapportEvenementMensuelTypeEvenementAdminService service, RapportEvenementMensuelTypeEvenementConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final RapportEvenementMensuelTypeEvenementAdminService service;
    private final RapportEvenementMensuelTypeEvenementConverter converter;





}
