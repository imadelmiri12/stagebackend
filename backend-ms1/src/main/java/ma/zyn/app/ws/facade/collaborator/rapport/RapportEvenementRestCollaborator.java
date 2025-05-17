package  ma.zyn.app.ws.facade.collaborator.rapport;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.rapport.RapportEvenement;
import ma.zyn.app.dao.criteria.core.rapport.RapportEvenementCriteria;
import ma.zyn.app.service.facade.collaborator.rapport.RapportEvenementCollaboratorService;
import ma.zyn.app.ws.converter.rapport.RapportEvenementConverter;
import ma.zyn.app.ws.dto.rapport.RapportEvenementDto;
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
@RequestMapping("/api/collaborator/rapportEvenement/")
public class RapportEvenementRestCollaborator {




    @Operation(summary = "Finds a list of all rapportEvenements")
    @GetMapping("")
    public ResponseEntity<List<RapportEvenementDto>> findAll() throws Exception {
        ResponseEntity<List<RapportEvenementDto>> res = null;
        List<RapportEvenement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<RapportEvenementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all rapportEvenements")
    @GetMapping("optimized")
    public ResponseEntity<List<RapportEvenementDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<RapportEvenementDto>> res = null;
        List<RapportEvenement> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<RapportEvenementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a rapportEvenement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RapportEvenementDto> findById(@PathVariable Long id) {
        RapportEvenement t = service.findById(id);
        if (t != null) {
            converter.init(true);
            RapportEvenementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a rapportEvenement by code")
    @GetMapping("code/{code}")
    public ResponseEntity<RapportEvenementDto> findByCode(@PathVariable String code) {
	    RapportEvenement t = service.findByReferenceEntity(new RapportEvenement(code));
        if (t != null) {
            converter.init(true);
            RapportEvenementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  rapportEvenement")
    @PostMapping("")
    public ResponseEntity<RapportEvenementDto> save(@RequestBody RapportEvenementDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            RapportEvenement myT = converter.toItem(dto);
            RapportEvenement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                RapportEvenementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  rapportEvenement")
    @PutMapping("")
    public ResponseEntity<RapportEvenementDto> update(@RequestBody RapportEvenementDto dto) throws Exception {
        ResponseEntity<RapportEvenementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RapportEvenement t = service.findById(dto.getId());
            converter.copy(dto,t);
            RapportEvenement updated = service.update(t);
            RapportEvenementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of rapportEvenement")
    @PostMapping("multiple")
    public ResponseEntity<List<RapportEvenementDto>> delete(@RequestBody List<RapportEvenementDto> dtos) throws Exception {
        ResponseEntity<List<RapportEvenementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<RapportEvenement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified rapportEvenement")
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

    @Operation(summary = "find by journalAmelioaration id")
    @GetMapping("journalAmelioaration/id/{id}")
    public List<RapportEvenementDto> findByJournalAmelioarationId(@PathVariable Long id){
        return findDtos(service.findByJournalAmelioarationId(id));
    }
    @Operation(summary = "delete by journalAmelioaration id")
    @DeleteMapping("journalAmelioaration/id/{id}")
    public int deleteByJournalAmelioarationId(@PathVariable Long id){
        return service.deleteByJournalAmelioarationId(id);
    }

    @Operation(summary = "Finds a rapportEvenement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RapportEvenementDto> findWithAssociatedLists(@PathVariable Long id) {
        RapportEvenement loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        RapportEvenementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds rapportEvenements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RapportEvenementDto>> findByCriteria(@RequestBody RapportEvenementCriteria criteria) throws Exception {
        ResponseEntity<List<RapportEvenementDto>> res = null;
        List<RapportEvenement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<RapportEvenementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated rapportEvenements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RapportEvenementCriteria criteria) throws Exception {
        List<RapportEvenement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<RapportEvenementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets rapportEvenement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RapportEvenementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<RapportEvenementDto> findDtos(List<RapportEvenement> list){
        converter.initObject(true);
        List<RapportEvenementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<RapportEvenementDto> getDtoResponseEntity(RapportEvenementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public RapportEvenementRestCollaborator(RapportEvenementCollaboratorService service, RapportEvenementConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final RapportEvenementCollaboratorService service;
    private final RapportEvenementConverter converter;





}
